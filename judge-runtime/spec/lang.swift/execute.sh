#!/usr/bin/env sh

if [ "$( ./main.out )" != "Hello, World!" ]; then
  exit 1
fi
