---
applyTo: "src/main/java/**/adapter/outbound/persistence/**/*.java"
description: "Rules for persistence adapters, JPA entities, repositories, and mappers"
---

# Persistence Adapter Rules

- JPA entities must be separate from domain models.
- Use explicit mappers between persistence entities and domain objects.
- Spring Data repositories must stay inside the persistence adapter.
- Do not leak JPA entities outside this adapter.
- Keep persistence logic focused on storage and retrieval concerns.
- Avoid embedding business rules in JPA entities.
- Keep relationship mapping clear and maintainable.
- Support aggregate reconstruction cleanly from persisted state.