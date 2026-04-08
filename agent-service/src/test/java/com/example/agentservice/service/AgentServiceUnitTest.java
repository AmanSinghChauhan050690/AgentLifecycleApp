package com.example.agentservice.service;

import com.example.agentservice.entity.Agent;
import com.example.agentservice.repository.AgentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AgentServiceUnitTest {

    @Mock
    private AgentRepository repo;

    @InjectMocks
    private AgentService service;

    @Test
    void create_returnsAgentWithId() {
        Agent a = new Agent();
        a.setName("unit-agent");

        Agent saved = new Agent();
        saved.setId("id-1");
        saved.setName("unit-agent");

        when(repo.save(a)).thenReturn(saved);

        Agent out = service.create(a);
        assertThat(out).isNotNull();
        assertThat(out.getId()).isEqualTo("id-1");
    }

    @Test
    void delete_marksAgentDeleted() {
        Agent a = new Agent();
        a.setId("del-1");
        a.setStatus("active");

        when(repo.findById("del-1")).thenReturn(Optional.of(a));
        when(repo.save(a)).thenReturn(a);

        service.delete("del-1");

        assertThat(a.getStatus()).isEqualTo("deleted");
    }
}
