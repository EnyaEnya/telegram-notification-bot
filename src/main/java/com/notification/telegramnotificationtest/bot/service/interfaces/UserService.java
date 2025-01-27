package com.notification.telegramnotificationtest.bot.service.interfaces;

import org.telegram.telegrambots.meta.api.objects.User;

public interface UserService {

    void startSubscribe(User telegramUser);
    void addNewUser(User user) throws Exception;

    void manageSubscribe(Long userId, boolean subscribe);
}
