package com.sigma.view.adminpages;

import com.google.cloud.firestore.Firestore;
import com.sigma.config.FirebaseConfig;
import com.sigma.controller.Ashaprofilecontroller;
import com.sigma.controller.MotherWlcController;
import com.sigma.controller.HospitalController.HospitalAdminProfileController;
import com.sigma.controller.doctorController.DoctorProfileController;
import com.sigma.model.AshaProfileModel;
import com.sigma.model.HospitalAdminProfile;
import com.sigma.model.MotherWlcModel;
import com.sigma.model.DoctorModel.DoctorProfileModel;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.time.LocalDate;
import java.util.UUID;

public class AdminNewMember {
    private BorderPane root;
    private ComboBox<String> type;
    private VBox fields;
    private final VBox form=new VBox(14);
    private static final String PURPLE="#9C49C8", DARK="#24234F", GREY="#77778D", BORDER="#F0DCE8", BG="#FFF8FC";

    public BorderPane getNewMemberRoot(){
        root=new BorderPane();root.setStyle("-fx-background-color:"+BG+";");
        VBox header=new VBox(6);header.setPadding(new Insets(30,40,15,40));Label title=new Label("＋  Add New Member");title.setFont(Font.font("Arial",FontWeight.BOLD,30));title.setTextFill(Color.web(DARK));Label sub=new Label("Register a Mother, Doctor, Hospital or ASHA Worker using the existing Firebase data paths.");sub.setStyle("-fx-text-fill:"+GREY+";-fx-font-size:14px;");header.getChildren().addAll(title,sub);
        VBox content=new VBox(18);content.setPadding(new Insets(15,40,40,40));
        VBox typeCard=card();Label t=new Label("①  Member Type");t.setFont(Font.font("Arial",FontWeight.BOLD,18));t.setTextFill(Color.web(DARK));type=new ComboBox<>();type.getItems().addAll("Mother","Doctor","Hospital","ASHA Worker");type.setPromptText("Select member type");type.setMaxWidth(Double.MAX_VALUE);type.setPrefHeight(42);typeCard.getChildren().addAll(t,type);
        fields=new VBox(14); form.setPadding(new Insets(18)); form.setStyle("-fx-background-color:white;-fx-background-radius:15px;-fx-border-color:"+BORDER+";-fx-border-radius:15px;"); fields.getChildren().add(new Label("Select a member type to load its fields."));
        type.setOnAction(e->buildFields(type.getValue()));
        content.getChildren().addAll(typeCard,form);ScrollPane sp=new ScrollPane(content);sp.setFitToWidth(true);sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);sp.setStyle("-fx-background-color:transparent;-fx-background:"+BG+";");root.setTop(header);root.setCenter(sp);return root;
    }

    private void buildFields(String role){
        form.getChildren().clear();
        if("Mother".equals(role)) buildMother(); else if("Doctor".equals(role)) buildDoctor(); else if("Hospital".equals(role)) buildHospital(); else buildAsha();
    }
    private void buildMother(){
        TextField name=tf("Full name"), location=tf("City / Location"), weight=tf("Weight (kg)"), blood=tf("Blood Group"), condition=tf("Medical Condition"), address=tf("Address"), phone=tf("Phone"), allergies=tf("Allergies"), marital=tf("Marital Status");
        DatePicker dob=new DatePicker(),lmp=new DatePicker();dob.setPromptText("Date of Birth");lmp.setPromptText("LMP Date");
        Button save=saveButton();save.setOnAction(e->{try{if(empty(name,location,weight,blood,condition)||dob.getValue()==null||lmp.getValue()==null){error("Fill all required Mother fields.");return;}double w=Double.parseDouble(weight.getText().trim());LocalDate edd=lmp.getValue().plusDays(280);MotherWlcModel m=new MotherWlcModel(null,name.getText().trim(),dob.getValue(),location.getText().trim(),w,blood.getText().trim(),condition.getText().trim(),lmp.getValue(),edd,address.getText().trim(),phone.getText().trim(),allergies.getText().trim(),marital.getText().trim(),null,null,null,null,null,null,null);if(new MotherWlcController().saveMotherData(m)){success("Mother saved to Firebase. ID: "+m.getMotherId());clear();}else error("Mother could not be saved to Firebase.");}catch(Exception ex){error("Invalid Mother details: "+ex.getMessage());}});
        add("Full Name",name);add("Date of Birth",dob);add("Location",location);add("Weight",weight);add("Blood Group",blood);add("Medical Condition",condition);add("LMP Date",lmp);add("Address",address);add("Phone",phone);add("Allergies",allergies);add("Marital Status",marital);form.getChildren().add(save);
    }
    private void buildDoctor(){
        TextField first=tf("First name"),last=tf("Last name"),gender=tf("Gender"),phone=tf("Phone"),email=tf("Email"),address=tf("Address"),spec=tf("Specialization"),qual=tf("Qualification"),exp=tf("Experience"),license=tf("Medical License"),clinic=tf("Clinic Name"),clinicAddr=tf("Clinic Address");DatePicker dob=new DatePicker();dob.setPromptText("Date of Birth");
        Button save=saveButton();save.setOnAction(e->{try{if(empty(first,last,email,phone,spec,qual,license)||dob.getValue()==null){error("Fill all required Doctor fields.");return;}String uid="DOCTOR_"+UUID.randomUUID().toString().substring(0,8);Firestore db=FirebaseConfig.getFirestore();DoctorProfileController c=new DoctorProfileController(db,uid);boolean ok=c.updateProfile(first.getText().trim(),last.getText().trim(),gender.getText().trim(),dob.getValue().toString(),phone.getText().trim(),email.getText().trim(),address.getText().trim(),spec.getText().trim(),qual.getText().trim(),exp.getText().trim(),license.getText().trim(),clinic.getText().trim(),clinicAddr.getText().trim());if(ok){success("Doctor saved to Firebase. UID: "+uid);clear();}else error("Doctor could not be saved to Firebase.");}catch(Exception ex){error("Doctor save failed: "+ex.getMessage());}});
        add("First Name",first);add("Last Name",last);add("Gender",gender);add("Date of Birth",dob);add("Phone",phone);add("Email",email);add("Address",address);add("Specialization",spec);add("Qualification",qual);add("Experience",exp);add("Medical License",license);add("Clinic Name",clinic);add("Clinic Address",clinicAddr);form.getChildren().add(save);
    }
    private void buildAsha(){
        TextField name=tf("Name"),phone=tf("Phone"),email=tf("Email"),address=tf("Address");DatePicker joining=new DatePicker();joining.setPromptText("Date of Joining");CheckBox active=new CheckBox("Active");active.setSelected(true);Button save=saveButton();save.setOnAction(e->{if(empty(name,phone,email,address)||joining.getValue()==null){error("Fill all required ASHA fields.");return;}String id="ASHA_"+UUID.randomUUID().toString().substring(0,8);new Ashaprofilecontroller().addAshaProfile(name.getText().trim(),id,phone.getText().trim(),email.getText().trim(),address.getText().trim(),joining.getValue().toString(),"",active.isSelected());success("ASHA Worker saved to Firebase. ID: "+id);clear();});add("Name",name);add("Phone",phone);add("Email",email);add("Address",address);add("Date of Joining",joining);form.getChildren().addAll(active,save);
    }
    private void buildHospital(){
        TextField hospital=tf("Hospital Name"),phone=tf("Hospital Phone"),type=tf("Hospital Type"),address=tf("Hospital Address"),hospitalEmail=tf("Hospital Email"),admin=tf("Admin Name"),role=tf("Admin Role"),email=tf("Admin Email"),contact=tf("Admin Contact"),year=tf("Established Year");Button save=saveButton();save.setOnAction(e->{if(empty(hospital,phone,type,address,hospitalEmail,admin,role,email,contact,year)){error("Fill all Hospital fields.");return;}new HospitalAdminProfileController().addHospitalAdminProfile(hospital.getText().trim(),phone.getText().trim(),type.getText().trim(),address.getText().trim(),hospitalEmail.getText().trim(),admin.getText().trim(),role.getText().trim(),email.getText().trim(),contact.getText().trim(),"",false,year.getText().trim(),"","","","","ACTIVE","All Systems Operational");success("Hospital profile saved through the existing Hospital controller/DAO path. Note: that existing DAO uses the fixed 'hospitalAdmin' document.");clear();});add("Hospital Name",hospital);add("Hospital Phone",phone);add("Hospital Type",type);add("Address",address);add("Hospital Email",hospitalEmail);add("Admin Name",admin);add("Admin Role",role);add("Admin Email",email);add("Admin Contact",contact);add("Established Year",year);form.getChildren().add(save);
    }
    private void add(String label,Control c){Label l=new Label(label);l.setTextFill(Color.web(DARK));l.setFont(Font.font("Arial",FontWeight.BOLD,12));form.getChildren().addAll(l,c);}
    private TextField tf(String p){TextField f=new TextField();f.setPromptText(p);f.setPrefHeight(40);return f;}
    private VBox card(){VBox b=new VBox(10);b.setPadding(new Insets(18));b.setStyle("-fx-background-color:white;-fx-background-radius:15px;-fx-border-color:"+BORDER+";-fx-border-radius:15px;");return b;}
    private Button saveButton(){Button b=new Button("✓  Save Member to Firebase");b.setStyle("-fx-background-color:"+PURPLE+";-fx-text-fill:white;-fx-font-weight:bold;-fx-background-radius:9px;-fx-padding:11 20;");return b;}
    private boolean empty(TextField... f){for(TextField x:f)if(x.getText().isBlank())return true;return false;}
    private void clear(){type.getSelectionModel().clearSelection();form.getChildren().clear();form.getChildren().add(new Label("Select a member type to load its fields."));}
    private void success(String s){Alert a=new Alert(Alert.AlertType.INFORMATION);a.setTitle("Success");a.setHeaderText(null);a.setContentText(s);a.showAndWait();}
    private void error(String s){Alert a=new Alert(Alert.AlertType.ERROR);a.setTitle("Error");a.setHeaderText(null);a.setContentText(s);a.showAndWait();}
}
