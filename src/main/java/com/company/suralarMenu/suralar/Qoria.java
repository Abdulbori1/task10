package com.company.suralarMenu.suralar;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class Qoria {

    public SendMessage sendTextMessage(Message message){
        SendMessage sendMessage1 = new SendMessage();
        sendMessage1.setText("Аль-Кариа.\n" +
                "\nМаль-Кариа.\n" +
                "\nУа Ма Адрака Маль-Кариа.\n" +
                "\nЙаума Йакунун-Насу Кальфарашиль-Мабсуус.\n" +
                "\nУа Такунуль-Джибалю Кальихниль-Манфууш.\n" +
                "\nФаамма Ман Сакулят Мауазинуух.\n" +
                "\nФахува Фи Ишатин Радыйаа.\n" +
                "\nУа Амма Ман Хаффат Мауазинуух.\n" +
                "\nФауммуху Хавийаа.\n" +
                "\nУа Ма Адрака Ма Хийаах.\n" +
                "\nНарун Хамийаа.\n");
        sendMessage1.setParseMode(ParseMode.MARKDOWN);
        sendMessage1.setChatId(String.valueOf(message.getChatId()));
        return sendMessage1;
    }

    public SendAudio sendAudioMessage(Message message){
        SendAudio sendAudio = new SendAudio();
        sendAudio.setChatId(String.valueOf(message.getChatId()));
        sendAudio.setCaption("Mishary Rashid - Al-Qaria surasi");
        return sendAudio;
    }
}
