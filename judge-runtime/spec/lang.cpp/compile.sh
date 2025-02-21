#!/usr/bin/env bash

stderr() { echo "$@" 1>&2; }
stderr "[JUDGE_FEEDBACK]"

clang++ -std=c++23 -o main.out main.cpp solution.cpp validator.cpp

EXIT_CODE="$?"
if [[ "$EXIT_CODE" -eq 0 ]]
then stderr "ACCEPTED"
fi
stderr "[JUDGE_FEEDBACK]"
exit "$EXIT_CODE"
