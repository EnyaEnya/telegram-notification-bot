package com.notification.telegramnotificationtest.bot.service;

import com.notification.telegramnotificationtest.bot.dto.ReceiveMessageDto;

public interface NotificationService {

    String sendNotificationToUser(ReceiveMessageDto receiveMessageDto);
}
