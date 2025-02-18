package judge.service

import com.rabbitmq.client.Channel
import io.kotest.assertions.nondeterministic.eventually
import io.kotest.assertions.nondeterministic.eventuallyConfig
import io.kotest.matchers.shouldBe
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.context.annotation.Requires
import io.micronaut.messaging.annotation.MessageBody
import io.micronaut.rabbitmq.annotation.Binding
import io.micronaut.rabbitmq.annotation.Queue
import io.micronaut.rabbitmq.annotation.RabbitClient
import io.micronaut.rabbitmq.annotation.RabbitListener
import io.micronaut.rabbitmq.connect.ChannelInitializer
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import jakarta.inject.Inject
import jakarta.inject.Named
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory
import java.lang.Thread.sleep
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger
import kotlin.time.Duration.Companion.seconds


@MicronautTest
class RabbitMqSpec() : IntegrationSpec() {

    @Inject
    lateinit var producer: Producer

    @Inject
    lateinit var consumer: Consumer

    init {
        describe("RabbitMQ Listener") {
            it("should handle messages concurrently") {
                val config = eventuallyConfig {
                    duration = 2.seconds
                    initialDelay = 1.seconds
                }

                repeat(10) { producer.send("message $it") }

                eventually(config) {
                    consumer.messages.get() shouldBe 10
                }
            }
        }
    }

    @Requires(property = "spec.name", value = "RabbitMqSpec")
    @Factory
    class Config {
        @Bean
        @Named("rabbit-consumer")
        fun executor() = Executors.newFixedThreadPool(5)!!
    }

    @Requires(property = "spec.name", value = "RabbitMqSpec")
    @RabbitClient
    interface Producer {
        @Binding("rabbitmq.spec.queue")
        fun send(message: String)
    }

    @Requires(property = "spec.name", value = "RabbitMqSpec")
    @RabbitListener
    class Consumer {
        val messages = AtomicInteger()

        @Queue(
            value = "rabbitmq.spec.queue",
            executor = "rabbit-consumer",
            numberOfConsumers = "5",
        )
        fun onMessage(@MessageBody message: String) {
            log.info("Received message: {}", message)
            sleep(1000)
            messages.incrementAndGet()
        }
    }

    @Singleton
    @Requires(property = "spec.name", value = "RabbitMqSpec")
    class RabbitMQConfig : ChannelInitializer() {
        override fun initialize(channel: Channel, name: String?) {
            channel.queueDeclare("rabbitmq.spec.queue", true, false, false, null)
        }
    }

    companion object {
        val log = LoggerFactory.getLogger(RabbitMqSpec::class.java)!!
    }
}
