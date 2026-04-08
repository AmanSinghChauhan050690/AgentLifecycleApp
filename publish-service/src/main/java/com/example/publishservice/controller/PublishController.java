package com.example.publishservice.controller;

import com.example.publishservice.entity.Publish;
import com.example.publishservice.service.PublishService;

import java.util.List;
import java.util.Optional;

public class PublishController {
    private final PublishService service;

    public PublishController(PublishService service) {
        this.service = service;
    }

    public Publish publish(String agentId) {
        return service.publishAgent(agentId);
    }

    public Publish unpublish(String agentId) {
        return service.unpublishAgent(agentId);
    }

    public List<Publish> getPublished() {
        return service.list();
    }

    public Optional<Publish> getById(String id) {
        return service.getById(id);
    }
}
