package com.sigma.view.motherPages;

import java.awt.Desktop;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.sigma.controller.MedicalReportMotherController;
import com.sigma.model.MedicalReportMother;
import com.sigma.model.MotherWlcModel;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

// =============================================================
// MOTHER REPORTS PAGE
// =============================================================

public class MotherReports {

        private final String PINK = "#E84A87";
        private final String DARK = "#24234F";
        private final String PURPLE = "#9B4DCC";
        private final String TEXT_GRAY = "#77778D";
        private final String GREEN = "#3C9A68";

        // =========================================================
        // MOTHER MODEL
        // =========================================================

        private final MotherWlcModel motherModel;

        // =========================================================
        // CONTROLLER
        // =========================================================

        private final MedicalReportMotherController controller;

        // =========================================================
        // REPORT LIST
        // =========================================================

        private final List<MedicalReportMother> reports = new ArrayList<>();

        // =========================================================
        // REPORTS CONTAINER
        // =========================================================

        private VBox reportsContainer;

        // =========================================================
        // CONSTRUCTOR
        // =========================================================

        public MotherReports(
                        MotherWlcModel motherModel) {

                this.motherModel = motherModel;

                this.controller = new MedicalReportMotherController();

                loadReportsFromFirebase();
        }

        // =========================================================
        // MAIN PAGE
        // =========================================================

        public VBox createReportsPage() {

                VBox page = new VBox();

                page.setFillWidth(true);

                page.setStyle(
                                "-fx-background-color: linear-gradient(" +
                                                "to bottom right, " +
                                                "#FFFFFF 0%, " +
                                                "#FFF7FB 55%, " +
                                                "#F4EDFF 100%);");

                // =====================================================
                // CONTENT
                // =====================================================

                VBox content = new VBox();

                content.setSpacing(20);

                content.setPadding(
                                new Insets(
                                                25,
                                                30,
                                                40,
                                                30));

                // =====================================================
                // TITLE
                // =====================================================

                VBox titleBox = new VBox();

                titleBox.setSpacing(5);

                Label title = new Label(
                                "Medical Reports");

                title.setStyle(
                                "-fx-font-size: 25px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + DARK + ";");

                Label subtitle = new Label(
                                "Keep your important pregnancy and health reports in one place.");

                subtitle.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-text-fill: " + TEXT_GRAY + ";");

                titleBox.getChildren().addAll(
                                title,
                                subtitle);

                // =====================================================
                // REPORTS CONTAINER
                // =====================================================

                reportsContainer = new VBox();

                reportsContainer.setSpacing(12);

                refreshReportsUI();

                // =====================================================
                // INFORMATION CARD
                // =====================================================

                VBox infoCard = createInfoCard();

                content.getChildren().addAll(
                                titleBox,
                                reportsContainer,
                                infoCard);

                // =====================================================
                // SCROLL
                // =====================================================

                ScrollPane scrollPane = new ScrollPane(content);

                scrollPane.setFitToWidth(true);

                scrollPane.setPannable(true);

                scrollPane.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                scrollPane.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                scrollPane.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-background: transparent;" +
                                                "-fx-border-color: transparent;");

                page.getChildren().add(
                                scrollPane);

                VBox.setVgrow(
                                scrollPane,
                                Priority.ALWAYS);

                return page;
        }

        // =========================================================
        // LOAD REPORTS FROM FIREBASE
        // =========================================================

        private void loadReportsFromFirebase() {

                reports.clear();

                if (motherModel == null) {

                        loadDemoReports();

                        return;
                }

                String motherId = motherModel.getMotherId();

                if (motherId == null ||
                                motherId.trim().isEmpty()) {

                        System.out.println(
                                        "Mother ID not available. Loading demo reports.");

                        loadDemoReports();

                        return;
                }

                try {

                        System.out.println(
                                        "Loading medical reports for mother: "
                                                        + motherId);

                        List<MedicalReportMother> firebaseReports = controller.getReportsByMotherId(motherId);

                        if (firebaseReports != null &&
                                        !firebaseReports.isEmpty()) {

                                reports.addAll(firebaseReports);

                                System.out.println(
                                                "Firebase reports loaded: "
                                                                + firebaseReports.size());

                        } else {

                                System.out.println(
                                                "No Firebase reports found. Loading demo reports.");

                                loadDemoReports();
                        }

                } catch (Exception e) {

                        System.out.println(
                                        "Error loading reports from Firebase.");

                        e.printStackTrace();

                        loadDemoReports();
                }
        }

        // =========================================================
        // DEMO REPORTS
        // =========================================================

