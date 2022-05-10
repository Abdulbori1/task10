package com.company.suralarMenu.suralar;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class Zalzala {

    public SendMessage sendTextMessage(Message message){
        SendMessage sendMessage1 = new SendMessage();
        sendMessage1.setText("Bismillohir rohmanir rohim. Iza zulzilatil arzu " +
                "zilzalaha. Va axrojatil arzu asqolaha. Va qolal insanu ma laha. " +
                "Yavma izin tuhaddisu axbaroha. Bi anna robbaka avhalaha. Yavma izin " +
                "yasdurun nasu ashtatal li yurov a'malahum. Fa may ya'mal misqola zarotin " +
                "xoyroy yoroh. Va may yamal misqola zarrotin sharroy yaroh.");
        sendMessage1.setParseMode(ParseMode.MARKDOWN);
        sendMessage1.setChatId(String.valueOf(message.getChatId()));
        return sendMessage1;
    }

    public SendAudio sendAudioMessage(Message message){
        SendAudio sendAudio = new SendAudio();
        sendAudio.setChatId(String.valueOf(message.getChatId()));
        sendAudio.setCaption("Mishary Rashid - Az-Zalzala surasi");
        return sendAudio;
    }
}
