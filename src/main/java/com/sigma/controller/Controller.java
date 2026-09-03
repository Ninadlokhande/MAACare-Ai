
package com.sigma.controller;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

import com.sigma.config.DoctorModule.FirebaseConfig;

public class Controller {

        // =============================================================
        // FIREBASE WEB API KEY
        // =============================================================

        private static final String API_KEY = "AIzaSyAhkH0AhllTx10IFjFA3VzbKvZSxIMA5bQ";

        // =============================================================
        // STATUS CODE
        // =============================================================

        public int status_code = 0;

        // =============================================================
        // LAST ERROR
        // =============================================================

        private String lastError = "";

        public String getLastError() {
                return lastError;
        }

        // =============================================================
        // FIREBASE UID
        // =============================================================

        private String firebaseUid = "";

        public String getFirebaseUid() {
                return firebaseUid;
        }

        public void setFirebaseUid(String firebaseUid) {

                this.firebaseUid = firebaseUid == null
                                ? ""
                                : firebaseUid.trim();

                // -----------------------------------------------------
                // ALSO STORE UID IN FIREBASE CONFIG
                // -----------------------------------------------------

                if (this.firebaseUid != null
                                && !this.firebaseUid.isEmpty()) {

                        FirebaseConfig.setCurrentDoctorUid(
                                        this.firebaseUid);

                        System.out.println(
                                        "[CONTROLLER] Firebase UID stored in FirebaseConfig: "
                                                        + this.firebaseUid);
                }
        }

        // =============================================================
        // HTTP CLIENT
        // =============================================================

        private final HttpClient client = HttpClient.newHttpClient();

        // =============================================================
        // SIGN UP
        // =============================================================

        public boolean signup(
                        String email,
                        String password) {

                status_code = 0;
                lastError = "";

                // -----------------------------------------------------
                // VALIDATION
                // -----------------------------------------------------

                if (email == null ||
                                email.trim().isEmpty()) {

                        lastError = "Please enter email.";
                        status_code = 400;

                        System.out.println(
                                        "[FIREBASE SIGNUP] "
                                                        + lastError);

                        return false;
                }

                if (password == null ||
                                password.isEmpty()) {

                        lastError = "Please enter password.";
                        status_code = 400;

                        System.out.println(
                                        "[FIREBASE SIGNUP] "
                                                        + lastError);

                        return false;
                }

                if (password.length() < 6) {

                        lastError = "Password must contain at least 6 characters.";

                        status_code = 400;

                        System.out.println(
                                        "[FIREBASE SIGNUP] "
                                                        + lastError);

                        return false;
                }

                // -----------------------------------------------------
                // PAYLOAD
                // -----------------------------------------------------

                JSONObject payload = new JSONObject();

                payload.put(
                                "email",
                                email.trim());

                payload.put(
                                "password",
                                password);

                payload.put(
                                "returnSecureToken",
                                true);

                // -----------------------------------------------------
                // FIREBASE SIGNUP URL
                // -----------------------------------------------------

                String url = "https://identitytoolkit.googleapis.com/v1/"
                                + "accounts:signUp?key="
                                + API_KEY;

                try {

                        URI uri = URI.create(url);

                        HttpRequest request = HttpRequest.newBuilder()
                                        .uri(uri)
                                        .header(
                                                        "Content-Type",
                                                        "application/json")
                                        .POST(
                                                        HttpRequest.BodyPublishers
                                                                        .ofString(
                                                                                        payload.toString()))
                                        .build();

                        System.out.println(
                                        "========================================");

                        System.out.println(
                                        "[FIREBASE SIGNUP] Sending request");

                        System.out.println(
                                        "[FIREBASE SIGNUP] Email: "
                                                        + email.trim());

                        HttpResponse<String> response = client.send(
                                        request,
                                        HttpResponse.BodyHandlers
                                                        .ofString());

                        status_code = response.statusCode();

                        System.out.println(
                                        "[FIREBASE SIGNUP] Status: "
                                                        + status_code);

                        System.out.println(
                                        "[FIREBASE SIGNUP] Response: "
                                                        + response.body());

                        // -------------------------------------------------
                        // SUCCESS
                        // -------------------------------------------------

                        if (status_code == 200) {

                                JSONObject result = new JSONObject(
                                                response.body());

                                String localId = result.optString(
                                                "localId",
                                                "");

                                System.out.println(
                                                "[FIREBASE SIGNUP] "
                                                                + "Account created successfully.");

                                System.out.println(
                                                "[FIREBASE SIGNUP] Firebase UID: "
                                                                + localId);

                                lastError = "";

                                return true;
                        }

                        // -------------------------------------------------
                        // ERROR
                        // -------------------------------------------------

                        handleFirebaseError(
                                        response.body(),
                                        "SIGNUP");

                        return false;

                } catch (Exception e) {

                        status_code = 500;

                        lastError = "Unable to connect to Firebase.";

                        System.out.println(
                                        "[FIREBASE SIGNUP] "
                                                        + "Connection error.");

                        e.printStackTrace();

                        return false;
                }
        }

