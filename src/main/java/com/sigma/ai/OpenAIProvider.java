package com.sigma.ai;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import org.json.JSONArray;
import org.json.JSONObject;

public class OpenAIProvider implements AIProvider {

    private final String[] apiKeys = {
        AIConfig.get("OPENAI_API_KEY_1"),
        AIConfig.get("OPENAI_API_KEY_2")
    };

    private final String model =
        "gpt-5.6";

    private final String apiUrl =
        "https://api.openai.com/v1/responses";

    private final HttpClient httpClient =
        HttpClient.newBuilder()
            .connectTimeout(
                Duration.ofSeconds(30)
            )
            .build();

    @Override
    public String getName() {
        return "OpenAI";
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
                    "OpenAI Key "
                    + (i + 1)
                    + " is missing."
                );

                continue;
            }

            try {

                System.out.println(
                    "Trying OpenAI Key "
                    + (i + 1)
                    + "..."
                );

                JSONObject requestBody =
                    new JSONObject();

                requestBody.put(
                    "model",
                    model
                );

                requestBody.put(
                    "instructions",
                    systemPrompt
                );

                requestBody.put(
                    "input",
                    userMessage
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
                        "OpenAI Key "
                        + (i + 1)
                        + " successful."
                    );

                    return extractText(
                        response.body()
                    );
                }

                System.out.println(
                    "OpenAI Key "
                    + (i + 1)
                    + " failed. HTTP: "
                    + status
                );

                System.out.println(
                    "OpenAI response:"
                );

                System.out.println(
                    response.body()
                );

                lastException =
                    new RuntimeException(
                        "OpenAI HTTP "
                        + status
                        + ": "
                        + response.body()
                    );

            } catch (Exception e) {

                System.out.println(
                    "OpenAI Key "
                    + (i + 1)
                    + " failed: "
                    + e.getMessage()
                );

                lastException = e;
            }
        }

        throw new RuntimeException(
            "All OpenAI API keys failed.",
            lastException
        );
    }

    private String extractText(
            String responseBody) {

        JSONObject root =
            new JSONObject(
                responseBody
            );

        if (
            root.has("output_text")
        ) {

            String output =
                root.getString(
                    "output_text"
                );

            if (
                output != null &&
                !output.isBlank()
            ) {

                return output;
            }
        }

        JSONArray output =
            root.getJSONArray(
                "output"
            );

        StringBuilder result =
            new StringBuilder();

        for (
            int i = 0;
            i < output.length();
            i++
        ) {

            JSONObject outputItem =
                output.getJSONObject(i);

            if (
                !outputItem.has("content")
            ) {
                continue;
            }

            JSONArray content =
                outputItem.getJSONArray(
                    "content"
                );

            for (
                int j = 0;
                j < content.length();
                j++
            ) {

                JSONObject contentItem =
                    content.getJSONObject(j);

                if (
                    contentItem.has("text")
                ) {

                    result.append(
                        contentItem.getString(
                            "text"
                        )
                    );
                }
            }
        }

        if (result.isEmpty()) {

            throw new RuntimeException(
                "OpenAI response contained no text."
            );
        }

        return result.toString();
    }
}