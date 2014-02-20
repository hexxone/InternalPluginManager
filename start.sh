#!/bin/bash

screen -S "eclipse-bukkit-debugger" -p 0 -X stuff "`printf "stop\r"`"

sleep 2s

screen -A -m -d -S "eclipse-bukkit-debugger" java -jar craftbukkit.jar

gnome-terminal --execute screen -r "eclipse-bukkit-debugger"
