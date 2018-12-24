#!/bin/bash

## CONFIG LOCAL ENV
echo "[*] Config local environment..."

if ! [ -x "$(command -v vault)" ]; then
  echo 'vault is not installed. using "docker-compose exec" version' >&2
  alias vault='docker-compose exec -e VAULT_ADDR=$VAULT_ADDR -e VAULT_TOKEN=$VAULT_TOKEN  vault vault "$@"'
fi

export VAULT_ADDR=http://127.0.0.1:8200
export VAULT_TOKEN=$(grep 'Initial Root Token:' ./_data/keys.txt | awk '{print substr($NF, 1, length($NF))}')

