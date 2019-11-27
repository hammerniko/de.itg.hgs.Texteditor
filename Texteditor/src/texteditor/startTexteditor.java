package texteditor;

import javax.swing.SwingUtilities;

import gui.StandardGui;


public class startTexteditor{

	public static void main(String[] args){

		
	SwingUtilities.invokeLater(new Runnable() {
		
		public void run() {
			StandardGui app = new StandardGui();
			app.setVisible(true);
		}
	});
		

	}

}
