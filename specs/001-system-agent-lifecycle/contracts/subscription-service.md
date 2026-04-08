# Subscription Service API Contract

POST /subscribe
- Request: { agentId: UUID, userId: string }
- Behavior: Validate `agentId` via `agent-service`. If agent is published and active, create subscription.
- Response: 201 Created { id, agentId, userId, status: "active", createdAt }
- Errors: 404 Not Found if agent invalid; 409 Conflict if agent not subscribable

POST /unsubscribe
- Request: { agentId: UUID, userId: string }
- Response: 200 OK { id, agentId, userId, status: "inactive" }

GET /subscriptions
- Query: filter by `userId` or `agentId`
- Response: 200 OK [ { id, agentId, userId, status, createdAt }, ... ]

Errors: Standardized error object { code, message, details }
