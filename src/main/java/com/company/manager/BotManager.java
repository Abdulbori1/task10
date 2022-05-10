package com.company.manager;

import com.company.ComponentContainer;
import com.company.Enams;
import com.company.Statistic;
import com.company.menuNamozOqishTartibiAyollarUchun.NamozOqishTartibiAyollarUchun;
import com.company.menuNamozOqishTartibiErkaklarUchun.NamozOqishTartibiErkaklarUchun;
import com.company.collections.Change;
import com.company.kalimalarMenu.*;
import com.company.kalimalarMenu.Kalimalar.*;
import com.company.menu.*;
import com.company.menuZikrlar.*;
import com.company.menuZikrlar.zikrlar.*;
import com.company.namozVaqtlariToshkentShahriMenu.NamozVaqtlariToshkentShahriMenu;
import com.company.namozVaqtlariToshkentShahriMenu.toshkentShahri.ToshkentShahriMenu;
import com.company.namozVaqtlariViloyatlarMenu.NamozVaqtlariViloyatlarMenu;
import com.company.namozVaqtlariViloyatlarMenu.viloyatlar.*;
import com.company.suralarMenu.suralar.*;
import com.company.suralarMenu.SuralarMenu;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;

public class BotManager extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "islom_dini_uzbek_bot";
    }

    @Override
    public String getBotToken() {
        return "5109752791:AAGsGMZEPv6HKOCHS4XKFwHLEMtUXjmf_pI";
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage()) {

            Message message = update.getMessage();

            if (message.hasText()) {
                String text = message.getText();
                User user = message.getFrom();
                if (text.equals("/start") || text.equals("\uD83D\uDD19 Ortga") || text.equals("\uD83D\uDD1D Asosiy Menyu")) {

                    MenuService menuService = new MenuService();

                    if (!checkUserId(user.getId())) {
                        PreparedStatement st = null;
                        Connection connection = null;

                        try {
                            connection = DriverManager
                                    .getConnection("jdbc:postgresql://localHost:5432/java_db_database",
                                            "java_db_user", "12345");
                            String sql = "insert into users(id, first_name, last_name) " +
                                    "values(?, ?, ?)";
                            st = connection.prepareStatement(sql);
                            st.setInt(1, Math.toIntExact(user.getId()));
                            st.setString(2, user.getFirstName());
                            st.setString(3, user.getLastName());
                            st.executeUpdate();

                            st.close();
                            connection.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                if (st != null) {
                                    st.close();
                                }

                                if (connection != null) {
                                    connection.close();
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    try {
                        execute(menuService.start(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("\uD83D\uDD4C  Donat")) {
                    Donat donat = new Donat();
                    try {
                        execute(donat.donat(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("\uD83D\uDCF1  Biz bilan bog'lanish")) {
                    TellManager tellManager = new TellManager();

                    try {
                        execute(tellManager.tellMe(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("\uD83D\uDCFF  Zikrlar")) {
                    ZikrlarManager zikrlarManager = new ZikrlarManager();
                    try {
                        execute(zikrlarManager.zikrlar(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Allohum mag'firili")) {
                    AllohumMagfirili allohumMagfirili = new AllohumMagfirili();
                    try {
                        execute(allohumMagfirili.allohumMagfirili(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Attahiyat")) {
                    Attahiyot attahiyot = new Attahiyot();
                    SendAudio sendAudio = attahiyot.sendAudioAttahiyat(message);
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIGTGH6eVkZkU4jNIOs7W9RRSMvUKVKAAKMFQAC657ZS6LEZ3nJ5vG-IwQ");
                        sendAudio.setAudio(inputFile);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                    try {
                        execute(attahiyot.sendTextAttahiyotMessage1(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    try {
                        execute(attahiyot.sendTextAttahiyatMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Salovot")) {
                    Salovot salovot = new Salovot();
                    try {
                        execute(salovot.sendTextMessage1(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    try {
                        execute(salovot.sendTextMessage2(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Duo")) {
                    Duo duo = new Duo();
                    try {
                        execute(duo.sendTextMessage1(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    try {
                        execute(duo.sendTextMessage2(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Qunut duosi")) {
                    QunutDuosi qunutDuosi = new QunutDuosi();
                    try {
                        execute(qunutDuosi.sendTextMessage1(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    try {
                        execute(qunutDuosi.sendTextMessage2(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("\uD83D\uDD4B  Suralar")) {
                    SuralarMenu qisqaSuralarMenu = new SuralarMenu();
                    try {
                        execute(qisqaSuralarMenu.sendTextMessageMenu(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Nos")) {
                    Nos nos = new Nos();
                    SendAudio sendAudio = nos.sendAudioMessage(message);
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIFqmH6cUrRrywSULEWSA_C_ibPvvZfAAJWFQAC657ZS-191pR8DKDPIwQ");
                        sendAudio.setAudio(inputFile);
                        execute(sendAudio);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        execute(nos.sendTextMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Falaq")) {
                    Falaq falaq = new Falaq();
                    SendAudio sendAudio = falaq.sendAudioMessage(message);
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIFpGH6cKwBXlwYiGGcSTQLenlrW88LAAJNFQAC657ZS-8LgbKX11Q7IwQ");
                        sendAudio.setAudio(inputFile);
                        execute(sendAudio);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        execute(falaq.sendTextMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Ixlos")) {
                    Ixlas ixlas = new Ixlas();
                    SendAudio sendAudio = ixlas.sendAudioMessage(message);
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIFn2H6cBInqbML_Z3tWyHgjl94FolaAAJIFQAC657ZS1wJcpxM10T_IwQ");
                        sendAudio.setAudio(inputFile);
                        execute(sendAudio);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        execute(ixlas.sendTextMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Masad")) {
                    Masad masad = new Masad();
                    SendAudio sendAudio = masad.sendAudioMessage(message);
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIFmWH6b5A_RfrxWVJKy6QnaPkW30UDAAJGFQAC657ZS25qHn3Xo5D_IwQ");
                        sendAudio.setAudio(inputFile);
                        execute(sendAudio);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        execute(masad.sendTextMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Nasr")) {
                    Nasr nasr = new Nasr();
                    SendAudio sendAudio = nasr.sendAudioMessage(message);
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIFmGH6busiMUNQJvrY-r8_SiSsYHUSAAJBFQAC657ZS3OYA_MDKRWfIwQ");
                        sendAudio.setAudio(inputFile);
                        execute(sendAudio);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        execute(nasr.sendTextMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Kofirun")) {
                    Kofirun kofirun = new Kofirun();
                    SendAudio sendAudio = kofirun.sendAudioMessage(message);
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIFlGH6bjg2V2V3d8s1D9ZjVzytr5DNAAI9FQAC657ZS4QXZ1uVujSAIwQ");
                        sendAudio.setAudio(inputFile);
                        execute(sendAudio);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        execute(kofirun.sendTextMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Kavsar")) {
                    Kavsar kavsar = new Kavsar();
                    SendAudio sendAudio = kavsar.sendAudioMessage(message);
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIFi2H6bR2Zr87z04TpYsc_cL9i7TKoAAI7FQAC657ZS9v9J8PvEoO8IwQ");
                        sendAudio.setAudio(inputFile);
                        execute(sendAudio);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        execute(kavsar.sendTextMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Moun")) {
                    Moun moun = new Moun();
                    SendAudio sendAudio = moun.sendAudioMessage(message);
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIFf2H6bDCO9iztm8hbrBI-St9_VIDaAAI5FQAC657ZS-Ft2OR6TIIrIwQ");
                        sendAudio.setAudio(inputFile);
                        execute(sendAudio);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        execute(moun.sendTextMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Quraysh")) {
                    Quraish quraish = new Quraish();
                    SendAudio sendAudio = quraish.sendAudioMessage(message);
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIFemH6auCmnX7C-xb_zMC8nOGJz92VAAI1FQAC657ZS3UxqIfNuVRSIwQ");
                        sendAudio.setAudio(inputFile);
                        execute(sendAudio);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        execute(quraish.sendTextMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Fil")) {
                    Fill fill = new Fill();
                    SendAudio sendAudio = fill.sendAudioMessage(message);
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIFdmH6al7SVhcx6AQTF8lXi1c7aS7eAAIwFQAC657ZSwx7KE6GBelqIwQ");
                        sendAudio.setAudio(inputFile);
                        execute(sendAudio);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        execute(fill.sendTextMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Humaza")) {
                    Humaza humaza = new Humaza();
                    SendAudio sendAudio = humaza.sendAudioMessage(message);
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIFVWH6aU2oAAH3lxrFQ2kbimKzd1NXXwACLRUAAuue2Uv3-zqn2fbgRiME");
                        sendAudio.setAudio(inputFile);
                        execute(sendAudio);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        execute(humaza.sendTextMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Asr")) {
                    Asr asr = new Asr();
                    SendAudio sendAudio = asr.sendAudioMessage(message);
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIFVGH6aNjf4ve-BTW4kdCaYFcFsBJlAAIrFQAC657ZS_7s4I3na3nyIwQ");
                        sendAudio.setAudio(inputFile);
                        execute(sendAudio);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        execute(asr.sendTextMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Takasur")) {
                    Takassur takassur = new Takassur();
                    SendAudio sendAudio = takassur.sendAudioMessage(message);
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIFU2H6Z-biVzdQUZpHxfCum6rtIS3GAAImFQAC657ZS2BmVRhrmev2IwQ");
                        sendAudio.setAudio(inputFile);
                        execute(sendAudio);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        execute(takassur.sendTextMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Qoria")) {
                    Qoria qoria = new Qoria();
                    SendAudio sendAudio = qoria.sendAudioMessage(message);
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIFUmH6Z4OT4iKI0xRTS8XjiyJoizSMAAIlFQAC657ZS8pkWLqgSy36IwQ");
                        sendAudio.setAudio(inputFile);
                        execute(sendAudio);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        execute(qoria.sendTextMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Adiyat")) {
                    Adiyat adiyat = new Adiyat();
                    SendAudio sendAudio = adiyat.sendAudioMessage(message);
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIFTmH6ZxDBAmA9MTMD7kvOBuaaKHM0AAIjFQAC657ZS6c_xCFY4N8AASME");
                        sendAudio.setAudio(inputFile);
                        execute(sendAudio);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        execute(adiyat.sendTextMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Zalzala")) {
                    Zalzala zalzala = new Zalzala();
                    SendAudio sendAudio = zalzala.sendAudioMessage(message);
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIFTGH6ZiK22l_AN1zGXZFAqJijWB4sAAIhFQAC657ZS7iqOAxaW8hSIwQ");
                        sendAudio.setAudio(inputFile);
                        execute(sendAudio);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        execute(zalzala.sendTextMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Bayyina")) {
                    Bayyina bayyina = new Bayyina();
                    SendAudio sendAudio = bayyina.sendAudioMassage(message);
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIFTWH6ZpiyWPAItPyjMoOr-njSp-tfAAIiFQAC657ZS_NrTfdMZCMJIwQ");
                        sendAudio.setAudio(inputFile);
                        execute(sendAudio);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        execute(bayyina.sendTextMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Qadr")) {
                    Qadr qadr = new Qadr();
                    SendAudio sendAudio = qadr.sendAudioMassage(message);
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIFS2H6ZWXuulZHNmT8zL9Ie4SC8jUsAAIaFQAC657ZS24p2Kvx9ttPIwQ");
                        sendAudio.setAudio(inputFile);
                        execute(sendAudio);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        execute(qadr.sendTextMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Alaq")) {
                    Alaq alaq = new Alaq();
                    SendAudio sendAudio = alaq.sendAudioMessage(message);
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIFRGH6ZE7L2_6k9Ay_EHdMekqGMJayAAIXFQAC657ZSwxlvj1SX9xwIwQ");
                        sendAudio.setAudio(inputFile);
                        execute(sendAudio);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        execute(alaq.sendTextMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Tiyn")) {
                    Tiyn tiyn = new Tiyn();
                    SendAudio sendAudio = tiyn.sendAudioMessage(message);
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIFQ2H6Y7cbN5Te3ent61xkwxNMhCarAAITFQAC657ZS2fXlHOVcJB8IwQ");
                        sendAudio.setAudio(inputFile);
                        execute(sendAudio);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        execute(tiyn.sendTextMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Sharh")) {
                    Sharh sharh = new Sharh();
                    SendAudio sendAudio = sharh.sendAudioMessage(message);
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIFP2H6Yw7dg7nfysslvEpxNfnBiILzAAIOFQAC657ZS7fqO6MvYsVAIwQ");
                        sendAudio.setAudio(inputFile);
                        execute(sendAudio);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        execute(sharh.sendTextMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Zuho")) {
                    Zuho zuho = new Zuho();
                    SendAudio sendAudio = zuho.sendAudioMessage(message);
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIFO2H6YbLDGWMFwTAukDzwHxqfZ9ORAAL_FAAC657ZSxqfI81r17_IIwQ");
                        sendAudio.setAudio(inputFile);
                        execute(sendAudio);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        execute(zuho.sendTexMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Fotiha")) {
                    Fotiha fotiha = new Fotiha();
                    SendAudio sendAudio = fotiha.sendAudioMessage(message);
                    try {
                        InputFile inputFile = new InputFile();
//                        inputFile.setMedia(new FileInputStream("audio/sura-al--fatiha-al-ham.mp3"), "sura-al--fatiha-al-ham");
                        inputFile.setMedia("CQACAgIAAxkBAAIFJWH6WtzheydiwZJLtwSRncihD3C3AALcFAAC657ZS2Z7-pOWw_R7IwQ");
                        sendAudio.setAudio(inputFile);
                        execute(sendAudio);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        execute(fotiha.sendTextMessage1(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    try {
                        execute(fotiha.sendTextMessage2(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Shams")) {
                    Shams shams = new Shams();
                    SendAudio sendAudio = shams.sendAudioMessage(message);
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIFMWH6XpzBOpUwTNApW8mJjB2tLzagAALwFAAC657ZSynD2hhVp4WWIwQ");
                        sendAudio.setAudio(inputFile);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                    try {
                        execute(shams.sendTextMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Layl")) {
                    Layl layl = new Layl();
                    SendAudio sendAudio = layl.sendAudioMessage(message);
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIFN2H6X8r--kjP7g2c-dFtZLpljCBbAALxFAAC657ZS3uLlGSUxt9nIwQ");
                        sendAudio.setAudio(inputFile);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                    try {
                        execute(layl.sendTextMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("☝\uD83C\uDFFB  Olti Kalima")) {
                    Kanlima6Menu kanlima6Menu = new Kanlima6Menu();
                    try {
                        execute(kanlima6Menu.sendTextMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Toyyiba")) {
                    Toyyiba toyyiba = new Toyyiba();
                    SendAudio sendAudio = toyyiba.sendAudioMessage(message);
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIF_GH6cs6IdIUUkw5n7z5CYpcjZx0wAAJhFQAC657ZS7eMF1LXjdNuIwQ");
                        sendAudio.setAudio(inputFile);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                    try {
                        execute(toyyiba.sendTextMessage1(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    try {
                        execute(toyyiba.sendMessage2(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Shahodat")) {
                    Shahodat shahodat = new Shahodat();
                    SendAudio sendAudio = shahodat.sendAudioMessage(message);
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIGBmH6c3jGwkNVXyAC9sbJ1j-Yq4zUAAJnFQAC657ZSxY6bXOtt0zvIwQ");
                        sendAudio.setAudio(inputFile);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                    try {
                        execute(shahodat.sendTextMessage1(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    try {
                        execute(shahodat.sendTextMessage2(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Tavhid")) {
                    Tavhid tavhid = new Tavhid();
                    SendAudio sendAudio = tavhid.sendAudioMessage(message);
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIGDmH6dEenyiXqTikdB6QjdbTkvGchAAJuFQAC657ZS5MrQsxNp-HrIwQ");
                        sendAudio.setAudio(inputFile);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                    try {
                        execute(tavhid.sendTextMessage1(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    try {
                        execute(tavhid.sendTextMessage2(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Raddi Kufr")) {
                    RaddiKufr raddiKufr = new RaddiKufr();
                    SendAudio sendAudio = raddiKufr.sendAudioMessage(message);
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIGG2H6dhPjp_VK8469EhOKTwjLC0UOAAKAFQAC657ZSzIAARN7tDS7MSME");
                        sendAudio.setAudio(inputFile);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                    try {
                        execute(raddiKufr.sendTextMessage1(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    try {
                        execute(raddiKufr.sendTextMessage2(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("istig‘for")) {
                    Istigfor istigfor = new Istigfor();
                    SendAudio sendAudio = istigfor.sendAudioMessage(message);
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIGFmH6dUAHyivNCxI9rkz9VIyjBkP9AAJ5FQAC657ZSwetAcCn4lZCIwQ");
                        sendAudio.setAudio(inputFile);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                    try {
                        execute(istigfor.sendTextMessage1(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    try {
                        execute(istigfor.sendTextMessage2(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Tamjid")) {
                    Tamhid tamhid = new Tamhid();
                    SendAudio sendAudio = tamhid.sendAudioMessage(message);
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIGJ2H6dw4I8vy6zzMqy2hbDqbiqh8zAAKIFQAC657ZS8WS9WfFQJxuIwQ");
                        sendAudio.setAudio(inputFile);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                    try {
                        execute(tamhid.sendTextMessage1(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    try {
                        execute(tamhid.sendTextMessage2(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("\uD83D\uDC73\uD83C\uDFFB\u200D♂️  Namoz o'qish tartibi (Erkaklar uchun)")) {
                    NamozOqishTartibiErkaklarUchun namozOqishTartibiErkaklarUchun = new NamozOqishTartibiErkaklarUchun();
                    try {
                        execute(namozOqishTartibiErkaklarUchun.sendTextMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("\uD83E\uDDD5\uD83C\uDFFB  Namoz o'qish tartibi (Ayollar uchun)")) {
                    NamozOqishTartibiAyollarUchun namozOqishTartibiAyollarUchun = new NamozOqishTartibiAyollarUchun();
                    try {
                        execute(namozOqishTartibiAyollarUchun.sendTextMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Bomdod namozi")) {
                    SendVideo sendVideo = new SendVideo();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("BAACAgIAAxkBAAIJtmIAAR2_ii3PuG4lK0mvvltZhUunKgAC6wsAAvj2uUgxnCAReYA91yME");
                        sendVideo.setCaption("\uD83D\uDCF9 бомдод намози тулик укиш тартиби эркаклар учун");
                        sendVideo.setChatId(String.valueOf(message.getChatId()));
                        sendVideo.setParseMode(ParseMode.MARKDOWN);
                        sendVideo.setVideo(inputFile);
                        execute(sendVideo);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Peshin namozi")) {
                    SendVideo sendVideo = new SendVideo();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("BAACAgIAAxkBAAIJ0mIAAfm2dwAB7bC8-RuCK7Sxau_DwRoAAhMLAALL1mBJb63f4fgyYHsjBA");
                        sendVideo.setCaption("\uD83D\uDCF9 пешин намози укилиши батафсил тулик укиш тартиби эркаклар учун");
                        sendVideo.setChatId(String.valueOf(message.getChatId()));
                        sendVideo.setParseMode(ParseMode.MARKDOWN);
                        sendVideo.setVideo(inputFile);
                        execute(sendVideo);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Asr namozi")) {
                    SendVideo sendVideo = new SendVideo();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("BAACAgIAAxkBAAIJ3GIAAft5QhJeg_LOyQ6KLMfipxa0XAACLgsAAmbJSUmQCd-J6ngwBiME");
                        sendVideo.setCaption("\uD83D\uDCF9 аср намози укилиши батафсил тулик укиш тартиби эркаклар учун");
                        sendVideo.setChatId(String.valueOf(message.getChatId()));
                        sendVideo.setParseMode(ParseMode.MARKDOWN);
                        sendVideo.setVideo(inputFile);
                        execute(sendVideo);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Shom namozi")) {
                    SendVideo sendVideo = new SendVideo();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("BAACAgIAAxkBAAIJ3WIAAfwnlcOsrCze3dP7OsFGCQKmogAC9wsAAg5FuUnDGgmL8YnvqiME");
                        sendVideo.setCaption("\uD83D\uDCF9 шом намози укилиши тулик эркаклар учун");
                        sendVideo.setChatId(String.valueOf(message.getChatId()));
                        sendVideo.setParseMode(ParseMode.MARKDOWN);
                        sendVideo.setVideo(inputFile);
                        execute(sendVideo);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Xufton namozi")) {
                    SendVideo sendVideo = new SendVideo();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("BAACAgIAAxkBAAIJ32IAAfzaWkpcuGSo2kJpBg-7C973-AACPA0AAjHGaEtgFi1ncsanHyME");
                        sendVideo.setCaption("\uD83D\uDCF9 хуфтон намози укилиши батафсил тулик укиш тартиби эркаклар учун");
                        sendVideo.setChatId(String.valueOf(message.getChatId()));
                        sendVideo.setParseMode(ParseMode.MARKDOWN);
                        sendVideo.setVideo(inputFile);
                        execute(sendVideo);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Bomdod nomozi")) {
                    SendVideo sendVideo = new SendVideo();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("BAACAgIAAxkBAAIJ7mIAAf5YtWLLq3ZX2P3cjZ4DSnESCQACdQwAAu_HKUvK-iuqJQP7KyME");
                        sendVideo.setCaption("\uD83D\uDCF9 БОМДОД НАМОЗИ АЁЛЛАР УЧУН УРГАНИШ ТУЛИК");
                        sendVideo.setChatId(String.valueOf(message.getChatId()));
                        sendVideo.setParseMode(ParseMode.MARKDOWN);
                        sendVideo.setVideo(inputFile);
                        execute(sendVideo);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Peshin nomozi")) {
                    SendVideo sendVideo = new SendVideo();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("BAACAgIAAxkBAAIJ72IAAf7gAXKaIH_ypTExTDTKGn5bXwACkBMAAgHE6EuJq-YiEAmBByME");
                        sendVideo.setCaption("\uD83D\uDCF9 ПЕШИН НАМОЗИ АЁЛЛАР УЧУН УРГАНИШ ТЎЛИҚ");
                        sendVideo.setChatId(String.valueOf(message.getChatId()));
                        sendVideo.setParseMode(ParseMode.MARKDOWN);
                        sendVideo.setVideo(inputFile);
                        execute(sendVideo);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Asr nomozi")) {
                    SendVideo sendVideo = new SendVideo();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("BAACAgIAAxkBAAIJ8GIAAf9x9fqMOO2sAnafCaDk2AE88gACaw8AAjxU6Eq4detGhblgIyME");
                        sendVideo.setCaption("\uD83D\uDCF9 АСР НАМОЗИ АЁЛЛАР УЧУН УКИЛИШИ ТУЛИК");
                        sendVideo.setChatId(String.valueOf(message.getChatId()));
                        sendVideo.setParseMode(ParseMode.MARKDOWN);
                        sendVideo.setVideo(inputFile);
                        execute(sendVideo);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Shom nomozi")) {
                    SendVideo sendVideo = new SendVideo();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("BAACAgIAAxkBAAIJ8WIBAAELgx_Qeg93OS1Q14vtZZstPwACbw8AAjxU6Erl1SiuOzOySiME");
                        sendVideo.setCaption("\uD83D\uDCF9 шом намози аёллар учун тулик аеллар шом намози укилиши");
                        sendVideo.setChatId(String.valueOf(message.getChatId()));
                        sendVideo.setParseMode(ParseMode.MARKDOWN);
                        sendVideo.setVideo(inputFile);
                        execute(sendVideo);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Xufton nomozi")) {
                    SendVideo sendVideo = new SendVideo();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("BAACAgIAAxkBAAIJ82IBAAGsAQ8TX5VthKncEeBlNyYP8AACYQ8AAjxU6ErcBhIBFwjxuSME");
                        sendVideo.setCaption("\uD83D\uDCF9 хуфтон намози аёллар учун хуфтон намози укилиши аёллар учун");
                        sendVideo.setChatId(String.valueOf(message.getChatId()));
                        sendVideo.setParseMode(ParseMode.MARKDOWN);
                        sendVideo.setVideo(inputFile);
                        execute(sendVideo);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("⏳  Namoz vaqtlari Toshkent viloyati") || text.equals("\uD83D\uDD19 Orqasiga")) {
                    NamozVaqtlariToshkentShahriMenu namozVaqtlariToshkentShahriMenu = new NamozVaqtlariToshkentShahriMenu();
                    try {
                        execute(namozVaqtlariToshkentShahriMenu.sendTextMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Toshkent shahri")) {
                    ToshkentShahriMenu toshkentShahriMenu = new ToshkentShahriMenu();
                    try {
                        execute(toshkentShahriMenu.sendTextMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Angren shahri")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("angren", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("angren", message);
                    String peshin = change.printPeshin("angren", message);
                    String asr = change.printAsr("angren", message);
                    String shom = change.printShom("angren", message);
                    String xufton = change.printXufton("angren", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Toshkent  (Angren shahri) namoz vaqti" + "\n" +
                            "\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Bekobod shahri")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("bekobod", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("bekobod", message);
                    String peshin = change.printPeshin("bekobod", message);
                    String asr = change.printAsr("bekobod", message);
                    String shom = change.printShom("bekobod", message);
                    String xufton = change.printXufton("bekobod", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Toshkent  (Bekobod shahri) namoz vaqti" + "\n" +
                            "\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Parkent Tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("parkent", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("parkent", message);
                    String peshin = change.printPeshin("parkent", message);
                    String asr = change.printAsr("parkent", message);
                    String shom = change.printShom("parkent", message);
                    String xufton = change.printXufton("parkent", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Toshkent  (Parkent shahri) namoz vaqti" + "\n" +
                            "\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Olmaliq tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("olmaliq", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("olmaliq", message);
                    String peshin = change.printPeshin("olmaliq", message);
                    String asr = change.printAsr("olmaliq", message);
                    String shom = change.printShom("olmaliq", message);
                    String xufton = change.printXufton("olmaliq", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Toshkent  (Olmaliq shahri) namoz vaqti" + "\n" +
                            "\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Bo'ka tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("buka", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("buka", message);
                    String peshin = change.printPeshin("buka", message);
                    String asr = change.printAsr("buka", message);
                    String shom = change.printShom("buka", message);
                    String xufton = change.printXufton("buka", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Toshkent  (Bo'ka shahri) namoz vaqti" + "\n" +
                            "\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Nurafshon tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("buka", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("buka", message);
                    String peshin = change.printPeshin("buka", message);
                    String asr = change.printAsr("buka", message);
                    String shom = change.printShom("buka", message);
                    String xufton = change.printXufton("buka", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Toshkent  (Nurafshon tumani) namoz vaqti" + "\n" +
                            "\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("⏳  Namoz vaqtlari viloyatlarda") || text.equals("\uD83D\uDD19 Orqaga")) {
                    NamozVaqtlariViloyatlarMenu namozVaqtlariViloyatlarMenu = new NamozVaqtlariViloyatlarMenu();
                    try {
                        execute(namozVaqtlariViloyatlarMenu.sendTextMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Buhoro viloyati")) {
                    Buhoro buhoro = new Buhoro();
                    try {
                        execute(buhoro.sendTextMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Buhoro shahri")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("bukhara", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("bukhara", message);
                    String peshin = change.printPeshin("bukhara", message);
                    String asr = change.printAsr("bukhara", message);
                    String shom = change.printShom("bukhara", message);
                    String xufton = change.printXufton("bukhara", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Buhoro  (Buhoro shahri) namoz vaqti" + "\n" +
                            "\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Farg'ona viloyati")) {
                    Fargona fargona = new Fargona();
                    try {
                        execute(fargona.sendTextMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Farg'ona shahri")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("fergana", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("fergana", message);
                    String peshin = change.printPeshin("fergana", message);
                    String asr = change.printAsr("fergana", message);
                    String shom = change.printShom("fergana", message);
                    String xufton = change.printXufton("fergana", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Farg'ona  (Farg'ona shahri) namoz vaqti" + "\n" +
                            "\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Marg'ilon tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("margilan", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("margilan", message);
                    String peshin = change.printPeshin("margilan", message);
                    String asr = change.printAsr("margilan", message);
                    String shom = change.printShom("margilan", message);
                    String xufton = change.printXufton("margilan", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Farg'ona  (Marg'ilon tumani) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Oltiariq tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("oltiariq", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("oltiariq", message);
                    String peshin = change.printPeshin("oltiariq", message);
                    String asr = change.printAsr("oltiariq", message);
                    String shom = change.printShom("oltiariq", message);
                    String xufton = change.printXufton("oltiariq", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Farg'ona  (Oltiariq tumani) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Quva tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("kuva", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("kuva", message);
                    String peshin = change.printPeshin("kuva", message);
                    String asr = change.printAsr("kuva", message);
                    String shom = change.printShom("kuva", message);
                    String xufton = change.printXufton("kuva", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Farg'ona  (Quva tumani) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Qoqon shahri")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("kokand", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("kokand", message);
                    String peshin = change.printPeshin("kokand", message);
                    String asr = change.printAsr("kokand", message);
                    String shom = change.printShom("kokand", message);
                    String xufton = change.printXufton("kokand", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Farg'ona  (Qoqon shahri) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Bag'dod tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("fergana", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("fergana", message);
                    String peshin = change.printPeshin("fergana", message);
                    String asr = change.printAsr("fergana", message);
                    String shom = change.printShom("fergana", message);
                    String xufton = change.printXufton("fergana", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Farg'ona  (Bag'dod tumani) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Sirdaryo viloyati")) {
                    Sirdaryo sirdaryo = new Sirdaryo();
                    try {
                        execute(sirdaryo.sendTextMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Sirdaryo shahri")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("sirdaryo", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("sirdaryo", message);
                    String peshin = change.printPeshin("sirdaryo", message);
                    String asr = change.printAsr("sirdaryo", message);
                    String shom = change.printShom("sirdaryo", message);
                    String xufton = change.printXufton("sirdaryo", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Sirdaryo  (Sirdaryo shahri) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Guliston shahri")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("gulistan", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("gulistan", message);
                    String peshin = change.printPeshin("gulistan", message);
                    String asr = change.printAsr("gulistan", message);
                    String shom = change.printShom("gulistan", message);
                    String xufton = change.printXufton("gulistan", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Sirdaryo  (Gulistan shahri) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Jizzax viloyati")) {
                    Jizzah jizzah = new Jizzah();
                    try {
                        execute(jizzah.sendTextMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Jizzax shahri")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("jizzakh", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("jizzakh", message);
                    String peshin = change.printPeshin("jizzakh", message);
                    String asr = change.printAsr("jizzakh", message);
                    String shom = change.printShom("jizzakh", message);
                    String xufton = change.printXufton("jizzakh", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Jizzah  (Jizah shahri) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Zomin tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("zomin", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("zomin", message);
                    String peshin = change.printPeshin("zomin", message);
                    String asr = change.printAsr("zomin", message);
                    String shom = change.printShom("zomin", message);
                    String xufton = change.printXufton("zomin", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Jizzah  (Zomin tumani) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Dostlik tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("zomin", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("zomin", message);
                    String peshin = change.printPeshin("zomin", message);
                    String asr = change.printAsr("zomin", message);
                    String shom = change.printShom("zomin", message);
                    String xufton = change.printXufton("zomin", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Jizzah  (Dostlik tuman) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Forish tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("zomin", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("zomin", message);
                    String peshin = change.printPeshin("zomin", message);
                    String asr = change.printAsr("zomin", message);
                    String shom = change.printShom("zomin", message);
                    String xufton = change.printXufton("zomin", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Jizzah  (Forish tuman) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Navoiy viloyati")) {
                    Navoiy navoiy = new Navoiy();
                    try {
                        execute(navoiy.sendTextMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Navoiy shahri")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("navoi", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("navoi", message);
                    String peshin = change.printPeshin("navoi", message);
                    String asr = change.printAsr("navoi", message);
                    String shom = change.printShom("navoi", message);
                    String xufton = change.printXufton("navoi", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Navoiy  (Navoiy shahri) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Nurota tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("nurota", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("nurota", message);
                    String peshin = change.printPeshin("nurota", message);
                    String asr = change.printAsr("nurota", message);
                    String shom = change.printShom("nurota", message);
                    String xufton = change.printXufton("nurota", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Navoiy  (Nurota tuman) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Zarafshon tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("navoi", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("navoi", message);
                    String peshin = change.printPeshin("navoi", message);
                    String asr = change.printAsr("navoi", message);
                    String shom = change.printShom("navoi", message);
                    String xufton = change.printXufton("navoi", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Navoiy  (Zarafshon tumani) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Uchquduq tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("navoi", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("navoi", message);
                    String peshin = change.printPeshin("navoi", message);
                    String asr = change.printAsr("navoi", message);
                    String shom = change.printShom("navoi", message);
                    String xufton = change.printXufton("navoi", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Navoiy  (Uchquduq tuman) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Namangan viloyati")) {
                    Namangan namangan = new Namangan();
                    try {
                        execute(namangan.sendTextMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Namangan shahri")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("namangan", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("namangan", message);
                    String peshin = change.printPeshin("namangan", message);
                    String asr = change.printAsr("namangan", message);
                    String shom = change.printShom("namangan", message);
                    String xufton = change.printXufton("namangan", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Namangan  (Namangan shahri) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Mingbuloq tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("namangan", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("namangan", message);
                    String peshin = change.printPeshin("namangan", message);
                    String asr = change.printAsr("namangan", message);
                    String shom = change.printShom("namangan", message);
                    String xufton = change.printXufton("namangan", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Namangan  (Mingbuloq shahri) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Chortoq tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("namangan", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("namangan", message);
                    String peshin = change.printPeshin("namangan", message);
                    String asr = change.printAsr("namangan", message);
                    String shom = change.printShom("namangan", message);
                    String xufton = change.printXufton("namangan", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Namangan  (Chortoq tumani) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Uchqorg'on tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("namangan", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("namangan", message);
                    String peshin = change.printPeshin("namangan", message);
                    String asr = change.printAsr("namangan", message);
                    String shom = change.printShom("namangan", message);
                    String xufton = change.printXufton("namangan", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Namangan  ( Uchqorg'on tumani) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Chust tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("chust", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("chust", message);
                    String peshin = change.printPeshin("chust", message);
                    String asr = change.printAsr("chust", message);
                    String shom = change.printShom("chust", message);
                    String xufton = change.printXufton("chust", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Namangan  (Chust tumani) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Pop tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("pop", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("pop", message);
                    String peshin = change.printPeshin("pop", message);
                    String asr = change.printAsr("pop", message);
                    String shom = change.printShom("pop", message);
                    String xufton = change.printXufton("pop", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Namangan  (Pop tumani) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Uychi tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("uychi", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("uychi", message);
                    String peshin = change.printPeshin("uychi", message);
                    String asr = change.printAsr("uychi", message);
                    String shom = change.printShom("uychi", message);
                    String xufton = change.printXufton("uychi", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Namangan  (Uychi tumani) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Samarqand viloyati")) {
                    Samarqand samarqand = new Samarqand();
                    try {
                        execute(samarqand.sendTextMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Samarqand shahri")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("Samarkand", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("Samarkand", message);
                    String peshin = change.printPeshin("Samarkand", message);
                    String asr = change.printAsr("Samarkand", message);
                    String shom = change.printShom("Samarkand", message);
                    String xufton = change.printXufton("Samarkand", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Samarqand  (Samarqand shahri) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Kattaqorg'on tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("Samarkand", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("Samarkand", message);
                    String peshin = change.printPeshin("Samarkand", message);
                    String asr = change.printAsr("Samarkand", message);
                    String shom = change.printShom("Samarkand", message);
                    String xufton = change.printXufton("Samarkand", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Samarqand  (Kattako'rg'on tumani) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Ishtixon tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("Samarkand", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("Samarkand", message);
                    String peshin = change.printPeshin("Samarkand", message);
                    String asr = change.printAsr("Samarkand", message);
                    String shom = change.printShom("Samarkand", message);
                    String xufton = change.printXufton("Samarkand", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Samarqand  (Ishtixon tumani) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Mirbozor tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("Samarkand", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("Samarkand", message);
                    String peshin = change.printPeshin("Samarkand", message);
                    String asr = change.printAsr("Samarkand", message);
                    String shom = change.printShom("Samarkand", message);
                    String xufton = change.printXufton("Samarkand", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Samarqand  (Mirbozor tumani) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Urgut tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("Samarkand", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("Samarkand", message);
                    String peshin = change.printPeshin("Samarkand", message);
                    String asr = change.printAsr("Samarkand", message);
                    String shom = change.printShom("Samarkand", message);
                    String xufton = change.printXufton("Samarkand", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Samarqand  (Urgut tumani) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Surxondaryo viloyati")) {
                    Surxondaryo surxondaryo = new Surxondaryo();
                    try {
                        execute(surxondaryo.sendTextMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Termiz shahri")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("termez", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("termez", message);
                    String peshin = change.printPeshin("termez", message);
                    String asr = change.printAsr("termez", message);
                    String shom = change.printShom("termez", message);
                    String xufton = change.printXufton("termez", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Surxondaryo  (Termiz shahri) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Sherobod tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("termez", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("termez", message);
                    String peshin = change.printPeshin("termez", message);
                    String asr = change.printAsr("termez", message);
                    String shom = change.printShom("termez", message);
                    String xufton = change.printXufton("termez", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Surxondaryo  (Sherobod tumani) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Boysun tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("baysun", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("baysun", message);
                    String peshin = change.printPeshin("baysun", message);
                    String asr = change.printAsr("baysun", message);
                    String shom = change.printShom("baysun", message);
                    String xufton = change.printXufton("baysun", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Surxondaryo  (Boysun tumani) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Sho'rchi tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("baysun", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("baysun", message);
                    String peshin = change.printPeshin("baysun", message);
                    String asr = change.printAsr("baysun", message);
                    String shom = change.printShom("baysun", message);
                    String xufton = change.printXufton("baysun", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Surxondaryo  (Sho'rchi tumani) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Denov tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("denov", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("denov", message);
                    String peshin = change.printPeshin("denov", message);
                    String asr = change.printAsr("denov", message);
                    String shom = change.printShom("denov", message);
                    String xufton = change.printXufton("denov", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Surxondaryo  (Denov tumani) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Qashqadaryo viloyati")) {
                    Qashqadaryo qashqadaryo = new Qashqadaryo();
                    try {
                        execute(qashqadaryo.sendTextMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Qarshi shahri")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("qarshi", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("qarshi", message);
                    String peshin = change.printPeshin("qarshi", message);
                    String asr = change.printAsr("qarshi", message);
                    String shom = change.printShom("qarshi", message);
                    String xufton = change.printXufton("qarshi", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Qashqadaryo  (Qarshi shahri) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Shahrisabz tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("qarshi", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("qarshi", message);
                    String peshin = change.printPeshin("qarshi", message);
                    String asr = change.printAsr("qarshi", message);
                    String shom = change.printShom("qarshi", message);
                    String xufton = change.printXufton("qarshi", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Qashqadaryo  (Shahrisabz tumani) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Dehqonobod tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("qarshi", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("qarshi", message);
                    String peshin = change.printPeshin("qarshi", message);
                    String asr = change.printAsr("qarshi", message);
                    String shom = change.printShom("qarshi", message);
                    String xufton = change.printXufton("qarshi", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Qashqadaryo  (Dehqonobod tumani) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("G'ozor tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("qarshi", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("qarshi", message);
                    String peshin = change.printPeshin("qarshi", message);
                    String asr = change.printAsr("qarshi", message);
                    String shom = change.printShom("qarshi", message);
                    String xufton = change.printXufton("qarshi", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Qashqadaryo  (G'ozor tumani) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Muborak tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("qarshi", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("qarshi", message);
                    String peshin = change.printPeshin("qarshi", message);
                    String asr = change.printAsr("qarshi", message);
                    String shom = change.printShom("qarshi", message);
                    String xufton = change.printXufton("qarshi", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Qashqadaryo  (Muborak tumani) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Andijon viloyati")) {
                    Andijon andijon = new Andijon();
                    try {
                        execute(andijon.sendTextMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Andijon shahri")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("andijan", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("andijan", message);
                    String peshin = change.printPeshin("andijan", message);
                    String asr = change.printAsr("andijan", message);
                    String shom = change.printShom("andijan", message);
                    String xufton = change.printXufton("andijan", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Andijon  (Andijon shahri) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Xojaobod tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("andijan", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("andijan", message);
                    String peshin = change.printPeshin("andijan", message);
                    String asr = change.printAsr("andijan", message);
                    String shom = change.printShom("andijan", message);
                    String xufton = change.printXufton("andijan", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Andijon  (Xojaobod tumani) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Poytug' tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("andijan", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("andijan", message);
                    String peshin = change.printPeshin("andijan", message);
                    String asr = change.printAsr("andijan", message);
                    String shom = change.printShom("andijan", message);
                    String xufton = change.printXufton("andijan", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Andijon  (Poytug' tumani) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Xonobod tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("andijan", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("andijan", message);
                    String peshin = change.printPeshin("andijan", message);
                    String asr = change.printAsr("andijan", message);
                    String shom = change.printShom("andijan", message);
                    String xufton = change.printXufton("andijan", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Andijon  (Xonobod tumani) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Asaka tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("andijan", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("andijan", message);
                    String peshin = change.printPeshin("andijan", message);
                    String asr = change.printAsr("andijan", message);
                    String shom = change.printShom("andijan", message);
                    String xufton = change.printXufton("andijan", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Andijon  (Asaka tumani) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Shahrixon tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("andijan", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("andijan", message);
                    String peshin = change.printPeshin("andijan", message);
                    String asr = change.printAsr("andijan", message);
                    String shom = change.printShom("andijan", message);
                    String xufton = change.printXufton("andijan", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Andijon  (Shahrixon tumani) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Marxamat tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("andijan", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("andijan", message);
                    String peshin = change.printPeshin("andijan", message);
                    String asr = change.printAsr("andijan", message);
                    String shom = change.printShom("andijan", message);
                    String xufton = change.printXufton("andijan", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Andijon  (Marxamat tumani) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Xorazm viloyati")) {
                    Xorazm xorazm = new Xorazm();
                    try {
                        execute(xorazm.sendTextMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Urganch shahri")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("urgench", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("urgench", message);
                    String peshin = change.printPeshin("urgench", message);
                    String asr = change.printAsr("urgench", message);
                    String shom = change.printShom("urgench", message);
                    String xufton = change.printXufton("urgench", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Xorazm  (Urganch shahri) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Yangibozor tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("urgench", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("urgench", message);
                    String peshin = change.printPeshin("urgench", message);
                    String asr = change.printAsr("urgench", message);
                    String shom = change.printShom("urgench", message);
                    String xufton = change.printXufton("urgench", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Xorazm  (Yangibozor tumani) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Xazorast tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("urgench", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("urgench", message);
                    String peshin = change.printPeshin("urgench", message);
                    String asr = change.printAsr("urgench", message);
                    String shom = change.printShom("urgench", message);
                    String xufton = change.printXufton("urgench", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Xorazm  (Xazorast tumani) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Xonqa tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("urgench", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("urgench", message);
                    String peshin = change.printPeshin("urgench", message);
                    String asr = change.printAsr("urgench", message);
                    String shom = change.printShom("urgench", message);
                    String xufton = change.printXufton("urgench", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Xorazm  (Xonqa tumani) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Xiva shahri")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("khiva", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("khiva", message);
                    String peshin = change.printPeshin("khiva", message);
                    String asr = change.printAsr("khiva", message);
                    String shom = change.printShom("khiva", message);
                    String xufton = change.printXufton("khiva", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Xorazm  (Xiva shahri) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Qoraqalpog'iston respublikasi")) {
                    Qoraqalpogiston qoraqalpogiston = new Qoraqalpogiston();
                    try {
                        execute(qoraqalpogiston.sendTextMessage(message));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Nukus shahri")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("nukus", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("nukus", message);
                    String peshin = change.printPeshin("nukus", message);
                    String asr = change.printAsr("nukus", message);
                    String shom = change.printShom("nukus", message);
                    String xufton = change.printXufton("nukus", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Qoraqalpog'iston  (Nukus shahri) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("To'rtkol tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("nukus", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("nukus", message);
                    String peshin = change.printPeshin("nukus", message);
                    String asr = change.printAsr("nukus", message);
                    String shom = change.printShom("nukus", message);
                    String xufton = change.printXufton("nukus", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Qoraqalpog'iston  (To'rtkol tumani) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Mo'ynoq tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("nukus", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("nukus", message);
                    String peshin = change.printPeshin("nukus", message);
                    String asr = change.printAsr("nukus", message);
                    String shom = change.printShom("nukus", message);
                    String xufton = change.printXufton("nukus", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Qoraqalpog'iston  (Mo'ynoq tumani) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Qo'ng'irot tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("nukus", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("nukus", message);
                    String peshin = change.printPeshin("nukus", message);
                    String asr = change.printAsr("nukus", message);
                    String shom = change.printShom("nukus", message);
                    String xufton = change.printXufton("nukus", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Qoraqalpog'iston  (Qo'ng'irot tumani) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Taxtakopik tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("nukus", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("nukus", message);
                    String peshin = change.printPeshin("nukus", message);
                    String asr = change.printAsr("nukus", message);
                    String shom = change.printShom("nukus", message);
                    String xufton = change.printXufton("nukus", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Qoraqalpog'iston  (Taxtakopik tumani) namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Uchtepa tumani") || text.equals("Bektemir tumani")
                        || text.equals("Mirzo Ulug'bek tumani") || text.equals("Mirobod tumani")
                        || text.equals("Sergili tumani") || text.equals("Olmazor tumani")
                        || text.equals("Chilonzor tumani") || text.equals("Shayxontohur tumani")
                        || text.equals("Yunusobod tumani") || text.equals("Yakkasaroy tumani")
                        || text.equals("Yashnobod tumani")) {
                    Change change = new Change();
                    String imsak = change.printBamdod("tashkent", message);
                    String quyoshChiqishi = change.printQuyoshChiqishi("tashkent", message);
                    String peshin = change.printPeshin("tashkent", message);
                    String asr = change.printAsr("tashkent", message);
                    String shom = change.printShom("tashkent", message);
                    String xufton = change.printXufton("tashkent", message);

                    SendMessage sendMessage = new SendMessage();
                    LocalDate localDate = LocalDate.now();
                    sendMessage.setText(localDate + " kun uchun " + "\n" + "\n\uD83C\uDF10 Hudud - Toshkent shahri  (" + text + ") namoz vaqti" + "\n" +
                            "\n\uD83C\uDF0C Bomdod: " + imsak + "\n" +
                            "\uD83C\uDF04 Quyosh chiqishi: " + quyoshChiqishi + "\n" +
                            "\uD83C\uDF07 Peshin: " + peshin + "\n" +
                            "\uD83C\uDF06 Asr: " + asr + "\n" +
                            "\uD83C\uDFD9 Shom: " + shom + "\n" +
                            "\uD83C\uDF03 Xufton: " + xufton);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Baqara")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIP2mIIy5lKCxsimcSZlJTPVBgLutfOAAI4FQACDG85SMoKaBssNpxJIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Oli Imron")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIP22IIy96Wz_nz9aHxBLZoKYigJSdYAAIhFgACuu1ASGwzaXjGKqH4IwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Niso")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIP3GIIzAp9cN0_yaXLdqocXOxa3ausAAIfFgACuu1ASMsR2tq7Ly--IwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Moida")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIP6mII0ZwHc855SRgIsYeynPY3MGTqAAIZFgACuu1ASA5ElGMHaB1pIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("An'om")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIP62II0cVRaSfRdNPZen4wW2xS52FmAAIVFgACuu1ASLOOsUN4SHPMIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("A'rof")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIP7GII0fBNEqWngofjvCBCbls9vUJjAAISFgACuu1ASEk_CnWhis4WIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Anfol")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIP7WII0iKMyJhJIqyIOIVu6PRt5_MnAAIRFgACuu1ASFKQwQzafDk6IwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Tavba")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIP7mII0kfpg-SbUoRCZzKzxJKcuVh8AAIPFgACuu1ASK6RTcfqgOJmIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Yunus")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIP72II0mdDnYR8gLNjgc0t8xeKuuCJAAIOFgACuu1ASBLano9lEsiEIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Hud")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIP8GII0pdHFs1CusxfNJ6TyZ7EAeRSAAINFgACuu1ASJFMjEFKPj4-IwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Yusuf")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIP8WII0rhC7xfFrzhNekUffJ40XT-vAAIMFgACuu1ASOSU802wgi5bIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Ra'd")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIP8mII0t3i9eISjrn7GXny-VRn4hO9AAILFgACuu1ASDA1W7Xh12LzIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Ibrohim")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIP82II0wb3PC3t8PLfvR_Bt9tEc6n9AAIKFgACuu1ASHRkJG08h1ebIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Hijr")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIP9WII02e8tQX71OglKIDg-ycxqCMTAAIHFgACuu1ASOBrjkqa7A_RIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Nahl")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIP9GII0zDfsKJMjhICfoxle9_IAsj9AAIGFgACuu1ASE2WLfznavpFIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Isro")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIP9mII06RddH6qHjQZjF1TiYKLaKqXAAIFFgACuu1ASKuvH2Yzu0cGIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Kahf")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIP92II08IdUJJGSwVF0UeEz50Ju2NoAAICFgACuu1ASEZSDqKi4JvEIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Maryam")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIP-GII0_RmbbEokmRSvVZpjfIpzNI1AAMWAAK67UBIs74e-s5R-rUjBA");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Toha")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIP-WII1BPk5USb2aWFqtS7eyThX0XFAAL_FQACuu1ASKHpIQu3oLZtIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Anbiyo")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQQmII5LczB7IrBgABpWouSKJBfV8F3wACVhQAAr-XSUhEtsJiHp8smiME");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Haj")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIP-mII1DmhKe_3ORlV74ylYQ2quFClAAL8FQACuu1ASLSSNITocCkUIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Mu'minun")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIP-2II1GPnsCY2Ha_O2CJcKLlULlcNAAL6FQACuu1ASH17s_5qKaPRIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Nur")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIP_GII1ITIqyr8p7a_ICncQeZYoLJOAAL5FQACuu1ASKZU66Fee7wpIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Furqon")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIP_mII1MJBWakI-97_HBKkEkja4XlhAAL4FQACuu1ASJqKULcWeJbPIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Shu'aro")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIP_2II1OaXf4O-QTw6Y8fvkuuI4w40AAL2FQACuu1ASDfL5Sai02MbIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Naml")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQAAFiCNUE47qD-8SyV1J2rH_UqeCRwwAC7hUAArrtQEhTc6P9TiX7dCME");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Qasas")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQAWII1SWNS5Cxst_4c2--FQAB-XXWjQAC7RUAArrtQEi9v1ZuTKvz2CME");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Ankabut")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQAmII1UXyw5Tpu15bUsPP02dzFKtgAALrFQACuu1ASFypUOqykWibIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Rum")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQA2II1YF-emMWNTO741jYIHN-j6gXAALpFQACuu1ASOvRH_Z_fdTeIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Luqmon")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQBGII1Z8jD0hmHUYvdU1Oge8NkQcgAALoFQACuu1ASAjVW8714lukIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Sajda")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQBWII1eFuHdMQeaRMLvfhx8emysJzAALnFQACuu1ASDuViPeiTTjkIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Ahzob")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQBmII1gLlXhVqte_1nJlh8k6KM6eyAALmFQACuu1ASKoIZzGq-wH6IwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Saba'")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQB2II1ifVCQl_85dKjKCLzY9ylnSCAALlFQACuu1ASGuiCTnJkgXiIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Fotir")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQCGII1q5-suXj81qhLB1JsP1oDjOJAALjFQACuu1ASDnDfPVLOM7SIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Yosin")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQCWII1tW2sw70WZ4aju7-A--orHc7AALiFQACuu1ASHKLRbBXintVIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Soffat")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQCmII1vobSsVbBlrmf1wu4dVJFknJAALgFQACuu1ASIOW95fdulJUIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Sod")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQC2II1zOiFgcBdF2Qv99uyfv7bcFlAALfFQACuu1ASHHoLPfZRTOQIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Zumar")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQDGII129KxcAvXoovBLQHyphkGIA8AALdFQACuu1ASBYVy12fPAmrIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("G'ofir")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQDWII15E8ULUnmWDknajFVMf4G_h-AALZFQACuu1ASJoKM-AbUeoaIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Fassilat")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQEGII2AYnk4NPVazL7xlAgYNh-PUiAALYFQACuu1ASGMOtGOQmGY7IwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Sho'ro")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQDmII17SEtSEv5GflTGsDLo2lwriBAALWFQACuu1ASF6R8prZkit3IwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Zuhruf")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQEWII2C_dRoBPYFfXikKioCngArR7AALVFQACuu1ASIshUqXik1QYIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Duxon")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQEmII2EzEsogfr9QMGctnpUHYGt6eAALRFQACuu1ASLKLFgVsp2kbIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Josiya")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQQ2II5OM2vCwOqINzOXS8pmgyWEpkAAJTFAACv5dJSHiPENz9CC8TIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Ahqof")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQFGII2JUMZJE07IgcR54gLCzySsu2AALQFQACuu1ASMiCq0raknQiIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Muhammad")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQFWII2LQOTxObRn1NfBt3_VHJ-c6zAALNFQACuu1ASGBfBuUyJpNnIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Fath")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQFmII2NIU6L33xgttMhxsjVNvwYuvAALMFQACuu1ASJBztHpMh-b6IwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Hujurot")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQF2II2QlgpG7RF8Ek0q39ld3lXq9hAALLFQACuu1ASCvRtubwkBmaIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Qof")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQGGII2V0XrAxjaC2szlBLP42PO4V1AALKFQACuu1ASLAGrXSkDIHkIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Zoriyot")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQGWII2X129KEri--oA1KzKsrh6zOsAALJFQACuu1ASBw3cO40IeVfIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Tur")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQGmII2aDjHHb9WQmpiS4lv7MXymGOAALIFQACuu1ASNNQfXzG0oiDIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Najm")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQG2II2bxySQzTwZnzdaNQTE9IQ4xYAALHFQACuu1ASKOGgWW2r5n3IwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Qamar")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQHGII2d3HVcLutNz3iwgQKJJ1w2qCAALGFQACuu1ASFVbnAAB9SxQUSME");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Rahmon")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQHmII2lQFgVrX2AgJ0EC_AAEmN4yNuAACxBUAArrtQEikRThdQ9M4cSME");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Voqea")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQHWII2fudWClwPZBpsXg6BAJVWJPYAALDFQACuu1ASPoDgYd4_yItIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Hadid")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQH2II2ndnTr8I6Om9JM9SXzXRNjAHAALCFQACuu1ASHuZUBxAzjsGIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Mujadala")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQIGII2pYw7dJ7Pn9Rh5LQals3w--BAALAFQACuu1ASFMYKa46pbm4IwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Hashr")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQIWII2rfg6iJw_YV0FUDbjRrjs5WdAAK_FQACuu1ASDc5Q2F_rGEcIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Mumtahana")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQImII2vRImmpaOmiN5w607eo4oke3AAK-FQACuu1ASJm_imbjg6gPIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Sof")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQJGII2zB_Nay1oDu--PThCz8NA-qVAAK9FQACuu1ASFMxCoBs2yjcIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Jum'a")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQJWII21FOg1QjB9T2gisyvDxqb7klAAK8FQACuu1ASEdgIdVyxgJVIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Munofiqun")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQJmII244MDUaOWzjbt9hv_iUBd3JnAAK7FQACuu1ASLn--UUPO7CcIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Tag'obun")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQJ2II261dzPdp-8xu7BGtI407oomFAAK6FQACuu1ASB2l30Ne_GqiIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Taloq")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQKGII28gCUbbqETAK0ikX2DnGL7xYAAK5FQACuu1ASG1kgXLxUI9sIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Tahrim")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQKWII2_Y8U309Kq65OMDgWTENetwWAAK4FQACuu1ASJQ6ST-9or-SIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Mulk")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQKmII3CT-IaZ8bfk_lRKaNRQFjLGGAAK3FQACuu1ASCrrj7NtfEDVIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Qalam")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQK2II3EL6B5asRgieLH7Nh46ktZtAAAK2FQACuu1ASGJn-nk9kDncIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Haaqqa")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQLGII3F2Np8i1slWTqCpqTfKwIdV3AAK1FQACuu1ASN04UmOPyDoSIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Maorij")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQLWII3IAc7U7gJkAgQnrwWCyhawwWAAK0FQACuu1ASHJg0cSKqy3aIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Nuh")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQLmII3J41a8Lmwu4J0WXZCQ-xq2p7AAKzFQACuu1ASOezkl_0fstIIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Jin")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQL2II3NIxqJopCsETneEyyOV4sK8vAAKyFQACuu1ASLg11WVtntt3IwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Muzzammil")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQMGII3UIfUKWYbM60CtOSmSHdOQ-vAAKxFQACuu1ASDpIcq2_hUk2IwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Muddassir")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQMmII3bNJwU3wJXOb7wrMGwYfLKpjAAKwFQACuu1ASAmJVBGJTGkbIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Qiyomat")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQMWII3W9asMSMgpxuaTP22uu1cLI2AAKvFQACuu1ASD4FsRlHuPrjIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Inson")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQM2II3dwZHtGD2bpU9p4LJ6jTc6AlAAKtFQACuu1ASDhRKGTK05QqIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Mursalot")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQNGII3f0Ko7-AhsiHHt9aVOBu2t2LAAKsFQACuu1ASJcAAeURkAV1sCME");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Naba'")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQNWII3h9IuvGUqtJsmTb4CtDyuvncAAKrFQACuu1ASBXI_gfDlCWOIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Nazi'at")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQNmII3t7ikRAc6gv8y6PfGvxOCj9SAAKqFQACuu1ASKUD7dfe4XijIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Abasa")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQN2II3xazOPeDtXcvaBST22UbdtisAAKpFQACuu1ASP74dyKyqwKFIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Takvir")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQOGII30TDuPVnCA7N7_-DAqkCbtwsAAKoFQACuu1ASK0JiwPAck9ZIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Intifot")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQOWII32r8zvQiaT3LlSFO3IzP6PdgAAKnFQACuu1ASJpfNoWo8h0cIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Mutoffifin")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQOmII34u3gxwSM6RZdzYfbspyr5KgAAKmFQACuu1ASOZSbTdxikPhIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Inshiqoq")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQO2II36j8O84SXwAB89RHEAvR42Ui7QACpRUAArrtQEin62Unrk7D5CME");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Buruj")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQPGII38dq_cirGJzf5FXMxsDf8agHAAKkFQACuu1ASFoBDCxxInUMIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Toriq")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQPWII3-NaY8pl2m1Bq7rLzOLEoUrcAAKiFQACuu1ASMLvFICStnORIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("A'lo")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQPmII4AzbIHyISNUYXQ86sQwv4_twAAKhFQACuu1ASJqUGnCY0KhaIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("G'oshiya")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQP2II4D7WwdWmSSSizvI-NOI_IWleAAKgFQACuu1ASPjmjWoZghBVIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Fajr")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQQGII4FsysSUwxcfhZu7vLR1nt11DAAKfFQACuu1ASBYjKKgs_Lw7IwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Balad")) {
                    SendAudio sendAudio = new SendAudio();
                    try {
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia("CQACAgIAAxkBAAIQQWII4HhBICbdPjPv5JlSxh_xhtyxAAKdFQACuu1ASHXuJCkpvmzxIwQ");
                        sendAudio.setAudio(inputFile);
                        sendAudio.setChatId(String.valueOf(message.getChatId()));
                        sendAudio.setParseMode(ParseMode.MARKDOWN);
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (ComponentContainer.enams.equals(Enams.StartText) && message.getFrom().getId() == 911832156) {
                    if (message.hasText()) {
                        ComponentContainer.enams = Enams.EndText;

                        Connection connection = null;
                        Statement statement = null;
                        try {
                            Class.forName("org.postgresql.Driver");

                            connection = DriverManager
                                    .getConnection("jdbc:postgresql://localHost:5432/java_db_database",
                                            "java_db_user", "12345");
                            statement = connection.createStatement();
                            ResultSet resultSet = statement.executeQuery("select * from users");
                            while (resultSet.next()) {
                                Integer id = resultSet.getInt(1);

                                SendMessage sendMessage = new SendMessage();
                                sendMessage.setText(text);
                                sendMessage.setChatId(String.valueOf(id));
                                sendMessage.setReplyMarkup(message.getReplyMarkup());
                                try {
                                    execute(sendMessage);
                                } catch (TelegramApiException e) {
                                    e.printStackTrace();
                                }
                            }
                            statement.close();
                            connection.close();
                        } catch (ClassNotFoundException | SQLException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                if (statement != null) {
                                    statement.close();
                                }

                                if (connection != null) {
                                    connection.close();
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } else if (text.equals("reklama") && user.getId() == 911832156) {
                    if (message.hasText()) {
                        ComponentContainer.enams = Enams.StartText;
                        SendMessage sendMessage = new SendMessage();
                        sendMessage.setText("Jo'nat");
                        sendMessage.setChatId(String.valueOf(message.getChatId()));
                        try {
                            execute(sendMessage);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (text.equals("\uD83D\uDCC9  Bot statistikasi")) {
                    Statistic statistic = new Statistic();
                    Period period = Period.between(LocalDate.now(), statistic.getLocalDate());
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setText("\uD83E\uDDD1\uD83C\uDFFB\u200D\uD83D\uDCBB Botdagi obunachilar: " + statistic.getUserAllTime() + " ta\n" +
                            "\n" +
                            "Faol obunachilar: " + statistic.getUserAllTime() / 2 + " ta\n" +
                            "Oxirgi 24 soatda: " + statistic.getUserDay() + " ta obunachi qo'shildi\n" +
                            "Oxirgi 1 oyda: " + statistic.getUserMonth() + " ta obunachi qo'shildi\n" +
                            "Bot ishga tushganiga: " + period.getYears() +" yil " + period.getMonths() + " oy " + period.getDays() + " kun bo'ldi\n" +
                            "\n" +
                            "\uD83D\uDCCA @islom_dini_uzbek_bot statistikasi");
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                }
            } else if (message.hasPhoto() && ComponentContainer.enams.equals(Enams.StartText) && message.getFrom().getId() == 911832156) {
                ComponentContainer.enams = Enams.EndText;

                SendPhoto sendPhoto = new SendPhoto();

                Connection connection = null;
                Statement statement = null;
                try {
                    Class.forName("org.postgresql.Driver");

                    connection = DriverManager
                            .getConnection("jdbc:postgresql://localHost:5432/java_db_database",
                                    "java_db_user", "12345");
                    statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("select * from users");
                    while (resultSet.next()) {
                        Integer id = resultSet.getInt(1);

                        sendPhoto.setChatId(String.valueOf(id));
                        sendPhoto.setPhoto(new InputFile(message.getPhoto().get(message.getPhoto().size() - 1).getFileId()));
                        sendPhoto.setCaption(message.getCaption());
                        sendPhoto.setReplyMarkup(message.getReplyMarkup());

                        try {
                            execute(sendPhoto);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }
                    statement.close();
                    connection.close();
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (statement != null) {
                            statement.close();
                        }

                        if (connection != null) {
                            connection.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public boolean checkUserId(Long userId) {
        boolean found = false;
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("org.postgresql.Driver");

            connection = DriverManager
                    .getConnection("jdbc:postgresql://localHost:5432/java_db_database",
                            "java_db_user", "12345");
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users");
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");

                if (id == Math.toIntExact(userId)) {
                    found = true;
                    break;
                }
            }
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return found;
    }
}
