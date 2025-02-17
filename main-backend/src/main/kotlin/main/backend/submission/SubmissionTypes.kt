package main.backend.submission

import io.micronaut.serde.annotation.Serdeable
import judge.FeedbackEvent.Status
import judge.SolutionCode
import judge.SubmissionID
import problem.Problem.Language
import problem.ProblemID
import java.time.LocalDateTime

@Serdeable
data class CreateSubmission(
    val problemId: ProblemID,
    val lang: Language,
    val code: SolutionCode,
)

@Serdeable
data class SubmissionSummary(val id: SubmissionID)

@Serdeable
data class SubmissionDetails(
    val id: SubmissionID,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    var problemId: ProblemID,
    val feedback: FeedbackDetails?,
    val lang: Language,
    val code: SolutionCode
)

@Serdeable
data class FeedbackDetails(
    val status: Status,
    val output: String?,
    val time: Int?,
    val memory: Int?,
)
