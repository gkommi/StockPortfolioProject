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
 */
public class StockValue {
    /*
    string: Ticker
Int: Quantity
Double: Start Price
Double: End Price
Double: End Market Value
Double: ProfitLoss

    */
    private StockTrade trade;
    private double startPrice;
    private double endPrice;
    private double endMarketValue;
    private double profitLoss;
    private boolean isError;
    
    public StockValue(StockTrade trade, double startPrice, double endPrice, double endMarketValue, double profitLoss, boolean isError)
    {
        this.trade = trade;
        this.startPrice = startPrice;
        this.endPrice = endPrice;
        this.endMarketValue = endMarketValue;
        this.profitLoss = profitLoss;
        this.isError = isError;
    }
    
    public StockTrade getStockTrade()
    {
        return trade;
    }   
    public String getTicker()
    {
        return trade.getTicker();
    }
    public int getQuantity()
    {
        return trade.getQuantity();
    }
    public LocalDate getPurchaseDate()
    {
        return trade.getPurchaseDate();
    }
    public double getPurchasePrice()
    {
        return trade.getPurchasePrice();
    }
    public double getStartPrice()
    {
        return startPrice;
    }
    public double getEndPrice()
    {
        return endPrice;
    }
    public double getEndMarketValue()
    {
        return endMarketValue;
    }
    public double getProfitLoss()
    {
        return profitLoss;
    }
    public boolean getError()
    {
        return isError;
    }
    
    
    @Override
    public String toString()
    {
        return "StockVale: = " + getTicker() + " " + getQuantity() + " " + getPurchaseDate() + " " + getPurchasePrice()
                + " " + getStartPrice() + " " + getEndPrice() + " " + getEndMarketValue() + " " + getProfitLoss() + " " + getError();
    }
}
