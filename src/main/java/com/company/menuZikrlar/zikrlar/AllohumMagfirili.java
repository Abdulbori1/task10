package com.company.menuZikrlar.zikrlar;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class AllohumMagfirili {

    public SendMessage allohumMagfirili(Message message){
        SendMessage sendMessage1 = new SendMessage();
        sendMessage1.setText("Алохимма махфирлий вал валидаи вал муминини вал муминат вал муслимини вал " +
                "муслимат ал ахее мунхим амат иннака мужвид дават ва рофиул даражат вал мунзилил баракат ва " +
                "козил хожат.");
        sendMessage1.setParseMode(ParseMode.MARKDOWN);
        sendMessage1.setChatId(String.valueOf(message.getChatId()));
        return sendMessage1;
    }
}
