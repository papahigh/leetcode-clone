package problem.stub.problem1

import problem.Problem.*
import problem.Resources


val PYTHON3_PROJECT = Project(
    lang = Language.PYTHON3,
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
                
                with open('input.txt', 'r') as f:
                    for testCase in f.readlines():
                        testCase = testCase.strip()
                
                        actual = s.swapFirstAndLastWords(testCase)
                        expected = v.swapFirstAndLastWords(testCase)
                
                        if actual != expected:
                            print(f"Test case failed: {testCase}")
                            print(f"Expected: {expected}")
                            print(f"Actual: {actual}")
                            exit(404)
                
                print("All test cases passed!")
                """
        ),
        execute = ProjectFile(
            name = "execute.sh",
            content =
                // language=sh
                """
                #!/usr/bin/env sh
                
                python3 Main.py
                """.trimIndent()
        ),
    ),
    limits = Resources(time = 10, memory = 262144)
)
