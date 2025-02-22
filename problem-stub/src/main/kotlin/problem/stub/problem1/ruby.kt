package problem.stub.problem1

import problem.Problem.*
import problem.Problem.Language.RUBY
import problem.Resources
import problem.resources.Memory.Companion.kilobytes
import problem.resources.Time.Companion.seconds


val RUBY_PROJECT = Project(
    lang = RUBY,
    input = INPUT,
    files = ProjectFiles(
        solution = ProjectFile(
            name = "solution.rb",
            content =
                // language=ruby
                """
                def swap_first_and_last_words_solution(s)
                
                end
                """.trimIndent()
        ),
        validator = ProjectFile(
            name = "validator.rb",
            content =
                // language=ruby
                """
                def swap_first_and_last_words_validator(s)
                  words = s.split
                
                  if words.length <= 1
                    return s
                  end
                
                  words[0], words[-1] = words[-1], words[0]
                
                  return words.join(' ')
                end
                """.trimIndent()
        ),
        evaluator = ProjectFile(
            name = "main.rb",
            content =
                // language=ruby
                """
                require_relative 'solution'
                require_relative 'validator'
                
                File.readlines('input.txt').each do |testCase|
                  testCase = testCase.strip
                
                  actual = swap_first_and_last_words_solution(testCase)
                  expected = swap_first_and_last_words_validator(testCase)
                
                  if actual != expected
                    warn "[JUDGE_FEEDBACK]"
                    warn "WRONG_ANSWER"
                    warn "Input: #{testCase}"
                    warn "Output: #{actual}"
                    warn "Expected: #{expected}"
                    warn "[JUDGE_FEEDBACK]"
                    exit(65)
                  end
                end
                
                warn "[JUDGE_FEEDBACK]"
                warn "ACCEPTED"
                warn "[JUDGE_FEEDBACK]"
                """.trimIndent()
        ),
    ),
    execute = ProjectAction(
        script = ProjectFile(
            name = "execute.sh",
            content =
                // language=bash
                """
                #!/usr/bin/env bash
                
                ruby main.rb
                """.trimIndent()
        ),
        resources = Resources(time = 3.seconds, memory = 75000.kilobytes)
    ),
)
