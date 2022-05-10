package com.company.suralarMenu.suralar;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class Alaq {

    public SendMessage sendTextMessage(Message message){
        SendMessage sendMessage1 = new SendMessage();
        sendMessage1.setText("Бисмилляхир-Рахманир-Рахиим\n" +
                "\nИкра Биасми Раббикаль-Лязи Халяк.\n" +
                "\nХалякаль-Инсана Мин Аляк.\n" +
                "\nИкра Уа Раббукаль-Акрам.\n" +
                "\nАль-Лязи Алляма Биль-Калям.\n" +
                "\nАллямаль-Инсана Ма Лям Йалям.\n" +
                "\nКалля Инналь-Инсана Ляйатга.\n" +
                "\nАн Раахустагна.\n" +
                "\nИнна Иля Раббикар-Руджа.\n" +
                "\nАраайталь-Лязи Йанха.\n" +
                "\nАбдаан Иза Салля.\n" +
                "\nАраайта Ин Кана Аляль-Худа.\n" +
                "\nАу Амара Бит-Такуа.\n" +
                "\nАраайта Ин Каззаба Уа Тауалля.\n" +
                "\nАлям Йалям БианнаЛлаха Йара.\n" +
                "\nКалля Ляин Лям Йантахи Лянасфаан Бин-Насийа.\n" +
                "\nНасийатин Казибатин Хатиа.\n" +
                "\nФальйаду Надийа.\n" +
                "\nСанадуз-Забанийа.\n" +
                "\nКалля Ля Тутиху Уасджуд Уактариб.\n");
        sendMessage1.setParseMode(ParseMode.MARKDOWN);
        sendMessage1.setChatId(String.valueOf(message.getChatId()));
        return sendMessage1;
    }

    public SendAudio sendAudioMessage(Message message){
        SendAudio sendAudio = new SendAudio();
        sendAudio.setChatId(String.valueOf(message.getChatId()));
        sendAudio.setCaption("Mishary Rashid - Al-Alaq surasi");
        return sendAudio;
    }
}
