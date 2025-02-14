package judge

import problem.ProblemID

typealias SubmissionID = String
typealias SolutionCode = String

data class Submission(
    val id: SubmissionID,
    val problem: ProblemID,
    val solution: SolutionCode,
)
