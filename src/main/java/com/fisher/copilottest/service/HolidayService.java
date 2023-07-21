package com.fisher.copilottest.service;

import com.fisher.copilottest.model.ApiResponse;
import com.fisher.copilottest.model.CountryHoliday;
import com.fisher.copilottest.model.HolidayRequest;
import com.fisher.copilottest.util.HolidayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

@Service
public class HolidayService {

    //create a post method to add holiday by list
    public ApiResponse addHolidayList(List<CountryHoliday> countryHolidays) {
        HolidayUtil.addHolidays(countryHolidays);
        return HolidayUtil.generateApiResponse("1000", "add holiday success", null);
    }

    //create a method to update list of holidays
    public ApiResponse updateHoliday(List<CountryHoliday> countryHoliday) {
        HolidayUtil.updateHolidays(countryHoliday);
        return HolidayUtil.generateApiResponse("1000", "update holiday success", null);
    }

    public ApiResponse deleteHoliday(List<CountryHoliday> countryHolidays) {
        return null;
    }

    public ApiResponse getNextYearHoliday(HolidayRequest holidayRequest) {
        List<CountryHoliday> responseList = new ArrayList<>();
        List<CountryHoliday> holidayList = HolidayUtil.readCountryHolidayCsvFile();

        // get current year
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);

        for (CountryHoliday holiday : holidayList) {
            if (holiday.getCountryCode().equals(holidayRequest.getCountryCode()) && HolidayUtil.checkDateInTargetYear(holiday.getHolidayDate(), String.valueOf(currentYear + 1))) {
                responseList.add(holiday);
            }
        }

        return HolidayUtil.generateApiResponse("1000", "get next year holiday success", responseList);
    }

    public ApiResponse getNextHoliday(HolidayRequest holidayRequest) {
        List<CountryHoliday> responseList = new ArrayList<>();
        List<CountryHoliday> holidayList = HolidayUtil.readCountryHolidayCsvFile();

        // get current date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(new Date());

        for (CountryHoliday holiday : holidayList) {
            if (holiday.getCountryCode().equals(holidayRequest.getCountryCode()) && HolidayUtil.compareDate(holiday.getHolidayDate(), currentDate)) {
                responseList.add(holiday);
                break;
            }
        }

        return HolidayUtil.generateApiResponse("1000", "get next holiday success", responseList);
    }

    // check if a given date is holiday or not
    public ApiResponse checkHoliday(HolidayRequest holidayRequest) {
        List<CountryHoliday> responseList = new ArrayList<>();
        List<CountryHoliday> holidayList = HolidayUtil.readCountryHolidayCsvFile();

        Map<String, String> response = new HashMap<>();
        for (CountryHoliday holiday : holidayList) {
            if ("Y".equals(response.get(holiday.getCountryCode()))) {
                continue;
            }
            response.put(holiday.getCountryCode(), "N");
            if (holiday.getHolidayDate().equals(holidayRequest.getHolidayDate())) {
                response.put(holiday.getCountryCode(), "Y");
            }
        }

        return HolidayUtil.generateApiResponse("1000", "check holiday success", response);
    }

}
