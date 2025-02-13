#!/usr/bin/env sh

if [ "$( ./out/main )" != "Hello, World!" ]; then
  exit 1
fi
