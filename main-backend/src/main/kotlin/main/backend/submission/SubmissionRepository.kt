package main.backend.submission

import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository
import judge.SubmissionID


@JdbcRepository(dialect = Dialect.MYSQL)
interface SubmissionRepository : CrudRepository<SubmissionModel, SubmissionID> {

    fun getById(id: SubmissionID): SubmissionModel?

    fun updateFeedbackById(id: SubmissionID, feedback: FeedbackModel)

}
