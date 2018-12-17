#!/bin/bash
set -euo pipefail

docker stop instrument-craft-shop || true

docker run --rm -d \
  -p 9080:9080 \
  -p 9443:9443 \
  --name instrument-craft-shop \
  --network dkrnet \
  sdaschner/instrument-craft-shop:1
