#!/usr/bin/env sh

if [ "$( dart main.dart )" != "Hello, World!" ]; then
  exit 1
fi
