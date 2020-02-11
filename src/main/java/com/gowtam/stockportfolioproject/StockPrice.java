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
import javax.swing.JOptionPane;

/**
 *
 * @author 1687968
 * @description holds the closing price for ticker and date
 */
public class StockPrice implements Comparable {
    private LocalDate _date;
    private String _ticker;
    private double _close;  // closing price
    
    public StockPrice(String ticker, LocalDate closeDate, double closePrice)
    {
        _date = closeDate;
        _ticker = ticker;
        _close = closePrice;
    }
    
    public StockPrice(String commaSepString)
    {
        convertFromCommaString(commaSepString);
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
    public String convertToCommaString()
    {
        StringBuilder buf = new StringBuilder();
        buf.append(_ticker)
            .append(",").append(_date)
            .append(",").append(_close);
        return buf.toString();
    }
    
    public void convertFromCommaString(String line)
    {
        String[] x = line.split(",");
        _ticker = x[0];
        _date = LocalDate.parse(x[1]);
        _close = Double.parseDouble(x[2]); 
    }
    
    @Override
    public int compareTo(Object arg0)
    {
        StockPrice p = (StockPrice)arg0;
        int x = this.CloseDate().compareTo(p.CloseDate());
        if (x == 0)
        {
            return _ticker.compareTo(p._ticker);
        }
        return x;
    }

    @Override
    public String toString()
    {
        return "StockPrice: = " + _ticker + " " + _date + " " + _close;
    }
    
    public static ArrayList<StockPrice> readPrices(String folder)
    {
        ArrayList<StockPrice> pricesList = new ArrayList<>();
        String filename = getStockPricesFile(folder);
        File f = new File(filename);
        if (!f.exists())        // file does not exists
            return pricesList;
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine(); //skipping first line, header
            System.out.println(line);
            while ((line = reader.readLine()) != null) {
              System.out.println(line);
//                String[] x = line.split(",");
//                StockPrice p = new StockPrice(x[0], LocalDate.parse(x[1]), Double.parseDouble(x[2]));
                StockPrice p = new StockPrice(line);
                pricesList.add(p);
            }
            reader.close(); 
        }
        catch (IOException ex)
        {
           System.out.println("MY ERROR: file does not exist");
           ex.printStackTrace();
        }
        return pricesList;
        
     }
    
    //save data in portfolio object into portfolioDetails.txt file
    public static boolean savePricesToFile(ArrayList<StockPrice> pricesList, String folder) {
        String filename = getStockPricesFile(folder); 
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write("Ticker,Date,Price");
            writer.newLine();

            for (StockPrice price : pricesList)
            {
                writer.write(price.convertToCommaString());
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
    
    private static String getStockPricesFile(String folder) {
        return folder + File.separator + "StockPrices.txt";
    }

    // merge new prices into pricesList
    // if new price exists in the old list, we will replace
    public static void mergePrices(ArrayList<StockPrice> pricesList, final ArrayList<StockPrice>newPricesList)
    {
        HashSet<String> keys = new HashSet<>();
        HashSet<StockPrice> uniquePrices = new HashSet<>();
        String key;
        
        // add new prices first
        for (StockPrice price : newPricesList)
        {
            key = price.Ticker() + price.CloseDate();
            if (!keys.contains(key))
            {
                uniquePrices.add(price);
                keys.add(key);
            }
        }
        
        // add prices old pricesList
        for (StockPrice price : pricesList)
        {
            key = price.Ticker() + price.CloseDate();
            if (!keys.contains(key))
            {
                uniquePrices.add(price);
                keys.add(key);
            }
        }
        
        pricesList.clear();
        pricesList.addAll(uniquePrices);
        
        Collections.sort(pricesList);
    }
}
