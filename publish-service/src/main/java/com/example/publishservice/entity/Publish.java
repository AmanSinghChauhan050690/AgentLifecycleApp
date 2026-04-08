package com.example.publishservice.entity;

import java.time.Instant;

public class Publish {
    private String id;
    private String agentId;
    private String status;
    private Instant publishedAt;

    public Publish() {}

    public Publish(String id, String agentId, String status, Instant publishedAt) {
        this.id = id;
        this.agentId = agentId;
        this.status = status;
        this.publishedAt = publishedAt;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getAgentId() { return agentId; }
    public void setAgentId(String agentId) { this.agentId = agentId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Instant getPublishedAt() { return publishedAt; }
    public void setPublishedAt(Instant publishedAt) { this.publishedAt = publishedAt; }
}
