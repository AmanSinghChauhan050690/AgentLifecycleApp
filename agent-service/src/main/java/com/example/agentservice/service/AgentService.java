package com.example.agentservice.service;

import com.example.agentservice.entity.Agent;
import com.example.agentservice.repository.AgentRepository;

import java.util.List;
import java.util.Optional;

public class AgentService {
    private final AgentRepository repository;

    public AgentService(AgentRepository repository) {
        this.repository = repository;
    }

    public Agent create(Agent agent) {
        return repository.save(agent);
    }

    public Optional<Agent> getById(String id) {
        return repository.findById(id);
    }

    public List<Agent> list() {
        return repository.findAll();
    }

    public Agent update(Agent agent) {
        return repository.save(agent);
    }

    public void softDelete(String id) {
        // Repository deleteById used here; implementations should implement soft-delete behavior
        repository.deleteById(id);
    }
}
