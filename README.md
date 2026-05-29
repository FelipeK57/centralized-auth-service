# Centralized Authentication Service

Servicio centralizado de autenticación y autorización diseñado para sistemas internos empresariales.

El proyecto proporciona autenticación basada en JWT, control de acceso por roles (RBAC) y validación de sistemas mediante API Keys, permitiendo centralizar usuarios, permisos y autenticación en una única plataforma.

---

## Características

* Autenticación basada en JWT
* Refresh Tokens
* Control de acceso basado en roles (RBAC)
* Validación de sistemas mediante API Keys
* Gestión centralizada de usuarios y permisos
* API REST documentada con Swagger/OpenAPI
* Seguridad stateless con Spring Security
* Migraciones versionadas con Flyway
* Persistencia con PostgreSQL
* Arquitectura modular y escalable

---

## Arquitectura

```text
┌─────────────────────┐
│ Internal Systems    │
│ ERP / CRM / Admin   │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│ Central Auth API    │
│ JWT + RBAC + Keys   │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│ PostgreSQL Database │
│ Users / Roles       │
└─────────────────────┘
```

---

## Stack Tecnológico

* Java 21
* Spring Boot
* Spring Security
* PostgreSQL
* Flyway
* Hibernate / JPA
* JWT (JJWT)
* MapStruct
* Swagger / OpenAPI
* Maven
* Docker

---

## Estructura del Proyecto

```text
src/main/java/com/semcon/sgu/
│
├── modules/
│   ├── auth/
│   ├── users/
│   ├── workareas/
│
├── security/
│
├── common/
│
└── config/
```

Cada módulo sigue una arquitectura desacoplada basada en:

* controllers
* services
* repositories
* entities
* dtos
* mappers
* exceptions

---

## Seguridad

La autenticación se realiza mediante JWT utilizando Spring Security.

Características implementadas:

* autenticación stateless
* validación de Bearer Tokens
* control de acceso por roles
* manejo centralizado de errores
* passwords hasheadas
* endpoints protegidos mediante filtros JWT

---

## Endpoints Principales

### Auth

```http
POST /api/v1/auth/login
```

### Usuarios

```http
GET /api/v1/users
POST /api/v1/users
```

### Áreas de trabajo

```http
GET /api/v1/work-areas
POST /api/v1/work-areas
```

---

## Ejecutar el Proyecto

### Requisitos

* Java 21
* PostgreSQL
* Maven

### Build

```bash
./mvnw clean package
```

### Ejecutar

```bash
./mvnw spring-boot:run
```

### Tests

```bash
./mvnw test
```

---

## Swagger

La documentación de la API está disponible en:

```text
/api/swagger-ui.html
```

---

## Objetivo del Proyecto

Este proyecto fue desarrollado como una solución backend para centralizar autenticación y autorización en entornos con múltiples sistemas internos, aplicando buenas prácticas de seguridad, arquitectura modular y diseño de APIs REST.
