package com.example.bookmanagement.scheduler;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.bookmanagement.entity.Book;
import com.example.bookmanagement.entity.BookLending;
import com.example.bookmanagement.entity.BookstoreUser;
import com.example.bookmanagement.entity.Notification;
import com.example.bookmanagement.service.BookLendingService;
import com.example.bookmanagement.service.NotificationService;

import java.time.LocalDate;

import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {NotificationScheduler.class})
@ExtendWith(SpringExtension.class)
class NotificationSchedulerDiffBlueTest {
    @MockBean
    private BookLendingService bookLendingService;

    @Autowired
    private NotificationScheduler notificationScheduler;

    @MockBean
    private NotificationService notificationService;

    /**
     * Method under test: {@link NotificationScheduler#sendBookDueReminders()}
     */
    @Test
    void testSendBookDueReminders() {
        // Arrange
        when(bookLendingService.getOverdueLendings()).thenReturn(new ArrayList<>());

        // Act
        notificationScheduler.sendBookDueReminders();

        // Assert that nothing has changed
        verify(bookLendingService).getOverdueLendings();
    }

    /**
     * Method under test: {@link NotificationScheduler#sendBookDueReminders()}
     */
    @Test
    void testSendBookDueReminders2() {
        // Arrange
        Book book = new Book();
        book.setAuthor("JaneDoe");
        book.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        book.setId(1L);
        book.setTitle("Dr");

        BookstoreUser bookstoreUser = new BookstoreUser();
        bookstoreUser.setEmail("jane.doe@example.org");
        bookstoreUser.setId(1L);
        bookstoreUser.setUsername("janedoe");

        BookLending bookLending = new BookLending();
        bookLending.setBook(book);
        bookLending.setBookstoreUser(bookstoreUser);
        bookLending.setId(1L);
        bookLending.setLendingDate(LocalDate.of(1970, 1, 1));
        bookLending.setReturnDate(LocalDate.of(1970, 1, 1));
        bookLending.setReturned(true);

        ArrayList<BookLending> bookLendingList = new ArrayList<>();
        bookLendingList.add(bookLending);
        when(bookLendingService.getOverdueLendings()).thenReturn(bookLendingList);
        doNothing().when(notificationService).createNotification(Mockito.<Notification>any());
        doNothing().when(notificationService).sendNotification(Mockito.<Notification>any());

        // Act
        notificationScheduler.sendBookDueReminders();

        // Assert
        verify(bookLendingService).getOverdueLendings();
        verify(notificationService).createNotification(Mockito.<Notification>any());
        verify(notificationService).sendNotification(Mockito.<Notification>any());
    }

