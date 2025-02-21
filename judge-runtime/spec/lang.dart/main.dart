import 'dart:io';
import 'solution.dart';
import 'validator.dart';

void main() {
  var solution = Solution();
  var validator = Validator();

  var lines = File('input.txt').readAsLinesSync();

  for (var testCase in lines) {
    var actual = solution.swapFirstAndLastWords(testCase);
    var expected = validator.swapFirstAndLastWords(testCase);

    if (actual != expected) {
      stderr.writeln('[JUDGE_FEEDBACK]');
      stderr.writeln('WRONG_ANSWER');
      stderr.writeln('Input: ' + testCase);
      stderr.writeln('Output: ' + actual);
      stderr.writeln('Expected: ' + expected);
      stderr.writeln('[JUDGE_FEEDBACK]');
      exit(405);
    }
  }

  stderr.writeln('[JUDGE_FEEDBACK]');
  stderr.writeln('ACCEPTED');
  stderr.writeln('[JUDGE_FEEDBACK]');
}
