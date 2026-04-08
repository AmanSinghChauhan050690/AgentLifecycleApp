package com.example.subscriptionservice.controller;

import com.example.subscriptionservice.entity.Subscription;
import com.example.subscriptionservice.service.SubscriptionService;

import java.util.List;
import java.util.Optional;

public class SubscriptionController {
    private final SubscriptionService service;

    public SubscriptionController(SubscriptionService service) {
        this.service = service;
    }

    public Subscription subscribe(String agentId, String userId) {
        return service.subscribe(agentId, userId);
    }

    public Subscription unsubscribe(String agentId, String userId) {
        return service.unsubscribe(agentId, userId);
    }

    public List<Subscription> getSubscriptions() { return service.list(); }

    public Optional<Subscription> getById(String id) { return service.getById(id); }
}
