# Group10 - Catan - Backend Development

This module consists of the implentations for server-side development of desktop application of our Catan game.

## Getting Started

### Prerequisites

- JDK 11 or later
- Gradle (as defined in `gradle/wrapper/gradle-wrapper.properties`)

### Build and run:

The application uses Spring profiles for different environments, with configurations located under `src/main/resources/application.properties`.

#### Local Development

To start the application in the local environment, run the following command:

```bash
./gradlew bootRun --args='--spring.profiles.active=dev'
```

#### Production Environment 

To build the application for production, run:

```bash
./gradlew build
```

Then, to execute the application, use:
```bash
java -jar -Dspring.profiles.active=prod build/libs/catan-backend-group10-0.0.1-SNAPSHOT.jar
```

## Project Structure

- `src/main/java/edu/odtu/ceng453/group10/catanbackend`: Main application package.
  - `/config`: Configuration classes (e.g., OpenApiConfig, SMTPConfig).
  - `/controller`: REST controllers like GameScoreController and UserAccountController.
  - `/dto`: Data Transfer Objects for API requests and responses.
  - `/model`: JPA entities representing the data model.
  - `/repository`: Spring Data repositories for data access.
  - `/service`: Service classes with business logic.

## API Documentation

Visit `http://localhost:8080/swagger-ui.html` for the interactive Swagger UI documentation of the API.

## Database Schema

## Testing 

Run unit and database tests with:

```bash
./gradlew test
```

## Authors 
- Meric Kerem Yalçın
- Mustafa Bera Türk