package com.sigma.controller.HospitalController;

import java.util.List;

import com.sigma.dao.HospitalAdminProfileDao;
import com.sigma.model.HospitalAdminProfile;

public class HospitalAdminProfileController {

    HospitalAdminProfileDao dao =
            new HospitalAdminProfileDao();


    // =====================================================
    // ADD / SAVE HOSPITAL ADMIN PROFILE
    // =====================================================

    public void addHospitalAdminProfile(
            String hospitalName,
            String phoneNumber,
            String hospitalType,
            String address,
            String hospitalEmail,
            String adminName,
            String role,
            String email,
            String contactNumber,
            String memberSince,
            boolean verified,
            String establishedYear,
            String lastLoginDate,
            String lastLoginTime,
           // String loginIp,
           // String loginLocation,
            String activeSession,
            String sessionDuration,
            String accountStatus,
            String systemStatus) {


        HospitalAdminProfile profile =
                new HospitalAdminProfile(

                        hospitalName,
                        phoneNumber,
                        hospitalType,
                        address,
                        hospitalEmail,

                        adminName,
                        role,
                        email,
                        contactNumber,
                        memberSince,
                        verified,

                        establishedYear,

                        lastLoginDate,
                        lastLoginTime,
                        //loginIp,
                       // loginLocation,
                        activeSession,
                        sessionDuration,
                        accountStatus,
                        systemStatus
                );


        dao.saveHospitalAdminProfile(profile);
    }


    // =====================================================
    // GET HOSPITAL ADMIN PROFILE
    // =====================================================

    public HospitalAdminProfile getHospitalAdminProfile() {

        return dao.getHospitalAdminProfile();
    }


    // =====================================================
    // UPDATE HOSPITAL ADMIN PROFILE
    // =====================================================

    public void updateHospitalAdminProfile(
            String hospitalName,
            String phoneNumber,
            String hospitalType,
            String address,
            String hospitalEmail,
            String adminName,
            String role,
            String email,
            String contactNumber,
            String memberSince,
            boolean verified,
            String establishedYear,
            String lastLoginDate,
            String lastLoginTime,
           // String loginIp,
           // String loginLocation,
            String activeSession,
            String sessionDuration,
            String accountStatus,
            String systemStatus) {


        HospitalAdminProfile profile =
                new HospitalAdminProfile(

                        hospitalName,
                        phoneNumber,
                        hospitalType,
                        address,
                        hospitalEmail,

                        adminName,
                        role,
                        email,
                        contactNumber,
                        memberSince,
                        verified,

                        establishedYear,

                        lastLoginDate,
                        lastLoginTime,
                       // loginIp,
                       // loginLocation,
                        activeSession,
                        sessionDuration,
                        accountStatus,
                        systemStatus
                );


        dao.updateHospitalAdminProfile(profile);
    }


    // =====================================================
    // DELETE HOSPITAL ADMIN PROFILE
    // =====================================================

    public void deleteHospitalAdminProfile() {

        dao.deleteHospitalAdminProfile();
    }


    // =====================================================
    // GET ALL HOSPITAL ADMIN PROFILES
    // =====================================================

    public List<HospitalAdminProfile>
            getAllHospitalAdminProfiles() {

        return dao.getHospitalAdminProfiles();
    }  
// =====================================================
// UPDATE NOTIFICATION SETTINGS
// =====================================================

public void updateNotificationSettings(
        boolean appointmentNotification,
        boolean labNotification,
        boolean emergencyNotification,
        boolean bedNotification) {

    dao.updateNotificationSettings(
            appointmentNotification,
            labNotification,
            emergencyNotification,
            bedNotification
    );
} 


// =====================================================
// UPDATE PROFILE IMAGE
// =====================================================

public void updateProfileImage(String imageUrl) {

    dao.updateProfileImage(imageUrl);
}




}