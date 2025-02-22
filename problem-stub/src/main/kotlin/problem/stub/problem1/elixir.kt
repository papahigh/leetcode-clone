package problem.stub.problem1

import problem.Problem.*
import problem.Problem.Language.ELIXIR
import problem.Resources
import problem.resources.Memory.Companion.kilobytes
import problem.resources.Time.Companion.seconds


val ELIXIR_PROJECT = Project(
    lang = ELIXIR,
    input = INPUT,
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
                        System.halt(65)
                      end
                    end)
                
                    IO.write(:stderr, "[JUDGE_FEEDBACK]\n")
                    IO.write(:stderr, "ACCEPTED\n")
                    IO.write(:stderr, "[JUDGE_FEEDBACK]\n")
                  end
                end
                """.trimIndent()
        ),
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
                
                elixirc solution.ex validator.ex main.ex
                
                EXIT_CODE="${'$'}?"
                if [[ "${'$'}EXIT_CODE" -eq 0 ]]
                then stderr "ACCEPTED"
                fi
                stderr "[JUDGE_FEEDBACK]"
                exit "${'$'}EXIT_CODE"
                """.trimIndent()
        ),
        resources = Resources(time = 3.seconds, memory = 75000.kilobytes)
    ),
    execute = ProjectAction(
        script = ProjectFile(
            name = "execute.sh",
            content =
                // language=bash
                """
                #!/usr/bin/env bash
                
                elixir -e "Main.run()"
                """.trimIndent()
        ),
        resources = Resources(time = 3.seconds, memory = 75000.kilobytes)
    ),
)
