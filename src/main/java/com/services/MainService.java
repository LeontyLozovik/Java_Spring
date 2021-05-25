package com.services;

import com.Exceptions.ClientException;
import com.Exceptions.ServerException;
import com.domain.DataBase;
import com.metods.Stat;
import com.metods.WhatDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MainService {

    Map<Long, String> states = new HashMap<Long, String>();

    public void Inicialize(Long value, String msg){
        states.put(value, msg);
    }

    @Autowired
    private CashService cashService;
    @Autowired
    private DateRepo dateRepo;

    public int[] DataTransformAndCheck(InitialServise initialServise) {
        int iday = 0;
        int imonth = 0;
        int iyear = 0;
        int MonthCount = 0;
        int result[];

        result = new int[3];

        try {
            iyear = Integer.parseInt(initialServise.getYear());
        } catch (Exception ex) {
            throw new ServerException();
        }

        try {
            imonth = Integer.parseInt(initialServise.getMonth());
        } catch (Exception ex) {
            throw new ServerException();
        }
        if (imonth > 12 || imonth < 1) {
            throw new ClientException();
        }

        if (imonth == 4 || imonth == 6 || imonth == 9 || imonth == 11)
            MonthCount = 1;
        else if (imonth == 2 && iyear % 4 == 0)
            MonthCount = 2;
        else if (imonth == 2 && iyear % 4 != 0)
            MonthCount = 3;

        try {
            iday = Integer.parseInt(initialServise.getDay());
        } catch (Exception ex) {
            throw new ServerException();
        }
        if (iday > (31 - MonthCount) || iday < 1) {
            throw new ClientException();
        }
        result[0] = iday;
        result[1] = imonth;
        result[2] = iyear;
        return (result);
    }


    public Model getResult(InitialServise initialServise) {
        int result[];
        DataBase dataBase = new DataBase();
        result = new int[3];
        result = this.DataTransformAndCheck(initialServise);
        if (cashService.hasKey(initialServise)) {
            Model model = initialServise.getModel();
            model.addAttribute("DayOfWeek", cashService.get(initialServise));
            initialServise.setModel(model);
            return model;
        } else {
            WhatDay whatday = new WhatDay();
            whatday.setDay(result[0]);
            whatday.setMonth(result[1]);
            whatday.setYear(result[2]);
            String dayOfWeek = whatday.Get_week_day();
            Model model = initialServise.getModel();
            model.addAttribute("DayOfWeek", dayOfWeek);
            initialServise.setModel(model);
            dataBase.setDay(result[0]);
            dataBase.setMonth(result[1]);
            dataBase.setYear(result[2]);
            dataBase.setWhatDay(dayOfWeek);
            dateRepo.save(dataBase);
            cashService.put(initialServise, dayOfWeek);
            return model;
            }
        }

    Stat stat = new Stat();
    public List<Object> getBulk(List<InitialServise> params){
        List<Object> days;
        Integer count= params.size();
        days=params.stream().map((param)->{
            WhatDay whatday = new WhatDay();
            whatday.setDay(Integer.parseInt(param.getDay()));
            whatday.setMonth(Integer.parseInt(param.getMonth()));
            whatday.setYear(Integer.parseInt(param.getYear()));
            String dayOfWeek = whatday.Get_week_day();

            String normalFormDate = param.getDay() + '.' + param.getMonth() + '.' + param.getYear();
            Long longDate = param.DateToLong();
            this.Inicialize(longDate, normalFormDate);

            stat.addAnswer(dayOfWeek);
            if(stat.getMin() == null) stat.setMin(normalFormDate);
            else{
                if(getbyvalue(normalFormDate)<getbyvalue(stat.getMin())){
                    stat.setMin(normalFormDate);
                }
            }
            if(stat.getMax() == null) stat.setMax(normalFormDate);
            else{
                if(getbyvalue(normalFormDate)>getbyvalue(stat.getMax())){
                    stat.setMax(normalFormDate);
                }
            }
            return dayOfWeek;
        }).collect(Collectors.toList());
        stat.setAmount_in(count);
        days.add(stat);
        return days;
    }

    public Long getbyvalue(String value){
        for (Long key : states.keySet()) {
            String obj = states.get(key);
            if (key != null) {
                if (value.equals(obj)) {
                    return key;// нашли наше значение и возвращаем  ключ
                }
            }
        }
        return null;
    }
}
