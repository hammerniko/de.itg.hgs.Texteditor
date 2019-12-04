package gui;

import java.awt.BorderLayout;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.print.Printable;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.undo.UndoManager;

import texteditor.Steuerung;

/**
 * * *
 * 
 * @author Nico Standard Applikation mit ActionListenern
 */
@SuppressWarnings("deprecation")
public class StandardGui extends JFrame implements ActionListener, DocumentListener {

	private static final String READY = "ready";
	private static final String INHALT_VON_ZWISCHENABLAGE_EINFUEGEN = "Inhalt von Zwischenablage einfuegen";
	private static final String DATEI_DRUCKEN = "Datei drucken";
	private static final String DATEIFUNKTIONEN = "Dateifunktionen";
	private static final String DATEI = "Datei";
	private static final String DATEI_OEFFNEN = "Datei oeffnen";
	private static final String PATH_ICON_FOLDER = "/icon/fldr_obj.gif";
	private static final String SPEICHERN_UNTER = "Speichern unter";
	private static final String PATH_ICON_PASTE = "/icon/paste_obj.gif";
	private static final String PATH_ICON_COPY = "/icon/copy_edit.gif";
	private static final String PATH_ICON_CUT = "/icon/editcut.png";
	private static final String UEBER = "ueber";
	private static final String PATH_ICON_EXIT = "/icon/icon (17).gif";
	private static final String PATH_ICON_E_PRINT = "/icon/e_print_edit.gif";
	private static final String FONT_DIALOG = "Dialog";
	private static final String BEARBEITEN_DER_AKTUELLEN_DATEI = "Bearbeiten der aktuellen Datei";
	private static final String BEARBEITEN = "Bearbeiten";
	
	private static final String VERSION = "HGS-Texteditor 0.4";
	private static final String PATH_ICON_LOGO_SCHWARZ_GIF = "/icon/logo_schwarz.gif";
	private static final String KOPIEREN = "Kopieren";
	private static final String EINFUEGEN = "Einfuegen";
	private static final String AUSSCHNEIDEN = "Ausschneiden";
	private static final String ALLES_AUSWAEHLEN = "Alles ausw‰hlen";
	private static final int UNDO_LIMIT = 100;
	private static final long serialVersionUID = -101370178615826787L;
	private static final String SPEICHERN = "Speichern";
	// Objeke
	private Document doc;
	private UndoManager undo;
	private File currentFile = null;

	// Assoziationen
	private Steuerung dieSteuerung;

	// Gui
	private JPanel jContentPane = null;
	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	private JLabel jLabel = null;
	private JLabel jLabelStatusMeld = null;
	private JPanel jPanel2 = null;
	// Menu
	private JMenuBar jJMenuBar = null;
	private JMenu fileMenu = null;
	private JMenu editMenu = null;
	private JMenu helpMenu = null;
	private JMenuItem jMenuItemExit = null;
	private JMenuItem jMenuItemOpen = null;
	private JMenuItem jMenuItemDrucken = null;
	private JMenuItem jMenuItemAbout = null;
	private JMenuItem jMenuItemCut = null;
	private JMenuItem jMenuItemCopy = null;
	private JMenuItem jMenuItemPaste = null;
	private JMenuItem jMenuItemSave = null;

	// Symbolleiste
	private JToolBar jJToolBarBar = null;
	private JButton jButtonOp = null;
	private JButton jButtonPrint = null;
	private JButton jButtonPaste = null;

	// Debugging
	private boolean debug;

	// Popup menu
	private JPopupMenu popUpEditor = null;
	private JMenuItem menuItemSelectAll = null;
	private JMenuItem menuItemCut = null;
	private JMenuItem menuItemPaste = null;
	private JMenuItem menuItemCopy = null;
	private JEditorPane jEditorPane = null;
	private JScrollPane jScrollPane = null;
	private JMenuItem jMenuItemSpeichern = null;
	private JButton jButtonZurueck = null;
	private JButton jButtonVor = null;
	private JButton jButtonSave = null;
	private JMenuItem jMenuItemZurueck = null;
	private JMenuItem jMenuItemVor = null;

