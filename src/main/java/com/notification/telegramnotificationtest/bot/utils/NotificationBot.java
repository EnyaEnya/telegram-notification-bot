package com.notification.telegramnotificationtest.bot.utils;

import com.notification.telegramnotificationtest.bot.exception.AlreadyHaveSubscribeException;
import com.notification.telegramnotificationtest.bot.service.ITelegramFacade;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
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

    ITelegramFacade telegramFacade;


    public NotificationBot(SetWebhook setWebhook, ITelegramFacade telegramFacade) {
        super(setWebhook);
        this.telegramFacade = telegramFacade;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        try {
            return handleUpdate(update);
        } catch (AlreadyHaveSubscribeException e) {
            return telegramFacade.sendAlreadyHaveSubscribeErrorMessage(update.getMessage().getChatId().toString());
        } catch (Exception e) {
            return telegramFacade.sendSubscribeErrorMessage(update.getMessage().getChatId().toString());
        }
    }

    private BotApiMethod<?> handleUpdate(Update update) {
        if (!update.hasCallbackQuery()) {
            Message message = update.getMessage();
            if (message != null) {
                return telegramFacade.answerMessage(update.getMessage());
            }
        }
        return null;
    }

}
