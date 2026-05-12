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
- **Spring Security + JJWT 0.12.6** — stateless JWT authentication; `JwtAuthenticationFilter` validates Bearer tokens and sets the `SecurityContext`; `JwtService` handles token generation and validation
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
exceptions/   — AppException subclasses specific to the module
```

**Current modules:**
- `users` — CRUD for users; `Roles` enum for role management. Endpoints: `GET /api/v1/users`, `POST /api/v1/users`
- `workareas` — get-or-create work areas by name. Endpoints: `GET /api/v1/work-areas`, `POST /api/v1/work-areas`
- `auth` — JWT login. Endpoint: `POST /api/v1/auth/login` (public). Returns `{ token }` on success; throws `InvalidCredentialsException` (401) on bad credentials or `ForbiddenRoleException` (403) if the authenticated user does not have the `admin` role.

## Security

`src/main/java/com/semcon/sgu/security/` layout:

```
config/SecurityConfig.java          — SecurityFilterChain, beans for PasswordEncoder,
                                       AuthenticationManager, DaoAuthenticationProvider,
                                       JwtAuthenticationFilter; AuthenticationEntryPoint (401)
                                       and AccessDeniedHandler (403) return JSON ErrorResponse
filter/JwtAuthenticationFilter.java — OncePerRequestFilter; validates Bearer token via JwtService;
                                       catches JwtException and writes 401 JSON directly to response
service/JwtService.java             — generates and validates JWT tokens (JJWT 0.12.6, HS256)
service/UserDetailsServiceImpl.java — loads user by email; maps Roles enum to ROLE_<name> authority
```

**Public paths:** `/v1/auth/**`, `/swagger-ui/**`, `/swagger-ui.html`, `/v3/api-docs/**`, `/webjars/**`  
**Protected paths:** everything else requires `Authorization: Bearer <token>`

**JWT config** (`application.yaml`):
- `jwt.secret` — signing key (must be ≥ 32 chars for HS256)
- `jwt.expiration` — token TTL in milliseconds (default: `86400000` = 24 h)

## Common infrastructure

`src/main/java/com/semcon/sgu/common/`:
- `exception/AppException.java` — abstract base for all domain exceptions; carries `HttpStatus` and `code`
- `exception/ErrorResponse.java` — record `{ status, message, timestamp }` returned by all error responses
- `exception/GlobalExceptionHandler.java` — `@RestControllerAdvice`; handles `AppException` and `AuthenticationException`
- `config/JacksonConfig.java` — `ObjectMapper` bean with `JavaTimeModule` (required to serialize `Instant` in `ErrorResponse`)
