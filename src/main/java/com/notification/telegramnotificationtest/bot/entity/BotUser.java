package com.notification.telegramnotificationtest.bot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity //todo уточнить
@Getter
@Setter
@Table(name = "bot_user")
public class BotUser {

    @Id
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "notification_subscribe")
    private boolean notificationSubscribe;

}
