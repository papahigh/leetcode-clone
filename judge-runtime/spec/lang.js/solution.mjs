export function swapFirstAndLastWords(s) {
    const words = s.split(' ');

    if (words.length <= 1) {
        return s;
    }

    const temp = words[0];
    words[0] = words[words.length - 1];
    words[words.length - 1] = temp;

    return words.join(' ');
}
