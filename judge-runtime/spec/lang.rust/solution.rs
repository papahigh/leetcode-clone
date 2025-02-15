pub fn swap_first_and_last_words(s: &str) -> String {
    let mut words: Vec<&str> = s.split_whitespace().collect();

    if words.len() <= 1 {
        return s.to_string();
    }

    let len = words.len();
    words.swap(0, len - 1);

    words.join(" ")
}
