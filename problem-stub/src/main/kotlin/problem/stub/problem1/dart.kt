package problem.stub.problem1

import INPUT
import problem.Problem.*
import problem.Problem.Language.DART
import problem.Resources
import kotlin.time.Duration.Companion.seconds


val DART_PROJECT = Project(
    lang = DART,
    input = INPUT,
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
                      stderr.writeln('[JUDGE_FEEDBACK]');
                      stderr.writeln('WRONG_ANSWER');
                      stderr.writeln('Input: ' + testCase);
                      stderr.writeln('Output: ' + actual);
                      stderr.writeln('Expected: ' + expected);
                      stderr.writeln('[JUDGE_FEEDBACK]');
                      exit(65);
                    }
                  }
                
                  stderr.writeln('[JUDGE_FEEDBACK]');
                  stderr.writeln('ACCEPTED');
                  stderr.writeln('[JUDGE_FEEDBACK]');
                }
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
    compile = ProjectAction(
        script = ProjectFile(
            name = "compile.sh",
            content =
                // language=bash
                """
                #!/usr/bin/env bash
                
                stderr() { echo "${'$'}@" 1>&2; }
                stderr "[JUDGE_FEEDBACK]"
                
                dart compile exe main.dart -o main.out
                
                EXIT_CODE="${'$'}?"
                if [[ "${'$'}EXIT_CODE" -eq 0 ]]
                then stderr "ACCEPTED"
                fi
                stderr "[JUDGE_FEEDBACK]"
                exit "${'$'}EXIT_CODE"
                """.trimIndent()
        ),
        resources = Resources(time = 3.seconds, memory = 75000)
    ),
    execute = ProjectAction(
        script = ProjectFile(
            name = "execute.sh",
            content =
                // language=bash
                """
                #!/usr/bin/env bash
                
                ./main.out
                """.trimIndent()
        ),
        resources = Resources(time = 3.seconds, memory = 75000)
    ),
)
