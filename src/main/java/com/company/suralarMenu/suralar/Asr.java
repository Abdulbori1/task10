package com.company.suralarMenu.suralar;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class Asr {

    public SendMessage sendTextMessage(Message message){
        SendMessage sendMessage1 = new SendMessage();
        sendMessage1.setText("Val 'asri. Innal insana lafiy xusr. Illal " +
                "laziyna a'manu va 'amilus solihati va tavasov bil haqqi va tavasov bis sobr.  ");
        sendMessage1.setParseMode(ParseMode.MARKDOWN);
        sendMessage1.setChatId(String.valueOf(message.getChatId()));
        return sendMessage1;
    }

    public SendAudio sendAudioMessage(Message message){
        SendAudio sendAudio = new SendAudio();
        sendAudio.setChatId(String.valueOf(message.getChatId()));
        sendAudio.setCaption("Mishary Rashid - Al-Asr surasi");
        return sendAudio;
    }
}
