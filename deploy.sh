#! /bin/sh
mvn clean install -DskipTests
cp ./target/qamission-web-example.jar ./bin/

