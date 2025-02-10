#!/usr/bin/env sh

if [ "$( node main.js )" != "Hello, World!" ]; then
  exit 1
fi
