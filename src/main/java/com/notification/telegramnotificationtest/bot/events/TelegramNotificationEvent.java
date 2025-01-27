package com.notification.telegramnotificationtest.bot.events;

import com.notification.telegramnotificationtest.bot.dto.ReceiveMessageDto;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class TelegramNotificationEvent extends ApplicationEvent {

    private final ReceiveMessageDto receiveMessage;

    public TelegramNotificationEvent(Object source, ReceiveMessageDto receiveMessage) {
        super(source);
        this.receiveMessage = receiveMessage;
    }

}
