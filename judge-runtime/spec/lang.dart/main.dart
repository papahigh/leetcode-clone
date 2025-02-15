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
      print('Test case failed: ' + testCase);
      print('Expected: ' + expected);
      print('Actual: ' + actual);
      exit(404);
    }
  }

  print('All test cases passed!');
}
