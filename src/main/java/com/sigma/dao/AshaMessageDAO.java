package com.sigma.dao;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.sigma.config.FirebaseConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

public class AshaMessageDAO {

    private final Firestore db =
            FirebaseConfig.getFirestore();

    private static final String COLLECTION =
            "AshaMessages";


    // =========================================================
    // SAVE MESSAGE
    // =========================================================

    public boolean saveMessage(
            String beneficiaryName,
            String message) {

        try {

            if (beneficiaryName == null ||
                    beneficiaryName.trim().isEmpty()) {

                System.out.println(
                        "ERROR: Beneficiary name is empty"
                );

                return false;
            }

            if (message == null ||
                    message.trim().isEmpty()) {

                System.out.println(
                        "ERROR: Message is empty"
                );

                return false;
            }


            Map<String, Object> data =
                    new HashMap<>();

            data.put(
                    "beneficiaryName",
                    beneficiaryName.trim()
            );

            data.put(
                    "message",
                    message.trim()
            );

            data.put(
                    "status",
                    "Sent"
            );

            data.put(
                    "sentAt",
                    System.currentTimeMillis()
            );
            /*public List<Map<String, Object>> getMessages() {

    List<Map<String, Object>> messages =
            new ArrayList<>();

    try {

        QuerySnapshot snapshot =
                db.collection(COLLECTION)
                  .get()
                  .get();

        for (DocumentSnapshot doc :
                snapshot.getDocuments()) {

            Map<String, Object> data =
                    doc.getData();

            if (data != null) {
                messages.add(data);
            }
        }

    } catch (Exception e) {

        System.out.println(
                "ERROR: Messages fetch failed"
        );

        e.printStackTrace();
    }

    return messages;
}*/


            // =================================================
            // FIRESTORE SAVE
            // =================================================

            db.collection(COLLECTION)
                    .add(data)
                    .get();


            System.out.println(
                    "================================"
            );

            System.out.println(
                    "MESSAGE SAVED SUCCESSFULLY"
            );

            System.out.println(
                    "Beneficiary: "
                            + beneficiaryName
            );

            System.out.println(
                    "Message: "
                            + message
            );

            System.out.println(
                    "================================"
            );

            return true;


        } catch (Exception e) {

            System.out.println(
                    "================================"
            );

            System.out.println(
                    "MESSAGE SAVE FAILED"
            );

            e.printStackTrace();

            System.out.println(
                    "================================"
            );

            return false;
        }
}
        public List<Map<String, Object>> getMessages() {

    List<Map<String, Object>> messages =
            new ArrayList<>();

    try {

        QuerySnapshot snapshot =
                db.collection(COLLECTION)
                  .get()
                  .get();

        for (DocumentSnapshot doc :
                snapshot.getDocuments()) {

            Map<String, Object> data =
                    doc.getData();

            if (data != null) {
                messages.add(data);
            }
        }

    } catch (Exception e) {

        System.out.println(
                "ERROR: Messages fetch failed"
        );

        e.printStackTrace();
    }

    return messages;
}
    }
