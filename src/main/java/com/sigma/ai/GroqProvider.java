package com.sigma.ai;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import org.json.JSONArray;
import org.json.JSONObject;

public class GroqProvider implements AIProvider {

    private final String[] apiKeys = {
        AIConfig.get("GROQ_API_KEY_1"),
        AIConfig.get("GROQ_API_KEY_2")
    };

    private final String model =
        "openai/gpt-oss-120b";

    private final String apiUrl =
        "https://api.groq.com/openai/v1/chat/completions";

    private final HttpClient httpClient =
        HttpClient.newBuilder()
            .connectTimeout(
                Duration.ofSeconds(30)
            )
            .build();

    @Override
    public String getName() {
        return "Groq";
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
                    "Groq Key "
                    + (i + 1)
                    + " is missing."
                );

                continue;
            }

            try {

                System.out.println(
                    "Trying Groq Key "
                    + (i + 1)
                    + "..."
                );

                JSONObject systemMessage =
                    new JSONObject();

                systemMessage.put(
                    "role",
                    "system"
                );

                systemMessage.put(
                    "content",
                    systemPrompt
                );

                JSONObject userMessageObject =
                    new JSONObject();

                userMessageObject.put(
                    "role",
                    "user"
                );

                userMessageObject.put(
                    "content",
                    userMessage
                );

                JSONArray messages =
                    new JSONArray();

                messages.put(
                    systemMessage
                );

                messages.put(
                    userMessageObject
                );

                JSONObject requestBody =
                    new JSONObject();

                requestBody.put(
                    "model",
                    model
                );

                requestBody.put(
                    "messages",
                    messages
                );

                HttpRequest request =
                    HttpRequest.newBuilder()
                        .uri(
                            URI.create(apiUrl)
                        )
                        .timeout(
                            Duration.ofSeconds(60)
                        )
                        .header(
                            "Content-Type",
                            "application/json"
                        )
                        .header(
                            "Authorization",
                            "Bearer " + apiKey
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
                        "Groq Key "
                        + (i + 1)
                        + " successful."
                    );

                    return extractText(
                        response.body()
                    );
                }

                System.out.println(
                    "Groq Key "
                    + (i + 1)
                    + " failed. HTTP: "
                    + status
                );

                System.out.println(
                    "Groq response:"
                );

                System.out.println(
                    response.body()
                );

                lastException =
                    new RuntimeException(
                        "Groq HTTP "
                        + status
                        + ": "
                        + response.body()
                    );

            } catch (Exception e) {

                System.out.println(
                    "Groq Key "
                    + (i + 1)
                    + " failed: "
                    + e.getMessage()
                );

                lastException = e;
            }
        }

        throw new RuntimeException(
            "All Groq API keys failed.",
            lastException
        );
    }

    private String extractText(
            String responseBody) {

        JSONObject root =
            new JSONObject(
                responseBody
            );

        JSONArray choices =
            root.getJSONArray(
                "choices"
            );

        if (choices.isEmpty()) {

            throw new RuntimeException(
                "Groq returned no choices."
            );
        }

        JSONObject choice =
            choices.getJSONObject(0);

        JSONObject message =
            choice.getJSONObject(
                "message"
            );

        String text =
            message.getString(
                "content"
            );

        if (
            text == null ||
            text.isBlank()
        ) {

            throw new RuntimeException(
                "Groq response contained no text."
            );
        }

        return text;
    }
}