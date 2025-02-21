import Foundation

let solution = Solution()
let validator = Validator()

func stderr(_ message: String) {
    if let data = message.data(using: .utf8) {
        FileHandle.standardError.write(data)
        FileHandle.standardError.write("\n".data(using: .utf8)!)
    }
}

if let input = try? String(contentsOfFile: "input.txt") {
    let testCases = input.split(separator: "\n")

    for testCase in testCases {
        let actual = solution.swapFirstAndLastWords(String(testCase))
        let expected = validator.swapFirstAndLastWords(String(testCase))

        if actual != expected {
            stderr("[JUDGE_FEEDBACK]")
            stderr("WRONG_ANSWER")
            stderr("Input: \(testCase)")
            stderr("Output: \(actual)")
            stderr("Expected: \(expected)")
            stderr("[JUDGE_FEEDBACK]")
            exit(405)
        }
    }
}

stderr("[JUDGE_FEEDBACK]")
stderr("ACCEPTED")
stderr("[JUDGE_FEEDBACK]")
