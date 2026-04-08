<!--
Sync Impact Report

Version change: UNVERSIONED -> 1.0.0

Modified principles:
- SERVICE PER DOMAIN: agent-service, publish-service, subscription-service
- LOOSE COUPLING: REST-only, no shared DB
- DATABASE PER SERVICE: H2 per service
- API GATEWAY PATTERN: single entry point required
- SERVICE DISCOVERY: Eureka Server required
- SYNCHRONOUS COMMUNICATION: OpenFeign/RestTemplate
- FAIL FAST: explicit error handling on dependency failure
- CLEAN CODE STRUCTURE: controller → service → repository → entity
- SCALABILITY READY: design must permit future Kafka integration
- NO CROSS-SERVICE ENTITY SHARING: only IDs passed between services

Added sections: Architecture Constraints, Development Workflow
Removed sections: none

Templates requiring updates:
- .specify/templates/plan-template.md ✅ updated
- .specify/templates/spec-template.md ✅ updated
- .specify/templates/tasks-template.md ✅ updated

Follow-up TODOs:
- RATIFICATION_DATE: TODO(RATIFICATION_DATE): confirm official ratification date
-->

# Agent Management Lifecycle System (Microservices) Constitution

## Core Principles

### 1. SERVICE PER DOMAIN

Description: The system is organized as separate services by domain. The canonical
services are: `agent-service`, `publish-service`, and `subscription-service`.
Each service is responsible for its domain logic, contracts, and persistence.

### 2. LOOSE COUPLING

Description: Services MUST communicate via REST APIs only. No other IPC
mechanisms (message brokers, in-process calls) are permitted for cross-service
communication in this constitution. Services MUST avoid runtime coupling such as
shared libraries that contain domain entities.

### 3. DATABASE PER SERVICE

Description: Each service MUST own its data and schema. For the current
implementation baseline, each service uses an embedded H2 database. Direct
cross-service database access is PROHIBITED.

### 4. API GATEWAY PATTERN

Description: All external clients MUST go through a single API Gateway which
serves as the single entry point for routing, authentication, and common
cross-cutting concerns.

### 5. SERVICE DISCOVERY

Description: Services MUST register with a Eureka Server for runtime discovery
and health checks. Service registration and discovery configuration MUST be
documented in each service's deployment manifest.

### 6. SYNCHRONOUS COMMUNICATION

Description: Inter-service calls MUST be synchronous HTTP requests implemented
via OpenFeign or RestTemplate. Circuit breakers and timeouts MUST be used to
limit cascading failures.

### 7. FAIL FAST

Description: If a dependent service is unavailable or returns an error, the
callee MUST return an appropriate HTTP error (e.g., 502/503) with clear, typed
error details. Services MUST fail fast and avoid blocking retries without
backoff or time limits.

### 8. CLEAN CODE STRUCTURE

Description: Service code MUST adhere to the `controller → service →
repository → entity` layering. Public APIs and domain boundaries MUST be
explicit and minimal.

### 9. SCALABILITY READY

Description: Designs MUST be prepared for horizontal scaling and for later
integration of asynchronous components (e.g., Kafka) without fundamental
redesign. This means clear messaging/interaction seams and non-blocking
operation points where asynchronous integration may be inserted.

### 10. NO CROSS-SERVICE ENTITY SHARING

Description: Only identifiers (IDs) may be exchanged between services; full
entity objects MUST NOT be passed or shared. Each service owns its entity
definitions and serialization contracts.

## Architecture Constraints

The system architecture is the following:

- Distributed Microservices (REST-based communication)
- No event streaming (Kafka excluded at present)
- No Notification Service
- Datastore per service: H2 (embedded)
- Discovery: Eureka Server
- Inter-service client: OpenFeign / RestTemplate
- Gateway: Single API Gateway must be deployed

## Development Workflow & Quality Gates

- All changes to services MUST include contract tests for any public REST API.
- Integration tests verifying inter-service contracts MUST be present when a
	change affects cross-service behavior.
- A `Constitution Check` gate (see plan template) MUST pass before Phase 0
	research is closed and before merges to main that change architecture.

## Governance

Amendments: Amendments to this constitution require a documented proposal in a
pull request, a technical rationale, and approval from at least two maintainers
including one architect. Major or non-backward-compatible principle changes
require a migration plan and a MAJOR version bump.

Versioning: The constitution uses semantic versioning: MAJOR.MINOR.PATCH.
- MAJOR: Incompatible governance or principle redefinitions or removals.
- MINOR: New principle or material expansion of guidance.
- PATCH: Clarifications, wording fixes, non-semantic refinements.

Ratification & Amendments:
- **Ratified**: TODO(RATIFICATION_DATE): confirm official adoption date.
- **Last Amended**: 2026-04-08

Compliance: All PRs that change architecture or cross-service contracts MUST
include a `Constitution Compliance` checklist that references this document and
documents how changes preserve or deliberately alter the principles above.

**Version**: 1.0.0 | **Ratified**: TODO(RATIFICATION_DATE) | **Last Amended**: 2026-04-08

