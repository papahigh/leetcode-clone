#!/usr/bin/env bash

set -e
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

sudo nsjail --config /etc/runtime/java.cfg --bindmount "$DIR:/app" --cwd=/app -- /usr/bin/bash execute.sh
