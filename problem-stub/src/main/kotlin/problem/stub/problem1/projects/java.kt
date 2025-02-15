package problem.stub.problem1.projects

import problem.Problem.*
import problem.Resources


val JAVA_PROJECT = Project(
    lang = Language.JAVA,
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
                                System.out.println("Test case failed: " + testCase);
                                System.out.println("Expected: " + expected);
                                System.out.println("Actual: " + actual);
                                System.exit(404);
                            }
                        });
                
                        System.out.println("All test cases passed!");
                    }
                }
                """.trimIndent()
        ),
        compile = ProjectFile(
            name = "compile.sh",
            content =
                // language=sh
                """
                #!/usr/bin/env sh
                
                javac ./*.java
                """.trimIndent()
        ),
        execute = ProjectFile(
            name = "execute.sh",
            content =
                // language=sh
                """
                #!/usr/bin/env sh
                
                java Main
                """.trimIndent()
        ),
    ),
    limits = Resources(time = 10, memory = 262144)
)
