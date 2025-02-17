package main.backend

import io.kotest.matchers.shouldBe
import io.micronaut.context.ApplicationContext
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import jakarta.inject.Inject


@MicronautTest
class AppContextSpec() : IntegrationSpec() {

    @Inject
    lateinit var applicationContext: ApplicationContext

    init {
        it("should be running") { applicationContext.isRunning shouldBe true }
    }
}
