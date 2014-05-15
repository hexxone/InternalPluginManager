#!/bin/bash

#mvn clean source:jar javadoc:jar compile replacer:replace site:jar site:deploy deploy
mvn clean buildnumber:create source:jar javadoc:jar compile replacer:replace package
