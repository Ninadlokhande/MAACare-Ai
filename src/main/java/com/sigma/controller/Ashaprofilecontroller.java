package com.sigma.controller;

import java.util.List;
import com.sigma.dao.AshaProfileDAO;
import com.sigma.model.AshaProfileModel;

public class Ashaprofilecontroller {

    private AshaProfileDAO dao = new AshaProfileDAO();

    public void addAshaProfile(
            String name,
            String ashaId,
            String phoneNumber,
            String email,
            String address,
            String dateOfJoining,
            String profileImage,
            boolean active) {

        AshaProfileModel profile = new AshaProfileModel(
                name,
                ashaId,
                phoneNumber,
                email,
                address,
                dateOfJoining,
                profileImage,
                active
        );
        dao.saveAshaProfile(profile);
    }

    public AshaProfileModel getAshaProfile(String ashaId) {
        return dao.getAshaProfile(ashaId);
    }

    public void updateAshaProfile(
            String name,
            String ashaId,
            String phoneNumber,
            String email,
            String address,
            String dateOfJoining,
            String profileImage,
            boolean active) {

        AshaProfileModel profile = new AshaProfileModel(
                name,
                ashaId,
                phoneNumber,
                email,
                address,
                dateOfJoining,
               profileImage,
                active
        );
        dao.updateAshaProfile(profile);
    }

    public void deleteAshaProfile(String ashaId) {
        dao.deleteAshaProfile(ashaId);
    }

    public List<AshaProfileModel> getAllAshaProfiles() {
        return dao.getAshaProfiles();
    }
}