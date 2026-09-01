


package com.sigma.dao;

import java.util.ArrayList;
import java.util.List;

import com.sigma.config.FirebaseConfig;
import com.sigma.model.Labrecords;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

public class LabrecordsDao {

    private Firestore db = FirebaseConfig.getFirestore();

    // =====================================================
    // SAVE
    // =====================================================

    public void saveLabrecord(Labrecords labrecord) {
        try {

            db.collection("labrecords")
              .document(labrecord.getNumber())
              .create(labrecord)
              .get();

            System.out.println("Lab Record Data Inserted");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // =====================================================
    // GET ONE
    // =====================================================

    public Labrecords getLabrecord(String number) {
        try {

            ApiFuture<DocumentSnapshot> future =
                    db.collection("labrecords")
                      .document(number)
                      .get();

            DocumentSnapshot document = future.get();

            if (document.exists()) {
                return document.toObject(Labrecords.class);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // =====================================================
    // UPDATE
    // =====================================================

    public void updateLabrecord(Labrecords labrecord) {
        try {

            db.collection("labrecords")
              .document(labrecord.getNumber())
              .set(labrecord)
              .get();

            System.out.println("Lab Record Data Updated");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // =====================================================
    // DELETE
    // =====================================================

    public void deleteLabrecord(String number) {
        try {

            db.collection("labrecords")
              .document(number)
              .delete()
              .get();

            System.out.println("Lab Record Data Deleted");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // =====================================================
    // GET ALL
    // =====================================================

    public List<Labrecords> getLabrecords() {

        List<Labrecords> list = new ArrayList<>();

        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection("labrecords").get();

            QuerySnapshot snapshot = future.get();

            for (DocumentSnapshot doc : snapshot.getDocuments()) {

                Labrecords labrecord =
                        doc.toObject(Labrecords.class);

                if (labrecord != null) {
                    list.add(labrecord);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}