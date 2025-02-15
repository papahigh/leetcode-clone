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
		fmt.Println("Error reading file:", err)
		return
	}

	lines := strings.Split(string(data), "\n")

	for _, testCase := range lines {
		actual := solution.SwapFirstAndLastWords(testCase)
		expected := validator.SwapFirstAndLastWords(testCase)

		if actual != expected {
			fmt.Printf("Test case failed: %s\n", testCase)
			fmt.Printf("Expected: %s\n", expected)
			fmt.Printf("Actual: %s\n", actual)
			return
		}
	}

	fmt.Println("All test cases passed!")
}
