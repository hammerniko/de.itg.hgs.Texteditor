/*
 * SaveFile
 * 
 * Version:  0.0.1
 *
 * Date 20.01.2008
 * 
 * Copyright by TeXter-Project (Jan Rode, Nicholas Lingel, Sebastian Kumpf)
 */

package io;

import java.awt.Frame;
import java.io.File;
import java.io.FileWriter;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SaveFile{
	private JFileChooser			saveDialog;
	private File					currentFile;
	private FileWriter				writer;
	private FileNameExtensionFilter	fileNameFilter;
	private String					saveText;
	private int						check;
	private boolean					cancel;
	private String					path;

	/**
	 * Konstruktor. Initialisiert ein neues Objekt.
	 */
	public SaveFile(){
		saveDialog = new JFileChooser();
		currentFile = null;
		writer = null;
		fileNameFilter = new FileNameExtensionFilter("Text Dateien", "txt",
				"txt");
		saveText = "";
		path = "";
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(saveDialog);
		}
		catch (Exception e){}

		saveDialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
		saveDialog.addChoosableFileFilter(fileNameFilter);
		saveDialog.setFileFilter(fileNameFilter);
		saveDialog.setAcceptAllFileFilterUsed(true);
	}

	/**
	 * Speichert den Editorinhalt in eine neue Datei ab.
	 * 
	 * @return Abbrechen gedr√ºckt true/false.
	 */
	public boolean saveAsNewFile(Frame owner){
		saveDialog.setSelectedFile(new File("Dokument.txt"));
		check = saveDialog.showSaveDialog(owner);
		try{
			if(check == 0){
				// Bei 0 wurde der ÷ffnen Button gedr¸ckt.
				currentFile = saveDialog.getSelectedFile();
				path = currentFile.getAbsolutePath();

				if(path.endsWith(".tex") == false
						&& path.endsWith(".txt") == false){
					currentFile = new File(path + ".tex");
				}

				writer = new FileWriter(currentFile);
				writer.write(saveText);
				writer.close();
				cancel = false;

			}
			else{
				cancel = true;
			}
		}
		catch (Exception e){}
		return cancel;
	}

	/**
	 * Schreibt Text in eine bereits vorhandene Datei.
	 * 
	 * @param pCurrentFile
	 */
	public void saveFile(File pCurrentFile){
		try{
			writer = new FileWriter(pCurrentFile);
			writer.write(saveText);
			writer.close();
		}
		catch (Exception e){
			// System.out.println("Fehler");
		}
	}// end saveFile

	/**
	 * Gibt die aktuelle Datei zur√ºck.
	 * 
	 * @return aktuelle Datei
	 */
	public File getFile(){
		return currentFile;
	}

	/**
	 * Speichert den zu speichernden Text.
	 * 
	 * @param pText
	 */
	public void setText(String pText){
		saveText = pText;
	}

	/**
	 * Liefert einen boolschen Wert zur√ºck, ob auf Abbrechen geklickt wurde
	 * 
	 * @return true/false falls Abgebrochen wurde.
	 */
	public boolean isCancel(){
		return cancel;
	}
}
