# data-model.md

## Entities

### Agent
- `id` (UUID) - primary identifier
- `name` (string)
- `status` (enum: active | inactive | deleted)
- `createdAt` (timestamp)

### Publish
- `id` (UUID)
- `agentId` (UUID) - foreign reference to Agent via ID only
- `status` (enum: published | unpublished)
- `publishedAt` (timestamp)

### Subscription
- `id` (UUID)
- `agentId` (UUID) - foreign reference to Agent via ID only
- `userId` (string)
- `status` (enum: active | inactive)
- `createdAt` (timestamp)

## Validation rules
- `name` is required and must be unique within `agent-service`.
- `agentId` references MUST be validated via `agent-service` before creating Publish or Subscription records.
- `status` transitions: only allow valid transitions (e.g., cannot publish a deleted agent).

## Relationships
- No cross-service entity sharing; relationships maintained by IDs only.

***
