#!/usr/bin/env sh

if [ "$( erl -noshell -s main start -s init stop )" != "Hello, World!" ]; then
  exit 1
fi

