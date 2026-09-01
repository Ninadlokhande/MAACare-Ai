package com.sigma.dao;

import java.util.ArrayList;
import java.util.List;

import com.sigma.config.FirebaseConfig;
import com.sigma.model.BedBooking;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

public class BedBookingDao {

    private Firestore db = FirebaseConfig.getFirestore();

    // =====================================================
    // SAVE
    // =====================================================

    public void saveBedBooking(BedBooking booking) {

        try {

            db.collection("bedBookings")
              .document(booking.getBookingID())
              .set(booking)
              .get();

            System.out.println("Bed Booking Data Inserted");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =====================================================
    // GET ONE
    // =====================================================

    public BedBooking getBedBooking(String bookingID) {

        try {

            ApiFuture<DocumentSnapshot> future =
                    db.collection("bedBookings")
                      .document(bookingID)
                      .get();

            DocumentSnapshot document =
                    future.get();

            if (document.exists()) {

                return document.toObject(
                        BedBooking.class
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

    public void updateBedBooking(
            BedBooking booking
    ) {

        try {

            db.collection("bedBookings")
              .document(booking.getBookingID())
              .set(booking)
              .get();

            System.out.println(
                    "Bed Booking Data Updated"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =====================================================
    // DELETE
    // =====================================================

    public void deleteBedBooking(
            String bookingID
    ) {

        try {

            db.collection("bedBookings")
              .document(bookingID)
              .delete()
              .get();

            System.out.println(
                    "Bed Booking Data Deleted"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =====================================================
    // GET ALL
    // =====================================================

    public List<BedBooking> getBedBookings() {

        List<BedBooking> list =
                new ArrayList<>();

        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection("bedBookings")
                      .get();

            QuerySnapshot snapshot =
                    future.get();

            for (
                    DocumentSnapshot doc :
                    snapshot.getDocuments()
            ) {

                BedBooking booking =
                        doc.toObject(
                                BedBooking.class
                        );

                if (booking != null) {

                    list.add(booking);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }
}