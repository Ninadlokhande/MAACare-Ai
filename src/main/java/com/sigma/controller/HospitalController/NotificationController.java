package com.sigma.controller.HospitalController;

import java.util.Date;
import java.util.List;

import com.sigma.dao.NotificationDao;
import com.sigma.model.Notification;

public class NotificationController {

    NotificationDao dao = new NotificationDao();


    // =====================================================
    // ADD NOTIFICATION
    // =====================================================

    public void addNotification(
            String id,
            String message,
            String type) {

        Notification notification =
                new Notification(
                        id,
                        message,
                        type,
                        false,
                        new Date());

        dao.saveNotification(notification);
    }


    // =====================================================
    // GET ALL NOTIFICATIONS
    // =====================================================

    public List<Notification> getAllNotifications() {

        return dao.getAllNotifications();
    }


    // =====================================================
    // GET UNREAD NOTIFICATIONS
    // =====================================================

    public List<Notification> getUnreadNotifications() {

        return dao.getUnreadNotifications();
    }


    // =====================================================
    // MARK NOTIFICATION AS READ
    // =====================================================

    public void markAsRead(String notificationId) {

        dao.markAsRead(notificationId);
    }


    // =====================================================
    // DELETE NOTIFICATION
    // =====================================================

    public void deleteNotification(String notificationId) {

        dao.deleteNotification(notificationId);
    }
}