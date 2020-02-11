/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gowtam.stockportfolioproject;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 1687968
 */
public class MarketClosingPricesWindow extends javax.swing.JFrame {

    /**
     * Creates new form marketClosingPricesWindow
     */
    public MarketClosingPricesWindow() {
        initComponents();
    }

    private Portfolio _portfolio;
    private DefaultTableModel _model;
    private ArrayList<StockPrice> _pricesList;      // holds all prices read from file
    
    public void initialize(Portfolio portfolio)
    {
        _portfolio = portfolio;
        
        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        todaysDateField.setDate(today);
        
        cal.add(Calendar.DATE, -7);        // go back 1 week
        latestPricesDateField.setDate(cal.getTime());
        
        _model = (DefaultTableModel) mcpTable.getModel();
        _model.setRowCount(0);      // clear table

        _pricesList = StockPrice.readPrices(_portfolio.getFolder());
        
        loadPricesToTable(_pricesList);
    }
    
    private void loadPricesToTable(List<StockPrice> priceByTicker)
    {
        //Clear table
        _model.setRowCount(0);
            
        //List<StockPrice> priceByTicker = pricesList.get(ticker);
        for (StockPrice p : priceByTicker)
        {
            System.out.printf("%s %s %.4f\n", p.Ticker(), p.CloseDate(), p.ClosePrice());

            _model.addRow(new Object[] {p.Ticker(), p.CloseDate(), p.ClosePrice()});
        }
        System.out.println();
    }
    
    private void downloadPrices(LocalDate fromDate)
    {
        StockPriceDownloader downloader = new StockPriceDownloader();
        ArrayList<String> allTickers = _portfolio.getAllTickersInTrades();
        
        ArrayList<StockPrice> newPricesList = new ArrayList<>();
        for (String ticker : allTickers)
        {
            ArrayList<StockPrice> pricesList = downloader.getPrices(ticker, fromDate);
            newPricesList.addAll(pricesList);
        }
        
        Collections.sort(newPricesList);
        loadPricesToTable(newPricesList);

        StockPrice.mergePrices(_pricesList, newPricesList);
        _portfolio.setPrices(_pricesList);
        StockPrice.savePricesToFile(_pricesList, _portfolio.getFolder());
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        todaysDateMCP = new javax.swing.JLabel();
        latestPricesLabel = new javax.swing.JLabel();
        downloadPricesButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        mcpTable = new javax.swing.JTable();
        todaysDateField = new org.jdesktop.swingx.JXDatePicker();
        latestPricesDateField = new org.jdesktop.swingx.JXDatePicker();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Market Closing Prices");

        todaysDateMCP.setText("Todays Date");

        latestPricesLabel.setText("Load Prices From");

        downloadPricesButton.setText("Download Prices");
        downloadPricesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downloadPricesButtonActionPerformed(evt);
            }
        });

        mcpTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Security Ticker", "Date", "Closing Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        mcpTable.setEnabled(false);
        jScrollPane1.setViewportView(mcpTable);

        todaysDateField.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(latestPricesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(todaysDateMCP))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(latestPricesDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(todaysDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(downloadPricesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(todaysDateMCP)
                    .addComponent(todaysDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(latestPricesLabel)
                    .addComponent(latestPricesDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(downloadPricesButton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void downloadPricesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downloadPricesButtonActionPerformed
        // TODO add your handling code here:
        Date date = latestPricesDateField.getDate();
        LocalDate fromDate = Conversions.convertToLocalDate(date);
        
        downloadPrices(fromDate);
    }//GEN-LAST:event_downloadPricesButtonActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MarketClosingPricesWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MarketClosingPricesWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MarketClosingPricesWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MarketClosingPricesWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MarketClosingPricesWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton downloadPricesButton;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXDatePicker latestPricesDateField;
    private javax.swing.JLabel latestPricesLabel;
    private javax.swing.JTable mcpTable;
    private org.jdesktop.swingx.JXDatePicker todaysDateField;
    private javax.swing.JLabel todaysDateMCP;
    // End of variables declaration//GEN-END:variables
}
