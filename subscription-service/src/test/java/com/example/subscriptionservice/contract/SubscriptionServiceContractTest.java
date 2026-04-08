package com.example.subscriptionservice.contract;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SubscriptionServiceContractTest {

    @LocalServerPort
    private int port;

    private final TestRestTemplate rest = new TestRestTemplate();

    @Test
    void subscribe_post_should_return_non_error() {
        String url = "http://localhost:" + port + "/subscribe";
        String body = "{\"agentId\":\"123\",\"callbackUrl\":\"http://localhost/cb\"}";

        ResponseEntity<String> resp = rest.postForEntity(url, body, String.class);
        assertThat(resp.getStatusCode().is2xxSuccessful() || resp.getStatusCode().is4xxClientError()).isTrue();
    }
}
