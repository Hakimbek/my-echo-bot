package org.example;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.PhotoSize;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;

import static org.telegram.telegrambots.logging.BotLogger.log;

public class MyAmazingBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {

        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            String text = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            // Create a message object
            SendMessage message = new SendMessage();
            message.setChatId(String.valueOf(chatId));
            message.setText(text);

            try {
                // Sending our message object to user
                execute(message);
            } catch (TelegramApiException e) {
                log(Level.SEVERE, "Exception: ", e.toString());
            }
        } else if (update.hasMessage() && update.getMessage().hasPhoto()) {
            // Message contains photo
            // Set variables
            long chatId = update.getMessage().getChatId();

            // Array with photo objects with different sizes
            // We will get the biggest photo from that array
            List<PhotoSize> photos = update.getMessage().getPhoto();

            // Know photoId
            String photoId = Objects.requireNonNull(photos.stream().max(Comparator.comparing(PhotoSize::getFileSize))
                    .orElse(null)).getFileId();

            // Know photo width
            int width = Objects.requireNonNull(photos.stream().max(Comparator.comparing(PhotoSize::getFileSize))
                    .orElse(null)).getWidth();

            // Know photo height
            int height = Objects.requireNonNull(photos.stream().max(Comparator.comparing(PhotoSize::getFileSize))
                    .orElse(null)).getHeight();

            // Set photo caption
            String caption = "file_id = " + photoId + "\nwidth = " + Integer.toString(width) + "\nheight = " + Integer.toString(height);
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(String.valueOf(chatId));
            sendPhoto.setPhoto(photoId);
            sendPhoto.setCaption(caption);

            try {
                // Call method to send the photo with caption
                sendPhoto(sendPhoto);
            } catch (TelegramApiException e) {
                log(Level.SEVERE, "Exception: ", e.toString());
            }
        }

    }

    @Override
    public String getBotToken() {
        // Return bot token from BotFather
        return "5077343614:AAH4Ro74Pz3pKkYcW0VetuW3lZg7tO0e1lk";
    }

    @Override
    public String getBotUsername() {
        // Return bot username
        return "khakimbek_bot";
    }

}
