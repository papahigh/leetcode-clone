package problem.stub.problem1

import INPUT
import problem.Problem.*
import problem.Problem.Language.RUST
import problem.Resources


val RUST_PROJECT = Project(
    lang = RUST,
    input = INPUT,
    files = ProjectFiles(
        solution = ProjectFile(
            name = "solution.rs",
            content =
                // language=rust
                """
                pub fn swap_first_and_last_words(s: &str) -> String {
                
                }
                """
        ),
        validator = ProjectFile(
            name = "validator.rs",
            content =
                // language=rust
                """
                pub fn swap_first_and_last_words(s: &str) -> String {
                    let mut words: Vec<&str> = s.split_whitespace().collect();
                
                    if words.len() <= 1 {
                        return s.to_string();
                    }
                
                    let len = words.len();
                    words.swap(0, len - 1);
                
                    words.join(" ")
                }
                """
        ),
        evaluator = ProjectFile(
            name = "main.rs",
            content =
                // language=rust
                """
                use std::fs;
                
                mod solution;
                mod validator;
                
                fn main() {
                    let input = fs::read_to_string("input.txt").expect("Unable to read file");
                
                    for test_case in input.lines() {
                        let actual = solution::swap_first_and_last_words(test_case);
                        let expected = validator::swap_first_and_last_words(test_case);
                
                        if actual != expected {
                            eprintln!("[JUDGE_FEEDBACK]");
                            eprintln!("WRONG_ANSWER");
                            eprintln!("Input: {}", test_case);
                            eprintln!("Output: {}", actual);
                            eprintln!("Expected: {}", expected);
                            eprintln!("[JUDGE_FEEDBACK]");
                            std::process::exit(65);
                        }
                    }
                
                    eprintln!("[JUDGE_FEEDBACK]");
                    eprintln!("ACCEPTED");
                    eprintln!("[JUDGE_FEEDBACK]");
                }
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
                
                rustc main.rs -o main.out
                
                EXIT_CODE="${'$'}?"
                if [[ "${'$'}EXIT_CODE" -eq 0 ]]
                then stderr "ACCEPTED"
                fi
                stderr "[JUDGE_FEEDBACK]"
                exit "${'$'}EXIT_CODE"
                """.trimIndent()
        ),
        resources = Resources(time = 3, memory = 75000)
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
        resources = Resources(time = 3, memory = 75000)
    ),
)
