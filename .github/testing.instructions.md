---
applyTo: "src/test/java/**/*.java"
description: "Testing strategy for Track Day Legends API"
---

# Testing Rules

- Domain tests use pure JUnit 5 with no Spring context.
- Application tests use Mockito to mock outbound ports.
- Web adapter tests use `@WebMvcTest`.
- Persistence adapter tests use `@DataJpaTest`.
- Load the full Spring context only when truly necessary.
- Test business invariants in the domain first.
- Test orchestration in the application layer separately from adapters.
- Keep tests readable and focused on one behavior at a time.