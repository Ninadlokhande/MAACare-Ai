
package com.sigma.model.DoctorModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Patient {

        // =====================================================
        // FIELDS
        // =====================================================

        private final StringProperty name;
        private final StringProperty age;
        private final StringProperty contact;
        private final StringProperty lastVisit;
        private final StringProperty nextVisit;
        private final StringProperty action;

        // =====================================================
        // CONSTRUCTOR
        // =====================================================

        public Patient(
                        String name,
                        String age,
                        String contact,
                        String lastVisit,
                        String nextVisit,
                        String action) {

                this.name = new SimpleStringProperty(name);

                this.age = new SimpleStringProperty(age);

                this.contact = new SimpleStringProperty(contact);

                this.lastVisit = new SimpleStringProperty(lastVisit);

                this.nextVisit = new SimpleStringProperty(nextVisit);

                this.action = new SimpleStringProperty(action);
        }

        // =====================================================
        // GETTERS
        // =====================================================

        public String getName() {
                return name.get();
        }

        public String getAge() {
                return age.get();
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

        // =====================================================
        // PROPERTY METHODS
        // =====================================================

        public StringProperty nameProperty() {
                return name;
        }

        public StringProperty ageProperty() {
                return age;
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

        // =====================================================
        // SETTERS
        // =====================================================

        public void setName(String name) {
                this.name.set(name);
        }

        public void setAge(String age) {
                this.age.set(age);
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
}
