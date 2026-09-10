package com.sigma.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.sigma.config.FirebaseConfig;
import com.sigma.model.BedBooking;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;


public class BedBookingDao {

    private final Firestore db = FirebaseConfig.getFirestore();


    // =====================================================
    // SAVE BED BOOKING
    // =====================================================

    public void saveBedBooking(BedBooking booking) {

        try {

            db.collection("bedBookings")
              .document(booking.getBookingID())
              .set(booking)
              .get();

            System.out.println("Bed Booking Data Inserted");

            System.out.println(
                    "Booking ID: "
                            + booking.getBookingID()
            );

            System.out.println(
                    "Mother UID: "
                            + booking.getMotherUid()
            );

        } catch (Exception e) {

            System.err.println(
                    "Error saving bed booking:"
            );

            e.printStackTrace();
        }
    }


    // =====================================================
    // GET ONE BED BOOKING
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

            System.err.println(
                    "Error getting bed booking:"
            );

            e.printStackTrace();
        }

        return null;
    }


    // =====================================================
    // UPDATE BED BOOKING
    // PRESERVE MOTHER UID
    // =====================================================

    public void updateBedBooking(BedBooking booking) {

        try {

            // -------------------------------------------------
            // Get existing booking from Firebase
            // -------------------------------------------------

            BedBooking existingBooking =
                    getBedBooking(
                            booking.getBookingID()
                    );


            // -------------------------------------------------
            // Preserve motherUid
            // -------------------------------------------------

            if (existingBooking != null) {

                String existingMotherUid =
                        existingBooking.getMotherUid();

                String newMotherUid =
                        booking.getMotherUid();


                if (newMotherUid == null ||
                        newMotherUid.trim().isEmpty()) {

                    booking.setMotherUid(
                            existingMotherUid
                    );
                }
            }


            // -------------------------------------------------
            // Update Firebase document
            // -------------------------------------------------

            db.collection("bedBookings")
              .document(booking.getBookingID())
              .set(booking)
              .get();


            System.out.println(
                    "Bed Booking Data Updated"
            );

            System.out.println(
                    "Booking ID: "
                            + booking.getBookingID()
            );

            System.out.println(
                    "Mother UID: "
                            + booking.getMotherUid()
            );

            System.out.println(
                    "Status: "
                            + booking.getStatus()
            );

            System.out.println(
                    "Bed No: "
                            + booking.getBedNo()
            );


        } catch (Exception e) {

            System.err.println(
                    "Error updating bed booking:"
            );

            e.printStackTrace();
        }
    }


    // =====================================================
    // DELETE BED BOOKING
    // =====================================================

    public void deleteBedBooking(String bookingID) {

        try {

            db.collection("bedBookings")
              .document(bookingID)
              .delete()
              .get();

            System.out.println(
                    "Bed Booking Data Deleted"
            );

            System.out.println(
                    "Booking ID: "
                            + bookingID
            );

        } catch (Exception e) {

            System.err.println(
                    "Error deleting bed booking:"
            );

            e.printStackTrace();
        }
    }


    // =====================================================
    // GET ALL BED BOOKINGS
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


            for (DocumentSnapshot doc :
                    snapshot.getDocuments()) {

                BedBooking booking =
                        doc.toObject(
                                BedBooking.class
                        );

                if (booking != null) {

                    list.add(booking);
                }
            }


        } catch (Exception e) {

            System.err.println(
                    "Error getting all bed bookings:"
            );

            e.printStackTrace();
        }

        return list;
    }


    // =====================================================
    // REALTIME ALL BED BOOKINGS LISTENER
    // EXISTING FUNCTION
    // =====================================================

    public ListenerRegistration listenToBedBookings(
            Consumer<List<BedBooking>> callback) {


        return db.collection("bedBookings")
                .addSnapshotListener(
                        (snapshot, error) -> {


                    // -----------------------------------------
                    // Error
                    // -----------------------------------------

                    if (error != null) {

                        System.err.println(
                                "Error listening to bed bookings:"
                        );

                        error.printStackTrace();

                        return;
                    }


                    List<BedBooking> list =
                            new ArrayList<>();


                    // -----------------------------------------
                    // Read Firebase data
                    // -----------------------------------------

                    if (snapshot != null) {

                        for (DocumentSnapshot doc :
                                snapshot.getDocuments()) {


                            BedBooking booking =
                                    doc.toObject(
                                            BedBooking.class
                                    );


                            if (booking != null) {

                                list.add(booking);
                            }
                        }
                    }


                    // -----------------------------------------
                    // Send data to callback
                    // -----------------------------------------

                    if (callback != null) {

                        callback.accept(list);
                    }

                });
    }


    // =====================================================
    // REALTIME MOTHER-SPECIFIC BED BOOKING LISTENER
    // =====================================================

    public ListenerRegistration listenToBedBookingsForMother(
            String motherUid,
            Consumer<List<BedBooking>> callback) {


        // -------------------------------------------------
        // Invalid UID check
        // -------------------------------------------------

        if (motherUid == null ||
                motherUid.trim().isEmpty()) {

            System.err.println(
                    "Cannot listen for bed bookings: Mother UID is empty."
            );

            return null;
        }


        // -------------------------------------------------
        // Firebase query
        // Only current mother's bookings
        // -------------------------------------------------

        return db.collection("bedBookings")
                .whereEqualTo(
                        "motherUid",
                        motherUid
                )
                .addSnapshotListener(
                        (snapshot, error) -> {


                    // -----------------------------------------
                    // Error
                    // -----------------------------------------

                    if (error != null) {

                        System.err.println(
                                "Error listening to mother's bed bookings:"
                        );

                        error.printStackTrace();

                        return;
                    }


                    List<BedBooking> list =
                            new ArrayList<>();


                    // -----------------------------------------
                    // Read bookings
                    // -----------------------------------------

                    if (snapshot != null) {

                        for (DocumentSnapshot doc :
                                snapshot.getDocuments()) {


                            BedBooking booking =
                                    doc.toObject(
                                            BedBooking.class
                                    );


                            if (booking != null) {

                                list.add(booking);
                            }
                        }
                    }


                    // -----------------------------------------
                    // Send data to callback
                    // -----------------------------------------

                    if (callback != null) {

                        callback.accept(list);
                    }

                });
    }
}