	/**
	 * This is the default constructor
	 */
	public StandardGui() {
		super();
		initialize();
		setLookAndFeel();
		setPopUpMenu();
		dieSteuerung = new Steuerung(this);
		debug = true;
		undo = new UndoManager();
		undo.setLimit(UNDO_LIMIT);

	}

	/**
	 * Erzeugt ein Popup Menu und aktiviert die Mouseevents.
	 * 
	 * @see processMouseEvent(MouseEvent event)
	 */
	private void setPopUpMenu() {
		popUpEditor = new JPopupMenu();
		popUpEditor.setComponentPopupMenu(popUpEditor);
		menuItemSelectAll = setPopUpMenuItem(popUpEditor, ALLES_AUSWAEHLEN);
		menuItemCut = setPopUpMenuItem(popUpEditor, AUSSCHNEIDEN);
		menuItemCut.setIcon(new ImageIcon(getClass().getResource(PATH_ICON_CUT)));
		menuItemPaste = setPopUpMenuItem(popUpEditor, EINFUEGEN);
		menuItemPaste.setIcon(new ImageIcon(getClass().getResource(PATH_ICON_PASTE)));
		menuItemCopy = setPopUpMenuItem(popUpEditor, KOPIEREN);
		menuItemCopy.setIcon(new ImageIcon(getClass().getResource(PATH_ICON_COPY)));
		jEditorPane.add(popUpEditor);

	}

	/**
	 * Erzeugt die MenuItems eines Popupmenues
	 * 
	 * @param pm
	 * @param txt
	 * @return
	 */
	private JMenuItem setPopUpMenuItem(JPopupMenu pm, String txt) {
		JMenuItem menuItem = new javax.swing.JMenuItem();
		menuItem.setText(txt);
		menuItem.addActionListener(this);
		pm.add(menuItem);
		return menuItem;
	}

	/**
	 * Setzt bei rechter Maustaste das PopupMenu an die Mouseposition
	 */
	public void processMouseEvent(MouseEvent event) {
		// trace("mouse");
		if (event.isPopupTrigger()) {
			popUpEditor.show(event.getComponent(), event.getX(), event.getY());
		}
		super.processMouseEvent(event);
	}

