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
        halt(405);
      true ->
        ok
    end
  end, Lines),

  io:format(standard_error, "[JUDGE_FEEDBACK]~n", []),
  io:format(standard_error, "ACCEPTED~n", []),
  io:format(standard_error, "[JUDGE_FEEDBACK]~n", []),
  halt(0).
