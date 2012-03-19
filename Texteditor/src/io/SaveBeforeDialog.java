/*
 * SaveBeforeDialog
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


public class SaveBeforeDialog extends javax.swing.JDialog {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fileName; 
    private int dialogAnswer;
       
    public SaveBeforeDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        fileName="Unbekannt";
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
        /*Image programIcon = Toolkit.getDefaultToolkit()
        .getImage(this.getClass().getResource(
        "/org/texterproject/icons/exclamation.png"));
        this.setIconImage(programIcon);*/
    }

    /** 
     * Initialiserungs Methodde des Konstrutkor, um die GUI zu initialisieren.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelDialog = new javax.swing.JPanel();
        labelMessage = new javax.swing.JLabel();
        buttonNein = new javax.swing.JButton();
        buttonJa = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();
        labelAssistant = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("TeXter");
        setAlwaysOnTop(true);
        setModal(true);
        setResizable(false);

        labelMessage.setText("\u00C4nderungen in Unbekannt speichern ?");

        buttonNein.setText("Nein");
        buttonNein.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNeinActionPerformed(evt);
            }
        });

        buttonJa.setText("Ja");
        buttonJa.setMaximumSize(new java.awt.Dimension(53, 23));
        buttonJa.setMinimumSize(new java.awt.Dimension(53, 23));
        buttonJa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonJaActionPerformed(evt);
            }
        });

        buttonCancel.setText("Abbrechen");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        labelAssistant.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/texterproject/icons/assistant.png"))); // NOI18N

        javax.swing.GroupLayout panelDialogLayout = new javax.swing.GroupLayout(panelDialog);
        panelDialog.setLayout(panelDialogLayout);
        panelDialogLayout.setHorizontalGroup(
            panelDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDialogLayout.createSequentialGroup()
                        .addComponent(labelAssistant)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelMessage))
                    .addGroup(panelDialogLayout.createSequentialGroup()
                        .addComponent(buttonJa, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonNein, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonCancel)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        panelDialogLayout.setVerticalGroup(
            panelDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelAssistant)
                    .addComponent(labelMessage))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonJa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonNein)
                    .addComponent(buttonCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelDialog, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelDialog, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleParent(this);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Schließt das Fenster mit der Antwort "2".
     * @param evt
     */
    private void buttonNeinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNeinActionPerformed
        dialogAnswer=2;
        dispose();
    }//GEN-LAST:event_buttonNeinActionPerformed

    /**
     * Schließt das Fenster mit der Antwort "1".
     * @param evt
     */
    private void buttonJaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonJaActionPerformed
        dialogAnswer=1;
        dispose();
    }//GEN-LAST:event_buttonJaActionPerformed

    /**
     * Schließt das Fenster
     * @param evt
     */
    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        dispose();
        dialogAnswer=3;
    }//GEN-LAST:event_buttonCancelActionPerformed
  
    /**
     * Setzt den Text, der als Nachricht angezeigt wird.
     * @param pString
     */
    public void setFileName(String pString){
        fileName=pString;
        labelMessage.setText("<html>Änderungen in <b>\""+fileName+"\"</b><br/> vor dem Schließen speichern ?<br/> Falls Nein, gehen alle ungespeicherten Änderung verloren.</html>");
    }

    /**
     * Gibt die Antowort des Nutzers zurück (1 oder 2).
     * @return
     */
    public int getDialogAnswer(){
        return dialogAnswer;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonJa;
    private javax.swing.JButton buttonNein;
    private javax.swing.JLabel labelAssistant;
    private javax.swing.JLabel labelMessage;
    private javax.swing.JPanel panelDialog;
    // End of variables declaration//GEN-END:variables
}