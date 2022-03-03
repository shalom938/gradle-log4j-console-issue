It looks like slf4j and/or log4j-slf4j-impl interfer with gradle's internal logging and console in blackbox testing a jpms module.

## Expected Behavior
when blackbox testing a jpms module and slf4j and/or log4j-slf4j-impl is in the module-path:
'gradle clean build' should not output the unit tests output to the console and it should be recorded in the test-results xml files.

## Current Behavior
the unit tests logs and some gradle internal logging ,and log4j debug logs all go to gradle's console and nothing recorded in the test-results xml files.

## Context
Using log4j2 in java modular application, when one external module coded to use slf4j api, the main app adds a dependency on log4j-slf4j-impl.
in blackbox test suites the unit test stdout should be recorded in test-results xml .
but the gradle console is fluded with gradle internal logging , plus the tested code logging. and nothing is recorded in test-results xml files.

## Steps to Reproduce
I manage to reproduce it with the gradle sample app created by gradle init,
basically the same as gradle sample sample_java_modules_multi_project_with_integration_tests
with the eddition of log4j 2 and slf4j
https://github.com/shalom938/gradle-log4j-console-issue
build scan:
https://scans.gradle.com/s/aavpc4os6nqqk

the sample shows the issue with two examples , when blackbox testing with module-info.java in the test sources, and when using org.javamodularity.moduleplugin.
in both cases its the same outcome.
it looks to me when using one or both of these libraries they interfere with gradle's internal logging in Test tasks.
it can be seen in the build scan console log.

## more details:
module list is a java module that logs to slf4j-api and does not provide an implementation. testImplementation depends on slf4j-simple for logging in unit tests. it applies the org.javamodularity.moduleplugin for blackbox testing.

module list2 is is exactly the same as list but does not use slf4j or any other logging framework.

module utilities is a java module , it requires list, log4j and log4j.slf4j binding.
the test task is whitebox testing , integrationTest task is blackbox testing, it has a module-info.java.

module app is a java module and is the main application module. it applies the
org.javamodularity.moduleplugin so its test task becomes blackbox testing.
it requires utilities, log4j and log4j.slf4j binding.

test source sets in 'utilities' and 'app' have a log4j2-test.xml in resources
with only a console appender so effectively all log4j logging from unit tests go to stdout.

the expected behaviour is that 'gradle clean build' shouldn't print the unit tests
stdout to console, unit tests stdout should be recorded in the test-results xml files.
and classes that use slf4j api should be redirected to log4j2 using log4j-slf4j binding.
the distribution created by the application plugin should include log4j-slf4j binding and logs to slf4j api should be redirected to log4j.

So The generated distribution app works as expected , the issue is only when gradle executes the blackbox test suites.

the log4j-slf4j binding works in unit test but with the blackbox test tasks everything goes to gradle console and nothing is recoreded in the test-result xml files. only system.out really goes to the test-results.

for example:
./gradlew clean list:test
blackbox testing with moduleplugin, uses slf4j and slf4j-simple for unit tests.
some gradle internal logging are printed to the console. slf4j-simple logging
are recorded correctly in the test-results xml.

./gradlew clean list:test -PdisableModulePlugin
same thing but disables moduleplugin , gradle console is clean and everything looks correct.

./gradlew clean list2:test
list2 is the same as list but does NOT use slf4j, its blackbox testing with moduleplugin , everything is as expected.

./gradlew clean :utilities:test
whitebox testing , everything is as expected.

./gradlew clean :utilities:integrationTest
blackbox testing with module-info.java in integrationTest source set.
uses log4j2 plus log4j-slf4j binding.
some internal gradle logging go to the console, the production code logging are correct and log4j-slf4j binding is used correctly but it all goes to gradle's console and not recorded in the test-results xml.

same happens with :app:test and again works correctly with
':app:test -PdisableModulePlugin'

removing log4j-slf4j-impl and slf4j from the dependencies and from the modules requires directives, everything is fine.
(git checkout no-log4j-slf4j-binding)

running the generated app is fine:
./gradlew clean installDist
./app/build/install/app/bin/app

## Your Environment
Operating system of the build machine | Linux 5.16.0-1-amd64 |  
CPU cores | 16 cores |  
Max Gradle workers | 16 workers |  
Java runtime | Debian OpenJDK Runtime Environment 11.0.14+9-post-Debian-1 |  
Java VM | Debian OpenJDK 64-Bit Server VM 11.0.14+9-post-Debian-1 (mixed mode)
Build scan URL:
https://scans.gradle.com/s/aavpc4os6nqqk
