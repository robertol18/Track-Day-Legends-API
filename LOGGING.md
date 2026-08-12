# Logging in Track Day Legends API

## General Configuration

The application uses **SLF4J** with **Logback** as the default logging backend in Spring Boot.

### Configuration Files

- **logback-spring.xml**: Centralized logging configuration
- **application.properties**: Basic logging properties

## Log Levels

Severity levels (from lowest to highest):

1. **TRACE**: Very detailed debugging information (method parameters, intermediate values)
2. **DEBUG**: Useful debugging information (method flow, executed queries)
3. **INFO**: Important informational messages (completed CRUD operations)
4. **WARN**: Warnings that do not block execution (resources not found)
5. **ERROR**: Errors that block execution (exceptions, failed validations)

## Levels by Component

### Development (profile: `dev` or default)

```properties
ROOT: INFO
com.example.trackdaylegends: DEBUG
org.springframework.web: DEBUG
org.hibernate.SQL: DEBUG
org.hibernate.type.descriptor.sql.BasicBinder: TRACE
```

### Production (profile: `prd`)

```properties
ROOT: WARN
com.example.trackdaylegends: INFO
org.springframework.web: INFO
org.hibernate: ERROR
```

## Logging Pattern

### In Controllers (Web Adapter)

```java
@RestController
public class CarModelController {
    private static final Logger logger = LoggerFactory.getLogger(CarModelController.class);

    @GetMapping
    public ResponseEntity<List<CarModelResponse>> getAllCarModels() {
        logger.debug("Fetching all car models");
        List<CarModel> models = service.getAllCarModels();
        logger.info("Successfully retrieved {} car models", models.size());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CarModelResponse> createCarModel(@RequestBody CarModelRequest request) {
        logger.debug("Creating new car model: brand={}", request.getBrand());
        CarModel saved = service.createCarModel(model);
        logger.info("Car model created successfully with id: {}", saved.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
```

**Recommendations:**
- `DEBUG`: Method entry and parameters
- `INFO`: Successful outcome and generated IDs
- `WARN`: Failed validations and not-found resources

### In Use Cases (Application Layer)

```java
public class CarModelService implements CarModelUseCase {
    private static final Logger logger = LoggerFactory.getLogger(CarModelService.class);

    @Override
    public CarModel createCarModel(CarModel carModel) {
        logger.info("Creating new car model: brand={}, model={}, year={}",
                   carModel.getBrand(), carModel.getModel(), carModel.getYear());
        CarModel saved = repository.save(carModel);
        logger.info("Car model created successfully with id: {}", saved.getId());
        return saved;
    }

    @Override
    public List<CarModel> getAllCarModels(String brand, Integer year, String segment, Boolean active) {
        logger.trace("Querying car models with filters: brand={}, year={}, segment={}, active={}",
                    brand, year, segment, active);
        List<CarModel> result = repository.findAll(brand, year, segment, active);
        logger.debug("Found {} car models matching the filters", result.size());
        return result;
    }
}
```

**Recommendations:**
- `TRACE`: Input parameters and intermediate values
- `DEBUG`: Query results and counts
- `INFO`: Completed operations and important events
- `WARN`: Failed attempts and recoverable issues

### In Exception Handlers

```java
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(
            EntityNotFoundException ex, WebRequest request) {
        logger.warn("Entity not found: {} | Request URI: {}",
                   ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex, WebRequest request) {
        logger.error("Unexpected error occurred | Request URI: {}",
                    request.getDescription(false), ex);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```

**Recommendations:**
- `WARN`: Expected exceptions (not found, validation)
- `ERROR`: Unexpected exceptions with stack trace

### In Persistence Adapters

