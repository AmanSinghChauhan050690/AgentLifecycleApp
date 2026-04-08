# Publish Service API Contract

POST /publish/{agentId}
- Behavior: Call `agent-service` to validate `agentId` and `status` before publishing.
- Response: 200 OK { id, agentId, status: "published", publishedAt }
- Errors: 404 Not Found if agent invalid; 409 Conflict if agent not in publishable state

POST /unpublish/{agentId}
- Response: 200 OK { id, agentId, status: "unpublished" }

GET /published
- Response: 200 OK [ { id, agentId, status, publishedAt }, ... ]

Errors: Standardized error object { code, message, details }
