package problem.stub.problem1.projects

import problem.Problem.*
import problem.Resources


val KOTLIN_PROJECT = Project(
    lang = Language.KOTLIN,
    files = ProjectFiles(
        solution = ProjectFile(
            name = "solution.kt",
            content =
                // language=kotlin
                """
                class Solution {
                    fun swapFirstAndLastWords(s: String): String {
                
                    }
                }
                """.trimIndent()
        ),
        validator = ProjectFile(
            name = "validator.kt",
            content =
                // language=kotlin
                """
                class Validator {
                    fun swapFirstAndLastWords(s: String): String {
                        val words = s.split(" ").toMutableList()
                
                        if (words.size <= 1) {
                            return s
                        }
                
                        val temp = words[0]
                        words[0] = words[words.size - 1]
                        words[words.size - 1] = temp
                
                        return words.joinToString(" ")
                    }
                }
                """.trimIndent()
        ),
        evaluator = ProjectFile(
            name = "main.kt",
            content =
                // language=kotlin
                """
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
                """.trimIndent()
        ),
        compile = ProjectFile(
            name = "compile.sh",
            content =
                // language=sh
                """
                #!/usr/bin/env sh
                
                kotlinc ./*.kt -include-runtime -d main.jar
                """.trimIndent()
        ),
        execute = ProjectFile(
            name = "execute.sh",
            content =
                // language=sh
                """
                #!/usr/bin/env sh
                
                java -jar main.jar
                """.trimIndent()
        ),
    ),
    limits = Resources(time = 10, memory = 262144)
)