        private void loadDemoReports() {

                String motherId = "";

                if (motherModel != null &&
                                motherModel.getMotherId() != null) {

                        motherId = motherModel.getMotherId();
                }

                // =====================================================
                // DEMO REPORT 1
                // =====================================================

                MedicalReportMother bloodReport = new MedicalReportMother(
                                "DEMO_REPORT_001",
                                motherId,
                                "Complete Blood Count",
                                "18 Aug 2026",
                                "City Care Hospital",
                                "Dr. Priya Sharma",
                                "");

                // =====================================================
                // DEMO REPORT 2
                // =====================================================

                MedicalReportMother ultrasoundReport = new MedicalReportMother(
                                "DEMO_REPORT_002",
                                motherId,
                                "Pregnancy Ultrasound Scan",
                                "25 Aug 2026",
                                "City Care Hospital",
                                "Dr. Anjali Patil",
                                "");

                reports.add(
                                bloodReport);

                reports.add(
                                ultrasoundReport);

                System.out.println(
                                "Demo medical reports loaded.");
        }

        // =========================================================
        // REFRESH REPORT UI
        // =========================================================

        private void refreshReportsUI() {

                if (reportsContainer == null) {
                        return;
                }

                reportsContainer.getChildren().clear();

                if (reports.isEmpty()) {

                        reportsContainer.getChildren().add(
                                        createEmptyState());

                        return;
                }

                for (MedicalReportMother report : reports) {

                        reportsContainer.getChildren().add(
                                        createReportCard(report));
                }
        }

        // =========================================================
        // REPORT CARD
        // =========================================================

        private VBox createReportCard(
                        MedicalReportMother report) {

                VBox card = new VBox();

                card.setSpacing(12);

                card.setPadding(
                                new Insets(18));

                card.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 18;" +
                                                "-fx-border-color: #E7DCE8;" +
                                                "-fx-border-radius: 18;");

                // =====================================================
                // TOP
                // =====================================================

                HBox top = new HBox();

                top.setSpacing(15);

                top.setAlignment(
                                Pos.CENTER_LEFT);

                Label icon = new Label(
                                getReportIcon(
                                                report.getReportName()));

                icon.setAlignment(
                                Pos.CENTER);

                icon.setPrefSize(
                                55,
                                55);

                icon.setStyle(
                                "-fx-background-color: #FFF0F6;" +
                                                "-fx-background-radius: 15;" +
                                                "-fx-font-size: 27px;");

                // =====================================================
                // REPORT DETAILS
                // =====================================================

                VBox details = new VBox();

                details.setSpacing(4);

                Label name = new Label(
                                safeText(
                                                report.getReportName(),
                                                "Medical Report"));

                name.setWrapText(true);

                name.setStyle(
                                "-fx-font-size: 17px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + DARK + ";");

                Label date = new Label(
                                "📅 " +
                                                safeText(
                                                                report.getReportDate(),
                                                                "Date not available"));

                date.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-text-fill: " + TEXT_GRAY + ";");

                Label hospital = new Label(
                                "🏥 " +
                                                safeText(
                                                                report.getHospitalName(),
                                                                "Hospital not available"));

                hospital.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-text-fill: " + TEXT_GRAY + ";");

                Label doctor = new Label(
                                "👩‍⚕ " +
                                                safeText(
                                                                report.getDoctorName(),
                                                                "Doctor not available"));

                doctor.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-text-fill: " + TEXT_GRAY + ";");

                details.getChildren().addAll(
                                name,
                                date,
                                hospital,
                                doctor);

                HBox.setHgrow(
                                details,
                                Priority.ALWAYS);

                top.getChildren().addAll(
                                icon,
                                details);

                // =====================================================
                // BUTTONS
                // =====================================================

                HBox buttons = new HBox();

                buttons.setSpacing(10);

                buttons.setAlignment(
                                Pos.CENTER_RIGHT);

                Button view = createOutlineButton(
                                "View Report");

                Button download = createGradientButton(
                                "Download");

                // =====================================================
                // VIEW ACTION
                // =====================================================

                view.setOnAction(e -> {

                        openReport(
                                        report,
                                        false);

                });

                // =====================================================
                // DOWNLOAD ACTION
                // =====================================================

                download.setOnAction(e -> {

                        openReport(
                                        report,
                                        true);

                });

                buttons.getChildren().addAll(
                                view,
                                download);

                card.getChildren().addAll(
                                top,
                                buttons);

                return card;
        }

        // =========================================================
        // REPORT ICON
        // =========================================================

        private String getReportIcon(
                        String reportName) {

                if (reportName == null) {
                        return "📄";
                }

                String name = reportName.toLowerCase();

                if (name.contains("blood")) {
                        return "🩸";
                }

                if (name.contains("ultrasound") ||
                                name.contains("scan")) {

                        return "🩺";
                }

                if (name.contains("prescription")) {
                        return "💊";
                }

                if (name.contains("discharge")) {
                        return "🏥";
                }

                return "📄";
        }

        // =========================================================
        // OPEN REPORT
        // =========================================================

