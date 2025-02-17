package judge.service

import io.micrometer.core.annotation.Timed
import jakarta.inject.Singleton
import judge.FeedbackEvent
import judge.FeedbackEvent.Status.*
import judge.SubmissionEvent
import org.slf4j.LoggerFactory
import problem.Problem.Language
import problem.Problem.Project
import problem.ProblemID
import problem.ProjectRepository
import problem.Resources


@Singleton
class JudgeService(
    private val projectRepository: ProjectRepository
) {

    @Timed(value = "judge_service.evaluate")
    fun evaluate(event: SubmissionEvent): FeedbackEvent {
        log.trace("Processing submission {}", event.id)

        var project = getProject(event)
        if (project != null) {

            Deployment.of(project, event.code).use { deployment ->

                // ::WIP::
                log.trace("Compilation started for {}", event.id)
                val compile = SystemCommand(deployment.compileCommand())
                log.trace("Compilation finished for {}", event.id)

                if (compile.result.isFailure()) {
                    return FeedbackEvent(
                        id = event.id,
                        status = COMPILATION_ERROR,
                        output = compile.result.stderr,
                        resources = Resources.NOT_AVAILABLE
                    )
                }

                log.trace("Execution started for {}", event.id)
                val execute = SystemCommand(deployment.executeCommand())
                log.trace("Execution finished for {}", event.id)

                if (execute.result.isFailure()) {
                    return FeedbackEvent(
                        id = event.id,
                        status = RUNTIME_ERROR,
                        output = execute.result.stdout,
                        resources = Resources.NOT_AVAILABLE
                    )
                }

                return FeedbackEvent(
                    id = event.id,
                    status = ACCEPTED,
                    output = execute.result.stdout,
                    resources = Resources.NOT_AVAILABLE
                )
                // ::WIP::
            }

        } else {
            log.error("Project not found {}", event)
            throw ProjectNotFoundException(event.problemId, event.lang)
        }
    }

    private class ProjectNotFoundException(problemId: ProblemID, lang: Language) :
        RuntimeException("Project Not Found: $problemId, $lang")

    private fun getProject(it: SubmissionEvent): Project? {
        return projectRepository.findProject(it.problemId, it.lang)
    }

    /**
     * A utility class for running external processes and capturing their results.
     */
    private class SystemCommand(command: List<String>) {

        val result: CommandResult

        init {
            log.trace("Executing command: {}", command)
            var process = Runtime.getRuntime().exec(command.toTypedArray())

            var stdout = process.readStdOut()
            var stderr = process.readStdErr()
            var exitCode = process.waitFor()

            result = CommandResult(exitCode, stdout, stderr)
            log.trace("Command executed: {}", result)
        }

        data class CommandResult(val exitCode: Int, val stdout: String, val stderr: String) {
            fun isSuccess() = exitCode == 0
            fun isFailure() = !isSuccess()
            fun hasStderr() = stderr.isNotEmpty()
        }

        private companion object {
            fun Process.readStdOut(): String = inputStream.bufferedReader().use { it.readText() }
            fun Process.readStdErr(): String = errorStream.bufferedReader().use { it.readText() }
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(JudgeService::class.java)
    }
}