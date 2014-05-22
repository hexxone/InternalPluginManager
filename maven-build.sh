#!/bin/bash

#mvn clean source:jar javadoc:jar compile replacer:replace site:jar site:deploy deploy

SCRIPT="$(pwd)/maven.sh"

case $1 in
    deploy)
	echo "Starting Maven BUILD and DEPLOY process in 5 seconds! Last possibility to abort!"
	sleep 5s
	mvn clean buildnumber:create source:jar javadoc:jar compile replacer:replace package bukkit-tester-maven-plugin:bukkit-test deploy
	;;
    package)
	echo "Starting Maven BUILD and PACKAGE process in 5 seconds! Last possibility to abort!"
	sleep 5s
	mvn clean buildnumber:create source:jar javadoc:jar compile replacer:replace package bukkit-tester-maven-plugin:bukkit-test
	;;
    *)
	echo "$SCRIPT <deploy|package>"
	;;
esac
