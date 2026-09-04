package com.sigma.controller.HospitalController;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.sigma.config.CloudinaryDocumentUploader;
import com.sigma.dao.LabrecordsDao;
import com.sigma.model.Labrecords;

public class LabrecordsController {

    // =========================================================
    // DAO
    // =========================================================

    private final LabrecordsDao dao =
            new LabrecordsDao();


    // =========================================================
    // NOTIFICATION CONTROLLER
    // =========================================================

    private final NotificationController notificationController =
            new NotificationController();


    // =========================================================
    // ADD LAB RECORD
    // =========================================================

    public void addLabrecord(
            String number,
            String patientName,
            String testName,
            String department,
            String date,
            String status,
            String results,
            String documentUrl) {

        Labrecords labrecord =
                new Labrecords(
                        number,
                        patientName,
                        testName,
                        department,
                        date,
                        status,
                        results,
                        documentUrl
                );


        // Save record to Firebase
        dao.saveLabrecord(labrecord);


        // Add notification
        notificationController.addNotification(
                number,
                "New lab record added for " + patientName,
                "LAB_RECORD"
        );
    }


    // =========================================================
    // UPLOAD LAB REPORT DOCUMENT
    // =========================================================

    public String uploadLabReportDocument(
            File file
    ) throws IOException {

        if (file == null) {

            throw new IOException(
                    "No document selected."
            );
        }


        return CloudinaryDocumentUploader
                .uploadDocument(file);
    }


    // =========================================================
    // GET SINGLE LAB RECORD
    // =========================================================

    public Labrecords getLabrecord(
            String number
    ) {

        return dao.getLabrecord(number);
    }


    // =========================================================
    // UPDATE LAB RECORD
    // =========================================================

    public void updateLabrecord(
            String number,
            String patientName,
            String testName,
            String department,
            String date,
            String status,
            String results,
            String documentUrl) {

        Labrecords labrecord =
                new Labrecords(
                        number,
                        patientName,
                        testName,
                        department,
                        date,
                        status,
                        results,
                        documentUrl
                );


        // Update record in Firebase
        dao.updateLabrecord(labrecord);
    }


    // =========================================================
    // DELETE LAB RECORD
    // =========================================================

    public void deleteLabrecord(
            String number
    ) {

        dao.deleteLabrecord(number);
    }


    // =========================================================
    // GET ALL LAB RECORDS
    // =========================================================

    public List<Labrecords> getAllLabrecords() {

        return dao.getLabrecords();
    }
}