#!/bin/bash
set -euo pipefail

docker stop maker-bot || true

docker run --rm -d \
  --name maker-bot \
  --network dkrnet \
  sdaschner/maker-bot:1
