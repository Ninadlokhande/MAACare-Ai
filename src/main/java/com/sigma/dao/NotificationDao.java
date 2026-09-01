
package com.sigma.dao;

import java.util.ArrayList;
import java.util.List;

import com.sigma.config.FirebaseConfig;
import com.sigma.model.Notification;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

public class NotificationDao {

    private Firestore db =
            FirebaseConfig.getFirestore();

    // =====================================================
    // COLLECTION
    // =====================================================

    private static final String COLLECTION =
            "hospitalNotifications";


    // =====================================================
    // SAVE NOTIFICATION
    // =====================================================

    public void saveNotification(
            Notification notification) {

        try {

            db.collection(COLLECTION)
              .add(notification)
              .get();

            System.out.println(
                    "Notification Saved in Firebase"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // =====================================================
    // GET ALL NOTIFICATIONS
    // =====================================================

    public List<Notification> getAllNotifications() {

        List<Notification> list =
                new ArrayList<>();

        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection(COLLECTION)
                      .orderBy(
                              "timestamp",
                              Query.Direction.DESCENDING
                      )
                      .get();

            QuerySnapshot snapshot =
                    future.get();

            for (
                    DocumentSnapshot doc :
                    snapshot.getDocuments()
            ) {

                Notification notification =
                        doc.toObject(
                                Notification.class
                        );

                if (notification != null) {
                    notification.setId(doc.getId());

                    list.add(notification);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }


    // =====================================================
    // GET UNREAD NOTIFICATIONS
    // =====================================================

public List<Notification> getUnreadNotifications() {

    List<Notification> list =
            new ArrayList<>();

    try {

        ApiFuture<QuerySnapshot> future =
                db.collection(COLLECTION)
                  .orderBy(
                          "timestamp",
                          Query.Direction.DESCENDING
                  )
                  .get();

        QuerySnapshot snapshot =
                future.get();

        for (DocumentSnapshot doc :
                snapshot.getDocuments()) {

            Notification notification =
                    doc.toObject(
                            Notification.class
                    );

            if (notification != null) {

                notification.setId(
                        doc.getId()
                );

                // Only unread notifications
                if (!notification.isRead()) {
                    list.add(notification);
                }
            }
        }

    } catch (Exception e) {

        e.printStackTrace();
    }

    return list;
}



  /*  public List<Notification>
            getUnreadNotifications() {

        List<Notification> list =
                new ArrayList<>();

        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection(COLLECTION)
                      .whereEqualTo(
                              "read",
                              false
                      )
                      .orderBy(
                              "timestamp",
                              Query.Direction.DESCENDING
                      )
                      .get();

            QuerySnapshot snapshot =
                    future.get();

            for (
                    DocumentSnapshot doc :
                    snapshot.getDocuments()
            ) {

                Notification notification =
                        doc.toObject(
                                Notification.class
                        );

                if (notification != null) {
                    notification.setId(doc.getId());

                    list.add(notification);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }*/



    // =====================================================
    // MARK NOTIFICATION AS READ
    // =====================================================

    public void markAsRead(
            String notificationId) {

        try {

            db.collection(COLLECTION)
              .document(notificationId)
              .update(
                      "read",
                      true
              )
              .get();

            System.out.println(
                    "Notification Marked as Read"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // =====================================================
    // DELETE NOTIFICATION
    // =====================================================

    public void deleteNotification(
            String notificationId) {

        try {

            db.collection(COLLECTION)
              .document(notificationId)
              .delete()
              .get();

            System.out.println(
                    "Notification Deleted"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}