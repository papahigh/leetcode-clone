package judge

import problem.Resources

data class Feedback(
    val id: SubmissionID,
    val status: Status,
    val output: String?,
    val resources: Resources
) {

    enum class Status {
        COMPILATION_ERROR,
        RUNTIME_ERROR,
        TIME_LIMIT_EXCEEDED,
        MEMORY_LIMIT_EXCEEDED,
        WRONG_ANSWER,
        ACCEPTED,
        ;
    }

}
