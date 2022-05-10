package com.company.collections;

import java.time.LocalDate;

public class Timedate {

    private Integer timestamp;
    private String gregorian;
    private String hijri;

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public String getGregorian() {
        return gregorian;
    }

    public void setGregorian(String gregorian) {
        this.gregorian = gregorian;
    }

    public String getHijri() {
        return hijri;
    }

    public void setHijri(String hijri) {
        this.hijri = hijri;
    }
}
