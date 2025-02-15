package problem.stub.problem1.projects

import problem.Problem.*
import problem.Resources


val ELIXIR_PROJECT = Project(
    lang = Language.ELIXIR,
    files = ProjectFiles(
        solution = ProjectFile(
            name = "solution.ex",
            content =
                // language=elixir
                """
                defmodule Solution do
                  @spec swap_first_and_last_words(String.t) :: String.t
                  def swap_first_and_last_words(s) do
                
                  end
                end
                """.trimIndent()
        ),
        validator = ProjectFile(
            name = "validator.ex",
            content =
                // language=elixir
                """
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
                """.trimIndent()
        ),
        evaluator = ProjectFile(
            name = "main.ex",
            content =
                // language=elixir
                """
                defmodule Main do
                  def run do
                    File.read!("input.txt")
                    |> String.split("\\n")
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
                """.trimIndent()
        ),
        compile = ProjectFile(
            name = "compile.sh",
            content =
                // language=sh
                """
                #!/usr/bin/env sh
                
                elixirc solution.ex validator.ex main.ex
                """.trimIndent()
        ),
        execute = ProjectFile(
            name = "execute.sh",
            content =
                // language=sh
                """
                #!/usr/bin/env sh
                
                elixir -e "Main.run()"
                """.trimIndent()
        ),
    ),
    limits = Resources(time = 10, memory = 262144)
)
