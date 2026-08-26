package com.sigma.controller.doctorController;

import com.sigma.model.DoctorModel.Settings;

public class SettingsController {

        private Settings doctorSettings;

        public SettingsController() {

                doctorSettings = new Settings(
                                "Dr. Anjali Mehta",
                                "anjalimehta@maacare.com",
                                "9876543210",
                                "Obstetrician & Gynecologist",
                                "GYN/2020/12345");
        }

        // ==========================
        // SAVE SETTINGS
        // ==========================

        public void saveSettings(
                        String fullName,
                        String email,
                        String phone,
                        String specialization,
                        String license) {

                doctorSettings.setFullName(fullName);
                doctorSettings.setEmail(email);
                doctorSettings.setPhone(phone);
                doctorSettings.setSpecialization(specialization);
                doctorSettings.setLicense(license);

                System.out.println("Settings Saved Successfully!");

                System.out.println(
                                "Doctor: " +
                                                doctorSettings.getFullName());

                System.out.println(
                                "Email: " +
                                                doctorSettings.getEmail());

                System.out.println(
                                "Phone: " +
                                                doctorSettings.getPhone());

                System.out.println(
                                "Specialization: " +
                                                doctorSettings.getSpecialization());

                System.out.println(
                                "License: " +
                                                doctorSettings.getLicense());
        }

        // ==========================
        // GET MODEL
        // ==========================

        public Settings getDoctorSettings() {
                return doctorSettings;
        }
}