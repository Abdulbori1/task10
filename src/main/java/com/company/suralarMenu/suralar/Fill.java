package com.company.suralarMenu.suralar;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class Fill {

    public SendMessage sendTextMessage(Message message){
        SendMessage sendMessage1 = new SendMessage();
        sendMessage1.setText("Алям Тара Кайфа Фааля Раббука Биасхабиль-Фииль.\n" +
                "\nАлям Йаджаль Кайдахум Фи Тадлииль.\n" +
                "\nУа Арсаля Алейхим Тайраан Абабииль.\n" +
                "\nТармихим Бихиджаратин Мин Сиджииль.\n" +
                "\nФаджааляхум Каасфин Макууль.\n");
        sendMessage1.setParseMode(ParseMode.MARKDOWN);
        sendMessage1.setChatId(String.valueOf(message.getChatId()));
        return sendMessage1;
    }

    public SendAudio sendAudioMessage(Message message){
        SendAudio sendAudio = new SendAudio();
        sendAudio.setChatId(String.valueOf(message.getChatId()));
        sendAudio.setCaption("Mishary Rashid - Al-Fil surasi");
        return sendAudio;
    }
}
