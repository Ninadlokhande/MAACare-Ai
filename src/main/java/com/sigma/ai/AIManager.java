// package com.sigma.ai;

// import java.util.ArrayList;
// import java.util.List;

// public class AIManager {

//     private final List<AIProvider> providers =
//         new ArrayList<>();

//     public AIManager() {

//         providers.add(new GeminiProvider());
//         providers.add(new GroqProvider());
//         providers.add(new OpenRouterProvider());
//         providers.add(new OpenAIProvider());

//         System.out.println(
//             "AI Manager initialized"
//         );

//         System.out.println(
//             "Available providers: "
//             + providers.size()
//         );
//     }

//     public String ask(
//             String systemPrompt,
//             String userMessage) {

//         Exception lastException = null;

//         for (AIProvider provider : providers) {

//             System.out.println(
//                 "\n================================"
//             );

//             System.out.println(
//                 "TRYING: "
//                 + provider.getName()
//             );

//             System.out.println(
//                 "================================"
//             );

//             try {

//                 String response =
//                     provider.generateResponse(
//                         systemPrompt,
//                         userMessage
//                     );

//                 if (
//                     response != null &&
//                     !response.isBlank()
//                 ) {

//                     System.out.println(
//                         "SUCCESS: "
//                         + provider.getName()
//                     );

//                     return response;
//                 }

//                 System.out.println(
//                     "EMPTY RESPONSE FROM: "
//                     + provider.getName()
//                 );

//             } catch (Exception e) {

//                 System.out.println(
//                     "FAILED: "
//                     + provider.getName()
//                 );

//                 System.out.println(
//                     "ERROR MESSAGE:"
//                 );

//                 System.out.println(
//                     e.getMessage()
//                 );

//                 System.out.println(
//                     "FULL ERROR:"
//                 );

//                 e.printStackTrace();

//                 lastException = e;

//                 System.out.println(
//                     "Switching to next provider..."
//                 );
//             }
//         }

//         throw new RuntimeException(
//             "All AI providers failed.",
//             lastException
//         );
//     }

//     public List<String> getAvailableProviders() {

//         List<String> names =
//             new ArrayList<>();

//         for (AIProvider provider : providers) {

//             names.add(
//                 provider.getName()
//             );
//         }

//         return names;
//     }
// }

package com.sigma.ai;

import java.util.ArrayList;
import java.util.List;

public class AIManager {

    private final List<AIProvider> providers =
        new ArrayList<>();

    public AIManager() {

        if (isEnabled("AI_GEMINI_ENABLED")) {
            providers.add(new GeminiProvider());
        }

        if (isEnabled("AI_GROQ_ENABLED")) {
            providers.add(new GroqProvider());
        }

        if (isEnabled("AI_OPENROUTER_ENABLED")) {
            providers.add(new OpenRouterProvider());
        }

        if (isEnabled("AI_OPENAI_ENABLED")) {
            providers.add(new OpenAIProvider());
        }

        System.out.println(
            "AI Manager initialized"
        );

        System.out.println(
            "Enabled providers: "
            + providers.size()
        );

        for (AIProvider provider : providers) {

            System.out.println(
                " - "
                + provider.getName()
            );
        }
    }

    private boolean isEnabled(
            String key) {

        String value =
            AIConfig.get(key);

        return value != null &&
               value.equalsIgnoreCase("true");
    }

    public String ask(
            String systemPrompt,
            String userMessage) {

        Exception lastException = null;

        for (AIProvider provider : providers) {

            System.out.println(
                "\n================================"
            );

            System.out.println(
                "TRYING: "
                + provider.getName()
            );

            System.out.println(
                "================================"
            );

            try {

                String response =
                    provider.generateResponse(
                        systemPrompt,
                        userMessage
                    );

                if (
                    response != null &&
                    !response.isBlank()
                ) {

                    System.out.println(
                        "SUCCESS: "
                        + provider.getName()
                    );

                    return response;
                }

                System.out.println(
                    "EMPTY RESPONSE FROM: "
                    + provider.getName()
                );

            } catch (Exception e) {

                System.out.println(
                    "FAILED: "
                    + provider.getName()
                );

                System.out.println(
                    "ERROR: "
                    + e.getMessage()
                );

                lastException = e;

                System.out.println(
                    "Switching to next provider..."
                );
            }
        }

        throw new RuntimeException(
            "All enabled AI providers failed.",
            lastException
        );
    }

    public List<String> getAvailableProviders() {

        List<String> names =
            new ArrayList<>();

        for (AIProvider provider : providers) {

            names.add(
                provider.getName()
            );
        }

        return names;
    }
}
