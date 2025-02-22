package problem.stub.problem1

import INPUT
import problem.Problem.*
import problem.Problem.Language.TYPE_SCRIPT
import problem.Resources
import kotlin.time.Duration.Companion.seconds


val TS_PROJECT = Project(
    lang = TYPE_SCRIPT,
    input = INPUT,
    files = ProjectFiles(
        solution = ProjectFile(
            name = "solution.ts",
            content =
                // language=typescript
                """
                export class Solution {
                    public swapFirstAndLastWords(s: string): string {
                
                    }
                }
                """.trimIndent()
        ),
        validator = ProjectFile(
            name = "validator.ts",
            content =
                // language=typescript
                """
                export class Validator {
                    public swapFirstAndLastWords(s: string): string {
                        const words = s.split(' ');
                
                        if (words.length <= 1) {
                            return s;
                        }
                
                        [words[0], words[words.length - 1]] = [words[words.length - 1], words[0]];
                
                        return words.join(' ');
                    }
                }
                """.trimIndent()
        ),
        evaluator = ProjectFile(
            name = "main.ts",
            content =
                // language=typescript
                """
                import { Solution } from './solution';
                import { Validator } from './validator';
                // @ts-ignore
                import * as fs from 'node:fs';
                
                const solution = new Solution();
                const validator = new Validator();
                
                // @ts-ignore
                fs.readFileSync('input.txt', 'utf-8').split('\n').forEach((testCase: string) => {
                    const actual = solution.swapFirstAndLastWords(testCase);
                    const expected = validator.swapFirstAndLastWords(testCase);
                
                    if (actual !== expected) {
                        console.error('[JUDGE_FEEDBACK]');
                        console.error('WRONG_ANSWER');
                        console.error('Input: ' + testCase);
                        console.error('Output: ' + actual);
                        console.error('Expected: ' + expected);
                        console.error('[JUDGE_FEEDBACK]');
                        // @ts-ignore
                        process.exit(65);
                    }
                });
                
                console.error('[JUDGE_FEEDBACK]');
                console.error('ACCEPTED');
                console.error('[JUDGE_FEEDBACK]');
                """.trimIndent()
        ),

        resources = listOf(
            ProjectFile(
                name = "tsconfig.json",
                content =
                    // language=json
                    """
                    {
                      "compilerOptions": {
                        "target": "ES2024",
                        "lib": ["ES2024", "DOM"],
                        "module": "commonjs",
                        "esModuleInterop": true,
                        "forceConsistentCasingInFileNames": true,
                        "strict": true,
                        "skipLibCheck": true
                      }
                    }
                    """.trimIndent()
            )
        )
    ),
    compile = ProjectAction(
        script = ProjectFile(
            name = "compile.sh",
            content =
                // language=sh
                """
                #!/usr/bin/env bash
                
                stderr() { echo "${'$'}@" 1>&2; }
                stderr "[JUDGE_FEEDBACK]"
                
                tsc -p tsconfig.json
                
                EXIT_CODE="${'$'}?"
                if [[ "${'$'}EXIT_CODE" -eq 0 ]]
                then stderr "ACCEPTED"
                fi
                stderr "[JUDGE_FEEDBACK]"
                exit "${'$'}EXIT_CODE"
                """.trimIndent()
        ),
        resources = Resources(time = 3.seconds, memory = 75000)
    ),
    execute = ProjectAction(
        script = ProjectFile(
            name = "execute.sh",
            content =
                // language=bash
                """
                #!/usr/bin/env bash
                
                node main.js
                """.trimIndent()
        ),
        resources = Resources(time = 3.seconds, memory = 75000)
    ),
)
