package com.notification.telegramnotificationtest.bot.repository;

import com.notification.telegramnotificationtest.bot.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
