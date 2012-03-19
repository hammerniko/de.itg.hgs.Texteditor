package gui;

import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class About extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JFrame owner;
	
	public About() {
		super();
		// TODO Automatisch erstellter Konstruktoren-Stub
		initialize();
		
	}

	
	
	public About(Frame owner) {
		super(owner);
		// TODO Automatisch erstellter Konstruktoren-Stub
		initialize();
		this.setModal(true);
		
	}

	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setDefaultCloseOperation(About.DISPOSE_ON_CLOSE);
		this.setSize(407, 210);
		this.setTitle("Über...");
		this.setContentPane(getJContentPane());
		this.setLocationRelativeTo(owner);
		
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel = new JLabel();
			jLabel.setText("");
			jLabel.setIcon(new ImageIcon(getClass().getResource("/bilder/SplashTexteditor.png")));
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(jLabel, java.awt.BorderLayout.CENTER);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
