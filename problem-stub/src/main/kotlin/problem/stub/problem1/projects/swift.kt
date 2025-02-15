package problem.stub.problem1.projects

import problem.Problem.*
import problem.Resources


val SWIFT_PROJECT = Project(
    lang = Language.SWIFT,
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
                
                if let input = try? String(contentsOfFile: "input.txt") {
                    let testCases = input.split(separator: "\\n")
                
                    for testCase in testCases {
                        let actual = solution.swapFirstAndLastWords(String(testCase))
                        let expected = validator.swapFirstAndLastWords(String(testCase))
                
                        if actual != expected {
                            print("Test case failed: \(testCase)")
                            print("Expected: \(expected)")
                            print("Actual: \(actual)")
                            exit(404)
                        }
                    }
                }
                
                print("All test cases passed!")
                """.trimIndent()
        ),
        compile = ProjectFile(
            name = "compile.sh",
            content =
                // language=sh
                """
                #!/usr/bin/env sh
                
                swiftc ./*.swift -o main.out
                """.trimIndent()
        ),
        execute = ProjectFile(
            name = "execute.sh",
            content =
                // language=sh
                """
                #!/usr/bin/env sh
                
                ./main.out
                """.trimIndent()
        ),
    ),
    limits = Resources(time = 10, memory = 262144)
)
