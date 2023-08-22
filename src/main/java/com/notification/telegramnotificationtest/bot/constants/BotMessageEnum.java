package com.notification.telegramnotificationtest.bot.constants;

/**
 * Текстовые сообщения, посылаемые ботом
 */
public enum BotMessageEnum {
    //ответы на команды с клавиатуры
    HELP_MESSAGE("\uD83D\uDC4B Это бот уведомлений \n\n" +
            "*Вы можете выполнить одну из команд:* \n\n" +
            "✅ Подписаться на уведомления\n\n" +
            "✅ Отписаться от рассылки\n\n" +
            "✅ Помощь\n\n" +
            "Введите текст команды: "),

    START_SUBSCRIBE_MESSAGE("Вы подписались на уведомления"),
    NON_COMMAND_MESSAGE("Пожалуйста, воспользуйтесь клавиатурой\uD83D\uDC47"),
    END_SUBSCRIBE_MESSAGE("Вы отписались от рассылки"),
    DEFAULT_MESSAGE("Введите в текстовое поле одну из команд"),

    ALREADY_HAVE_SUBSCRIBE_EXCEPTION("Вы уже подписаны на уведомления"),
    SUBSCRIBE_EXCEPTION("Что-то пошло не так. Обратитесь в службу поддержки.");

    private final String message;

    BotMessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}