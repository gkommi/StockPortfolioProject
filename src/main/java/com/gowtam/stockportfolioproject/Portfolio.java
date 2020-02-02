/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gowtam.stockportfolioproject;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author 1687968
 */
public class Portfolio {
    
/*
    
String: Name
String: Description
String: Account Number
Date: Date Opened
Int: Number of Stock trades
ArrayList<StockTrade>: List of Stock Trades
String: Folder Location
    
save()
read(String)
convertPortfolioDetailsToCommaString(): String
convertCommaStringToPortfolio(String)	

    */
    
    public Portfolio(String folder)
    {
    }
    
    public Portfolio(String name, String description, String accounNumber, LocalDate dateOpened, ArrayList<StockTrade> trades, String folder)
    {
    }

    private String _folder;
    private String _name;
    private String _description;
    private String _accounNumber;
    private LocalDate _dateOpened;
    private ArrayList<StockTrade> _trades;
    
    public String getFolder()
    {
        return _folder;
    }
    
    public String getName() { return _name; }
    //public void setName(String name) { _name = name; }
    public void setName(String name) throws Exception
    {
        if (name == null || name.equals(""))
        {
            System.err.println("name cannot be null or empty");
            throw new Exception("name cannot be null or empty");
        }
        else
             _name = name;
    }
    
    public LocalDate getDateOpened() { return _dateOpened; }
    public void setDateOpened(LocalDate dateOpened) { _dateOpened = dateOpened; }
    public void setDateOpened(String dateOpenedStr) throws Exception
    {
        try {
            LocalDate date = LocalDate.parse(dateOpenedStr);
            _dateOpened = date;
        }
        catch (Exception e)
        {
            throw new Exception("bad date format for DateOpened, use yyyy-mm-dd format");
        }
    }
}
