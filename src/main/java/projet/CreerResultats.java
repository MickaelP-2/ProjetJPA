package projet;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import utils.MessageAide;

public class CreerResultats extends JFrame implements ActionListener,MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//attributs: date, homeTeam, awayTeam, homeScore, awayScore, tournment, city, country, neutral
	// table : Results
	/*
	 * private int id;
	private LocalDate date;
	private String homeTeam;
	private String awayTeam;
	private int homeScore;
	private int awayScore;
	private String tournois;
	private String nomVille;
	private String country;
	private boolean neutre;
	 */
	//
	/**
	 * Attributs de classe de création de résultat.
	 * Composants swing et classes.
	 */
	BorderLayout bl;
	GridLayout gl1,gl2,gl3;
	JPanel JP1,JP2,JP3,JP4;
	JLabel label, lab1,lab2,lab3,lab4,lab5,lab6,lab7,lab8,lab9,lab10,jl1,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9,jl10;
	JTextField txtf1,txtf2,txtf3,txtf4,txtf5,txtf6,txtf7,txtf8,txtf9,txtf10;
	JButton retour, effacer,creer; 
	Results RES;//pour importer la classe
	MessageAide ma;
	//
	/**
	 * Constructeur de la fenetre de création de résultat.
	 */
	public CreerResultats() {
		//constructeur
		bl = new BorderLayout();
		gl1 = new GridLayout(10,1);
		gl2 = new GridLayout(10,1);
		gl3 = new GridLayout(10,1);
		JP1 = new JPanel();
		JP2 = new JPanel();
		JP3 = new JPanel();
		JP4 = new JPanel();
		label = new JLabel("Création/insertion de résultats.");
		label.setHorizontalAlignment((int)CENTER_ALIGNMENT);
		lab1 = new JLabel(" Date: ");
		lab2 = new JLabel(" Equipe home: ");
		lab3 = new JLabel(" Equipe away: ");
		lab4 = new JLabel(" Score home: ");
		lab5 = new JLabel(" Away score: ");
		lab6 = new JLabel(" Tournois: ");
		lab7 = new JLabel(" Ville: ");
		lab8 = new JLabel(" Pays: ");
		lab9 = new JLabel(" Neutre: ");
		lab10 = new JLabel("");
		JP1.setLayout(gl1);
		JP1.add(lab1);
		JP1.add(lab2);
		JP1.add(lab3);
		JP1.add(lab4);
		JP1.add(lab5);
		JP1.add(lab6);
		JP1.add(lab7);
		JP1.add(lab8);
		JP1.add(lab9);
		JP1.add(lab10);
		txtf1 = new JTextField();
		txtf2 = new JTextField();
		txtf3 = new JTextField();
		txtf4 = new JTextField();
		txtf5 = new JTextField();
		txtf6 = new JTextField();
		txtf7 = new JTextField();
		txtf8 = new JTextField();
		txtf9 = new JTextField();
		txtf10 = new JTextField();
		JP2.setLayout(gl2);
		JP2.add(txtf1);
		JP2.add(txtf2);
		JP2.add(txtf3);
		JP2.add(txtf4);
		JP2.add(txtf5);
		JP2.add(txtf6);
		JP2.add(txtf7);
		JP2.add(txtf8);
		JP2.add(txtf9);
		JP2.add(txtf10);
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
		jl7 = new JLabel(new ImageIcon(getClass().getResource("/interro2.png")));
		jl7.addMouseListener(this);
		jl8 = new JLabel(new ImageIcon(getClass().getResource("/interro2.png")));
		jl8.addMouseListener(this);
		jl9 = new JLabel(new ImageIcon(getClass().getResource("/interro2.png")));
		jl9.addMouseListener(this);
		jl10 = new JLabel(new ImageIcon(getClass().getResource("/interro2.png")));
		jl10.addMouseListener(this);
		JP3.setLayout(gl3);
		JP3.add(jl1);
		JP3.add(jl2);
		JP3.add(jl3);
		JP3.add(jl4);
		JP3.add(jl5);
		JP3.add(jl6);
		JP3.add(jl7);
		JP3.add(jl8);
		JP3.add(jl9);
		JP3.add(jl10);
		retour = new JButton("Retour");
		retour.addActionListener(this);
		effacer = new JButton("Effacer");
		effacer.addActionListener(this);
		creer = new JButton("Créer");
		creer.addActionListener(this);
		JP4 = new JPanel();
		JP4.add(retour);
		JP4.add(creer);
		JP4.add(effacer);
		this.setLayout(bl);
		this.add("North",label);
		this.add("West",JP1);
		this.add("Center",JP2);
		this.add("East",JP3);
		this.add("South",JP4);
		this.setLocation(150,150);
		this.setSize(350,300);
		this.setVisible(true);
	}
	//
	/**
	 * méthode pour vérifier les valeurs saisies dans les champs de texte du formulaire.
	 * @return boolean res
	 */
	public boolean verifierDonnees() {
		//fonction pour vérifier les valeurs du formulaire
		LocalDate date = null;
		int home = 0;
		int away =0;
		Object bool = false;
		boolean res = true;
		if(txtf1.getText().equals("")==true || txtf2.getText().equals("")==true || txtf3.getText().equals("")==true || txtf4.getText().equals("")==true || txtf5.getText().equals("")==true || txtf6.getText().equals("")==true || txtf7.getText().equals("")==true || txtf8.getText().equals("")==true || txtf9.getText().equals("")==true) {
			res = false;
			txtf10.setText("Remplir les champs!");
		}
		try {
			 LocalDate.parse(txtf1.getText().toString());
		}
		catch(Exception e) {
			txtf10.setText("Vérifier la date saisie!");
			res = false;
		}
		try {
			Integer.parseInt(txtf4.getText());
		}
		catch(Exception e) {
			txtf10.setText("Vérifier le home score saisi!");
			res = false;
		}
		try {
			Integer.parseInt(txtf5.getText());
		}
		catch(Exception e) {
			txtf10.setText("Vérifier le away score saisi!");
			res = false;
		}
		
		if(txtf9.getText().equals("true")==true || txtf9.getText().equals("false")==true || txtf9.getText().equals("TRUE")==true || txtf9.getText().equals("FALSE")==true) {
			res = true;
		}
		else res = false;
		return res;
	}
	/**
	 * méthode pour implémenter la requete de création d'un résultat
	 */
	public void creer() {
		//requete pour ajout dans mysql
		//verification des valeurs
		if(verifierDonnees()==false) {
			return;
		}
		else if(verifierDonnees()==true) {
					EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("config1");//Foot dans persistence.xm
					EntityManager em = entityManagerFactory.createEntityManager();
					EntityTransaction transaction = em.getTransaction();
					//Ouverture de la transaction pour le contexte de persistence
					transaction.begin();//ouverture de la transaction
						Results RS = new Results();
						RS.setDate(LocalDate.parse(txtf1.getText().toString()));//LocalDate
						RS.setHomeTeam(txtf2.getText().toString());
						RS.setAwayTeam(txtf3.getText().toString());
						RS.setHomeScore(Integer.parseInt(txtf4.getText().toString()));
						RS.setAwayScore(Integer.parseInt(txtf5.getText().toString()));
						RS.setTournois(txtf6.getText().toString());
						RS.setNomVille(txtf7.getText().toString());
						RS.setCountry(txtf8.getText().toString());
						RS.setNeutre(Boolean.parseBoolean(txtf9.getText().toString()));
						em.persist(RS);
					transaction.commit();//application de la transaction
					em.close();
		}
	}
	/**
	 * méthode pour éffacer les champs du formulaire.
	 */
	public void effacer() {
		txtf1.setText(null);
		txtf2.setText(null);
		txtf3.setText(null);
		txtf4.setText(null);
		txtf5.setText(null);
		txtf6.setText(null);
		txtf7.setText(null);
		txtf8.setText(null);
		txtf9.setText(null);
		txtf10.setText(null);
	}
	/**
	 * méthode pour tester la fenetres et les composants
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CreerResultats CR = new CreerResultats();
		CR.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	/**
	 * méthode pour afficher un message d'aide au survol du composant
	 * par le pointeur de la souris.
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
			ma.txta.setText("Date de la recontre.(format IOS: YYYY-MM-DD)");
			ma.setVisible(true);
		}
		else if(obj==jl2) {
			Point p = this.jl2.getLocationOnScreen();
			ma = new MessageAide();
			ma.xma = p.x+25;
			ma.yma = p.y+5;
			ma.setLocation(ma.xma,ma.yma);
			ma.txta.setText("Nom de l'équipe à domicile.");
			ma.setVisible(true);
		}
		else if(obj==jl3) {
			Point p = this.jl3.getLocationOnScreen();
			ma = new MessageAide();
			ma.xma = p.x+25;
			ma.yma = p.y+5;
			ma.setLocation(ma.xma,ma.yma);
			ma.txta.setText("Nom de l'équipe invitée.");
			ma.setVisible(true);
		}
		else if(obj==jl4) {
			Point p = this.jl4.getLocationOnScreen();
			ma = new MessageAide();
			ma.xma = p.x+25;
			ma.yma = p.y+5;
			ma.setLocation(ma.xma,ma.yma);
			ma.txta.setText("Score de l'équipe home.");
			ma.setVisible(true);
		}
		else if(obj==jl5) {
			Point p = this.jl5.getLocationOnScreen();
			ma = new MessageAide();
			ma.xma = p.x+25;
			ma.yma = p.y+5;
			ma.setLocation(ma.xma,ma.yma);
			ma.txta.setText("Score de l'équipe invitée");
			ma.setVisible(true);
		}
		else if(obj==jl6) {
			Point p = this.jl6.getLocationOnScreen();
			ma = new MessageAide();
			ma.xma = p.x+25;
			ma.yma = p.y+5;
			ma.setLocation(ma.xma,ma.yma);
			ma.txta.setText("Nom du tournois.");
			ma.setVisible(true);
		}
		else if(obj==jl7) {
			Point p = this.jl7.getLocationOnScreen();
			ma = new MessageAide();
			ma.xma = p.x+25;
			ma.yma = p.y+5;
			ma.setLocation(ma.xma,ma.yma);
			ma.txta.setText("Nom de la ville de la rencontre.");
			ma.setVisible(true);
		}
		else if(obj==jl8) {
			Point p = this.jl8.getLocationOnScreen();
			ma = new MessageAide();
			ma.xma = p.x+25;
			ma.yma = p.y+5;
			ma.setLocation(ma.xma,ma.yma);
			ma.txta.setText("Nom de la ville ou se déroule le tournois/match.");
			ma.setVisible(true);
		}
		else if(obj==jl9) {
			Point p = this.jl9.getLocationOnScreen();
			ma = new MessageAide();
			ma.xma = p.x+25;
			ma.yma = p.y+5;
			ma.setLocation(ma.xma,ma.yma);
			ma.txta.setText("Neutralité de l'endroit du match.(valeurs: true/false");
			ma.setVisible(true);
		}
		else if(obj==jl10) {
			Point p = this.jl10.getLocationOnScreen();
			ma = new MessageAide();
			ma.xma = p.x+25;
			ma.yma = p.y+5;
			ma.setLocation(ma.xma,ma.yma);
			ma.txta.setText("Affiche des messages durant l'utilisation du logiciel");
			ma.setVisible(true);
		}
	}
	/**
	 * méthode pour restituer la mémoire utilisée par
	 * la fenetre d'aide et masquer celle-ci. 
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
		else if(obj==jl7) {
			ma.setVisible(false);
			ma.dispose();
		}
		else if(obj==jl8) {
			ma.setVisible(false);
			ma.dispose();
		}
		else if(obj==jl9) {
			ma.setVisible(false);
			ma.dispose();
		}
		else if(obj==jl10) {
			ma.setVisible(false);
			ma.dispose();
		}
	}
	/**
	 * méthode pour gerer les actions des différents boutons.
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
