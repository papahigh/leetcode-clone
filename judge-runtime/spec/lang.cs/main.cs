using System;
using System.IO;

class Program {
    static void Main() {
        var solution = new Solution();
        var validator = new Validator();

        foreach (var testCase in File.ReadAllLines("input.txt")) {
            var actual = solution.SwapFirstAndLastWords(testCase);
            var expected = validator.SwapFirstAndLastWords(testCase);

            if (actual != expected) {
                Console.WriteLine($"Test case failed: {testCase}");
                Console.WriteLine($"Expected: {expected}");
                Console.WriteLine($"Actual: {actual}");
                Environment.Exit(404);
            }
        }

        Console.WriteLine("All test cases passed!");
    }
}