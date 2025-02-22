package problem.stub.problem1

import INPUT
import problem.Problem.*
import problem.Problem.Language.JAVA_SCRIPT
import problem.Resources
import kotlin.time.Duration.Companion.seconds


val JS_PROJECT = Project(
    lang = JAVA_SCRIPT,
    input = INPUT,
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
                
                fs.readFileSync('input.txt', 'utf-8').split('\n').forEach(testCase => {
                    const actual = solution.swapFirstAndLastWords(testCase);
                    const expected = validator.swapFirstAndLastWords(testCase);
                
                    if (actual !== expected) {
                        console.error('[JUDGE_FEEDBACK]');
                        console.error('WRONG_ANSWER');
                        console.error('Input: ' + testCase);
                        console.error('Output: ' + actual);
                        console.error('Expected: ' + expected);
                        console.error('[JUDGE_FEEDBACK]');
                        process.exit(65);
                    }
                });
                
                console.error('[JUDGE_FEEDBACK]');
                console.error('ACCEPTED');
                console.error('[JUDGE_FEEDBACK]');
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
                
                node main.mjs
                """.trimIndent()
        ),
        resources = Resources(time = 3.seconds, memory = 75000)
    ),
)
