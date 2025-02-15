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
