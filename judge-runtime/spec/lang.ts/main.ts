import { Solution } from './solution';
import { Validator } from './validator';
// @ts-ignore
import * as fs from 'node:fs';

const solution = new Solution();
const validator = new Validator();

fs.readFileSync('input.txt', 'utf-8').split('\\n').forEach((testCase: string) => {
    const actual = solution.swapFirstAndLastWords(testCase);
    const expected = validator.swapFirstAndLastWords(testCase);

    if (actual !== expected) {
        console.log('Test case failed: ' + testCase);
        console.log('Expected: ' + expected);
        console.log('Actual: ' + actual);
        // @ts-ignore
        process.exit(404);
    }
});

console.log('All test cases passed!');
