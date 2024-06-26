package utils;
import java.awt.BorderLayout;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class FenetreAffichage extends JFrame implements ActionListener{
	/**
	 * Interface pour afficher le contenu d'un fichier en mode texte
	 * /////////////////////////////////
	 * //Copyrigth © Mickael Penon	   //
	 * // penon.mickael@orange.fr     //
	 * // date:10/07/2017			   //
	 * /////////////////////////////////
	 */
	private static final long serialVersionUID = 1L;
	JLabel label1;//Pour le titre
	public JTextArea text;
	JButton retour;
	BorderLayout bl;
	JScrollPane jsp;
	int xpr;
	int ypr;
	////////////////////////////
	public FenetreAffichage(){
		label1 = new JLabel("Fenetre d'affichage!");
		label1.setHorizontalAlignment((int)CENTER_ALIGNMENT);
		text = new JTextArea(10,25);
		text.setSize(10,25);
		jsp = new JScrollPane(text);
		jsp.setBounds(10,50,350,150);
		retour = new JButton("Fermer");
		retour.addActionListener(this);
		bl = new BorderLayout();
		this.setLayout(bl);
		this.add("North",label1);
		this.add("Center",jsp);
		this.add("South",retour);
		this.setSize(290,230);
	}//fin FenetreAF()
	//////////////////////
	
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if(obj==retour){
			this.setVisible(false);
			this.dispose();
		}//fin if
	}//fin public void actionPerformed()
}//fin classe AF(