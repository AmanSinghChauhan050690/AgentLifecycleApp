package com.example.subscriptionservice.service;

import com.example.subscriptionservice.entity.Subscription;
import com.example.subscriptionservice.repository.SubscriptionRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public class SubscriptionService {
    private final SubscriptionRepository repository;

    public SubscriptionService(SubscriptionRepository repository) {
        this.repository = repository;
    }

    public Subscription subscribe(String agentId, String userId) {
        Subscription s = new Subscription();
        s.setAgentId(agentId);
        s.setUserId(userId);
        s.setStatus("active");
        s.setCreatedAt(Instant.now());
        return repository.save(s);
    }

    public Subscription unsubscribe(String agentId, String userId) {
        Subscription s = new Subscription();
        s.setAgentId(agentId);
        s.setUserId(userId);
        s.setStatus("inactive");
        s.setCreatedAt(Instant.now());
        return repository.save(s);
    }

    public Optional<Subscription> getById(String id) { return repository.findById(id); }
    public List<Subscription> list() { return repository.findAll(); }
}
