#!/usr/bin/env sh

rm -f main.out

if [ "$( clang++ -std=c++23 -o main.out main.cc && ./main.out )" != "Hello, World!" ]; then
  exit 1
fi
