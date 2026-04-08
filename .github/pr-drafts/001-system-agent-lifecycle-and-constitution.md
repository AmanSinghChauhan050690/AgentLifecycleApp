PR Draft: Agent Lifecycle (feature) + Constitution sync

---

### PR 1 — docs: add constitution v1.0.0 and sync templates

Title
- docs: add constitution v1.0.0 and sync templates

Body
- Summary: Adds the Agent Management Lifecycle System constitution (v1.0.0) derived from the requested microservices principles and updates plan/spec/tasks templates to enforce constitution gates and checks.
- Rationale: Establish project governance and automated gate checks for architecture decisions (REST-only, DB-per-service, Eureka, API Gateway, synchronous clients, fail-fast, no cross-service entity sharing).
- Changes:
  - Constitution: .specify/memory/constitution.md
  - Plan template: .specify/templates/plan-template.md
  - Spec template: .specify/templates/spec-template.md
  - Tasks template: .specify/templates/tasks-template.md
- Important notes / TODO:
  - Ratification date: TODO(RATIFICATION_DATE) — please confirm official adoption date to replace placeholder.
  - This is the initial formal constitution (version 1.0.0). Future governance changes must follow the Versioning rules in the document.
- Validation / Testing steps:
  - Verify the updated templates require the Architecture Compliance and Constitution Check sections in new plans/specs.
  - Open .specify/memory/constitution.md and confirm the principles match project expectations.
  - Run any repository CI that validates templates or pre-merge checks (if present).
- Suggested reviewers:
  - Primary: repo maintainers / architecture owners
  - Optional: service owners for agent-service, publish-service, subscription-service
- Suggested labels: docs, governance, infra
- Suggested merge strategy: squash and merge

---

### PR 2 — feat(specs): add Agent Lifecycle feature spec, plan, and tasks

Title
- feat(specs): add Agent Lifecycle feature spec, plan, and tasks

Body
- Summary: Adds the Agent Lifecycle feature specification, plan, data model, quickstart, contracts, tasks, and a spec quality checklist. Implements soft-delete semantics for agents and defines the core service contracts for `agent-service`, `publish-service`, and `subscription-service`.

- Rationale: Provides a complete, implementation-agnostic design and plan to implement the three core services following the project constitution (REST-based microservices, database-per-service, API Gateway, service discovery). This enables parallel implementation and clear contract-driven development.

- Changes (files added/updated on branch `001-system-agent-lifecycle`):
  - specs/001-system-agent-lifecycle/spec.md
  - specs/001-system-agent-lifecycle/plan.md
  - specs/001-system-agent-lifecycle/tasks.md
  - specs/001-system-agent-lifecycle/data-model.md
  - specs/001-system-agent-lifecycle/research.md
  - specs/001-system-agent-lifecycle/quickstart.md
  - specs/001-system-agent-lifecycle/checklists/requirements.md
  - specs/001-system-agent-lifecycle/contracts/agent-service.md
  - specs/001-system-agent-lifecycle/contracts/publish-service.md
  - specs/001-system-agent-lifecycle/contracts/subscription-service.md

- Important notes / TODO:
  - `DELETE /agents/{id}` is specified as soft-delete (status=deleted). A retention/purge policy can be added later (e.g., timed purge job) if desired.
  - Implementation choices (language/runtime, production DB) are intentionally left unspecified in the spec; these should be chosen per-service during implementation planning.

- Validation / Testing steps:
  - Run contract tests (to be implemented) between `agent-service` and `publish/subscription` in CI.
  - Verify that no cross-service DB access exists and that only IDs are exchanged in inter-service calls.
  - Ensure the Constitution Check in `plan.md` passes after implementation choices are selected.

- Suggested reviewers:
  - Primary: architecture owners and maintainers
  - Optional: intended implementers of `agent-service`, `publish-service`, and `subscription-service`

- Suggested labels: specs, feature, infra
- Suggested merge strategy: squash and merge

---

Notes:
- These two PR drafts are separate by intent (governance vs feature). If you prefer one combined PR, comment and I will produce a single combined title/body and update the branch accordingly.
