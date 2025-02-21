import solution as s
import validator as v
import sys

with open('input.txt', 'r') as f:
    for testCase in f.readlines():
        testCase = testCase.strip()

        actual = s.swapFirstAndLastWords(testCase)
        expected = v.swapFirstAndLastWords(testCase)

        if actual != expected:
            print("[JUDGE_FEEDBACK]", file=sys.stderr)
            print("WRONG_ANSWER", file=sys.stderr)
            print(f"Input: {testCase}", file=sys.stderr)
            print(f"Output: {actual}", file=sys.stderr)
            print(f"Expected: {expected}", file=sys.stderr)
            print("[JUDGE_FEEDBACK]", file=sys.stderr)
            exit(65)

print("[JUDGE_FEEDBACK]", file=sys.stderr)
print("ACCEPTED", file=sys.stderr)
print("[JUDGE_FEEDBACK]", file=sys.stderr)
