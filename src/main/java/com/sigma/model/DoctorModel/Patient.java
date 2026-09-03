
package com.sigma.model.DoctorModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Patient {

        private final StringProperty patientId;
        private final StringProperty doctorId;
        private final StringProperty name;
        private final StringProperty age;
        private final StringProperty gender;
        private final StringProperty contact;
        private final StringProperty lastVisit;
        private final StringProperty nextVisit;
        private final StringProperty action;
        private final StringProperty createdDate;

        // =====================================================
        // OLD CONSTRUCTOR
        // =====================================================

        public Patient(
                        String name,
                        String age,
                        String contact,
                        String lastVisit,
                        String nextVisit,
                        String action) {

                this(
                                "",
                                "",
                                name,
                                age,
                                "",
                                contact,
                                lastVisit,
                                nextVisit,
                                action,
                                "");
        }

        // =====================================================
        // FULL CONSTRUCTOR
        // =====================================================

        public Patient(
                        String patientId,
                        String doctorId,
                        String name,
                        String age,
                        String gender,
                        String contact,
                        String lastVisit,
                        String nextVisit,
                        String action,
                        String createdDate) {

                this.patientId = new SimpleStringProperty(
                                patientId == null ? "" : patientId);

                this.doctorId = new SimpleStringProperty(
                                doctorId == null ? "" : doctorId);

                this.name = new SimpleStringProperty(
                                name == null ? "" : name);

                this.age = new SimpleStringProperty(
                                age == null ? "" : age);

                this.gender = new SimpleStringProperty(
                                gender == null ? "" : gender);

                this.contact = new SimpleStringProperty(
                                contact == null ? "" : contact);

                this.lastVisit = new SimpleStringProperty(
                                lastVisit == null ? "" : lastVisit);

                this.nextVisit = new SimpleStringProperty(
                                nextVisit == null ? "" : nextVisit);

                this.action = new SimpleStringProperty(
                                action == null ? "" : action);

                this.createdDate = new SimpleStringProperty(
                                createdDate == null ? "" : createdDate);
        }

        // =====================================================
        // GETTERS
        // =====================================================

        public String getPatientId() {
                return patientId.get();
        }

        public String getDoctorId() {
                return doctorId.get();
        }

        public String getName() {
                return name.get();
        }

        public String getAge() {
                return age.get();
        }

        public String getGender() {
                return gender.get();
        }

        public String getContact() {
                return contact.get();
        }

        public String getLastVisit() {
                return lastVisit.get();
        }

        public String getNextVisit() {
                return nextVisit.get();
        }

        public String getAction() {
                return action.get();
        }

        public String getCreatedDate() {
                return createdDate.get();
        }

        // =====================================================
        // PROPERTIES
        // =====================================================

        public StringProperty patientIdProperty() {
                return patientId;
        }

        public StringProperty doctorIdProperty() {
                return doctorId;
        }

        public StringProperty nameProperty() {
                return name;
        }

        public StringProperty ageProperty() {
                return age;
        }

        public StringProperty genderProperty() {
                return gender;
        }

        public StringProperty contactProperty() {
                return contact;
        }

        public StringProperty lastVisitProperty() {
                return lastVisit;
        }

        public StringProperty nextVisitProperty() {
                return nextVisit;
        }

        public StringProperty actionProperty() {
                return action;
        }

        public StringProperty createdDateProperty() {
                return createdDate;
        }

        // =====================================================
        // SETTERS
        // =====================================================

        public void setPatientId(String patientId) {
                this.patientId.set(patientId);
        }

        public void setDoctorId(String doctorId) {
                this.doctorId.set(doctorId);
        }

        public void setName(String name) {
                this.name.set(name);
        }

        public void setAge(String age) {
                this.age.set(age);
        }

        public void setGender(String gender) {
                this.gender.set(gender);
        }

        public void setContact(String contact) {
                this.contact.set(contact);
        }

        public void setLastVisit(String lastVisit) {
                this.lastVisit.set(lastVisit);
        }

        public void setNextVisit(String nextVisit) {
                this.nextVisit.set(nextVisit);
        }

        public void setAction(String action) {
                this.action.set(action);
        }

        public void setCreatedDate(String createdDate) {
                this.createdDate.set(createdDate);
        }

        @Override
        public String toString() {

                return "Patient{" +
                                "patientId='" + getPatientId() + '\'' +
                                ", doctorId='" + getDoctorId() + '\'' +
                                ", name='" + getName() + '\'' +
                                ", age='" + getAge() + '\'' +
                                ", gender='" + getGender() + '\'' +
                                ", contact='" + getContact() + '\'' +
                                '}';
        }
}
