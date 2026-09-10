package com.sigma.controller.HospitalController;

import java.util.List;
import java.util.function.Consumer;

import com.google.cloud.firestore.ListenerRegistration;
import com.sigma.dao.BedBookingDao;
import com.sigma.model.BedBooking;

public class BedBookingController {

    private final BedBookingDao dao = new BedBookingDao();

    private final NotificationController notificationController =
            new NotificationController();

    // =====================================================
    // ADD BED BOOKING
    // EXISTING METHOD - KEPT UNCHANGED
    // =====================================================

    public void addBedBooking(
            String number,
            String bookingID,
            String patientName,
            String hospitalName,
            String department,
            String bedNo,
            String bedType,
            String checkinDate,
            String expectedCheckout,
            String status) {

        BedBooking booking =
                new BedBooking(
                        number,
                        bookingID,
                        patientName,
                        hospitalName,
                        department,
                        bedNo,
                        bedType,
                        checkinDate,
                        expectedCheckout,
                        status
                );

        dao.saveBedBooking(booking);

        // =================================================
        // NOTIFICATION
        // =================================================

        notificationController.addNotification(
                number,
                "New bed booking added",
                "BED_BOOKING"
        );
    }

    // =====================================================
    // ADD BED BOOKING WITH MOTHER UID
    // NEW METHOD - FOR MOTHER SIDE ONLY
    // =====================================================

    public void addBedBooking(
            String number,
            String bookingID,
            String motherUid,
            String patientName,
            String hospitalName,
            String department,
            String bedNo,
            String bedType,
            String checkinDate,
            String expectedCheckout,
            String status) {

        BedBooking booking =
                new BedBooking(
                        number,
                        bookingID,
                        patientName,
                        hospitalName,
                        department,
                        bedNo,
                        bedType,
                        checkinDate,
                        expectedCheckout,
                        status
                );

        booking.setMotherUid(motherUid);

        dao.saveBedBooking(booking);

        // =================================================
        // NOTIFICATION
        // =================================================

        notificationController.addNotification(
                number,
                "New bed booking added",
                "BED_BOOKING"
        );
    }

    // =====================================================
    // GET ONE BED BOOKING
    // =====================================================

    public BedBooking getBedBooking(String bookingID) {

        return dao.getBedBooking(bookingID);
    }

    // =====================================================
    // UPDATE BED BOOKING
    // EXISTING METHOD - UNCHANGED
    // =====================================================

    public void updateBedBooking(
            String number,
            String bookingID,
            String patientName,
            String hospitalName,
            String department,
            String bedNo,
            String bedType,
            String checkinDate,
            String expectedCheckout,
            String status) {

        BedBooking booking =
                new BedBooking(
                        number,
                        bookingID,
                        patientName,
                        hospitalName,
                        department,
                        bedNo,
                        bedType,
                        checkinDate,
                        expectedCheckout,
                        status
                );

        dao.updateBedBooking(booking);
    }

    // =====================================================
    // DELETE BED BOOKING
    // =====================================================

    public void deleteBedBooking(String bookingID) {

        dao.deleteBedBooking(bookingID);
    }

    // =====================================================
    // GET ALL BED BOOKINGS
    // =====================================================

    public List<BedBooking> getAllBedBookings() {

        return dao.getBedBookings();
    }

    // =====================================================
    // REALTIME BED BOOKING LISTENER
    // EXISTING METHOD - UNCHANGED
    // =====================================================

    public ListenerRegistration listenToBedBookings(
            Consumer<List<BedBooking>> callback) {

        return dao.listenToBedBookings(callback);
    }

    // =====================================================
    // REALTIME MOTHER-SPECIFIC BED BOOKING LISTENER
    // NEW METHOD
    // =====================================================

    public ListenerRegistration listenToBedBookingsForMother(
            String motherUid,
            Consumer<List<BedBooking>> callback) {

        return dao.listenToBedBookingsForMother(
                motherUid,
                callback
        );
    }
}