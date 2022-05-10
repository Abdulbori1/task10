package com.company.menu;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class Donat {

    public SendMessage donat(Message message){

        SendMessage sendMessage1 = new SendMessage();
        sendMessage1.setText("Donat " +
                "Agar sizga ushbu botning foydasi tegayotgan bo‘lsa va uning kelgusi faoliyatiga " +
                "o‘z hissangizni qo‘shishni istasangiz, moddiy qo‘llab-quvvatlashingiz mumkin. ( Eslatma: botdan" +
                " tushgan barcha mablag' xayriyaga sarflanadi )" + "\n" +
                "Donat uchun karta raqami: " + "\n" +
                "9860040118150149\n" +
                "\n" +
                "Karta Abdukhahhor Madmusayev nomiga ochilgan.");
        sendMessage1.setParseMode(ParseMode.MARKDOWN);
        sendMessage1.setChatId(String.valueOf(message.getChatId()));
        return sendMessage1;
    }
}
