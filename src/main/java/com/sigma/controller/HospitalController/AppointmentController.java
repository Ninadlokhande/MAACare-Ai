package com.sigma.controller.HospitalController;


import java.util.List;

import com.sigma.dao.AppointmentDao;
import com.sigma.model.Appointment;
import com.sigma.controller.HospitalController.NotificationController;

public class AppointmentController {

    AppointmentDao dao = new AppointmentDao();

NotificationController notificationController =
        new NotificationController();


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


    public Appointment getAppointment(String number) {
        return dao.getAppointment(number);
    }

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

    public void deleteAppointment(String number) {
        dao.deleteAppointment(number);
    }

    public List<Appointment> getAllAppointments() {
        return dao.getAppointments();
    }
}