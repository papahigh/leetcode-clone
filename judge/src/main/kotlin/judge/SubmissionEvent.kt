package judge

import problem.Problem.Language
import problem.ProblemID

typealias SubmissionID = String
typealias SolutionCode = String

data class SubmissionEvent(
    val id: SubmissionID,
    val lang: Language,
    val code: SolutionCode,
    val problemId: ProblemID,
)
