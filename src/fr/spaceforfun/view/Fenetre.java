package fr.spaceforfun.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;






import javax.swing.text.AttributeSet;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import fr.spaceforfun.controller.Controller;
import fr.spaceforfun.controller.StoppeListener;
import fr.spaceforfun.controller.SurveillanceListener;
import fr.spaceforfun.model.Editevt;
import fr.spaceforfun.model.Panneau;
import fr.spaceforfun.model.Surveillance;

/**
 * Classe gérant l'apparence de la fenêtre
 * @author Sébastien Bernard
 *
 */
public class Fenetre extends JFrame{


	private int findLastNonWordChar (String text, int index) {
		while (--index >= 0) {
			if (String.valueOf(text.charAt(index)).matches("\\W")) {
				break;
			}
		}
		return index;
	}

	private int findFirstNonWordChar (String text, int index) {
		while (index < text.length()) {
			if (String.valueOf(text.charAt(index)).matches("\\W")) {
				break;
			}
			index++;
		}
		return index;
	}

	private int findFirstcomment (String text, int index) 
	{
		while (index < text.length()) {
			if (String.valueOf(text.charAt(index)).matches("\\W")) {
				break;
			}
			index++;
		}
		return index;
	}

	private int findLastComment (String text, int index)
	{
		while (--index >= 0) {
			if (String.valueOf(text.charAt(index)).matches("\\W")) {
				break;
			}
		}
		return index;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	final JFileChooser fc = new JFileChooser();

	private Panneau support_bouton = new Panneau(),
			affiche        = new Panneau();
	private JLabel instructions = new JLabel();
	private JPanel container       = new JPanel();
	private JPanel modif		   = new JPanel();
	private JPanel evt			= new JPanel();
	private Panneau encours         = new Panneau();
	private JLabel label           = new JLabel();
	private static JLabel modifTexte     = new JLabel();

	private JButton dem           = new JButton("Démarer la surveillance");
	private JButton stop          = new JButton("Arreter la surveillance"); 
	private JButton app			= new JButton("Appliquer les changements");



	private JMenuBar menuBar = new JMenuBar();
	private JMenu fichier    = new JMenu("Fichier"),
			edition    = new JMenu("Edition"),
			aide       = new JMenu("Aide");

	private JMenuItem ouvrir      = new JMenuItem("choisir un fichier"),
			ouvrep      = new JMenuItem("choisir un repertoire"),
			demarer     = new JMenuItem("demarer la surveillance"),
			stopper     = new JMenuItem("stopper la surveillance"),
			quitter     = new JMenuItem("quitter"),
			changer     = new JMenuItem("choisir un son d'alerte (.wav)"),
			chooseevt	= new JMenuItem("Sélectionner un fichier evt"),
			aProposItem = new JMenuItem("A Propos de Surveillance"),
			maj			= new JMenuItem("Mise à jour");

	private JTabbedPane onglet = new JTabbedPane(); 


	/********************************************
	 * 
	 */
	final StyleContext cont = StyleContext.getDefaultStyleContext();
	final AttributeSet attr = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.RED);
	final AttributeSet attrBlack = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.BLACK);
	@SuppressWarnings("serial")
	DefaultStyledDocument doc = new DefaultStyledDocument() {
		@Override
		public void insertString (int offset, String str, AttributeSet a) throws BadLocationException {
			super.insertString(offset, str, a);

			String text = getText(0, getLength());
			//int before = findLastNonWordChar(text, offset);
			int before = findLastComment(text, offset);
			if (before < 0) before = 0;
			//int after = findFirstNonWordChar(text, offset + str.length());
			//int after = findFirstcomment(text, offset + str.length());
			//int before = 0;
			int after = offset + str.length();
			int wordL = before;
			int wordR = before;

			while (wordR <= after) {
				if (wordR == after || String.valueOf(text.charAt(wordR)).matches("\n")) {
					//if (text.substring(wordL, wordR).matches("(\\W)*(private|public|protected)")) 
					if (text.substring(wordL, wordR).matches(".*(;.*)"))
					{
						setCharacterAttributes(wordL, wordR - wordL, attr, false);
					
					}
					else
						setCharacterAttributes(wordL, wordR - wordL, attrBlack, false);
					wordL = wordR;
				}
				wordR++;
			}
		}

		@Override
		public void remove (int offs, int len) throws BadLocationException {
			super.remove(offs, len);

			String text = getText(0, getLength());
			int before = findLastNonWordChar(text, offs);
			if (before < 0) before = 0;
			int after = findFirstNonWordChar(text, offs);
			//if (text.substring(before, after).matches("(\\W)*(private|public|protected)")) {
			if (text.substring(before, after).matches("(\\W)")) {
				setCharacterAttributes(before, after - before, attr, false);
			} else {
				setCharacterAttributes(before, after - before, attrBlack, false);
			}
		}
	};


	/********************************************
	 * 
	 */

	private JTextPane contenuevt = new JTextPane(doc);

	Surveillance recup;
	Editevt edevt = new Editevt(this);

	public Editevt getEdevt()
	{
		return edevt;
	}

	public JTabbedPane getOnglet()
	{
		return onglet;
	}

	public Panneau getEncours()
	{
		return encours;
	}
	public Surveillance getRecup()
	{
		return recup;
	}

	/**
	 * Constructeur qui crée la fenêtre
	 */
	public Fenetre(){

		this.setTitle("surveillance"); //titre de la fenetre
		this.setSize(500,600); //taille de la fenetre
		this.setResizable(false);
		this.setLocationRelativeTo(null); //position au centre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //la fenetre se ferme quand on clique sur fermer

		recup = new Surveillance();
		label.setText("aucune surveillance");

		/*affiche le dossier surveillé*/ 
		affiche.setLayout(new GridBagLayout());
		affiche.setColor(Color.GRAY);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		affiche.add(label, gbc);


		modifTexte.setOpaque(true);
		modifTexte.setBackground(new Color(198,198,198)); //gris clair

		recup.getPan().setLayout(new BorderLayout());		
		recup.getPan().setColor(Color.WHITE);
		recup.getPan().add(modifTexte, BorderLayout.SOUTH);

		JScrollPane scroll = new JScrollPane(recup.getPan(),
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setPreferredSize(new Dimension(50,300));

		encours.setColor(Color.WHITE);
		encours.add(instructions);

		modif.setLayout(new BorderLayout());
		modif.setBorder(new LineBorder(Color.black, 3));
		modif.add(encours,BorderLayout.CENTER);
		modif.add(scroll, BorderLayout.SOUTH);


		support_bouton.setLayout(new GridBagLayout());
		support_bouton.setColor(Color.gray);


		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.ipadx = 2; //espacement Horizontal
		support_bouton.add(dem, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;;
		gbc.gridheight = 1;
		support_bouton.add(stop, gbc);




		container.setLayout(new BorderLayout());
		//container.add(affiche, BorderLayout.NORTH);
		//container.add(modif, BorderLayout.CENTER);
		//container.add(support_bouton, BorderLayout.SOUTH);
		container.add(onglet, BorderLayout.CENTER);
		JPanel surv = new JPanel();
		surv.setLayout(new BorderLayout());
		surv.add(affiche, BorderLayout.NORTH);
		surv.add(modif, BorderLayout.CENTER);
		surv.add(support_bouton, BorderLayout.SOUTH);
		onglet.addTab("surveillance", null, surv, null);

		evt.setLayout(new BorderLayout());

		JScrollPane scrollevt = new JScrollPane(contenuevt);
		evt.add(scrollevt, BorderLayout.CENTER);
		app.setEnabled(false);
		evt.add(app, BorderLayout.SOUTH);
		onglet.addTab("evt", null, evt, null);

		this.setContentPane(container);
		this.initMenu();

		this.setVisible(true);
	}

	public JButton getApp() {
		return app;
	}

	/**
	 * Initialise les menus. Crée les racourcis clavier et ajoute les listener pour répondre aux clics
	 */
	private void initMenu(){
		ouvrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
		demarer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK));
		stopper.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		ouvrep.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));

		//initialisation des menus
		Controller ctrl = new Controller(this);
		ouvrir.setActionCommand("ouvrir");
		ouvrir.addActionListener(ctrl);
		fichier.add(ouvrir);

		ouvrep.setActionCommand("ouvrep");
		ouvrep.addActionListener(ctrl);

		fichier.add(ouvrep);
		fichier.add(demarer);
		fichier.add(stopper);
		quitter.setActionCommand("quitter");
		quitter.addActionListener(ctrl);
		fichier.addSeparator();
		fichier.add(quitter);

		//On crée un nouvel écouteur
		SurveillanceListener sl = new SurveillanceListener(this);
		demarer.addActionListener(sl);
		dem.addActionListener(sl);

		StoppeListener stopl = new StoppeListener(this);
		stopper.addActionListener(stopl);
		stop.addActionListener(stopl);


		//menu Edition
		changer.setActionCommand("changer");
		changer.addActionListener(ctrl);
		edition.add(changer);

		chooseevt.setActionCommand("chooseevt");
		chooseevt.addActionListener(ctrl);
		edition.add(chooseevt);

		//menu Aide

		//Ajout de ce que doit faire le "?"
		aProposItem.setActionCommand("aPropos");
		aProposItem.addActionListener(ctrl);
		aide.add(aProposItem);
		
		maj.setActionCommand("maj");
		maj.addActionListener(ctrl);
		aide.add(maj);

		app.setActionCommand("app");
		app.addActionListener(ctrl);

		//L'ordre d'ajout va determiner l'ordre d'apparition dans le menu de gauche a droite
		//Le premier ajouté sera tout a gauche de la barre de menu et inversement pour le dernier

		fichier.setMnemonic('F');
		menuBar.add(fichier);
		edition.setMnemonic('E');
		menuBar.add(edition);
		aide.setMnemonic('A');
		this.menuBar.add(aide);

		this.setJMenuBar(menuBar);
	}

	public JTextPane getContenuevt() {
		return contenuevt;
	}
	/**
	 * Getter et Setter
	 * @author Sébastien Bernard
	 *
	 */
	public JLabel getModifTexte() {
		return modifTexte;
	}

	public static void setModifTexte(String s) {
		modifTexte.setText(s);
	}

	public JLabel getLabel()
	{
		return label;
	}

	public Panneau getAffiche()
	{
		return affiche;
	}
}

