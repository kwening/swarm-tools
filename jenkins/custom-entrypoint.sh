#! /bin/bash -e

echo "EXECUTING custom entrypoint"

echo "--- Copying files at $(date)" >> "$COPY_REFERENCE_FILE_LOG"
find /usr/share/jenkins/cdc/ \( -type f -o -type l \) -exec bash -c '. /usr/local/bin/jenkins-support; for arg; do echo "$arg"; copy_reference_file "$arg"; done' _ {} +

/usr/local/bin/jenkins.sh
