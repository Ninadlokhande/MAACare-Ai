package com.sigma.ai;


public interface AIProvider {

    String getName();

    String generateResponse(
            String systemPrompt,
            String userMessage
    ) throws Exception;
}