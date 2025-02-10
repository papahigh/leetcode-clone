#!/usr/bin/env sh

rm -rf ./obj
rm -rf ./out

if [ "$( dotnet build --configuration Release -o ./out . > /dev/null && ./out/main )" != "Hello, World!" ]; then
  exit 1
fi
