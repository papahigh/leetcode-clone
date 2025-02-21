import java.io.File


fun main() {
    val solution = Solution()
    val validator = Validator()

    File("input.txt").forEachLine { testCase ->
        val actual = solution.swapFirstAndLastWords(testCase)
        val expected = validator.swapFirstAndLastWords(testCase)

        if (actual != expected) {
            System.err.println("[JUDGE_FEEDBACK]")
            System.err.println("WRONG_ANSWER")
            System.err.println("Input: " + testCase)
            System.err.println("Output: " + actual)
            System.err.println("Expected: " + expected)
            System.err.println("[JUDGE_FEEDBACK]")
            System.exit(405)
        }
    }

    System.err.println("[JUDGE_FEEDBACK]")
    System.err.println("ACCEPTED")
    System.err.println("[JUDGE_FEEDBACK]")
}