package com.example.agentservice.controller;

import com.example.agentservice.entity.Agent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AgentController.class)
public class AgentControllerCrudTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private com.example.agentservice.service.AgentService agentService;

    @Test
    void getAgent_returns200_andBody() throws Exception {
        Agent a = new Agent();
        a.setId("123");
        a.setName("test-agent");
        a.setStatus("active");

        when(agentService.getById("123")).thenReturn(a);

        mvc.perform(get("/agents/123").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("123"))
                .andExpect(jsonPath("$.name").value("test-agent"));
    }

    @Test
    void updateAgent_returns200_andBody() throws Exception {
        Agent updated = new Agent();
        updated.setId("123");
        updated.setName("updated-name");
        updated.setStatus("active");

        when(agentService.update(eq("123"), any(Agent.class))).thenReturn(updated);

        String body = "{\"name\":\"updated-name\"}";

        mvc.perform(put("/agents/123").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("123"))
                .andExpect(jsonPath("$.name").value("updated-name"));
    }

    @Test
    void deleteAgent_returns204() throws Exception {
        doNothing().when(agentService).delete("123");

        mvc.perform(delete("/agents/123"))
                .andExpect(status().isNoContent());
    }
}
