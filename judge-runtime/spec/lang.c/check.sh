#!/usr/bin/env sh

rm -f main.out

if [ "$( clang -std=c23 -o main.out main.c && ./main.out )" != "Hello, World!" ]; then
  exit 1
fi
