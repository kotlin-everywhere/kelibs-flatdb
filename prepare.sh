#!/bin/sh

echo "rootProject.name='kelibs-flatdb'\ninclude 'kelibs-flatdb-java', 'kelibs-flatdb-js'" > settings.gradle
touch build.gradle
kelibs-flatdb-java/gradlew wrapper
