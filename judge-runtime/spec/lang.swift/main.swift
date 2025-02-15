import Foundation

let solution = Solution()
let validator = Validator()

if let input = try? String(contentsOfFile: "input.txt") {
    let testCases = input.split(separator: "\n")

    for testCase in testCases {
        let actual = solution.swapFirstAndLastWords(String(testCase))
        let expected = validator.swapFirstAndLastWords(String(testCase))

        if actual != expected {
            print("Test case failed: \(testCase)")
            print("Expected: \(expected)")
            print("Actual: \(actual)")
            exit(404)
        }
    }
}

print("All test cases passed!")