    /**
     * Method under test: {@link NotificationScheduler#sendBookDueReminders()}
     */
    @Test
    void testSendBookDueReminders3() {
        // Arrange
        Book book = new Book();
        book.setAuthor("JaneDoe");
        book.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        book.setId(1L);
        book.setTitle("Dr");

        BookstoreUser bookstoreUser = new BookstoreUser();
        bookstoreUser.setEmail("jane.doe@example.org");
        bookstoreUser.setId(1L);
        bookstoreUser.setUsername("janedoe");

        BookstoreUser bookstoreUser2 = new BookstoreUser();
        bookstoreUser2.setEmail("jane.doe@example.org");
        bookstoreUser2.setId(1L);
        bookstoreUser2.setUsername("janedoe");

        Book book2 = new Book();
        book2.setAuthor("JaneDoe");
        book2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        book2.setId(1L);
        book2.setTitle("Dr");
        BookLending bookLending = mock(BookLending.class);
        when(bookLending.getLendingDate())
                .thenThrow(new IllegalArgumentException(NotificationScheduler.REMINDER_MESSAGE));
        when(bookLending.getBook()).thenReturn(book2);
        when(bookLending.getBookstoreUser()).thenReturn(bookstoreUser2);
        doNothing().when(bookLending).setBook(Mockito.<Book>any());
        doNothing().when(bookLending).setBookstoreUser(Mockito.<BookstoreUser>any());
        doNothing().when(bookLending).setId(Mockito.<Long>any());
        doNothing().when(bookLending).setLendingDate(Mockito.<LocalDate>any());
        doNothing().when(bookLending).setReturnDate(Mockito.<LocalDate>any());
        doNothing().when(bookLending).setReturned(anyBoolean());
        bookLending.setBook(book);
        bookLending.setBookstoreUser(bookstoreUser);
        bookLending.setId(1L);
        bookLending.setLendingDate(LocalDate.of(1970, 1, 1));
        bookLending.setReturnDate(LocalDate.of(1970, 1, 1));
        bookLending.setReturned(true);

        ArrayList<BookLending> bookLendingList = new ArrayList<>();
        bookLendingList.add(bookLending);
        when(bookLendingService.getOverdueLendings()).thenReturn(bookLendingList);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> notificationScheduler.sendBookDueReminders());
        verify(bookLendingService).getOverdueLendings();
        verify(bookLending).getBook();
        verify(bookLending).getBookstoreUser();
        verify(bookLending).getLendingDate();
        verify(bookLending).setBook(Mockito.<Book>any());
        verify(bookLending).setBookstoreUser(Mockito.<BookstoreUser>any());
        verify(bookLending).setId(Mockito.<Long>any());
        verify(bookLending).setLendingDate(Mockito.<LocalDate>any());
        verify(bookLending).setReturnDate(Mockito.<LocalDate>any());
        verify(bookLending).setReturned(anyBoolean());
    }

    /**
     * Method under test: {@link NotificationScheduler#sendBookDueReminders()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSendBookDueReminders4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.time.LocalDate.isLeapYear()" because the return value of "com.example.bookmanagement.entity.BookLending.getLendingDate()" is null
        //       at com.example.bookmanagement.scheduler.NotificationScheduler.sendBookDueReminders(NotificationScheduler.java:31)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        Book book = new Book();
        book.setAuthor("JaneDoe");
        book.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        book.setId(1L);
        book.setTitle("Dr");

        BookstoreUser bookstoreUser = new BookstoreUser();
        bookstoreUser.setEmail("jane.doe@example.org");
        bookstoreUser.setId(1L);
        bookstoreUser.setUsername("janedoe");

        BookstoreUser bookstoreUser2 = new BookstoreUser();
        bookstoreUser2.setEmail("jane.doe@example.org");
        bookstoreUser2.setId(1L);
        bookstoreUser2.setUsername("janedoe");

        Book book2 = new Book();
        book2.setAuthor("JaneDoe");
        book2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        book2.setId(1L);
        book2.setTitle("Dr");
        BookLending bookLending = mock(BookLending.class);
        when(bookLending.getLendingDate()).thenReturn(null);
        when(bookLending.getBook()).thenReturn(book2);
        when(bookLending.getBookstoreUser()).thenReturn(bookstoreUser2);
        doNothing().when(bookLending).setBook(Mockito.<Book>any());
        doNothing().when(bookLending).setBookstoreUser(Mockito.<BookstoreUser>any());
        doNothing().when(bookLending).setId(Mockito.<Long>any());
        doNothing().when(bookLending).setLendingDate(Mockito.<LocalDate>any());
        doNothing().when(bookLending).setReturnDate(Mockito.<LocalDate>any());
        doNothing().when(bookLending).setReturned(anyBoolean());
        bookLending.setBook(book);
        bookLending.setBookstoreUser(bookstoreUser);
        bookLending.setId(1L);
        bookLending.setLendingDate(LocalDate.of(1970, 1, 1));
        bookLending.setReturnDate(LocalDate.of(1970, 1, 1));
        bookLending.setReturned(true);

        ArrayList<BookLending> bookLendingList = new ArrayList<>();
        bookLendingList.add(bookLending);
        when(bookLendingService.getOverdueLendings()).thenReturn(bookLendingList);
        doNothing().when(notificationService).createNotification(Mockito.<Notification>any());
        doNothing().when(notificationService).sendNotification(Mockito.<Notification>any());

        // Act
        notificationScheduler.sendBookDueReminders();
    }
}

