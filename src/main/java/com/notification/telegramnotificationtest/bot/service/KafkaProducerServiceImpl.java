package com.notification.telegramnotificationtest.bot.service;

import com.notification.telegramnotificationtest.bot.dto.ReceiveMessageDto;
import com.notification.telegramnotificationtest.bot.events.TelegramNotificationEvent;
import com.notification.telegramnotificationtest.bot.service.interfaces.KafkaProducerService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerServiceImpl implements KafkaProducerService {

    private static final Properties producerProperties = new Properties();

    @Override
    public void produceMessageToKafka(ReceiveMessageDto receiveMessageDto) {
        try (var producer = new KafkaProducer<String, ReceiveMessageDto>(producerProperties)) {
            producer.send(new ProducerRecord<>("telegram", receiveMessageDto));
        }
    }

    @PostConstruct
    private void initProperties() {
       String KAFKA_URL = System.getenv("KAFKA_URL");
//        String KAFKA_URL = "localhost:39092"; // for debug
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_URL);
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());
    }

}
