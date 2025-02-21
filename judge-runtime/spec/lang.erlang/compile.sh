#!/usr/bin/env bash

stderr() { echo "$@" 1>&2; }
stderr "[JUDGE_FEEDBACK]"

erlc solution.erl validator.erl main.erl

EXIT_CODE="$?"
if [[ "$EXIT_CODE" -eq 0 ]]
then stderr "ACCEPTED"
fi
stderr "[JUDGE_FEEDBACK]"
exit "$EXIT_CODE"
