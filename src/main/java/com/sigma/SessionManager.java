package com.sigma;

public class SessionManager {

    private static String ashaId;

    public static void setAshaId(String id) {
        ashaId = id;
    }

    public static String getAshaId() {
        return ashaId;
    }

    public static boolean isLoggedIn() {
        return ashaId != null && !ashaId.isEmpty();
    }

    public static void logout() {
        ashaId = null;
    }
}
