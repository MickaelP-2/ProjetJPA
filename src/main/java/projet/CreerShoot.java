package projet;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import javax.swing.ImageIcon;
import utils.MessageAide;

public class CreerShoot extends JFrame implements ActionListener,MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*	attributs: date, homeTeam, awayTeam, winner, firstShoot
	 * table = shootouts
	 * private int id;
	private LocalDate date;
	private String homeTeam;
	private String awayTeam;
	private String winner;
	private String firstShooter;
	 */
	//
	BorderLayout bl;
	GridLayout gl1,gl2,gl3;
	JPanel JP1,JP2,JP3,JP4;
	JButton creer,retour,effacer;
	JLabel label,lab1,lab2,lab3,lab4,lab5,lab6,jl1,jl2,jl3,jl4,jl5,jl6;
	JTextField txtf1,txtf2,txtf3,txtf4,txtf5,txtf6;
	Shootout SH;
	MessageAide ma;
	//
	/**
	 * consructeur dela classe graphique.
	 */
	public CreerShoot() {
		bl = new BorderLayout();
		gl1 = new GridLayout(6,1);
		gl2 = new GridLayout(6,1);
		gl3 = new GridLayout(6,1);
		JP1 = new JPanel();
		JP2 = new JPanel();
		JP3 = new JPanel();
		JP4 = new JPanel();
		label = new JLabel("Création de tirs aux buts.");
		label.setHorizontalAlignment((int)CENTER_ALIGNMENT);
		lab1 = new JLabel(" Date: ");
		lab2 = new JLabel(" Equipe home: ");
		lab3 = new JLabel(" Equipe away:");
		lab4 = new JLabel(" Vainqueur: ");
		lab5 = new JLabel(" Premier tir: ");
		lab6 = new JLabel("");
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
		jl6 = new JLabel(new ImageIcon(getClass().getResource("/interro2.png")));
		jl6.addMouseListener(this);
		retour = new JButton("Retour");
		retour.addActionListener(this);
		effacer = new JButton("Effacer");
		effacer.addActionListener(this);
		creer = new JButton("Créer");
		creer.addActionListener(this);
		txtf1 = new JTextField();
		txtf2 = new JTextField();
		txtf3 = new JTextField();
		txtf4 = new JTextField();
		txtf5 = new JTextField();
		txtf6 = new JTextField();
		JP1.setLayout(gl1);
		JP1.add(lab1);
		JP1.add(lab2);
		JP1.add(lab3);
		JP1.add(lab4);
		JP1.add(lab5);
		JP1.add(lab6);
		JP2.setLayout(gl2);
		JP2.add(txtf1);
		JP2.add(txtf2);
		JP2.add(txtf3);
		JP2.add(txtf4);
		JP2.add(txtf5);
		JP2.add(txtf6);
		JP3.setLayout(gl3);
		JP3.add(jl1);
		JP3.add(jl2);
		JP3.add(jl3);
		JP3.add(jl4);
		JP3.add(jl5);
		JP3.add(jl6);
		JP4.add(retour);
		JP4.add(effacer);
		JP4.add(creer);
		this.setLayout(bl);
		this.add("North",label);
		this.add("West",JP1);
		this.add("Center",JP2);
		this.add("East",JP3);
		this.add("South",JP4);
		this.setLocation(100,100);
		this.setSize(300,250);
		this.setVisible(true);
	}
	//
	/**
	 * methode pour effacer le texte des composants JtextField
	 */
	public void effacer() {
		txtf1.setText(null);
		txtf2.setText(null);
		txtf3.setText(null);
		txtf4.setText(null);
		txtf5.setText(null);
		txtf6.setText(null);
	}
	/**
	 * méthode pour vérifier les valeurs saisies dans les champs de texte du formulaire.
	 * @return boolean res
	 */
	public boolean verifierDonnees() {
		//fonction pour vérifier les valeurs du formulaire
		boolean res = true;
		if(txtf1.getText().equals("")==true || txtf2.getText().equals("")==true || txtf3.getText().equals("")==true || txtf4.getText().equals("")==true || txtf5.getText().equals("")==true) {
			res = false;
			txtf6.setText("Remplir les champs!");
		}
		try {
			 //LocalDate date = 
					 LocalDate.parse(txtf1.getText().toString());
		}
		catch(Exception e) {
			txtf6.setText("Vérifier la date saisie!");
			res = false;
		}
		return res;
	}
	/**
	 * methode pour le requete de création d'un objet tir au but(shootout)
	 */
	public void creer() {
		if(verifierDonnees()==false) {
			return;
		}
		else if(verifierDonnees()==true) {
				EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("config1");//pu_essai dans persistence.xm
				EntityManager em = entityManagerFactory.createEntityManager();
				EntityTransaction transaction = em.getTransaction();
				//Ouverture de la transaction pour le contexte de persistence
				transaction.begin();//ouverture de la transaction
					Shootout SH = new Shootout();
					SH.setDate(LocalDate.parse(txtf1.getText().toString()));//LocalDate
					SH.setHomeTeam(txtf2.getText().toString());
					SH.setAwayTeam(txtf3.getText().toString());
					SH.setWinner(txtf4.getText().toString());
					SH.setFirstShooter(txtf5.getText().toString());
					em.persist(SH);
				transaction.commit();
				em.close();
		}
	}
	//
	/**
	 * méthode main pour tester la fenetre
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CreerShoot CSH = new CreerShoot();
		CSH.setVisible(true);
	}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	/**
	 * méthode pour gerer le survole d'un composant par 
	 * le pointeur de la souris afin d'afficher un message d'aide.
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
			ma.txta.setText("Date des tirs aux buts.(format ISO: YYYY-MM-DD)");
			ma.setVisible(true);
		}
		else if(obj==jl2) {
			Point p = this.jl2.getLocationOnScreen();
			ma = new MessageAide();
			ma.xma = p.x+25;
			ma.yma = p.y+5;
			ma.setLocation(ma.xma,ma.yma);
			ma.txta.setText("Nom de léquipe à domicile(home team).");
			ma.setVisible(true);
		}
		else if(obj==jl3) {
			Point p = this.jl3.getLocationOnScreen();
			ma = new MessageAide();
			ma.xma = p.x+25;
			ma.yma = p.y+5;
			ma.setLocation(ma.xma,ma.yma);
			ma.txta.setText("Nom de l'équipe invitée(away team).");
			ma.setVisible(true);
		}
		else if(obj==jl4) {
			Point p = this.jl4.getLocationOnScreen();
			ma = new MessageAide();
			ma.xma = p.x+25;
			ma.yma = p.y+5;
			ma.setLocation(ma.xma,ma.yma);
			ma.txta.setText("Nom de l'équipe victorieuse.");
			ma.setVisible(true);
		}
		else if(obj==jl5) {
			Point p = this.jl5.getLocationOnScreen();
			ma = new MessageAide();
			ma.xma = p.x+25;
			ma.yma = p.y+5;
			ma.setLocation(ma.xma,ma.yma);
			ma.txta.setText("Nom de l'équipe du premier tir.");
			ma.setVisible(true);
		}
		else if(obj==jl6) {
			Point p = this.getLocationOnScreen();
			ma = new MessageAide();
			ma.xma = p.x+25;
			ma.yma = p.y+5;
			ma.setLocation(ma.xma,ma.yma);
			ma.txta.setText("Affiche des messages durant l'utilisation.");
			ma.setVisible(true);
		}
	}
	/**
	 * méthode pour gerer la sortie du pointeur de la souris
	 * d'un composant JLabel, afin de liberer la mémoire utilisée 
	 * et masquer la fenetre d'aide.
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
		else if(obj==jl6) {
			ma.setVisible(false);
			ma.dispose();
		}
	}
	/**
	 * méthode de gestion des actions des boutons de l'interface.
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		Object obj = ae.getSource();
		if(obj==retour) {
			this.setVisible(false);
			this.dispose();
			MenuAcces MA = new MenuAcces();
			MA.setVisible(true);
		}
		else if(obj==effacer) {
			effacer();
		}
		else if(obj==creer) {
			creer();
		}
	}

}
