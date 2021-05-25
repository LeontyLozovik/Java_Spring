package com.services;

import com.Exceptions.ClientException;
import com.Exceptions.ServerException;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class InitialServise {
    @JsonProperty("day")
    String day;
    @JsonProperty("month")
    String month;
    @JsonProperty("year")
    String year;
    Model model;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            return false;
        InitialServise initialServise = (InitialServise) obj;
        return (this.day.equals(initialServise.getDay()) && this.month.equals(initialServise.getMonth()) && this.year.equals(initialServise.getYear()));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Integer.parseInt(day);
        hash = 31 * hash + Integer.parseInt(month);
        hash = 31 * hash + Integer.parseInt(year);
        return hash;
    }

    public InitialServise(String day, String month, String year, Model model) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.model = model;
    }

    public InitialServise() {
        this.day = "0";
        this.month = "0";
        this.year = "0";
        this.model = null;
    }

    public InitialServise(String day, String month, String year) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.model = null;
    }

    public long DateToLong()
    {
        long result;
        result = Integer.parseInt(this.day) + Integer.parseInt(this.month)*100 + Integer.parseInt(this.year)*10000;
        return result;
    }
}
