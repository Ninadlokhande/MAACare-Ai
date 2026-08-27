package com.sigma.dao;

import com.sigma.model.DoctorModel.PatientReport;

import java.util.ArrayList;
import java.util.List;

public class PatientReportDAO {

    // =====================================================
    // HARD-CODED REPORT DATA
    // =====================================================

    public List<PatientReport> getAllReports() {

        List<PatientReport> reports = new ArrayList<>();

        reports.add(
                new PatientReport(
                        "CBC Blood Test",
                        "Priya Sharma",
                        "Blood Test",
                        "07 May 2024",
                        "Normal",
                        "View"));

        reports.add(
                new PatientReport(
                        "Pregnancy Ultrasound",
                        "Neha Kulkarni",
                        "Ultrasound",
                        "05 May 2024",
                        "Normal",
                        "View"));

        reports.add(
                new PatientReport(
                        "Urine Test",
                        "Sneha Patil",
                        "Urine Test",
                        "04 May 2024",
                        "Normal",
                        "View"));

        reports.add(
                new PatientReport(
                        "Thyroid Profile",
                        "Ayesha Khan",
                        "Thyroid Profile",
                        "03 May 2024",
                        "Abnormal",
                        "View"));

        reports.add(
                new PatientReport(
                        "Vitamin D Report",
                        "Ritika Singh",
                        "Vitamin D Test",
                        "01 May 2024",
                        "Low",
                        "View"));

        return reports;
    }

    // =====================================================
    // ADD REPORT
    // =====================================================

    public void addReport(PatientReport report) {

        if (report == null) {
            return;
        }

        System.out.println("Report added successfully.");

        System.out.println(
                "Report Name: " +
                        report.getReportName());

        System.out.println(
                "Patient: " +
                        report.getPatientName());

        System.out.println(
                "Report Type: " +
                        report.getReportType());

        System.out.println(
                "Date: " +
                        report.getDate());

        System.out.println(
                "Status: " +
                        report.getStatus());

        System.out.println(
                "File / Action: " +
                        report.getAction());
    }

    // =====================================================
    // DELETE REPORT
    // =====================================================

    public void deleteReport(PatientReport report) {

        if (report == null) {
            return;
        }

        System.out.println(
                "Deleting report: " +
                        report.getReportName());
    }
}