	/**
	 * Setzt das Look & Feel auf den Systemlook
	 */
	private void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (InstantiationException e) {

			e.printStackTrace();
		} catch (IllegalAccessException e) {

			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {

			e.printStackTrace();
		}
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(PATH_ICON_LOGO_SCHWARZ_GIF)));
		this.setJMenuBar(getJJMenuBar());
		this.setSize(572, 531);
		this.setContentPane(getJContentPane());
		this.setTitle(VERSION);
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				System.exit(0);
			}
		});
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane
					.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
			jContentPane.add(getJJToolBarBar(), java.awt.BorderLayout.NORTH);
			jContentPane.add(getJPanel(), java.awt.BorderLayout.SOUTH);
			jContentPane.add(getJScrollPane(), java.awt.BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jJMenuBar
	 * 
	 * @return javax.swing.JMenuBar
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.setFont(new java.awt.Font(FONT_DIALOG, java.awt.Font.PLAIN, 12));
			jJMenuBar.add(getFileMenu());
			jJMenuBar.add(getEditMenu());
			jJMenuBar.add(getHelpMenu());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes jMenu
	 * 
	 * @return javax.swing.JMenu
	 */
	private JMenu getFileMenu() {
		if (fileMenu == null) {
			fileMenu = new JMenu();
			fileMenu.setText(DATEI);
			fileMenu.setToolTipText(DATEIFUNKTIONEN);
			fileMenu.setFont(new java.awt.Font(FONT_DIALOG, java.awt.Font.PLAIN, 12));
			fileMenu.add(getJMenuItemOpen());
			fileMenu.add(getJMenuItemSpeichern());
			fileMenu.add(getSaveMenuItem());
			fileMenu.addSeparator();
			fileMenu.add(getJMenuItemDrucken());
			fileMenu.addSeparator();
			fileMenu.add(getExitMenuItem());
		}
		return fileMenu;
	}

	/**
	 * This method initializes jMenu
	 * 
	 * @return javax.swing.JMenu
	 */
	private JMenu getEditMenu() {
		if (editMenu == null) {
			editMenu = new JMenu();
			editMenu.setText(BEARBEITEN);
			editMenu.setToolTipText(BEARBEITEN_DER_AKTUELLEN_DATEI);
			editMenu.setFont(new java.awt.Font(FONT_DIALOG, java.awt.Font.PLAIN, 12));
			editMenu.add(getCutMenuItem());
			editMenu.add(getCopyMenuItem());
			editMenu.add(getPasteMenuItem());
			editMenu.addSeparator();
			editMenu.add(getJMenuItemZurueck());
			editMenu.add(getJMenuItemVor());

		}
		return editMenu;
	}

	/**
	 * This method initializes jMenu
	 * 
	 * @return javax.swing.JMenu
	 */
	private JMenu getHelpMenu() {
		if (helpMenu == null) {
			helpMenu = new JMenu();
			helpMenu.setText("Hilfe");
			helpMenu.setFont(new java.awt.Font(FONT_DIALOG, java.awt.Font.PLAIN, 12));
			helpMenu.add(getAboutMenuItem());
		}
		return helpMenu;
	}

	/**
	 * This method initializes jMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getExitMenuItem() {
		if (jMenuItemExit == null) {
			jMenuItemExit = new JMenuItem();
			jMenuItemExit.setText("Beenden");
			jMenuItemExit.setIcon(new ImageIcon(getClass().getResource(PATH_ICON_EXIT)));
			jMenuItemExit.setFont(new java.awt.Font(FONT_DIALOG, java.awt.Font.PLAIN, 12));
			jMenuItemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, Event.CTRL_MASK, true));
			jMenuItemExit.addActionListener(this);
		}
		return jMenuItemExit;
	}

	/**
	 * This method initializes jMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getAboutMenuItem() {
		if (jMenuItemAbout == null) {
			jMenuItemAbout = new JMenuItem();
			jMenuItemAbout.setText(UEBER);
			jMenuItemAbout.setFont(new java.awt.Font(FONT_DIALOG, java.awt.Font.PLAIN, 12));
			jMenuItemAbout.addActionListener(this);
		}
		return jMenuItemAbout;
	}

	/**
	 * This method initializes jMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getCutMenuItem() {
		if (jMenuItemCut == null) {
			jMenuItemCut = new JMenuItem();
			jMenuItemCut.setText(AUSSCHNEIDEN);
			jMenuItemCut.setFont(new java.awt.Font(FONT_DIALOG, java.awt.Font.PLAIN, 12));
			jMenuItemCut.setIcon(new ImageIcon(getClass().getResource(PATH_ICON_CUT)));
			jMenuItemCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, Event.CTRL_MASK, true));
			jMenuItemCut.addActionListener(this);
		}
		return jMenuItemCut;
	}

	/**
	 * This method initializes jMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getCopyMenuItem() {
		if (jMenuItemCopy == null) {
			jMenuItemCopy = new JMenuItem();
			jMenuItemCopy.setText(KOPIEREN);
			jMenuItemCopy.setFont(new java.awt.Font(FONT_DIALOG, java.awt.Font.PLAIN, 12));
			jMenuItemCopy.setIcon(new ImageIcon(getClass().getResource(PATH_ICON_COPY)));
			jMenuItemCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK, true));
			jMenuItemCopy.addActionListener(this);
		}
		return jMenuItemCopy;
	}

	/**
	 * This method initializes jMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getPasteMenuItem() {
		if (jMenuItemPaste == null) {
			jMenuItemPaste = new JMenuItem();
			jMenuItemPaste.setText(EINFUEGEN);
			jMenuItemPaste.setFont(new java.awt.Font(FONT_DIALOG, java.awt.Font.PLAIN, 12));
			jMenuItemPaste.setIcon(new ImageIcon(getClass().getResource(PATH_ICON_PASTE)));
			jMenuItemPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK, true));
			jMenuItemPaste.addActionListener(this);
		}
		return jMenuItemPaste;
	}

	/**
	 * This method initializes jMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getSaveMenuItem() {
		if (jMenuItemSave == null) {
			jMenuItemSave = new JMenuItem();
			jMenuItemSave.setText(SPEICHERN_UNTER);
			jMenuItemSave.setFont(new java.awt.Font(FONT_DIALOG, java.awt.Font.PLAIN, 12));
			jMenuItemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, Event.CTRL_MASK, true));
			jMenuItemSave.addActionListener(this);
		}
		return jMenuItemSave;
	}

	/**
	 * This method initializes jJToolBarBar
	 * 
	 * @return javax.swing.JToolBar
	 */
	private JToolBar getJJToolBarBar() {
		if (jJToolBarBar == null) {
			jJToolBarBar = new JToolBar();
			jJToolBarBar.setFloatable(false);
			jJToolBarBar.add(getJButtonOp());
			jJToolBarBar.add(getJButtonSave());
			jJToolBarBar.add(getJButtonPrint());
			jJToolBarBar.add(getJButtonPaste());
			jJToolBarBar.add(getJButtonZurueck());
			jJToolBarBar.add(getJButtonVor());
		}
		return jJToolBarBar;
	}

	private JButton getJButtonOp() {
		if (jButtonOp == null) {
			jButtonOp = new JButton();
			jButtonOp.setIcon(new ImageIcon(getClass().getResource(PATH_ICON_FOLDER)));
			jButtonOp.setToolTipText(DATEI_OEFFNEN);
			jButtonOp.addActionListener(this);
		}
		return jButtonOp;
	}

	private JButton getJButtonPrint() {
		if (jButtonPrint == null) {
			jButtonPrint = new JButton();
			jButtonPrint.setIcon(new ImageIcon(getClass().getResource(PATH_ICON_E_PRINT)));
			jButtonPrint.setToolTipText(DATEI_DRUCKEN);
			jButtonPrint.addActionListener(this);
		}
		return jButtonPrint;
	}

	private JButton getJButtonPaste() {
		if (jButtonPaste == null) {
			jButtonPaste = new JButton();
			jButtonPaste.setIcon(new ImageIcon(getClass().getResource(PATH_ICON_PASTE)));
			jButtonPaste.setToolTipText(INHALT_VON_ZWISCHENABLAGE_EINFUEGEN);
			jButtonPaste.addActionListener(this);
		}
		return jButtonPaste;
	}

	private JMenuItem getJMenuItemOpen() {
		if (jMenuItemOpen == null) {
			jMenuItemOpen = new JMenuItem();
			jMenuItemOpen.setText(DATEI_OEFFNEN);
			jMenuItemOpen.setIcon(new ImageIcon(getClass().getResource(PATH_ICON_FOLDER)));
			jMenuItemOpen.setFont(new java.awt.Font(FONT_DIALOG, java.awt.Font.PLAIN, 12));
			jMenuItemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Event.CTRL_MASK, true));
			jMenuItemOpen.addActionListener(this);
		}
		return jMenuItemOpen;
	}

	/**
	 * behandelt alle Standardereignisse
	 */
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(jButtonOp) || o.equals(jMenuItemOpen)) {
			trace("open");
			setStatus(DATEI_OEFFNEN);
			dieSteuerung.openFile();
			setStatus(READY);

		} else if (o.equals(jButtonPaste) || o.equals(jMenuItemPaste) || o.equals(menuItemPaste)) {
			trace(EINFUEGEN);
			setStatus(EINFUEGEN);
			jEditorPane.paste();
			setStatus(READY);
		} else if (o.equals(jButtonPrint) || e.getSource().equals(jMenuItemDrucken)) {
			trace("print");
			setStatus(DATEI_DRUCKEN);
			dieSteuerung.print();

		}

		else if (o.equals(jMenuItemCopy) || o.equals(menuItemCopy) || o.equals(menuItemCopy)) {
			trace(KOPIEREN);
			setStatus(KOPIEREN);
			jEditorPane.copy();
			setStatus(READY);
		} else if (o.equals(jMenuItemCut) || o.equals(menuItemCut) || o.equals(menuItemCut)) {
			trace(AUSSCHNEIDEN);
			setStatus(AUSSCHNEIDEN);
			jEditorPane.cut();
			setStatus(READY);
		} else if (o.equals(jMenuItemSave) || o.equals(jButtonSave)) {
			trace(SPEICHERN_UNTER);
			setStatus(SPEICHERN_UNTER);
			dieSteuerung.saveAsNewFile();
			setStatus(READY);
		} else if (o.equals(jMenuItemSpeichern)) {
			trace("save");
			setStatus(SPEICHERN);
			dieSteuerung.saveFile();
			setStatus(READY);
		}

		else if (o.equals(jMenuItemExit)) {
			trace("exit");
			System.exit(0);

		} else if (o.equals(jMenuItemAbout)) {
			trace("about");
			About ab = new About(this);
			ab.setVisible(true);
			setStatus("About...");

		} else if (o.equals(menuItemSelectAll)) {
			trace("alles markieren");
			setStatus("Alles auswählen...");
			jEditorPane.selectAll();
		} else if (o.equals(jButtonVor) || o.equals(jMenuItemVor)) {
			System.out.println("redo");
			setStatus("wiederhole...");
			if (undo.canRedo()) {
				undo.redo();

			}
		} else if (o.equals(jButtonZurueck) || o.equals(jMenuItemZurueck)) {
			System.out.println("undo");
			setStatus("rückgängig");
			if (undo.canUndo()) {
				undo.undo();

			}
		} else {

		}

	}

	/**
	 * This method initializes jPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new BoxLayout(getJPanel(), BoxLayout.X_AXIS));
			jPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
			jPanel.add(getJPanel1(), null);
			jPanel.add(getJPanel2(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jPanel1
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(java.awt.FlowLayout.LEFT);
			jLabelStatusMeld = new JLabel();
			jLabelStatusMeld.setText("");
			jLabelStatusMeld.setFont(new java.awt.Font(FONT_DIALOG, java.awt.Font.ITALIC, 10));
			jLabel = new JLabel();
			jLabel.setText("Status:");
			jLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
			jLabel.setFont(new java.awt.Font(FONT_DIALOG, java.awt.Font.BOLD, 10));
			jLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
			jPanel1 = new JPanel();
			jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
			jPanel1.setLayout(flowLayout);
			jPanel1.add(jLabel, null);
			jPanel1.add(jLabelStatusMeld, null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jPanel2
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setLayout(new FlowLayout());
			jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
			jPanel2.setPreferredSize(new java.awt.Dimension(30, 28));
		}
		return jPanel2;
	}

	/**
	 * Zeigt debug Meldungen an, wenn die Variable debug auf true gesetzt ist
	 * 
	 * @param str
	 * @serialField debug
	 */
	private void trace(String str) {
		if (debug) {
			System.out.println(str);
		}
	}

	/**
	 * This method initializes jMenuItemDrucken
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItemDrucken() {
		if (jMenuItemDrucken == null) {
			jMenuItemDrucken = new JMenuItem();
			jMenuItemDrucken.setText("Drucken");
			jMenuItemDrucken.setFont(new java.awt.Font(FONT_DIALOG, java.awt.Font.PLAIN, 12));
			jMenuItemDrucken.setIcon(new ImageIcon(getClass().getResource(PATH_ICON_E_PRINT)));
			jMenuItemDrucken.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, Event.CTRL_MASK, true));
			jMenuItemDrucken.addActionListener(this);

		}
		return jMenuItemDrucken;
	}

	public void setStatus(String meldung) {
		jLabelStatusMeld.setText(meldung);
	}

	/**
	 * This method initializes jEditorPane
	 * 
	 * @return javax.swing.JEditorPane
	 */
	private JEditorPane getJEditorPane() {
		if (jEditorPane == null) {
			jEditorPane = new JEditorPane();
			doc = jEditorPane.getDocument();
			doc.addDocumentListener(this);

			doc.addUndoableEditListener(new UndoableEditListener() {
				public void undoableEditHappened(UndoableEditEvent evt) {
					undo.addEdit(evt.getEdit());

				}
			});

			jEditorPane.addMouseListener(new java.awt.event.MouseAdapter() {

				@Override
				public void mousePressed(java.awt.event.MouseEvent evt) {
					processMouseEvent(evt);
				}

				@Override
				public void mouseReleased(java.awt.event.MouseEvent evt) {
					processMouseEvent(evt);
				}
			});
		}
		return jEditorPane;
	}

	/**
	 * This method initializes jScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJEditorPane());
		}
		return jScrollPane;
	}

	public File getCurrentFile() {
		return currentFile;
	}

	public void setCurrentFile(File currentFile) {
		this.currentFile = currentFile;
	}

	public String getEditorText() {
		return jEditorPane.getText();

	}

	public Printable getEditorPrintable() {
		return jEditorPane.getPrintable(null, null);

	}

	public void setEditorText(String txt) {
		jEditorPane.setText(txt);
	}

	/**
	 * This method initializes jMenuItemSpeichern
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItemSpeichern() {
		if (jMenuItemSpeichern == null) {
			jMenuItemSpeichern = new JMenuItem();
			jMenuItemSpeichern.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK, true));
			
			
			// old jMenuItemSpeichern.setIcon(new
			// ImageIcon(StandardGui.class.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
			
			ImageIcon iconSave = (ImageIcon) UIManager.getIcon("FileView.floppyDriveIcon");
			jMenuItemSpeichern.setIcon(iconSave);
			jMenuItemSpeichern.setFont(new java.awt.Font(FONT_DIALOG, java.awt.Font.PLAIN, 12));
			jMenuItemSpeichern.setText("Speichern");
			jMenuItemSpeichern.addActionListener(this);
		}
		return jMenuItemSpeichern;
	}

	public void insertUpdate(DocumentEvent e) {
		System.out.println("insert");

	}

	public void removeUpdate(DocumentEvent e) {
		System.out.println("remove");

	}

	public void changedUpdate(DocumentEvent e) {
		System.out.println("change");

	}

	/**
	 * This method initializes jButtonZurueck
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonZurueck() {
		if (jButtonZurueck == null) {
			jButtonZurueck = new JButton();
			jButtonZurueck.setText("");
			jButtonZurueck.setToolTipText("rueckg‰ngig");
			jButtonZurueck.setIcon(new ImageIcon(getClass().getResource("/icon/icon (4).gif")));
			jButtonZurueck.addActionListener(this);
		}
		return jButtonZurueck;
	}

	/**
	 * This method initializes jButtonVor
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonVor() {
		if (jButtonVor == null) {
			jButtonVor = new JButton();
			jButtonVor.setText("");
			jButtonVor.setToolTipText("wiederholen");
			jButtonVor.setIcon(new ImageIcon(getClass().getResource("/icon/icon (5).gif")));
			jButtonVor.addActionListener(this);
		}
		return jButtonVor;
	}

	/**
	 * This method initializes jButtonSave
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonSave() {
		if (jButtonSave == null) {
			jButtonSave = new JButton();
			ImageIcon iconSave = (ImageIcon) UIManager.getIcon("FileView.floppyDriveIcon");
			jButtonSave.setIcon(iconSave);
			jButtonSave.setToolTipText("Datei speichern");
			jButtonSave.setText("");
			jButtonSave.addActionListener(this);
		}
		return jButtonSave;
	}

	/**
	 * This method initializes jMenuItemZurueck
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItemZurueck() {
		if (jMenuItemZurueck == null) {
			jMenuItemZurueck = new JMenuItem();
			jMenuItemZurueck.setIcon(new ImageIcon(getClass().getResource("/icon/icon (4).gif")));
			jMenuItemZurueck.setText("r\u00FCckg\u00E4ngig");
			jMenuItemZurueck.setFont(new java.awt.Font(FONT_DIALOG, java.awt.Font.PLAIN, 12));
			jMenuItemZurueck.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Event.CTRL_MASK, true));
			jMenuItemZurueck.addActionListener(this);
		}
		return jMenuItemZurueck;
	}

	/**
	 * This method initializes jMenuItemVor
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItemVor() {
		if (jMenuItemVor == null) {
			jMenuItemVor = new JMenuItem();
			jMenuItemVor.setIcon(new ImageIcon(getClass().getResource("/icon/icon (5).gif")));
			jMenuItemVor.setText("wiederholen");
			jMenuItemVor.setToolTipText("");
			jMenuItemVor.setFont(new java.awt.Font(FONT_DIALOG, java.awt.Font.PLAIN, 12));
			jMenuItemVor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, Event.CTRL_MASK, true));
			jMenuItemVor.addActionListener(this);
		}
		return jMenuItemVor;
	}

} // @jve:decl-index=0:visual-constraint="10,10"
