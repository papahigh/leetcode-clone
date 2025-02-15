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
        echo "Test case failed: " . $testCase . "\n";
        echo "Expected: " . $expected . "\n";
        echo "Actual: " . $actual . "\n";
        exit(404);
    }
}

echo "All test cases passed!\n";
