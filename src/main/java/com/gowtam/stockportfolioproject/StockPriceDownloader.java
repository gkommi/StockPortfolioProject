/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gowtam.stockportfolioproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author 1687968
 */
public class StockPriceDownloader {
    //API Key
    private static final String API_KEY = "demo";
    private static final String API_URL_DEMO_CSV = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=MSFT&apikey=demo&datatype=csv";
    private static final String API_URL_DEMO_JSON = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=5min&apikey=demo";
    
    //private static final String API_KEY = "2J3WAYJY09SFK2WM";
    public static final String BASE_URI = "https://www.alphavantage.co/query?";
    public static final String REST_API_URI = BASE_URI + "function=TIME_SERIES_DAILY_ADJUSTED&apikey=" + API_KEY;
    
    //https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=MSFT&apikey=demo&datatype=csv

    public ArrayList<StockPrice> getPrices(String symbol, LocalDate fromDate)
    {
        ArrayList<StockPrice> pricesList = new ArrayList<>();
        
        try {

            BufferedReader br = getPricesFromURL(symbol, fromDate);
            if (br == null)
              {return null;}
            
            // Sample output
            //timestamp,open,high,low,close,adjusted_close,volume,dividend_amount,split_coefficient
            //2020-01-17,167.4200,167.4675,165.4300,167.1000,167.1000,32775039,0.0000,1.0000
            //2020-01-16,164.3500,166.2400,164.0300,166.1700,166.1700,23865360,0.0000,1.0000

            String line;
            int linenum = 0;
            int timestampCol = 0;
            int closeCol = 4;
            String dateStr;
            LocalDate date;
            double closePrice;
            System.out.println("Output from Server .... \n");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                
                String[] fields = line.split(",");
                if (linenum == 0)
                {
                    timestampCol = Arrays.asList(fields).indexOf("timestamp");
                    closeCol = Arrays.asList(fields).indexOf("close");
                }
                else
                {
                    dateStr = fields[timestampCol].trim();
                    date = LocalDate.parse(dateStr);
                    if (date.compareTo(fromDate) >= 0)
                    {
                        closePrice = Double.parseDouble(fields[closeCol]);

                        StockPrice p = new StockPrice(symbol, date, closePrice);
                        pricesList.add(p);
                    }
                }
                linenum++;
            }

        } catch (IOException e) {
              e.printStackTrace();
        }
        
        return pricesList;
    }
    
    public BufferedReader getPricesFromURL(String symbol, LocalDate fromDate)
    {
        ArrayList<StockPrice> pricesList = new ArrayList<>();
        
        try {
            String uri = getURI(symbol, "csv");
            
            URL url = new URL(uri);
            System.out.printf("URL = %s\n", url);
            
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            //conn.disconnect();

            return br;
        } catch (MalformedURLException e) {
              e.printStackTrace();
        } catch (IOException e) {
              e.printStackTrace();
        }
        
        return null;
    }
        
    public String getURI(String symbol, String datatype)
    {
        String uri;
        if (!API_KEY.equals("demo"))
             uri = REST_API_URI + String.format("&symbol=%s&datatype=%s", symbol, datatype);
        else
        {
            if (datatype.equals("csv"))
                uri = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=MSFT&apikey=demo&datatype=csv";
            else
                uri = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=5min&apikey=demo";
        }
        
        return uri;
    }
}