        // =============================================================
        // SIGN IN
        // =============================================================

        public boolean signin(
                        String email,
                        String password) {

                status_code = 0;
                lastError = "";

                // -----------------------------------------------------
                // CLEAR PREVIOUS LOGIN UID
                // -----------------------------------------------------

                firebaseUid = "";

                FirebaseConfig.clearCurrentDoctorUid();

                // -----------------------------------------------------
                // VALIDATION
                // -----------------------------------------------------

                if (email == null ||
                                email.trim().isEmpty()) {

                        lastError = "Please enter email.";

                        status_code = 400;

                        System.out.println(
                                        "[FIREBASE LOGIN] "
                                                        + lastError);

                        return false;
                }

                if (password == null ||
                                password.isEmpty()) {

                        lastError = "Please enter password.";

                        status_code = 400;

                        System.out.println(
                                        "[FIREBASE LOGIN] "
                                                        + lastError);

                        return false;
                }

                // -----------------------------------------------------
                // PAYLOAD
                // -----------------------------------------------------

                JSONObject payload = new JSONObject();

                payload.put(
                                "email",
                                email.trim());

                payload.put(
                                "password",
                                password);

                payload.put(
                                "returnSecureToken",
                                true);

                // -----------------------------------------------------
                // FIREBASE LOGIN URL
                // -----------------------------------------------------

                String url = "https://identitytoolkit.googleapis.com/v1/"
                                + "accounts:signInWithPassword?key="
                                + API_KEY;

                try {

                        URI uri = URI.create(url);

                        HttpRequest request = HttpRequest.newBuilder()
                                        .uri(uri)
                                        .header(
                                                        "Content-Type",
                                                        "application/json")
                                        .POST(
                                                        HttpRequest.BodyPublishers
                                                                        .ofString(
                                                                                        payload.toString()))
                                        .build();

                        System.out.println(
                                        "========================================");

                        System.out.println(
                                        "[FIREBASE LOGIN] Sending request");

                        System.out.println(
                                        "[FIREBASE LOGIN] Email: "
                                                        + email.trim());

                        HttpResponse<String> response = client.send(
                                        request,
                                        HttpResponse.BodyHandlers
                                                        .ofString());

                        status_code = response.statusCode();

                        System.out.println(
                                        "[FIREBASE LOGIN] Status: "
                                                        + status_code);

                        System.out.println(
                                        "[FIREBASE LOGIN] Response: "
                                                        + response.body());

                        // -------------------------------------------------
                        // SUCCESS
                        // -------------------------------------------------

                        if (status_code == 200) {

                                JSONObject result = new JSONObject(
                                                response.body());

                                // -------------------------------------------------
                                // GET FIREBASE AUTHENTICATION UID
                                // -------------------------------------------------

                                String localId = result.optString(
                                                "localId",
                                                "");

                                // -------------------------------------------------
                                // CHECK UID
                                // -------------------------------------------------

                                if (localId == null ||
                                                localId.trim().isEmpty()) {

                                        lastError = "Firebase UID was not returned.";

                                        System.out.println(
                                                        "[FIREBASE LOGIN] ERROR: "
                                                                        + lastError);

                                        return false;
                                }

                                // -------------------------------------------------
                                // STORE UID
                                // -------------------------------------------------

                                setFirebaseUid(localId);

                                // -------------------------------------------------
                                // IMPORTANT:
                                // setFirebaseUid() automatically stores
                                // the same UID in FirebaseConfig.
                                // -------------------------------------------------

                                System.out.println(
                                                "[FIREBASE LOGIN] Login successful.");

                                System.out.println(
                                                "[FIREBASE LOGIN] Firebase UID stored: "
                                                                + getFirebaseUid());

                                System.out.println(
                                                "[FIREBASE LOGIN] FirebaseConfig UID: "
                                                                + FirebaseConfig
                                                                                .getCurrentDoctorUid());

                                lastError = "";

                                return true;
                        }

                        // -------------------------------------------------
                        // ERROR
                        // -------------------------------------------------

                        handleFirebaseError(
                                        response.body(),
                                        "LOGIN");

                        return false;

                } catch (Exception e) {

                        status_code = 500;

                        lastError = "Unable to connect to Firebase.";

                        System.out.println(
                                        "[FIREBASE LOGIN] "
                                                        + "Connection error.");

                        e.printStackTrace();

                        return false;
                }
        }

