#!/usr/bin/env sh

if [ "$( php main.php )" != "Hello, World!" ]; then
  exit 1
fi
