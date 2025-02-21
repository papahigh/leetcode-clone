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
                Console.Error.WriteLine("[JUDGE_FEEDBACK]");
                Console.Error.WriteLine("WRONG_ANSWER");
                Console.Error.WriteLine($"Input: {testCase}");
                Console.Error.WriteLine($"Output: {actual}");
                Console.Error.WriteLine($"Expected: {expected}");
                Console.Error.WriteLine("[JUDGE_FEEDBACK]");
                Environment.Exit(405);
            }
        }

        Console.Error.WriteLine("[JUDGE_FEEDBACK]");
        Console.Error.WriteLine("ACCEPTED");
        Console.Error.WriteLine("[JUDGE_FEEDBACK]");
    }
}