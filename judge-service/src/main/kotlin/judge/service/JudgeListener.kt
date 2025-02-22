package judge.service

import io.micrometer.core.annotation.Counted
import io.micronaut.rabbitmq.annotation.Binding
import io.micronaut.rabbitmq.annotation.Queue
import io.micronaut.rabbitmq.annotation.RabbitClient
import io.micronaut.rabbitmq.annotation.RabbitListener
import judge.FeedbackEvent
import judge.FeedbackEvent.Status.EVALUATION_FAILED
import judge.FeedbackEvent.Status.EVALUATION_RUNNING
import judge.SubmissionEvent
import org.slf4j.LoggerFactory


@RabbitListener
class JudgeListener(
    private val sender: FeedbackSender,
    private val service: JudgeService,
) {

    @Queue(
        value = "\${judge.queue.submission}",
        numberOfConsumers = "\${judge.concurrency}",
        executor = "judge-listener",
    )
    @Counted("judge_service.submission_received")
    fun onSubmission(event: SubmissionEvent) {
        try {

            log.info("Received submission: {}", event.id)
            sender.send(FeedbackEvent(event.id, EVALUATION_RUNNING))

            service.evaluate(event).apply { sender.send(this) }
            log.info("Processed submission: {}", event.id)

        } catch (e: Exception) {
            log.error("Failed to process submission: {}", event.id, e)
            sender.send(FeedbackEvent(event.id, EVALUATION_FAILED))
        }
    }

    @RabbitClient
    interface FeedbackSender {

        @Binding("\${judge.queue.feedback}")
        @Counted("judge_service.feedback_sent")
        fun send(event: FeedbackEvent)
    }

    private companion object {
        val log = LoggerFactory.getLogger(JudgeListener::class.java)!!
    }
}
