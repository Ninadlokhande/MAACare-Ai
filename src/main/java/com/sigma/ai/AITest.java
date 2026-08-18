package com.sigma.ai;

public class AITest {

    public static void main(String[] args) {

        System.out.println(
            "================================"
        );

        System.out.println(
            "       MaaCare AI Test"
        );

        System.out.println(
            "================================"
        );

        try {

            AIAssistant assistant =
                new AIAssistant();

            System.out.println(
                "\nSending test question..."
            );

            String response =
                assistant.ask(
                    "What are some healthy foods "
                    + "during pregnancy?"
                );

            System.out.println(
                "\n================================"
            );

            System.out.println(
                "AI RESPONSE:"
            );

            System.out.println(
                response
            );

            System.out.println(
                "================================"
            );

        } catch (Exception e) {

            System.out.println(
                "\n================================"
            );

            System.out.println(
                "AI TEST FAILED"
            );

            System.out.println(
                "================================"
            );

            System.out.println(
                "Error: "
                + e.getMessage()
            );

            e.printStackTrace();
        }
    }
}