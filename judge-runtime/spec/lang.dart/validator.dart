class Validator {
  String swapFirstAndLastWords(String s) {
    List<String> words = s.split(' ');

    if (words.length <= 1) {
      return s;
    }

    String temp = words[0];
    words[0] = words[words.length - 1];
    words[words.length - 1] = temp;

    return words.join(' ');
  }
}
