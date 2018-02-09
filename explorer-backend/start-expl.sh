#!/usr/bin/env bash
java -Xms1024m -Xmx2048m -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8085,suspend=n -jar ./build/libs/explorer-0.0.1-SNAPSHOT.jar --spring.profiles.active=test --spring.datasource.initialize=false  >console.log 2>&1 &
