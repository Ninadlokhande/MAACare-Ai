package com.sigma.ai;

import java.util.ArrayList;
import java.util.List;

public class ConversationManager {

    private final List<String> conversationHistory =
            new ArrayList<>();

    public void addUserMessage(String message) {

        conversationHistory.add(
                "User: " + message
        );
    }

    public void addAIMessage(String message) {

        conversationHistory.add(
                "MaaCare AI: " + message
        );
    }

    public List<String> getHistory() {

        return new ArrayList<>(
                conversationHistory
        );
    }

    public String getConversationText() {

        StringBuilder conversation =
                new StringBuilder();

        for (String message :
                conversationHistory) {

            conversation
                    .append(message)
                    .append("\n");
        }

        return conversation.toString();
    }

    public void clearConversation() {

        conversationHistory.clear();
    }

    public int getMessageCount() {

        return conversationHistory.size();
    }
}