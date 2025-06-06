#!/usr/bin/env bash

stderr() { echo "$@" 1>&2; }
stderr "[JUDGE_FEEDBACK]"

dart compile exe main.dart -o main.out

EXIT_CODE="$?"
if [[ "$EXIT_CODE" -eq 0 ]]
then stderr "ACCEPTED"
fi
stderr "[JUDGE_FEEDBACK]"
exit "$EXIT_CODE"
