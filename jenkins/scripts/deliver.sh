#!/usr/bin/env bash
set -euo pipefail

JAR_DIR="build/libs"
JAR_FILE=$(find "$JAR_DIR" -maxdepth 1 -name "*.jar" | head -n 1)

if [[ -z "$JAR_FILE" ]]; then
    echo "No jar file found in $JAR_DIR"
    exit 1
fi

echo "Running $JAR_FILE"
java -jar "$JAR_FILE"

echo "The application ran successfully"
