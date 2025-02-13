#!/usr/bin/env sh

if [ "$( java main )" != "Hello, World!" ]; then
  exit 1
fi
