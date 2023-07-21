package com.fisher.copilottest.util;

import com.fisher.copilottest.model.ApiResponse;
import com.fisher.copilottest.model.CountryHoliday;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HolidayUtil {

    // insert test data into CountryHoliday csv file
    // create a method to read the csv file and return a list of CountryHoliday objects
    public static void main(String[] args) {
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
        addHolidays(countryHolidays);
    }

    // create a method to read the csv file and return a list of CountryHoliday objects
    public static List<CountryHoliday> readCountryHolidayCsvFile() {
        List<CountryHoliday> countryHolidayList = new ArrayList<>();
        // read the csv file and return a list of CountryHoliday objects
        String csvFile = "classpath:CountryHolidays.csv";
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource(csvFile);

        String line = "";
        String csvSplitBy = ",";
        File file = new File(csvFile);
        System.out.println(file.getPath());
        try (BufferedReader br = new BufferedReader(new FileReader(resource.getFile()))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                CountryHoliday holiday = new CountryHoliday(data[0], data[1], data[2], data[3]);
                countryHolidayList.add(holiday);
            }

            //sort the list by country code and holiday date
            countryHolidayList.sort((o1, o2) -> {
                if (o1.getCountryCode().equals(o2.getCountryCode())) {
                    return o1.getHolidayDate().compareTo(o2.getHolidayDate());
                }
                return o1.getCountryCode().compareTo(o2.getCountryCode());
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
        return countryHolidayList;
    }

    // create a method to write a list of CountryHoliday objects into the csv file
    public static void addHolidays(List<CountryHoliday> countryHolidays) {
        List<CountryHoliday> existList = readCountryHolidayCsvFile();
        String csvFile = "classpath:CountryHolidays.csv";
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource(csvFile);
        boolean append = true;

        try (PrintWriter pw = new PrintWriter(new FileWriter(resource.getFile(), append))) {
            for (CountryHoliday holiday : countryHolidays) {
                if (existList.contains(holiday)) {
                    continue;
                }
                pw.print(holiday.getCountryCode());
                pw.print(",");
                pw.print(holiday.getCountryDesc());
                pw.print(",");
                pw.print(holiday.getHolidayDate());
                pw.print(",");
                pw.print(holiday.getHolidayName());
                pw.println();
            }
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // create a method to update a list of CountryHoliday objects into the csv file
    public static boolean updateHolidays(List<CountryHoliday> countryHolidays) {
        List<CountryHoliday> existList = readCountryHolidayCsvFile();
        String csvFile = "classPath: CountryHoliday.csv";
        boolean append = false;

        try (PrintWriter pw = new PrintWriter(new FileWriter(csvFile, append))) {
            for (CountryHoliday holiday : countryHolidays) {
                if (!existList.contains(holiday)) {
                    continue;
                }
                pw.print(holiday.getCountryCode());
                pw.print(",");
                pw.print(holiday.getCountryDesc());
                pw.print(",");
                pw.print(holiday.getHolidayDate());
                pw.print(",");
                pw.print(holiday.getHolidayName());
                pw.println();
            }
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    // check date is in target year
    public static boolean checkDateInTargetYear(String date, String targetYear) {
        String[] dateArray = date.split("-");
        if (dateArray[0].equals(targetYear)) {
            return true;
        }
        return false;
    }

    // compare two dates, if date1 is after date2, return true, else return false
    public static boolean compareDate(String date1, String date2) {
        String[] dateArray1 = date1.split("-");
        String[] dateArray2 = date2.split("-");
        if (Integer.parseInt(dateArray1[0]) > Integer.parseInt(dateArray2[0])) {
            return true;
        } else if (Integer.parseInt(dateArray1[0]) == Integer.parseInt(dateArray2[0])) {
            if (Integer.parseInt(dateArray1[1]) > Integer.parseInt(dateArray2[1])) {
                return true;
            } else if (Integer.parseInt(dateArray1[1]) == Integer.parseInt(dateArray2[1])) {
                if (Integer.parseInt(dateArray1[2]) > Integer.parseInt(dateArray2[2])) {
                    return true;
                }
            }
        }
        return false;
    }

    // generate ApiResponse
    public static ApiResponse generateApiResponse(String code, String message, Object data) {
        ApiResponse response = new ApiResponse(code, message, data);
        return response;
    }
}
