package main.backend.problem

import io.micronaut.http.HttpStatus.NOT_FOUND
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.exceptions.HttpStatusException


@Controller("/api/problems")
class ProblemController(
    private val problemService: ProblemService
) {

    @Get
    fun getAllProblems(): Collection<ProblemSummary> {
        return problemService.findAll()
    }

    @Get("/{slug}")
    fun getProblemBySlug(slug: String): ProblemDetails {
        return problemService.findBySlug(slug) ?: throw NotFoundException()
    }

    class NotFoundException() : HttpStatusException(NOT_FOUND, "Problem Not Found")
}
