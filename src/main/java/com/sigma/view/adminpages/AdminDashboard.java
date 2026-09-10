package com.sigma.view.adminpages;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.sigma.config.FirebaseConfig;
import com.sigma.model.AshaProfileModel;
import com.sigma.model.HospitalAdminProfile;
import com.sigma.model.MotherWlcModel;
import com.sigma.model.DoctorModel.DoctorProfileModel;
import com.sigma.controller.Ashaprofilecontroller;
import com.sigma.controller.HospitalController.HospitalAdminProfileController;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdminDashboard {
    private BorderPane root;
    private Scene adminDashboardScene;
    private Scene loginpageScene;
    private ScrollPane dashboardScrollPane;
    private Label pageTitle;

    private AdminNewMember newMemberPage;
    private AdminRequestApproval approvalPage;
    private AdminProfile profilePage;
    private AdminSettings settingsPage;
    private Node analyticsPage;
    private AboutUsPage aboutUsPage;
    private Node newMemberRoot, approvalRoot, profileRoot, settingsRoot;

    private final List<Button> sidebarButtons = new ArrayList<>();

    private static final String PINK="#E84A87", PURPLE="#9B4DCC", DARK="#24234F", GREY="#77778D", BG="#FFF8FC";

    public Scene gotoAdminDashboard() {
        if (adminDashboardScene != null) return adminDashboardScene;
        try { loginpageScene = new com.sigma.view.Loginpage().gotologinpage(); } catch(Exception ignored) {}

        root = new BorderPane();
        root.setStyle("-fx-background-color:"+BG+";");
        pageTitle = new Label("Dashboard");
        pageTitle.setFont(Font.font("Arial",FontWeight.BOLD,22));
        pageTitle.setTextFill(Color.web(DARK));

        VBox sidebar = createSidebar();
        root.setLeft(sidebar);
        root.setTop(createTopBar());

        newMemberPage = new AdminNewMember();
        newMemberRoot = newMemberPage.getNewMemberRoot();
        approvalPage = new AdminRequestApproval();
        approvalRoot = approvalPage.getAdminrequestApprovalRoot();
        profilePage = new AdminProfile();
        profileRoot = profilePage.getProfileRoot();
        settingsPage = new AdminSettings();
        settingsRoot = settingsPage.getSettingsRoot();
        analyticsPage = new AdminAnalytics().getAnalyticsRoot();
        aboutUsPage = new AboutUsPage();

        showDashboard();
        adminDashboardScene = new Scene(root, com.sigma.view.scenesettings.rectanguler2d.getWidth(), com.sigma.view.scenesettings.rectanguler2d.getHeight());
        return adminDashboardScene;
    }

    private HBox createTopBar() {
        HBox bar=new HBox(15);
        bar.setAlignment(Pos.CENTER_LEFT); bar.setPadding(new Insets(14,25,14,25));
        bar.setStyle("-fx-background-color:white;-fx-border-color:#E7DCE8;-fx-border-width:0 0 1 0;");
        Region spacer=new Region(); HBox.setHgrow(spacer,Priority.ALWAYS);
        Label admin=new Label("MaaCare Administrator"); admin.setTextFill(Color.web(DARK)); admin.setFont(Font.font("Arial",FontWeight.BOLD,14));
        bar.getChildren().addAll(pageTitle,spacer,admin); return bar;
    }

    private VBox createSidebar() {
        VBox side=new VBox(8); side.setPrefWidth(230); side.setPadding(new Insets(25,15,20,15)); side.setStyle("-fx-background-color:white;-fx-border-color:#E7DCE8;-fx-border-width:0 1 0 0;");
        Label logo=new Label("MaaCare AI"); logo.setFont(Font.font("Arial",FontWeight.BOLD,25)); logo.setTextFill(Color.web(PINK)); logo.setPadding(new Insets(0,0,25,10));
        Button dashboard=menu("Dashboard"), newMember=menu("New Member"), approvals=menu("Members"), analytics=menu("Analytics"), profile=menu("My Profile"), settings=menu("Settings"), about=menu("About Us"), logout=menu("Logout");
        sidebarButtons.addAll(List.of(dashboard,newMember,approvals,analytics,profile,settings,about,logout));
        dashboard.setOnAction(e->{showDashboard(); select(dashboard);});
        newMember.setOnAction(e->{root.setCenter(newMemberRoot); pageTitle.setText("New Member"); select(newMember);});
        approvals.setOnAction(e->{approvalPage.refreshMembers(); root.setCenter(approvalRoot); pageTitle.setText("Members"); select(approvals);});
        analytics.setOnAction(e->{analyticsPage=new AdminAnalytics().getAnalyticsRoot(); root.setCenter(analyticsPage); pageTitle.setText("Analytics"); select(analytics);});
        profile.setOnAction(e->{root.setCenter(profileRoot); pageTitle.setText("My Profile"); select(profile);});
        settings.setOnAction(e->{root.setCenter(settingsRoot); pageTitle.setText("Settings"); select(settings);});
        about.setOnAction(e->{aboutUsPage.show(com.sigma.view.Welcomepage.stage, () -> { root.setCenter(dashboardScrollPane); pageTitle.setText("Dashboard"); }); pageTitle.setText("About Us"); select(about);});
        logout.setOnAction(e->{ if(loginpageScene!=null && com.sigma.view.Welcomepage.stage!=null){com.sigma.view.Welcomepage.stage.setScene(loginpageScene);com.sigma.view.Welcomepage.stage.setMaximized(false);} });
        side.getChildren().addAll(logo,dashboard,newMember,approvals,analytics,profile,settings,about,logout); return side;
    }

    private Button menu(String text){ Button b=new Button(text); b.setMaxWidth(Double.MAX_VALUE); b.setAlignment(Pos.CENTER_LEFT); b.setPadding(new Insets(12,15,12,15)); b.setStyle("-fx-background-color:transparent;-fx-text-fill:#24234F;-fx-font-size:13px;-fx-background-radius:10px;-fx-cursor:hand;"); b.setOnMouseEntered(e->{if(!b.getStyle().contains(PINK)) b.setStyle("-fx-background-color:#FFF0F6;-fx-text-fill:"+PINK+";-fx-font-size:13px;-fx-background-radius:10px;-fx-cursor:hand;");}); b.setOnMouseExited(e->{if(!b.getStyle().contains("#E84A87")) b.setStyle("-fx-background-color:transparent;-fx-text-fill:#24234F;-fx-font-size:13px;-fx-background-radius:10px;-fx-cursor:hand;");}); return b; }
    private void select(Button selected){for(Button b:sidebarButtons)b.setStyle(b==selected?"-fx-background-color:#FFF0F6;-fx-text-fill:"+PINK+";-fx-font-size:13px;-fx-font-weight:bold;-fx-background-radius:10px;":"-fx-background-color:transparent;-fx-text-fill:#24234F;-fx-font-size:13px;-fx-background-radius:10px;-fx-cursor:hand;");}

    private void showDashboard(){
        VBox content=new VBox(18); content.setPadding(new Insets(28)); content.setStyle("-fx-background-color:"+BG+";");
        Label welcome=new Label("Welcome back, Admin!"); welcome.setFont(Font.font("Arial",FontWeight.BOLD,28)); welcome.setTextFill(Color.WHITE);
        Label sub=new Label("Live overview from Firebase healthcare profiles."); sub.setTextFill(Color.WHITE); sub.setStyle("-fx-font-size:14px;");
        VBox banner=new VBox(5,welcome,sub); banner.setPadding(new Insets(22)); banner.setStyle("-fx-background-color:linear-gradient(to right,#F2468B,#A64BC8);-fx-background-radius:18px;");
        List<MemberRecord> members=loadAllMembers();
        long doctors=members.stream().filter(m->m.role.equals("Doctor")).count(), hospitals=members.stream().filter(m->m.role.equals("Hospital")).count(), mothers=members.stream().filter(m->m.role.equals("Mother")).count(), asha=members.stream().filter(m->m.role.equals("ASHA Worker")).count();
        HBox stats=new HBox(15,stat("Total Members",String.valueOf(members.size())),stat("Doctors",String.valueOf(doctors)),stat("Hospitals",String.valueOf(hospitals)),stat("Mothers",String.valueOf(mothers)),stat("ASHA Workers",String.valueOf(asha)));
        for(Node n:stats.getChildren()) HBox.setHgrow(n,Priority.ALWAYS);
        VBox feed=box("Recent Members"); int shown=0; for(MemberRecord m:members){if(shown++>=8)break; feed.getChildren().add(memberRow(m));}
        if(feed.getChildren().size()==1) feed.getChildren().add(new Label("No members found in Firebase."));
        Button view=new Button("View all members →"); view.setStyle("-fx-background-color:#FFF0F6;-fx-text-fill:#C53F83;-fx-font-weight:bold;-fx-background-radius:10px;-fx-padding:10px 14px;"); view.setOnAction(e->{approvalPage.refreshMembers();root.setCenter(approvalRoot);pageTitle.setText("Members");}); feed.getChildren().add(view);
        content.getChildren().addAll(banner,stats,feed);
        dashboardScrollPane=new ScrollPane(content); dashboardScrollPane.setFitToWidth(true); dashboardScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); dashboardScrollPane.setStyle("-fx-background-color:transparent;-fx-background:"+BG+";"); root.setCenter(dashboardScrollPane); pageTitle.setText("Dashboard");
    }
    private VBox stat(String t,String v){VBox b=box("");Label a=new Label(t),c=new Label(v);a.setTextFill(Color.web(GREY));c.setTextFill(Color.web(DARK));c.setFont(Font.font("Arial",FontWeight.BOLD,24));b.getChildren().addAll(a,c);return b;}
    private VBox box(String title){VBox b=new VBox(10);b.setPadding(new Insets(18));b.setStyle("-fx-background-color:white;-fx-background-radius:15px;-fx-border-color:#F0DCE8;-fx-border-radius:15px;");if(!title.isEmpty()){Label l=new Label(title);l.setFont(Font.font("Arial",FontWeight.BOLD,18));l.setTextFill(Color.web(DARK));b.getChildren().add(l);}return b;}
    private HBox memberRow(MemberRecord m){HBox r=new HBox(12);r.setAlignment(Pos.CENTER_LEFT);Label name=new Label(m.name);name.setFont(Font.font("Arial",FontWeight.BOLD,14));name.setTextFill(Color.web(DARK));Label role=new Label(m.role);role.setStyle("-fx-background-color:#FFF0F6;-fx-text-fill:"+PURPLE+";-fx-padding:4 8;-fx-background-radius:8;");Label email=new Label(m.email);email.setTextFill(Color.web(GREY));Region s=new Region();HBox.setHgrow(s,Priority.ALWAYS);r.getChildren().addAll(name,role,email,s,new Label(m.id));return r;}

    public static class MemberRecord {
        public String role,id,name,email,phone,qualification,address,extra,photo;
        public MemberRecord(String role,String id,String name,String email,String phone,String qualification,String address,String extra,String photo){this.role=role;this.id=id;this.name=name;this.email=email;this.phone=phone;this.qualification=qualification;this.address=address;this.extra=extra;this.photo=photo;}
    }

    public static List<MemberRecord> loadAllMembers(){
        List<MemberRecord> list=new ArrayList<>();
        try{
            Firestore db=FirebaseConfig.getFirestore();
            QuerySnapshot mothers=db.collection("mothers").get().get();
            for(DocumentSnapshot d:mothers.getDocuments()){MotherWlcModel m=mother(d);if(m!=null)list.add(new MemberRecord("Mother",safe(m.getMotherId(),d.getId()),safe(m.getName(),"Mother"),safe(m.getPhone(),""),safe(m.getPhone(),""),safe(m.getBloodGroup(),""),safe(m.getAddress(),safe(m.getLocation(),"")),"DOB: "+safe(String.valueOf(m.getDateOfBirth()),""),safe(m.getProfilePhotoPath(),"")));}
            QuerySnapshot doctors=db.collection("doctors").get().get();
            for(DocumentSnapshot d:doctors.getDocuments()){DoctorProfileModel x=doctor(d);list.add(new MemberRecord("Doctor",d.getId(),x.getFullName().isEmpty()?"Doctor":x.getFullName(),x.getEmail(),x.getPhone(),x.getQualification(),x.getAddress(),"Specialization: "+x.getSpecialization()+" | License: "+x.getMedicalLicense(),getString(d,"photoUrl")));}
            for(AshaProfileModel x:new Ashaprofilecontroller().getAllAshaProfiles()) list.add(new MemberRecord("ASHA Worker",safe(x.getAshaId(),""),safe(x.getName(),"ASHA Worker"),safe(x.getEmail(),""),safe(x.getPhoneNumber(),""),"ASHA Worker",safe(x.getAddress(),""),"Joining: "+safe(x.getDateOfJoining(),"")+" | "+(x.isActive()?"Active":"Inactive"),safe(x.getProfileImage(),"")));
            for(HospitalAdminProfile x:new HospitalAdminProfileController().getAllHospitalAdminProfiles()) list.add(new MemberRecord("Hospital",safe(x.getHospitalName(),"hospital"),safe(x.getHospitalName(),"Hospital"),safe(x.getHospitalEmail(),safe(x.getEmail(),"")),safe(x.getPhoneNumber(),safe(x.getContactNumber(),"")),safe(x.getHospitalType(),"Hospital"),safe(x.getAddress(),""),"Admin: "+safe(x.getAdminName(),"")+" | Verified: "+x.isVerified(),safe(x.getProfileImageUrl(),"")));
        }catch(Exception e){e.printStackTrace();}
        return list;
    }
    private static MotherWlcModel mother(DocumentSnapshot d){try{MotherWlcModel m=new MotherWlcModel();m.setMotherId(getString(d,"motherId"));m.setName(getString(d,"name"));m.setLocation(getString(d,"location"));m.setAddress(getString(d,"address"));m.setPhone(getString(d,"phone"));m.setBloodGroup(getString(d,"bloodGroup"));m.setMedicalCondition(getString(d,"medicalCondition"));m.setAllergies(getString(d,"allergies"));m.setMaritalStatus(getString(d,"maritalStatus"));m.setProfilePhotoPath(getString(d,"profilePhotoPath"));String dob=getString(d,"dateOfBirth"),lmp=getString(d,"lmpDate"),edd=getString(d,"eddDate");if(!dob.isEmpty())m.setDateOfBirth(LocalDate.parse(dob));if(!lmp.isEmpty())m.setLmpDate(LocalDate.parse(lmp));if(!edd.isEmpty())m.setEddDate(LocalDate.parse(edd));Object w=d.get("weight");if(w instanceof Number)m.setWeight(((Number)w).doubleValue());return m;}catch(Exception e){return null;}}
    private static DoctorProfileModel doctor(DocumentSnapshot d){DoctorProfileModel x=new DoctorProfileModel();x.setFirstName(getString(d,"firstName"));x.setLastName(getString(d,"lastName"));x.setGender(getString(d,"gender"));x.setDob(getString(d,"dob"));x.setPhone(getString(d,"phone"));x.setEmail(getString(d,"email"));x.setAddress(getString(d,"address"));x.setSpecialization(getString(d,"specialization"));x.setQualification(getString(d,"qualification"));x.setExperience(getString(d,"experience"));x.setMedicalLicense(getString(d,"medicalLicense"));x.setClinicName(getString(d,"clinicName"));x.setClinicAddress(getString(d,"clinicAddress"));return x;}
    private static String getString(DocumentSnapshot d,String f){Object v=d.get(f);return v==null?"":String.valueOf(v).trim();}
    private static String safe(String a,String b){return a==null||a.trim().isEmpty()?b:a.trim();}

    public static MemberRecord findMember(String role,String id){for(MemberRecord m:loadAllMembers())if(m.role.equals(role)&&m.id.equals(id))return m;return null;}
}
