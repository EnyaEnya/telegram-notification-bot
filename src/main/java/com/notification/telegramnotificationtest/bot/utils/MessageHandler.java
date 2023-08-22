package com.notification.telegramnotificationtest.bot.utils;

import com.notification.telegramnotificationtest.bot.config.TelegramApiClient;
import com.notification.telegramnotificationtest.bot.constants.BotMessageEnum;
import com.notification.telegramnotificationtest.bot.constants.ButtonNameEnum;
import com.notification.telegramnotificationtest.bot.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class MessageHandler {

    TelegramApiClient telegramApiClient;

    private final UserService userService;

    public BotApiMethod<?> answerMessage(Message message) throws Exception {
        String chatId = message.getChatId().toString();
        Long telegramUserId = message.getFrom().getId();

        String inputText = message.getText();

        if (inputText == null) {
            throw new IllegalArgumentException();
        } else if (inputText.equals("/start")) {
            return prepareSendMessage(chatId, BotMessageEnum.HELP_MESSAGE.getMessage());
        } else if (inputText.equals(ButtonNameEnum.START_NOTIFICATION_BUTTON.getButtonName())) {
            userService.findUser(message.getFrom());
            return prepareSendMessage(chatId, BotMessageEnum.START_SUBSCRIBE_MESSAGE.getMessage());
        } else if (inputText.equals(ButtonNameEnum.END_NOTIFICATION_BUTTON.getButtonName())) {
            userService.manageSubscribe(telegramUserId, false);
            return prepareSendMessage(chatId, BotMessageEnum.END_SUBSCRIBE_MESSAGE.getMessage());
        } else if (inputText.equals(ButtonNameEnum.HELP_BUTTON.getButtonName())) {
            return prepareSendMessage(chatId, BotMessageEnum.HELP_MESSAGE.getMessage());
        } else {
            return prepareSendMessage(chatId, BotMessageEnum.DEFAULT_MESSAGE.getMessage());
        }
    }


    private SendMessage prepareSendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        sendMessage.enableMarkdown(true);
        return sendMessage;
    }

}