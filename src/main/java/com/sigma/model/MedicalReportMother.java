package com.sigma.model;

public class MedicalReportMother {

        private String reportId;
        private String motherId;
        private String reportName;
        private String reportDate;
        private String hospitalName;
        private String doctorName;
        private String fileUrl;

        // Required for Firebase Firestore
        public MedicalReportMother() {
        }

        // Parameterized constructor
        public MedicalReportMother(
                        String reportId,
                        String motherId,
                        String reportName,
                        String reportDate,
                        String hospitalName,
                        String doctorName,
                        String fileUrl) {

                this.reportId = reportId;
                this.motherId = motherId;
                this.reportName = reportName;
                this.reportDate = reportDate;
                this.hospitalName = hospitalName;
                this.doctorName = doctorName;
                this.fileUrl = fileUrl;
        }

        public String getReportId() {
                return reportId;
        }

        public void setReportId(String reportId) {
                this.reportId = reportId;
        }

        public String getMotherId() {
                return motherId;
        }

        public void setMotherId(String motherId) {
                this.motherId = motherId;
        }

        public String getReportName() {
                return reportName;
        }

        public void setReportName(String reportName) {
                this.reportName = reportName;
        }

        public String getReportDate() {
                return reportDate;
        }

        public void setReportDate(String reportDate) {
                this.reportDate = reportDate;
        }

        public String getHospitalName() {
                return hospitalName;
        }

        public void setHospitalName(String hospitalName) {
                this.hospitalName = hospitalName;
        }

        public String getDoctorName() {
                return doctorName;
        }

        public void setDoctorName(String doctorName) {
                this.doctorName = doctorName;
        }

        public String getFileUrl() {
                return fileUrl;
        }

        public void setFileUrl(String fileUrl) {
                this.fileUrl = fileUrl;
        }

        @Override
        public String toString() {
                return "MedicalReportMother{" +
                                "reportId='" + reportId + '\'' +
                                ", motherId='" + motherId + '\'' +
                                ", reportName='" + reportName + '\'' +
                                ", reportDate='" + reportDate + '\'' +
                                ", hospitalName='" + hospitalName + '\'' +
                                ", doctorName='" + doctorName + '\'' +
                                ", fileUrl='" + fileUrl + '\'' +
                                '}';
        }
}