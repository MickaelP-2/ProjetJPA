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

import utils.MessageAide;

public class MenuResults extends JFrame implements ActionListener,MouseListener{

	/**
	 * Classe graphique pour le menu des opérations sur les scores et buteurs
	 */
	private static final long serialVersionUID = 1L;
	//
	/**
	 * Attributs de la classe.
	 */
	BorderLayout bl;
	GridLayout gl1,gl2;
	JLabel label,jl1,jl2,jl3,jl4,jl5;
	JPanel JP1,JP2;
	JButton retour,creer,rechercher,modifier,supprimer;
	MessageAide ma;
	//
	/**
	 * Constructeur de la classe de menu.
	 * Opérations CRUD et retour au menu précédent.
	 */
	public MenuResults() {
		bl = new BorderLayout();
		gl1 = new GridLayout(5,1);
		gl2 = new GridLayout(5,1);
		label = new JLabel("Menu pour les résultats des matchs.");
		jl1 = new JLabel(new ImageIcon(getClass().getResource("/interro2.png")));
		jl1.addMouseListener(this);
		jl2 = new JLabel(new ImageIcon(getClass().getResource("/interro2.png")));
		jl2.addMouseListener(this);
		jl3 = new JLabel(new ImageIcon(getClass().getResource("/interro2.png")));
		jl3.addMouseListener(this);
		jl4 = new JLabel(new ImageIcon(getClass().getResource("/interro2.png")));
		jl4.addMouseListener(this);
		jl5 = new JLabel(new ImageIcon(getClass().getResource("/interro2.png")));
		jl5.addMouseListener(this);
		retour = new JButton("Retour");
		retour.addActionListener(this);
		creer = new JButton("Créer");
		creer.addActionListener(this);
		rechercher = new JButton("Rechercher");
		rechercher.addActionListener(this);
		modifier = new JButton("Modifier");
		modifier.addActionListener(this);
		supprimer = new JButton("Supprimer");
		supprimer.addActionListener(this);
		JP1 = new JPanel();
		JP1.setLayout(gl1);
		JP1.add(creer);
		JP1.add(rechercher);
		JP1.add(modifier);
		JP1.add(supprimer);
		JP1.add(retour);
		JP2 = new JPanel();
		JP2.setLayout(gl2);
		JP2.add(jl1);
		JP2.add(jl2);
		JP2.add(jl3);
		JP2.add(jl4);
		JP2.add(jl5);
		this.setLayout(bl);
		this.add("North",label);
		this.add("Center",JP1);
		this.add("East",JP2);
		this.setLocation(150,150);
		this.setSize(250,250);
		this.setVisible(true);
	}
	//
	/**
	 * méthode main pour tester l'affichage de la fenetre(interface graphique)
	 * ainsi que les composants boutons et icone pour signaler l'aide.
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MenuResults MR = new MenuResults();
		MR.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	/**
	 * Méthode pour associer l'affichage d'une fenetre d'aide quand le pointeur de
	 * la souris survole un composant, afin d'afficher un message d'aide. 
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
			ma.txta.setText("Pour créer les résultats d'un match.");
			ma.setVisible(true);
		}
		else if(obj==jl2) {
			Point p = this.jl2.getLocationOnScreen();
			ma = new MessageAide();
			ma.xma = p.x+25;
			ma.yma = p.y+5;
			ma.setLocation(ma.xma,ma.yma);
			ma.txta.setText("Pour rechercher les résultats d'un match.");
			ma.setVisible(true);
		}
		else if(obj==jl3) {
			Point p = this.jl3.getLocationOnScreen();
			ma = new MessageAide();
			ma.xma = p.x+25;
			ma.yma = p.y+5;
			ma.setLocation(ma.xma,ma.yma);
			ma.txta.setText("Pour modifier les résultats d'un match.");
			ma.setVisible(true);
		}
		else if(obj==jl4) {
			Point p = this.jl4.getLocationOnScreen();
			ma = new MessageAide();
			ma.xma = p.x+25;
			ma.yma = p.y+5;
			ma.setLocation(ma.xma,ma.yma);
			ma.txta.setText("Pour supprimer les résultats d'un match.");
			ma.setVisible(true);
		}
		else if(obj==jl5) {
			Point p = this.jl5.getLocationOnScreen();
			ma = new MessageAide();
			ma.xma = p.x+25;
			ma.yma = p.y+5;
			ma.setLocation(ma.xma,ma.yma);
			ma.txta.setText("Pour retourner au menu précédent.");
			ma.setVisible(true);
		}
	}
	/**
	 * méthode pour les cas de pointeur de souris sortant d'un composant.
	 * restitue la mémoire utilisée et masque la fenetre.
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
		else if(obj==jl5) {
			ma.setVisible(false);
			ma.dispose();
		}
	}
	/**
	 * methode pour associer des actions aux boutons.
	 * écouteur d'actions.
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		Object obj = ae.getSource();
		if(obj==creer) {
			this.setVisible(false);
			this.dispose();
			CreerResultats CR = new CreerResultats();
			CR.setVisible(true);
		}
		else if(obj==rechercher) {
			this.setVisible(false);
			this.dispose();
			RechercherResultats RR = new RechercherResultats();
			RR.setVisible(true);
		}
		else if(obj==modifier) {
			this.setVisible(false);
			this.dispose();
			ModifierResultats MR = new ModifierResultats();
			MR.setVisible(true);
		}
		else if(obj==supprimer) {
			this.setVisible(false);
			this.dispose();
			SupprimerResultats SR = new SupprimerResultats();
			SR.setVisible(true);
		}
		else if(obj == retour) {
			this.setVisible(false);
			this.dispose();
			MenuAcces MA = new MenuAcces();
			MA.setVisible(true);
		}
	}

}
