package com.notification.telegramnotificationtest.bot.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;


@Getter
@Setter
public class ReceiveMessageDto {

    private String head;

    private String text;

    private Instant receiveTime;

    private Long userId;

}
