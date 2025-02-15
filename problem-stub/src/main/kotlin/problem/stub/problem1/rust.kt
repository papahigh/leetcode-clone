package problem.stub.problem1

import problem.Problem.*
import problem.Resources


val RUST_PROJECT = Project(
    lang = Language.RUST,
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
                            println!("Test case failed: {}", test_case);
                            println!("Expected: {}", expected);
                            println!("Actual: {}", actual);
                            std::process::exit(404);
                        }
                    }
                
                    println!("All test cases passed!");
                }
                """.trimIndent()
        ),
        compile = ProjectFile(
            name = "compile.sh",
            content =
                // language=sh
                """
                #!/usr/bin/env sh
                
                rustc main.rs -o main.out
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
