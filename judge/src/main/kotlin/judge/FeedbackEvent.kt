package judge

data class FeedbackEvent(
    val id: SubmissionID,
    val status: Status,
    val stdout: String? = null,
    val stderr: String? = null,
    val memory: Int? = null,
    val time: Int? = null,
) {

    enum class Status {
        EVALUATION_PENDING,
        EVALUATION_STARTED,
        EVALUATION_FAILED,
        COMPILATION_ERROR,
        RUNTIME_ERROR,
        TIME_LIMIT_EXCEEDED,
        MEMORY_LIMIT_EXCEEDED,
        WRONG_ANSWER,
        ACCEPTED,
        ;
    }

}
