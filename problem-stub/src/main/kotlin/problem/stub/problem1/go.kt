package problem.stub.problem1

import problem.Problem.*
import problem.Problem.Language.GO
import problem.Resources
import problem.resources.Memory.Companion.kilobytes
import problem.resources.Time.Companion.seconds


val GO_PROJECT = Project(
    lang = GO,
    input = INPUT,
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
                        fmt.Fprintf(os.Stderr, "[JUDGE_ERROR]:%v\n", err)
                        os.Exit(1)
                    }
                
                    lines := strings.Split(string(data), "\n")
                
                    for _, testCase := range lines {
                        actual := solution.SwapFirstAndLastWords(testCase)
                        expected := validator.SwapFirstAndLastWords(testCase)
                
                        if actual != expected {
                            fmt.Fprintln(os.Stderr, "[JUDGE_FEEDBACK]")
                            fmt.Fprintln(os.Stderr, "WRONG_ANSWER")
                            fmt.Fprintf(os.Stderr, "Input: %s\n", testCase)
                            fmt.Fprintf(os.Stderr, "Output: %s\n", actual)
                            fmt.Fprintf(os.Stderr, "Expected: %s\n", expected)
                            fmt.Fprintln(os.Stderr, "[JUDGE_FEEDBACK]")
                            os.Exit(65)
                        }
                    }
                
                    fmt.Fprintln(os.Stderr, "[JUDGE_FEEDBACK]")
                    fmt.Fprintln(os.Stderr, "ACCEPTED")
                    fmt.Fprintln(os.Stderr, "[JUDGE_FEEDBACK]")
                }
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
    compile = ProjectAction(

        script = ProjectFile(
            name = "compile.sh",
            content =
                // language=bash
                """
                #!/usr/bin/env bash
                
                stderr() { echo "${'$'}@" 1>&2; }
                stderr "[JUDGE_FEEDBACK]"
                
                go build -o main.out
                
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
                
                ./main.out
                """.trimIndent()
        ),
        resources = Resources(time = 3.seconds, memory = 75000.kilobytes)
    ),
)
