package problem.stub.problem1.projects

import problem.Problem.*
import problem.Resources


val ERLANG_PROJECT = Project(
    lang = Language.ERLANG,
    files = ProjectFiles(
        solution = ProjectFile(
            name = "solution.erl",
            content =
                // language=erl
                """
                -module(solution).
                -export([swap_first_and_last_words/1]).
                
                -spec swap_first_and_last_words(string()) -> string().
                swap_first_and_last_words(S) ->
                    .
                """.trimIndent()
        ),
        validator = ProjectFile(
            name = "validator.erl",
            content =
                // language=erl
                """
                -module(validator).
                -export([swap_first_and_last_words/1]).
                
                -spec swap_first_and_last_words(string()) -> string().
                swap_first_and_last_words(S) ->
                    Words = string:tokens(S, " "),
                    case Words of
                        [_] -> S;
                        [] -> S;
                        _ ->
                            [First | Rest] = Words,
                            RestLen = length(Rest),
                            case RestLen of
                                0 -> S;
                                _ ->
                                    Last = lists:last(Rest),
                                    Middle = lists:sublist(Rest, 1, RestLen - 1),
                                    Rearranged = [Last | Middle] ++ [First],
                                    string:join(Rearranged, " ")
                            end
                    end.
                """.trimIndent()
        ),
        evaluator = ProjectFile(
            name = "main.erl",
            content =
                // language=erl
                """
                -module(main).
                -export([run/0]).
                
                run() ->
                  Solution = solution,
                  Validator = validator,
                
                  {ok, Data} = file:read_file("input.txt"),
                  Lines = string:tokens(binary_to_list(Data), "\\n"),
                
                  lists:foreach(fun(TestCase) ->
                    Actual = Solution:swap_first_and_last_words(TestCase),
                    Expected = Validator:swap_first_and_last_words(TestCase),
                
                    if
                      Actual /= Expected ->
                        io:format("Test case failed: ~s~n", [TestCase]),
                        io:format("Expected: ~s~n", [Expected]),
                        io:format("Actual: ~s~n", [Actual]),
                        halt(404);
                      true ->
                        ok
                    end
                  end, Lines),
                
                  io:format("All test cases passed!~n"),
                  halt(0).
                """.trimIndent()
        ),
        compile = ProjectFile(
            name = "compile.sh",
            content =
                // language=sh
                """
                #!/usr/bin/env sh
                
                erlc solution.erl validator.erl main.erl
                """.trimIndent()
        ),
        execute = ProjectFile(
            name = "execute.sh",
            content =
                // language=sh
                """
                #!/usr/bin/env sh
                
                erl -noshell -s main run -s init stop
                """.trimIndent()
        ),
    ),
    limits = Resources(time = 10, memory = 262144)
)
