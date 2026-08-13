package com.sigma.controller;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

public class Controller {

    private String API_KEY = "AIzaSyCMwA_qdCY2hTWCaV0EzStXGM3Hh8gpLEA";
    public int status_code;

    public boolean signup(String email, String password) {
        JSONObject payload = new JSONObject()
                .put("email", email)
                .put("password", password);

        try {
            HttpClient client = HttpClient.newHttpClient();

            URI uri = URI.create("https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=" + API_KEY);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(payload.toString()))
                    .build();
            System.out.println(request);

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response);
            System.out.println(response.statusCode());
            status_code = response.statusCode();
            System.out.println(response.body());
            if (response.statusCode() == 200) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean signin(String email, String password) {
        JSONObject payload = new JSONObject()
                .put("email", email)
                .put("password", password);

        try {
            HttpClient client = HttpClient.newHttpClient();

            URI uri = URI
                    .create("https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + API_KEY);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(payload.toString()))
                    .build();
            System.out.println(request);

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response);
            System.out.println(response.statusCode());
            status_code = response.statusCode();
            System.out.println(response.body());
            if (response.statusCode() == 200) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
