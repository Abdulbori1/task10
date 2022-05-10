package com.company.suralarMenu.suralar;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class Sharh {

    public SendMessage sendTextMessage(Message message) {
        SendMessage sendMessage1 = new SendMessage();
        sendMessage1.setText("УАлям Нашрах Ляка Садракэ\n" +
                "Уа Уаданаа Анка Уизракэ\n" +
                "Аль-Лязии Анкада Захракэ\n" +
                "Уа Рафанаа Ляка Зикракэ\n" +
                "Фа инна Мааль-Усри Йусраа..\n" +
                "Инна Мааль-Усри Йусраа.\n" +
                "Фаизаа Фарагта Фансабэ.\n" +
                "Уа Иляя Раббика Фаргабэ");
        sendMessage1.setParseMode(ParseMode.MARKDOWN);
        sendMessage1.setChatId(String.valueOf(message.getChatId()));
        return sendMessage1;
    }

    public SendAudio sendAudioMessage(Message message){
        SendAudio sendAudio = new SendAudio();
        sendAudio.setChatId(String.valueOf(message.getChatId()));
        sendAudio.setCaption("Mishary Rashid - Al-Insharh surasi");
        return sendAudio;
    }
}
