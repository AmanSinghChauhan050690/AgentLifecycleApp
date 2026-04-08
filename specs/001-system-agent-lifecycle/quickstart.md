# quickstart.md

## Local quickstart (development)

Prerequisites: Java 11+ or runtime of chosen stack, Docker (optional), Git.

1. Start Eureka (service discovery) and API Gateway locally (Docker compose or local run).

2. Start `agent-service`:

- Run the service app in `backend/agent-service` (framework-specific run command).
- Verify: `GET http://localhost:{agent_port}/agents` returns 200.

3. Start `publish-service` and `subscription-service`.

4. Example flows:

- Create agent:
  POST http://localhost:{gateway_port}/agents
  Body: { "name": "my-agent" }

- Publish agent:
  POST http://localhost:{gateway_port}/publish/{agentId}

- Subscribe:
  POST http://localhost:{gateway_port}/subscribe
  Body: { "agentId": "...", "userId": "user-123" }

Notes:
- The API Gateway serves as the public entry point. Internal services may use discovery to call each other directly.
- For development, H2 is used as the baseline datastore.

***
