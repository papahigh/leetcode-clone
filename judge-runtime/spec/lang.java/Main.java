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
                System.out.println("Test case failed: " + testCase);
                System.out.println("Expected: " + expected);
                System.out.println("Actual: " + actual);
                System.exit(404);
            }
        });

        System.out.println("All test cases passed!");
    }
}
