package com.notification.telegramnotificationtest.bot.dto;

import lombok.*;

import java.time.Instant;


@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiveMessageDto {

    private String text;

    private Long userId;

}

