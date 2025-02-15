package problem.stub.problem1

import problem.Problem.*
import problem.Resources


val DART_PROJECT = Project(
    lang = Language.DART,
    files = ProjectFiles(
        solution = ProjectFile(
            name = "solution.dart",
            content =
                // language=dart
                """
                class Solution {
                  String swapFirstAndLastWords(String s) {
                
                  }
                }
                """.trimIndent()
        ),
        validator = ProjectFile(
            name = "validator.dart",
            content =
                // language=dart
                """
                class Validator {
                  String swapFirstAndLastWords(String s) {
                    List<String> words = s.split(' ');
                
                    if (words.length <= 1) {
                      return s;
                    }
                
                    String temp = words[0];
                    words[0] = words[words.length - 1];
                    words[words.length - 1] = temp;
                
                    return words.join(' ');
                  }
                }
                """.trimIndent()
        ),
        evaluator = ProjectFile(
            name = "main.dart",
            content =
                // language=dart
                """
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
                """.trimIndent()
        ),
        compile = ProjectFile(
            name = "compile.sh",
            content =
                // language=sh
                """
                #!/usr/bin/env sh
                
                dart compile exe main.dart -o main.out
                """.trimIndent()
        ),
        execute = ProjectFile(
            name = "execute.sh",
            content =
                // language=sh
                """
                #!/usr/bin/env sh
                
                ./main.out
                """.trimIndent()
        ),
        resources = listOf(
            ProjectFile(
                name = "pubspec.yaml",
                content =
                    // language=yaml
                    """
                    name: Problem
                    version: 1.0.0
                    environment:
                      sdk: '>=3.0.0 <4.0.0'
                    """.trimIndent()
            )
        )
    ),
    limits = Resources(time = 10, memory = 262144)
)
