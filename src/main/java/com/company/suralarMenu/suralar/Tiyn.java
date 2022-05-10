package com.company.suralarMenu.suralar;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class Tiyn {

    public SendMessage sendTextMessage(Message message){
        SendMessage sendMessage1 = new SendMessage();
        sendMessage1.setText("Bismillahir rohmanir rohim. Vat tiyni vaz zaytunu. Va " +
                "turisiyniyna. Va hazal baladil amiyn. Laqod xolaqnal insana fiy ahsani " +
                "taqviym. Summa rodadnahu asfala safiliyn. Ilallaziyna amanu va amilus solihati " +
                "fa lahum ajrun ĝoyru mamnun. Fa ma yukazzibuka ba'du biddiyn. A laysallohu biahkamil xakimiyn.");
        sendMessage1.setParseMode(ParseMode.MARKDOWN);
        sendMessage1.setChatId(String.valueOf(message.getChatId()));
        return sendMessage1;
    }

    public SendAudio sendAudioMessage(Message message){
        SendAudio sendAudio = new SendAudio();
        sendAudio.setChatId(String.valueOf(message.getChatId()));
        sendAudio.setCaption("Mishary Rashid - At-Tin surasi");
        return sendAudio;
    }
}
