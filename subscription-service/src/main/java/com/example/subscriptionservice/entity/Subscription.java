package com.example.subscriptionservice.entity;

import java.time.Instant;

public class Subscription {
    private String id;
    private String agentId;
    private String userId;
    private String status;
    private Instant createdAt;

    public Subscription() {}

    public Subscription(String id, String agentId, String userId, String status, Instant createdAt) {
        this.id = id;
        this.agentId = agentId;
        this.userId = userId;
        this.status = status;
        this.createdAt = createdAt;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getAgentId() { return agentId; }
    public void setAgentId(String agentId) { this.agentId = agentId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
