package com.notification.telegramnotificationtest.bot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Getter
@Setter
public class SendMessageDto {

    private String text;

    @JsonProperty("chat_id")
    private String chatId;

    @JsonProperty("parse_mode")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String parseMode;

    @JsonProperty("disable_web_page_preview")
    private boolean disableWebPagePreview;

    @JsonProperty("disable_notification")
    private boolean disableNotification;

    @JsonProperty("reply_to_message_id")
    private int replyToMessage;
}
