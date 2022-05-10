package com.company.suralarMenu.suralar;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class Layl {

    public SendMessage sendTextMessage(Message message){
        SendMessage sendMessage1 = new SendMessage();
        sendMessage1.setText("Уаль-Ляйли Иза Йагшаа.\n" +
                "Уан-Нахари Иза Таджалля.\n" +
                "Уа Ма Халяказ-Закара Уаль-Унсаа.\n" +
                "Инна Сайакум Ляшаттаа.\n" +
                "Фаамма Ман Ата Уаттакаа.\n" +
                "Уа Саддака Биль-Хуснаа.\n" +
                "Фасануйассируху Лильйусраа.\n" +
                "Уа Амма Ман Бахиля Уастагнаа.\n" +
                "Уа Каззаба Биль-Хуснаа.\n" +
                "Фасануйассируху Лильусраа.\n" +
                "Уа Ма Йугни Анху Малюху Иза Тараддаа.\n" +
                "Инна Алейна Лальхудаа.\n" +
                "Уа Инна Ляна Лильахырата Уаль-Уля.\n" +
                "Фаанзартукум Нараан Таляззаа.\n" +
                "Ля Йасляха Илляль-Ашкаа.\n" +
                "Аль-Лязи Каззаба Уа Тауалля.\n" +
                "Уа Сайуджаннабухаль-Аткаа.\n" +
                "Аль-Лязи Йути Маляху Йатазаккаа.\n" +
                "Уа Ма Лихадин Индаху Мин Ниматин Туджзаа.\n" +
                "Иллябтигаа Уаджхи Раббихиль-Аля.\n" +
                "Уа Лясауфа Йардаа.");
        sendMessage1.setParseMode(ParseMode.MARKDOWN);
        sendMessage1.setChatId(String.valueOf(message.getChatId()));
        return sendMessage1;
    }

    public SendAudio sendAudioMessage(Message message){
        SendAudio sendAudio = new SendAudio();
        sendAudio.setChatId(String.valueOf(message.getChatId()));
        sendAudio.setCaption("Mishary Rashid - Al-Layl surasi");
        return sendAudio;
    }
}
