package problem.stub.problem1.projects

import problem.Problem.*
import problem.Resources


val GO_PROJECT = Project(
    lang = Language.GO,
    files = ProjectFiles(
        solution = ProjectFile(
            name = "solution/solution.go",
            content =
                // language=go
                """
                package solution
                
                import "strings"
                
                func SwapFirstAndLastWords(s string) string {
                
                }
                """.trimIndent()
        ),
        validator = ProjectFile(
            name = "validator/validator.go",
            content =
                // language=go
                """
                package validator
                
                import "strings"
                
                func SwapFirstAndLastWords(s string) string {
                    words := strings.Fields(s)
                
                    if len(words) <= 1 {
                        return s
                    }
                
                    words[0], words[len(words)-1] = words[len(words)-1], words[0]
                    return strings.Join(words, " ")
                }
                """.trimIndent()
                        ),
        evaluator = ProjectFile(
            name = "main.go",
            content =
                // language=go
                """
                package main
                
                import (
                    "fmt"
                    "os"
                    "problem/solution"
                    "problem/validator"
                    "strings"
                )
                
                func main() {
                
                    data, err := os.ReadFile("input.txt")
                    if err != nil {
                        fmt.Println("Error reading file:", err)
                        return
                    }
                
                    lines := strings.Split(string(data), "\\n")
                
                    for _, testCase := range lines {
                        actual := solution.SwapFirstAndLastWords(testCase)
                        expected := validator.SwapFirstAndLastWords(testCase)
                
                        if actual != expected {
                            fmt.Printf("Test case failed: %s\\n", testCase)
                            fmt.Printf("Expected: %s\\n", expected)
                            fmt.Printf("Actual: %s\\n", actual)
                            return
                        }
                    }
                
                    fmt.Println("All test cases passed!")
                }
                """.trimIndent()
        ),
        compile = ProjectFile(
            name = "compile.sh",
            content =
                // language=sh
                """
                #!/usr/bin/env sh
                
                go build -o main.out
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
        resources = listOf(
            ProjectFile(
                name = "go.mod",
                content =
                    """
                    module problem

                    go 1.23
                    """.trimIndent()
            )
        )
    ),
    limits = Resources(time = 10, memory = 262144)
)
