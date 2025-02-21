package main.backend.submission

import io.micrometer.core.annotation.Counted
import io.micronaut.rabbitmq.annotation.Queue
import io.micronaut.rabbitmq.annotation.RabbitListener
import io.micronaut.transaction.annotation.Transactional
import judge.FeedbackEvent
import org.slf4j.LoggerFactory.getLogger


@RabbitListener
class FeedbackListener(
    private val repository: SubmissionRepository,
) {

    @Queue(value = "\${judge.queue.feedback}")
    @Counted("main_backend.feedback_received")
    @Transactional
    fun onFeedback(event: FeedbackEvent) {
        log.debug("Received feedback for: {}", event.id)
        repository.updateFeedback(event)
    }

    private companion object {
        val log = getLogger(FeedbackListener::class.java)!!
    }
}
