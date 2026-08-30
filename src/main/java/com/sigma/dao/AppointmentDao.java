
package com.sigma.dao;

import java.util.ArrayList;
import java.util.List;

import com.sigma.config.FirebaseConfig;
import com.sigma.model.Appointment;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

public class AppointmentDao {

    private Firestore db = FirebaseConfig.getFirestore();

    // SAVE
    public void saveAppointment(Appointment appointment) {
        try {

            db.collection("appointments")
              .document(appointment.getNumber())
              .create(appointment)
              .get();

            System.out.println("Appointment Data Inserted");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // GET ONE
    public Appointment getAppointment(String number) {
        try {

            ApiFuture<DocumentSnapshot> future =
                    db.collection("appointments")
                      .document(number)
                      .get();

            DocumentSnapshot document = future.get();

            if (document.exists()) {
                return document.toObject(Appointment.class);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // UPDATE
    public void updateAppointment(Appointment appointment) {
        try {

            db.collection("appointments")
              .document(appointment.getNumber())
              .set(appointment)
              .get();

            System.out.println("Appointment Data Updated");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteAppointment(String number) {
        try {

            db.collection("appointments")
              .document(number)
              .delete()
              .get();

            System.out.println("Appointment Data Deleted");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // GET ALL
    public List<Appointment> getAppointments() {

        List<Appointment> list = new ArrayList<>();

        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection("appointments").get();

            QuerySnapshot snapshot = future.get();

            for (DocumentSnapshot doc : snapshot.getDocuments()) {

                Appointment appointment =
                        doc.toObject(Appointment.class);

                if (appointment != null) {
                    list.add(appointment);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}