package problem.stub.problem1.projects

import problem.Problem.*
import problem.Resources


val TS_PROJECT = Project(
    lang = Language.TYPE_SCRIPT,
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
                fs.readFileSync('input.txt', 'utf-8').split('\\n').forEach((testCase: string) => {
                    const actual = solution.swapFirstAndLastWords(testCase);
                    const expected = validator.swapFirstAndLastWords(testCase);
                
                    if (actual !== expected) {
                        console.log('Test case failed: ' + testCase);
                        console.log('Expected: ' + expected);
                        console.log('Actual: ' + actual);
                        // @ts-ignore
                        process.exit(404);
                    }
                });
                
                console.log('All test cases passed!');
                """.trimIndent()
        ),
        compile = ProjectFile(
            name = "compile.sh",
            content =
                // language=sh
                """
                #!/usr/bin/env sh
                
                tsc -p tsconfig.json
                """.trimIndent()
        ),
        execute = ProjectFile(
            name = "execute.sh",
            content =
                // language=sh
                """
                #!/usr/bin/env sh
                
                node main.js
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
    limits = Resources(time = 10, memory = 262144)
)
