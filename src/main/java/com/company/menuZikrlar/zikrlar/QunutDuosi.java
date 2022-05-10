package com.company.menuZikrlar.zikrlar;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class QunutDuosi {

    public SendMessage sendTextMessage1(Message message){
        SendMessage sendMessage1 = new SendMessage();
        sendMessage1.setText("Allohumma inna nasta'inuka va nastag‘firuk. " +
                "Vanu'minu bika va natavakalu ‘alayka va nusniy ‘alaykal xoyr. " +
                "Kullahu nashkuruka va la nakfuruk. Va naxla'u va natruku may-yafjuruk. \n" +
                "Allohumma iyyaka na'budu va laka nusolliy va nasjudu va ilayka nas'a va" +
                " nahfidu narju rohmatak(a). Va naxsha azabaka inna azabaka bil kuffari mulhiq.");
        sendMessage1.setParseMode(ParseMode.MARKDOWN);
        sendMessage1.setChatId(String.valueOf(message.getChatId()));
        return sendMessage1;
    }

    public SendMessage sendTextMessage2(Message message){
        SendMessage sendMessage2 = new SendMessage();
        sendMessage2.setText("Allohim! Biz Sendan yordam istaymiz, gunohlarimizni " +
                "afu etishingni so‘raymiz. Allohim! Senga imon keltiramiz, ishlarimizda Senga suyanamiz " +
                "va Senga tavakkal qilamiz. Va Senga hamd aytamiz. Butun yaxshiliklar Sendandir. " +
                "Bizlarga berganing shuncha ne'matlar tufayli Senga shukrona aytamiz va nonko‘rlik qilmaymiz. " +
                "Senga qarshi nonko‘rlik qilgan gunohkorlardan ayrilamiz, ular bilan aloqani uzamiz. \n" +
                "Allohim! Biz yolg‘iz Sengagina qullik qilamiz, namozni Sen uchungina o‘qiymiz." +
                " Sengagina sajda qilamiz. Sengagina intilamiz. Ibodatni xushu'-kamtarlik bilan ado etamiz." +
                " Rahmating ko‘p bo‘lishini so‘raymiz, azobing-dan qo‘rqamiz. Hech shubha yo‘qki," +
                " Sening azobing kofirlargadir. ");
        sendMessage2.setParseMode(ParseMode.MARKDOWN);
        sendMessage2.setChatId(String.valueOf(message.getChatId()));
        return sendMessage2;
    }
}
