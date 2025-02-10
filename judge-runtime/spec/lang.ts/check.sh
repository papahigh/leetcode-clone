#!/usr/bin/env sh

rm -f main.js

if [ "$( tsc main.ts && node main.js )" != "Hello, World!" ]; then
  exit 1
fi
