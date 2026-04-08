package com.example.agentservice.repository;

import com.example.agentservice.entity.Agent;
import java.util.List;
import java.util.Optional;

public interface AgentRepository {
    Agent save(Agent agent);
    Optional<Agent> findById(String id);
    List<Agent> findAll();
    void deleteById(String id);
}

