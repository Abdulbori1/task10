package com.company.suralarMenu.suralar;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class Qadr {

    public SendMessage sendTextMessage(Message message){
        SendMessage sendMessage1 = new SendMessage();
        sendMessage1.setText("Bismillahir rohmanir rohim. Inna anzalnahu fiy laylatil qudir. " +
                "Va ma adroka ma laylatul qodir. Laylatulqodr xoyrum min alfi shahr. Tanaz zalul " +
                "mala ikatu var ruhu bi izni robbihim min kulli amr. Salamun hiya hatta matla'il fajr.");
        sendMessage1.setParseMode(ParseMode.MARKDOWN);
        sendMessage1.setChatId(String.valueOf(message.getChatId()));
        return sendMessage1;
    }

    public SendAudio sendAudioMassage(Message message){
        SendAudio sendAudio = new SendAudio();
        sendAudio.setChatId(String.valueOf(message.getChatId()));
        sendAudio.setCaption("Mishary Rashid - Al-Qadr surasi");
        return sendAudio;
    }
}
