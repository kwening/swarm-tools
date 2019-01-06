#!/bin/bash
curl -H "X-Vault-Token: $VAULT_TOKEN" -H "Content-Type: application/json" -X DELETE $VAULT_ADDR/v1/sys/mounts/secret
curl -H "X-Vault-Token: $VAULT_TOKEN" -H "Content-Type: application/json" -X POST $VAULT_ADDR/v1/sys/mounts/secret --data @payload.json 
curl -H "X-Vault-Token: $VAULT_TOKEN" -H "Content-Type: application/json" -X GET $VAULT_ADDR/v1/sys/mounts | python -m json.tool

