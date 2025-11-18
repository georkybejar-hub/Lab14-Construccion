# Copilot / Agent Instructions for laboratorio-calidad-codigo

Purpose: Help AI code assistants be productive quickly in this repository.

**Project Overview**
- **Type:** Small Java Maven project (single-module).
- **Language/Platform:** Java 11 (see `pom.xml` properties `maven.compiler.source/target`).
- **Top-level files:** `pom.xml`, `src/main/java`, `src/test/java`.

**Build & Test**
- **Build:** `mvn -DskipTests=false package` (runs compiler). Note: Checkstyle runs in the `verify` phase and is configured to `failOnViolation` — keep formatting and style.
- **Run tests:** `mvn test` (tests are JUnit 3 style in `src/test/java` using `junit.framework.*`).
- **Verify (includes static checks):** `mvn verify` (runs Checkstyle plugin configured in `pom.xml`).

**Key Files / Patterns to Inspect**
- `pom.xml` — declares Java 11 and the Checkstyle plugin; don't add features that break existing plugin assumptions.
- `src/main/java/com/tecsup/labs/App.java` — simple CLI entrypoint (`main`) printing to stdout.
- `src/main/java/com/tecsup/labs/UserRegistrationService.java` — main service example: internal state pattern with a `lastErrorMessage` field, boolean-returning `registerUser(...)`, and `saveUser(...)` that throws `IllegalArgumentException` on special input. Contains Spanish identifiers like `contarCaracteres`.
- `src/test/java/com/tecsup/labs/AppTest.java` — JUnit 3-style test harness; tests extend `junit.framework.TestCase`.

**Project-specific conventions & gotchas**
- Tests use legacy JUnit 3 APIs (tests extend `TestCase` and use `suite()` factory). When adding tests, follow that same style unless migrating the whole project.
- Methods commonly return `boolean` and set `lastErrorMessage` instead of throwing broad exceptions. When changing behavior, update callers/tests to read `getLastErrorMessage()` accordingly.
- Some code prints to stdout (e.g., constructors and `main`). Be mindful when writing tests that assert output — tests currently don't assert prints.
- Names may include Spanish verbs (e.g., `contarCaracteres`); keep naming consistent with existing style.

**Style & Static Checks**
- Checkstyle is enabled and will fail the build on violations. Prefer minimal edits that keep existing formatting. Run `mvn verify` locally after changes.

**When editing `UserRegistrationService`**
- Preserve the `lastErrorMessage` stateful pattern or explicitly document if switching to exceptions.
- `registerUser(...)` does input validation (nulls, length, basic email check). Follow similar guard clauses when adding validation.
- `saveUser(...)` may throw `IllegalArgumentException` for reserved inputs (e.g., username `"error"`). Tests should cover both normal and error cases.

**Suggested PR checklist for agents**
- Run `mvn -DskipTests=false test` and `mvn verify` before proposing changes.
- Update or add unit tests matching the JUnit 3 style in `src/test/java`.
- Keep changes small and focused; avoid broad refactors unless requested.

If anything here is unclear or you'd like more detail (for example, migrating to JUnit 5 or adding a new module), tell me which direction to expand and I will update this doc.
