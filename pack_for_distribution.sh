#/bin/sh

# Packages all of the dependencies necessary to run the Offline API test server
# and compresses them into a single ZIP archive.

LINUX_JDK="/home/roman/jdk-11.0.3+7"
WINDOWS_JDK="/mnt/c/Program Files/AdoptOpenJDK/jdk-11.0.3.7-hotspot"
JLINK_FLAGS="--strip-debug --compress 0 --no-header-files --no-man-pages"

mkdir ./offline-api-dist
cp 'target/offline-test-api-1.0.0.jar' ./offline-api-dist
cp ./fake_eircode.db ./offline-api-dist
cp ./run_server.bat ./offline-api-dist

$LINUX_JDK/bin/jlink $JLINK_FLAGS --add-modules java.base,java.datatransfer,java.desktop,java.instrument,java.logging,java.management,java.naming,java.scripting,java.sql,java.sql.rowset,java.xml,jdk.unsupported,java.security.jgss --module-path "$WINDOWS_JDK"/jmods/ --output ./offline-api-dist/offline-api-jre

zip -r target/offline-api-dist.zip offline-api-dist/
rm -rf ./offline-api-dist/
