#!/bin/bash

# Example Command: mvn clean buildnumber:create source:jar javadoc:jar compile replacer:replace package bukkit-tester-maven-plugin:bukkit-test wagon:upload deploy

SCRIPT="./maven-build.sh"

# functions
function printHelp {
	echo -e "Usage: $SCRIPT <publish|build>"
	echo -e "\tpublish:\tRequires extra argument (<release|snapshot|nightly>)."
	echo -e "\t\trelease:\tBuilds project, deploys binary, source and JavaDoc jar, uploads JavaDoc. [$SCRIPT publish release]"
	echo -e "\t\tsnapshot:\tAppends '-SNAPSHOT' to projet version, builds project, deploys binary, source and JavaDoc jar. [$SCRIPT publish snapshot]"
	echo -e "\t\tnightly:\tAppends '-NIGHTLY' to project version, build project, deploys binary, source and JavaDoc jar (Does not run tests!). [$SCRIPT publish nightly]"
	echo -e "\tbuild: Builds project with default lifecycle (compiling, replacing, packaging, testing). [$SCRIPT build]"
}

function editVersion {
	mv pom.xml pom.xml~
	head -n 7 pom.xml~ > pom.xml
	sed -ne '8,8p' pom.xml~ | sed -e 's/<\/version>$/-'$1'<\/version>/' >> pom.xml
	cat pom.xml~ | awk 'NR > 8 {print $0}' >> pom.xml
}

function clearVersion {
	rm pom.xml
	mv pom.xml~ pom.xml
}


# main script
case $1 in
	publish)
		case $2 in
			release)
				mvn -P Release clean checkstyle:checkstyle buildnumber:create source:jar javadoc:jar compile replacer:replace package bukkit-tester-maven-plugin:bukkit-test wagon:upload deploy
				;;
			snapshot)
				editVersion "SNAPSHOT"
				mvn -P Snapshot clean buildnumber:create source:jar javadoc:jar compile replacer:replace package bukkit-tester-maven-plugin:bukkit-test deploy
				clearVersion
				;;
			nightly)
				editVersion "NIGHTLY"
				mvn -P Nightly clean buildnumber:create source:jar javadoc:jar compile replacer:replace package deploy
				clearVersion
				;;
			*)
				echo "Unknown Argument."
				printHelp
				;;
		esac
		;;
	build)
		mvn clean checkstyle:checkstyle buildnumber:create compile replacer:replace package bukkit-tester-maven-plugin:bukkit-test
		;;
	*)
		echo -e "Unknown Argument."
		printHelp
		;;
esac
