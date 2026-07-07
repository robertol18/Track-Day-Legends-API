---
applyTo: "src/main/java/**/*.java"
description: "Architecture and DDD rules for Track Day Legends API"
---

# Architecture Rules

- Respect Hexagonal Architecture and DDD boundaries at all times.
- Domain must not depend on Spring, JPA, Jackson, or web concerns.
- Application defines use cases and ports.
- Adapters implement infrastructure and framework concerns.
- All dependencies point inward.
- `CarModel` is the aggregate root.
- `EngineSpec` belongs to `CarModel` and should be modified through application use cases.
- New features should usually start from the use case and port design, then adapter implementation.
- Prefer explicit, small, cohesive classes over generic utility layers.
- Do not bypass the application layer from controllers to repositories.