---
applyTo: "src/main/java/**/adapter/inbound/web/**/*.java"
description: "Rules for REST controllers, web DTOs, and exception handling"
---

# Web Adapter Rules

- Controllers must be thin and delegate to input ports or use cases.
- Do not place business rules in controllers.
- Validate request DTOs at the web boundary.
- Return response DTOs, never JPA entities.
- Keep endpoint naming RESTful and predictable.
- Use `/api/car-models` and `/api/engine-specs` as the primary resource paths.
- Use `GET`, `POST`, `PUT`, `PATCH`, and `DELETE` consistently.
- Map exceptions centrally in `GlobalExceptionHandler`.
- Return consistent JSON error payloads.
- Use `ResponseEntity` when it improves status-code clarity.