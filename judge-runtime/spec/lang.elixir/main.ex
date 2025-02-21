defmodule Main do
  def run do
    File.read!("input.txt")
    |> String.split("\n")
    |> Enum.each(fn test_case ->
      actual = Solution.swap_first_and_last_words(test_case)
      expected = Validator.swap_first_and_last_words(test_case)

      if actual != expected do
        IO.write(:stderr, "[JUDGE_FEEDBACK]\n")
        IO.write(:stderr, "WRONG_ANSWER\n")
        IO.write(:stderr, "Input: #{test_case}\n")
        IO.write(:stderr, "Output: #{actual}\n")
        IO.write(:stderr, "Expected: #{expected}\n")
        IO.write(:stderr, "[JUDGE_FEEDBACK]\n")
        System.halt(405)
      end
    end)

    IO.write(:stderr, "[JUDGE_FEEDBACK]\n")
    IO.write(:stderr, "ACCEPTED\n")
    IO.write(:stderr, "[JUDGE_FEEDBACK]\n")
  end
end
