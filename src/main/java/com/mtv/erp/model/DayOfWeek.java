package com.mtv.erp.model;

import java.util.Locale;

import static java.time.format.TextStyle.FULL;

public enum DayOfWeek {
    MONDAY("ПН"), TUESDAY("ВТ"), WEDNESDAY("СР"), THURSDAY("ЧТ"), FRIDAY("ПТ"), SATURDAY("СБ"), SUNDAY("ВС");

    private String day;

    DayOfWeek(String day) {
        this.day = day;
    }

    public String getDay() {
        return day;
    }

    public static DayOfWeek getDay(java.time.DayOfWeek dayOfWeek){
        return DayOfWeek.valueOf(dayOfWeek.getDisplayName(FULL, Locale.ENGLISH).toUpperCase());
    }
}
