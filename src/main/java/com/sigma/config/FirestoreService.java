
package com.sigma.config;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import com.google.firebase.cloud.FirestoreClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class FirestoreService {

    private final Firestore db;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public FirestoreService() {

        try {

            // Initialize Firebase
            FirebaseConfig.getFirebaseApp();

            // Connect to Firestore
            db = FirestoreClient.getFirestore();

            System.out.println(
                    "Firestore connected successfully!");

        } catch (Exception e) {

            System.out.println(
                    "Firestore connection failed!");

            e.printStackTrace();

            throw new RuntimeException(
                    "Unable to connect to Firestore.",
                    e);
        }
    }

    // =====================================================
    // ADD DOCUMENT
    // =====================================================

    public String addDocument(
            String collection,
            Map<String, Object> data) {

        try {

            DocumentReference document = db.collection(collection)
                    .document();

            ApiFuture<WriteResult> future = document.set(data);

            future.get();

            System.out.println(
                    "Document added: "
                            + document.getId());

            return document.getId();

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            e.printStackTrace();

            return null;

        } catch (ExecutionException e) {

            e.printStackTrace();

            return null;
        }
    }

    // =====================================================
    // GET ALL DOCUMENTS
    // =====================================================

    public List<QueryDocumentSnapshot> getDocuments(
            String collection) {

        List<QueryDocumentSnapshot> documents = new ArrayList<>();

        try {

            ApiFuture<QuerySnapshot> future = db.collection(collection)
                    .get();

            QuerySnapshot snapshot = future.get();

            documents.addAll(
                    snapshot.getDocuments());

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            e.printStackTrace();

        } catch (ExecutionException e) {

            e.printStackTrace();
        }

        return documents;
    }

    // =====================================================
    // UPDATE DOCUMENT
    // =====================================================

    public boolean updateDocument(
            String collection,
            String documentId,
            Map<String, Object> data) {

        try {

            ApiFuture<WriteResult> future = db.collection(collection)
                    .document(documentId)
                    .update(data);

            future.get();

            System.out.println(
                    "Document updated: "
                            + documentId);

            return true;

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            e.printStackTrace();

            return false;

        } catch (ExecutionException e) {

            e.printStackTrace();

            return false;
        }
    }

    // =====================================================
    // DELETE DOCUMENT
    // =====================================================

    public boolean deleteDocument(
            String collection,
            String documentId) {

        try {

            ApiFuture<WriteResult> future = db.collection(collection)
                    .document(documentId)
                    .delete();

            future.get();

            System.out.println(
                    "Document deleted: "
                            + documentId);

            return true;

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            e.printStackTrace();

            return false;

        } catch (ExecutionException e) {

            e.printStackTrace();

            return false;
        }
    }
}
