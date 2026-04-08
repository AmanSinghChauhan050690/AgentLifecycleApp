package com.example.agentservice.controller;

import com.example.agentservice.entity.Agent;
import com.example.agentservice.service.AgentService;

import java.util.List;
import java.util.Optional;

public class AgentController {
    private final AgentService service;

    public AgentController(AgentService service) {
        this.service = service;
    }

    public Agent createAgent(Agent agent) {
        return service.create(agent);
    }

    public Optional<Agent> getAgent(String id) {
        return service.getById(id);
    }

    public List<Agent> listAgents() {
        return service.list();
    }

    public Agent updateAgent(Agent agent) {
        return service.update(agent);
    }

    public void deleteAgent(String id) {
        service.softDelete(id);
    }
}
