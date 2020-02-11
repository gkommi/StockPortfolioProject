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
    
    public StockTrade(String commaString)
    {
        convertFromCommaString(commaString);
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
    public int Quantity()
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
        buf.append(_ticker)
            .append(",").append(_quantity)
            .append(",").append(_purchaseDate)
            .append(",").append(_purchasePrice);
        return buf.toString();
    }
    
    private void convertFromCommaString(String line)
    {
        String[] x = line.split(",");
        _ticker = x[0];
        _quantity = Integer.parseInt(x[1]);
        _purchaseDate = LocalDate.parse(x[2]);
        _purchasePrice = Double.parseDouble(x[3]);
    }
    
    @Override
    public String toString()
    {
        return "StockTrade: = " + _ticker + " " + _quantity + " " + _purchaseDate + " " + _purchasePrice;
    }
    
    public static ArrayList<StockTrade> readTrades(String folder)
    {
        ArrayList<StockTrade> tradesList = new ArrayList<>();
        String filename = getStockTradesFile(folder);
        File f = new File(filename);
        if (!f.exists())        // file does not exists
            return tradesList;
        
        try {
            System.out.println("Reading trades file = " + filename);
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine(); //skipping first line, header
            System.out.println(line);
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
//                StockTrade p = new StockTrade(line);
//                tradesList.add(p);
                tradesList.add(new StockTrade(line));
            }
            reader.close(); 
        }
        catch (IOException ex)
        {
           System.out.println("MY ERROR: file does not exist");
           ex.printStackTrace();
        }
        return tradesList;
        
     }
    
    //save data in portfolio object into portfolioDetails.txt file
    public static boolean saveTradesToFile(ArrayList<StockTrade> tradesList, String folder) {
        String filename = getStockTradesFile(folder); 
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            
            writer.write("Ticker,Quantity,Date Purchased,Purchase Price");
            writer.newLine();
            
            for (StockTrade trade : tradesList)
            {
                writer.write(trade.convertToCommaString());
                writer.newLine();
            }
            
            writer.close();
            //JOptionPane.showMessageDialog(this, "Downloaded and saved!", "Save", JOptionPane.INFORMATION_MESSAGE);
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
    
    private static String getStockTradesFile(String folder) {
        return folder + File.separator + "StockTrades.txt";
    }

}
