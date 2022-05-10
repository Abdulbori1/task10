package com.company.suralarMenu.suralar;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class Falaq {


    public SendMessage sendTextMessage(Message message){
        SendMessage sendMessage1 = new SendMessage();
        sendMessage1.setText("Куль Аузу Бираббиль-Фаляк.\n" +
                "\nМин Шарри Ма Халяк.\n" +
                "\nУа Мин Шарри Гасикыйн Иза Уакаб.\n" +
                "\nУа Мин Шаррин-Наффасати Филь-Укад.\n" +
                "\nУа Мин Шарри Хасидин Иза Хасад.\n");
        sendMessage1.setParseMode(ParseMode.MARKDOWN);
        sendMessage1.setChatId(String.valueOf(message.getChatId()));
        return sendMessage1;
    }

    public SendAudio sendAudioMessage(Message message){
        SendAudio sendAudio = new SendAudio();
        sendAudio.setChatId(String.valueOf(message.getChatId()));
        sendAudio.setCaption("Mishary Rashid - Falaq surasi");
        return sendAudio;
    }
}
