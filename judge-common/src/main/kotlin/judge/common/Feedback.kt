package judge.common

data class Feedback(
    val submissionId: String,
    val status: Status,
) {

    enum class Status {
        COMPILATION_ERROR,
        RUNTIME_ERROR,
        TIME_LIMIT_EXCEEDED,
        MEMORY_LIMIT_EXCEEDED,
        WRONG_ANSWER,
        SUCCESS,
        ;

        fun of(submission: Submission): Feedback {
            return Feedback(submission.id, this)
        }
    }

}
