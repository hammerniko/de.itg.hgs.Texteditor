/*
 * FileErrorDialog
 * 
 * Version:  0.0.1
 *
 * Date 20.01.2008
 * 
 * Copyright by TeXter-Project (Jan Rode, Nicholas Lingel, Sebastian Kumpf)
 */

package io;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class FileErrorDialog extends javax.swing.JDialog {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * Konstruktor. Initialisiert ein neues Objekt.
     * @param parent
     * @param modal
     */
    public FileErrorDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        // Größe des Bildschirms ermitteln
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // Position des JFrames errechnen
        int top = (screenSize.height - this.getHeight()) / 2;
        int left = (screenSize.width - this.getWidth()) / 2;
        // Position zuordnen
        this.setLocation(left, top);         
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(this);         
        }//end try
        catch (Exception e) {
        }
                
    }
    
    /** 
     * Initialiserungs Methodde des Konstrutkor, um die GUI zu initialisieren.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelDialog = new javax.swing.JPanel();
        labelErrorMessage = new javax.swing.JLabel();
        buttonOK = new javax.swing.JButton();
        labelAlert = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Fehler");
        setAlwaysOnTop(true);
        setModal(true);
        setResizable(false);

        labelErrorMessage.setText("<html><b>Fehler:</b> Auf die Datei konnte nicht zugegriffen werden.<br/>Sie ist entweder schreibgeschützt oder exisitiert nicht.");

        buttonOK.setText("OK");
        buttonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelDialogLayout = new javax.swing.GroupLayout(panelDialog);
        panelDialog.setLayout(panelDialogLayout);
        panelDialogLayout.setHorizontalGroup(
            panelDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelAlert)
                .addGroup(panelDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDialogLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelErrorMessage))
                    .addGroup(panelDialogLayout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(buttonOK, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelDialogLayout.setVerticalGroup(
            panelDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDialogLayout.createSequentialGroup()
                        .addComponent(labelErrorMessage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonOK))
                    .addComponent(labelAlert))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        getAccessibleContext().setAccessibleParent(this);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Schließt das Fenster
     * @param evt
     */
    private void buttonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOKActionPerformed
        dispose();
    }//GEN-LAST:event_buttonOKActionPerformed
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonOK;
    private javax.swing.JLabel labelAlert;
    private javax.swing.JLabel labelErrorMessage;
    private javax.swing.JPanel panelDialog;
    // End of variables declaration//GEN-END:variables
  
}