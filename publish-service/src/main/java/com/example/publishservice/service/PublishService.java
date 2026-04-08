package com.example.publishservice.service;

import com.example.publishservice.entity.Publish;
import com.example.publishservice.repository.PublishRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public class PublishService {
    private final PublishRepository repository;

    public PublishService(PublishRepository repository) {
        this.repository = repository;
    }

    public Publish publishAgent(String agentId) {
        Publish p = new Publish();
        p.setAgentId(agentId);
        p.setStatus("published");
        p.setPublishedAt(Instant.now());
        return repository.save(p);
    }

    public Optional<Publish> getById(String id) {
        return repository.findById(id);
    }

    public List<Publish> list() {
        return repository.findAll();
    }

    public Publish unpublishAgent(String agentId) {
        // simplistic: create an unpublished record or update existing
        Publish p = new Publish();
        p.setAgentId(agentId);
        p.setStatus("unpublished");
        p.setPublishedAt(Instant.now());
        return repository.save(p);
    }
}
