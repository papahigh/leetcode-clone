#!/usr/bin/env sh

if [ "$( python3 main.py )" != "Hello, World!" ]; then
  exit 1
fi
