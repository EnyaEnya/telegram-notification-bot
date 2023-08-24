package com.notification.telegramnotificationtest.bot.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.notification.telegramnotificationtest.bot.dto.ReceiveMessageDto;
import com.notification.telegramnotificationtest.bot.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
@Slf4j
@RequestMapping(value = "/telegram-notification", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExternalController {

    private final NotificationService notificationService;

    @Autowired
    public ExternalController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/send-notification")
    public ResponseEntity<String> sendNotification(@RequestBody ReceiveMessageDto receiveMessageDto) {
        return new ResponseEntity<>(notificationService.sendNotificationToUser(receiveMessageDto), HttpStatus.OK);
    }
}
