# Track Day Legends API 🏁

Welcome to the **Track Day Legends API**! This is an elegant, high-performance, and robust REST API designed for petrolheads, track day enthusiasts, and amateur racing drivers. It manages a technical catalog of iconic sports cars (`CarModel`) and their mechanical variants or specifications (`EngineSpec`).

## 🏗️ Architectural Design

The project is built following strict **Hexagonal Architecture (Ports and Adapters)** and **Domain-Driven Design (DDD)** principles to ensure maximum maintainability, scalability, and testability:

1. **Pure Domain (`domain`)**: Houses the core business entities (`CarModel` and `EngineSpec`) completely decoupled from Spring, JPA, Jackson, or other infrastructure frameworks.
2. **Application (`application`)**: Defines use cases (inbound ports `in` and outbound ports `out`) and implements business logic as pure Java POJOs.
3. **Persistence Adapter (`adapter.outbound.persistence`)**: Interacts with Spring Data JPA over an **H2 in-memory database**, utilizing explicit manual mappers to prevent leaking JPA entities into the domain.
4. **Web Adapter (`adapter.inbound.web`)**: Implements clean REST controllers, declarative request validation DTOs, manual mappers, and a centralized `GlobalExceptionHandler`.
5. **Infrastructure (`infrastructure`)**: Configures manual Spring Beans via `BeanConfiguration` and loads rich, realistic demo data with `DemoDataLoader`.

> **Design Note**: Lombok and MapStruct are intentionally omitted in favor of explicit, standard, and highly readable Java 21 code.

---

## 🗂️ Directory Tree

```text
Track-Day-Legends-API/
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/
    │   │       └── example/
    │   │           └── trackdaylegends/
    │   │               ├── TrackDayLegendsApplication.java
    │   │               ├── domain/
    │   │               │   ├── exception/ (Domain exceptions)
    │   │               │   └── model/ (Pure models: CarModel, EngineSpec)
    │   │               ├── application/
    │   │               │   ├── port/ (Inbound & Outbound ports)
    │   │               │   └── usecase/ (Pure Java service/use case implementations)
    │   │               ├── adapter/
    │   │               │   ├── inbound/web/ (REST controllers, DTOs, mappers, handlers)
    │   │               │   └── outbound/persistence/ (JPA Entities, repositories, adapters)
    │   │               └── infrastructure/
    │   │                   ├── config/ (Manual Spring Bean wiring)
    │   │                   └── bootstrap/ (Initial demo data loader)
    │   └── resources/
    │       └── application.properties
    └── test/ (Unit & Integration tests using JUnit 5 + Mockito)
```

---

## 🚀 How to Run the Project

### Prerequisites:
- **Java 21** or higher installed.
- **Maven 3.8+** installed.

### Build and Run:
1. Navigate to the root directory of the project:
   ```bash
   cd Track-Day-Legends-API
   ```
2. Build the project and run the tests:
   ```bash
   mvn clean test
   ```
3. Run the Spring Boot application:
   ```bash
   mvn spring-boot:run
   ```
4. The server will start on port **`8080`**.
5. Access the **H2 Database Web Console** at:
   - **URL**: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
   - **JDBC URL**: `jdbc:h2:mem:trackdaydb`
   - **Username**: `sa`
   - **Password**: *(leave empty)*

---

## 🔌 API Endpoints

### 🚗 Car Model Management (`CarModel`)

| Method | Endpoint | Description | Optional Filter Parameters |
| :--- | :--- | :--- | :--- |
| **GET** | `/api/car-models` | List all car models | `brand`, `year`, `segment`, `active` |
| **GET** | `/api/car-models/{id}` | Retrieve a model with all its engine specs | |
| **POST** | `/api/car-models` | Create a new car model | *(JSON Payload)* |
| **PUT** | `/api/car-models/{id}` | Update an existing car model | *(JSON Payload)* |
| **PATCH** | `/api/car-models/{id}/deactivate` | Deactivate a car model and its specs | |
| **DELETE** | `/api/car-models/{id}` | Delete a car model | |

### ⚙️ Engine Specification Management (`EngineSpec`)

| Method | Endpoint | Description | Optional Filter Parameters |
| :--- | :--- | :--- | :--- |
| **GET** | `/api/engine-specs` | List all engine specifications | `engineType`, `minHorsepower`, `maxZeroToHundred`, `drivetrain`, `active` |
| **GET** | `/api/engine-specs/{id}` | Retrieve an engine spec by ID | |
| **POST** | `/api/car-models/{carModelId}/engine-specs` | Create an engine spec for a specific car model | *(JSON Payload)* |
| **PUT** | `/api/engine-specs/{id}` | Update an engine specification | *(JSON Payload)* |
| **PATCH** | `/api/engine-specs/{id}/deactivate` | Deactivate an engine specification | |
| **DELETE** | `/api/engine-specs/{id}` | Delete an engine specification | |

