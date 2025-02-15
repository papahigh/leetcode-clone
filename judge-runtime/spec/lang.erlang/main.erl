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