```java
public class CarModelPersistenceAdapter implements CarModelRepositoryPort {
    private static final Logger logger = LoggerFactory.getLogger(CarModelPersistenceAdapter.class);

    @Override
    public CarModel save(CarModel carModel) {
        logger.trace("Saving car model to database: {}", carModel.getBrand());
        CarModelEntity entity = mapper.toPersistence(carModel);
        CarModelEntity saved = repository.save(entity);
        logger.debug("Car model persisted with id: {}", saved.getId());
        return mapper.toDomain(saved);
    }
}
```

**Recommendations:**
- `TRACE`: Query parameter details and input values
- `DEBUG`: Generated IDs and affected rows

## Logging Best Practices

### 1. Parameterization (never string concatenation)

**Avoid:**
```java
logger.info("User " + userId + " created at " + timestamp);
logger.info("Car model created: " + brand + " " + model);
```

**Prefer:**
```java
logger.info("User created with id: {}", userId);
logger.info("Car model created: brand={}, model={}", brand, model);
```

### 2. Use the right level

```java
logger.trace("Method called with parameters: id={}", id);           // Very detailed
logger.debug("Query returned {} results", count);                   // Flow information
logger.info("Car model created successfully with id: {}", id);      // Important event
logger.warn("Car model not found with id: {}", id);                 // Expected abnormal case
logger.error("Database connection failed", exception);              // Severe error
```

### 3. Add context

```java
logger.info("Updating car model id={} with: brand={}, year={}",
           id, newBrand, newYear);
logger.warn("Failed to delete car model id={}, reason: {}",
           id, ex.getMessage());
```

### 4. Log exceptions correctly

```java
try {
    // code
} catch (Exception ex) {
    logger.error("Failed to process car model", ex);  // Automatic stack trace
}

// Or in an exception handler:
@ExceptionHandler(Exception.class)
public ResponseEntity<?> handle(Exception ex) {
    logger.error("Unexpected error", ex);  // Automatic stack trace
    return ResponseEntity.status(500).build();
}
```

**Avoid:**
```java
logger.error("Error: " + ex.getMessage());  // No stack trace
logger.error("Exception", ex);              // Vague message
```

## Business Logging Patterns

### CRUD operations

```java
// CREATE
logger.info("Car model created: id={}, brand={}, model={}, year={}",
           id, brand, model, year);

// READ
logger.debug("Fetching car model with id: {}", id);

// UPDATE
logger.info("Car model updated: id={}, changes: {}", id, changes);

// DELETE
logger.info("Car model deleted: id={}", id);
```

### Business exceptions

```java
logger.warn("Cannot update car model: id={}, reason: car model not found", id);
logger.warn("Validation failed: brand cannot be null");
logger.warn("Cannot deactivate car model id={}: already inactive", id);
```

### Query operations

```java
logger.trace("Query filters: brand={}, year={}, segment={}", brand, year, segment);
logger.debug("Query returned {} results in {}ms", resultCount, executionTime);
```

## Production Performance

1. **Asynchronous logging**: logs are written through a buffer without blocking request threads.
2. **Log rotation**: files are rotated by size (50MB) and age (90 days).
3. **Compression**: old logs are compressed automatically (`.gz`).
4. **Storage cap**: total log storage is limited to 5GB.

## Running by Profile

### Development (default)
```bash
./mvnw spring-boot:run
# Or
./mvnw spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=dev"
```

### Production
```bash
java -jar track-day-legends-api-1.0.0.jar --spring.profiles.active=prd
```

## Log Location

- **Development**: `logs/application.log` in the current directory
- **Production**: `logs/application.log` with daily rotation

## Changing Levels at Runtime

In development, you can adjust levels without restart (for example, with Spring Boot DevTools):

```properties
# application.properties
logging.level.com.example.trackdaylegends=TRACE
logging.level.org.hibernate.SQL=DEBUG
```

## References

- [SLF4J Documentation](https://www.slf4j.org/)
- [Logback Configuration](https://logback.qos.ch/manual/configuration.html)
- [Spring Boot Logging](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.logging)
- [Best Practices for Java Logging](https://www.baeldung.com/java-logging-best-practices)
