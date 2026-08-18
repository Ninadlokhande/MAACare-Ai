package com.sigma.ai;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import org.json.JSONArray;
import org.json.JSONObject;

public class GeminiProvider implements AIProvider {

    private final String[] apiKeys = {
        AIConfig.get("GEMINI_API_KEY_1"),
        AIConfig.get("GEMINI_API_KEY_2")
    };

    private final String model =
        "gemini-3.6-flash";

    private final String apiUrl =
        "https://generativelanguage.googleapis.com/v1beta/models/"
        + model
        + ":generateContent";

    private final HttpClient httpClient =
        HttpClient.newBuilder()
            .connectTimeout(
                Duration.ofSeconds(30)
                
            )
            .build();

    @Override
    public String getName() {
        return "Gemini";
    }

    @Override
    public String generateResponse(
            String systemPrompt,
            String userMessage) throws Exception {

        Exception lastException = null;

        for (int i = 0; i < apiKeys.length; i++) {

            String apiKey = apiKeys[i];

            if (
                apiKey == null ||
                apiKey.isBlank()
            ) {

                System.out.println(
                    "Gemini Key "
                    + (i + 1)
                    + " is missing."
                );

                continue;
            }

            try {

                System.out.println(
                    "Trying Gemini Key "
                    + (i + 1)
                    + "..."
                );

                String prompt =
                    systemPrompt
                    + "\n\nUser:\n"
                    + userMessage;

                JSONObject textPart =
                    new JSONObject();

                textPart.put(
                    "text",
                    prompt
                );

                JSONArray parts =
                    new JSONArray();

                parts.put(
                    textPart
                );

                JSONObject content =
                    new JSONObject();

                content.put(
                    "parts",
                    parts
                );

                JSONArray contents =
                    new JSONArray();

                contents.put(
                    content
                );

                JSONObject requestBody =
                    new JSONObject();

                requestBody.put(
                    "contents",
                    contents
                );

                HttpRequest request =
                    HttpRequest.newBuilder()
                        .uri(
                            URI.create(apiUrl)
                        )
                       .timeout(Duration.ofSeconds(60)
                        )
                        .header(
                            "Content-Type",
                            "application/json"
                        )
                        .header(
                            "x-goog-api-key",
                            apiKey
                        )
                        .POST(
                            HttpRequest.BodyPublishers
                                .ofString(
                                    requestBody.toString()
                                )
                        )
                        .build();

                HttpResponse<String> response =
                    httpClient.send(
                        request,
                        HttpResponse.BodyHandlers
                            .ofString()
                    );

                int status =
                    response.statusCode();

                if (
                    status >= 200 &&
                    status < 300
                ) {

                    System.out.println(
                        "Gemini Key "
                        + (i + 1)
                        + " successful."
                    );

                    return extractText(
                        response.body()
                    );
                }

                System.out.println(
                    "Gemini Key "
                    + (i + 1)
                    + " failed. HTTP: "
                    + status
                );

                System.out.println(
                    "Gemini response:"
                );

                System.out.println(
                    response.body()
                );

                lastException =
                    new RuntimeException(
                        "Gemini HTTP "
                        + status
                        + ": "
                        + response.body()
                    );

            } catch (Exception e) {

                System.out.println(
                    "Gemini Key "
                    + (i + 1)
                    + " failed: "
                    + e.getMessage()
                );

                lastException = e;
            }
        }

        throw new RuntimeException(
            "All Gemini API keys failed.",
            lastException
        );
    }

    private String extractText(
            String responseBody) {

        JSONObject root =
            new JSONObject(
                responseBody
            );

        JSONArray candidates =
            root.getJSONArray(
                "candidates"
            );

        if (candidates.isEmpty()) {

            throw new RuntimeException(
                "Gemini returned no candidates."
            );
        }

        JSONObject candidate =
            candidates.getJSONObject(0);

        JSONObject content =
            candidate.getJSONObject(
                "content"
            );

        JSONArray parts =
            content.getJSONArray(
                "parts"
            );

        StringBuilder result =
            new StringBuilder();

        for (
            int i = 0;
            i < parts.length();
            i++
        ) {

            JSONObject part =
                parts.getJSONObject(i);

            if (
                part.has("text")
            ) {

                result.append(
                    part.getString("text")
                );
            }
        }

        if (result.isEmpty()) {

            throw new RuntimeException(
                "Gemini response contained no text."
            );
        }

        return result.toString();
    }
}