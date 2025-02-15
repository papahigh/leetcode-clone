def swapFirstAndLastWords(s: str) -> str:
    words = s.split()

    if len(words) <= 1:
        return s

    words[0], words[-1] = words[-1], words[0]

    return " ".join(words)
