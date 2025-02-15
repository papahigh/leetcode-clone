package problem.stub.problem1

import problem.Problem.*
import problem.Resources


val RUBY_PROJECT = Project(
    lang = Language.RUBY,
    files = ProjectFiles(
        solution = ProjectFile(
            name = "solution.rb",
            content =
                // language=ruby
                """
                def swap_first_and_last_words_solution(s)
                
                end
                """
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
                """
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
                    puts "Test case failed: #{testCase}"
                    puts "Expected: #{expected}"
                    puts "Actual: #{actual}"
                    exit(404)
                  end
                end
                
                puts "All test cases passed!"
                """
        ),
        execute = ProjectFile(
            name = "execute.sh",
            content =
                // language=sh
                """
                #!/usr/bin/env sh
                
                ruby main.rb
                """.trimIndent()
        ),
    ),
    limits = Resources(time = 10, memory = 262144)
)
