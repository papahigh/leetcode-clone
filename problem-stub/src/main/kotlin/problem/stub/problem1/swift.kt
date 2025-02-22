package problem.stub.problem1

import problem.Problem.*
import problem.Problem.Language.SWIFT
import problem.Resources
import problem.Resources.Companion.kilobytes
import problem.Resources.Companion.seconds


val SWIFT_PROJECT = Project(
    lang = SWIFT,
    input = INPUT,
    files = ProjectFiles(
        solution = ProjectFile(
            name = "solution.swift",
            content =
                // language=swift
                """
                import Foundation
                
                public class Solution {
                    public func swapFirstAndLastWords(_ s: String) -> String {
                
                    }
                }
                """.trimIndent()
        ),
        validator = ProjectFile(
            name = "validator.swift",
            content =
                // language=swift
                """
                import Foundation
                
                public class Validator {
                    public func swapFirstAndLastWords(_ s: String) -> String {
                        let words = s.split(separator: " ")
                
                        if words.count <= 1 {
                            return s
                        }
                
                        var wordsArray = Array(words)
                        wordsArray.swapAt(0, wordsArray.count - 1)
                
                        return wordsArray.joined(separator: " ")
                    }
                }
                """.trimIndent()
        ),
        evaluator = ProjectFile(
            name = "main.swift",
            content =
                // language=swift
                """
                import Foundation
                
                let solution = Solution()
                let validator = Validator()
                
                func stderr(_ message: String) {
                    if let data = message.data(using: .utf8) {
                        FileHandle.standardError.write(data)
                        FileHandle.standardError.write("\n".data(using: .utf8)!)
                    }
                }
                
                if let input = try? String(contentsOfFile: "input.txt") {
                    let testCases = input.split(separator: "\n")
                
                    for testCase in testCases {
                        let actual = solution.swapFirstAndLastWords(String(testCase))
                        let expected = validator.swapFirstAndLastWords(String(testCase))
                
                        if actual != expected {
                            stderr("[JUDGE_FEEDBACK]")
                            stderr("WRONG_ANSWER")
                            stderr("Input: \(testCase)")
                            stderr("Output: \(actual)")
                            stderr("Expected: \(expected)")
                            stderr("[JUDGE_FEEDBACK]")
                            exit(65)
                        }
                    }
                }
                
                stderr("[JUDGE_FEEDBACK]")
                stderr("ACCEPTED")
                stderr("[JUDGE_FEEDBACK]")
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
                
                swiftc ./*.swift -o main.out
                
                EXIT_CODE="${'$'}?"
                if [[ "${'$'}EXIT_CODE" -eq 0 ]]
                then stderr "ACCEPTED"
                fi
                stderr "[JUDGE_FEEDBACK]"
                exit "${'$'}EXIT_CODE"
                """.trimIndent()
        ),
        resources = Resources(time = 3.seconds, memory = 75000.kilobytes)
    ),
    execute = ProjectAction(
        script = ProjectFile(
            name = "execute.sh",
            content =
                // language=bash
                """
                #!/usr/bin/env bash
                
                chmod +x main.out
                ./main.out
                """.trimIndent()
        ),
        resources = Resources(time = 3.seconds, memory = 75000.kilobytes)
    ),
)
