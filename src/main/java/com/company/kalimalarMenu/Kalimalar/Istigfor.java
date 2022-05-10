package com.company.kalimalarMenu.Kalimalar;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class Istigfor {

    public SendMessage sendTextMessage1(Message message){
        SendMessage sendMessage1 = new SendMessage();
        sendMessage1.setText("Astag‘firulloh, astag‘firulloh, astag‘firulloha ta’ala min kulli " +
                "zambin aznabtuhu ‘amdan av xotoan sirron va ‘alaniyah. Va atubu ilayhi minaz " +
                "zambillazi a’lamu va minaz-zambillazi la a’lam. Innaka anta ‘allamul g‘uyub.");
        sendMessage1.setParseMode(ParseMode.MARKDOWN);
        sendMessage1.setChatId(String.valueOf(message.getChatId()));
        return sendMessage1;
    }

    public  SendMessage sendTextMessage2(Message message){
        SendMessage sendMessage2 = new SendMessage();
        sendMessage2.setText("Ma'nosi: Allohdan gunohlarimni kechishini so‘rayman. Allohdan" +
                " gunohlarimni kechishini so‘rayman. Alloh taolodan ataylab yo adashib, " +
                "yashirin yo oshkora qilgan hamma gunohlarimni kechishini so‘rayman. " +
                "O‘zim bilgan va bilmagan gunohlardan Allohga qaytaman. Albatta, " +
                "Sen g‘ayblarni bilguchi Zotsan. ");
        sendMessage2.setParseMode(ParseMode.MARKDOWN);
        sendMessage2.setChatId(String.valueOf(message.getChatId()));
        return sendMessage2;
    }

    public SendAudio sendAudioMessage(Message message){
        SendAudio sendAudio = new SendAudio();
        sendAudio.setChatId(String.valueOf(message.getChatId()));
        sendAudio.setCaption("Istig'for");
        return sendAudio;
    }
}
