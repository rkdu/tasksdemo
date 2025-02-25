
# Micronaut Task Management API

## Table of Contents
1. [Prerequisites](#Prerequisites)
2. [Installation](#Installation)
3. [API Endpoints](#API)


This RESTful API enables task management with CRUD operations using the 
Micronaut framework and an embedded H2 database.

## Installation and Execution <a name="Prerequisites" />


### Prerequisites
- Java 21
- Micronaut CLI (optional)
- Bruno for test

### Installation <a name="Installation" />

1. **Clone the repository:**
   ```sh
   git clone https://github.com/rkdu/tasksdemo.git
   cd tasksdemo
   ```
2. **Resolve dependencies and start the application:**
   ```sh
   ./gradlew run
   ```

## API Endpoints <a name="API" />

### 1. Retrieve all tasks
   ```sh
   curl --request GET \
  --url http://localhost:8080/tasks
   ```

### 2. Create a new task
   ```sh
curl --request POST \
  --url http://localhost:8080/tasks \
  --header 'content-type: application/json' \
  --data '{
  "title": "Tierarzt Besuch",
  "description": "Katze zur Impfung bringen - 12. Juni 2025 um 11 Uhr",
  "completed": true
}'
   ```

### 3. Retrieve a single task
   ```sh
   curl --request GET \
  --url http://localhost:8080/tasks/1
   ```

### 4. Update a task
   ```sh
 curl --request PUT \
  --url http://localhost:8080/tasks/1 \
  --header 'content-type: application/json' \
  --data '{
  "title": "Tierarzt Besuch",
  "description": "Katze zur Impfung bringen - 12. Juni 2025 um 11 Uhr",
  "completed": true
}'
   ```

### 5. Delete a task
   ```sh
   curl -X DELETE http://localhost:8080/tasks/1
   ```

## Database
The API uses an embedded H2 database to store tasks, which is automatically created on startup.

## Test
The project can be tested with Bruno. Collection is found under src/test/bruno/Tasks.

## OpenAPI

The gradle build will create a swagger documentation under 

Writing OpenAPI file to destination: ./build/classes/java/main/META-INF/swagger/tasks-1.0.0.yml


## Micronaut 4.7.6 Documentation

- [User Guide](https://docs.micronaut.io/4.7.6/guide/index.html)
- [API Reference](https://docs.micronaut.io/4.7.6/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/4.7.6/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
---

- [Micronaut Gradle Plugin documentation](https://micronaut-projects.github.io/micronaut-gradle-plugin/latest/)
- [GraalVM Gradle Plugin documentation](https://graalvm.github.io/native-build-tools/latest/gradle-plugin.html)
- [Shadow Gradle Plugin](https://plugins.gradle.org/plugin/com.github.johnrengelman.shadow)
## Feature serialization-jackson documentation

- [Micronaut Serialization Jackson Core documentation](https://micronaut-projects.github.io/micronaut-serialization/latest/guide/)


## Feature micronaut-aot documentation

- [Micronaut AOT documentation](https://micronaut-projects.github.io/micronaut-aot/latest/guide/)


