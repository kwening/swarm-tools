# List, create, update, and delete key/value secrets
path "secret/jenkins"
{
  capabilities = ["create", "read", "update", "delete", "list"]
}
