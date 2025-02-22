package problem.stub.problem1

import INPUT
import problem.Problem.*
import problem.Problem.Language.JAVA
import problem.Resources
import kotlin.time.Duration.Companion.seconds


val JAVA_PROJECT = Project(
    lang = JAVA,
    input = INPUT,
    files = ProjectFiles(
        solution = ProjectFile(
            name = "Solution.java",
            content =
                // language=java
                """
                import java.util.*;
                
                class Solution {
                    public String swapFirstAndLastWords(String s) {
                
                    }
                }
                """.trimIndent()
        ),
        validator = ProjectFile(
            name = "Validator.java",
            content =
                // language=java
                """
                import java.util.*;
                
                class Validator {
                    public String swapFirstAndLastWords(String s) {
                        String[] words = s.split(" ");
                
                        if (words.length <= 1) {
                            return s;
                        }
                
                        String temp = words[0];
                        words[0] = words[words.length - 1];
                        words[words.length - 1] = temp;
                
                        return String.join(" ", words);
                    }
                }
                """.trimIndent()
        ),
        evaluator = ProjectFile(
            name = "Main.java",
            content =
                // language=java
                """
                import java.nio.file.Files;
                import java.nio.file.Paths;
                
                public class Main {
                
                    public static void main(String[] args) throws Exception {
                
                        var solution = new Solution();
                        var verifier = new Validator();
                
                        Files.readAllLines(Paths.get("input.txt")).forEach(testCase -> {
                
                            var actual = solution.swapFirstAndLastWords(testCase);
                            var expected = verifier.swapFirstAndLastWords(testCase);
                
                            if (!expected.equals(actual)) {
                                System.err.println("[JUDGE_FEEDBACK]");
                                System.err.println("WRONG_ANSWER");
                                System.err.println("Input: " + testCase);
                                System.err.println("Output: " + actual);
                                System.err.println("Expected: " + expected);
                                System.err.println("[JUDGE_FEEDBACK]");
                                System.exit(65);
                            }
                        });
                
                        System.err.println("[JUDGE_FEEDBACK]");
                        System.err.println("ACCEPTED");
                        System.err.println("[JUDGE_FEEDBACK]");
                    }
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
                
                javac ./*.java
                
                EXIT_CODE="${'$'}?"
                if [[ "${'$'}EXIT_CODE" -eq 0 ]]
                then stderr "ACCEPTED"
                fi
                stderr "[JUDGE_FEEDBACK]"
                exit "${'$'}EXIT_CODE"
                """.trimIndent()
        ),
        resources = Resources(time = 10.seconds, memory = 512000)
    ),
    execute = ProjectAction(
        script = ProjectFile(
            name = "execute.sh",
            content =
                // language=bash
                """
                #!/usr/bin/env bash
                
                java Main
                """.trimIndent()
        ),
        resources = Resources(time = 3.seconds, memory = 75000)
    ),
)
