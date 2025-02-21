package main

import (
	"fmt"
	"os"
	"problem/solution"
	"problem/validator"
	"strings"
)

func main() {

	data, err := os.ReadFile("input.txt")
	if err != nil {
		fmt.Fprintf(os.Stderr, "[JUDGE_ERROR]:%v\n", err)
		os.Exit(1)
	}

	lines := strings.Split(string(data), "\n")

	for _, testCase := range lines {
		actual := solution.SwapFirstAndLastWords(testCase)
		expected := validator.SwapFirstAndLastWords(testCase)

		if actual != expected {
			fmt.Fprintln(os.Stderr, "[JUDGE_FEEDBACK]")
			fmt.Fprintln(os.Stderr, "WRONG_ANSWER")
			fmt.Fprintf(os.Stderr, "Input: %s\n", testCase)
			fmt.Fprintf(os.Stderr, "Output: %s\n", actual)
			fmt.Fprintf(os.Stderr, "Expected: %s\n", expected)
			fmt.Fprintln(os.Stderr, "[JUDGE_FEEDBACK]")
			os.Exit(65)
		}
	}

	fmt.Fprintln(os.Stderr, "[JUDGE_FEEDBACK]")
	fmt.Fprintln(os.Stderr, "ACCEPTED")
	fmt.Fprintln(os.Stderr, "[JUDGE_FEEDBACK]")
}
