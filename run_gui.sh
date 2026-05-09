#!/usr/bin/env bash
set -euo pipefail

JAVA_FX_LIB="lib/javafx-25.0.1"
BUILD_DIR="build/classes"

mkdir -p "$BUILD_DIR"

javac \
  --module-path "$JAVA_FX_LIB" \
  --add-modules javafx.controls \
  -d "$BUILD_DIR" \
  $(find src/BobsCircus -name '*.java' ! -name '*JUnitTest.java')

if [ -d src/images ]; then
  cp -R src/images "$BUILD_DIR"/
fi

java \
  --module-path "$JAVA_FX_LIB" \
  --add-modules javafx.controls \
  -cp "$BUILD_DIR" \
  BobsCircus.CircusDriverApp_GUI
