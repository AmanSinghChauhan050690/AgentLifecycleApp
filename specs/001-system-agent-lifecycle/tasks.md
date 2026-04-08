---

description: "Task list for Agent Lifecycle feature"
---

# Tasks: Agent Lifecycle (Microservices)

**Input**: `spec.md`, `plan.md`
**Prerequisites**: plan.md, spec.md

## Phase 1: Setup (Shared Infrastructure)

- [ ] T001 Create repository layout: backend/agent-service, backend/publish-service, backend/subscription-service
- [ ] T002 Initialize language projects and dependency manifests for each service
- [ ] T003 Configure linting, formatting, and CI skeleton

---

## Phase 2: Foundational (Blocking Prerequisites)

- [ ] T004 Setup service discovery (Eureka server) and document access
- [ ] T005 Deploy and configure API Gateway as single external entry point
- [ ] T006 Configure per-service datastores (H2 for baseline) and connection configs
- [ ] T007 Add contract-test harness for public REST APIs
- [ ] T008 Provide HTTP client boilerplate with sensible timeouts and circuit-breaker patterns
- [ ] T009 Add fail-fast error handling templates and structured error types

**Checkpoint**: Foundation ready - user story implementation can now begin in parallel

---

## Phase 3: User Story 1 - Manage Agents (Priority: P1) 🎯 MVP

- [ ] T010 [US1] Create `agent-service` project skeleton in `backend/agent-service`
- [ ] T011 [US1] Implement data model for `Agent` in `agent-service`
- [ ] T012 [US1] Implement POST `/agents` with validation and persistence
- [ ] T013 [US1] Implement GET `/agents/{id}` and GET `/agents` (pagination)
- [ ] T014 [US1] Implement PUT `/agents/{id}` to update agent attributes
- [ ] T015 [US1] Implement DELETE `/agents/{id}` as soft-delete (set `status=deleted`)
- [ ] T016 [US1] Add unit tests and contract tests for `agent-service` endpoints

**Checkpoint**: Agent-service MVP complete

---

## Phase 4: User Story 2 - Publish Management (Priority: P2)

- [ ] T020 [US2] Create `publish-service` project skeleton
- [ ] T021 [US2] Implement publish data model and persistence
- [ ] T022 [US2] Implement POST `/publish/{agentId}` which validates agent via `agent-service` and records publish
- [ ] T023 [US2] Implement POST `/unpublish/{agentId}`
- [ ] T024 [US2] Implement GET `/published`
- [ ] T025 [US2] Add contract tests verifying agent validation and publish lifecycle

**Checkpoint**: Publish-service complete

---

## Phase 5: User Story 3 - Subscriptions (Priority: P3)

- [ ] T030 [US3] Create `subscription-service` project skeleton
- [ ] T031 [US3] Implement subscription data model and persistence
- [ ] T032 [US3] Implement POST `/subscribe` and POST `/unsubscribe` (validate agent via `agent-service`)
- [ ] T033 [US3] Implement GET `/subscriptions`
- [ ] T034 [US3] Add contract and integration tests for subscribe/unsubscribe

**Checkpoint**: Subscription-service complete

---

## Phase N: Polish & Cross-Cutting Concerns

- [ ] T040 Documentation updates in `docs/`
- [ ] T041 Add integration tests and run in CI
- [ ] T042 Security hardening and auth integration at API Gateway
- [ ] T043 Implement purge job for soft-deleted agents (if retention policy chosen)

*** End File"}