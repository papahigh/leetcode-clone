#!/usr/bin/env sh

rm -f main.out

if [ "$( rustc main.rs -o main.out && ./main.out )" != "Hello, World!" ]; then
  exit 1
fi
