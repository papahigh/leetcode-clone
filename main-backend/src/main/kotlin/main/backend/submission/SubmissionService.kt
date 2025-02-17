package main.backend.submission

import io.micrometer.core.annotation.Counted
import io.micronaut.context.event.ApplicationEventPublisher
import io.micronaut.rabbitmq.annotation.Binding
import io.micronaut.rabbitmq.annotation.RabbitClient
import io.micronaut.transaction.annotation.Transactional
import io.micronaut.transaction.annotation.TransactionalEventListener
import io.micronaut.transaction.annotation.TransactionalEventListener.TransactionPhase
import jakarta.inject.Singleton
import judge.SubmissionEvent
import judge.SubmissionID
import org.slf4j.LoggerFactory


interface SubmissionService {
    fun create(request: CreateSubmission): SubmissionSummary
    fun getById(id: SubmissionID): SubmissionDetails?
}

@Singleton
class SubmissionServiceImpl(
    private val sender: SubmissionSender,
    private val mapper: SubmissionMapper,
    private val repository: SubmissionRepository,
    private val eventPublisher: ApplicationEventPublisher<SubmissionEvent>,
) : SubmissionService {

    @Transactional(readOnly = true)
    override fun getById(id: SubmissionID): SubmissionDetails? {
        return repository.getById(id)?.let { mapper.toDetails(it) }
    }

    @Transactional
    @Counted("main_backend.submission_created")
    override fun create(request: CreateSubmission): SubmissionSummary {
        var model = repository.save(mapper.map(request))
        eventPublisher.publishEvent(mapper.toEvent(model))
        return mapper.toSummary(model)
    }

    @TransactionalEventListener(TransactionPhase.AFTER_COMMIT)
    fun onCreated(event: SubmissionEvent) {
        log.debug("Submission created: {}", event.id)
        sender.send(event)
    }

    @RabbitClient
    interface SubmissionSender {

        @Binding("\${judge.queue.submission}")
        @Counted("main_backend.submission_sent")
        fun send(submission: SubmissionEvent)

    }

    private companion object {
        val log = LoggerFactory.getLogger(SubmissionServiceImpl::class.java)!!
    }
}
