package com.notification.telegramnotificationtest.bot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;


@Entity //todo уточнить
@Getter
@Setter
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY, generator = "notification_seq")
    private Long id;

    private String text;

    private String head;

    @Column(name="receive_time")
    private Instant receiveTime;

    private Long userId;

}
