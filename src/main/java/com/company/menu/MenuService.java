package com.company.menu;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class MenuService {

    public SendMessage start(Message message){
        SendMessage sendMessage = new SendMessage();
        if (message.getText().equals("/start")) {
            sendMessage.setText("Assalomu alaykum xurmatli dindoshim botimizga hush kelibsiz!");
        } else {
            sendMessage.setText("Qaysi menuni tanlaysiz: ");
        }
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
        KeyboardRow keyboardRow6 = new KeyboardRow();
        keyboardRow1.add("\uD83D\uDD4B  Suralar");
        keyboardRow1.add("☝\uD83C\uDFFB  Olti Kalima");
        keyboardRow1.add("\uD83D\uDCFF  Zikrlar");
        keyboardRow5.add("⏳  Namoz vaqtlari Toshkent viloyati");
        keyboardRow5.add("⏳  Namoz vaqtlari viloyatlarda");
        keyboardRow3.add("\uD83D\uDC73\uD83C\uDFFB\u200D♂️  Namoz o'qish tartibi (Erkaklar uchun)");
        keyboardRow4.add("\uD83E\uDDD5\uD83C\uDFFB  Namoz o'qish tartibi (Ayollar uchun)");
        keyboardRow2.add("\uD83D\uDCF1  Biz bilan bog'lanish");
        keyboardRow2.add("\uD83D\uDD4C  Donat");
        keyboardRow6.add("\uD83D\uDCC9  Bot statistikasi");
        keyboardRowList.add(keyboardRow1);
        keyboardRowList.add(keyboardRow5);
        keyboardRowList.add(keyboardRow3);
        keyboardRowList.add(keyboardRow4);
        keyboardRowList.add(keyboardRow2);
        keyboardRowList.add(keyboardRow6);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }
}
