import { Solution } from './solution';
import { Validator } from './validator';
// @ts-ignore
import * as fs from 'node:fs';

const solution = new Solution();
const validator = new Validator();

fs.readFileSync('input.txt', 'utf-8').split('\n').forEach((testCase: string) => {
    const actual = solution.swapFirstAndLastWords(testCase);
    const expected = validator.swapFirstAndLastWords(testCase);

    if (actual !== expected) {
        console.error('[JUDGE_FEEDBACK]');
        console.error('WRONG_ANSWER');
        console.error('Input: ' + testCase);
        console.error('Output: ' + actual);
        console.error('Expected: ' + expected);
        console.error('[JUDGE_FEEDBACK]');
        // @ts-ignore
        process.exit(405);
    }
});

console.error('[JUDGE_FEEDBACK]');
console.error('ACCEPTED');
console.error('[JUDGE_FEEDBACK]');
