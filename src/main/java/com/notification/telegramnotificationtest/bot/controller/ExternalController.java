package com.notification.telegramnotificationtest.bot.controller;

import com.notification.telegramnotificationtest.bot.dto.ReceiveMessageDto;
import com.notification.telegramnotificationtest.bot.service.interfaces.KafkaProducerService;
import com.notification.telegramnotificationtest.bot.service.interfaces.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@Slf4j
@RequestMapping(value = "/telegram-notification")
public class ExternalController {

    private final NotificationService notificationService;

    private final KafkaProducerService producerService;

    @Autowired
    public ExternalController(NotificationService notificationService, KafkaProducerService producerService) {
        this.notificationService = notificationService;
        this.producerService = producerService;
    }

    @PostMapping("/send-notification")
    public ResponseEntity<String> sendNotification(@RequestBody ReceiveMessageDto receiveMessageDto) {
        return new ResponseEntity<>(notificationService.sendNotificationPrimary(receiveMessageDto), HttpStatus.OK);
    }

    @PostMapping("/send-notification-kafka")
    public void sendNotificationKafka(@RequestBody ReceiveMessageDto receiveMessageDto) {
        producerService.produceMessageToKafka(receiveMessageDto);
    }
}
