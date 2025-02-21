package judge.service

import io.micrometer.core.annotation.Timed
import jakarta.inject.Singleton
import judge.FeedbackEvent
import judge.SubmissionEvent
import judge.service.evaluation.DeploymentService
import judge.service.evaluation.FeedbackProvider
import org.slf4j.LoggerFactory


@Singleton
class JudgeService(
    private val deploymentService: DeploymentService,
    private val feedbackProvider: FeedbackProvider,
) {

    @Timed(value = "judge_service.evaluate")
    fun evaluate(event: SubmissionEvent): FeedbackEvent {
        log.debug("Processing submission {}", event.id)

        deploymentService.deploy(event).use { deployment ->

            log.debug("Compilation started for {}", event.id)
            val compileResult = deployment.compile()
            log.debug("Compilation finished for {}", event.id)

            if (compileResult?.isFailure() == true)
                return feedbackProvider.ofCompileResult(event, compileResult)

            log.debug("Execution started for {}", event.id)
            val executeResult = deployment.execute()
            log.debug("Execution finished for {}", event.id)

            return feedbackProvider.ofExecuteResult(event, executeResult)
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(JudgeService::class.java)
    }
}