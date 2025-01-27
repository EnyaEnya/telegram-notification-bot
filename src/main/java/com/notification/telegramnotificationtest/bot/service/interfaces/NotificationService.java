package com.notification.telegramnotificationtest.bot.service.interfaces;

import com.notification.telegramnotificationtest.bot.dto.ReceiveMessageDto;
import com.notification.telegramnotificationtest.bot.events.TelegramNotificationEvent;

public interface NotificationService {

    String sendNotificationPrimary(ReceiveMessageDto receiveMessageDto);
    void sendNotificationFromKafka(ReceiveMessageDto receiveMessage);
}
