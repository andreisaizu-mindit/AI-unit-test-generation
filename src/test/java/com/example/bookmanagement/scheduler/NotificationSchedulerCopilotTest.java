package com.example.bookmanagement.scheduler;

import com.example.bookmanagement.entity.*;
import com.example.bookmanagement.service.BookLendingService;
import com.example.bookmanagement.service.NotificationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {NotificationScheduler.class})
@ExtendWith(SpringExtension.class)
class NotificationSchedulerCopilotTest {

    @MockBean
    private BookLendingService bookLendingService;

    @MockBean
    private NotificationService notificationService;

    @Autowired
    private NotificationScheduler notificationScheduler;

    // TODO: Write tests for NotificationScheduler.sendBookDueReminders

    // test sendBookReminders with no overdue lendings
    @Test
    void sendBookRemindersWithNoOverdueLendings() {
        // Arrange
        when(bookLendingService.getOverdueLendings()).thenReturn(new ArrayList<>());

        // Act
        notificationScheduler.sendBookDueReminders();

        // Assert that nothing has changed
        verify(bookLendingService).getOverdueLendings();
        verifyNoInteractions(notificationService);
    }
    // test sendBookReminders with one overdue lending
    @Test
    void sendBookRemindersWithOneOverdueLending() {
        //mock the book
        Book book = new Book();
        book.setTitle("test");

        //mock the user
        BookstoreUser user = new BookstoreUser();
        user.setUsername("test1");


        // mock the overdue lending
        BookLending overdueLending = new BookLending();
        overdueLending.setLendingDate(LocalDate.now().minusDays(2));
        overdueLending.setReturnDate(LocalDate.now().minusDays(1));
        overdueLending.setBook(book);
        overdueLending.setBookstoreUser(user);

        // mock the notification
        Notification notification = new Notification();
        notification.setType(NotificationType.BOOK_DUE_REMINDER);
        notification.setMessage(String.format(NotificationScheduler.REMINDER_MESSAGE, overdueLending.getBookstoreUser().getUsername(),
                overdueLending.getBook().getTitle()));
        notification.setBookstoreUser(user);

        // Arrange
        when(bookLendingService.getOverdueLendings()).thenReturn(List.of(overdueLending));

        // Act
        notificationScheduler.sendBookDueReminders();

        verify(bookLendingService).getOverdueLendings();
        verify(notificationService).createNotification(notification);
        verify(notificationService).sendNotification(notification);
    }
    // test sendBookReminders with multiple overdue lendings
    @Test
    void sendBookRemindersWithMultipleOverdueLendings() {
        //mock the book
        Book book = new Book();
        book.setTitle("test");

        //mock the user
        BookstoreUser user = new BookstoreUser();
        user.setUsername("test1");

        //mock another user
        BookstoreUser anotherUser = new BookstoreUser();
        anotherUser.setUsername("test2");

        // mock the overdue lending
        BookLending overdueLending = new BookLending();
        overdueLending.setLendingDate(LocalDate.now().minusDays(2));
        overdueLending.setReturnDate(LocalDate.now().minusDays(1));
        overdueLending.setBook(book);
        overdueLending.setBookstoreUser(user);

        // mock another overdue lending
        BookLending overdueLending2 = new BookLending();
        overdueLending2.setLendingDate(LocalDate.now().minusDays(2));
        overdueLending2.setReturnDate(LocalDate.now().minusDays(1));
        overdueLending2.setBook(book);
        overdueLending2.setBookstoreUser(anotherUser);

        // mock the notification
        Notification notification = new Notification();
        notification.setType(NotificationType.BOOK_DUE_REMINDER);
        notification.setMessage(String.format(NotificationScheduler.REMINDER_MESSAGE, overdueLending.getBookstoreUser().getUsername(),
                overdueLending.getBook().getTitle()));
        notification.setBookstoreUser(user);

        // mock another notification
        Notification notification2 = new Notification();
        notification2.setType(NotificationType.BOOK_DUE_REMINDER);
        notification2.setMessage(String.format(NotificationScheduler.REMINDER_MESSAGE, overdueLending2.getBookstoreUser().getUsername(),
                overdueLending2.getBook().getTitle()));
        notification2.setBookstoreUser(anotherUser);

        // Arrange
        when(bookLendingService.getOverdueLendings()).thenReturn(List.of(overdueLending, overdueLending2));

        // Act
        notificationScheduler.sendBookDueReminders();

        verify(bookLendingService).getOverdueLendings();

        verify(notificationService).createNotification(notification);
        verify(notificationService).sendNotification(notification);
        verify(notificationService).createNotification(notification2);
        verify(notificationService).sendNotification(notification2);

    }
    // test sendBookReminders with a lending date that is not a leap year
    // test sendBookReminders with a lending date that is a leap year
    @Test
    void testSendBookRemindersWithLeapYear() {
        //mock the book
        Book book = new Book();
        book.setTitle("test");

        //mock the user
        BookstoreUser user = new BookstoreUser();
        user.setUsername("test1");

        // mock the overdue lending
        BookLending overdueLending = new BookLending();
        overdueLending.setLendingDate(LocalDate.of(2020, 1, 1));
        overdueLending.setReturnDate(LocalDate.of(2020, 1, 2));
        overdueLending.setBook(book);
        overdueLending.setBookstoreUser(user);

        // Arrange
        when(bookLendingService.getOverdueLendings()).thenReturn(List.of(overdueLending));

        // Act
        assertThrows(IllegalArgumentException.class, () -> notificationScheduler.sendBookDueReminders());

        verify(bookLendingService).getOverdueLendings();

    }
    // test sendBookReminders with a lending date that is a leap year and a user named "test"
    // test sendBookReminders with a lending date that is a leap year and a user not named "test"
    // test sendBookReminders with a lending date that is not a leap year and a user named "test"
    // test sendBookReminders with a lending date that is not a leap year and a user not named "test"


}
