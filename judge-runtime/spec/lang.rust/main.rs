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
