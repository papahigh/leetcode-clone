defmodule Main do
  def run do
    File.read!("input.txt")
    |> String.split("\n")
    |> Enum.each(fn test_case ->
      actual = Solution.swap_first_and_last_words(test_case)
      expected = Validator.swap_first_and_last_words(test_case)

      if actual != expected do
        IO.puts("Test case failed: #{test_case}")
        IO.puts("Expected: #{expected}")
        IO.puts("Actual: #{actual}")
        System.halt(404)
      end
    end)

    IO.puts("All test cases passed!")
  end
end
