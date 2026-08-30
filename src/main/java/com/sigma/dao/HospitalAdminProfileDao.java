

package com.sigma.dao;

import java.util.ArrayList;
import java.util.List;

import com.sigma.config.FirebaseConfig;
import com.sigma.model.HospitalAdminProfile;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

public class HospitalAdminProfileDao {

    private Firestore db = FirebaseConfig.getFirestore();

    // =====================================================
    // DOCUMENT ID
    // =====================================================

    private static final String DOCUMENT_ID = "hospitalAdmin";


    // =====================================================
    // SAVE
    // =====================================================

    public void saveHospitalAdminProfile(
            HospitalAdminProfile profile) {

        try {

            db.collection("hospitalAdminProfiles")
              .document(DOCUMENT_ID)
             // .create(profile)
             .set(profile)
              .get();

            System.out.println(
                    "Hospital Admin Profile Data Inserted"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // =====================================================
    // GET ONE
    // =====================================================

    public HospitalAdminProfile getHospitalAdminProfile() {

        try {

            ApiFuture<DocumentSnapshot> future =
                    db.collection("hospitalAdminProfiles")
                      .document(DOCUMENT_ID)
                      .get();

            DocumentSnapshot document =
                    future.get();

            if (document.exists()) {

                return document.toObject(
                        HospitalAdminProfile.class
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }


    // =====================================================
    // UPDATE
    // =====================================================

    public void updateHospitalAdminProfile(
            HospitalAdminProfile profile) {

        try {

            db.collection("hospitalAdminProfiles")
              .document(DOCUMENT_ID)
              .set(profile)
              .get();

            System.out.println(
                    "Hospital Admin Profile Data Updated"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // =====================================================
    // DELETE
    // =====================================================

    public void deleteHospitalAdminProfile() {

        try {

            db.collection("hospitalAdminProfiles")
              .document(DOCUMENT_ID)
              .delete()
              .get();

            System.out.println(
                    "Hospital Admin Profile Data Deleted"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // =====================================================
    // GET ALL
    // =====================================================

    public List<HospitalAdminProfile>
            getHospitalAdminProfiles() {

        List<HospitalAdminProfile> list =
                new ArrayList<>();

        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection(
                            "hospitalAdminProfiles"
                    ).get();

            QuerySnapshot snapshot =
                    future.get();

            for (
                    DocumentSnapshot doc :
                    snapshot.getDocuments()
            ) {

                HospitalAdminProfile profile =
                        doc.toObject(
                                HospitalAdminProfile.class
                        );

                if (profile != null) {

                    list.add(profile);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }   

// =====================================================
// UPDATE NOTIFICATION SETTINGS
// =====================================================

public void updateNotificationSettings(
        boolean appointmentNotification,
        boolean labNotification,
        boolean emergencyNotification,
        boolean bedNotification) {

    try {

        db.collection("hospitalAdminProfiles")
          .document(DOCUMENT_ID)
          .update(
                  "appointmentNotification",
                  appointmentNotification,

                  "labNotification",
                  labNotification,

                  "emergencyNotification",
                  emergencyNotification,

                  "bedNotification",
                  bedNotification
          )
          .get();

        System.out.println(
                "Notification Settings Updated in Firebase"
        );

    } catch (Exception e) {

        e.printStackTrace();
    }
}  

// =====================================================
// UPDATE PROFILE IMAGE
// =====================================================

public void updateProfileImage(String imageUrl) {

    try {

        db.collection("hospitalAdminProfiles")
          .document(DOCUMENT_ID)
          .update(
                  "profileImageUrl",
                  imageUrl
          )
          .get();

        System.out.println(
                "Profile Image URL Updated in Firebase"
        );

    } catch (Exception e) {

        e.printStackTrace();
    }
}






}