        // =============================================================
        // SIGN OUT / CLEAR SESSION
        // =============================================================

        public void logout() {

                firebaseUid = "";

                // -----------------------------------------------------
                // CLEAR GLOBAL DOCTOR UID
                // -----------------------------------------------------

                FirebaseConfig.clearCurrentDoctorUid();

                System.out.println(
                                "[FIREBASE LOGIN] Firebase UID cleared.");

                System.out.println(
                                "[FIREBASE LOGIN] FirebaseConfig UID cleared.");
        }

        // =============================================================
        // FORGOT PASSWORD / PASSWORD RESET
        // =============================================================

        public boolean resetPassword(
                        String email) {

                status_code = 0;
                lastError = "";

                // -----------------------------------------------------
                // VALIDATION
                // -----------------------------------------------------

                if (email == null ||
                                email.trim().isEmpty()) {

                        lastError = "Please enter your email address.";

                        status_code = 400;

                        System.out.println(
                                        "[FIREBASE PASSWORD RESET] "
                                                        + lastError);

                        return false;
                }

                String cleanEmail = email.trim();

                // -----------------------------------------------------
                // BASIC EMAIL VALIDATION
                // -----------------------------------------------------

                if (!cleanEmail.contains("@") ||
                                !cleanEmail.contains(".")) {

                        lastError = "Please enter a valid email address.";

                        status_code = 400;

                        System.out.println(
                                        "[FIREBASE PASSWORD RESET] "
                                                        + lastError);

                        return false;
                }

                // -----------------------------------------------------
                // PAYLOAD
                // -----------------------------------------------------

                JSONObject payload = new JSONObject();

                payload.put(
                                "requestType",
                                "PASSWORD_RESET");

                payload.put(
                                "email",
                                cleanEmail);

                // -----------------------------------------------------
                // FIREBASE PASSWORD RESET URL
                // -----------------------------------------------------

                String url = "https://identitytoolkit.googleapis.com/v1/"
                                + "accounts:sendOobCode?key="
                                + API_KEY;

                try {

                        URI uri = URI.create(url);

                        HttpRequest request = HttpRequest.newBuilder()
                                        .uri(uri)
                                        .header(
                                                        "Content-Type",
                                                        "application/json")
                                        .POST(
                                                        HttpRequest.BodyPublishers
                                                                        .ofString(
                                                                                        payload.toString()))
                                        .build();

                        System.out.println(
                                        "========================================");

                        System.out.println(
                                        "[FIREBASE PASSWORD RESET] "
                                                        + "Sending request");

                        System.out.println(
                                        "[FIREBASE PASSWORD RESET] Email: "
                                                        + cleanEmail);

                        HttpResponse<String> response = client.send(
                                        request,
                                        HttpResponse.BodyHandlers
                                                        .ofString());

                        status_code = response.statusCode();

                        System.out.println(
                                        "[FIREBASE PASSWORD RESET] Status: "
                                                        + status_code);

                        System.out.println(
                                        "[FIREBASE PASSWORD RESET] Response: "
                                                        + response.body());

                        // -------------------------------------------------
                        // SUCCESS
                        // -------------------------------------------------

                        if (status_code == 200) {

                                lastError = "";

                                System.out.println(
                                                "[FIREBASE PASSWORD RESET] "
                                                                + "Password reset email sent successfully.");

                                return true;
                        }

                        // -------------------------------------------------
                        // ERROR
                        // -------------------------------------------------

                        handleFirebaseError(
                                        response.body(),
                                        "PASSWORD_RESET");

                        return false;

                } catch (Exception e) {

                        status_code = 500;

                        lastError = "Unable to connect to Firebase.";

                        System.out.println(
                                        "[FIREBASE PASSWORD RESET] "
                                                        + "Connection error.");

                        e.printStackTrace();

                        return false;
                }
        }

