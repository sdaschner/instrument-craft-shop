#!/bin/bash
set -euo pipefail
cd ${0%/*}

./docker-run-instrument-craft-shop.sh
./docker-run-maker-bot.sh
./docker-run-monitoring.sh
