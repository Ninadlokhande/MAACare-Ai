package com.sigma.ai;

public class AIResponse {

    private boolean success;
    private String provider;
    private String response;
    private String error;

    public AIResponse(
            boolean success,
            String provider,
            String response,
            String error) {

        this.success = success;
        this.provider = provider;
        this.response = response;
        this.error = error;
    }

    public static AIResponse success(
            String provider,
            String response) {

        return new AIResponse(
                true,
                provider,
                response,
                null
        );
    }

    public static AIResponse failure(
            String provider,
            String error) {

        return new AIResponse(
                false,
                provider,
                null,
                error
        );
    }

    public boolean isSuccess() {
        return success;
    }

    public String getProvider() {
        return provider;
    }

    public String getResponse() {
        return response;
    }

    public String getError() {
        return error;
    }
}