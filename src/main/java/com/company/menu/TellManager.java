package com.company.menu;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

public class TellManager {

    public SendMessage tellMe(Message message){
        SendMessage sendMessage1 = new SendMessage();
        sendMessage1.setText("My name and surname : " + "\n" +
                "Phone number: +998904091478");
        sendMessage1.setParseMode(ParseMode.MARKDOWN);
        sendMessage1.setChatId(String.valueOf(message.getChatId()));
        return sendMessage1;
    }
}
