package com.notification.telegramnotificationtest.bot.events;

import com.notification.telegramnotificationtest.bot.service.interfaces.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class NotificationsEventListener {

    private final NotificationService notificationService;

    @Async
    @EventListener
    public void handleTelegramNotificationEvent(TelegramNotificationEvent event) {
        notificationService.sendNotificationFromKafka(event.getReceiveMessage());
    }
}
