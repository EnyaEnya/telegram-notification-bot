package com.notification.telegramnotificationtest.bot.service.interfaces;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface ITelegramFacade {

    BotApiMethod<?> answerMessage(Message message);
    SendMessage startSubscribe(String chatId, Message message);

    SendMessage endSubscribe(String chatId, Long telegramUserId);

    SendMessage sendHelpMessage(String chatId);

    SendMessage sendDefault(String chatId);

    SendMessage sendSubscribeErrorMessage(String chatId);

    SendMessage sendAlreadyHaveSubscribeErrorMessage(String chatId);


}
