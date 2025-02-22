package main.backend.submission

import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository
import io.micronaut.data.repository.jpa.JpaSpecificationExecutor
import io.micronaut.data.runtime.criteria.get
import io.micronaut.data.runtime.criteria.update
import judge.FeedbackEvent
import judge.SubmissionID
import java.time.LocalDateTime


@JdbcRepository(dialect = Dialect.MYSQL)
abstract class SubmissionRepository : CrudRepository<SubmissionModel, SubmissionID>,
    JpaSpecificationExecutor<SubmissionModel> {

    abstract fun getById(id: SubmissionID): SubmissionModel?

    fun updateFeedback(event: FeedbackEvent) {
        var update = update<SubmissionModel> {
            set(SubmissionModel::updatedAt, LocalDateTime.now())
            set(SubmissionModel::status, event.status)

            event.stdout?.let { set(SubmissionModel::stdout, it) }
            event.stderr?.let { set(SubmissionModel::stderr, it) }
            event.memory?.let { set(SubmissionModel::memory, it) }
            event.time?.let { set(SubmissionModel::time, it) }

            where { root[SubmissionModel::id] eq event.id }
        }
        updateAll(update)
    }
}
