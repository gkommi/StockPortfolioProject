/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gowtam.stockportfolioproject;

import java.util.ArrayList;
import java.time.LocalDate;
/**
 *
 * @author 1687968
 */
public class Calculator {
  /*
Portfolio: inputPortfolio
Date: StartDate
Date: EndDate
Calculate(): ArrayList<StockValue>
    */  
    public Calculator()
    {
    }
    
    public ArrayList<StockValue> Calculate(Portfolio portfolio, LocalDate startDate, LocalDate endDate)
    {
        ArrayList<StockValue> stockValues = new ArrayList<>();

        for (StockTrade trade : portfolio.getTrades())
        {
            StockValue value = CalculateForOneTrade(trade, startDate, endDate, portfolio.getPrices());
            //add to the stockValues list
            stockValues.add(value);
        }
        //return the final results
        return stockValues;
    }
    
    public StockValue CalculateForOneTrade(StockTrade trade, LocalDate startDate, LocalDate endDate, ArrayList<StockPrice> prices)
    {
        double startPrice = 0;
        double endPrice = 0;
        double endMarketValue = 0;
        double profitLoss = 0;
        boolean isError = false;
        //calculate profit and loss for each stock trade
        if (startDate.isBefore(trade.getPurchaseDate()))        // start date < purchase date
        {
            startPrice = trade.getPurchasePrice();
        } 
        else 
        {
            startPrice = getStartPrice(prices, trade.getTicker(), startDate);
        }
        if (endDate.isBefore(trade.getPurchaseDate()))        // if end date < purchase date, end price = purchase price
        {
            endPrice = trade.getPurchasePrice();
        } else
        {
            endPrice = getEndPrice(prices, trade.getTicker(), endDate);
        }

        if (endPrice > 0)
        {
            endMarketValue = endPrice * trade.getQuantity();
        }
        
        if (startPrice > 0 && endPrice > 0)
        {
            profitLoss = endMarketValue - (startPrice * trade.getQuantity());
        } else
        {
            isError = true;
        }
        
        //save values into stock value object
        StockValue value = new StockValue(trade,startPrice, endPrice, endMarketValue, profitLoss, isError);
        //add to the stockValues list
        return value;
    }
    
    private double getStartPrice(ArrayList<StockPrice> prices, String ticker, LocalDate date)
    {
        for (StockPrice price : prices)
        {
            if (price.getTicker().equals(ticker) && price.getCloseDate().equals(date))
            {
                return price.getClosePrice();
            }
        }    
        return -1;
    }
    
    private double getEndPrice(ArrayList<StockPrice> prices, String ticker, LocalDate date)
    {
        for (StockPrice price : prices)
        {
            if (price.getTicker().equals(ticker) && price.getCloseDate().equals(date))
            {
                return price.getClosePrice();
            }
        }    
        return -1;
    }
}
