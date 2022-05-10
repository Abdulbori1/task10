package com.company.menuZikrlar.zikrlar;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class Duo {

    public SendMessage sendTextMessage1(Message message){
        SendMessage sendMessage1 = new SendMessage();
        sendMessage1.setText("Robbana atina fid-dunya hasanatav-va fil axiroti hasanatav-va qina azaban-nar.");
        sendMessage1.setParseMode(ParseMode.MARKDOWN);
        sendMessage1.setChatId(String.valueOf(message.getChatId()));
        return sendMessage1;
    }

    public SendMessage sendTextMessage2(Message message){
        SendMessage sendMessage2 = new SendMessage();
        sendMessage2.setText("Ey Robbimiz, bizga bu dunyoda ham, oxiratda ham yaxshilikni bergin va " +
                "bizni do‘zax olovi azobidan saqlagin");
        sendMessage2.setParseMode(ParseMode.MARKDOWN);
        sendMessage2.setChatId(String.valueOf(message.getChatId()));
        return sendMessage2;
    }
}
