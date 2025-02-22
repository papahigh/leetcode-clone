package judge.service.evaluation

import jakarta.inject.Singleton
import judge.SolutionCode
import judge.SubmissionEvent
import judge.service.evaluation.SystemCommand.CommandResult
import problem.Problem.*
import problem.ProblemID
import problem.ProjectRepository
import java.nio.file.Files
import java.nio.file.Path
import kotlin.math.roundToInt


interface Deployment : AutoCloseable {
    fun compile(): CommandResult?
    fun execute(): CommandResult
}

interface DeploymentService {
    fun deploy(event: SubmissionEvent): Deployment
}

@Singleton
class DeploymentServiceImpl(
    private val projectRepository: ProjectRepository
) : DeploymentService {

    override fun deploy(event: SubmissionEvent): Deployment {

        val project = getProject(event)
        var tmpDir = createTmpDir(event)

        project.deploy(tmpDir, event.code)
        tmpDir.chown()

        return object : Deployment {

            override fun compile(): CommandResult? = project.compile?.let {
                val command = assemble(it)
                return SystemCommand(command).execute()
            }

            override fun execute(): CommandResult {
                val command = assemble(project.execute)
                return SystemCommand(command).execute()
            }

            override fun close() {
                tmpDir.remove()
            }

            private fun assemble(action: ProjectAction) = ArrayList<String>().apply {
                addAll(listOf("sudo", "nsjail"))
                add("--config=/etc/runtime/${project.lang.runtimeKey}.cfg")
                add("--cgroup_mem_max=${action.resources.memory.bytes()}")
                add("--time_limit=${action.resources.time.seconds()}")
                add("--bindmount=${tmpDir}:/app")
                add("--cwd=/app")
                addAll(listOf("--", "/usr/bin/bash", action.script.name))
            }
        }
    }

    private fun createTmpDir(event: SubmissionEvent): Path {
        return Files.createTempDirectory("submission_${event.id}__")
    }

    private fun getProject(event: SubmissionEvent): Project {
        return projectRepository.findProject(event.problemId, event.lang)
            ?: throw ProjectNotFoundException(event.problemId, event.lang)
    }

    private class ProjectNotFoundException(problemId: ProblemID, lang: Language) :
        RuntimeException("Project Not Found: $problemId, $lang")

    private companion object {

        private fun Project.deploy(path: Path, solution: SolutionCode) {

            val assets = ArrayList<ProjectFile>()
                .apply {
                    addAll(files.resources)
                    add(files.validator)
                    add(files.evaluator)
                    add(execute.script)
                    compile?.let { add(it.script) }
                    add(input)
                }

            for (file in assets)
                file.deploy(path)

            files.solution.deploy(path, solution)
        }

        private fun ProjectFile.deploy(path: Path, data: String = this.content) {
            val file = Path.of(this.name)
            file.parent?.let { Files.createDirectories(path.resolve(it)) }
            Files.write(path.resolve(file), data.toByteArray())
        }

        private fun Path.chown() {
            Runtime.getRuntime().exec(arrayOf("sudo", "chown", "-R", "nobody:nogroup", toString())).waitFor()
        }

        private fun Path.remove() {
            Runtime.getRuntime().exec(arrayOf("sudo", "rm", "-rf", toString())).waitFor()
        }
    }
}
