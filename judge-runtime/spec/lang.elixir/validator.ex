defmodule Validator do
  @spec swap_first_and_last_words(String.t) :: String.t
  def swap_first_and_last_words(s) do
    words = String.split(s)

    if length(words) <= 1 do
      s
    else
      [first | rest] = words
      last = List.last(rest)
      rest = Enum.take(rest, length(rest) - 1)
      [last | rest] ++ [first]
      |> Enum.join(" ")
    end
  end
end
