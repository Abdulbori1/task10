package com.company.suralarMenu.suralar;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class Adiyat {

    public SendMessage sendTextMessage(Message message){
        SendMessage sendMessage1 = new SendMessage();
        sendMessage1.setText("Уаль-Адийати Дабхаа.\n" +
                "\nФальмурийати Кадхаа.\n" +
                "\nФальмугирати Субхаа.\n" +
                "\nФаасарна Бихи Накаа.\n" +
                "\nФауасатна Бихи Джамаа.\n" +
                "\nИнналь-Инсана Лираббихи Ляканууд.\n" +
                "\nУа Иннаху Аля Залика Ляшахиид.\n" +
                "\nУа Иннаху Лихуббиль-Хайри Ляшадиид.\n" +
                "\nАфаля Йаляму Иза Бусира Ма Филь-Кубуур.\n" +
                "\nУа Хуссиля Ма Фис-Судуур.\n" +
                "\nИнна Раббахум Бихим Йаумаизин Ляхабиир.\n");
        sendMessage1.setParseMode(ParseMode.MARKDOWN);
        sendMessage1.setChatId(String.valueOf(message.getChatId()));
        return sendMessage1;
    }

    public SendAudio sendAudioMessage(Message message){
        SendAudio sendAudio = new SendAudio();
        sendAudio.setChatId(String.valueOf(message.getChatId()));
        sendAudio.setCaption("Mishary Rashid - Al-Adiyat surasi");
        return sendAudio;
    }
}
