# AGENTS.md

Track Day Legends API is a Spring Boot 3 backend that implements Hexagonal Architecture and DDD for a track-focused car catalog.

<!-- codebase-memory-mcp:start -->
# Codebase Knowledge Graph (codebase-memory-mcp)

This project uses codebase-memory-mcp from `http://localhost:9749` to maintain a knowledge graph of the codebase.
ALWAYS prefer MCP graph tools over grep/glob/file-search for code discovery.

## Priority Order
1. `search_graph` — find functions, classes, routes, variables by pattern
2. `trace_path` — trace who calls a function or what it calls
3. `get_code_snippet` — read specific function/class source code
4. `query_graph` — run Cypher queries for complex patterns
5. `get_architecture` — high-level project summary

## When to fall back to grep/glob
- Searching for string literals, error messages, config values
- Searching non-code files (Dockerfiles, shell scripts, configs)
- When MCP tools return insufficient results

## Examples
- Find a handler: `search_graph(name_pattern=".*OrderHandler.*")`
- Who calls it: `trace_path(function_name="OrderHandler", direction="inbound")`
- Read source: `get_code_snippet(qualified_name="pkg/orders.OrderHandler")`
<!-- codebase-memory-mcp:end -->

## Build and run

Use Maven Wrapper when available:

```bash
./mvnw clean test
./mvnw spring-boot:run
```

If the wrapper is not available:

```bash
mvn clean test
mvn spring-boot:run
```

## Project focus

- Core aggregate: `CarModel`
- Child concept: `EngineSpec`
- Domain must remain framework-free
- Application layer defines use cases and ports
- Adapters implement web and persistence
- Wiring is explicit in infrastructure configuration

## Main architectural rules

- Do not add Spring annotations to domain or application classes.
- Do not expose JPA entities outside the persistence adapter.
- Do not bypass use cases from controllers directly to repositories.
- Do not move domain rules into controllers, repositories, or JPA entities.
- Keep manual wiring updated when new use cases or adapters are added.

## Preferred implementation style

- Use constructor injection.
- Use explicit mapping.
- Prefer small, intention-revealing classes and methods.
- Use domain language consistently.
- Keep changes minimal but coherent across layers.

## Testing expectations

- Domain: JUnit 5
- Application: Mockito
- Web adapter: `@WebMvcTest`
- Persistence adapter: `@DataJpaTest`

## Copilot customization layout

- Global repository instructions live in `.github/copilot-instructions.md`.
- Task and file-pattern instructions live in `.github/instructions/*.instructions.md`.
- Reusable workflows live in `.github/skills/<skill-name>/SKILL.md`.
- Prefer reusing vetted community instructions from `github/awesome-copilot` when they match project needs.
- Keep local instruction files focused on Track Day Legends constraints (Hexagonal Architecture, DDD boundaries, package conventions).
- Avoid duplicating generic guidance already covered by imported community instruction files.
- Commit workflow skill: `.github/skills/conventional-commits/SKILL.md`.

## Avoid by default

- Lombok
- MapStruct
- QueryDSL
- Specifications
- Authentication and authorization
- Messaging, microservices, or external integrations

## Definition of done

A change is done when:
- architecture boundaries are respected
- code compiles
- affected tests are updated
- wiring is complete
- REST contracts stay coherent
- documentation/examples are updated when behavior changes