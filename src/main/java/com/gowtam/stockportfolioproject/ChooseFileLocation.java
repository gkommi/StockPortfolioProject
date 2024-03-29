/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gowtam.stockportfolioproject;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author 1687968
 */
public class ChooseFileLocation extends javax.swing.JFrame {

    /**
     * Creates new form chooseFileLocation
     */
    public ChooseFileLocation() {
        initComponents();
        setTitle("Choose Portfolio Folder");
        this.setDefaultCloseOperation ( JFrame.DISPOSE_ON_CLOSE );
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        folderLabel = new javax.swing.JLabel();
        browseButton = new javax.swing.JButton();
        fileLocationField = new javax.swing.JTextField();
        openPortfolioLabel = new javax.swing.JLabel();
        okayButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Choose Portfolio Folder");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        folderLabel.setText("Folder");

        browseButton.setText("Browse");
        browseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseButtonActionPerformed(evt);
            }
        });

        fileLocationField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileLocationFieldActionPerformed(evt);
            }
        });

        openPortfolioLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        openPortfolioLabel.setText("Open Portfolio");

        okayButton.setText("Okay");
        okayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okayButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(folderLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fileLocationField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(browseButton))
                    .addComponent(openPortfolioLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addComponent(okayButton)
                .addContainerGap(180, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(openPortfolioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(folderLabel)
                    .addComponent(browseButton)
                    .addComponent(fileLocationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(okayButton)
                .addContainerGap(112, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private String _browseType;
    public void setBrowseType(String browseType){
        _browseType = browseType;
        if (browseType.equals("Create"))
        {
            openPortfolioLabel.setText("Create Portfolio");
        } else 
            openPortfolioLabel.setText("Open Portfolio");
    }
    
    private javax.swing.JFrame _mainWindow;
    public void setMainWindowToClose(javax.swing.JFrame mainWindow)
    {
        _mainWindow = mainWindow;
    }
    
    private void fileLocationFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileLocationFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fileLocationFieldActionPerformed

    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseButtonActionPerformed
        // TODO add your handling code here:
    JFileChooser browser = new JFileChooser(); 
    browser.setCurrentDirectory(new java.io.File(System.getProperty("user.home") + "\\Documents"));
    browser.setDialogTitle("Choose Portoflio's Folder");
    browser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

    browser.setAcceptAllFileFilterUsed(false);
    //    
    if (browser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
      System.out.println("getCurrentDirectory(): " 
         +  browser.getCurrentDirectory());
      System.out.println("getSelectedFile() : " 
         +  browser.getSelectedFile());
      fileLocationField.setText(browser.getSelectedFile().toString());
      }
    else {
      System.out.println("No Selection ");
      }
        
    }//GEN-LAST:event_browseButtonActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed

    private void okayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okayButtonActionPerformed
        // TODO add your handling code here:
        // check if the 
        PortfolioMainWindow portfolioWindow = new PortfolioMainWindow();
        if (portfolioWindow.initialize(_browseType, fileLocationField.getText()))
        {
            portfolioWindow.setVisible(true);
            
            setVisible(false);
            dispose();
        
            _mainWindow.setVisible(false);
            _mainWindow.dispose();
        }
    }//GEN-LAST:event_okayButtonActionPerformed

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
            java.util.logging.Logger.getLogger(ChooseFileLocation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChooseFileLocation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChooseFileLocation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChooseFileLocation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChooseFileLocation().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browseButton;
    private javax.swing.JTextField fileLocationField;
    private javax.swing.JLabel folderLabel;
    private javax.swing.JButton okayButton;
    private javax.swing.JLabel openPortfolioLabel;
    // End of variables declaration//GEN-END:variables
}
