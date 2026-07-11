# Instructions Folder

This folder contains project instruction files for GitHub Copilot.

## Structure

- Local project rules:
  - `architecture.instructions.md`
  - `persistence.instructions.md`
  - `web.instructions.md`
  - `testing.instructions.md`
- Reused community rules:
  - `community-java-junit5-assertions.instructions.md`
  - `community-springboot.instructions.md` (scoped to adapters/infrastructure to avoid conflicts with DDD layers)

## Policy

- Prefer community instructions from `github/awesome-copilot` for generic guidance.
- Keep local files limited to Track Day Legends constraints and architecture boundaries.
- Avoid duplicating guidance that already exists in community files.

## Update community files

Use these commands to refresh imported community files:

```powershell
Invoke-WebRequest -Uri https://raw.githubusercontent.com/github/awesome-copilot/main/instructions/java-junit5-assertions.instructions.md -OutFile .github\instructions\community-java-junit5-assertions.instructions.md
Invoke-WebRequest -Uri https://raw.githubusercontent.com/github/awesome-copilot/main/instructions/springboot.instructions.md -OutFile .github\instructions\community-springboot.instructions.md
```
