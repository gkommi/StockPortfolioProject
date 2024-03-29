/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gowtam.stockportfolioproject;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 1687968
 */
public class ManageStockTradesWindow extends javax.swing.JFrame {

    /**
     * Creates new form manageStockTradesWindow
     */
    public ManageStockTradesWindow() {
        initComponents();
        //this.setDefaultCloseOperation ( JFrame.DISPOSE_ON_CLOSE );
    }

    private Portfolio _portfolio;
    private DefaultTableModel _model;
    private ArrayList<StockTrade> _tradesList;      // holds all prices read from file
            
    public void initialize(Portfolio portfolio)
    {
        _portfolio = portfolio;
        
        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        todaysDateField.setText(formatter.format(today));
        
        _model = (DefaultTableModel) tradeTable.getModel();
        //_model = new MyTableModel();
        //tradeTable.setModel(_model);
        _model.setRowCount(0);      // clear table

        _tradesList = StockTrade.readTrades(_portfolio.getFolder());
        
        _model = (DefaultTableModel) tradeTable.getModel();
        _model.setRowCount(0);      // clear table
        
        
        loadTradeToTable(_model, _tradesList);
    }
    
    private void loadTradeToTable(DefaultTableModel model, List<StockTrade> list)
    {
        for (StockTrade s : list)
        {
            System.out.println(s.getTicker() + " " + s.getQuantity() + " " + s.getPurchaseDate() + " " + s.getPurchasePrice());

            model.addRow(new Object[] {s.getTicker(), s.getQuantity(), s.getPurchaseDate(), s.getPurchasePrice()});
        }
        System.out.println();
    }
    
//    private class MyTableModel extends DefaultTableModel {
//
//        @Override
//        public boolean isCellEditable(int row, int column) {
//            if (row < _tradesList.size())
//                return true;
//            
//           //all cells false
//           return false;
//        }
//    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        stockTradesInPortfolioLabel = new javax.swing.JLabel();
        todaysDateLabel = new javax.swing.JLabel();
        todaysDateField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tradeTable = new javax.swing.JTable();
        addStockTradeButton = new javax.swing.JButton();
        saveStockTradesButton = new javax.swing.JButton();
        deleteStockTradeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Manage Stock Trades");

        stockTradesInPortfolioLabel.setText("Stock Trades in Portfolio:");

        todaysDateLabel.setText("Todays Date:");

        todaysDateField.setEditable(false);
        todaysDateField.setText("jTextField1");

        tradeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Security Ticker", "Quantity", "Date Purchased", "Purchase Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tradeTable);

        addStockTradeButton.setText("Add Stock Trade");
        addStockTradeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStockTradeButtonActionPerformed(evt);
            }
        });

        saveStockTradesButton.setText("Save");
        saveStockTradesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveStockTradesButtonActionPerformed(evt);
            }
        });

        deleteStockTradeButton.setText("Delete Selected");
        deleteStockTradeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteStockTradeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(stockTradesInPortfolioLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(todaysDateLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(todaysDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(addStockTradeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(saveStockTradesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(deleteStockTradeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stockTradesInPortfolioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(todaysDateLabel)
                    .addComponent(todaysDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addStockTradeButton)
                    .addComponent(saveStockTradesButton)
                    .addComponent(deleteStockTradeButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addStockTradeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStockTradeButtonActionPerformed
        // TODO add your handling code here:
        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        todaysDateField.setText(formatter.format(today));
        _model.addRow(new Object[] {"NewTicker", 100, formatter.format(today), 100.0});
    }//GEN-LAST:event_addStockTradeButtonActionPerformed

    private void deleteStockTradeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteStockTradeButtonActionPerformed
        // TODO add your handling code here:
        if (tradeTable.getSelectedRow() == -1)
        {
            JOptionPane.showMessageDialog(this, "No rows selected.", "Delete Row.", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            System.out.println("# of rows = " + _model.getRowCount());
            System.out.println("selected row = " + tradeTable.getSelectedRow());
            _model.removeRow(tradeTable.getSelectedRow());
        }
    }//GEN-LAST:event_deleteStockTradeButtonActionPerformed

    private void saveStockTradesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveStockTradesButtonActionPerformed
        // TODO add your handling code here:
        System.out.println("# of rows = " + _model.getRowCount());
        
        _tradesList.clear();
        for (int i = 0; i < _model.getRowCount(); i++)
        {
            String ticker = _model.getValueAt(i, 0).toString();
            int quantity = Integer.parseInt(_model.getValueAt(i, 1).toString());
            LocalDate datePurchased = LocalDate.parse(_model.getValueAt(i, 2).toString());
            Double purchasePrice = Double.parseDouble(_model.getValueAt(i, 3).toString());
            
            StockTrade t = new StockTrade(ticker, quantity, datePurchased, purchasePrice);
            System.out.println(t.toString());
            _tradesList.add(t);
        }
        _portfolio.setTrades(_tradesList);
        
        if (StockTrade.saveTradesToFile(_tradesList, _portfolio.getFolder()))
            JOptionPane.showMessageDialog(this, "Saved " + _tradesList.size() + " rows.", "Save.", JOptionPane.INFORMATION_MESSAGE);
        else
            JOptionPane.showMessageDialog(this, "Error saving to File", "Save", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_saveStockTradesButtonActionPerformed

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
            java.util.logging.Logger.getLogger(ManageStockTradesWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageStockTradesWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageStockTradesWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageStockTradesWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageStockTradesWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addStockTradeButton;
    private javax.swing.JButton deleteStockTradeButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton saveStockTradesButton;
    private javax.swing.JLabel stockTradesInPortfolioLabel;
    private javax.swing.JTextField todaysDateField;
    private javax.swing.JLabel todaysDateLabel;
    private javax.swing.JTable tradeTable;
    // End of variables declaration//GEN-END:variables
}
