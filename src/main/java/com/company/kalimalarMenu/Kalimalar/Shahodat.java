package com.company.kalimalarMenu.Kalimalar;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class Shahodat {

    public SendMessage sendTextMessage1(Message message){
        SendMessage sendMessage1 = new SendMessage();
        sendMessage1.setText("Ashhadu alla ilaha illallohu va ashhadu anna Muhammadan ‘abduhu va rosuluh.");
        sendMessage1.setParseMode(ParseMode.MARKDOWN);
        sendMessage1.setChatId(String.valueOf(message.getChatId()));
        return sendMessage1;
    }

    public SendMessage sendTextMessage2(Message message){
        SendMessage sendMessage2 = new SendMessage();
        sendMessage2.setText("Ma'nosi: Allohdan o‘zga sig‘iniladigan (iloh)ning yo‘qligiga va Muhammad " +
                "Allohning bandasi va rasuli ekaniga iqrorman. ");
        sendMessage2.setParseMode(ParseMode.MARKDOWN);
        sendMessage2.setChatId(String.valueOf(message.getChatId()));
        return sendMessage2;
    }

    public SendAudio sendAudioMessage(Message message){
        SendAudio sendAudio = new SendAudio();
        sendAudio.setChatId(String.valueOf(message.getChatId()));
        sendAudio.setCaption("Shahodat");
        return sendAudio;
    }
}
