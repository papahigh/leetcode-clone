export class Validator {
    public swapFirstAndLastWords(s: string): string {
        const words = s.split(' ');

        if (words.length <= 1) {
            return s;
        }

        [words[0], words[words.length - 1]] = [words[words.length - 1], words[0]];

        return words.join(' ');
    }
}
