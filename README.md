# Group10 - Catan - Backend Development

This module consists of the implentations for server-side development of desktop application of our Catan game.

- Detailed documentation of the project can be found on [./documents/Catan Backend.pdf](./documents/Catan%20Backend.pdf)


## Used Libraries
- Kotlin (for cleaner entities)
- Spotless (linter)
- Spring Data JPA
- MariaDB Driver
- H2 In-Memory Database (for tests)
- OpenAPI 3 (for Swagger UI)
- Eclipse Angus Mail

## Getting Started

### Prerequisites

- JDK 21 or later
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

Visit [API Documentation - Swagger UI](https://catan-backend-ds1e.onrender.com/swagger-ui/index.html) for the interactive Swagger UI documentation of the API.

## Database Schema

![ERDiagram](https://github.com/Darkathx/CENG453_20231_Group10_backend/assets/94515749/89c3bd0f-4618-4bb0-b001-663fd16588e3)

- **UserAccount Entity**: Represents the users of the system with attributes including a unique ID, username, password, creation date, and email.

- **GameRecord Entity**: Represents individual game sessions with attributes including a unique ID and the date of play.

- **GameScore Entity**: Represents the scores obtained in game sessions with attributes including a unique game record ID, user account ID, and the score as an integer.

- **UserAccount to GameScore Relationship**: This is a one-to-many relationship, indicating that a single user can have multiple game scores, but each game score is associated with only one user.

- **GameRecord to GameScore Relationship**: This is also a one-to-many relationship, indicating that a single game record can have multiple game scores associated with it, but each game score is related to only one game record.

- **UserAccount to GameRecord Relationship**: This is a many-to-many relationship where users can have multiple game records and each game record can be associated with multiple users. This is because users can play multiple games and obtain scores in each, and a game can be played by multiple users each having their own scores.

### After Multiplayer Support
![cwe](https://github.com/Darkathx/CENG453_20231_Group10_backend/assets/69731004/0b2a8cb6-db3f-45d2-af52-11362e95943a)

## Testing 

Run unit and database tests with:

```bash
./gradlew test
```

## Authors 
- Meric Kerem Yalçın
- Mustafa Bera Türk
