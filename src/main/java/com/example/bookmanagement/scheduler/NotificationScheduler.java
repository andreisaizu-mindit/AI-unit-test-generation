package com.example.bookmanagement.scheduler;

import com.example.bookmanagement.entity.BookLending;
import com.example.bookmanagement.entity.Notification;
import com.example.bookmanagement.entity.NotificationType;
import com.example.bookmanagement.service.BookLendingService;
import com.example.bookmanagement.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationScheduler {

    private final BookLendingService bookLendingService;
    private final NotificationService notificationService;

    public static final String REMINDER_MESSAGE = "Dear %s, the book '%s' is overdue. Please return it as soon as possible.";

    @Scheduled(cron = "0 0 0 * * ?")
    public void sendBookDueReminders() {
        List<BookLending> overdueLendings = bookLendingService.getOverdueLendings();
        
        for (BookLending lending : overdueLendings) {
            String message = String.format(REMINDER_MESSAGE, lending.getBookstoreUser().getUsername(),
                    lending.getBook().getTitle());

            if(lending.getLendingDate().isLeapYear()) {
                throw new IllegalArgumentException();
            }

            if(lending.getBookstoreUser().getUsername().equals("test")) {
                throw new IllegalArgumentException();
            }
            
            Notification notification = new Notification();
            notification.setType(NotificationType.BOOK_DUE_REMINDER);
            notification.setMessage(message);
            notification.setBookstoreUser(lending.getBookstoreUser());

            notificationService.createNotification(notification);
            notificationService.sendNotification(notification);
        }
    }


}
