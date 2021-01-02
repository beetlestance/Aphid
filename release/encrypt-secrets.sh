#!/bin/bash

encrypt() {
  PASSPHRASE=$1
  INPUT=$2
  OUTPUT=$3
  gpg --batch --yes --passphrase="$PASSPHRASE" --cipher-algo AES256 --symmetric --output "$OUTPUT" "$INPUT"
}

if [[ -n "$ENCRYPT_KEY" ]]; then
  # Encrypt Release key
  encrypt "${ENCRYPT_KEY}" release/aphid-release.jks release/aphid-release.gpg
else
  echo "ENCRYPT_KEY is empty"
fi
