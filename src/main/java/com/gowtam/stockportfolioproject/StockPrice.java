/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gowtam.stockportfolioproject;

import java.time.LocalDate;

/**
 *
 * @author 1687968
 * @description holds the closing price for ticker and date
 */
public class StockPrice {
    private LocalDate _date;
    private String _ticker;
    private double _close;  // closing price
    
    public StockPrice(String ticker, LocalDate closeDate, double closePrice)
    {
        _date = closeDate;
        _ticker = ticker;
        _close = closePrice;
    }
    
    public LocalDate CloseDate()
    {
        return _date;
    }
    public String Ticker()
    {
        return _ticker;
    }
    public double ClosePrice()
    {
        return _close;
    }
}
