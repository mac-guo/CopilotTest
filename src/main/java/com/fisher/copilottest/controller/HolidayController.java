package com.fisher.copilottest.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fisher.copilottest.model.ApiResponse;
import com.fisher.copilottest.model.CountryHoliday;
import com.fisher.copilottest.model.HolidayRequest;
import com.fisher.copilottest.service.HolidayService;
import com.fisher.copilottest.util.HolidayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class HolidayController {

    @Autowired
    private HolidayService holidayService;

    @PostMapping("/initHoliday")
    public String initHoliday() {
        // insert test data into CountryHoliday csv file
        CountryHoliday countryHoliday = new CountryHoliday("US", "United States", "2023-01-01", "New Year's Day");
        CountryHoliday countryHoliday1 = new CountryHoliday("US", "United States", "2023-01-20", "Martin Luther King Jr. Day");
        CountryHoliday countryHoliday2 = new CountryHoliday("US", "United States", "2023-02-17", "Presidents' Day");
        CountryHoliday countryHoliday3 = new CountryHoliday("US", "United States", "2023-05-25", "Memorial Day");
        CountryHoliday countryHoliday4 = new CountryHoliday("US", "United States", "2023-07-03", "Independence Day");
        CountryHoliday countryHoliday5 = new CountryHoliday("US", "United States", "2023-09-07", "Labor Day");
        CountryHoliday countryHoliday6 = new CountryHoliday("US", "United States", "2023-10-12", "Columbus Day");
        CountryHoliday countryHoliday7 = new CountryHoliday("US", "United States", "2024-11-11", "Veterans Day");
        CountryHoliday countryHoliday12 = new CountryHoliday("CN", "China", "2023-01-01", "New Year's Day");
        CountryHoliday countryHoliday13 = new CountryHoliday("CN", "China", "2023-11-24", "Chinese New Year");
        CountryHoliday countryHoliday131 = new CountryHoliday("CN", "China", "2024-11-24", "Chinese New Year");
        CountryHoliday countryHoliday14 = new CountryHoliday("GB", "United Kingdom", "2023-01-01", "New Year's Day");
        CountryHoliday countryHoliday15 = new CountryHoliday("GB", "United Kingdom", "2023-04-10", "Good Friday");
        CountryHoliday countryHoliday16 = new CountryHoliday("GB", "United Kingdom", "2023-11-13", "Easter Monday");
        CountryHoliday countryHoliday161 = new CountryHoliday("GB", "United Kingdom", "2024-01-13", "Easter Monday");
        CountryHoliday countryHoliday162 = new CountryHoliday("GB", "United Kingdom", "2024-11-13", "Easter Monday");
        CountryHoliday countryHoliday17 = new CountryHoliday("IN", "India", "2023-01-01", "New Year's Day");
        CountryHoliday countryHoliday18 = new CountryHoliday("IN", "India", "2023-08-26", "Republic Day");
        CountryHoliday countryHoliday19 = new CountryHoliday("IN", "India", "2023-11-10", "Holi");
        CountryHoliday countryHoliday20 = new CountryHoliday("IN", "India", "2024-11-10", "Holi");
        List<CountryHoliday> countryHolidays = Arrays.asList(countryHoliday, countryHoliday1, countryHoliday2, countryHoliday3, countryHoliday4, countryHoliday5, countryHoliday6, countryHoliday7, countryHoliday12, countryHoliday13, countryHoliday131, countryHoliday14, countryHoliday15, countryHoliday16, countryHoliday161, countryHoliday162, countryHoliday17, countryHoliday18, countryHoliday19, countryHoliday20);
        HolidayUtil.addHolidays(countryHolidays);
        return "OK";
    }

    //create a post method to add new holidays by json
    @PostMapping("/addHoliday")
    public String addHoliday(@RequestBody List<CountryHoliday> countryHolidays) {
        ApiResponse response = holidayService.addHolidayList(countryHolidays);
        return JSONObject.toJSONString(response);
    }

    //create a post method to update holidays
    @PostMapping("/updateHoliday")
    public String updateHoliday(@RequestBody List<CountryHoliday> countryHolidays) {
        ApiResponse response = holidayService.updateHoliday(countryHolidays);
        return JSONObject.toJSONString(response);
    }

    //create a post method to delete holidays
    @PostMapping("/deleteHoliday")
    public String deleteHoliday(@RequestBody List<CountryHoliday> countryHolidays) {
        ApiResponse response = holidayService.deleteHoliday(countryHolidays);
        return JSONObject.toJSONString(response);
    }

    //create a post method to get next year's holidays for given countryCode
    @PostMapping("/getNextYearHoliday")
    @ResponseBody
    public String getNextYearHoliday(@RequestBody HolidayRequest holidayRequest) {
        ApiResponse response = holidayService.getNextYearHoliday(holidayRequest);
        return JSONObject.toJSONString(response);
    }

    //create a post method to get next holiday for given countryCode
    @PostMapping("/getNextHoliday")
    public String getNextHoliday(@RequestBody HolidayRequest holidayRequest) {
        ApiResponse response = holidayService.getNextHoliday(holidayRequest);
        return JSONObject.toJSONString(response);
    }

    //create a post method to check if given date is holiday
    @PostMapping("/checkHoliday")
    public String checkHoliday(@RequestBody HolidayRequest holidayRequest) {
        ApiResponse response = holidayService.checkHoliday(holidayRequest);
        return JSONObject.toJSONString(response);
    }
}
