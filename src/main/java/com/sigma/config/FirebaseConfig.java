
package com.sigma.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.IOException;
import java.io.InputStream;

public class FirebaseConfig {

    private static FirebaseApp firebaseApp;

    // =====================================================
    // SERVICE ACCOUNT FILE
    // =====================================================

    private static final String SERVICE_ACCOUNT_FILE = "serviceAccountKey.json";

    // =====================================================
    // INITIALIZE FIREBASE
    // =====================================================

    public static synchronized void initializeFirebase() {

        try {

            // -------------------------------------------------
            // CHECK IF FIREBASE IS ALREADY INITIALIZED
            // -------------------------------------------------

            if (!FirebaseApp.getApps().isEmpty()) {

                firebaseApp = FirebaseApp.getInstance();

                System.out.println(
                        "Firebase already initialized!");

                return;
            }

            // -------------------------------------------------
            // LOAD JSON FROM src/main/resources
            // -------------------------------------------------

            System.out.println(
                    "Looking for Firebase service account file:");

            System.out.println(
                    "src/main/resources/"
                            + SERVICE_ACCOUNT_FILE);

            InputStream serviceAccount = FirebaseConfig.class
                    .getClassLoader()
                    .getResourceAsStream(
                            SERVICE_ACCOUNT_FILE);

            // -------------------------------------------------
            // FILE NOT FOUND
            // -------------------------------------------------

            if (serviceAccount == null) {

                System.out.println(
                        "Firebase service account file NOT found!");

                System.out.println(
                        "Expected location:");

                System.out.println(
                        "src/main/resources/"
                                + SERVICE_ACCOUNT_FILE);

                throw new IOException(
                        "Service account JSON file not found.");
            }

            System.out.println(
                    "Firebase service account file found.");

            // -------------------------------------------------
            // FIREBASE OPTIONS
            // -------------------------------------------------

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(
                            GoogleCredentials.fromStream(
                                    serviceAccount))
                    .build();

            // -------------------------------------------------
            // INITIALIZE FIREBASE
            // -------------------------------------------------

            firebaseApp = FirebaseApp.initializeApp(options);

            serviceAccount.close();

            System.out.println(
                    "Firebase initialized successfully!");

        } catch (IOException e) {

            firebaseApp = null;

            System.out.println(
                    "Firebase initialization failed!");

            e.printStackTrace();

            throw new IllegalStateException(
                    "Firebase initialization failed.",
                    e);
        }
    }

    // =====================================================
    // GET FIREBASE APP
    // =====================================================

    public static FirebaseApp getFirebaseApp() {

        if (firebaseApp == null) {

            initializeFirebase();
        }

        return firebaseApp;
    }

    // =====================================================
    // CHECK INITIALIZATION
    // =====================================================

    public static boolean isInitialized() {

        return firebaseApp != null;
    }
}
