@echo off

mvn clean buildnumber:create compile replacer:replace package bukkit-tester-maven-plugin:bukkit-test
