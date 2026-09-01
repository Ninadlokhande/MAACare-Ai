package com.sigma.controller.HospitalController;

import java.util.List;

import com.sigma.dao.BedBookingDao;
import com.sigma.model.BedBooking;
import com.sigma.controller.HospitalController.NotificationController;

public class BedBookingController {

    BedBookingDao dao = new BedBookingDao();

    NotificationController notificationController =
        new NotificationController();

    // =====================================================
    // ADD BED BOOKING
    // =====================================================

    public void addBedBooking(
            String number,
            String bookingID,
            String patientName,
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
                        department,
                        bedNo,
                        bedType,
                        checkinDate,
                        expectedCheckout,
                        status
                );

        dao.saveBedBooking(booking);
    

    notificationController.addNotification(
        number,
        "New bed booking added",
        "BED_BOOKING"
); 
}

    // =====================================================
    // GET ONE BED BOOKING
    // =====================================================

    public BedBooking getBedBooking(
            String bookingID) {

        return dao.getBedBooking(bookingID);
    }

    // =====================================================
    // UPDATE BED BOOKING
    // =====================================================

    public void updateBedBooking(
            String number,
            String bookingID,
            String patientName,
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

    public void deleteBedBooking(
            String bookingID) {

        dao.deleteBedBooking(bookingID);
    }

    // =====================================================
    // GET ALL BED BOOKINGS
    // =====================================================

    public List<BedBooking> getAllBedBookings() {

        return dao.getBedBookings();
    }
}