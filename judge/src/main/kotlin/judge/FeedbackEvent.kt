package judge

data class FeedbackEvent(
    val id: SubmissionID,
    val status: Status,
    val stdout: String? = null,
    val stderr: String? = null,
    val time: Int? = null,
    val memory: Int? = null,
) {

    enum class Status {
        EVALUATION_PENDING,
        EVALUATION_RUNNING,
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
