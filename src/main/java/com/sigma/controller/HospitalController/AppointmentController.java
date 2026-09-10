
package com.sigma.controller.HospitalController;

import java.util.List;

import com.sigma.dao.AppointmentDao;
import com.sigma.model.Appointment;

public class AppointmentController {

    AppointmentDao dao = new AppointmentDao();

    NotificationController notificationController =
            new NotificationController();

    // =========================================================
    // EXISTING ADD APPOINTMENT
    // DO NOT REMOVE
    // =========================================================
    public void addAppointment(
            String number,
            String patient,
            String doctor,
            String date,
            String time,
            String department,
            String status) {

        Appointment appointment =
                new Appointment(
                        number,
                        patient,
                        doctor,
                        date,
                        time,
                        department,
                        status);

        dao.saveAppointment(appointment);

        notificationController.addNotification(
                number,
                "New appointment added for " + patient,
                "APPOINTMENT"
        );
    }

    // =========================================================
    // NEW - ADD HOSPITAL APPOINTMENT
    // =========================================================
    public void addHospitalAppointment(
            String number,
            String patient,
            String hospital,
            String date,
            String time,
            String department,
            String motherUid) {

        Appointment appointment =
                new Appointment(
                        number,
                        patient,
                        hospital,
                        date,
                        time,
                        department,
                        "Pending",
                        hospital,
                        motherUid,
                        "HOSPITAL"
                );

        dao.saveAppointment(appointment);

        notificationController.addNotification(
                number,
                "New hospital appointment request from " + patient,
                "APPOINTMENT"
        );
    }

    // =========================================================
    // NEW - ADD HOSPITAL APPOINTMENT
    // WITH EXTRA INFORMATION
    // =========================================================
    public void addHospitalAppointment(
            String number,
            String patient,
            String hospital,
            String date,
            String time,
            String department,
            String motherUid,
            String status) {

        Appointment appointment =
                new Appointment(
                        number,
                        patient,
                        hospital,
                        date,
                        time,
                        department,
                        status,
                        hospital,
                        motherUid,
                        "HOSPITAL"
                );

        dao.saveAppointment(appointment);

        notificationController.addNotification(
                number,
                "New hospital appointment request from " + patient,
                "APPOINTMENT"
        );
    }

    // =========================================================
    // GET ONE
    // =========================================================
    public Appointment getAppointment(String number) {
        return dao.getAppointment(number);
    }

    // =========================================================
    // EXISTING UPDATE
    // DO NOT REMOVE
    // =========================================================
    public void updateAppointment(
            String number,
            String patient,
            String doctor,
            String date,
            String time,
            String department,
            String status) {

        Appointment appointment =
                new Appointment(
                        number,
                        patient,
                        doctor,
                        date,
                        time,
                        department,
                        status);

        dao.updateAppointment(appointment);
    }

    // =========================================================
    // NEW - UPDATE ONLY STATUS
    // Hospital Admin Confirm / Reject
    // =========================================================
    public boolean updateAppointmentStatus(
            String number,
            String status) {

        boolean updated =
                dao.updateAppointmentStatus(
                        number,
                        status
                );

        if (updated) {

            Appointment appointment =
                    dao.getAppointment(number);

            if (appointment != null) {

                String patient =
                        appointment.getPatient();

                String message;

                if ("Confirmed".equalsIgnoreCase(status)) {

                    message =
                            "Your hospital appointment has been confirmed.";

                } else if ("Rejected".equalsIgnoreCase(status)
                        || "Cancelled".equalsIgnoreCase(status)) {

                    message =
                            "Your hospital appointment request was rejected.";

                } else {

                    message =
                            "Your appointment status is now "
                            + status;
                }

                notificationController.addNotification(
                        number,
                        message,
                        "APPOINTMENT"
                );
            }
        }

        return updated;
    }

    // =========================================================
    // GET MOTHER APPOINTMENTS
    // =========================================================
    public List<Appointment> getAppointmentsByMother(
            String motherUid) {

        return dao.getAppointmentsByMother(motherUid);
    }

    // =========================================================
    // DELETE
    // =========================================================
    public void deleteAppointment(String number) {
        dao.deleteAppointment(number);
    }

    // =========================================================
    // GET ALL
    // =========================================================
    public List<Appointment> getAllAppointments() {
        return dao.getAppointments();
    }
}

