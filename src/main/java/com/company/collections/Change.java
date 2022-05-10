package com.company.collections;

import com.google.gson.Gson;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;

public class Change {

    public String printBamdod(String cityName, Message message){
        String json = change(cityName);
        if (json == null) {
            return null;
        }
        Gson gson = new Gson();
        Time dto = gson.fromJson(json, Time.class);
        SendMessage sendMessage = new SendMessage();
        String imsak = dto.getResults().getTimeDatetime()[0].getTimes().getImsak();
        return imsak;
    }

    public String printQuyoshChiqishi(String cityName, Message message){
        String json = change(cityName);
        if (json == null) {
            return null;
        }
        Gson gson = new Gson();
        Time dto = gson.fromJson(json, Time.class);
        SendMessage sendMessage = new SendMessage();
        String imsak = dto.getResults().getTimeDatetime()[0].getTimes().getSunrise();
        return imsak;
    }

    public String printPeshin(String cityName, Message message){
        String json = change(cityName);
        if (json == null) {
            return null;
        }
        Gson gson = new Gson();
        Time dto = gson.fromJson(json, Time.class);
        SendMessage sendMessage = new SendMessage();
        String imsak = dto.getResults().getTimeDatetime()[0].getTimes().getDhuhr();
        sendMessage.setText(imsak);
        sendMessage.setChatId(String.valueOf(message.getChatId()));
        sendMessage.setParseMode(ParseMode.MARKDOWN);
        return imsak;
    }

    public String printAsr(String cityName, Message message){
        String json = change(cityName);
        if (json == null) {
            return null;
        }
        Gson gson = new Gson();
        Time dto = gson.fromJson(json, Time.class);
        SendMessage sendMessage = new SendMessage();
        String imsak = dto.getResults().getTimeDatetime()[0].getTimes().getAsr();
        sendMessage.setText(imsak);
        sendMessage.setChatId(String.valueOf(message.getChatId()));
        sendMessage.setParseMode(ParseMode.MARKDOWN);
        return imsak;
    }

    public String printQuyoshBotishi(String cityName, Message message){
        String json = change(cityName);
        if (json == null) {
            return null;
        }
        Gson gson = new Gson();
        Time dto = gson.fromJson(json, Time.class);
        SendMessage sendMessage = new SendMessage();
        String imsak = dto.getResults().getTimeDatetime()[0].getTimes().getSunset();
        sendMessage.setText(imsak);
        sendMessage.setChatId(String.valueOf(message.getChatId()));
        sendMessage.setParseMode(ParseMode.MARKDOWN);
        return imsak;
    }

    public String printShom(String cityName, Message message){
        String json = change(cityName);
        if (json == null) {
            return null;
        }
        Gson gson = new Gson();
        Time dto = gson.fromJson(json, Time.class);
        SendMessage sendMessage = new SendMessage();
        String imsak = dto.getResults().getTimeDatetime()[0].getTimes().getMaghrib();
        sendMessage.setText(imsak);
        sendMessage.setChatId(String.valueOf(message.getChatId()));
        sendMessage.setParseMode(ParseMode.MARKDOWN);
        return imsak;
    }

    public String printXufton(String cityName, Message message){
        String json = change(cityName);
        if (json == null) {
            return null;
        }
        Gson gson = new Gson();
        Time dto = gson.fromJson(json, Time.class);
        SendMessage sendMessage = new SendMessage();
        String imsak = dto.getResults().getTimeDatetime()[0].getTimes().getIsha();
        sendMessage.setText(imsak);
        sendMessage.setChatId(String.valueOf(message.getChatId()));
        sendMessage.setParseMode(ParseMode.MARKDOWN);
        return imsak;
    }

    public String change(String cityName){
        try {
            LocalDate localDate = LocalDate.now();
            URL url = new URL("https://api.pray.zone/v2/times/today.json?city=" + cityName);
            URLConnection connection = url.openConnection();

            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder builder = new StringBuilder();

            String line = bufferedReader.readLine();
            while (line != null) {
                builder.append(line);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();

            return builder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}
