package com.example.agentservice.service;

import com.example.agentservice.entity.Agent;
import com.example.agentservice.repository.AgentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgentService {
    private final AgentRepository repository;

    public AgentService(AgentRepository repository) {
        this.repository = repository;
    }

    public Agent create(Agent agent) {
        if (agent.getStatus() == null) {
            agent.setStatus("active");
        }
        return repository.save(agent);
    }

    public Optional<Agent> getById(String id) {
        return repository.findById(id);
    }

    public List<Agent> list() {
        return repository.findAll();
    }

    public Agent update(String id, Agent updates) {
        Optional<Agent> existing = repository.findById(id);
        if (existing.isPresent()) {
            Agent a = existing.get();
            if (updates.getName() != null) a.setName(updates.getName());
            if (updates.getStatus() != null) a.setStatus(updates.getStatus());
            return repository.save(a);
        }
        // If not found, treat as create with given id
        updates.setId(id);
        return repository.save(updates);
    }

    public void softDelete(String id) {
        repository.deleteById(id);
    }
}
