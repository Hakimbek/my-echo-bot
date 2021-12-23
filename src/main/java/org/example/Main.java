package org.example;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class Main {
    public static void main(String[] args) {
        // Initialize Api Context
        ApiContextInitializer.init();

        // Instantiate Telegram Bots API
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        // Register our bot
        try {
            telegramBotsApi.registerBot(new MyAmazingBot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
