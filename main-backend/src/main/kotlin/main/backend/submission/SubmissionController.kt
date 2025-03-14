package main.backend.submission

import io.micronaut.http.HttpStatus.CREATED
import io.micronaut.http.HttpStatus.NOT_FOUND
import io.micronaut.http.annotation.*
import io.micronaut.http.exceptions.HttpStatusException
import jakarta.validation.Valid
import judge.SubmissionID


@Controller("/api/submissions")
class SubmissionController(
    private val submissionService: SubmissionService,
) {

    @Post
    @Status(CREATED)
    fun createSubmission(@Valid @Body request: CreateSubmission): SubmissionDetails {
        return submissionService.create(request)
    }

    @Get("{id}")
    fun getSubmissionById(id: SubmissionID): SubmissionDetails {
        return submissionService.getById(id) ?: throw NotFoundException()
    }

    class NotFoundException() : HttpStatusException(NOT_FOUND, "Submission Not Found")
}
