package com.company.kalimalarMenu.Kalimalar;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class Tavhid {

    public SendMessage sendTextMessage1(Message message){
        SendMessage sendMessage1 = new SendMessage();
        sendMessage1.setText("Ashhadu alla ilaha illallohu vahdahu la sharika lah, lahul mulku va lahul " +
                "hamd(u) yuhyi va yumit(u) va huva hayyul " +
                "la yamut(u), biyadihil xoyr(u) va huva ‘ala kulli shayin qodir.");
        sendMessage1.setParseMode(ParseMode.MARKDOWN);
        sendMessage1.setChatId(String.valueOf(message.getChatId()));
        return sendMessage1;
    }

    public SendMessage sendTextMessage2(Message message){
        SendMessage sendMessage2 = new SendMessage();
        sendMessage2.setText("Ma'nosi: Tanho Allohdan o‘zga sig‘iniladigan (iloh) " +
                "yo‘qligiga iqrorman! Allohning sherigi yo‘qdir. Mulk Allohnikidir. " +
                "Maqtov Allohgadir. (Alloh) tiriltiradi va o‘ldiradi. Ammo O‘zi tirikdir, o‘lmaydi." +
                " Yaxshilik Uning ixtiyoridadir va U hamma narsaga qodirdir! ");
        sendMessage2.setParseMode(ParseMode.MARKDOWN);
        sendMessage2.setChatId(String.valueOf(message.getChatId()));
        return sendMessage2;
    }

    public SendAudio sendAudioMessage(Message message){
        SendAudio sendAudio = new SendAudio();
        sendAudio.setChatId(String.valueOf(message.getChatId()));
        sendAudio.setCaption("Tavhid");
        return sendAudio;
    }
}
