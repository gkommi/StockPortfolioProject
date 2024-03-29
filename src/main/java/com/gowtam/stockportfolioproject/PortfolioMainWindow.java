/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gowtam.stockportfolioproject;

import java.time.LocalDate;
import javax.swing.JFrame;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 1687968
 */
public class PortfolioMainWindow extends javax.swing.JFrame {

    /**
     * Creates new form PortfolioMainWindow
     */
    public PortfolioMainWindow() {
        initComponents();
        this.setDefaultCloseOperation ( JFrame.DISPOSE_ON_CLOSE );
    }

    private String _folder;
    private String _openType;
    private Portfolio _portfolio;
    private DefaultTableModel _model;   //model for stocksDataTable
    
    public boolean initialize(String openType, String folder){
        _openType = openType;
        _folder = folder;
        if (openType.equals("Create")) {
            File f = new File(Portfolio.getPortfolioDetailsFile(_folder));
            if (f.exists())
            {
                int userOption = JOptionPane.showConfirmDialog(this,"This Portfilio already exists, do you want to overwrite?", "Overwrite Portfolio", JOptionPane.ERROR_MESSAGE);
                // 0=yes, 1=no, 2=cancel
                if (userOption != 0)        // no or cancel
                {
                    JOptionPane.showMessageDialog(this, "Cancel", "Overwrite Portfolio?", JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }
                JOptionPane.showMessageDialog(this, "You chose to overwrite the portfolio.", "Overwrite Portfolio?", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
        if (openType.equals("Create")){
            _portfolio = new Portfolio(_folder);
            _portfolio.setName("Portfolio 1");
            _portfolio.setDescription("Portfolio 12");
            _portfolio.setAccountNumber("Portfolio 123");
            _portfolio.setDateOpened(LocalDate.now());
        }
        else {
            _portfolio = Portfolio.readPortfolio(_folder);
        }
        
        //Initialize startDate and endDate
        Calendar cal = Calendar.getInstance();  //get today's date
        cal = Conversions.adjustDate(cal);  //adjust if the date is a weekend
        endDateJXDatePicker.setDate(cal.getTime());
        
        cal.add(Calendar.DATE, -7);        // go back 1 week
        startDateJXDatePicker.setDate(cal.getTime());
        
        _model = (DefaultTableModel) stocksDataTable.getModel();
        _model.setRowCount(0);      // clear table
        
        fillPortfolioScreen();
        
        calculateAndFillStockValuesScreen();
        return true;
    }
    
    private void calculateAndFillStockValuesScreen()
    {
        System.out.println("calculateAndFillStockValuesScreen():");
        
        LocalDate startDate = Conversions.convertToLocalDate(startDateJXDatePicker.getDate());
        LocalDate endDate = Conversions.convertToLocalDate(endDateJXDatePicker.getDate());
        Calculator calculator = new Calculator();
        ArrayList<StockValue> stockValues = calculator.Calculate(_portfolio, startDate, endDate);
        

        checkErrors(stockValues);
        
        _model.setRowCount(0);      // clear table
        for (StockValue s : stockValues)
        {
            System.out.println(s.toString());

            _model.addRow(new Object[] {s.getTicker(), s.getPurchaseDate(), s.getQuantity(), s.getPurchasePrice(),
                s.getStartPrice(), s.getEndPrice(), s.getEndMarketValue(), s.getProfitLoss()});
        }
        System.out.println();
    }
    
    private void checkErrors(ArrayList<StockValue> stockValues)
    {
       int numErrors = 0;
       for (StockValue value : stockValues)
       {
            if (value.getError())
            {
                numErrors++;
            }
        }
        if (numErrors > 0)
        {
            JOptionPane.showMessageDialog(null, "Unable to find prices. There were " + numErrors + " errors. Download latest prices!", "Error in calculation.", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }
    
    private void fillPortfolioScreen()
    {
        nameBox.setText(_portfolio.getName());
        descriptionBox.setText(_portfolio.getDescription());
        folderLocationBox.setText(_portfolio.getFolder());
        accountNumberBox.setText(_portfolio.getAccountNumber());
        dateOpenedBox.setText(_portfolio.getDateOpened().toString());
        numberOfStocksBox.setText(Integer.toString(_portfolio.getNumberOfStocks()));
    }
    
    private void fillPortfolioObject(){
        _portfolio.setName(nameBox.getText());
        _portfolio.setDescription(descriptionBox.getText());
        _portfolio.setAccountNumber(accountNumberBox.getText());
        LocalDate date = LocalDate.parse(dateOpenedBox.getText());
        _portfolio.setDateOpened(date);
        System.out.println(_portfolio);     
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new java.awt.Panel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        descriptionLabel = new javax.swing.JLabel();
        accountNumberLabel = new javax.swing.JLabel();
        dateOpenedLabel = new javax.swing.JLabel();
        numberOfStocksLabel = new javax.swing.JLabel();
        folderLabel = new javax.swing.JLabel();
        nameBox = new javax.swing.JTextField();
        descriptionBox = new javax.swing.JTextField();
        accountNumberBox = new javax.swing.JTextField();
        dateOpenedBox = new javax.swing.JTextField();
        numberOfStocksBox = new javax.swing.JTextField();
        folderLocationBox = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        manageStockTrades = new javax.swing.JButton();
        marketClosingPrices = new javax.swing.JButton();
        savePortfolio = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        endDateLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        stocksDataTable = new javax.swing.JTable();
        stockTradesLabel = new javax.swing.JLabel();
        calculateButton = new javax.swing.JButton();
        startDateLabel = new javax.swing.JLabel();
        startDateJXDatePicker = new org.jdesktop.swingx.JXDatePicker();
        endDateJXDatePicker = new org.jdesktop.swingx.JXDatePicker();

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Your Portfolio");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Portfolio Details"));

        nameLabel.setText("Name");

        descriptionLabel.setText("Description");

        accountNumberLabel.setText("Account #");

        dateOpenedLabel.setText("Date Opened");

        numberOfStocksLabel.setText("# of Stocks");

        folderLabel.setText("Folder");

        nameBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameBoxActionPerformed(evt);
            }
        });

        accountNumberBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountNumberBoxActionPerformed(evt);
            }
        });

        numberOfStocksBox.setEditable(false);
        numberOfStocksBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberOfStocksBoxActionPerformed(evt);
            }
        });

        folderLocationBox.setEditable(false);
        folderLocationBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                folderLocationBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(accountNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateOpenedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numberOfStocksLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(folderLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(folderLocationBox)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameBox, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(descriptionBox, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(accountNumberBox, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(numberOfStocksBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                                .addComponent(dateOpenedBox, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(0, 305, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(nameBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descriptionLabel)
                    .addComponent(descriptionBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(accountNumberLabel)
                    .addComponent(accountNumberBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateOpenedLabel)
                    .addComponent(dateOpenedBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numberOfStocksLabel)
                    .addComponent(numberOfStocksBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(folderLabel)
                    .addComponent(folderLocationBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        manageStockTrades.setText("Manage Stock Trades");
        manageStockTrades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageStockTradesActionPerformed(evt);
            }
        });

        marketClosingPrices.setText("Market Closing Prices");
        marketClosingPrices.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                marketClosingPricesActionPerformed(evt);
            }
        });

        savePortfolio.setText("Save Portfolio");
        savePortfolio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savePortfolioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(manageStockTrades, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(marketClosingPrices, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(savePortfolio, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(marketClosingPrices)
                .addGap(18, 18, 18)
                .addComponent(manageStockTrades)
                .addGap(38, 38, 38))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(savePortfolio)
                    .addContainerGap(241, Short.MAX_VALUE)))
        );

        endDateLabel.setText("End Date:");

        stocksDataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Security Ticker", "Date Purchased", "Quantity", "Purchase Price", "Start Price", "End Price", "End Market Value", "Profit & Loss"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Long.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        stocksDataTable.setEnabled(false);
        jScrollPane3.setViewportView(stocksDataTable);

        stockTradesLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        stockTradesLabel.setText("Stock Trades:");

        calculateButton.setText("Calculate");
        calculateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculateButtonActionPerformed(evt);
            }
        });

        startDateLabel.setText("Start Date:");

        endDateJXDatePicker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endDateJXDatePickerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(172, 172, 172)
                .addComponent(startDateLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(startDateJXDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(endDateLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(endDateJXDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(calculateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(stockTradesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 921, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startDateLabel)
                    .addComponent(endDateLabel)
                    .addComponent(calculateButton)
                    .addComponent(startDateJXDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(endDateJXDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(282, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(stockTradesLabel)
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void folderLocationBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_folderLocationBoxActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_folderLocationBoxActionPerformed

    private void nameBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameBoxActionPerformed

    private void accountNumberBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountNumberBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_accountNumberBoxActionPerformed

    private void savePortfolioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savePortfolioActionPerformed
        // TODO add your handling code here:
        fillPortfolioObject();
        Portfolio.saveToFile(_folder, _portfolio);
    }//GEN-LAST:event_savePortfolioActionPerformed

    private void marketClosingPricesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_marketClosingPricesActionPerformed
        // TODO add your handling code here:
        MarketClosingPricesWindow window = new MarketClosingPricesWindow();
        window.initialize(_portfolio);
        window.setVisible(true);
    }//GEN-LAST:event_marketClosingPricesActionPerformed

    private void manageStockTradesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageStockTradesActionPerformed
        // TODO add your handling code here:
        ManageStockTradesWindow window = new ManageStockTradesWindow();
        window.initialize(_portfolio);
        window.setVisible(true);
    }//GEN-LAST:event_manageStockTradesActionPerformed

    private void calculateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calculateButtonActionPerformed
        // TODO add your handling code here:
        calculateAndFillStockValuesScreen();
        fillPortfolioScreen();
        JOptionPane.showMessageDialog(null, "Calculation complete.", "Calculate", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_calculateButtonActionPerformed

    private void endDateJXDatePickerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endDateJXDatePickerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_endDateJXDatePickerActionPerformed

    private void numberOfStocksBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numberOfStocksBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numberOfStocksBoxActionPerformed
    
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
            java.util.logging.Logger.getLogger(PortfolioMainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PortfolioMainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PortfolioMainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PortfolioMainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PortfolioMainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField accountNumberBox;
    private javax.swing.JLabel accountNumberLabel;
    private javax.swing.JButton calculateButton;
    private javax.swing.JTextField dateOpenedBox;
    private javax.swing.JLabel dateOpenedLabel;
    private javax.swing.JTextField descriptionBox;
    private javax.swing.JLabel descriptionLabel;
    private org.jdesktop.swingx.JXDatePicker endDateJXDatePicker;
    private javax.swing.JLabel endDateLabel;
    private javax.swing.JLabel folderLabel;
    private javax.swing.JTextField folderLocationBox;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JButton manageStockTrades;
    private javax.swing.JButton marketClosingPrices;
    private javax.swing.JTextField nameBox;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField numberOfStocksBox;
    private javax.swing.JLabel numberOfStocksLabel;
    private java.awt.Panel panel1;
    private javax.swing.JButton savePortfolio;
    private org.jdesktop.swingx.JXDatePicker startDateJXDatePicker;
    private javax.swing.JLabel startDateLabel;
    private javax.swing.JLabel stockTradesLabel;
    private javax.swing.JTable stocksDataTable;
    // End of variables declaration//GEN-END:variables
}
