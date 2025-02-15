package problem.stub.problem1

import problem.Problem.*
import problem.Resources


val JS_PROJECT = Project(
    lang = Language.JAVA_SCRIPT,
    files = ProjectFiles(
        solution = ProjectFile(
            name = "solution.mjs",
            content =
                // language=js
                """
                export function swapFirstAndLastWords(s) {
                
                }
                """.trimIndent()
        ),
        validator = ProjectFile(
            name = "validator.mjs",
            content =
                // language=js
                """
                export function swapFirstAndLastWords(s) {
                    const words = s.split(' ');
                
                    if (words.length <= 1) {
                        return s;
                    }
                
                    const temp = words[0];
                    words[0] = words[words.length - 1];
                    words[words.length - 1] = temp;
                
                    return words.join(' ');
                }
                """.trimIndent()
        ),
        evaluator = ProjectFile(
            name = "main.mjs",
            content =
                // language=js
                """
                import * as solution from './solution.mjs';
                import * as validator from './validator.mjs';
                import * as fs from 'node:fs';
                
                fs.readFileSync('input.txt', 'utf-8').split('\\n').forEach(testCase => {
                    const actual = solution.swapFirstAndLastWords(testCase);
                    const expected = validator.swapFirstAndLastWords(testCase);
                
                    if (actual !== expected) {
                        console.log('Test case failed: ' + testCase);
                        console.log('Expected: ' + expected);
                        console.log('Actual: ' + actual);
                        process.exit(404);
                    }
                });
                
                console.log('All test cases passed!');
                """.trimIndent()
        ),
        execute = ProjectFile(
            name = "execute.sh",
            content =
                // language=sh
                """
                #!/usr/bin/env sh
                
                node main.mjs
                """.trimIndent()
        ),
    ),
    limits = Resources(time = 10, memory = 262144)
)
