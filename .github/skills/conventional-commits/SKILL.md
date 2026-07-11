---
name: conventional-commits
description: "Use when preparing Git commits. Generates and validates Conventional Commits messages, proposes commit scope from changed files, suggests body/footer, and runs a safe commit workflow with user confirmation before commit."
---

# Conventional Commits Skill

Use this skill whenever the task includes creating or polishing commit messages.

## Goals

- Produce commit messages compliant with Conventional Commits.
- Keep messages aligned with the actual code changes.
- Ask for confirmation before creating the commit.

## Allowed types

- feat: new user-visible functionality
- fix: bug fix
- refactor: internal code change without behavior change
- test: test additions or refactors
- docs: documentation-only changes
- chore: maintenance, build, or tooling tasks
- perf: performance improvements
- ci: CI/CD workflow changes
- build: build system or dependency changes

## Message format

- Header: `<type>(<scope>): <short summary>`
- Body: explain what changed and why (optional but recommended)
- Footer: use for breaking changes or issue references

Examples:

- `feat(api): add filters for car model listing`
- `fix(persistence): prevent null drivetrain mapping`
- `refactor(application): split engine spec update use case`
- `test(web): cover validation errors in create endpoint`

Breaking change example:

- Header: `feat(api)!: rename engine-spec endpoint`
- Footer: `BREAKING CHANGE: /api/engine-specs/{id} now requires modelId query param`

## Workflow

1. Inspect staged and unstaged changes.
2. Suggest 1-3 candidate commit messages with rationale.
3. Ask user to pick one or edit it.
4. If requested, stage files intentionally (`git add` with explicit paths).
5. Commit only after explicit user confirmation.
6. Show a short post-commit summary (hash, message, files).

## Validation checklist

- Type matches the nature of the change.
- Scope is specific and concise.
- Subject uses imperative mood and is under 72 chars.
- Body explains reason, not only implementation details.
- Footer is included when required.
- Commit content and message are consistent.

## Repository overlay

For this repository, prefer these scopes when applicable:

- domain
- application
- web
- persistence
- config
- tests
- docs
