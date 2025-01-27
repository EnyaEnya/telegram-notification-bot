package com.notification.telegramnotificationtest.bot.service;


import com.notification.telegramnotificationtest.bot.dto.ReceiveMessageDto;
import com.notification.telegramnotificationtest.bot.events.NotificationsEventPublisher;
import com.notification.telegramnotificationtest.bot.service.interfaces.KafkaConsumerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@AllArgsConstructor
public class KafkaConsumerServiceImpl implements KafkaConsumerService {
    private static final String topicCreateOrder = "${topic.telegram}";
    private static final String kafkaConsumerGroupId = "${spring.kafka.consumer.group-id}";

    private NotificationsEventPublisher eventPublisher;

    @Transactional
    @KafkaListener(topics = topicCreateOrder, groupId = kafkaConsumerGroupId, properties = {"spring.json.value.default.type=com.notification.telegramnotificationtest.bot.dto.ReceiveMessageDto"})
    @Override
    @Async
    public void sendMessageFromKafka(ReceiveMessageDto receiveMessageDto) {
        eventPublisher.publishTelegramNotificationEvent(receiveMessageDto);
    }

}
