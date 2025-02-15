using System;

public class Validator {
    public string SwapFirstAndLastWords(string s) {
        var words = s.Split(' ');

        if (words.Length <= 1) {
            return s;
        }

        (words[0], words[^1]) = (words[^1], words[0]);

        return string.Join(" ", words);
    }
}
