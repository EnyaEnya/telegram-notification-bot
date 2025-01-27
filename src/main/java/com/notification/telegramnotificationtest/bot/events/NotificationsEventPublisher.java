package com.notification.telegramnotificationtest.bot.events;

import com.notification.telegramnotificationtest.bot.dto.ReceiveMessageDto;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class NotificationsEventPublisher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(@NonNull ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishTelegramNotificationEvent(final ReceiveMessageDto receiveMessage) {
        TelegramNotificationEvent telegramNotificationEvent = new TelegramNotificationEvent(this, receiveMessage);
        applicationEventPublisher.publishEvent(telegramNotificationEvent);
    }
}
