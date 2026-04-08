package com.example.agentservice.repository;

import com.example.agentservice.entity.Agent;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.UUID;

@Repository
public class InMemoryAgentRepository implements AgentRepository {
    private final Map<String, Agent> store = new ConcurrentHashMap<>();

    @Override
    public Agent save(Agent agent) {
        if (agent.getId() == null) {
            agent.setId(UUID.randomUUID().toString());
            agent.setCreatedAt(Instant.now());
        }
        store.put(agent.getId(), agent);
        return agent;
    }

    @Override
    public Optional<Agent> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Agent> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void deleteById(String id) {
        Agent a = store.get(id);
        if (a != null) {
            a.setStatus("deleted");
            a.setCreatedAt(a.getCreatedAt() == null ? Instant.now() : a.getCreatedAt());
            store.put(id, a);
        }
    }
}
