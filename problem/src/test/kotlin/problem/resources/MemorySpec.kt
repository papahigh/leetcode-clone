package problem.resources

import io.kotest.matchers.shouldBe
import problem.ProblemSpec
import problem.resources.Memory.Companion.bytes
import problem.resources.Memory.Companion.kilobytes
import problem.resources.Memory.Companion.megabytes

class MemorySpec : ProblemSpec({
    describe("Extension functions") {
        it("should handle bytes value") {
            val mem = 1000.bytes

            mem shouldBe Memory.ofBytes(1000)
            mem.bytes() shouldBe 1000
            mem.kilobytes() shouldBe 1
            mem.megabytes() shouldBe 1.0 / 1000.0
        }

        it("should handle kilobytes value") {
            val mem = 1000.kilobytes

            mem shouldBe Memory.ofBytes(1000 * 1000)
            mem.bytes() shouldBe 1000 * 1000
            mem.kilobytes() shouldBe 1000
            mem.megabytes() shouldBe 1
        }

        it("should handle megabytes value") {
            val mem = 1000.megabytes

            mem shouldBe Memory.ofBytes(1000 * 1000 * 1000)
            mem.bytes() shouldBe 1000 * 1000 * 1000
            mem.kilobytes() shouldBe 1000 * 1000
            mem.megabytes() shouldBe 1000
        }
    }
})
