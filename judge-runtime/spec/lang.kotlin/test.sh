#!/usr/bin/env bash

set -e

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
bash "$DIR/cleanup.sh"

sudo nsjail --config /etc/runtime/kotlin.cfg --bindmount "$DIR:/app" --time_limit=10 --cgroup_mem_max=512000000 --cwd=/app -- /usr/bin/bash compile.sh
sudo nsjail --config /etc/runtime/kotlin.cfg --bindmount "$DIR:/app" --cwd=/app -- /usr/bin/bash execute.sh

bash "$DIR/cleanup.sh"
