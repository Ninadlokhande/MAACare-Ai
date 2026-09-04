package com.sigma.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.SetOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.sigma.model.MotherVaccinationmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MotherVaccinationDAO {

    private final Firestore db;

    private static final String COLLECTION =
            "motherVaccinations";

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public MotherVaccinationDAO() {

        db = FirestoreClient.getFirestore();

        System.out.println(
                "MotherVaccinationDAO connected to Firebase"
        );
    }

    // =========================================================
    // GET ALL
    // =========================================================

    public List<MotherVaccinationmodel>
    getAllVaccinations() {

        List<MotherVaccinationmodel> vaccinations =
                new ArrayList<>();

        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection(COLLECTION)
                      .get();

            QuerySnapshot snapshot =
                    future.get();

            for (QueryDocumentSnapshot document :
                    snapshot.getDocuments()) {

                MotherVaccinationmodel vaccination =
                        documentToVaccination(document);

                if (vaccination != null) {
                    vaccinations.add(vaccination);
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Error fetching vaccinations"
            );

            e.printStackTrace();
        }

        return vaccinations;
    }

    // =========================================================
    // GET BY MOTHER ID
    // =========================================================

    public List<MotherVaccinationmodel>
    getVaccinationsByMotherId(
            String motherId) {

        List<MotherVaccinationmodel> vaccinations =
                new ArrayList<>();

        if (motherId == null ||
            motherId.trim().isEmpty()) {

            return vaccinations;
        }

        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection(COLLECTION)
                      .whereEqualTo(
                              "motherId",
                              motherId
                      )
                      .get();

            QuerySnapshot snapshot =
                    future.get();

            for (QueryDocumentSnapshot document :
                    snapshot.getDocuments()) {

                MotherVaccinationmodel vaccination =
                        documentToVaccination(document);

                if (vaccination != null) {
                    vaccinations.add(vaccination);
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Error fetching vaccinations for mother: "
                            + motherId
            );

            e.printStackTrace();
        }

        return vaccinations;
    }

    // =========================================================
    // GET BY ID
    // =========================================================

    public MotherVaccinationmodel
    getVaccinationById(String id) {

        if (id == null ||
            id.trim().isEmpty()) {

            return null;
        }

        try {

            DocumentSnapshot document =
                    db.collection(COLLECTION)
                      .document(id)
                      .get()
                      .get();

            if (!document.exists()) {
                return null;
            }

            return documentToVaccination(document);

        } catch (Exception e) {

            System.out.println(
                    "Error fetching vaccination: "
                            + id
            );

            e.printStackTrace();

            return null;
        }
    }

    // =========================================================
    // GET BY PERSON
    // =========================================================

    public List<MotherVaccinationmodel>
    getVaccinationsByPerson(
            String motherId,
            String person) {

        List<MotherVaccinationmodel> result =
                new ArrayList<>();

        List<MotherVaccinationmodel> all =
                getVaccinationsByMotherId(motherId);

        if (person == null) {
            return result;
        }

        for (MotherVaccinationmodel vaccination : all) {

            if (person.equalsIgnoreCase(
                    vaccination.getPerson())) {

                result.add(vaccination);
            }
        }

        return result;
    }

    // =========================================================
    // GET BY STATUS
    // =========================================================

    public List<MotherVaccinationmodel>
    getVaccinationsByStatus(
            String motherId,
            String status) {

        List<MotherVaccinationmodel> result =
                new ArrayList<>();

        List<MotherVaccinationmodel> all =
                getVaccinationsByMotherId(motherId);

        if (status == null) {
            return result;
        }

        for (MotherVaccinationmodel vaccination : all) {

            if (status.equalsIgnoreCase(
                    vaccination.getStatus())) {

                result.add(vaccination);
            }
        }

        return result;
    }

    // =========================================================
    // SAVE
    // =========================================================

    public boolean saveVaccination(
            MotherVaccinationmodel vaccination) {

        if (vaccination == null) {
            return false;
        }

        if (vaccination.getId() == null ||
            vaccination.getId().trim().isEmpty()) {

            return false;
        }

        try {

            Map<String, Object> data =
                    vaccinationToMap(vaccination);

            db.collection(COLLECTION)
              .document(vaccination.getId())
              .set(data)
              .get();

            System.out.println(
                    "Vaccination saved successfully: "
                            + vaccination.getId()
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Error saving vaccination"
            );

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // UPDATE
    // =========================================================

    public boolean updateVaccination(
            MotherVaccinationmodel vaccination) {

        if (vaccination == null ||
            vaccination.getId() == null ||
            vaccination.getId().trim().isEmpty()) {

            return false;
        }

        try {

            Map<String, Object> data =
                    vaccinationToMap(vaccination);

            db.collection(COLLECTION)
              .document(vaccination.getId())
              .set(
                      data,
                      SetOptions.merge()
              )
              .get();

            System.out.println(
                    "Vaccination updated successfully: "
                            + vaccination.getId()
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Error updating vaccination"
            );

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // DELETE
    // =========================================================

    public boolean deleteVaccination(
            String id) {

        if (id == null ||
            id.trim().isEmpty()) {

            return false;
        }

        try {

            db.collection(COLLECTION)
              .document(id)
              .delete()
              .get();

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Error deleting vaccination"
            );

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // MARK AS COMPLETED
    // =========================================================

    public boolean markAsCompleted(
            String id,
            String completedDate) {

        if (id == null ||
            id.trim().isEmpty()) {

            return false;
        }

        try {

            Map<String, Object> updates =
                    new HashMap<>();

            updates.put(
                    "status",
                    "Completed"
            );

            updates.put(
                    "completedDate",
                    completedDate
            );

            db.collection(COLLECTION)
              .document(id)
              .set(
                      updates,
                      SetOptions.merge()
              )
              .get();

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Error marking vaccination completed"
            );

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // MODEL → FIRESTORE
    // =========================================================

    private Map<String, Object>
    vaccinationToMap(
            MotherVaccinationmodel vaccination) {

        Map<String, Object> data =
                new HashMap<>();

        data.put(
                "id",
                vaccination.getId()
        );

        data.put(
                "motherId",
                vaccination.getMotherId()
        );

        data.put(
                "person",
                vaccination.getPerson()
        );

        data.put(
                "vaccineName",
                vaccination.getVaccineName()
        );

        data.put(
                "dose",
                vaccination.getDose()
        );

        data.put(
                "dueDate",
                vaccination.getDueDate()
        );

        data.put(
                "completedDate",
                vaccination.getCompletedDate()
        );

        data.put(
                "status",
                vaccination.getStatus()
        );

        data.put(
                "description",
                vaccination.getDescription()
        );

        return data;
    }

    // =========================================================
    // FIRESTORE → MODEL
    // =========================================================

    private MotherVaccinationmodel
    documentToVaccination(
            DocumentSnapshot document) {

        try {

            MotherVaccinationmodel vaccination =
                    new MotherVaccinationmodel();

            vaccination.setId(
                    document.getString("id")
            );

            vaccination.setMotherId(
                    document.getString("motherId")
            );

            vaccination.setPerson(
                    document.getString("person")
            );

            vaccination.setVaccineName(
                    document.getString("vaccineName")
            );

            vaccination.setDose(
                    document.getString("dose")
            );

            vaccination.setDueDate(
                    document.getString("dueDate")
            );

            vaccination.setCompletedDate(
                    document.getString("completedDate")
            );

            vaccination.setStatus(
                    document.getString("status")
            );

            vaccination.setDescription(
                    document.getString("description")
            );

            return vaccination;

        } catch (Exception e) {

            System.out.println(
                    "Error converting vaccination document"
            );

            e.printStackTrace();

            return null;
        }
    }
}