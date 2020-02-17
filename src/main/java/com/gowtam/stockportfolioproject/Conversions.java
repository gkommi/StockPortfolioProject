/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gowtam.stockportfolioproject;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author 1687968
 */
public class Conversions {
    
    public static LocalDate convertToLocalDate(Date dateToConvert)
    {
        return dateToConvert.toInstant()
          .atZone(ZoneId.systemDefault())
          .toLocalDate();
    }
    public static Date convertToDate(LocalDate localDateToConvert)
    {
        return java.util.Date.from(localDateToConvert.atStartOfDay().atZone(ZoneId.systemDefault())
            .toInstant());
    }
    //start/end dates that are weekend days are moved back to friday
    public static Calendar adjustDate(Calendar cal)
    {
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
        {
            //Go back one day
            cal.add(Calendar.DATE, -1);
        } else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
        {
            //Go back two days
            cal.add(Calendar.DATE, -2);
        }
        return cal;
    }
}
