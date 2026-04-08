package com.example.publishservice.repository;

import com.example.publishservice.entity.Publish;
import java.util.List;
import java.util.Optional;

public interface PublishRepository {
    Publish save(Publish publish);
    Optional<Publish> findById(String id);
    List<Publish> findAll();
    void deleteById(String id);
}
