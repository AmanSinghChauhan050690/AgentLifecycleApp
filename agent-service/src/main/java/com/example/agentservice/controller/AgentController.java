package com.example.agentservice.controller;

import com.example.agentservice.entity.Agent;
import com.example.agentservice.service.AgentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/agents")
public class AgentController {
    private final AgentService service;

    public AgentController(AgentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Agent> createAgent(@RequestBody Agent agent) {
        Agent created = service.create(agent);
        return ResponseEntity.created(URI.create("/agents/" + created.getId())).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agent> getAgent(@PathVariable String id) {
        return service.getById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Agent> listAgents() {
        return service.list();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agent> updateAgent(@PathVariable String id, @RequestBody Agent updates) {
        Agent updated = service.update(id, updates);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgent(@PathVariable String id) {
        service.softDelete(id);
        return ResponseEntity.ok().build();
    }
}
