package problem.stub.problem1

import problem.Problem.*
import problem.Problem.Language.ERLANG
import problem.Resources
import problem.resources.Memory.Companion.kilobytes
import problem.resources.Time.Companion.seconds


val ERLANG_PROJECT = Project(
    lang = ERLANG,
    input = INPUT,
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
                  Lines = string:tokens(binary_to_list(Data), "\n"),
                
                  lists:foreach(fun(TestCase) ->
                    Actual = Solution:swap_first_and_last_words(TestCase),
                    Expected = Validator:swap_first_and_last_words(TestCase),
                
                    if
                      Actual /= Expected ->
                        io:format(standard_error, "[JUDGE_FEEDBACK]~n", []),
                        io:format(standard_error, "WRONG_ANSWER~n", []),
                        io:format(standard_error, "Input: ~s~n", [TestCase]),
                        io:format(standard_error, "Output: ~s~n", [Actual]),
                        io:format(standard_error, "Expected: ~s~n", [Expected]),
                        io:format(standard_error, "[JUDGE_FEEDBACK]~n", []),
                        halt(65);
                      true ->
                        ok
                    end
                  end, Lines),
                
                  io:format(standard_error, "[JUDGE_FEEDBACK]~n", []),
                  io:format(standard_error, "ACCEPTED~n", []),
                  io:format(standard_error, "[JUDGE_FEEDBACK]~n", []),
                  halt(0).
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
                
                erlc solution.erl validator.erl main.erl
                
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
                
                erl -noshell -s main run -s init stop
                """.trimIndent()
        ),
        resources = Resources(time = 3.seconds, memory = 75000.kilobytes)
    ),
)
