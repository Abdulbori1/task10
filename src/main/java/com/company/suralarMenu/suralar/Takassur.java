package com.company.suralarMenu.suralar;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class Takassur {

    public SendMessage sendTextMessage(Message message){
        SendMessage sendMessage1 = new SendMessage();
        sendMessage1.setText("Bismillahir rohmanir rohim. Al hakumut\n" +
                " takasur. Hatta\n" +
                " zurtumul maqobir. Kalla safa ta'lamun. Summa kalla savfa\n" +
                " ta'lamun. Kalla lav\n" +
                " ta'lamuna ilmal\n" +
                " yaqiyn. La\n" +
                " tarovunnal jahiym. Summa la tarovunnuh a aynal\n" +
                " yaqiyn. Summa latus\n" +
                " alanna\n" +
                " yavma izin\n" +
                " anin\n" +
                " naiym.");
        sendMessage1.setParseMode(ParseMode.MARKDOWN);
        sendMessage1.setChatId(String.valueOf(message.getChatId()));
        return sendMessage1;
    }

    public SendAudio sendAudioMessage(Message message){
        SendAudio sendAudio = new SendAudio();
        sendAudio.setChatId(String.valueOf(message.getChatId()));
        sendAudio.setCaption("Mishary Rashid - At-Takathur surasi");
        return sendAudio;
    }
}
