package com.company.suralarMenu.suralar;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class Zuho {

    public SendMessage sendTexMessage(Message message){
        SendMessage sendMessage1 = new SendMessage();
        sendMessage1.setText("Уад-Духаа.\n" +
                "Уаль-Ляйли Иза Саджаа.\n" +
                "Ма Уаддаака Раббука Уа Ма Каля.\n" +
                "Уа Ляльахырату Хайрун Ляка Миналь-Уля.\n" +
                "Уа Лясауфа Йутика Раббука Фатардаа.\n" +
                "Алям Йаджидка Йатимаан Фаауаа.\n" +
                "Уа Уаджадака Даллян Фахадаа.\n" +
                "Уа Уаджадака Аилян Фаагнаа.\n" +
                "Фааммаль-Йатима Фаля Такхаар.\n" +
                "Уа Аммас-Саиля Фаля Танхаар.\n" +
                "Уа Амма Бинимати Раббика Фахаддис");
        sendMessage1.setParseMode(ParseMode.MARKDOWN);
        sendMessage1.setChatId(String.valueOf(message.getChatId()));
        return sendMessage1;
    }

    public SendAudio sendAudioMessage(Message message){
        SendAudio sendAudio = new SendAudio();
        sendAudio.setChatId(String.valueOf(message.getChatId()));
        sendAudio.setCaption("Mishary Rashid - Ad-Dhuha surasi");
        return sendAudio;
    }
}
