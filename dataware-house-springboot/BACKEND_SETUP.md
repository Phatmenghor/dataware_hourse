# Spring Boot Backend Setup Guide

## Overview
Clean, production-ready Spring Boot REST API built with:
- Spring Boot 2.7.6
- Spring Data JPA
- MapStruct for DTO mapping
- PostgreSQL database
- JWT authentication
- Comprehensive exception handling

## Prerequisites
- Java 8+
- Maven 3.6+
- PostgreSQL 12+ (or configured database)

## Quick Start

### 1. Database Configuration
Edit `src/main/resources/application-local.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/dwh_cpb_bi
    username: postgres
    password: your_password
```

Or use environment variables:
```bash
export DB_URL=jdbc:postgresql://localhost:5432/dwh_cpb_bi
export DB_USER=postgres
export DB_PASSWORD=your_password
export JWT_SECRET=your_secret_key
```

### 2. Build Project
```bash
mvn clean install
```

### 3. Run Locally
```bash
# Development (local profile)
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=local"

# Or with Maven Wrapper
./mvnw spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=local"
```

### 4. Verify Server
```bash
curl http://localhost:8081/api/v1/departments
```

## Environment Profiles

### Local Development
```bash
# application-local.yml
spring.profiles.active=local
```
- Full SQL logging
- DDL auto: update
- Debug logging enabled

### Development
```bash
# application-dev.yml
spring.profiles.active=dev
```
- Info logging
- DDL auto: validate
- Dev database

### Production
```bash
# application-prod.yml
spring.profiles.active=prod
```
- Warn logging only
- DDL auto: validate
- Production database

## Configuration Variables

### Application
| Variable | Default | Description |
|----------|---------|-------------|
| `spring.profiles.active` | local | Active profile (local/dev/prod) |
| `server.port` | 8081 | Server port |
| `JWT_SECRET` | cpbank@dwh@bi | JWT signing secret |

### Database
| Variable | Default | Description |
|----------|---------|-------------|
| `DB_URL` | jdbc:postgresql://localhost:5432/dwh_cpb_bi | Database URL |
| `DB_USER` | postgres | Database username |
| `DB_PASSWORD` | - | Database password |

## API Documentation

### Base URL
```
http://localhost:8081/api/v1
```

### Core Endpoints

#### Departments
```
GET    /departments           # Get all
POST   /departments           # Create
GET    /departments/{id}      # Get by ID
PUT    /departments/{id}      # Update
DELETE /departments/{id}      # Delete
```

#### Positions
```
GET    /positions
POST   /positions
GET    /positions/{id}
PUT    /positions/{id}
DELETE /positions/{id}
```

#### Roles
```
GET    /roles
POST   /roles
GET    /roles/{id}
PUT    /roles/{id}
DELETE /roles/{id}
```

#### Users
```
GET    /users                          # Get all
POST   /users                          # Create
GET    /users/{id}                     # Get by ID
GET    /users/username/{username}      # Get by username
PUT    /users/{id}                     # Update
DELETE /users/{id}                     # Delete
POST   /users/{id}/reset-password      # Reset password
POST   /users/profile                  # Get current user profile
```

#### Reports
```
GET    /reports
POST   /reports
GET    /reports/{id}
PUT    /reports/{id}
DELETE /reports/{id}
```

#### Widgets
```
GET    /widgets
POST   /widgets
GET    /widgets/{id}
PUT    /widgets/{id}
DELETE /widgets/{id}
```

#### Units
```
GET    /units
POST   /units
GET    /units/{id}
PUT    /units/{id}
DELETE /units/{id}
```

#### Metadata
```
GET    /metadata
POST   /metadata
GET    /metadata/{id}
DELETE /metadata/{id}
```

## Request/Response Format

### Success Response
```json
{
  "code": 200,
  "message": "Success",
  "success": true,
  "data": { ... }
}
```

