package config;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@Configuration
@Profile("!real")
public class WireMockConfig {

    private static final int MOCK_PORT = 8089;

    @Bean(destroyMethod = "stop")
    public WireMockServer wireMockServer() {
        WireMockServer server = new WireMockServer(
                WireMockConfiguration.options().port(MOCK_PORT)
        );
        server.start();
        initStubs(server);
        return server;
    }

    private void initStubs(WireMockServer server) {
        // === Auth: POST /api/login ===
        server.stubFor(post(urlEqualTo("/api/login"))
                .atPriority(1)
                .withRequestBody(notMatching(".*password.*"))
                .willReturn(aResponse()
                        .withStatus(400)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"error\": \"Missing password\"}")));

        server.stubFor(post(urlEqualTo("/api/login"))
                .atPriority(2)
                .withRequestBody(matchingJsonPath("$.email", equalTo("notfound@example.com")))
                .willReturn(aResponse()
                        .withStatus(400)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"error\": \"user not found\"}")));

        server.stubFor(post(urlEqualTo("/api/login"))
                .atPriority(3)
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"token\": \"QpwL5tke4Pnpja7X4\"}")));

        // === Auth: POST /api/register ===
        server.stubFor(post(urlEqualTo("/api/register"))
                .atPriority(1)
                .withRequestBody(notMatching(".*password.*"))
                .willReturn(aResponse()
                        .withStatus(400)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"error\": \"Missing password\"}")));

        server.stubFor(post(urlEqualTo("/api/register"))
                .atPriority(2)
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"id\": 4, \"token\": \"QpwL5tke4Pnpja7X4\"}")));

        // === Users: GET /api/users?page=2 ===
        server.stubFor(get(urlEqualTo("/api/users?page=2"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                {
                                  "page": 2,
                                  "per_page": 6,
                                  "total": 12,
                                  "total_pages": 2,
                                  "data": [
                                    {
                                      "id": 7,
                                      "email": "michael.lawson@reqres.in",
                                      "first_name": "Michael",
                                      "last_name": "Lawson",
                                      "avatar": "https://reqres.in/img/faces/7-image.jpg"
                                    },
                                    {
                                      "id": 8,
                                      "email": "lindsay.ferguson@reqres.in",
                                      "first_name": "Lindsay",
                                      "last_name": "Ferguson",
                                      "avatar": "https://reqres.in/img/faces/8-image.jpg"
                                    }
                                  ],
                                  "support": {
                                    "url": "https://reqres.in/#support-heading",
                                    "text": "To keep ReqRes free, contributions are appreciated!"
                                  }
                                }
                                """)));

        // === Users: GET /api/users/2 ===
        server.stubFor(get(urlEqualTo("/api/users/2"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                {
                                  "data": {
                                    "id": 2,
                                    "email": "janet.weaver@reqres.in",
                                    "first_name": "Janet",
                                    "last_name": "Weaver",
                                    "avatar": "https://reqres.in/img/faces/2-image.jpg"
                                  },
                                  "support": {
                                    "url": "https://reqres.in/#support-heading",
                                    "text": "ReqRes Support"
                                  }
                                }
                                """)));

        // === Users: GET /api/users/9999 (404) ===
        server.stubFor(get(urlEqualTo("/api/users/9999"))
                .willReturn(aResponse()
                        .withStatus(404)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{}")));

        // === Users: POST /api/users ===
        server.stubFor(post(urlEqualTo("/api/users"))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                {
                                  "id": "101",
                                  "name": "{{jsonPath request.body '$.name'}}",
                                  "job": "{{jsonPath request.body '$.job'}}",
                                  "createdAt": "2026-10-26T20:00:00.000Z"
                                }
                                """)
                        .withTransformers("response-template")));

        // === Users: PUT /api/users/2 ===
        server.stubFor(put(urlEqualTo("/api/users/2"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                {
                                  "name": "{{jsonPath request.body '$.name'}}",
                                  "job": "{{jsonPath request.body '$.job'}}",
                                  "updatedAt": "2026-10-26T20:00:00.000Z"
                                }
                                """)
                        .withTransformers("response-template")));

        // === Users: PATCH /api/users/2 ===
        server.stubFor(patch(urlEqualTo("/api/users/2"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                {
                                  "name": "Janet",
                                  "job": "{{jsonPath request.body '$.job'}}",
                                  "updatedAt": "2026-10-26T20:00:00.000Z"
                                }
                                """)
                        .withTransformers("response-template")));

        // === Users: DELETE /api/users/2 ===
        server.stubFor(delete(urlEqualTo("/api/users/2"))
                .willReturn(aResponse().withStatus(204)));
    }
}