package com.notification.telegramnotificationtest.bot.service.impl;

import com.notification.telegramnotificationtest.bot.dto.ReceiveMessageDto;
import com.notification.telegramnotificationtest.bot.dto.SendMessageDto;
import com.notification.telegramnotificationtest.bot.entity.BotUser;
import com.notification.telegramnotificationtest.bot.entity.Notification;
import com.notification.telegramnotificationtest.bot.repository.NotificationRepository;
import com.notification.telegramnotificationtest.bot.repository.UserRepository;
import com.notification.telegramnotificationtest.bot.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    @Value("${telegram.bot-url}")
    private String NOTIFICATION_URL;

    private final RestTemplate restTemplate;

    private final NotificationRepository notificationRepository;

    private final UserRepository userRepository;


    @Override
    public String sendNotificationToUser(ReceiveMessageDto receiveMessageDto) {

        Long userId = receiveMessageDto.getUserId();

        Notification notification = new Notification();
        notification.setHead(receiveMessageDto.getHead());
        notification.setText(receiveMessageDto.getText());
        notification.setReceiveTime(Instant.now()); //todo logic with user time
        notification.setUserId(userId);
        notificationRepository.save(notification);

        return isUserSubscribe(userId) ? sendNotificationToUser(mapReceiveMessageToSendMessage(receiveMessageDto)) : null;
        //todo return UserNoSubscribeException
    }

    private String sendNotificationToUser(SendMessageDto sendMessageDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<SendMessageDto> entity = new HttpEntity<>(sendMessageDto, headers);
        ResponseEntity<String> res = restTemplate.exchange(NOTIFICATION_URL, HttpMethod.POST, entity, String.class);
        return res.getBody();
    }

    private SendMessageDto mapReceiveMessageToSendMessage(ReceiveMessageDto receiveMessageDto) {
        SendMessageDto sendMessageDto = new SendMessageDto();
        sendMessageDto.setChatId(String.valueOf(receiveMessageDto.getUserId()));
        sendMessageDto.setText(receiveMessageDto.getText());
        //todo other params
        return sendMessageDto;
    }

    private boolean isUserSubscribe(Long userId) {
        return userRepository.isActualSubscribeUsers(userId);
    }

}