### Error Response
```json
{
  "code": 400,
  "message": "Validation failed",
  "success": false,
  "errors": {
    "fieldName": "error message"
  }
}
```

## Authentication

### JWT Token
All protected endpoints require JWT token in header:

```bash
Authorization: Bearer YOUR_JWT_TOKEN
```

### Login
```bash
POST /api/v1/auth/login
Content-Type: application/json

{
  "username": "user",
  "password": "password"
}
```

Response:
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

## Example Requests

### Create Department
```bash
curl -X POST http://localhost:8081/api/v1/departments \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -d '{
    "name": "Finance",
    "shortName": "FIN",
    "code": "DPT001",
    "status": true
  }'
```

### Get All Users
```bash
curl http://localhost:8081/api/v1/users \
  -H "Authorization: Bearer YOUR_TOKEN"
```

### Update User
```bash
curl -X PUT http://localhost:8081/api/v1/users/uuid \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -d '{
    "fullName": "John Doe",
    "status": true
  }'
```

## Logging

### View Logs
```bash
# Tail logs
tail -f logs/application.log

# Check specific level
grep ERROR logs/application.log
grep WARN logs/application.log
```

### Log Configuration
Edit `application.yml`:
```yaml
logging:
  level:
    root: INFO
    cpb.dwh_bi_api: DEBUG
    org.springframework.web: WARN
```

## Database Migrations

### Automatic Schema Generation
Hibernate automatically creates/updates schema based on entities:

```yaml
spring.jpa.hibernate.ddl-auto: update  # Development
spring.jpa.hibernate.ddl-auto: validate  # Production
```

### Manual Migrations (Optional)
For production, consider using Liquibase or Flyway for version control.

## Performance Tuning

### Connection Pool
```yaml
spring.datasource.hikari.maximum-pool-size: 20
spring.datasource.hikari.minimum-idle: 5
```

### Query Optimization
- Add indexes on frequently queried columns
- Use custom query methods in repositories
- Implement lazy loading for relationships

### Caching (Optional)
Add Spring Cache abstraction:
```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-cache</artifactId>
</dependency>
```

## Troubleshooting

### Connection Refused
```
Connection refused: connect
```
- Check PostgreSQL is running
- Verify DB_URL, DB_USER, DB_PASSWORD
- Check firewall allows connections

### Authentication Failed
```
JwtAuthenticationException
```
- Verify JWT_SECRET is correct
- Check token format: `Bearer <token>`
- Verify token is not expired

### Entity Not Found
```
EntityNotFoundException
```
- Check UUID format is correct
- Verify entity exists in database
- Check relationships are properly configured

## Deployment

### JAR Build
```bash
mvn clean package
java -jar target/dwh_bi_api.jar \
  --spring.profiles.active=prod \
  --DB_URL=your_db_url \
  --DB_USER=your_user \
  --DB_PASSWORD=your_password \
  --JWT_SECRET=your_secret
```

### Docker (Optional)
```dockerfile
FROM openjdk:8-jdk-slim
COPY target/dwh_bi_api.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

## Architecture

```
src/main/java/cpb/dwh_bi_api/
├── controllers/          # REST endpoints
├── services/             # Business logic
│   └── impl/             # Service implementations
├── repositories/         # Data access (JPA)
├── entities/             # JPA entities
├── dto/                  # Data transfer objects
│   ├── request/          # Input DTOs
│   └── response/         # Output DTOs
├── mappers/              # MapStruct mappers
├── exceptions/           # Exception handling
├── configs/              # Spring configuration
└── utils/                # Utility classes
```

## Best Practices

✅ Always validate input with `@Valid`
✅ Use DTOs for API responses
✅ Implement proper error handling
✅ Log important operations
✅ Use transactions for multi-step operations
✅ Keep business logic in services
✅ Use repositories for data access
✅ Implement proper authentication/authorization

## Additional Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [MapStruct Documentation](https://mapstruct.org/)
- [PostgreSQL Documentation](https://www.postgresql.org/docs/)
