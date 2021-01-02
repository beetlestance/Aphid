#!/bin/bash

decrypt() {
  PASSPHRASE=$1
  INPUT=$2
  OUTPUT=$3
  gpg --quiet --batch --yes --decrypt --passphrase="$PASSPHRASE" --output "$OUTPUT" "$INPUT"
}

if [[ -n "$ENCRYPT_KEY" ]]; then
  # Decrypt Release key
  decrypt "${ENCRYPT_KEY}" release/aphid-release.gpg release/aphid-release.jks
else
  echo "ENCRYPT_KEY is empty"
fi