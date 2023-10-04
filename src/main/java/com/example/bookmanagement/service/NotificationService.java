package com.example.bookmanagement.service;

import com.example.bookmanagement.entity.Notification;
import com.example.bookmanagement.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final JavaMailSender javaMailSender;


    public void createNotification(Notification notification) {
        notificationRepository.save(notification);
    }

    public void sendNotification(Notification notification) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(notification.getBookstoreUser().getEmail());
        mailMessage.setSubject("Book Due Reminder");
        mailMessage.setText(notification.getMessage());
        javaMailSender.send(mailMessage);
    }
}
