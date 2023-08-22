package com.notification.telegramnotificationtest.bot.service;

import com.notification.telegramnotificationtest.bot.dto.MessageDto;

public interface NotificationService {

    String saveNotification(MessageDto messageDto);

}
