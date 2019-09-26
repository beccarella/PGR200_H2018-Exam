# PGR200 Exam, autumn 2018

##### Authors
Rebecca Urquhart(urqreb17) og Tharin Chobkaphol (chotha17)

## About
The program is designed to let a user handle various conferences in a designated database. 
Data is added to, edited or removed from the database accordingly by the user. 

Note: The folder "Commandline" contains code that is not implemented and is not finished. We were unable to finish this in time, but have deliberetly kept it in the final delivery to show the quality of code that was written and the inteded scope of the program.

## How to run the program

In the parent module, run the command "mvn package" to create jar files.
Run the main method in EchoServer to establish a connection to the server, then run the main method in HttpRequest to verify that the connection has indeed been established.

At this point, the connection may be closed, as the rest of the commands will be run locally.

Continue to stay inside the parent module to run the java jar-commands.

##### List of jar command arguments:
1. insert  - creates tables in the db
2. add  - adds a new row in the table
3. update -updatetitle  - updates the title in a row
4. show -topic  - show talks with a specific topic. (available topics: java, HTML & CSS, kotlin)
5. list -talks -topics   - lists talks by name and topic
6. resetdb  - deletes all the tables in the db


#### Testing
Run the "mvn test" command in the terminal to run the tests and check the test coverage


#### Running program example

```bash
> mvn test
[INFO] Scanning for projects...
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Build Order:
[INFO] 
[INFO] PGR200 Mappeinnlevering                                            [pom]
[INFO] database                                                           [jar]
[INFO] http                                                               [jar]
[INFO] commandline                                                        [jar]
[INFO] 
[INFO] ----------< no.kristiania.pgr200:pgr200-innlevering-starter >-----------
[INFO] Building PGR200 Mappeinnlevering 0.0.1-SNAPSHOT                    [1/4]
[INFO] --------------------------------[ pom ]---------------------------------
[INFO] 
[INFO] --- jacoco-maven-plugin:0.8.2:prepare-agent (default) @ pgr200-innlevering-starter ---
[INFO] argLine set to -javaagent:/Users/rebeccaurquhart/.m2/repository/org/jacoco/org.jacoco.agent/0.8.2/org.jacoco.agent-0.8.2-runtime.jar=destfile=/Users/rebeccaurquhart/Desktop/pgr200-eksamen-beccarella/target/jacoco.exec
....
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
....

> mvn package or alternately, mvn install
[INFO] Scanning for projects...
[INFO] ------------------------------------------------------------------------
[INFO] Building conference-server 0.1-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO]
...
[INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ conference-server ---
[INFO] Building jar: e:\Profiles\jbrodwal\workspaces\demo\conference-server\target/conference-server-0.1-SNAPSHOT.jar
[INFO]
[INFO] --- maven-shade-plugin:3.1.1:shade (default) @ conference-server ---
[INFO] Including org.postgresql:postgresql:jar:42.2.2 in the shaded jar.
[INFO] Replacing original artifact with shaded artifact.
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 12.565 s
[INFO] Finished at: 2018-07-08T17:18:12+02:00
[INFO] Final Memory: 21M/211M
[INFO] ------------------------------------------------------------------------


Continue within the parent module
> java -jar database/target/database-0.0.1-SNAPSHOT.jar insert 
> java -jar database/target/database-0.0.1-SNAPSHOT.jar add
> java -jar database/target/database-0.0.1-SNAPSHOT.jar update -updatetitle
> java -jar database/target/database-0.0.1-SNAPSHOT.jar show -topic
> java -jar database/target/database-0.0.1-SNAPSHOT.jar list -talks -topics
> java -jar database/target/database-0.0.1-SNAPSHOT.jar resetdb


alternately, if you are in the db module

> cd database
> java -jar target/database-0.0.1-SNAPSHOT.jar insert 
> java -jar target/database-0.0.1-SNAPSHOT.jar add
> java -jar target/database-0.0.1-SNAPSHOT.jar update -updatetitle
> java -jar target/database-0.0.1-SNAPSHOT.jar show -topic
> java -jar target/database-0.0.1-SNAPSHOT.jar list -talks -topics
> java -jar target/database-0.0.1-SNAPSHOT.jar resetdb
```
