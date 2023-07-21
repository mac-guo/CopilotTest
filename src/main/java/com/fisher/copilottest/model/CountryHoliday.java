package com.fisher.copilottest.model;

import java.util.Objects;

public class CountryHoliday {

    // add countryCode and countryDesc and holidayDate and holidayName properties
    private String countryCode;
    private String countryDesc;
    private String holidayDate;
    private String holidayName;

    // add getters and setters for countryCode and countryDesc and holidayDate and holidayName
    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryDesc() {
        return countryDesc;
    }

    public void setCountryDesc(String countryDesc) {
        this.countryDesc = countryDesc;
    }

    public String getHolidayDate() {
        return holidayDate;
    }

    public void setHolidayDate(String holidayDate) {
        this.holidayDate = holidayDate;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }

    // add a constructor that takes countryCode and countryDesc and holidayDate and holidayName as parameters
    public CountryHoliday(String countryCode, String countryDesc, String holidayDate, String holidayName) {
        this.countryCode = countryCode;
        this.countryDesc = countryDesc;
        this.holidayDate = holidayDate;
        this.holidayName = holidayName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryHoliday holiday = (CountryHoliday) o;
        return Objects.equals(countryCode, holiday.countryCode) && Objects.equals(countryDesc, holiday.countryDesc) && Objects.equals(holidayDate, holiday.holidayDate) && Objects.equals(holidayName, holiday.holidayName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, countryDesc, holidayDate, holidayName);
    }
}
