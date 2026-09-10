package com.sigma.dao;

import java.util.ArrayList;
import java.util.List;

import com.sigma.config.FirebaseConfig;
import com.sigma.model.Appointment;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

public class AppointmentDao {

    private Firestore db =
            FirebaseConfig.getFirestore();

    // =========================================================
    // SAVE APPOINTMENT
    // =========================================================

    public void saveAppointment(
            Appointment appointment) {

        try {

            if (appointment == null) {

                System.out.println(
                        "Appointment Save Failed: Appointment is null"
                );

                return;
            }

            String number =
                    appointment.getNumber();

            if (number == null
                    || number.trim().isEmpty()) {

                System.out.println(
                        "Appointment Save Failed: Empty appointment number"
                );

                return;
            }

            db.collection("appointments")
              .document(number.trim())
              .create(appointment)
              .get();

            System.out.println(
                    "Appointment Data Inserted"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =========================================================
    // GET SINGLE APPOINTMENT
    // =========================================================

    public Appointment getAppointment(
            String number) {

        if (number == null
                || number.trim().isEmpty()) {

            System.out.println(
                    "Get Appointment Failed: Empty appointment number"
            );

            return null;
        }

        try {

            ApiFuture<DocumentSnapshot> future =
                    db.collection("appointments")
                      .document(number.trim())
                      .get();

            DocumentSnapshot document =
                    future.get();

            if (document.exists()) {

                Appointment appointment =
                        document.toObject(
                                Appointment.class
                        );

                if (appointment != null) {

                    /*
                     * Some old Firestore documents may not
                     * contain the "number" field.
                     *
                     * In that case use Firestore document ID.
                     */
                    if (appointment.getNumber() == null
                            || appointment.getNumber()
                                    .trim()
                                    .isEmpty()) {

                        appointment.setNumber(
                                document.getId()
                        );
                    }

                }

                return appointment;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    // =========================================================
    // UPDATE APPOINTMENT
    // =========================================================

    public void updateAppointment(
            Appointment appointment) {

        try {

            if (appointment == null) {

                System.out.println(
                        "Appointment Update Failed: Appointment is null"
                );

                return;
            }

            String number =
                    appointment.getNumber();

            if (number == null
                    || number.trim().isEmpty()) {

                System.out.println(
                        "Appointment Update Failed: Empty appointment number"
                );

                return;
            }

            db.collection("appointments")
              .document(number.trim())
              .set(appointment)
              .get();

            System.out.println(
                    "Appointment Data Updated"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =========================================================
    // UPDATE APPOINTMENT STATUS
    // =========================================================

    public boolean updateAppointmentStatus(
            String number,
            String status) {

        if (number == null
                || number.trim().isEmpty()) {

            System.out.println(
                    "Status Update Failed: Empty appointment number"
            );

            return false;
        }

        if (status == null
                || status.trim().isEmpty()) {

            System.out.println(
                    "Status Update Failed: Empty status"
            );

            return false;
        }

        try {

            db.collection("appointments")
              .document(number.trim())
              .update(
                      "status",
                      status.trim()
              )
              .get();

            System.out.println(
                    "Appointment Status Updated: "
                    + number
                    + " -> "
                    + status
            );

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // GET APPOINTMENTS BY MOTHER
    // =========================================================

    public List<Appointment> getAppointmentsByMother(
            String motherUid) {

        List<Appointment> list =
                new ArrayList<>();

        if (motherUid == null
                || motherUid.trim().isEmpty()) {

            return list;
        }

        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection("appointments")
                      .whereEqualTo(
                              "motherUid",
                              motherUid.trim()
                      )
                      .get();

            QuerySnapshot snapshot =
                    future.get();

            for (DocumentSnapshot doc :
                    snapshot.getDocuments()) {

                Appointment appointment =
                        doc.toObject(
                                Appointment.class
                        );

                if (appointment != null) {

                    /*
                     * IMPORTANT:
                     *
                     * Old Firestore appointment documents
                     * may not have the "number" field.
                     *
                     * Use Firestore document ID as fallback.
                     */
                    if (appointment.getNumber() == null
                            || appointment.getNumber()
                                    .trim()
                                    .isEmpty()) {

                        appointment.setNumber(
                                doc.getId()
                        );
                    }

                    list.add(appointment);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    // =========================================================
    // GET ALL APPOINTMENTS
    // =========================================================

    public List<Appointment> getAppointments() {

        List<Appointment> list =
                new ArrayList<>();

        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection("appointments")
                      .get();

            QuerySnapshot snapshot =
                    future.get();

            for (DocumentSnapshot doc :
                    snapshot.getDocuments()) {

                Appointment appointment =
                        doc.toObject(
                                Appointment.class
                        );

                if (appointment != null) {

                    /*
                     * IMPORTANT FIX
                     *
                     * If "number" is missing/null/empty
                     * in Firestore, use the document ID.
                     *
                     * This fixes Delete / Edit /
                     * Confirm / Reject for old records.
                     */
                    if (appointment.getNumber() == null
                            || appointment.getNumber()
                                    .trim()
                                    .isEmpty()) {

                        appointment.setNumber(
                                doc.getId()
                        );
                    }

                    list.add(appointment);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    // =========================================================
    // DELETE APPOINTMENT
    // =========================================================

    public void deleteAppointment(
            String number) {

        /*
         * Prevent Firestore:
         *
         * IllegalArgumentException:
         * 'path' must be a non-empty String
         */
        if (number == null
                || number.trim().isEmpty()) {

            System.out.println(
                    "Appointment Delete Failed: "
                    + "Empty appointment number"
            );

            return;
        }

        try {

            String appointmentNumber =
                    number.trim();

            db.collection("appointments")
              .document(appointmentNumber)
              .delete()
              .get();

            System.out.println(
                    "Appointment Data Deleted: "
                    + appointmentNumber
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}