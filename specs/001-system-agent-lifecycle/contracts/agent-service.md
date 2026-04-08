# Agent Service API Contract

POST /agents
- Request: { name: string }
- Response: 201 Created { id, name, status, createdAt }

GET /agents/{id}
- Response: 200 OK { id, name, status, createdAt } or 404 Not Found

GET /agents
- Query: pagination params
- Response: 200 OK [ { id, name, status, createdAt }, ... ]

PUT /agents/{id}
- Request: { name?, status? }
- Response: 200 OK updated resource or 404 Not Found

DELETE /agents/{id}
- Behavior: soft-delete — set status=deleted, return 200 OK

Errors: Standardized error object { code, message, details }