        // =============================================================
        // FIREBASE ERROR HANDLER
        // =============================================================

        private void handleFirebaseError(
                        String responseBody,
                        String operation) {

                try {

                        JSONObject response = new JSONObject(
                                        responseBody);

                        JSONObject error = response.optJSONObject(
                                        "error");

                        if (error == null) {

                                lastError = "Unknown Firebase error.";

                                return;
                        }

                        String message = error.optString(
                                        "message",
                                        "UNKNOWN_ERROR");

                        System.out.println(
                                        "[FIREBASE ERROR] "
                                                        + message);

                        // -------------------------------------------------
                        // SIGNUP ERRORS
                        // -------------------------------------------------

                        if (message.equals(
                                        "EMAIL_EXISTS")) {

                                lastError = "Email already exists.";

                        } else if (message.equals(
                                        "INVALID_EMAIL")) {

                                lastError = "Invalid email address.";

                        } else if (message.equals(
                                        "WEAK_PASSWORD")) {

                                lastError = "Password is too weak.";
                        }

                        // -------------------------------------------------
                        // LOGIN ERRORS
                        // -------------------------------------------------

                        else if (message.equals(
                                        "INVALID_LOGIN_CREDENTIALS")) {

                                lastError = "Invalid email or password.";

                        } else if (message.equals(
                                        "EMAIL_NOT_FOUND")) {

                                lastError = "No account found with this email.";

                        } else if (message.equals(
                                        "INVALID_PASSWORD")) {

                                lastError = "Incorrect password.";
                        }

                        // -------------------------------------------------
                        // PASSWORD RESET ERRORS
                        // -------------------------------------------------

                        else if (message.equals(
                                        "USER_NOT_FOUND")) {

                                lastError = "No account found with this email.";

                        } else if (message.equals(
                                        "OPERATION_NOT_ALLOWED")) {

                                lastError = "Password reset is not enabled.";
                        }

                        // -------------------------------------------------
                        // OTHER
                        // -------------------------------------------------

                        else {

                                lastError = message;
                        }

                        System.out.println(
                                        "[FIREBASE "
                                                        + operation
                                                        + "] "
                                                        + lastError);

                } catch (Exception e) {

                        lastError = "Invalid Firebase response.";

                        System.out.println(
                                        "[FIREBASE ERROR] "
                                                        + "Could not parse response.");

                        e.printStackTrace();
                }
        }
}
