# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

```bash
# Build
./mvnw clean package

# Run the application
./mvnw spring-boot:run

# Run all tests
./mvnw test

# Run a single test class
./mvnw test -Dtest=ClassName

# Run a single test method
./mvnw test -Dtest=ClassName#methodName
```

## Architecture

Spring Boot 4.0.6 REST API (Java 21) backed by PostgreSQL. The base package is `com.semcon.sgu`. All endpoints are served under the `/api` context path.

**Key stack:**
- **Spring Data JPA + Hibernate** — database access; `ddl-auto: validate` means Hibernate never mutates the schema
- **Flyway** — owns all schema changes; migrations live in `src/main/resources/db/migration`
- **Spring Security + JJWT 0.12.6** — stateless JWT authentication via `JwtAuthenticationFilter`; token validation is not yet implemented (TODO in filter)
- **Bean Validation** — request body validation via `spring-boot-starter-validation`
- **MapStruct 1.6.3** — entity↔DTO mapping; annotation processor wired alongside Lombok in Maven
- **springdoc-openapi 3.0.2** — Swagger UI served at `/api/swagger-ui.html`
- **Lombok** — reduces boilerplate; annotation processing is wired in Maven for both compile and test phases

**Database:** PostgreSQL on `localhost:5432/sgu` (credentials in `application.yaml`; override via environment variables for non-local environments).

**Schema management:** Always add schema changes as new Flyway migration files — never alter existing ones. The naming convention is `V{version}__{description}.sql`.

## Module structure

Each feature module lives under `src/main/java/com/semcon/sgu/modules/<module>/` and follows this layout:

```
controller/   — @RestController, request/response mapping
dtos/         — CreateXxxDto (input) and XxxDto (output)
entity/       — @Entity classes
mappers/      — MapStruct @Mapper interfaces
repository/   — Spring Data JPA repositories
services/     — business logic
```

**Current modules:**
- `users` — CRUD for users; `Roles` enum for role management. Endpoints: `GET /api/v1/users`, `POST /api/v1/users`
- `workareas` — get-or-create work areas by name. Endpoints: `GET /api/v1/work-areas`, `POST /api/v1/work-areas`

**Security:** `src/main/java/com/semcon/sgu/security/` contains `SecurityConfig` (stateless, CSRF disabled, all routes currently public) and `JwtAuthenticationFilter` (extracts Bearer token; validation not yet implemented).