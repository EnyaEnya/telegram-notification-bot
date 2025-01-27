package com.notification.telegramnotificationtest.bot.config;

import com.notification.telegramnotificationtest.bot.service.interfaces.ITelegramFacade;
import com.notification.telegramnotificationtest.bot.utils.NotificationBot;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

@Configuration
@AllArgsConstructor
public class SpringConfig {
    private final TelegramConfig telegramConfig;

    @Bean
    public SetWebhook setWebhookInstance() {
        return SetWebhook.builder().url(telegramConfig.getWebhookPath()).build();
    }

    @Bean
    public NotificationBot springWebhookBot(SetWebhook setWebhook,
                                            ITelegramFacade telegramFacade) {
        NotificationBot bot = new NotificationBot(setWebhook, telegramFacade);

        bot.setBotPath(telegramConfig.getWebhookPath());
        bot.setBotUsername(telegramConfig.getBotName());
        bot.setBotToken(telegramConfig.getBotToken());

        return bot;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}