package com.company.suralarMenu.suralar;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class Fotiha {

    public SendMessage sendTextMessage1(Message message){
        SendMessage sendMessage1 = new SendMessage();
        sendMessage1.setText("Аузу билляхи минашшайтаани р-раджим.\n" +
                "Бисмилляхи р-рахмаани р-рахим.\n" +
                "Альхамдy лилляхи раббиль алямин.\n" +
                "Аррахмаани р-рахим. Маалики яумиддин.\n" +
                "Иййякя набyдy ва ийякя настаийн.\n" +
                "Ихдина с-сырааталь мyстакыйм.\n" +
                "Сырааталлязина анамта алейхим.\n" +
                "Гайриль магдуби алейхим валяд-дооллиин…\"\n" +
                "Аамин!\n");
        sendMessage1.setParseMode(ParseMode.MARKDOWN);
        sendMessage1.setChatId(String.valueOf(message.getChatId()));
        return sendMessage1;
    }

    public SendMessage sendTextMessage2(Message message){
        SendMessage sendMessage2 = new SendMessage();
        sendMessage2.setText("Ma'nosi: Allohning dargohidan quvilgan shayton yomonligidan " +
                "Allohning panohiga qochaman. Mehribon va rahmli Alloh nomi bilan (boshlayman)." +
                " Hamd olamlar rabbi Allohgakim, (U) mehribon, rahmli va hisob-kitob kuni " +
                "(Qiyomat)ning egasidir. Sengagina ibodat qilamiz va Sendangina yordam so‘raymiz! " +
                "Bizni shunday to‘g‘ri yo‘lga boshlaginki, (U) Sen in’om (hidoyat) etganlarning " +
                "(payg‘ambarlar,siddiq va shahidlarning) yo‘lidir, g‘azabga uchragan " +
                "(Muso qavmidan itoatsizlarining) va adashgan (Iso qavmining «Allohning farzandi " +
                "bor» deydigan)larningemas! ");
        sendMessage2.setParseMode(ParseMode.MARKDOWN);
        sendMessage2.setChatId(String.valueOf(message.getChatId()));
        return sendMessage2;
    }

    public SendAudio sendAudioMessage(Message message){
        SendAudio sendAudio = new SendAudio();
        sendAudio.setChatId(String.valueOf(message.getChatId()));
        sendAudio.setCaption("Mishary Rashid - Fotiha surasi");
//                    InputFile inputFile = new InputFile("audio/sura-al--fatiha-al-ham.mp3");
        return sendAudio;
    }
}
