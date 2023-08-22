package com.notification.telegramnotificationtest.bot.constants;

/**
 * Названия кнопок основной клавиатуры
 */
public enum ButtonNameEnum {
    START_NOTIFICATION_BUTTON("Подписаться на уведомления"),
    END_NOTIFICATION_BUTTON("Отписаться от рассылки"),
    HELP_BUTTON("Помощь");

    private final String buttonName;

    ButtonNameEnum(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getButtonName() {
        return buttonName;
    }
}