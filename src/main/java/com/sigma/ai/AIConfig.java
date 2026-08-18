package com.sigma.ai;

import io.github.cdimascio.dotenv.Dotenv;

public class AIConfig {

    private static final Dotenv dotenv =
        Dotenv.configure()
            .ignoreIfMissing()
            .load();

    public static String get(String key) {

        String value =
            dotenv.get(key);

        if (value == null ||
            value.isBlank()) {

            value =
                System.getenv(key);
        }

        return value;
    }

    public static String getRequired(String key) {

        String value =
            get(key);

        if (value == null ||
            value.isBlank()) {

            throw new RuntimeException(
                "Missing configuration: "
                + key
            );
        }

        return value;
    }
}