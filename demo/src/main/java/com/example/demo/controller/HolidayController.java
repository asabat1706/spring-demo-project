package com.example.demo.controller;

import com.example.demo.model.Holiday;
import com.example.demo.repository.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class HolidayController {

    @Autowired
    HolidayRepository holidayRepository;

    @GetMapping(value = {"/holidays","/holidays/{display}"})
    public String displayHolidays(@PathVariable(required = false) String display, Model model){
        if(!(display==null)) {
            switch (display) {
                case "all":
                    model.addAttribute("festival", true);
                    model.addAttribute("federal", true);
                    break;
                case "federal":
                    model.addAttribute("federal", true);
                    break;
                case "festival":
                    model.addAttribute("festival", true);
                    break;
            }
        }
        else{
            model.addAttribute("festival", true);
            model.addAttribute("federal", true);
        }
//        List<Holiday> holidays = Arrays.asList(
//                new Holiday(" Jan 1 ","New Year's Day", Holiday.Type.FESTIVAL),
//                new Holiday(" Oct 31 ","Halloween", Holiday.Type.FESTIVAL),
//                new Holiday(" Nov 24 ","Thanksgiving Day", Holiday.Type.FESTIVAL),
//                new Holiday(" Dec 25 ","Christmas", Holiday.Type.FESTIVAL),
//                new Holiday(" Jan 17 ","Martin Luther King Jr. Day", Holiday.Type.FEDERAL),
//                new Holiday(" July 4 ","Independence Day", Holiday.Type.FEDERAL),
//                new Holiday(" Sep 5 ","Labor Day", Holiday.Type.FEDERAL),
//                new Holiday(" Nov 11 ","Veterans Day", Holiday.Type.FEDERAL)
//        );
        List<Holiday> holidays = (List<Holiday>) holidayRepository.findAll();
        Holiday.Type[] types = Holiday.Type.values();
        for (Holiday.Type type : types) {
            model.addAttribute(type.toString(),
                    (holidays.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList())));
        }
        return "holidays.html";
    }
}
