#!/usr/bin/env sh

rm -f main.jar

if [ "$( kotlinc main.kt -d main.jar && java -jar main.jar )" != "Hello, World!" ]; then
  exit 1
fi
