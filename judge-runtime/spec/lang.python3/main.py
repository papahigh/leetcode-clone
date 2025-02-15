import solution as s
import validator as v

with open('input.txt', 'r') as f:
    for testCase in f.readlines():
        testCase = testCase.strip()

        actual = s.swapFirstAndLastWords(testCase)
        expected = v.swapFirstAndLastWords(testCase)

        if actual != expected:
            print(f"Test case failed: {testCase}")
            print(f"Expected: {expected}")
            print(f"Actual: {actual}")
            exit(404)

print("All test cases passed!")
