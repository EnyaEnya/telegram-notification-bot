package com.notification.telegramnotificationtest.bot.service.impl;

import com.notification.telegramnotificationtest.bot.entity.BotUser;
import com.notification.telegramnotificationtest.bot.exception.AlreadyHaveSubscribeException;
import com.notification.telegramnotificationtest.bot.repository.UserRepository;
import com.notification.telegramnotificationtest.bot.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public void findUser(User telegramUser) {

        BotUser user = userRepository.findUsers(telegramUser.getId());

        if (user != null) {
            if (user.isNotificationSubscribe()) {
                throw new AlreadyHaveSubscribeException();
            } else {
                manageSubscribe(telegramUser.getId(), true);
            }
        } else {
            addNewUser(telegramUser);
        }
    }


    @Override
    public void addNewUser(User telegramUser) throws AlreadyHaveSubscribeException {
        BotUser newUser = new BotUser();
        newUser.setId(telegramUser.getId());
        newUser.setFirstName(telegramUser.getFirstName());
        newUser.setLastName(telegramUser.getLastName());
        newUser.setUserName(telegramUser.getUserName());
        newUser.setNotificationSubscribe(true);
        userRepository.save(newUser);
    }

    @Override
    public void manageSubscribe(Long userId, boolean subscribe) {
        BotUser user = userRepository.getReferenceById(userId);
        user.setNotificationSubscribe(subscribe);
        userRepository.save(user);
    }

}
