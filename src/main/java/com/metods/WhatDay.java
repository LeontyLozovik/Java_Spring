package com.metods;

import java.util.Calendar;

public class WhatDay {

    static int day;
    static int month;
    static int year;

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public static String Get_week_day() {
        Calendar c = Calendar.getInstance();
        c.set(year - 1900, month - 1, day - 1);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        switch (dayOfWeek) {
            case 5: {
                return "It's monday";
            }
            case 6: {
                return "It's tuesday";
            }
            case 7: {
                return "It's wednesday";
            }
            case 1: {
                return "It's thursday";
            }
            case 2: {
                return "It's friday";
            }
            case 3: {
                return "It's saturday";
            }
            case 4: {
                return "It's sunday";
            }
        }
        return "error";
    }
}
