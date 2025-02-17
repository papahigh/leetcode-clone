package main.backend.submission

import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.DateUpdated
import io.micronaut.serde.annotation.Serdeable
import jakarta.annotation.Nullable
import jakarta.persistence.*
import jakarta.persistence.EnumType.STRING
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
    @Enumerated(STRING)
    @Column(name = "lang", nullable = false, updatable = false, columnDefinition = "VARCHAR(255)")
    lateinit var lang: Language

    @NotBlank
    @Column(name = "code", nullable = false, updatable = false, columnDefinition = "TEXT")
    lateinit var code: SolutionCode

    @NotNull
    @Column(name = "problem_id", nullable = false, updatable = false)
    var problemId: ProblemID? = null

    @Nullable
    @Embedded
    var feedback: FeedbackModel? = null

}

@Serdeable
@Embeddable
class FeedbackModel {

    @Nullable
    @Enumerated(STRING)
    @Column(name = "status", columnDefinition = "VARCHAR(255)")
    var status: Status? = null

    @Nullable
    @Column(name = "output", columnDefinition = "TEXT")
    var output: String? = null

    @Nullable
    @Column(name = "time")
    var time: Int? = null

    @Nullable
    @Column(name = "memory")
    var memory: Int? = null

}
