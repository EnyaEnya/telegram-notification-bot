package com.notification.telegramnotificationtest.bot.service.impl;

import com.notification.telegramnotificationtest.bot.dto.MessageDto;
import com.notification.telegramnotificationtest.bot.entity.Notification;
import com.notification.telegramnotificationtest.bot.repository.NotificationRepository;
import com.notification.telegramnotificationtest.bot.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {


    private final NotificationRepository notificationRepository;


    @Override
    public String saveNotification(MessageDto messageDto) {
        Notification notification = new Notification();
        notification.setHead(messageDto.getHead());
        notification.setText(messageDto.getText());
        notification.setReceiveTime(Instant.now()); //todo logic with user time
        notification.setUserId(messageDto.getUserId());
        notificationRepository.save(notification);
        return null;
    }

}
