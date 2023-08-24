package com.notification.telegramnotificationtest.bot.repository;

import com.notification.telegramnotificationtest.bot.entity.BotUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<BotUser, Long> {

    @Query("from BotUser u where u.id = :userId")
    BotUser findUsers(Long userId);

    @Query("select count(u) > 0 from BotUser u where u.id = :userId and u.notificationSubscribe = true")
    boolean isActualSubscribeUsers(Long userId);

}
