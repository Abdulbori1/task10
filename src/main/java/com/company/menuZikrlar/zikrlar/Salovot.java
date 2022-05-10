package com.company.menuZikrlar.zikrlar;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class Salovot {

    public SendMessage sendTextMessage1(Message message){
        SendMessage sendMessage1 = new SendMessage();
        sendMessage1.setText("Allohumma solli ala Muhammadiv-va ala ali Muhammad. Kama " +
                "sollayta ala Ibrohima va ala ali Ibrohim. Innaka hamidum-majid.\n" +
                "Allohumma barik ala Muhammadiv-va ala ali Muhammad. Kama barokta ala Ibrohima " +
                "va ala ali Ibrohim. Innaka hamidum-majid.");
        sendMessage1.setParseMode(ParseMode.MARKDOWN);
        sendMessage1.setChatId(String.valueOf(message.getChatId()));
        return sendMessage1;
    }

    public SendMessage sendTextMessage2(Message message){
        SendMessage sendMessage2 = new SendMessage();
        sendMessage2.setText("Allohim, Ibrohim va uning oilasiga rahmat etganing kabi, " +
                "Muhammad va ul zotning oilasiga rahmat ayla, Sen hamdu maqtovga loyiq va buyuk Zotsan.\n" +
                "Allohim, Ibrohim va uning oilasiga barakotingni ehson etganing kabi Muhammad va " +
                "ul zotning oilasi ustiga ham barakotingni ehson ayla. Sen hamdu maqtovga loyiq va buyuk Zotsan.");
        sendMessage2.setParseMode(ParseMode.MARKDOWN);
        sendMessage2.setChatId(String.valueOf(message.getChatId()));
        return sendMessage2;
    }
}
