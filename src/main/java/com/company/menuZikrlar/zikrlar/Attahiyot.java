package com.company.menuZikrlar.zikrlar;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class Attahiyot {

    public SendMessage sendTextAttahiyotMessage1(Message message){
        SendMessage sendMessage1 = new SendMessage();
        sendMessage1.setText("Attahiyyatu lillahi vas-solavatu vattoyyibat. Assalamu alayka " +
                "ayyuhan-nabiyyu va rohmatullohi va barokatuh. Assalamu alayna va ala ibadillahis-solihiyn." +
                " Ashhadu alla ilaha illallohu va ashhadu anna Muhammadan abduhu va rosuluh.");
        sendMessage1.setParseMode(ParseMode.MARKDOWN);
        sendMessage1.setChatId(String.valueOf(message.getChatId()));
        return sendMessage1;
    }

    public  SendMessage sendTextAttahiyatMessage(Message message){
        SendMessage sendMessage2 = new SendMessage();
        sendMessage2.setText("Mol, badan, til bilan ado etiladigan butun ibodatlar Alloh uchundir." +
                " Ey Nabiy! Allohning rahmati va barakoti Sizga bo‘lsin. Sizga va solih qullarga" +
                " Allohning salomi bo‘lsin. Iqrormanki, Allohdan o‘zga iloh yo‘q. Va yana iqrormanki, " +
                "Muhammad, alayhissalom, Allohning quli va elchisidirlar.");
        sendMessage2.setParseMode(ParseMode.MARKDOWN);
        sendMessage2.setChatId(String.valueOf(message.getChatId()));
        return sendMessage2;
    }

    public SendAudio sendAudioAttahiyat(Message message){
        SendAudio sendAudio = new SendAudio();
        sendAudio.setChatId(String.valueOf(message.getChatId()));
        sendAudio.setCaption("Attahiyat duosi");
        return sendAudio;
    }

}
