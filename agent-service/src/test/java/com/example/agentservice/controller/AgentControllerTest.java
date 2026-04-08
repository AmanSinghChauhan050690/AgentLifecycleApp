package com.example.agentservice.controller;

import com.example.agentservice.entity.Agent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AgentController.class)
public class AgentControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private com.example.agentservice.service.AgentService agentService;

    @Test
    void createAgent_returns201_andBody() throws Exception {
        Agent a = new Agent();
        a.setId("123");
        a.setName("test-agent");
        a.setStatus("active");

        when(agentService.create(any(Agent.class))).thenReturn(a);

        String body = "{\"name\":\"test-agent\"}";

        mvc.perform(post("/agents").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("123"))
                .andExpect(jsonPath("$.name").value("test-agent"));
    }
}
