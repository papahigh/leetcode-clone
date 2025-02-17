package main.backend.problem

import io.micronaut.serde.annotation.Serdeable
import problem.Markdown
import problem.Problem.Language
import problem.ProblemID


@Serdeable
data class ProblemSummary(val id: ProblemID, val slug: String, val name: String)

@Serdeable
data class ProblemDetails(
    val id: ProblemID,
    val slug: String,
    val name: String,
    val body: Markdown,
    val snippets: Collection<ProblemSnippet>
)

@Serdeable
data class ProblemSnippet(val code: String, val lang: Language)
