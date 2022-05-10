package com.company.suralarMenu.suralar;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class Humaza {

    public SendMessage sendTextMessage(Message message){
        SendMessage sendMessage1 = new SendMessage();
        sendMessage1.setText("Vaylul li\n" +
                "kulli humazatil lumazah. Al\n" +
                "laziy jama'a malav va\n" +
                "'аddadah. Yahsabu anna\n" +
                "malahu axladah. Kalla la\n" +
                "yunbazanna fil xutomati. Va\n" +
                "ma adroka mal xutomah.\n" +
                "Narullohil muqodah. Allatiy\n" +
                "tottali'u 'lal af idah.\n" +
                "Innaha 'alayhim musodah.\n" +
                "Fiy 'amadim mumaddadah.");
        sendMessage1.setParseMode(ParseMode.MARKDOWN);
        sendMessage1.setChatId(String.valueOf(message.getChatId()));
        return sendMessage1;
    }

    public SendAudio sendAudioMessage(Message message){
        SendAudio sendAudio = new SendAudio();
        sendAudio.setChatId(String.valueOf(message.getChatId()));
        sendAudio.setCaption("Mishary Rashid - Al-Humaza surasi");
        return sendAudio;
    }
}
