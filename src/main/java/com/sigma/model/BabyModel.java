package com.sigma.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

// =============================================================
// BABY MODEL
// =============================================================
// Contains:
// 1. Baby basic information
// 2. Birth information
// 3. Current growth information
// 4. Growth history
// 5. Automatic age calculation
//
// Firebase-ready model.
// No UI / Controller / Firebase code is used here.
// =============================================================

public class BabyModel {

    // =========================================================
    // BASIC INFORMATION
    // =========================================================

    private String babyId;
    private String motherId;
    private String babyName;
    private LocalDate dateOfBirth;
    private String gender;
    private String bloodGroup;

    // =========================================================
    // BIRTH INFORMATION
    // =========================================================

    private double birthWeight;
    private double birthHeight;

    // =========================================================
    // CURRENT GROWTH
    // =========================================================

    private double currentWeight;
    private double currentHeight;
    private double headCircumference;

    // =========================================================
    // GROWTH HISTORY
    // =========================================================

    private List<GrowthRecord> growthHistory;

    // =========================================================
    // DEFAULT CONSTRUCTOR
    // =========================================================
    // Useful later for Firebase / object mapping.
    // =========================================================

    public BabyModel() {

        this.growthHistory = new ArrayList<>();
    }

    // =========================================================
    // PARAMETERIZED CONSTRUCTOR
    // =========================================================

    public BabyModel(
            String babyId,
            String motherId,
            String babyName,
            LocalDate dateOfBirth,
            String gender,
            String bloodGroup,
            double birthWeight,
            double birthHeight,
            double currentWeight,
            double currentHeight,
            double headCircumference) {

        this.babyId = babyId;
        this.motherId = motherId;
        this.babyName = babyName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.bloodGroup = bloodGroup;

        this.birthWeight = birthWeight;
        this.birthHeight = birthHeight;

        this.currentWeight = currentWeight;
        this.currentHeight = currentHeight;
        this.headCircumference = headCircumference;

        this.growthHistory = new ArrayList<>();
    }

    // =========================================================
    // GET BABY AGE
    // =========================================================

    public Period getAge() {

        if (dateOfBirth == null) {
            return Period.ZERO;
        }

        return Period.between(
                dateOfBirth,
                LocalDate.now()
        );
    }

    // =========================================================
    // AGE IN MONTHS
    // =========================================================

    public int getAgeInMonths() {

        if (dateOfBirth == null) {
            return 0;
        }

        Period age = getAge();

        return age.getYears() * 12
                + age.getMonths();
    }

    // =========================================================
    // AGE IN DAYS
    // =========================================================

    public long getAgeInDays() {

        if (dateOfBirth == null) {
            return 0;
        }

        return java.time.temporal.ChronoUnit.DAYS.between(
                dateOfBirth,
                LocalDate.now()
        );
    }

    // =========================================================
    // FORMATTED AGE
    // =========================================================

    public String getFormattedAge() {

        if (dateOfBirth == null) {
            return "Age not available";
        }

        Period age = getAge();

        int years = age.getYears();
        int months = age.getMonths();
        int days = age.getDays();

        if (years > 0) {

            if (months == 0) {
                return years + (years == 1 ? " Year" : " Years");
            }

            return years + (years == 1 ? " Year " : " Years ")
                    + months
                    + (months == 1 ? " Month" : " Months");
        }

        if (months > 0) {

            if (days == 0) {
                return months
                        + (months == 1 ? " Month" : " Months");
            }

            return months
                    + (months == 1 ? " Month " : " Months ")
                    + days
                    + (days == 1 ? " Day" : " Days");
        }

        return days
                + (days == 1 ? " Day" : " Days");
    }

    // =========================================================
    // CHECK BABY IS UNDER ONE YEAR
    // =========================================================

    public boolean isUnderOneYear() {

        if (dateOfBirth == null) {
            return false;
        }

        return dateOfBirth
                .plusYears(1)
                .isAfter(LocalDate.now());
    }

    // =========================================================
    // CHECK VALID DOB
    // =========================================================

    public boolean hasValidDateOfBirth() {

        if (dateOfBirth == null) {
            return false;
        }

        return !dateOfBirth.isAfter(LocalDate.now());
    }

    // =========================================================
    // GROWTH HISTORY
    // =========================================================

    public void addGrowthRecord(
            LocalDate date,
            double weight,
            double height,
            double headCircumference) {

        GrowthRecord record = new GrowthRecord(
                date,
                weight,
                height,
                headCircumference
        );

        growthHistory.add(record);

        // Automatically update current/latest growth.
        this.currentWeight = weight;
        this.currentHeight = height;
        this.headCircumference = headCircumference;
    }

    // =========================================================
    // GET GROWTH HISTORY
    // =========================================================

    public List<GrowthRecord> getGrowthHistory() {

        return growthHistory;
    }

    public void setGrowthHistory(
            List<GrowthRecord> growthHistory) {

        this.growthHistory =
                growthHistory != null
                        ? growthHistory
                        : new ArrayList<>();
    }

    // =========================================================
    // GETTERS AND SETTERS
    // =========================================================

    public String getBabyId() {
        return babyId;
    }

    public void setBabyId(String babyId) {
        this.babyId = babyId;
    }

    public String getMotherId() {
        return motherId;
    }

    public void setMotherId(String motherId) {
        this.motherId = motherId;
    }

    public String getBabyName() {
        return babyName;
    }

    public void setBabyName(String babyName) {
        this.babyName = babyName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public double getBirthWeight() {
        return birthWeight;
    }

    public void setBirthWeight(double birthWeight) {
        this.birthWeight = birthWeight;
    }

    public double getBirthHeight() {
        return birthHeight;
    }

    public void setBirthHeight(double birthHeight) {
        this.birthHeight = birthHeight;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(double currentWeight) {
        this.currentWeight = currentWeight;
    }

    public double getCurrentHeight() {
        return currentHeight;
    }

    public void setCurrentHeight(double currentHeight) {
        this.currentHeight = currentHeight;
    }

    public double getHeadCircumference() {
        return headCircumference;
    }

    public void setHeadCircumference(double headCircumference) {
        this.headCircumference = headCircumference;
    }

    // =========================================================
    // INNER GROWTH RECORD CLASS
    // =========================================================
    // Kept inside Baby model as requested.
    // No separate BabyGrowth.java file is required.
    // =========================================================

    public static class GrowthRecord {

        private LocalDate date;
        private double weight;
        private double height;
        private double headCircumference;

        // Default constructor for Firebase/object mapping
        public GrowthRecord() {
        }

        public GrowthRecord(
                LocalDate date,
                double weight,
                double height,
                double headCircumference) {

            this.date = date;
            this.weight = weight;
            this.height = height;
            this.headCircumference = headCircumference;
        }

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public double getHeight() {
            return height;
        }

        public void setHeight(double height) {
            this.height = height;
        }

        public double getHeadCircumference() {
            return headCircumference;
        }

        public void setHeadCircumference(
                double headCircumference) {

            this.headCircumference = headCircumference;
        }
    }
}

