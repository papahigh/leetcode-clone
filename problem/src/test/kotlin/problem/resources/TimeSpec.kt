package problem.resources

import io.kotest.matchers.shouldBe
import problem.ProblemSpec
import problem.resources.Time.Companion.millis
import problem.resources.Time.Companion.minutes
import problem.resources.Time.Companion.seconds

class TimeSpec : ProblemSpec({
    describe("Extension functions") {
        it("should handle millis value") {
            val time = 1000.millis

            time shouldBe Time.ofMillis(1000)
            time.millis() shouldBe 1000
            time.seconds() shouldBe 1.0
            time.minutes() shouldBe 1 / 60.0
        }

        it("should handle seconds value") {
            val time = 60.seconds

            time shouldBe Time.ofMillis(60 * 1000)
            time.millis() shouldBe 60 * 1000
            time.seconds() shouldBe 60.0
            time.minutes() shouldBe 1.0
        }

        it("should handle minutes value") {
            val time = 60.minutes

            time shouldBe Time.ofMillis(60 * 60 * 1000)
            time.millis() shouldBe 60 * 60 * 1000
            time.seconds() shouldBe 60 * 60.0
            time.minutes() shouldBe 60.0
        }
    }
})