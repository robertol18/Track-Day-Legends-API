# Logging Improvements Summary for Track Day Legends API

## Changes Implemented

### 1. Logging Configuration (Logback)

#### File: `src/main/resources/logback-spring.xml`
- **Profiles**: `dev` (default) and `prd`
- **Development**:
  - Console output with a readable pattern
  - Log files rotated at 10MB and retained for 30 days
  - DEBUG levels for application and web layers
  - SQL queries in DEBUG with parameter bindings in TRACE

- **Production**:
  - File-only output (no console)
  - Asynchronous appender for better performance
  - 50MB compressed rotation retained for 90 days
  - WARN/INFO-focused levels to reduce noise
  - 5GB total cap

### 2. Application Properties

#### Updated: `src/main/resources/application.properties`
```properties
# Disabled noisy show-sql output in favor of structured logging
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=false

# Centralized logging setup
logging.level.root=INFO
logging.level.com.example.trackdaylegends=DEBUG
logging.level.org.springframework.web=DEBUG
logging.level.org.hibernate.SQL=DEBUG
logging.file.name=logs/application.log
```

#### New: `src/main/resources/application-prd.properties`
- Production-optimized configuration
- Reduced levels (WARN/INFO)
- Schema validation instead of auto-update

### 3. Logging Added to Java Classes

#### CarModelController (`adapter/inbound/web/controller/`)
```java
- DEBUG: Method entry with filter parameters
- INFO: Successful operation completion
- Context fields: id, brand, model, year
```

#### GlobalExceptionHandler (`adapter/inbound/web/handler/`)
```java
- WARN: Expected exceptions (EntityNotFoundException, validation)
- ERROR: Unexpected exceptions with stack trace
- Context fields: URI, failing field
```

#### CarModelService (`application/usecase/`)
```java
- TRACE: Input parameters and query details
- DEBUG: Result counts
- INFO: Completed CRUD operations
- WARN: Missing resources
```

### 4. Documentation

#### New: `LOGGING.md`
- Complete logging guide
- Layer-specific logging patterns
- Best practices (parameterization, correct levels)
- Examples for controllers, use cases, and handlers
- Run commands by profile
- Official documentation references

## Improvements Achieved

### Before
- Disorganized output from `spring.jpa.show-sql=true`
- No structured application logging
- Difficult production log analysis
- No consistent logging pattern

### After
**Professional pattern**: `[TIMESTAMP] [LEVEL] [THREAD] [LOGGER] - MESSAGE`
```
2026-08-05 22:45:10.408 [INFO ] [main] [c.e.t.a.usecase.CarModelService] - Creating new car model: brand=Toyota, model=GR Yaris, year=2021
2026-08-05 22:45:10.783 [DEBUG] [main] [org.hibernate.SQL] - insert into car_models (active,body_style,brand,country,...) values (?,?,?,?,?,?,?,?,default)
2026-08-05 22:45:11.146 [INFO ] [main] [c.e.t.a.usecase.CarModelService] - Car model created successfully with id: 1
```

- Structured logging with parameterization and context
- Better performance via async appenders in production
- Automatic retention and rotation management
- Compression of old logs

## Levels by Environment

### Development
- ROOT: INFO
- `com.example.trackdaylegends`: DEBUG
- `org.springframework.web`: DEBUG
- `org.hibernate.SQL`: DEBUG
- `org.hibernate.type.descriptor.sql.BasicBinder`: TRACE

### Production
- ROOT: WARN
- `com.example.trackdaylegends`: INFO
- `org.springframework.web`: INFO
- `org.hibernate`: ERROR

## How to Use

### Run in development
```bash
mvn spring-boot:run
# Or with an explicit profile
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=dev"
```

### Run in production
```bash
java -jar track-day-legends-api-1.0.0.jar --spring.profiles.active=prd
```

### View logs
```bash
# Development (console + file)
tail -f logs/application.log

# Production (file only, compressed)
zcat logs/application-2026-08-05.1.log.gz | head -100
```

## Standards Applied

- **SLF4J**: standard Java logging API
- **Logback**: Spring Boot recommended backend
- **Java 17+ compatibility**: compatible with Java 21
- **Spring Boot 3.3.1+**: native supported configuration
- **12-factor alignment**: stdout/files by environment

## Implementation Checklist

- [x] Create `logback-spring.xml` with dev/prd profiles
- [x] Update `application.properties` (disable show-sql)
- [x] Create optimized `application-prd.properties`
- [x] Add logging to CarModelController
- [x] Add logging to GlobalExceptionHandler
- [x] Add logging to CarModelService
- [x] Create `LOGGING.md`
- [x] Verify compilation (`mvn clean compile`)
- [x] Run tests (`mvn test`) - 4/4 passing
- [x] Validate log output during tests

## Optional Next Improvements

1. **ELK stack**: Elasticsearch + Logstash + Kibana integration
2. **Structured JSON logging**: use `logstash-logback-encoder`
3. **Distributed tracing**: Micrometer/OpenTelemetry integration
4. **Alerts**: send ERROR logs to email/Slack
5. **Cloud integration**: CloudWatch (AWS), Cloud Logging (GCP)

## Support

- See `LOGGING.md` for detailed guidance
- Official docs: https://logback.qos.ch/
- Spring Boot logging docs: https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.logging
