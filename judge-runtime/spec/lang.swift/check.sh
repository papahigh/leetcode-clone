#!/usr/bin/env sh

rm -f main.out

if [ "$( swiftc main.swift -o main.out && ./main.out )" != "Hello, World!" ]; then
  exit 1
fi
