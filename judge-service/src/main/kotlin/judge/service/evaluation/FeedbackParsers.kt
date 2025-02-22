package judge.service.evaluation

import judge.FeedbackEvent.Status
import problem.Resources
import kotlin.time.Duration.Companion.milliseconds


sealed interface FeedbackParser<T> {

    fun parse(): T

    companion object {

        fun compilation(stderr: String): String? {
            return CompilationFeedbackParser(stderr).parse()
        }

        fun accepted(stderr: String): Boolean {
            return SubmissionAcceptedParser(stderr).parse()
        }

        fun limitExceeded(stderr: String): Status? {
            return ResourceLimitExceededParser(stderr).parse()
        }

        fun wrongAnswer(stderr: String): String? {
            return WrongAnswerParser(stderr).parse()
        }

        fun resourcesUsage(stderr: String): Resources? {
            return ResourceUsageParser(stderr).parse()
        }

        private const val ACCEPTED_MARKER = "ACCEPTED"
        private const val FEEDBACK_MARKER = "[JUDGE_FEEDBACK]"
        private const val WRONG_ANSWER_MARKER = "WRONG_ANSWER"

        private fun String.lastIntValue(): Int {
            return this.substringAfterLast(' ').toInt()
        }
    }

    /**
     * A parser implementation for extracting a feedback from the stderr output of a compilation process.
     */
    private class CompilationFeedbackParser(stderr: String) : FeedbackParser<String?> {
        val iterator = stderr.lineSequence().iterator()
        override fun parse(): String? {
            while (iterator.hasNext()) {
                var line = iterator.next()
                if (line == FEEDBACK_MARKER) {
                    var sb = StringBuilder()
                    while (iterator.hasNext()) {
                        line = iterator.next()
                        if (line == FEEDBACK_MARKER) {
                            return sb.toString()
                        }
                        sb.appendLine(line)
                    }
                }
            }
            return null
        }
    }

    /**
     * Parser implementation that processes the stderr output to determine whether a submission
     * has been successfully accepted.
     */
    private class SubmissionAcceptedParser(stderr: String) : FeedbackParser<Boolean> {
        val iterator = stderr.lineSequence().iterator()
        override fun parse(): Boolean {
            while (iterator.hasNext()) {
                var line = iterator.next()
                if (line == FEEDBACK_MARKER) {
                    if (!iterator.hasNext() || iterator.next() != ACCEPTED_MARKER) return false
                    if (!iterator.hasNext() || iterator.next() != FEEDBACK_MARKER) return false
                    return true
                }
            }
            return false
        }
    }

    /**
     * Parses the error feedback from the standard error stream to extract details
     * about a "Wrong Answer" result during the execution of a problem evaluation.
     */
    private class WrongAnswerParser(stderr: String) : FeedbackParser<String?> {
        val iterator = stderr.lineSequence().iterator()
        override fun parse(): String? {
            while (iterator.hasNext()) {
                var line = iterator.next()
                if (line == FEEDBACK_MARKER) {
                    if (!iterator.hasNext() || iterator.next() != WRONG_ANSWER_MARKER) return null
                    var sb = StringBuilder()
                    while (iterator.hasNext()) {
                        line = iterator.next()
                        if (line == FEEDBACK_MARKER) {
                            return sb.toString()
                        }
                        sb.appendLine(line)
                    }
                }
            }
            return null
        }
    }

    /**
     * A parser that extracts resource usage details, such as memory and time,
     * from a given stderr output.
     */
    private class ResourceUsageParser(stderr: String) : FeedbackParser<Resources?> {
        val iterator = stderr.lineSequence().iterator()
        override fun parse(): Resources? {
            var time: Int? = null
            var memory: Int? = null
            while (iterator.hasNext()) {
                var line = iterator.next()
                when {
                    !line.startsWith("[S]") -> continue
                    line.contains("cgroup_memory_max_usage") -> memory = line.lastIntValue()
                    line.contains("time") -> time = line.lastIntValue()
                }
            }
            return if (time == null || memory == null) null
            else Resources(time.milliseconds, memory)
        }
    }

    /**
     * Parses the stderr output produced by a system command to determine specific resource limit
     * excesses (e.g., memory or time limits).
     */
    private class ResourceLimitExceededParser(stderr: String) : FeedbackParser<Status?> {
        val iterator = stderr.lineSequence().iterator()
        override fun parse(): Status? {
            var status: Status? = null
            while (iterator.hasNext()) {
                var line = iterator.next()
                if (line.startsWith("[S]") && line.contains("cgroup_memory_failcnt")) {
                    val count = line.lastIntValue()
                    if (count > 0)
                        status = Status.MEMORY_LIMIT_EXCEEDED
                }
                if (line.startsWith("[I]") && line.endsWith("Killing it")) {
                    status = Status.TIME_LIMIT_EXCEEDED
                }
            }
            return status
        }
    }
}
