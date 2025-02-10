#!/usr/bin/env sh

rm -f main.class

if [ "$( javac main.java && java main )" != "Hello, World!" ]; then
  exit 1
fi
