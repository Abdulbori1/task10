package com.company.kalimalarMenu.Kalimalar;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class Tamhid {

    public SendMessage sendTextMessage1(Message message){
        SendMessage sendMessage1 = new SendMessage();
        sendMessage1.setText("Subhanalloh val hamdu lillah va la ilaha illallohu vallohu akbar. " +
                "La havla va la quvvata illa billahil ‘aliyyil ‘azim. Ma shaallohu " +
                "kana va ma lam yashalam yakun.");
        sendMessage1.setParseMode(ParseMode.MARKDOWN);
        sendMessage1.setChatId(String.valueOf(message.getChatId()));
        return sendMessage1;
    }

    public SendMessage sendTextMessage2(Message message){
        SendMessage sendMessage2 = new SendMessage();
        sendMessage2.setText("Ma'nosi: Allohning aybu nuqsoni yo‘qdir. Va maqtov Allohgadir. " +
                "Allohdan o‘zga sig‘iniladigan (iloh) yo‘qdir! Alloh ulug‘dir. Mutloq kuch " +
                "va quvvat qudratli va buyuk Allohdan o‘zgada yo‘qdir. Alloh nimaniki xohlasa, " +
                "bo‘ladi, nimaniki xohlamasa, bo‘lmaydi. ");
        sendMessage2.setParseMode(ParseMode.MARKDOWN);
        sendMessage2.setChatId(String.valueOf(message.getChatId()));
        return sendMessage2;
    }

    public SendAudio sendAudioMessage(Message message){
        SendAudio sendAudio = new SendAudio();
        sendAudio.setChatId(String.valueOf(message.getChatId()));
        sendAudio.setCaption("Tamjid");
        return sendAudio;
    }
}
