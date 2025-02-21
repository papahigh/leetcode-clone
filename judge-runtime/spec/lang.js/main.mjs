import * as solution from './solution.mjs';
import * as validator from './validator.mjs';
import * as fs from 'node:fs';

fs.readFileSync('input.txt', 'utf-8').split('\n').forEach(testCase => {
    const actual = solution.swapFirstAndLastWords(testCase);
    const expected = validator.swapFirstAndLastWords(testCase);

    if (actual !== expected) {
        console.error('[JUDGE_FEEDBACK]');
        console.error('WRONG_ANSWER');
        console.error('Input: ' + testCase);
        console.error('Output: ' + actual);
        console.error('Expected: ' + expected);
        console.error('[JUDGE_FEEDBACK]');
        process.exit(405);
    }
});

console.error('[JUDGE_FEEDBACK]');
console.error('ACCEPTED');
console.error('[JUDGE_FEEDBACK]');
