package com.company.suralarMenu.suralar;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class Moun {

    public SendMessage sendTextMessage(Message message){
        SendMessage sendMessage1 = new SendMessage();
        sendMessage1.setText("Араайталь-Лязи Йуказзибу Бид-Диин" +
                "Фазаликаль-Лязи Йадууль-Йатиим.\n" +
                "\nУа Ля Йахудду Аля Таамиль-Мискиин.\n" +
                "\nФауайлю Лильмусаллиин.\n" +
                "\nАль-Лязина Хум Ан Салятихим Сахуун.\n" +
                "\nАль-Лязина Хум Йурауун.\n" +
                "\nУа Йамнауналь-Мауун.\n");
        sendMessage1.setParseMode(ParseMode.MARKDOWN);
        sendMessage1.setChatId(String.valueOf(message.getChatId()));
        return sendMessage1;
    }

    public SendAudio sendAudioMessage(Message message){
        SendAudio sendAudio = new SendAudio();
        sendAudio.setChatId(String.valueOf(message.getChatId()));
        sendAudio.setCaption("Mishary Rashid - Al-Maun surasi");
        return sendAudio;
    }
}
