#!/usr/bin/env sh

if [ "$( ruby main.rb )" != "Hello, World!" ]; then
  exit 1
fi
