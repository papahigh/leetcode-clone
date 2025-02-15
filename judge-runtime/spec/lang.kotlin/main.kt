import java.io.File


fun main() {
    val solution = Solution()
    val validator = Validator()

    File("input.txt").forEachLine { testCase ->
        val actual = solution.swapFirstAndLastWords(testCase)
        val expected = validator.swapFirstAndLastWords(testCase)

        if (actual != expected) {
            println("Test case failed: " + testCase)
            println("Expected: " + expected)
            println("Actual: " + actual)
            System.exit(404)
        }
    }

    println("All test cases passed!")
}