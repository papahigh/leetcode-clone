#!/usr/bin/env bash

stderr() { echo "$@" 1>&2; }
stderr "[JUDGE_FEEDBACK]"

clang -std=c23 -o main.out main.c solution.c validator.c

EXIT_CODE="$?"
if [[ "$EXIT_CODE" -eq 0 ]]
then stderr "ACCEPTED"
fi
stderr "[JUDGE_FEEDBACK]"
exit "$EXIT_CODE"
