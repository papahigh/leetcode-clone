#!/usr/bin/env sh

if [ "$( elixir main.exs )" != "Hello, World!" ]; then
  exit 1
fi
