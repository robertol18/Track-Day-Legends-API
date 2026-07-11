# Track Day Legends API Project Instructions

This repository follows **Hexagonal Architecture (Ports and Adapters)** and **Domain-Driven Design (DDD)** principles using **Java 21** and **Spring Boot 3**.

The application models track-focused cars and their technical variants through two core concepts:
- `CarModel`: the aggregate root for the vehicle model
- `EngineSpec`: a technical variant associated with a `CarModel`

The goal is to keep the domain expressive, the application layer framework-agnostic, and the adapters isolated from business rules.

## Instruction Organization

- Keep global project guidance in this file.
- Keep file-pattern and task-specific guidance in `.github/instructions/*.instructions.md`.
- Keep reusable task workflows in `.github/skills/<skill-name>/SKILL.md`.
- Reuse community-maintained instruction files from `github/awesome-copilot` whenever they fit the use case.
- Add local overlays only for repository-specific constraints that are not covered by community files.

## Architectural Principles

- **Dependency Inversion**: all dependencies must point inward toward the Domain layer.
- **Domain Purity**: the `domain` package must not depend on Spring, JPA, Jackson, or any infrastructure concern.
- **Application Layer**: contains use cases, orchestration logic, and port coordination. Complex business rules belong in the Domain.
- **Ports & Adapters**:
  - **Inbound ports** define the use cases the application exposes.
  - **Outbound ports** define the dependencies required by the application, such as persistence.
  - **Adapters** implement web, persistence, and configuration concerns.

## Package Structure

Use a structure similar to:

- `com.example.trackdaylegends.domain`
- `com.example.trackdaylegends.application`
- `com.example.trackdaylegends.adapter.inbound.web`
- `com.example.trackdaylegends.adapter.outbound.persistence`
- `com.example.trackdaylegends.infrastructure.config`

Suggested internal organization:

- `domain.model`
- `domain.exception`
- `domain.valueobject` if needed
- `application.port.in`
- `application.port.out`
- `application.usecase`
- `adapter.inbound.web.controller`
- `adapter.inbound.web.dto`
- `adapter.inbound.web.handler`
- `adapter.outbound.persistence.entity`
- `adapter.outbound.persistence.mapper`
- `adapter.outbound.persistence.repository`

## Domain Modeling Rules

### Domain Layer (`com.example.trackdaylegends.domain`)
- Keep domain models free of framework annotations.
- Prefer immutable modeling using `record` or final classes where practical.
- Aggregates must enforce their own invariants.
- `CarModel` should be treated as the aggregate root.
- `EngineSpec` belongs to a `CarModel` and must not violate aggregate consistency.
- Use factory methods such as `create()` for new instances and `reconstruct()` for restoring persisted state when needed.
- Use ubiquitous language based on the automotive domain: `car model`, `engine spec`, `track`, `horsepower`, `drivetrain`, `zero-to-hundred`.

## Application Layer Rules

### Application Layer (`com.example.trackdaylegends.application`)
- Do NOT use `@Service`, `@Component`, or other Spring stereotypes in this layer.
- Implement use cases as plain Java classes.
- Orchestrate domain objects and outbound ports.
- Keep the application layer free from framework and persistence details.
- Use command/query objects at the input port boundary when helpful for clarity.

Typical use cases may include:
- create car model
- update car model
- deactivate car model
- list car models with filters
- create engine spec for car model
- update engine spec
- deactivate engine spec
- list engine specs with filters
- compute summary stats

## Adapter Rules

### Inbound Web Adapter (`adapter.inbound.web`)
- Controllers are thin and delegate to input ports or use cases.
- Controllers must not contain business rules.
- Expose JSON REST endpoints only.
- Validate request payloads at the web boundary.
- Map domain/application exceptions through a centralized `GlobalExceptionHandler`.

### Outbound Persistence Adapter (`adapter.outbound.persistence`)
- Keep JPA entities separate from domain models.
- Provide explicit mappers between persistence entities and domain models.
- Repositories in this adapter may use Spring Data JPA.
- Do not leak JPA entities outside the persistence adapter.
- Keep persistence logic focused on storage concerns, not business decisions.

## Wiring Rules

### Infrastructure (`infrastructure.config`)
- Use manual wiring in `BeanConfiguration.java` to connect use cases, ports, and adapters.
- Spring annotations such as `@Configuration` and `@Bean` belong here, not in the domain or application layers.
- Favor explicit bean definitions over implicit component scanning for application services.

## API and DTO Conventions

- The API base path must start with `/api`.
- Main resources are:
  - `/api/car-models`
  - `/api/engine-specs`
- Use DTOs for all inbound and outbound web payloads.
- Keep web DTOs in the inbound adapter, not in the domain.
- Use clear names such as:
  - `CreateCarModelRequest`
  - `UpdateCarModelRequest`
  - `CarModelResponse`
  - `CreateEngineSpecRequest`
  - `UpdateEngineSpecRequest`
  - `EngineSpecResponse`
  - `StatsResponse`

## Persistence and Reconstruction

- Persistence must support the aggregate root `CarModel` and its associated `EngineSpec` variants.
- Rebuild domain objects from persistence using mapper logic or `reconstruct()` methods where appropriate.
- Avoid pushing persistence annotations or concerns into the domain model.
- Keep bidirectional JPA relationships internal to the persistence adapter if they simplify mapping, but do not expose them beyond that boundary.

## Error Handling

- Use a centralized `GlobalExceptionHandler` in the inbound web adapter.
- Map domain exceptions and validation errors to appropriate HTTP status codes.
- Use consistent JSON error responses.
- Common status codes:
  - `400 Bad Request`
  - `404 Not Found`
  - `409 Conflict` when appropriate
  - `500 Internal Server Error` only for unexpected failures

## Testing Strategy

- **Domain Tests**: pure JUnit 5, no Spring context.
- **Application Tests**: use Mockito to mock outbound ports.
- **Web Adapter Tests**: use `@WebMvcTest` for controller and handler behavior.
- **Persistence Adapter Tests**: use `@DataJpaTest` for repository and mapping verification.
- Avoid loading the full Spring context unless the test truly requires it.

## Coding Standards

- Use constructor injection only.
- Prefer explicit code over hidden framework magic.
- Keep methods small and intention-revealing.
- Use meaningful names from the domain.
- Do not use Lombok unless explicitly requested.
- Do not expose persistence entities in controllers.
- Do not place business rules in controllers or JPA entities.

## Common Patterns

- **Aggregate Reconstruction**: use `reconstruct()` or explicit mapper restoration for persisted aggregates.
- **Manual Mapping**: maintain separate mappers for web DTOs and persistence entities.
- **Use Case First**: new features should usually start by defining or updating an input port/use case before wiring adapters.
- **Validation at the Edge**: request validation belongs at the inbound adapter boundary.

## Non-Goals

- Do not add authentication or authorization unless explicitly requested.
- Do not introduce microservices, messaging, or external integrations unless explicitly requested.
- Do not collapse the architecture into an anemic CRUD service.
- Do not move domain logic into controllers, repositories, or entities.

## Definition of Done

A change is complete when:
- the domain model remains framework-free
- application use cases are explicit and testable
- adapters remain isolated
- manual wiring is updated if needed
- tests cover the affected layer
- REST contracts remain consistent
- documentation or examples are updated when API behavior changes