package judge.service.evaluation

import jakarta.inject.Singleton
import judge.FeedbackEvent
import judge.FeedbackEvent.Status
import judge.FeedbackEvent.Status.*
import judge.SubmissionEvent
import judge.service.evaluation.SystemCommand.CommandResult

interface FeedbackProvider {
    fun ofCompileResult(event: SubmissionEvent, result: CommandResult): FeedbackEvent
    fun ofExecuteResult(event: SubmissionEvent, result: CommandResult): FeedbackEvent
}

@Singleton
class FeedbackProviderImpl : FeedbackProvider {

    override fun ofCompileResult(event: SubmissionEvent, result: CommandResult): FeedbackEvent {
        val stderr = FeedbackParser.compilation(result.stderr)
        return createFeedback(event, result, status = COMPILATION_ERROR, stdout = null, stderr = stderr)
    }

    override fun ofExecuteResult(event: SubmissionEvent, result: CommandResult): FeedbackEvent {

        when (result.exitCode) {
            ACCEPTED_EXIT_CODE -> {
                val accepted = FeedbackParser.accepted(result.stderr)
                return if (accepted) {
                    val resources = FeedbackParser.resourcesUsage(result.stderr)
                    val time = resources?.time?.millis()
                    val memory = resources?.memory?.bytes()
                    createFeedback(event, result, status = ACCEPTED, time = time, memory = memory)
                } else createFeedback(event, result)
            }

            WRONG_ANSWER_EXIT_CODE -> {
                val stderr = FeedbackParser.wrongAnswer(result.stderr)
                return if (stderr != null) {
                    createFeedback(event, result, status = WRONG_ANSWER, stderr = stderr)
                } else createFeedback(event, result)
            }

            LIMIT_EXCEEDED_EXIT_CODE -> {
                val status = FeedbackParser.limitExceeded(result.stderr)
                return createFeedback(event, result, status = status ?: RUNTIME_ERROR)
            }

            else -> return createFeedback(event, result)
        }
    }

    private fun createFeedback(
        event: SubmissionEvent,
        result: CommandResult,
        stdout: String? = result.stdout,
        stderr: String? = null,
        status: Status = RUNTIME_ERROR,
        time: Int? = null,
        memory: Int? = null,
    ) = FeedbackEvent(event.id, status, stdout = stdout, stderr = stderr, time = time, memory = memory)

    private companion object {
        const val ACCEPTED_EXIT_CODE = 0
        const val WRONG_ANSWER_EXIT_CODE = 65
        const val LIMIT_EXCEEDED_EXIT_CODE = 137
    }
}
