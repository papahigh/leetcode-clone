package validator

import "strings"

func SwapFirstAndLastWords(s string) string {
	words := strings.Fields(s)

	if len(words) <= 1 {
		return s
	}

	words[0], words[len(words)-1] = words[len(words)-1], words[0]
	return strings.Join(words, " ")
}
