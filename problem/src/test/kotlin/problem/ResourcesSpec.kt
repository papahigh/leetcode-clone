package problem

import io.kotest.matchers.shouldBe
import problem.Resources.Companion.bytes
import problem.Resources.Companion.kilobytes
import problem.Resources.Companion.megabytes
import problem.Resources.Companion.millis
import problem.Resources.Companion.minutes
import problem.Resources.Companion.seconds
import problem.Resources.Memory
import problem.Resources.Time

class ResourcesSpec : ProblemSpec({
    describe("Memory Resource") {
        it("should handle bytes value") {
            val mem = 1024.bytes

            mem shouldBe Memory.ofBytes(1024)
            mem.bytes() shouldBe 1024
            mem.kilobytes() shouldBe 1
            mem.megabytes() shouldBe 0
        }

        it("should handle kilobytes value") {
            val mem = 1024.kilobytes

            mem shouldBe Memory.ofBytes(1024 * 1024)
            mem.bytes() shouldBe 1024 * 1024
            mem.kilobytes() shouldBe 1024
            mem.megabytes() shouldBe 1
        }

        it("should handle megabytes value") {
            val mem = 1024.megabytes

            mem shouldBe Memory.ofBytes(1024 * 1024 * 1024)
            mem.bytes() shouldBe 1024 * 1024 * 1024
            mem.kilobytes() shouldBe 1024 * 1024
            mem.megabytes() shouldBe 1024
        }
    }

    describe("Time Resource") {
        it("should handle millis value") {
            val time = 1000.millis

            time shouldBe Time.ofMillis(1000)
            time.millis() shouldBe 1000
            time.seconds() shouldBe 1
            time.minutes() shouldBe 0
        }

        it("should handle seconds value") {
            val time = 60.seconds

            time shouldBe Time.ofMillis(60 * 1000)
            time.millis() shouldBe 60 * 1000
            time.seconds() shouldBe 60
            time.minutes() shouldBe 1
        }

        it("should handle minutes value") {
            val time = 60.minutes

            time shouldBe Time.ofMillis(60 * 60 * 1000)
            time.millis() shouldBe 60 * 60 * 1000
            time.seconds() shouldBe 60 * 60
            time.minutes() shouldBe 60
        }
    }
})
