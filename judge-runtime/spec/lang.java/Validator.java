import java.util.*;

class Validator {
    public String swapFirstAndLastWords(String s) {
        String[] words = s.split(" ");

        if (words.length <= 1) {
            return s;
        }

        String temp = words[0];
        words[0] = words[words.length - 1];
        words[words.length - 1] = temp;

        return String.join(" ", words);
    }
}