### 📈 Metrics and Analytics

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| **GET** | `/api/engine-specs/search/fastest` | Retrieve top 5 fastest engine specs by 0-100 km/h acceleration |
| **GET** | `/api/engine-specs/search/most-powerful` | Retrieve top 5 most powerful engine specs by horsepower |
| **GET** | `/api/stats/summary` | Global database dashboard statistics (totals, averages, fastest 0-100, etc.) |
| **GET** | `/api/health/demo` | Quick API diagnostic health check |

---

## 💡 Practical `curl` Request Examples

### 1. List car models (filtering by brand "Toyota")
```bash
curl -X GET "http://localhost:8080/api/car-models?brand=Toyota" -H "Accept: application/json"
```

### 2. Get a car model with all its associated engine specs (ID 1: Toyota GR Yaris)
```bash
curl -X GET "http://localhost:8080/api/car-models/1" -H "Accept: application/json"
```

### 3. Create a new car model (Alpine A110 R)
```bash
curl -X POST "http://localhost:8080/api/car-models" \
  -H "Content-Type: application/json" \
  -d '{
    "brand": "Alpine",
    "model": "A110 R",
    "year": 2024,
    "segment": "S",
    "bodyStyle": "Coupe",
    "country": "France",
    "description": "The hardcore, 100% track-focused edition of the legendary Alpine A110, extensively utilizing carbon fiber to shed weight."
  }'
```

### 4. Create a performance engine specification for the model above (e.g., model ID 9)
```bash
curl -X POST "http://localhost:8080/api/car-models/9/engine-specs" \
  -H "Content-Type: application/json" \
  -d '{
    "versionName": "A110 R Turini",
    "engineType": "Turbo Petrol",
    "engineConfiguration": "1.8L L4",
    "displacementCc": 1798,
    "horsepowerHp": 300,
    "torqueNm": 340,
    "transmission": "7-speed dual-clutch EDC",
    "drivetrain": "RWD",
    "zeroToHundredSeconds": 3.9,
    "topSpeedKph": 285,
    "fuelConsumptionL100": 7.0
  }'
```

### 5. Retrieve top 5 most powerful engine specs in the database
```bash
curl -X GET "http://localhost:8080/api/engine-specs/search/most-powerful" -H "Accept: application/json"
```

### 6. Get global metrics and statistics
```bash
curl -X GET "http://localhost:8080/api/stats/summary" -H "Accept: application/json"
```

### 7. Trigger validation rules by posting an invalid payload (blank brand, invalid year)
```bash
curl -X POST "http://localhost:8080/api/car-models" \
  -H "Content-Type: application/json" \
  -d '{
    "brand": "",
    "model": "Invalid",
    "year": 1945,
    "segment": "C"
  }'
```
*Expected 400 Bad Request Response:*
```json
{
  "timestamp": "2026-07-07T22:22:38.123456",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation error in request payload",
  "path": "/api/car-models",
  "validationErrors": {
    "brand": "Brand cannot be empty",
    "year": "Year must be at least 1950"
  }
}
```

---

Enjoy burning virtual rubber with the **Track Day Legends API**! 🏎️💨

---

## 🐳 Docker + ☸️ Kubernetes (Helm)

### Build Docker image (multi-stage)
```bash
docker build -t ghcr.io/robertol18/track-day-legends-api:1.0.0 .
```

### Push image (example with GHCR)
```bash
docker push ghcr.io/robertol18/track-day-legends-api:1.0.0
```

### Install/upgrade on Kubernetes with Helm (dev)
```bash
helm upgrade --install track-day-legends ./helm/track-day-legends \
  -f ./helm/track-day-legends/values.yaml \
  -f ./helm/track-day-legends/values-dev.yaml \
  --namespace track-day-legends-dev \
  --create-namespace
```

### Install/upgrade on Kubernetes with Helm (tst)
```bash
helm upgrade --install track-day-legends ./helm/track-day-legends \
  -f ./helm/track-day-legends/values.yaml \
  -f ./helm/track-day-legends/values-tst.yaml \
  --namespace track-day-legends-tst \
  --create-namespace
```

### Security notes
- Do not store real secrets in `values*.yaml`.
- Use `secret.existingSecret` (or External Secrets/Sealed Secrets) in tst.
- Kubernetes probes are mapped to Spring Boot Actuator endpoints:
  - `/actuator/health/liveness`
  - `/actuator/health/readiness`
