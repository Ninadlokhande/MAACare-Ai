package com.sigma.config;

import java.io.FileInputStream;
import java.io.InputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class FirebaseConfig {

    private static String currentDoctorUid;

    static {
        getFirebaseConfig();
    }

    private static void getFirebaseConfig() {

        try {

        FileInputStream serviceAccount =
            new FileInputStream("src/main/resources/serviceAccountKey.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .build();

            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Firestore getFirestore() {
        return FirestoreClient.getFirestore();
    }

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