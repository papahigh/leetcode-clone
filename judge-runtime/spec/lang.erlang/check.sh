#!/usr/bin/env sh

rm -f main.beam

if [ "$( erlc main.erl && erl -noshell -s main start -s init stop )" != "Hello, World!" ]; then
  exit 1
fi
