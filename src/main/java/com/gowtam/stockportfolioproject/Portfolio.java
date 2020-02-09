/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gowtam.stockportfolioproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;

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
        _folder = folder;
        _trades = new ArrayList<>();
    }
    
    public Portfolio(String folder, String commaString)
    {
        _folder = folder;
        _trades = new ArrayList<>();
        _prices = new ArrayList<>();
        convertFromCommaString(commaString);
    }
    
    public Portfolio(String name, String description, String accountNumber, LocalDate dateOpened, String folder)
    {
        _name = name;
        _description = description;
        _accountNumber = accountNumber;
        _dateOpened = dateOpened;
        _folder = folder;
        _trades = new ArrayList<>();
        _prices = new ArrayList<>();
    }

    private String _folder;
    private String _name;
    private String _description;
    private String _accountNumber;
    private LocalDate _dateOpened;
    private ArrayList<StockTrade> _trades;
    private ArrayList<StockPrice> _prices;
    
    public String getFolder()
    {
        return _folder;
    }
    //public void setFolder(String folder)  { _folder = folder; }
    
    public String getName() { return _name; }
    public void setName(String name) { _name = name; }
    
    public String getDescription() { return _description; }
    public void setDescription(String description) { _description = description; }

    
    public String getAccountNumber() { return _accountNumber; }
    public void setAccountNumber(String accountNumber) { _accountNumber = accountNumber; } 
    
    
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
    public int getNumberOfStocks() { return _trades.size(); }

    public ArrayList<StockTrade> getTrades()
    {
        return _trades;
    }
    public void setTrades(ArrayList<StockTrade> trades)
    {
        _trades.clear();
        _trades.addAll(trades);
    }
    public ArrayList<String> getAllTickersInTrades()
    {
        Set<String> set = new HashSet<>();
        for (StockTrade t : _trades)
        {
            set.add(t.Ticker());
        }
        
        ArrayList<String> tickers = new ArrayList<>(set);
        
        Collections.sort(tickers);
        return tickers;
    }
    public ArrayList<StockPrice> getPrices()
    {
        return _prices;
    }
    public void setPrices(ArrayList<StockPrice> prices)
    {
        _prices.clear();
        _prices.addAll(prices);
    }
    
    public String convertToCommaString()
    {
        StringBuilder buf = new StringBuilder();
        buf.append(_name)
            .append(",").append(_description)
            .append(",").append(_accountNumber)
            .append(",").append(_dateOpened)
            .append(",").append(_trades.size())
            .append(",").append(_folder);
        return buf.toString();
    }
    
    private void convertFromCommaString(String line)
    {
        String[] x = line.split(",");
        //String name, String description, String accounNumber, LocalDate dateOpened, ArrayList<StockTrade> trades, String folder)
         _name = x[0];
         _description = x[1];
         _accountNumber = x[2];
         _dateOpened = LocalDate.parse(x[3]);
    }
    
    @Override
    public String toString()
    {
        return "Name = " + _name + ", Description = " + _description + ", Account Number = " + _accountNumber + ", Date Opened = " + _dateOpened 
                + ", Number of Trades = " + _trades.size() + ", Folder Location = " + _folder;
    }
    
    
    public static Portfolio readPortfolio(String folder)
    {
        Portfolio portfolio = null;
        try {
            String filename = getPortfolioDetailsFile(folder);
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine(); //skipping first line, header
            System.out.println(line);
            
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                portfolio = new Portfolio(folder, line);
            }
            reader.close();
            
            if (portfolio != null)
            {
                portfolio.setTrades(StockTrade.readTrades(folder));
                portfolio.setPrices(StockPrice.readPrices(folder));
            }
            
        }
        catch (IOException ex)
        {
           System.out.println("MY ERROR: file does not exist");
           ex.printStackTrace();
        }
        return portfolio;
     }
    
    //save data in portfolio object into portfolioDetails.txt file
    public static boolean saveToFile(String folder, Portfolio portfolio) {
        String filename = getPortfolioDetailsFile(folder); 
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write("Name,Description,Account Number,Date Opened,Number of Stocks");
            writer.newLine();
            writer.write(portfolio.convertToCommaString());
            writer.newLine();
            
            writer.close();
            //JOptionPane.showMessageDialog(this, "Saved!", "Save", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }     
        catch (IOException ex)
        {
          System.out.println("MY ERROR: file does not exist");
          ex.printStackTrace();
          //JOptionPane.showMessageDialog(this, "Error saving to File", "Save", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
    public static String getPortfolioDetailsFile(String folder) {
        return folder + File.separator + "PortfolioDetails.txt";
    }
    
    
}
