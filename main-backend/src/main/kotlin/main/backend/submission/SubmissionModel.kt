package main.backend.submission

import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.DateUpdated
import io.micronaut.serde.annotation.Serdeable
import jakarta.annotation.Nullable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import judge.FeedbackEvent.Status
import judge.SolutionCode
import main.backend.common.IdentifiableModel
import problem.Problem.Language
import problem.ProblemID
import java.time.LocalDateTime


@Entity
@Serdeable
@Table(name = "submission")
class SubmissionModel : IdentifiableModel() {

    @NotNull
    @DateCreated
    @Column(name = "created_at", nullable = false, updatable = false)
    lateinit var createdAt: LocalDateTime

    @NotNull
    @DateUpdated
    @Column(name = "updated_at", nullable = false, updatable = true)
    lateinit var updatedAt: LocalDateTime

    @NotNull
    @Column(name = "problem_id", nullable = false, updatable = false)
    var problemId: ProblemID? = null

    @NotNull
    @Enumerated(STRING)
    @Column(name = "lang", nullable = false, updatable = false, columnDefinition = "VARCHAR(255)")
    lateinit var lang: Language

    @NotBlank
    @Column(name = "code", nullable = false, updatable = false, columnDefinition = "TEXT")
    lateinit var code: SolutionCode

    @Nullable
    @Enumerated(STRING)
    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(255)")
    lateinit var status: Status

    @Nullable
    @Column(name = "stdout", columnDefinition = "TEXT")
    var stdout: String? = null

    @Nullable
    @Column(name = "stderr", columnDefinition = "TEXT")
    var stderr: String? = null

    @Nullable
    @Column(name = "time")
    var time: Int? = null

    @Nullable
    @Column(name = "memory")
    var memory: Int? = null

}
