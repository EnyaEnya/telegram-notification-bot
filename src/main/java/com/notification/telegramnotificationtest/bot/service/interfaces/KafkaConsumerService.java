package com.notification.telegramnotificationtest.bot.service.interfaces;

import com.notification.telegramnotificationtest.bot.dto.ReceiveMessageDto;

public interface KafkaConsumerService {

    public void sendMessageFromKafka(ReceiveMessageDto receiveMessageDto);

}
