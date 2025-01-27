package com.notification.telegramnotificationtest.bot.service.interfaces;

import com.notification.telegramnotificationtest.bot.dto.ReceiveMessageDto;

public interface KafkaProducerService {

    void produceMessageToKafka(ReceiveMessageDto receiveMessageDto);

}
