package com.notification.telegramnotificationtest.bot.service;

import com.notification.telegramnotificationtest.bot.service.interfaces.ITelegramFacade;
import com.notification.telegramnotificationtest.bot.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Locale;

@Slf4j
@Service
@AllArgsConstructor
public class TelegramFacade implements ITelegramFacade {

    private final MessageSource messageSource;

    private final UserService userService;


    @Override
    public BotApiMethod<?> answerMessage(Message message) {
        String chatId = message.getChatId().toString();
        Long telegramUserId = message.getFrom().getId();

        String inputText = message.getText();

        if (inputText == null) {
            throw new IllegalArgumentException();
        } else if (inputText.equals("/start") || inputText.equalsIgnoreCase(msg("telegram.help.button"))) {
            return sendHelpMessage(chatId);
        } else if (inputText.equalsIgnoreCase(msg("telegram.start_notification.button"))) {
            return startSubscribe(chatId, message);
        } else if (inputText.equalsIgnoreCase(msg("telegram.end_notification_button"))) {
            return endSubscribe(chatId, telegramUserId);
        } else {
            return sendDefault(chatId);
        }
    }

    @Override
    public SendMessage startSubscribe(String chatId, Message message) {
        userService.startSubscribe(message.getFrom());
        return new SendMessage(chatId, msg("telegram.start_subscribe_message.text"));
    }

    @Override
    public SendMessage endSubscribe(String chatId, Long telegramUserId) {
        userService.manageSubscribe(telegramUserId, false);
        return new SendMessage(chatId, msg("telegram.end_subscribe_message.text"));
    }

    @Override
    public SendMessage sendHelpMessage(String chatId) {
        return new SendMessage(chatId, msg("telegram.start_bot_message.text"));
    }

    @Override
    public SendMessage sendDefault(String chatId) {
        return new SendMessage(chatId, msg("telegram.default_message.text"));
    }

    @Override
    public SendMessage sendSubscribeErrorMessage(String chatId) {
        return new SendMessage(chatId, msg("telegram.common_error.text"));
    }

    @Override
    public SendMessage sendAlreadyHaveSubscribeErrorMessage(String chatId) {
        return new SendMessage(chatId, msg("telegram.already_haveSubscribe_error.text"));
    }


    private String msg(String code, String... params) {
        try {
            return messageSource.getMessage(code, params, Locale.getDefault());
        } catch (Exception ex) {
            log.error("code = {}", code);
            throw ex;
        }
    }
}
