/*
 * Control
 * 
 * Version:  0.0.1
 *
 * Date 20.01.2008
 * 
 * Copyright by TeXter-Project (Jan Rode, Nicholas Lingel, Sebastian Kumpf)
 */
package texteditor;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import gui.StandardGui;
import io.FileErrorDialog;
import io.OpenFile;
import io.SaveFile;

public class Steuerung {

    private StandardGui dieGui;
    private String currentEditorText;
    private FileErrorDialog fileErrorDialog;
    private OpenFile openFileFU;
    private SaveFile saveFileFU;
    
 

    /**
     * Control-Konstruktor, initialisiert ein neues Objekt.
     */
    public Steuerung(StandardGui pGUI) {
        dieGui = pGUI;
        fileErrorDialog = new FileErrorDialog(dieGui, true);
        openFileFU = new OpenFile();
        saveFileFU = new SaveFile();
        currentEditorText = "";
    }

    /**
     * Steuert das "Speichern unter" Verfahren
     */
    public void saveAsNewFile() {
    		currentEditorText = dieGui.getEditorText();
            saveFileFU.setText(currentEditorText);
            saveFileFU.saveAsNewFile(dieGui);
            dieGui.setCurrentFile(saveFileFU.getFile());
            if (saveFileFU.isCancel() == false) {
                 
            }
        //}
    }

    /**
     * Steuert das "Speichern" Verfahren
     */
    public void saveFile() {
        if (dieGui.getCurrentFile() != null) {
            currentEditorText = dieGui.getEditorText();
            saveFileFU.setText(currentEditorText);
            saveFileFU.saveFile(dieGui.getCurrentFile());
            
        } else {
            saveAsNewFile();
        }
    }

    /**
     * Steuert das "Datei Öffnen" Verfahren
     */
    public void openFile() {
        try {
            currentEditorText = openFileFU.readFile(dieGui);
            if (!(currentEditorText.equals("cancel"))) {
                dieGui.setEditorText("");
                dieGui.setCurrentFile(openFileFU.getFile());
                dieGui.setEditorText(currentEditorText);
                               
            }
        } catch (Exception ex) {
            fileErrorDialog.setVisible(true);
        }
    }
    
    public void print(){
    	PrinterJob pjob = PrinterJob.getPrinterJob(); 
    			 
		if ( pjob.printDialog() == false ) 
		  return; 
		 
		pjob.setPrintable( dieGui.getEditorPrintable() ); 
		pjob.setCopies(pjob.getCopies());
				
		try {
			pjob.print();
		} catch (PrinterException e1) {
			// TODO Automatisch erstellter Catch-Block
			e1.printStackTrace();
		}
    }

    /**
     * Steuert das Bauen der *.dvi Dateien
     */
    
}
