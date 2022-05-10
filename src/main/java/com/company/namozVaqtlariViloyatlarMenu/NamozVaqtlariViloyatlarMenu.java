package com.company.namozVaqtlariViloyatlarMenu;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class NamozVaqtlariViloyatlarMenu {

    public SendMessage sendTextMessage(Message message){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Qaysi shahar yoki tumanni tanlaysiz: ");
        sendMessage.setParseMode(ParseMode.MARKDOWN);
        sendMessage.setChatId(String.valueOf(message.getChatId()));

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardRow1 = new KeyboardRow();
        KeyboardRow keyboardRow2 = new KeyboardRow();
        KeyboardRow keyboardRow3 = new KeyboardRow();
        KeyboardRow keyboardRow4 = new KeyboardRow();
        KeyboardRow keyboardRow5 = new KeyboardRow();
        keyboardRow1.add("Buhoro viloyati");
        keyboardRow1.add("Farg'ona viloyati");
        keyboardRow1.add("Sirdaryo viloyati");
        keyboardRow2.add("Jizzax viloyati");
        keyboardRow2.add("Navoiy viloyati");
        keyboardRow2.add("Namangan viloyati");
        keyboardRow3.add("Samarqand viloyati");
        keyboardRow3.add("Surxondaryo viloyati");
        keyboardRow3.add("Qashqadaryo viloyati");
        keyboardRow4.add("Andijon viloyati");
        keyboardRow4.add("Xorazm viloyati");
        keyboardRow4.add("Qoraqalpog'iston respublikasi");
        keyboardRow5.add("\uD83D\uDD19 Ortga");
        keyboardRowList.add(keyboardRow1);
        keyboardRowList.add(keyboardRow2);
        keyboardRowList.add(keyboardRow3);
        keyboardRowList.add(keyboardRow4);
        keyboardRowList.add(keyboardRow5);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }
}
