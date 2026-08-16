package org.example.notification;

public class PopupService implements NotificationService {
    @Override
    public void sendNotification() {
        System.out.println("POP-UP send notification");
    }
}
