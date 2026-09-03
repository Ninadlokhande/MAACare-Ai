
package com.sigma.controller.HospitalController;

import java.util.List;

import com.sigma.dao.LabrecordsDao;
import com.sigma.model.Labrecords;
import com.sigma.controller.HospitalController.NotificationController;

public class LabrecordsController {

    LabrecordsDao dao = new LabrecordsDao();

    NotificationController notificationController =
        new NotificationController();

    public void addLabrecord(
            String number,
            String patientName,
            String testName,
            String department,
            String date,
            String status,
            String results) {

        Labrecords labrecord =
                new Labrecords(
                        number,
                        patientName,
                        testName,
                        department,
                        date,
                        status,
                        results);

        dao.saveLabrecord(labrecord);
notificationController.addNotification(
        number,
        "New lab record added for " + patientName,
        "LAB_RECORD"
);

    }

    public Labrecords getLabrecord(String number) {
        return dao.getLabrecord(number);
    }

    public void updateLabrecord(
            String number,
            String patientName,
            String testName,
            String department,
            String date,
            String status,
            String results) {

        Labrecords labrecord =
                new Labrecords(
                        number,
                        patientName,
                        testName,
                        department,
                        date,
                        status,
                        results);

        dao.updateLabrecord(labrecord);
    }

    public void deleteLabrecord(String number) {
        dao.deleteLabrecord(number);
    }

    public List<Labrecords> getAllLabrecords() {
        return dao.getLabrecords();
    }
}