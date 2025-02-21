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
            std::process::exit(405);
        }
    }

    eprintln!("[JUDGE_FEEDBACK]");
    eprintln!("ACCEPTED");
    eprintln!("[JUDGE_FEEDBACK]");
}
