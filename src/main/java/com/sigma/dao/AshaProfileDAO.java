package com.sigma.dao;

import java.util.ArrayList;
import java.util.List;

import com.sigma.model.AshaProfileModel;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import java.io.File;
import com.sigma.Cloudinary.CloudinaryService;
import com.sigma.config.FirebaseConfig;


public class AshaProfileDAO {

    private Firestore db = FirebaseConfig.getFirestore();
    private CloudinaryService cloudinaryService =
       new CloudinaryService();


    // =========================================================
    // 1. ASHA PROFILE SAVE
    // =========================================================

    public void saveAshaProfile(AshaProfileModel profile) {

        try {

            ApiFuture<WriteResult> future =
                    db.collection("AshaProfiles")
                      .document(profile.getAshaId())
                      .set(profile);

            WriteResult result = future.get();

            System.out.println(
                    "Asha Profile Saved Successfully: "
                    + result.getUpdateTime()
            );

        } catch (Exception e) {

            System.out.println(
                    "ERROR: Asha Profile Save Failed"
            );

            e.printStackTrace();
        }
    }


    // =========================================================
    // 2. ASHA PROFILE FETCH
    // =========================================================

    public AshaProfileModel getAshaProfile(String ashaId) {

        try {

            ApiFuture<DocumentSnapshot> future =
                    db.collection("AshaProfiles")
                      .document(ashaId)
                      .get();

            DocumentSnapshot document =
                    future.get();

            if (document.exists()) {

                System.out.println(
                        "Asha Profile Found: " + ashaId
                );

                return document.toObject(
                        AshaProfileModel.class
                );
            }

            System.out.println(
                    "Asha Profile Not Found: " + ashaId
            );

        } catch (Exception e) {

            System.out.println(
                    "ERROR: Fetch Asha Profile Failed"
            );

            e.printStackTrace();
        }

        return null;
    }


    // =========================================================
    // 3. ASHA PROFILE UPDATE / CREATE
    // =========================================================

    public void updateAshaProfile(
            AshaProfileModel profile) {

        try {

            ApiFuture<WriteResult> future =
                    db.collection("AshaProfiles")
                      .document(profile.getAshaId())
                      .set(profile);

            WriteResult result =
                    future.get();

            System.out.println(
                    "Asha Profile Updated Successfully: "
                    + result.getUpdateTime()
            );

        } catch (Exception e) {

            System.out.println(
                    "ERROR: Asha Profile Update Failed"
            );

            e.printStackTrace();
        }
    }


    // =========================================================
    // 4. ASHA PROFILE DELETE
    // =========================================================

    public void deleteAshaProfile(String ashaId) {

        try {

            ApiFuture<WriteResult> future =
                    db.collection("AshaProfiles")
                      .document(ashaId)
                      .delete();

            WriteResult result =
                    future.get();

            System.out.println(
                    "Asha Profile Deleted Successfully: "
                    + result.getUpdateTime()
            );

        } catch (Exception e) {

            System.out.println(
                    "ERROR: Asha Profile Delete Failed"
            );

            e.printStackTrace();
        }
    }


    // =========================================================
    // 5. GET ALL ASHA PROFILES
    // =========================================================

    public List<AshaProfileModel> getAshaProfiles() {

        List<AshaProfileModel> list =
                new ArrayList<>();

        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection("AshaProfiles")
                      .get();

            QuerySnapshot snapshot =
                    future.get();

            for (DocumentSnapshot doc :
                    snapshot.getDocuments()) {

                AshaProfileModel profile =
                        doc.toObject(
                                AshaProfileModel.class
                        );

                if (profile != null) {
                    list.add(profile);
                }
            }

            System.out.println(
                    "Total Asha Profiles: "
                    + list.size()
            );

        } catch (Exception e) {

            System.out.println(
                    "ERROR: Fetch All Asha Profiles Failed"
            );

            e.printStackTrace();
        }

        return list;
    }
    public String uploadProfileImage(
        String ashaId,
        File imageFile) {

    try {

        // 1. Upload image to Cloudinary
        String imageUrl =
                cloudinaryService.uploadProfileImage(
                        imageFile
                );

        System.out.println(
                "Image uploaded to Cloudinary successfully!"
        );

        System.out.println(
                "Image URL: " + imageUrl
        );

        // 2. Save image URL in Firestore
        db.collection("AshaProfiles")
                .document(ashaId)
                .update(
                        "profileImage",
                        imageUrl
                )
                .get();

        System.out.println(
                "Profile image URL saved in Firestore!"
        );

        return imageUrl;

    } catch (Exception e) {

        System.out.println(
                "ERROR: Profile image upload failed"
        );

        e.printStackTrace();

        return null;
    }
}

}