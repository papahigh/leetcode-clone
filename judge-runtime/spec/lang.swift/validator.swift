import Foundation

public class Validator {
    public func swapFirstAndLastWords(_ s: String) -> String {
        let words = s.split(separator: " ")

        if words.count <= 1 {
            return s
        }

        var wordsArray = Array(words)
        wordsArray.swapAt(0, wordsArray.count - 1)

        return wordsArray.joined(separator: " ")
    }
}
