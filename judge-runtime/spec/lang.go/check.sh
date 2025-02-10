#!/usr/bin/env sh

rm -f main.out

if [ "$( go build -o main.out main.go && ./main.out )" != "Hello, World!" ]; then
  exit 1
fi
