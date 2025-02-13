#!/usr/bin/env sh

if [ "$( java -jar main.jar )" != "Hello, World!" ]; then
  exit 1
fi
