package com.company.suralarMenu.suralar;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class Bayyina {

    public SendMessage sendTextMessage(Message message){
        SendMessage sendMessage1 = new SendMessage();
        sendMessage1.setText("Лям Йакуниль-Лязина Кафару Мин Ахлиль-Китаби Уаль-Мушрикина Мунфаккина Хатта " +
                "Татийахумуль-Баййина.\n" +
                "\nРасулюн МинаЛлахи Йатлю Сухуфаан Мутаххара.\n" +
                "\nФиха Кутубун Каййима.\n" +
                "\nУа Ма Тафарракаль-Лязина Утуль-Китаба Илля Мин Бади Ма Джаатхумуль-Баййина.\n" +
                "\nУа Ма Умиру Илля ЛийабудуЛлаха Мухлисина Ляхуд-Дина Хунафаа Уа Йукымус-Салята " +
                "Уа Йутуз-Закята Уа Залика Динуль-Каййима.\n" +
                "\nИнналь-Лязина Кафару Мин Ахлиль-Китаби Уаль-Мушрикина Фи Нари Джаханнама Халидина" +
                " Фиха Уляика Хум Шарруль-Барийа.\n" +
                "\nИнналь-Лязина Аману Уа Амилюс-Салихати Уляика Хум Хайруль-Барийа.\n" +
                "\nДжазаухум Инда Раббихим Джаннату Аднин Таджри Мин Тахтихаль-Анхару Халидина" +
                " Фиха Абадаан РадийаЛлаху Анхум Уа Раду Анху Залика Лиман Хашийа Рабба.\n" +
                "\n");
        sendMessage1.setParseMode(ParseMode.MARKDOWN);
        sendMessage1.setChatId(String.valueOf(message.getChatId()));
        return sendMessage1;
    }

    public SendAudio sendAudioMassage(Message message){
        SendAudio sendAudio = new SendAudio();
        sendAudio.setChatId(String.valueOf(message.getChatId()));
        sendAudio.setCaption("Mishary Rashid - Al-Bayyina surasi");
        return sendAudio;
    }
}
