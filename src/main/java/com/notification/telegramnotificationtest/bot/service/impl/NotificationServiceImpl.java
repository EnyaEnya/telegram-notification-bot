package com.notification.telegramnotificationtest.bot.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.notification.telegramnotificationtest.bot.dto.ReceiveMessageDto;
import com.notification.telegramnotificationtest.bot.dto.SendMessageDto;
import com.notification.telegramnotificationtest.bot.entity.Notification;
import com.notification.telegramnotificationtest.bot.exception.ParseTelegramAnswerException;
import com.notification.telegramnotificationtest.bot.repository.NotificationRepository;
import com.notification.telegramnotificationtest.bot.repository.UserRepository;
import com.notification.telegramnotificationtest.bot.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    @Value("${telegram.bot-url}")
    private String notificationUrl;

    private final RestTemplate restTemplate;

    private final NotificationRepository notificationRepository;

    private final UserRepository userRepository;


    @Override
    public String sendNotificationToUser(ReceiveMessageDto receiveMessageDto) {

        Long userId = receiveMessageDto.getUserId();

        Notification notification = new Notification();
        notification.setHead(receiveMessageDto.getHead());
        notification.setText(receiveMessageDto.getText());
        notification.setReceiveTime(Instant.now());
        notification.setUserId(userId);
        notificationRepository.save(notification);

        return isUserSubscribe(userId) ? isSentNotification(mapReceiveMessageToSendMessage(receiveMessageDto)) : null;
    }

    private String isSentNotification(SendMessageDto sendMessageDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<SendMessageDto> entity = new HttpEntity<>(sendMessageDto, headers);
        ResponseEntity<String> res = restTemplate.exchange(notificationUrl, HttpMethod.POST, entity, String.class);
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

//    todo delivery sign
//    private boolean isSentNotification(SendMessageDto telegramSendMessageDto) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<SendMessageDto> entity = new HttpEntity<>(telegramSendMessageDto, headers);
//        ResponseEntity<String> res = restTemplate.exchange(notificationUrl, HttpMethod.POST, entity, String.class);
//
//        return res.getStatusCode().equals(HttpStatus.OK) && isProcessingResult(res);
//    }

//    private boolean isProcessingResult(ResponseEntity<String> res) {
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode bodyNode;
//        try {
//            bodyNode = mapper.readTree(res.getBody());
//        } catch (JsonProcessingException e) {
//            throw new ParseTelegramAnswerException();
//        }
//        return bodyNode.get("ok") != null && bodyNode.get("ok").equals(BooleanNode.TRUE);
//    }
}
