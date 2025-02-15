package problem.stub.problem1

import problem.Problem.*
import problem.Resources


val PHP_PROJECT = Project(
    lang = Language.PHP,
    files = ProjectFiles(
        solution = ProjectFile(
            name = "solution.php",
            content =
                // language=php
                """
                <?php
                
                class Solution
                {
                    function swapFirstAndLastWords(${'$'}s)
                    {
                
                    }
                }
                """.trimIndent()
        ),
        validator = ProjectFile(
            name = "validator.php",
            content =
                // language=php
                """
                <?php
                
                class Validator
                {
                    function swapFirstAndLastWords(${'$'}s)
                    {
                        ${'$'}words = explode(' ', ${'$'}s);
                
                        if (count(${'$'}words) <= 1) {
                            return ${'$'}s;
                        }
                
                        ${'$'}temp = ${'$'}words[0];
                        ${'$'}words[0] = ${'$'}words[count(${'$'}words) - 1];
                        ${'$'}words[count(${'$'}words) - 1] = ${'$'}temp;
                
                        return implode(' ', ${'$'}words);
                    }
                }
                """.trimIndent()
        ),
        evaluator = ProjectFile(
            name = "main.php",
            content =
                // language=php
                """
                <?php
                require_once 'solution.php';
                require_once 'validator.php';
                
                ${'$'}input = file('input.txt', FILE_IGNORE_NEW_LINES);
                
                ${'$'}solution = new Solution();
                ${'$'}validator = new Validator();
                
                foreach (${'$'}input as ${'$'}testCase) {
                    ${'$'}actual = ${'$'}solution->swapFirstAndLastWords(${'$'}testCase);
                    ${'$'}expected = ${'$'}validator->swapFirstAndLastWords(${'$'}testCase);
                
                    if (${'$'}actual !== ${'$'}expected) {
                        echo "Test case failed: " . ${'$'}testCase . "\\n";
                        echo "Expected: " . ${'$'}expected . "\\n";
                        echo "Actual: " . ${'$'}actual . "\\n";
                        exit(404);
                    }
                }
                
                echo "All test cases passed!\\n";
                """.trimIndent()
        ),
        execute = ProjectFile(
            name = "execute.sh",
            content =
                // language=sh
                """
                #!/usr/bin/env sh
                
                php main.php
                """.trimIndent()
        ),
    ),
    limits = Resources(time = 10, memory = 262144)
)
