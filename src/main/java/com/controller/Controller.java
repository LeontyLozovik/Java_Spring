package com.controller;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.stereotype.Controller
public class Controller
{
    @GetMapping("/hello")
    public String helloPage()
    {
        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodByePage()
    {
        return "first/goodbye";
    }

    @GetMapping("/day")
    public String ConvertDay(@RequestParam(value = "day", required = false, defaultValue = "") String day,
                              @RequestParam(value = "month", required = false, defaultValue = "") String month,
                              @RequestParam(value = "year", required = false, defaultValue = "") String year,
                              Model model)
    {
        int iday = 0, imonth = 0, iyear = 0;
        try
        {
            iday = Integer.parseInt(day);
        }
        catch (Exception ex)
        {
            model.addAttribute("InfoString","Check your entered day");
            return "first/day";
        }
        if(iday > 31 || iday < 1)
        {
            model.addAttribute("InfoString","Check your entered day");
            return "first/day";
        }

        try
        {
            imonth = Integer.parseInt(month);
        }
        catch (Exception ex)
        {
            model.addAttribute("input",month);
            model.addAttribute("InfoString","Check your entered month");
            return "first/day";
        }
        if(imonth > 12 || imonth < 1)
        {
            model.addAttribute("InfoString","Check your entered month");
            return "first/day";
        }

        try
        {
            iyear = Integer.parseInt(year);
        }
        catch (Exception ex)
        {
            model.addAttribute("InfoString","Check your entered year");
            return "first/day";
        }
        if(imonth > 12 || iyear < 0)
        {
            model.addAttribute("InfoString","Check your entered year");
            return "first/day";
        }

        model.addAttribute("YourDate", "Date is: " + day + "." + month + "." + year);
        Calendar c = Calendar.getInstance();
        c.set(iyear - 1900, imonth - 1, iday-1);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        switch (dayOfWeek)
        {
            case 5:
            {
                model.addAttribute("DayOfWeek","It's monday");
                break;
            }
            case 6:
            {
                model.addAttribute("DayOfWeek","It's tuesday");
                break;
            }
            case 7:
            {
                model.addAttribute("DayOfWeek","It's wednesday");
                break;
            }
            case 1:
            {
                model.addAttribute("DayOfWeek","It's thursday");
                break;
            }
            case 2:
            {
                model.addAttribute("DayOfWeek","It's friday");
                break;
            }
            case 3:
            {
                model.addAttribute("DayOfWeek","It's saturday");
                break;
            }
            case 4:
            {
                model.addAttribute("DayOfWeek","It's sunday");
                break;
            }
        }
        return "first/day";
    };
}
