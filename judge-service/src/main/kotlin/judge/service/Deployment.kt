package judge.service

import problem.Problem
import problem.Problem.Project
import java.nio.file.Files
import java.nio.file.Path


class Deployment private constructor(
    private val tmpDir: Path,
    private val project: Project
) : AutoCloseable {

    fun compileCommand(): List<String> {
        return command(project.files.compile.name)
    }

    fun executeCommand(): List<String> {
        return command(project.files.execute.name)
    }

    override fun close() {
        fun delete(curr: Path) {
            if (Files.isDirectory(curr))
                Files.list(curr).forEach { delete(it) }
            Files.deleteIfExists(curr)
        }
        delete(tmpDir)
    }

    private fun command(script: String) = ArrayList<String>().apply {
        addAll(listOf("sudo", "nsjail"))
        add("--config=/etc/runtime/${project.lang.runtimeKey}.cfg")
        add("--bindmount=${tmpDir}:/app")
        add("--cwd=/app")
        addAll(listOf("--", "/usr/bin/bash", script))
    }

    companion object {

        fun of(project: Project, solution: String): Deployment {
            var dir = Files.createTempDirectory("workspace")
            return Deployment(dir, project.deploy(dir, solution))
        }

        /**
         * Deploys the project files to the specified directory.
         *
         * @param dir The directory where the project files will be deployed.
         * @param solution The solution content to be deployed.
         */
        private fun Project.deploy(dir: Path, solution: String): Project {
            for (file in this.files.asListNoSolution()) {
                file.deploy(dir)
            }
            files.solution.deploy(dir, solution)
            return this
        }

        /**
         * Deploys the project file into the specified directory.
         *
         * @param dir The target directory where the file will be deployed.
         * @param data The content of the file to be deployed.
         */
        private fun Problem.ProjectFile.deploy(dir: Path, data: String = this.content) {
            val path = Path.of(this.name)
            path.parent?.let { Files.createDirectories(dir.resolve(it)) }
            Files.write(dir.resolve(path), data.toByteArray())
        }

        private fun Problem.ProjectFiles.asListNoSolution(): List<Problem.ProjectFile> {
            return listOf(compile, execute, validator, evaluator).plus(resources)
        }
    }
}
