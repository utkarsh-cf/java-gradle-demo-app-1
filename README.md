# java-gradle-demo-app-1

This repository is the Gradle counterpart to
[jenkins-docs/java-gradle-demo-app-1](https://github.com/jenkins-docs/java-gradle-demo-app-1),
for anyone who wants to follow the same "Build a Java app" Jenkins tutorial
using **Gradle** instead of Maven.

The repository contains a simple Java application which outputs the string
`"Hello world!"`, and is accompanied by a couple of unit tests to check that
the main application works as expected. Test results are written out as a
JUnit XML report so Jenkins' `junit` step can pick them up.

The `jenkins/` directory contains an example `Jenkinsfile` (i.e. Pipeline),
and the `jenkins/scripts` subdirectory contains a shell script with the
commands executed when Jenkins processes the "Deliver" stage of the pipeline.

## Project structure

```
simple-java-gradle-app/
├── build.gradle
├── settings.gradle
├── gradlew
├── gradlew.bat
├── gradle/wrapper/
├── src/
│   ├── main/java/com/mycompany/app/App.java
│   └── test/java/com/mycompany/app/AppTest.java
└── jenkins/
    ├── Jenkinsfile
    └── scripts/deliver.sh
```

## Build & test locally

```bash
./gradlew build
```

This compiles the app, runs the unit tests, and produces a runnable jar at
`build/libs/simple-java-gradle-app-1.0-SNAPSHOT.jar`.

Run it directly:

```bash
java -jar build/libs/simple-java-gradle-app-1.0-SNAPSHOT.jar
```

Run just the tests:

```bash
./gradlew test
```

Test results:
- Human-readable HTML report: `build/reports/tests/test/index.html`
- JUnit XML (consumed by Jenkins): `build/test-results/test/*.xml`

## Requirements

- JDK 17+ (the build itself uses a Gradle toolchain pinned to 17)
- No local Gradle install needed — use the bundled wrapper (`./gradlew`)

## Setting up the Jenkins Pipeline

1. Push this repository to your own GitHub account/org.
2. In Jenkins, create a new Pipeline job.
3. Point it at your fork, with the Pipeline script path set to
   `jenkins/Jenkinsfile`.
4. Run the build — Jenkins will use the Docker agent defined in the
   Jenkinsfile (`eclipse-temurin:17-jdk-jammy`), so no JDK/Gradle install is
   needed on the agent itself, only Docker.

## How this differs from the Maven version

| | Maven version | This (Gradle) version |
|---|---|---|
| Build file | `pom.xml` | `build.gradle` + `settings.gradle` |
| Wrapper | `mvnw` | `gradlew` |
| Test runner | Surefire (JUnit 4) | Gradle `test` task (JUnit 5 / Jupiter) |
| Build command | `mvn -B -DskipTests clean package` | `./gradlew clean assemble` |
| Test command | `mvn test` | `./gradlew test` |
| Artifact | `target/*.jar` | `build/libs/*.jar` |

Everything else — the app itself, the pipeline shape (Build → Test → Deliver),
and the intent of the repo — mirrors the Maven version exactly, so the two
can be used side-by-side for comparison.
