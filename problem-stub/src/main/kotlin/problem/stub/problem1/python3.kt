package problem.stub.problem1

import INPUT
import problem.Problem.*
import problem.Problem.Language.PYTHON3
import problem.Resources


val PYTHON3_PROJECT = Project(
    lang = PYTHON3,
    input = INPUT,
    files = ProjectFiles(
        solution = ProjectFile(
            name = "solution.py",
            content =
                // language=python
                """
                def swapFirstAndLastWords(s: str) -> str:

                """
        ),
        validator = ProjectFile(
            name = "validator.py",
            content =
                // language=python
                """
                def swapFirstAndLastWords(s: str) -> str:
                    words = s.split()
                
                    if len(words) <= 1:
                        return s
                
                    words[0], words[-1] = words[-1], words[0]
                
                    return " ".join(words)
                """
        ),
        evaluator = ProjectFile(
            name = "main.py",
            content =
                // language=python
                """
                import solution as s
                import validator as v
                import sys
                
                with open('input.txt', 'r') as f:
                    for testCase in f.readlines():
                        testCase = testCase.strip()
                
                        actual = s.swapFirstAndLastWords(testCase)
                        expected = v.swapFirstAndLastWords(testCase)
                
                        if actual != expected:
                            print("[JUDGE_FEEDBACK]", file=sys.stderr)
                            print("WRONG_ANSWER", file=sys.stderr)
                            print(f"Input: {testCase}", file=sys.stderr)
                            print(f"Output: {actual}", file=sys.stderr)
                            print(f"Expected: {expected}", file=sys.stderr)
                            print("[JUDGE_FEEDBACK]", file=sys.stderr)
                            exit(65)
                
                print("[JUDGE_FEEDBACK]", file=sys.stderr)
                print("ACCEPTED", file=sys.stderr)
                print("[JUDGE_FEEDBACK]", file=sys.stderr)
                """
        ),
    ),
    execute = ProjectAction(
        script = ProjectFile(
            name = "execute.sh",
            content =
                // language=bash
                """
                #!/usr/bin/env bash
                
                python3 Main.py
                """.trimIndent()
        ),
        resources = Resources(time = 3, memory = 75000)
    ),
)
