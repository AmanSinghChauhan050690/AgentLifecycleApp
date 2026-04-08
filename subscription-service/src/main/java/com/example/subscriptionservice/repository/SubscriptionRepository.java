package com.example.subscriptionservice.repository;

import com.example.subscriptionservice.entity.Subscription;
import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository {
    Subscription save(Subscription subscription);
    Optional<Subscription> findById(String id);
    List<Subscription> findAll();
    void deleteById(String id);
}
