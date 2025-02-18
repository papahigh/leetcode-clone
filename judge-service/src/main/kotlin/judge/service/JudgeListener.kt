package judge.service

import io.micrometer.core.annotation.Counted
import io.micronaut.rabbitmq.annotation.Binding
import io.micronaut.rabbitmq.annotation.Queue
import io.micronaut.rabbitmq.annotation.RabbitClient
import io.micronaut.rabbitmq.annotation.RabbitListener
import judge.FeedbackEvent
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
        log.info("Received submission: {}", event.id)
        service.evaluate(event).apply { sender.send(this) }
        log.info("Processed submission: {}", event.id)
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
