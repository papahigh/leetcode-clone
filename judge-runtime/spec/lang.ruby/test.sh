#!/usr/bin/env bash

set -e

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
bash "$DIR/cleanup.sh"

sudo nsjail --config /etc/runtime/ruby.cfg --bindmount "$DIR:/app" --cwd=/app -- /usr/bin/bash compile.sh
sudo nsjail --config /etc/runtime/ruby.cfg --bindmount "$DIR:/app" --cwd=/app -- /usr/bin/bash execute.sh

bash "$DIR/cleanup.sh"
