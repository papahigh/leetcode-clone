package judge.service

import com.rabbitmq.client.Channel
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.context.annotation.Value
import io.micronaut.rabbitmq.connect.ChannelInitializer
import io.micronaut.serde.annotation.SerdeImport
import jakarta.inject.Singleton
import problem.ProjectRepository
import problem.stub.ProblemRepositoryStub


@SerdeImport(judge.FeedbackEvent::class)
@SerdeImport(judge.FeedbackEvent.Status::class)
@SerdeImport(judge.SubmissionEvent::class)
@SerdeImport(problem.Problem::class)
@SerdeImport(problem.Problem.Project::class)
@SerdeImport(problem.Problem.ProjectFile::class)
@SerdeImport(problem.Problem.ProjectFiles::class)
@SerdeImport(problem.Problem.ProjectAction::class)
@SerdeImport(problem.Problem.Language::class)
@SerdeImport(problem.Resources::class)
@SerdeImport(problem.resources.Time::class)
@SerdeImport(problem.resources.Memory::class)
class SerdeConfig


@Factory
class ProblemConfig {
    @Bean
    fun projectRepository(): ProjectRepository {
        return ProblemRepositoryStub()
    }
}


@Singleton
class RabbitConfig(
    @Value("\${judge.queue.feedback}") val feedbackQueue: String,
    @Value("\${judge.queue.submission}") val submissionQueue: String,
) : ChannelInitializer() {

    override fun initialize(channel: Channel, name: String?) {
        channel.queueDeclare(feedbackQueue)
        channel.queueDeclare(submissionQueue)
    }

    private companion object {
        const val DURABLE = true
        const val EXCLUSIVE = false
        const val AUTO_DELETE = false

        fun Channel.queueDeclare(queue: String) {
            this.queueDeclare(queue, DURABLE, EXCLUSIVE, AUTO_DELETE, null)
        }
    }
}

