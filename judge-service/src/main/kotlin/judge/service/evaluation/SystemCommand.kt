package judge.service.evaluation

import org.slf4j.LoggerFactory

/**
 * A utility class for running external processes and capturing their results.
 */
class SystemCommand(val command: List<String>) {

    fun execute(): CommandResult {
        log.trace("Executing command: {}", command)
        var process = Runtime.getRuntime().exec(command.toTypedArray())

        var stdout = process.readStdout().cutoff()
        var stderr = process.readStderr().cutoff()
        var exitCode = process.waitFor()

        var result = CommandResult(exitCode, stdout, stderr)
        log.trace("Command executed: {}", result)
        return result
    }

    data class CommandResult(val exitCode: Int, val stdout: String, val stderr: String) {
        fun isSuccess() = exitCode == 0
        fun isFailure() = !isSuccess()
    }

    private companion object {
        val log = LoggerFactory.getLogger(SystemCommand::class.java)!!

        const val CUTOFF_LENGTH = 16_000
        fun String.cutoff(): String {
            return if (this.length > CUTOFF_LENGTH) this.substring(0, CUTOFF_LENGTH) + "<...>" else this
        }

        fun Process.readStdout(): String = inputStream.bufferedReader().use { it.readText() }
        fun Process.readStderr(): String = errorStream.bufferedReader().use { it.readText() }
    }
}
