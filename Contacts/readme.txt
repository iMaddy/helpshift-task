--- Environment setup:
# go to directory contacts
# change JAVA_HOME according to JDK Path. (current settings work for Mac)
$ . env.sh
# build project using MAVEN or BUILD SCRIPT

-------- USING MAVEN BUILD
# Build project using maven: for 1st run maven takes time to download all the libraries in ~/.m2/

--- MAVEN EXAMPLE
$ . env.sh
$ mvn clean install
$ java -cp .:./target/classes com.helpshift.contact.ContactManager

--- BUILD SCRIPT EXAMPLE
$ . env.sh
$ . build.sh
$ java -cp .:./output/:./lib/junit-4.10.jar com.helpshift.test.TestRunner
$ java -cp .:./output/ com.helpshift.contact.ContactManager

-----
# Additional Notes:
# skip unit tests: $ mvn clean install -DskipTests=true
# incremental build: $ mvn install
# run unit test: $ mvn test

