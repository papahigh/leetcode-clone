import * as solution from './solution.mjs';
import * as validator from './validator.mjs';
import * as fs from 'node:fs';

fs.readFileSync('input.txt', 'utf-8').split('\n').forEach(testCase => {
    const actual = solution.swapFirstAndLastWords(testCase);
    const expected = validator.swapFirstAndLastWords(testCase);

    if (actual !== expected) {
        console.log('Test case failed: ' + testCase);
        console.log('Expected: ' + expected);
        console.log('Actual: ' + actual);
        process.exit(404);
    }
});

console.log('All test cases passed!');
