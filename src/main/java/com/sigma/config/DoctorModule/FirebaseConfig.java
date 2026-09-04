package com.sigma.config.DoctorModule;

import java.io.File;
import java.io.InputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class FirebaseConfig {

        // =========================================================
        // FIRESTORE
        // =========================================================

        private static Firestore firestore;

        // =========================================================
        // CURRENT DOCTOR UID
        // =========================================================

        private static String currentDoctorUid;

        // =========================================================
        // INITIALIZE FIREBASE
        // =========================================================

        public static synchronized void initialize() {

                try {

                        // -------------------------------------------------
                        // ALREADY INITIALIZED
                        // -------------------------------------------------

                        if (firestore != null) {
                                return;
                        }

                        System.out.println(
                                        "======================================");

                        System.out.println(
                                        "[FIREBASE] Checking serviceAccountKey.json");

                        System.out.println(
                                        "[FIREBASE] Working Directory: "
                                                        + System.getProperty("user.dir"));

                        // -------------------------------------------------
                        // FILE CHECK
                        // -------------------------------------------------

                        File file = new File(
                                        "src/main/resources/serviceAccountKey.json");

                        System.out.println(
                                        "[FIREBASE] File path: "
                                                        + file.getAbsolutePath());

                        System.out.println(
                                        "[FIREBASE] File exists: "
                                                        + file.exists());

                        System.out.println(
                                        "[FIREBASE] File readable: "
                                                        + file.canRead());

                        System.out.println(
                                        "======================================");

                        // -------------------------------------------------
                        // LOAD SERVICE ACCOUNT
                        // -------------------------------------------------

                        InputStream serviceAccount = FirebaseConfig.class
                                        .getClassLoader()
                                        .getResourceAsStream(
                                                        "serviceAccountKey.json");

                        if (serviceAccount == null) {

                                throw new RuntimeException(
                                                "serviceAccountKey.json exists in project, "
                                                                + "but is NOT available in runtime classpath.\n\n"
                                                                + "Check that it is inside:\n"
                                                                + "src/main/resources/serviceAccountKey.json");
                        }

                        // -------------------------------------------------
                        // FIREBASE OPTIONS
                        // -------------------------------------------------

                        FirebaseOptions options = FirebaseOptions.builder()
                                        .setCredentials(
                                                        GoogleCredentials.fromStream(
                                                                        serviceAccount))
                                        .build();

                        // -------------------------------------------------
                        // INITIALIZE FIREBASE APP
                        // -------------------------------------------------

                        if (FirebaseApp.getApps().isEmpty()) {

                                FirebaseApp.initializeApp(options);

                                System.out.println(
                                                "[FIREBASE] Firebase App initialized successfully.");
                        }

                        // -------------------------------------------------
                        // FIRESTORE
                        // -------------------------------------------------

                        firestore = FirestoreClient.getFirestore();

                        System.out.println(
                                        "[FIREBASE] Firestore initialized successfully.");

                } catch (Exception e) {

                        System.out.println(
                                        "[FIREBASE] Firebase initialization failed.");

                        e.printStackTrace();

                        throw new RuntimeException(
                                        "Failed to initialize Firebase.",
                                        e);
                }
        }

        // =========================================================
        // GET FIRESTORE
        // =========================================================

        public static synchronized Firestore getFirestore() {

                if (firestore == null) {
                        initialize();
                }

                return firestore;
        }

        // =========================================================
        // SET CURRENT DOCTOR UID
        // =========================================================

        public static synchronized void setCurrentDoctorUid(
                        String uid) {

                currentDoctorUid = uid;

                System.out.println(
                                "[FIREBASE] Current Doctor UID = "
                                                + currentDoctorUid);
        }

        // =========================================================
        // GET CURRENT DOCTOR UID
        // =========================================================

        public static synchronized String getCurrentDoctorUid() {

                return currentDoctorUid;
        }

        // =========================================================
        // CLEAR CURRENT DOCTOR UID
        // =========================================================

        public static synchronized void clearCurrentDoctorUid() {

                currentDoctorUid = null;

                System.out.println(
                                "[FIREBASE] Current Doctor UID cleared.");
        }
}