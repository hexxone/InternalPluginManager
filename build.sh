#!/bin/bash

rm dist/plugins/InternalPluginManager_Dev-Build.jar >> build.log

cd target/classes/ >> build.log
zip -r -u ../../dist/plugins/InternalPluginManager_Dev-Build.jar * >> build.log
cd ../../target/test-classes/ >> build.log
zip -r -u ../../dist/plugins/InternalPluginManager_Dev-Build.jar * >> build.log

screen -S "eclipse-bukkit-debugger" -p 0 -X stuff "`printf "reload\r"`" >> build.log
