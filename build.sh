#!/bin/bash

rm dist/plugins/InternalPluginManager_Dev-Build.jar > /dev/null

cd target/classes/ > /dev/null
zip -r -u ../../dist/plugins/InternalPluginManager_Dev-Build.jar * > /dev/null
cd ../test-classes/ > /dev/null
zip -r -u ../../dist/plugins/InternalPluginManager_Dev-Build.jar * > /dev/null

screen -S "eclipse-bukkit-debugger" -p 0 -X stuff "`printf "reload\r"`" > /dev/null
