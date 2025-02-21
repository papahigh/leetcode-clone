import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws Exception {

        var solution = new Solution();
        var verifier = new Validator();

        Files.readAllLines(Paths.get("input.txt")).forEach(testCase -> {

            var actual = solution.swapFirstAndLastWords(testCase);
            var expected = verifier.swapFirstAndLastWords(testCase);

            if (!expected.equals(actual)) {
                System.err.println("[JUDGE_FEEDBACK]");
                System.err.println("WRONG_ANSWER");
                System.err.println("Input: " + testCase);
                System.err.println("Output: " + actual);
                System.err.println("Expected: " + expected);
                System.err.println("[JUDGE_FEEDBACK]");
                System.exit(65);
            }
        });

        System.err.println("[JUDGE_FEEDBACK]");
        System.err.println("ACCEPTED");
        System.err.println("[JUDGE_FEEDBACK]");
    }
}
