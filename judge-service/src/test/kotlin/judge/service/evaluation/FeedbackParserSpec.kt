package judge.service.evaluation

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import judge.FeedbackEvent.Status
import problem.resources.Memory.Companion.bytes
import problem.resources.Time.Companion.millis

class FeedbackParserSpec : DescribeSpec({

    describe("Compilation error parser") {

        it("should parse a compilation error") {
            val result = FeedbackParser.compilation(
                """
                --------------------------------------
                --------------------------------------
                [JUDGE_FEEDBACK]
                this is
                a multiline
                compilation error
                [JUDGE_FEEDBACK]
                --------------------------------------
                --------------------------------------
                """.trimIndent()
            )

            result shouldBe """
                this is
                a multiline
                compilation error
                
                """.trimIndent()
        }

        it("should handle empty feedback") {
            val result = FeedbackParser.compilation(
                """
                **************************************
                **************************************
                [JUDGE_FEEDBACK]
                [JUDGE_FEEDBACK]
                **************************************
                **************************************
                """.trimIndent()
            )

            result shouldBe ""
        }

        it("should handle invalid feedback block") {
            val result = FeedbackParser.compilation(
                """
                --------------------------------------
                --------------------------------------
                [JUDGE_FEEDBACK]
                --------------------------------------
                --------------------------------------
                """.trimIndent()
            )

            result shouldBe null
        }

        it("should handle empty input") {
            val result = FeedbackParser.compilation("")

            result shouldBe null
        }
    }

    describe("Submission accepted parser") {

        it("should parse an accepted submission") {
            val result = FeedbackParser.accepted(
                """
                --------------------------------------
                --------------------------------------
                [JUDGE_FEEDBACK]
                ACCEPTED
                [JUDGE_FEEDBACK]
                --------------------------------------
                --------------------------------------
                """.trimIndent()
            )

            result shouldBe true
        }

        it("should handle random text") {
            val result = FeedbackParser.accepted(
                """
                --------------------------------------
                --------------------------------------
                [JUDGE_FEEDBACK]
                RANDOM TEXT
                [JUDGE_FEEDBACK]
                --------------------------------------
                --------------------------------------
                """.trimIndent()
            )

            result shouldBe false
        }

        it("should handle invalid feedback block") {
            val result = FeedbackParser.accepted(
                """
                --------------------------------------
                --------------------------------------
                [JUDGE_FEEDBACK]
                ACCEPTED
                --------------------------------------
                --------------------------------------
                """.trimIndent()
            )

            result shouldBe false
        }

        it("should handle empty input") {
            val result = FeedbackParser.accepted("")

            result shouldBe false
        }
    }

    describe("Wrong answer parser") {

        it("should parse a wrong answer") {
            val result = FeedbackParser.wrongAnswer(
                """
                --------------------------------------
                --------------------------------------
                [JUDGE_FEEDBACK]
                WRONG_ANSWER
                Input: abc
                Output: 1234
                Expected: 5678
                [JUDGE_FEEDBACK]
                --------------------------------------
                --------------------------------------
                """.trimIndent()
            )

            result shouldBe """
                Input: abc
                Output: 1234
                Expected: 5678

                """.trimIndent()
        }

        it("should handle random text") {
            val result = FeedbackParser.wrongAnswer(
                """
                --------------------------------------
                --------------------------------------
                [JUDGE_FEEDBACK]
                abc123
                [JUDGE_FEEDBACK]
                --------------------------------------
                --------------------------------------
                """.trimIndent()
            )

            result shouldBe null
        }

        it("should handle invalid feedback block") {
            val result = FeedbackParser.wrongAnswer(
                """
                --------------------------------------
                --------------------------------------
                [JUDGE_FEEDBACK]
                WRONG_ANSWER
                Input: abc
                Output: 1234
                Expected: 5678
                [JUDGE_FEED....]
                --------------------------------------
                --------------------------------------
                """.trimIndent()
            )

            result shouldBe null
        }

        it("should handle empty feedback") {
            val result = FeedbackParser.wrongAnswer("")

            result shouldBe null
        }
    }

    describe("Resource usage parser") {

        it("should parse resource usage") {
            val result = FeedbackParser.resourcesUsage(
                """
                ...........................................................
                ...........................................................
                [S][880] JUDGE:0 881:cgroup_memory_max_usage = 64286720
                ...........................................................
                [S][880] JUDGE:0 881:time = 383
                ...........................................................
                ...........................................................
                """.trimIndent()
            )

            result?.time shouldBe 383.millis
            result?.memory shouldBe 64286720.bytes
        }

        it("should handle empty input") {
            val result = FeedbackParser.resourcesUsage("")

            result shouldBe null
        }
    }

    describe("Resource limit exceeded parser") {

        it("should parse time limit exceeded") {
            val result = FeedbackParser.limitExceeded(
                """
                ...........................................................................................................
                ...........................................................................................................
                [I][2025-02-19T13:18:21+0000] pid=14 run time >= time limit (5001 >= 5000) ([STANDALONE MODE]). Killing it
                ...........................................................................................................
                ...........................................................................................................
                """.trimIndent()
            )

            result shouldBe Status.TIME_LIMIT_EXCEEDED

        }

        it("should parse memory limit exceeded") {
            val result = FeedbackParser.limitExceeded(
                """
                ...........................................................
                ...........................................................
                [S][887] JUDGE:0 888:cgroup_memory_failcnt = 11921
                ...........................................................
                ...........................................................
                """.trimIndent()
            )

            result shouldBe Status.MEMORY_LIMIT_EXCEEDED
        }

        it("should handle empty input") {
            val result = FeedbackParser.limitExceeded("")

            result shouldBe null
        }
    }
})
