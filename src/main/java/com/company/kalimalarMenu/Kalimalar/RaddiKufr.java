package com.company.kalimalarMenu.Kalimalar;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class RaddiKufr {

    public SendMessage sendTextMessage1(Message message) {
        SendMessage sendMessage1 = new SendMessage();
        sendMessage1.setText("Allohumma inni a’uzu bika min an ushrika bika shayan va ana a’lam. " +
                "Va astag‘firuka lima la a’lam. Innaka anta ‘allamul g‘uyub.");
        sendMessage1.setParseMode(ParseMode.MARKDOWN);
        sendMessage1.setChatId(String.valueOf(message.getChatId()));
        return sendMessage1;
    }

    public SendMessage sendTextMessage2(Message message) {
        SendMessage sendMessage2 = new SendMessage();
        sendMessage2.setText("Ma'nosi: Allohim, Sendan o‘zim bilganim holda Senga biror " +
                "narsani sherik qilishimdan asrashingni so‘rayman. Sendan o‘zim bilmaganim " +
                "holda shirk qilib qo‘ygan bo‘lsam, kechishingni tilayman. Albatta, Sen g‘ayblarni " +
                "bilguvchi Zotsan. ");
        sendMessage2.setParseMode(ParseMode.MARKDOWN);
        sendMessage2.setChatId(String.valueOf(message.getChatId()));
        return sendMessage2;
    }

    public SendAudio sendAudioMessage(Message message) {
        SendAudio sendAudio = new SendAudio();
        sendAudio.setChatId(String.valueOf(message.getChatId()));
        sendAudio.setCaption("Raddi Kufr");
        return sendAudio;
    }
}
