package problem.stub.problem1

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
                """.trimIndent()
        ),
    ),
    compile = ProjectAction(
        script = ProjectFile(
            name = "compile.sh",
            content =
                // language=bash
                """
                #!/usr/bin/env bash
                
                stderr() { echo "${'$'}@" 1>&2; }
                stderr "[JUDGE_FEEDBACK]"
                
                kotlinc ./*.kt -include-runtime -d main.jar
                
                EXIT_CODE="${'$'}?"
                if [[ "${'$'}EXIT_CODE" -eq 0 ]]
                then stderr "ACCEPTED"
                fi
                stderr "[JUDGE_FEEDBACK]"
                exit "${'$'}EXIT_CODE"
                """.trimIndent()
        ),
        resources = Resources(time = 10, memory = 512000)
    ),
    execute = ProjectAction(
        script = ProjectFile(
            name = "execute.sh",
            content =
                // language=bash
                """
                #!/usr/bin/env bash
                
                java -jar main.jar
                """.trimIndent()
        ),
        resources = Resources(time = 3, memory = 75000)
    ),
)
