/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gowtam.stockportfolioproject;

import java.time.LocalDate;
import java.time.ZoneId;
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
}
