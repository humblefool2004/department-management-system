# Department Management System

A layered REST API for managing college departments, built with Spring Boot. It supports full CRUD, including both **PUT** (full update) and **PATCH** (partial update), with request validation and consistent error responses.

## Tech Stack

- Java 21
- Spring Boot 4.0.6 (Spring Web MVC)
- Spring Data JPA / Hibernate
- MySQL
- MapStruct (entity ↔ DTO mapping)
- Jakarta Bean Validation (plus a custom constraint)
- Lombok
- Spring Boot Actuator
- springdoc-openapi (Swagger UI)
- Maven

## Features

- **Layered architecture:** Controller → Service → Repository
- **DTOs as Java records**, so JPA entities are never exposed through the API
- **MapStruct mappers** for create, full update (PUT) and partial update (PATCH). PATCH ignores null fields, so only the fields sent are changed
- **Validation** with standard annotations (`@NotBlank`, `@Email`, `@Size`, `@Pattern`, `@PositiveOrZero`) and a **custom `@PrimeNumberValidation`** constraint
- **Centralized error handling** with `@RestControllerAdvice`
- **Uniform response wrapper** (`ApiResponse`) for both success and error responses
- **Actuator** health and metrics endpoints, and **SLF4J logging** in the service layer
- **Swagger UI** for interactive API documentation

## API Endpoints

Base path: `/departments`

| Method | Endpoint | Description | Success |
|--------|----------|-------------|---------|
| GET | `/departments` | List all departments | 200 |
| GET | `/departments/{id}` | Get one department | 200 |
| POST | `/departments` | Create a department | 201 |
| PUT | `/departments/{id}` | Replace a department (all required fields) | 200 |
| PATCH | `/departments/{id}` | Update only the fields sent | 200 |
| DELETE | `/departments/{id}` | Delete a department | 204 |

### Validation rules (create / PUT)

| Field | Rule |
|-------|------|
| `departmentCode` | Required, 2–20 characters, unique |
| `departmentName` | Required |
| `contactEmail` | Required, valid email, unique |
| `phoneNumber` | Optional, 10–15 digits, may start with `+` |
| `budget` | Required, zero or positive |
| `description` | Optional, max 500 characters |
| `active` | Required |
| `primeNumber` | Optional, must be a prime number (defaults to 2) |

PATCH accepts the same fields, all optional.

### Error handling

| Situation | Status |
|-----------|--------|
| Validation failed | 400 (with per-field `subErrors`) |
| Malformed JSON or invalid path value | 400 |
| Department not found / unknown URL | 404 |
| Duplicate department code or contact email | 409 |
| Anything unexpected | 500 (generic message, details logged server-side) |

Example error response:

```json
{
  "localDateTime": "2026-09-28 15:22:44",
  "data": null,
  "error": {
    "message": "Input validation failed",
    "status": "BAD_REQUEST",
    "subErrors": {
      "contactEmail": "Contact email must be a valid email address"
    }
  }
}
```

## Running Locally

**Prerequisites:** JDK 21, MySQL running locally.

1. Create the database:
   ```sql
   CREATE DATABASE IF NOT EXISTS departmentdb;
   ```
2. Set your database credentials as environment variables (nothing secret is stored in the repository):
   ```bash
   export DB_USERNAME=your_mysql_user
   export DB_PASSWORD=your_mysql_password
   ```
3. Start the application:
   ```bash
   ./mvnw spring-boot:run
   ```

The app runs on `http://localhost:8080`.

| Tool | URL |
|------|-----|
| Swagger UI | `http://localhost:8080/swagger-ui.html` |
| OpenAPI JSON | `http://localhost:8080/v3/api-docs` |
| Health check | `http://localhost:8080/actuator/health` |
| Metrics | `http://localhost:8080/actuator/metrics` |

## Project Structure

```
src/main/java/.../module02
├── advices/        # GlobalExceptionHandler, response wrapper, ApiResponse / ApiError
├── annotations/    # Custom @PrimeNumberValidation + validator
├── controller/     # REST controller
├── dto/            # Record-based request/response DTOs
├── entities/       # JPA entity
├── exceptions/     # ResourceNotFoundException
├── mapper/         # MapStruct mapper
├── repositories/   # Spring Data JPA repository
└── services/       # Business logic
```

## Possible Improvements

- Pagination and sorting on the list endpoint
- Unit and integration tests
- Authentication and authorization

## Author

Saheel Mohanta — [GitHub](https://github.com/humblefool2004)
