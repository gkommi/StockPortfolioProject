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
public class StockTrade {
/*
    
String: Ticker
Date: Date Purchased
Int: Quantity
Double: Purchase Price
convertStockTradeToCommaString(): String
convertCommaStringToStockTrade(String)

*/
    
    private String _ticker;
    private int _quantity;
    private LocalDate _purchaseDate;
    private double _purchasePrice;  // closing price
    
    public StockTrade()
    {
    }
    
    public StockTrade(String ticker, int quantity, LocalDate purchaseDate, double purchasePrice)
    {
        _ticker = ticker;
        _quantity = quantity;
        _purchaseDate = purchaseDate;
        _purchasePrice = purchasePrice;
    }
    
    public String Ticker()
    {
        return _ticker;
    }
    public double Quantity()
    {
        return _quantity;
    }
    public LocalDate PurchaseDate()
    {
        return _purchaseDate;
    }
    public double PurchasePrice()
    {
        return _purchasePrice;
    }
    
    public String convertToCommaString()
    {
        StringBuilder buf = new StringBuilder();
        return "";
    }
    
    public void convertFromCommaString(String str)
    {
    
    }
}
