#!/bin/bash
curl -H "X-Vault-Token: $VAULT_TOKEN" -H "Content-Type: application/json" -X DELETE http://127.0.0.1:8200/v1/sys/mounts/secret
curl -H "X-Vault-Token: $VAULT_TOKEN" -H "Content-Type: application/json" -X POST http://127.0.0.1:8200/v1/sys/mounts/secret --data @payload.json 
curl -H "X-Vault-Token: $VAULT_TOKEN" -H "Content-Type: application/json" -X GET http://127.0.0.1:8200/v1/sys/mounts | python -m json.tool

