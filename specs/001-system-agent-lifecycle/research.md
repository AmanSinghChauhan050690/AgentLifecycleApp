# research.md

## Decisions

1. Communication: synchronous REST-only
   - Rationale: requirement specified REST-based microservices; synchronous calls simplify validation flows (publish/subscription must confirm agent exists) and align with fail-fast requirement.

2. Discovery: Eureka Server
   - Rationale: project constitution mandates Eureka for service registry; enables runtime discovery for client libraries.

3. API Gateway
   - Rationale: single-entry pattern required by constitution for routing, auth, and cross-cutting concerns.

4. Persistence: Database-Per-Service (H2 baseline)
   - Rationale: constitution defines H2 per-service baseline for development. Production DBs can be chosen per-service later.

5. Client libraries: HTTP client with timeouts and circuit-breakers
   - Rationale: constitution required synchronous calls using a client; incorporate circuit-breaker patterns (Resilience4j or similar) to implement fail-fast behavior.

6. Delete semantics: Soft-delete
   - Rationale: safer for audit and downstream processes; chosen by product owner.

## Alternatives considered

- Asynchronous messaging (Kafka): rejected due to constitution constraint (no event streaming).
- Shared database: rejected due to Database-Per-Service principle.

## Open questions (resolved)

- Deletion policy: resolved to Soft-delete (status=deleted). Purge policy optional later.

***
