package com.company.suralarMenu.suralar;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class Shams {

    public SendMessage sendTextMessage(Message message){
        SendMessage sendMessage1 = new SendMessage();
        sendMessage1.setText("Уащщамси уадухьахь. Уаль къомари иза таляхьа. Уаннахьари иза " +
                "джалляхьа. Уалляйли иза яг’щахьа. Уассама и уа ма банахьа. Уаль ард’и уа ма " +
                "тъохьахьа. Уа нафси-в-ва ма саввахьа. Фа альхьамахьа фуджу-в-рохьа уа такъувахьа. " +
                "Къод афляхьа ман’ заккахьа. Уа къод хоба манн дассахьа. Каззабат саму-в-ду би тъогъвахьа. " +
                "Изи-мм ба’асъа ащкъохьа. Факъоаля ляхь ум роасу-люллоахьи накъоталлахьи уасукъояхьа. " +
                "Факаззабу-вв-хьу фаакъорувхьа фадамдама алайхьим роббухьумм- биззамм-бихьим фасаввахаа. " +
                "Уа ля ях’офу укъбахьа.");
        sendMessage1.setParseMode(ParseMode.MARKDOWN);
        sendMessage1.setChatId(String.valueOf(message.getChatId()));
        return sendMessage1;
    }

    public SendAudio sendAudioMessage(Message message){
        SendAudio sendAudio = new SendAudio();
        sendAudio.setChatId(String.valueOf(message.getChatId()));
        sendAudio.setCaption("Mishary Rashid - Ash-Shams surasi");
        return sendAudio;
    }
}
