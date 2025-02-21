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
    exit(405)
  end
end

warn "[JUDGE_FEEDBACK]"
warn "ACCEPTED"
warn "[JUDGE_FEEDBACK]"
