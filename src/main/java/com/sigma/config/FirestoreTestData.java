
package com.sigma.config;

import java.util.HashMap;
import java.util.Map;

public class FirestoreTestData {

        public static void main(String[] args) {

                System.out.println("========================================");
                System.out.println("Adding MaaCareAI test data to Firestore");
                System.out.println("========================================");

                try {

                        FirestoreService firestore = new FirestoreService();

                        // =====================================================
                        // 1. APPOINTMENTS
                        // =====================================================

                        addAppointment(
                                        firestore,
                                        "09:30 AM",
                                        "Priya Sharma",
                                        "Consultation",
                                        "Confirmed",
                                        "Paid");

                        addAppointment(
                                        firestore,
                                        "10:15 AM",
                                        "Neha Kulkarni",
                                        "Routine Checkup",
                                        "Confirmed",
                                        "Paid");

                        addAppointment(
                                        firestore,
                                        "11:00 AM",
                                        "Sneha Patil",
                                        "First Consultation",
                                        "Confirmed",
                                        "Pending");

                        addAppointment(
                                        firestore,
                                        "12:00 PM",
                                        "Ritika Singh",
                                        "Ultrasound Follow-up",
                                        "Pending",
                                        "Paid");

                        addAppointment(
                                        firestore,
                                        "01:00 PM",
                                        "Ayesha Khan",
                                        "Pregnancy Checkup",
                                        "Confirmed",
                                        "Paid");

                        // =====================================================
                        // 2. PATIENTS
                        // =====================================================

                        addPatient(
                                        firestore,
                                        "P001",
                                        "Priya Sharma",
                                        "9876543210",
                                        "priya@gmail.com",
                                        "28",
                                        "24 weeks",
                                        "Normal");

                        addPatient(
                                        firestore,
                                        "P002",
                                        "Neha Kulkarni",
                                        "9876543211",
                                        "neha@gmail.com",
                                        "30",
                                        "20 weeks",
                                        "Normal");

                        addPatient(
                                        firestore,
                                        "P003",
                                        "Sneha Patil",
                                        "9876543212",
                                        "sneha@gmail.com",
                                        "26",
                                        "16 weeks",
                                        "Normal");

                        addPatient(
                                        firestore,
                                        "P004",
                                        "Ritika Singh",
                                        "9876543213",
                                        "ritika@gmail.com",
                                        "29",
                                        "28 weeks",
                                        "High Risk");

                        // =====================================================
                        // 3. REPORTS
                        // =====================================================

                        addReport(
                                        firestore,
                                        "P001",
                                        "Priya Sharma",
                                        "Blood Test",
                                        "Normal",
                                        "Hemoglobin level normal",
                                        "2026-08-29");

                        addReport(
                                        firestore,
                                        "P002",
                                        "Neha Kulkarni",
                                        "Ultrasound",
                                        "Normal",
                                        "Fetal growth normal",
                                        "2026-08-29");

                        addReport(
                                        firestore,
                                        "P003",
                                        "Sneha Patil",
                                        "Blood Test",
                                        "Normal",
                                        "All parameters within normal range",
                                        "2026-08-29");

                        // =====================================================
                        // 4. PRESCRIPTIONS
                        // =====================================================

                        addPrescription(
                                        firestore,
                                        "P001",
                                        "Priya Sharma",
                                        "Iron Tablet",
                                        "1 tablet",
                                        "Once daily",
                                        "After lunch");

                        addPrescription(
                                        firestore,
                                        "P002",
                                        "Neha Kulkarni",
                                        "Folic Acid",
                                        "1 tablet",
                                        "Once daily",
                                        "After breakfast");

                        // =====================================================
                        // 5. DOCTOR PROFILE
                        // =====================================================

                        Map<String, Object> doctorProfile = new HashMap<>();

                        doctorProfile.put("doctorId", "D001");
                        doctorProfile.put("name", "Dr. Anjali Deshmukh");
                        doctorProfile.put("specialization", "Gynecologist");
                        doctorProfile.put("phone", "9876500000");
                        doctorProfile.put("email", "doctor@maacareai.com");
                        doctorProfile.put("experience", "8 years");
                        doctorProfile.put("hospital", "MaaCareAI Hospital");

                        String doctorId = firestore.addDocument(
                                        "doctorProfiles",
                                        doctorProfile);

                        System.out.println(
                                        "Doctor profile added: " + doctorId);

                        System.out.println();
                        System.out.println("========================================");
                        System.out.println("ALL TEST DATA ADDED SUCCESSFULLY!");
                        System.out.println("========================================");

                } catch (Exception e) {

                        System.out.println(
                                        "Failed to add test data.");

                        e.printStackTrace();
                }
        }

        // =========================================================
        // ADD APPOINTMENT
        // =========================================================

        private static void addAppointment(
                        FirestoreService firestore,
                        String time,
                        String patient,
                        String type,
                        String status,
                        String payment) {

                Map<String, Object> data = new HashMap<>();

                data.put("time", time);
                data.put("patient", patient);
                data.put("type", type);
                data.put("status", status);
                data.put("payment", payment);

                String id = firestore.addDocument(
                                "appointments",
                                data);

                System.out.println(
                                "Appointment added: " + id);
        }

        // =========================================================
        // ADD PATIENT
        // =========================================================

        private static void addPatient(
                        FirestoreService firestore,
                        String patientId,
                        String name,
                        String phone,
                        String email,
                        String age,
                        String pregnancyWeek,
                        String riskLevel) {

                Map<String, Object> data = new HashMap<>();

                data.put("patientId", patientId);
                data.put("name", name);
                data.put("phone", phone);
                data.put("email", email);
                data.put("age", age);
                data.put("pregnancyWeek", pregnancyWeek);
                data.put("riskLevel", riskLevel);

                String id = firestore.addDocument(
                                "patients",
                                data);

                System.out.println(
                                "Patient added: " + id);
        }

        // =========================================================
        // ADD REPORT
        // =========================================================

        private static void addReport(
                        FirestoreService firestore,
                        String patientId,
                        String patientName,
                        String reportType,
                        String result,
                        String description,
                        String date) {

                Map<String, Object> data = new HashMap<>();

                data.put("patientId", patientId);
                data.put("patientName", patientName);
                data.put("reportType", reportType);
                data.put("result", result);
                data.put("description", description);
                data.put("date", date);

                String id = firestore.addDocument(
                                "reports",
                                data);

                System.out.println(
                                "Report added: " + id);
        }

        // =========================================================
        // ADD PRESCRIPTION
        // =========================================================

        private static void addPrescription(
                        FirestoreService firestore,
                        String patientId,
                        String patientName,
                        String medicine,
                        String dosage,
                        String frequency,
                        String instruction) {

                Map<String, Object> data = new HashMap<>();

                data.put("patientId", patientId);
                data.put("patientName", patientName);
                data.put("medicine", medicine);
                data.put("dosage", dosage);
                data.put("frequency", frequency);
                data.put("instruction", instruction);

                String id = firestore.addDocument(
                                "prescriptions",
                                data);

                System.out.println(
                                "Prescription added: " + id);
        }
}
