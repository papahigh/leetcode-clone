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
