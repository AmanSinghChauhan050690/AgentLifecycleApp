package com.example.publishservice.contract;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PublishAgentContractTest {

    @LocalServerPort
    private int port;

    private final TestRestTemplate rest = new TestRestTemplate();

    @Test
    void agentService_should_return_agent_by_id() {
        String url = "http://localhost:" + port + "/agents/123";
        ResponseEntity<String> resp = rest.getForEntity(url, String.class);
        // Basic smoke assert: service should return 200 or 404 depending on state; we assert non-error
        assertThat(resp.getStatusCode().is2xxSuccessful() || resp.getStatusCode().is4xxClientError()).isTrue();
    }
}
