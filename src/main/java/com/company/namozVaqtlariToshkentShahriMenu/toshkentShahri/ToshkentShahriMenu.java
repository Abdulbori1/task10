package com.company.namozVaqtlariToshkentShahriMenu.toshkentShahri;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class ToshkentShahriMenu {

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
        keyboardRow1.add("Uchtepa tumani");
        keyboardRow1.add("Bektemir tumani");
        keyboardRow1.add("Mirzo Ulug'bek tumani");
        keyboardRow2.add("Mirobod tumani");
        keyboardRow2.add("Sergili tumani");
        keyboardRow2.add("Olmazor tumani");
        keyboardRow3.add("Chilonzor tumani");
        keyboardRow3.add("Shayxontohur tumani");
        keyboardRow3.add("Yunusobod tumani");
        keyboardRow4.add("Yakkasaroy tumani");
        keyboardRow4.add("Yashnobod tumani");
        keyboardRow5.add("\uD83D\uDD19 Orqasiga");
        keyboardRow5.add("\uD83D\uDD1D Asosiy Menyu");
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
