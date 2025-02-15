class Validator {
    fun swapFirstAndLastWords(s: String): String {
        val words = s.split(" ").toMutableList()

        if (words.size <= 1) {
            return s
        }

        val temp = words[0]
        words[0] = words[words.size - 1]
        words[words.size - 1] = temp

        return words.joinToString(" ")
    }
}
