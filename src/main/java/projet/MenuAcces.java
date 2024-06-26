package projet;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.jna.platform.win32.WinDef.POINT;

import utils.MessageAide;

public class MenuAcces extends JFrame implements ActionListener,MouseListener{

	/**
	 * Classe graphique pour la fenetre du menu
	 */
	private static final long serialVersionUID = 1L;
	//
	/**
	 * attributs de classe
	 */
	BorderLayout bl;
	GridLayout gl1,gl2;
	JButton retour,goals,results,shoot;
	JLabel jl1,jl2,jl3,jl4,label;
	JPanel JP1,JP2;
	public MessageAide ma;
	//
	/**
	 * constructeur de la classe graphique
	 */
	public MenuAcces() {
		bl = new BorderLayout();
		gl1 = new GridLayout(4,1);
		gl2 = new GridLayout(4,1);
		retour = new JButton("Retour");
		retour.addActionListener(this);
		goals = new JButton("Goals");
		goals.addActionListener(this);
		results = new JButton("Resultats");
		results.addActionListener(this);
		shoot = new JButton("Tirs aux buts");
		shoot.addActionListener(this);
		JP1 = new JPanel();
		JP1.setLayout(gl1);
		JP1.add(goals);
		JP1.add(results);
		JP1.add(shoot);
		JP1.add(retour);
		//icone des la fichiers de resource -> src/main/resources
		jl1 = new JLabel(new ImageIcon(getClass().getResource("/interro2.png")));
		jl1.addMouseListener(this);
		jl2 = new JLabel(new ImageIcon(getClass().getResource("/interro2.png")));
		jl2.addMouseListener(this);
		jl3 = new JLabel(new ImageIcon(getClass().getResource("/interro2.png")));
		jl3.addMouseListener(this);
		jl4 = new JLabel(new ImageIcon(getClass().getResource("/interro2.png")));
		jl4.addMouseListener(this);
		JP2 = new JPanel();
		JP2.setLayout(gl2);
		JP2.add(jl1);
		JP2.add(jl2);
		JP2.add(jl3);
		JP2.add(jl4);
		label = new JLabel("Menu");
		label.setHorizontalAlignment((int)CENTER_ALIGNMENT);
		this.setLayout(bl);
		this.add("North",label);
		this.add("Center",JP1);
		this.add("East",JP2);
		this.setLocation(150,150);
		this.setSize(250,200);
		this.setVisible(true);
	}
	//
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	/**
	 * methode de gestion de l'evenement du pointeur de la souris
	 * cas d'entrée dans un composant JLabel
	 * Affiche une fenetre avec une aide textuelle.
	 */
	@Override
	public void mouseEntered(MouseEvent me) {
		// TODO Auto-generated method stub
		Object obj = me.getSource();
		if(obj==jl1) {
			Point p = this.jl1.getLocationOnScreen();
			ma = new MessageAide();
			ma.xma = p.x+25;
			ma.yma = p.y+5;
			ma.setLocation(ma.xma,ma.yma);
			ma.txta.setText("Pour accéder aux butteurs.");
			ma.setVisible(true);
		}
		else if(obj==jl2) {
			Point p = this.jl2.getLocationOnScreen();
			ma = new MessageAide();
			ma.xma = p.x+25;
			ma.yma = p.y+5;
			ma.setLocation(ma.xma,ma.yma);
			ma.txta.setText("Pour accéder aux résultats.");
			ma.setVisible(true);
		}
		else if(obj==jl3) {
			Point p = this.jl3.getLocationOnScreen();
			ma = new MessageAide();
			ma.xma = p.x+25;
			ma.yma = p.y+5;
			ma.setLocation(ma.xma,ma.yma);
			ma.txta.setText("Pour accéder aux tirs au but.");
			ma.setVisible(true);
		}
		else if(obj==jl4) {
			Point p = this.jl4.getLocationOnScreen();
			ma = new MessageAide();
			ma.xma = p.x+25;
			ma.yma = p.y+5;
			ma.setLocation(ma.xma,ma.yma);
			ma.txta.setText("Pour retourner à l'accueil.");
			ma.setVisible(true);
		}
	}//fin mouseEntered()
	/**
	 * methode de gestion de l'evenement du pointeur de la souris
	 * cas de sortie d'un composant JLabel
	 * masque le fenetre avec l'aide textuelle
	 */
	@Override
	public void mouseExited(MouseEvent me) {
		// TODO Auto-generated method stub
		Object obj = me.getSource();
		if(obj==jl1) {
			ma.setVisible(false);
			ma.dispose();
		}
		else if(obj==jl2) {
			ma.setVisible(false);
			ma.dispose();
		}
		else if(obj==jl3) {
			ma.setVisible(false);
			ma.dispose();
		}
		else if(obj==jl4) {
			ma.setVisible(false);
			ma.dispose();
		}
	}//fin mouseExited()
	/**
	 * methode de gestion des actions des boutons.
	 * retour pour revenir à l'accueil
	 * scores pour accéder aux opération sur la table goalscorers
	 * resultats pour accéde aux opérations sur la table results
	 * shootsouts pour accéder aux opération sur la table shootouts
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		Object obj = ae.getSource();
		if(obj==retour) {
			this.setVisible(false);
			this.dispose();
			Accueil ACC = new Accueil();
			ACC.setVisible(true);	
		}
		else if(obj==goals) {
			this.setVisible(false);
			this.dispose();
			MenuScore MSC = new MenuScore();
			MSC.setVisible(true);
		}
		else if(obj==results) {
			this.setVisible(false);
			this.dispose();
			MenuResults MRE = new MenuResults();
			MRE.setVisible(true);
		}
		else if(obj==shoot) {
			this.setVisible(false);
			this.dispose();
			MenuShoot MSH = new MenuShoot();
			MSH.setVisible(true);
		}
	}//fin actionListener()
}
