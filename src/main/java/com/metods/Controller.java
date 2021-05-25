package com.metods;

import com.domain.DataBase;
import com.services.Counter;
//import com.services.DateRepo;
import com.services.DateRepo;
import com.services.InitialServise;
import com.services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.stereotype.Controller
@ComponentScan("com.services")
public class Controller {

    @Autowired
    private Counter counter;
    @Autowired
    private MainService mainService;
    @Autowired
    private InitialServise initialServise;

    @GetMapping("/hello")
    public String helloPage() {
        return "hello";
    }

    @GetMapping("/goodbye")
    public String goodByePage() {
        return "goodbye";
    }

    @GetMapping("/day")
    public String Get() {
        return ("day");
    }

    @GetMapping("/counter")
    public String getCounter(Model model) {
        model.addAttribute("counter", counter.getCounter());
        return ("counter");
    }

    @PostMapping("/day")
    public String ConvertDay(@RequestParam(value = "day", required = false, defaultValue = "") String day,
                             @RequestParam(value = "month", required = false, defaultValue = "") String month,
                             @RequestParam(value = "year", required = false, defaultValue = "") String year,
                             Model model) {
        model.addAttribute("YourDate", "Date is: " + day + "." + month + "." + year);
        initialServise.setDay(day);
        initialServise.setMonth(month);
        initialServise.setYear(year);
        initialServise.setModel(model);
        model = mainService.getResult(initialServise);
        counter.increment();
        return ("day");
    }

    @RequestMapping(value = "/bulkCalculate", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public List<Object> bCalculate(@RequestBody List<InitialServise> inputParamsList){
        return mainService.getBulk(inputParamsList);
    }
}
