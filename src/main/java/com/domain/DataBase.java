package com.domain;


import javax.persistence.*;

@Entity
@Table(name = "what_day")
public class DataBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer day;
    private Integer month;
    private Integer year;
    private String whatDay;

    public DataBase() {
    }

    public DataBase(Integer day, Integer month, Integer year, String whatDay) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.whatDay = whatDay;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getWhatDay() {
        return whatDay;
    }

    public void setWhatDay(String whatDay) {
        this.whatDay = whatDay;
    }
}
