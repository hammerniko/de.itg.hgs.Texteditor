/*
 * OpenFile
 * 
 * Version:  0.0.1
 *
 * Date 20.01.2008
 * 
 * Copyright by TeXter-Project (Jan Rode, Nicholas Lingel, Sebastian Kumpf)
 */

package io;

import java.awt.Frame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;


public class OpenFile {
    
    private  File currentFile;
    private  String zeile;
    private  String text;
    private  JFileChooser openDialog;
    private  FileNameExtensionFilter fileNameFilter;
    private  FileReader fileReader;
    private  BufferedReader bufferedReader;
        
    /**
     * Konstruktor. Initialisiert ein neues Objekt.
     */
    public OpenFile() {
        currentFile = null;
        zeile = "";
        text = "";
        openDialog = new JFileChooser();
        fileNameFilter = new FileNameExtensionFilter("Text Dateien","txt","rtf");
        fileReader=null;
        bufferedReader=null;
            
        openDialog.setDialogTitle("Datei šffnen");
        openDialog.addChoosableFileFilter(fileNameFilter);
        openDialog.setFileFilter(fileNameFilter);
        openDialog.setAcceptAllFileFilterUsed(true);
            
        try {    
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(openDialog);
        } catch (Exception e){
        }
    } 
    
    /**
     * Liest den Text einer Datei ein und gibt ihn zurück.
     * @return Text, der Datei oder Fehlermeldung
     * @throws java.lang.Exception
     */
    public String readFile(Frame owner) throws Exception{
        
        int check=openDialog.showOpenDialog(owner);  
        try {
			if( check==0) {
			// Bei 0 wurde der Ã–ffnen Button gedrÃ¼ckt.  
			    currentFile=openDialog.getSelectedFile();
			    fileReader = new FileReader(currentFile);
			    bufferedReader= new BufferedReader(fileReader);
			    text="";
			    zeile="";
			    while ((zeile=bufferedReader.readLine())!=null) {
			        text = text + zeile + "\r\n";
			    }//end while
			    bufferedReader.close();
			}//end if
			else{
			    text="cancel";
			}
		} catch (FileNotFoundException e) {
			System.out.println("File i/o Fehler");
		} catch (IOException e) {
			// TODO Automatisch erstellter Catch-Block
			System.out.println("File i/o Fehler");
		}
        return text;
    }
    
    /**
     * Gibt die aktuelle Datei zurÃ¼ck
     * @return Aktuelle Datei
     */
    public File getFile(){
        return currentFile;
    }
}