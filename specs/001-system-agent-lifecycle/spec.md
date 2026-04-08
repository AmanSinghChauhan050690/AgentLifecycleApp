# Feature Specification: Agent Lifecycle (Microservices)

**Feature Branch**: `001-system-agent-lifecycle`  
**Created**: 2026-04-08  
**Status**: Draft  
**Input**: SYSTEM: Agent Lifecycle (Microservices) — services, entities, and API contracts provided by product owner

## User Scenarios & Testing *(mandatory)*

### User Story 1 - Manage Agents (Priority: P1)

An operator can create, update, activate/deactivate, retrieve, and list agents via the `agent-service` API.

**Why this priority**: Core domain capability — other services (publish/subscription) depend on agent existence and status.

**Independent Test**: Use the `agent-service` REST API to create an agent, assert HTTP 201 and that subsequent GET returns the same agent with correct attributes.

**Acceptance Scenarios**:

1. **Given** a valid create request, **When** POST `/agents`, **Then** return `201 Created` with `id`, `name`, `status`, and `createdAt` populated.
2. **Given** an existing agent, **When** PUT `/agents/{id}` with valid updates, **Then** return `200 OK` and subsequent GET shows updated fields.
3. **Given** an existing agent, **When** POST `/agents/{id}/activate` or `/agents/{id}/deactivate` (or equivalent), **Then** agent `status` toggles accordingly and downstream services see the updated status via GET `/agents/{id}`.
4. **Given** many agents, **When** GET `/agents`, **Then** return paginated list with basic attributes.

---

### User Story 2 - Publish Management (Priority: P2)

A publisher can publish or unpublish an agent via `publish-service`. `publish-service` MUST validate agent existence/status by calling `agent-service`.

**Why this priority**: Publishes expose agents to consumers; required after agent creation.

**Independent Test**: Call POST `/publish/{agentId}` for a valid agent; expect `200 OK` and a record in the published list returned by GET `/published`.

**Acceptance Scenarios**:

1. **Given** an active agent, **When** POST `/publish/{agentId}`, **Then** return `200 OK` and the publish record contains `agentId`, `status=published`, and `publishedAt`.
2. **Given** a published agent, **When** POST `/unpublish/{agentId}`, **Then** return `200 OK` and the publish record status becomes `unpublished` or is removed from GET `/published`.
3. **Given** an invalid or non-existent `agentId`, **When** POST `/publish/{agentId}`, **Then** return `404 Not Found` (agent validation failed).

---

### User Story 3 - Subscriptions (Priority: P3)

Users may subscribe or unsubscribe to published agents via `subscription-service`. `subscription-service` MUST validate agent existence via `agent-service`.

**Why this priority**: Subscription functionality provides end-user value after publishing.

**Independent Test**: POST `/subscribe` with `{agentId,userId}` and verify `200 OK` and a new subscription entry in GET `/subscriptions`.

**Acceptance Scenarios**:

1. **Given** a published agent, **When** POST `/subscribe` with valid `userId`, **Then** return `201 Created` and subscription record with `status=active`.
2. **Given** an active subscription, **When** POST `/unsubscribe` with the same `userId` and `agentId`, **Then** return `200 OK` and subscription status becomes `inactive` or record removed.
3. **Given** a non-existent agent, **When** POST `/subscribe`, **Then** return `404 Not Found`.

### Edge Cases

- Subscribing to an unpublished or deactivated agent → must fail with `409 Conflict` or `404` depending on policy.
- Race: concurrent publish + subscribe → publish must be authoritative; subscription must validate current publish status.
-- Agent deletion semantics (soft vs hard) affects publish and subscription lifecycles. Decision: **Soft-delete** — `DELETE /agents/{id}` sets `status=deleted` and retains the record for audit. Published and subscription records remain but must respect the `deleted` status (subscriptions rejected, publishes ignored). A background purge job may be added later via a migration plan if retention policy requires data removal.

## Requirements *(mandatory)*

### Functional Requirements

- **FR-001**: `agent-service` MUST expose CRUD endpoints: POST `/agents`, GET `/agents/{id}`, GET `/agents`, PUT `/agents/{id}`, DELETE `/agents/{id}`.
- **FR-002**: `publish-service` MUST expose POST `/publish/{agentId}`, POST `/unpublish/{agentId}`, and GET `/published` and MUST validate agent existence by calling `agent-service`.
- **FR-003**: `subscription-service` MUST expose POST `/subscribe`, POST `/unsubscribe`, and GET `/subscriptions` and MUST validate agent existence via `agent-service`.
- **FR-004**: Services MUST exchange only identifiers (e.g., `agentId`) — no full entity objects in inter-service calls.
- **FR-005**: Inter-service communication MUST be synchronous HTTP/REST calls using a client with sensible timeouts and circuit-breaker patterns configured.
- **FR-006**: Each service MUST maintain its own datastore for persistence and MUST NOT access another service's datastore directly.
- **FR-007**: API Gateway MUST serve as the single entry point for external clients; internal service-to-service calls may bypass the gateway but MUST use service discovery.
- **FR-008**: Error handling: dependent-service failures MUST return suitable 4xx/5xx codes (fail-fast) and include machine-readable error details.

### Key Entities

- **Agent**: { id: UUID, name: string, status: enum(active|inactive|deleted), createdAt: timestamp }
- **Publish**: { id: UUID, agentId: UUID, status: enum(published|unpublished), publishedAt: timestamp }
- **Subscription**: { id: UUID, agentId: UUID, userId: string, status: enum(active|inactive), createdAt: timestamp }

## Success Criteria *(mandatory)*

### Measurable Outcomes

- **SC-001**: 99% of successful POST `/agents` requests return `201 Created` and a valid `id` within 2 seconds under normal load.
- **SC-002**: Publish and subscribe flows validate agent existence and return the correct status in 99% of transactions.
- **SC-003**: Contract tests for cross-service APIs (agent ↔ publish, agent ↔ subscription) pass in CI on every merge.
- **SC-004**: No cross-service datastore reads occur (verified by code review and integration tests), and all inter-service exchanges contain only IDs.

## Assumptions

- Services will use the project's constitution defaults: REST-only inter-service communication, service discovery, and an API Gateway for external traffic. Refer to the project constitution for required deployment/configuration choices.
- Authentication/authorization is handled at the API Gateway layer for external clients; internal service-to-service calls are authenticated via service registry or mutual TLS (out of scope for this spec).
- `DELETE /agents/{id}` semantics require clarification (soft-delete vs hard-delete). The spec assumes soft-delete for safety unless product owner specifies otherwise.

## Architecture Compliance (mandatory)

- State: `Complies`

Rationale:

- Communication: All cross-service interactions are defined as REST calls (agent validation by publish/subscription) — matches REST-only requirement.
- Database: Each service is defined to own its H2 store — matches Database-Per-Service.
- Gateway & Discovery: Plan assumes an API Gateway and Eureka server per constitution.
- Entity sharing: Services exchange only `agentId` and `userId`, not full entity payloads.

If any deviation is required (for example, switching to hard-delete semantics, adding asynchronous messaging, or sharing richer entity payloads), a `Does Not Comply` rationale and migration plan MUST be added.

**Author**: Automated spec generation from input (review and ratify).  
**Next**: If you confirm the deletion policy (soft vs hard), I will update the spec to remove the NEEDS CLARIFICATION marker and then generate `tasks.md` and `plan.md` per templates.
