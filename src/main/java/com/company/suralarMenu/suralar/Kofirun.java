package com.company.suralarMenu.suralar;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class Kofirun {

    public SendMessage sendTextMessage(Message message){
        SendMessage sendMessage1 = new SendMessage();
        sendMessage1.setText("Куль Йа Аййухаль-Кафируун.\n" +
                "Ля Абуду Ма Табудуун.\n" +
                "Уа Ля Антум Абидуна Ма Абуд.\n" +
                "Уа Ля Ана Абидун Ма Абадттум.\n" +
                "Уа Ля Антум Абидуна Ма Абуд.\n" +
                "Лякум Динукум Уа Лийа Диин.");
        sendMessage1.setParseMode(ParseMode.MARKDOWN);
        sendMessage1.setChatId(String.valueOf(message.getChatId()));
        return sendMessage1;
    }

    public SendAudio sendAudioMessage(Message message){
        SendAudio sendAudio = new SendAudio();
        sendAudio.setChatId(String.valueOf(message.getChatId()));
        sendAudio.setCaption("Mishary Rashid - Kofirun surasi");
        return sendAudio;
    }
}
