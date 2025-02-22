package problem.stub.problem1

import INPUT
import problem.Problem.*
import problem.Problem.Language.PHP
import problem.Resources
import kotlin.time.Duration.Companion.seconds


val PHP_PROJECT = Project(
    lang = PHP,
    input = INPUT,
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
                        fwrite(STDERR, "[JUDGE_FEEDBACK]\n");
                        fwrite(STDERR, "WRONG_ANSWER\n");
                        fwrite(STDERR, "Input: " . ${'$'}testCase . "\n");
                        fwrite(STDERR, "Output: " . ${'$'}actual . "\n");
                        fwrite(STDERR, "Expected: " . ${'$'}expected . "\n");
                        fwrite(STDERR, "[JUDGE_FEEDBACK]\n");
                        exit(65);
                    }
                }
                
                fwrite(STDERR, "[JUDGE_FEEDBACK]\n");
                fwrite(STDERR, "ACCEPTED\n");
                fwrite(STDERR, "[JUDGE_FEEDBACK]\n");
                """.trimIndent()
        ),
    ),
    execute = ProjectAction(
        script = ProjectFile(
            name = "execute.sh",
            content =
                // language=bash
                """
                #!/usr/bin/env bash
                
                php main.php
                """.trimIndent()
        ),
        resources = Resources(time = 3.seconds, memory = 75000)
    ),
)
