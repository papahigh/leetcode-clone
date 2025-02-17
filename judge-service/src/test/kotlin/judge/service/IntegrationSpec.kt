package judge.service

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.spec.style.DescribeSpec
import io.micronaut.test.extensions.kotest5.MicronautKotest5Extension
import io.micronaut.test.support.TestPropertyProvider
import org.testcontainers.containers.RabbitMQContainer
import org.testcontainers.utility.DockerImageName

abstract class IntegrationSpec() : DescribeSpec(), TestPropertyProvider {

    object ProjectConfig : AbstractProjectConfig() {
        override fun extensions() = listOf(MicronautKotest5Extension)
    }

    override fun getProperties() = mapOf(
        "rabbitmq.uri" to rabbit.amqpUrl,
        "rabbitmq.username" to rabbit.adminUsername,
        "rabbitmq.password" to rabbit.adminPassword
    )

    private companion object {
        var rabbit = RabbitMQContainer(DockerImageName.parse("rabbitmq:4-alpine")).apply { start() }
    }
}
