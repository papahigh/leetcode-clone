<?php
require_once 'solution.php';
require_once 'validator.php';

$input = file('input.txt', FILE_IGNORE_NEW_LINES);

$solution = new Solution();
$validator = new Validator();

foreach ($input as $testCase) {
    $actual = $solution->swapFirstAndLastWords($testCase);
    $expected = $validator->swapFirstAndLastWords($testCase);

    if ($actual !== $expected) {
        fwrite(STDERR, "[JUDGE_FEEDBACK]\n");
        fwrite(STDERR, "WRONG_ANSWER\n");
        fwrite(STDERR, "Input: " . $testCase . "\n");
        fwrite(STDERR, "Output: " . $actual . "\n");
        fwrite(STDERR, "Expected: " . $expected . "\n");
        fwrite(STDERR, "[JUDGE_FEEDBACK]\n");
        exit(405);
    }
}

fwrite(STDERR, "[JUDGE_FEEDBACK]\n");
fwrite(STDERR, "ACCEPTED\n");
fwrite(STDERR, "[JUDGE_FEEDBACK]\n");
