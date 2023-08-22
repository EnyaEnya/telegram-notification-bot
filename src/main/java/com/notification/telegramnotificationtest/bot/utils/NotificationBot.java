package com.notification.telegramnotificationtest.bot.utils;

import com.notification.telegramnotificationtest.bot.constants.BotMessageEnum;
import com.notification.telegramnotificationtest.bot.exception.AlreadyHaveSubscribeException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationBot extends SpringWebhookBot {
    String botPath;
    String botUsername;
    String botToken;

    MessageHandler messageHandler;


    public NotificationBot(SetWebhook setWebhook, MessageHandler messageHandler) {
        super(setWebhook);
        this.messageHandler = messageHandler;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        try {
            return handleUpdate(update);
        } catch (AlreadyHaveSubscribeException e) {
            return new SendMessage(update.getMessage().getChatId().toString(),
                    BotMessageEnum.ALREADY_HAVE_SUBSCRIBE_EXCEPTION.getMessage());
        } catch (Exception e) {
            return new SendMessage(update.getMessage().getChatId().toString(),
                    BotMessageEnum.SUBSCRIBE_EXCEPTION.getMessage());
        }
    }

    private BotApiMethod<?> handleUpdate(Update update) throws Exception {
        if (!update.hasCallbackQuery()) {
            Message message = update.getMessage();
            if (message != null) {
                return messageHandler.answerMessage(update.getMessage());
            }
        }
        return null;
    }

}
