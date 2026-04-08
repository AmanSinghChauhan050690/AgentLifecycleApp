package com.example.publishservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "agent-service")
public interface AgentClient {
    @GetMapping("/agents/{id}")
    Object getAgent(@PathVariable("id") String agentId);
}