        private void openReport(
                        MedicalReportMother report,
                        boolean download) {

                // =====================================================
                // DEMO REPORT
                // =====================================================

                if (isDemoReport(report)) {

                        showMessage(
                                        "Sample Report",
                                        "This is sample report data displayed for demonstration.\n\n" +
                                                        "The actual report file will be available here " +
                                                        "when a hospital or doctor uploads it to Firebase.");

                        return;
                }

                String url = report.getFileUrl();

                // =====================================================
                // NO FILE URL
                // =====================================================

                if (url == null ||
                                url.trim().isEmpty()) {

                        showMessage(
                                        "Report Not Available",
                                        "The actual report file has not been uploaded yet.\n\n" +
                                                        "Once the hospital uploads the report, " +
                                                        "you will be able to view or download it here.");

                        return;
                }

                // =====================================================
                // OPEN FILE URL
                // =====================================================

                try {

                        if (Desktop.isDesktopSupported()) {

                                Desktop.getDesktop().browse(
                                                new URI(url));

                                if (download) {

                                        showMessage(
                                                        "Download",
                                                        "The report has been opened in your browser.\n\n" +
                                                                        "You can download the report from there.");
                                }

                        } else {

                                showMessage(
                                                "Report",
                                                "Unable to open the report on this device.");
                        }

                } catch (Exception ex) {

                        showMessage(
                                        "Error",
                                        "Unable to open the report.\n\n" +
                                                        "Please try again later.");

                        ex.printStackTrace();
                }
        }

        // =========================================================
        // CHECK DEMO REPORT
        // =========================================================

        private boolean isDemoReport(
                        MedicalReportMother report) {

                return report != null &&
                                report.getReportId() != null &&
                                report.getReportId()
                                                .startsWith("DEMO_");
        }

        // =========================================================
        // SAFE TEXT
        // =========================================================

        private String safeText(
                        String value,
                        String defaultValue) {

                if (value == null ||
                                value.trim().isEmpty()) {

                        return defaultValue;
                }

                return value;
        }

        // =========================================================
        // EMPTY STATE
        // =========================================================

        private VBox createEmptyState() {

                VBox box = new VBox();

                box.setAlignment(
                                Pos.CENTER);

                box.setSpacing(10);

                box.setPadding(
                                new Insets(35));

                box.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 18;" +
                                                "-fx-border-color: #E7DCE8;" +
                                                "-fx-border-radius: 18;");

                Label icon = new Label("📄");

                icon.setStyle(
                                "-fx-font-size: 42px;");

                Label title = new Label(
                                "No Reports Available");

                title.setStyle(
                                "-fx-font-size: 18px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + DARK + ";");

                Label message = new Label(
                                "Your medical reports will appear here " +
                                                "when they are uploaded by the hospital or doctor.");

                message.setWrapText(true);

                message.setAlignment(
                                Pos.CENTER);

                message.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-text-fill: " + TEXT_GRAY + ";");

                box.getChildren().addAll(
                                icon,
                                title,
                                message);

                return box;
        }

        // =========================================================
        // INFORMATION CARD
        // =========================================================

        private VBox createInfoCard() {

                VBox card = new VBox();

                card.setSpacing(7);

                card.setPadding(
                                new Insets(18));

                card.setStyle(
                                "-fx-background-color: #F8F2FF;" +
                                                "-fx-background-radius: 16;" +
                                                "-fx-border-color: #E2D2F2;" +
                                                "-fx-border-radius: 16;");

                Label title = new Label(
                                "💡 About Your Reports");

                title.setStyle(
                                "-fx-font-size: 16px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #7041A5;");

                Label text = new Label(
                                "Medical reports uploaded by your doctor or hospital " +
                                                "can be viewed and downloaded from this section. " +
                                                "Keep your reports safely stored for future consultations.");

                text.setWrapText(true);

                text.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-text-fill: " + TEXT_GRAY + ";");

                card.getChildren().addAll(
                                title,
                                text);

                return card;
        }

        // =========================================================
        // OUTLINE BUTTON
        // =========================================================

        private Button createOutlineButton(
                        String text) {

                Button button = new Button(text);

                button.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-text-fill: #7041A5;" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-border-color: #DCC9EC;" +
                                                "-fx-border-radius: 10;" +
                                                "-fx-background-radius: 10;" +
                                                "-fx-padding: 8px 15px;");

                return button;
        }

        // =========================================================
        // GRADIENT BUTTON
        // =========================================================

        private Button createGradientButton(
                        String text) {

                Button button = new Button(text);

                button.setStyle(
                                "-fx-background-color: linear-gradient(" +
                                                "to right, #F54B87, #9B4DCC);" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 20;" +
                                                "-fx-padding: 9px 17px;");

                return button;
        }

        // =========================================================
        // MESSAGE
        // =========================================================

        private void showMessage(
                        String title,
                        String message) {

                Alert alert = new Alert(
                                Alert.AlertType.INFORMATION);

                alert.setTitle(title);

                alert.setHeaderText(null);

                alert.setContentText(message);

                alert.showAndWait();
        }
}