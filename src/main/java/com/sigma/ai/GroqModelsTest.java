package com.sigma.ai;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class GroqModelsTest {

    public static void main(String[] args)
            throws Exception {

        String apiKey =
            AIConfig.get("GROQ_API_KEY_1");

        if (
            apiKey == null ||
            apiKey.isBlank()
        ) {

            System.out.println(
                "GROQ_API_KEY_1 is missing."
            );

            return;
        }

        HttpClient client =
            HttpClient.newBuilder()
                .connectTimeout(
                    Duration.ofSeconds(30)
                )
                .build();

        HttpRequest request =
            HttpRequest.newBuilder()
                .uri(
                    URI.create(
                        "https://api.groq.com/openai/v1/models"
                    )
                )
                .timeout(
                    Duration.ofSeconds(30)
                )
                .header(
                    "Authorization",
                    "Bearer " + apiKey
                )
                .GET()
                .build();

        HttpResponse<String> response =
            client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
            );

        System.out.println(
            "HTTP: "
            + response.statusCode()
        );

        System.out.println(
            response.body()
        );
    }
}