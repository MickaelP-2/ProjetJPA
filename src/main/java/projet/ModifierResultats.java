package projet;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

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
import jakarta.persistence.Query;
import utils.FenetreAffichage;
import utils.MessageAide;

public class ModifierResultats extends JFrame implements ActionListener,MouseListener{
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
	 * Attributs de la fenetre de modification de résultats.
	 * Composants swing, classes.
	 */
	BorderLayout bl;
	GridLayout gl1,gl2,gl3;
	JPanel JP1,JP2,JP3,JP4;
	JLabel label, lab1,lab2,lab3,lab4,lab5,lab6,lab7,lab8,lab9,lab10,lab11,jl1,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9,jl10,jl11;
	JTextField txtf1,txtf2,txtf3,txtf4,txtf5,txtf6,txtf7,txtf8,txtf9,txtf10,txtf11;
	JButton retour, effacer,rechercher,modifier; 
	Results RES;//pour importer la classe
	MessageAide ma;
	FenetreAffichage FA;
	Results RSmodif;//Pour conserver les valeurs precedent la modification
	//
	/**
	 * Constructeur de la fenetre de modification de résultats.
	 * Composants swing, classes.
	 */
	public ModifierResultats() {
		//constructeur
		bl = new BorderLayout();
		gl1 = new GridLayout(11,1);
		gl2 = new GridLayout(11,1);
		gl3 = new GridLayout(11,1);
		JP1 = new JPanel();
		JP2 = new JPanel();
		JP3 = new JPanel();
		JP4 = new JPanel();
		label = new JLabel("Modification de résultats.");
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
		lab10 = new JLabel(" Id: ");
		lab11 = new JLabel("");
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
		JP1.add(lab11);
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
		txtf11 = new JTextField();
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
		JP2.add(txtf11);
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
		jl11 = new JLabel(new ImageIcon(getClass().getResource("/interro2.png")));
		jl11.addMouseListener(this);
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
		JP3.add(jl11);
		retour = new JButton("Retour");
		retour.addActionListener(this);
		effacer = new JButton("Effacer");
		effacer.addActionListener(this);
		rechercher = new JButton("Recherche");
		rechercher.addActionListener(this);
		modifier = new JButton("Modifier");
		modifier.addActionListener(this);
		JP4 = new JPanel();
		JP4.add(retour);
		JP4.add(rechercher);
		JP4.add(effacer);
		JP4.add(modifier);
		this.setLayout(bl);
		this.add("North",label);
		this.add("West",JP1);
		this.add("Center",JP2);
		this.add("East",JP3);
		this.add("South",JP4);
		this.setLocation(150,150);
		this.setSize(450,300);
		this.setVisible(true);
	}
	//
	
	/**
	 * méthode de recherche par date de résultats  dans la table des résultats.
	 */
	public void rechercheDate() {
		//requete pour recherche dans mysql
		List<Results> list;
		Iterator<Results> iter = null;
		String ligne = null;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("config1");//Foot dans persistence.xm
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
			transaction.begin();//ouverture de la transaction
			Query query = em.createQuery("SELECT rs FROM Results rs WHERE rs.date= :date");
			query.setParameter("date",LocalDate.parse(txtf1.getText().toString()));
			list = query.getResultList();
			Results RS = new Results();
			if(list.size()>1) { 
				FA = new FenetreAffichage();
				iter = list.iterator();
				while(iter.hasNext()) {
					ligne = iter.next().toString();	
					FA.text.append(ligne+"\n");
				}
				FA.setVisible(true);
			}//fin if()
			else if(list.size()==1) {
				RSmodif = new Results();
				String[] str = list.toString().split(",");
				if(str[0].toString().contains("[")==true) {
					str[0] = str[0].toString().replace("[","");
				}
				if(str[9].toString().contains("]")==true) {
					str[9] = str[9].toString().replace("]","");
				}
				RS.setDate(LocalDate.parse(str[1].toString()));
				RS.setHomeTeam(str[2].toString());
				RS.setAwayTeam(str[3].toString());
				RS.setHomeScore(Integer.parseInt(str[4].toString()));
				RS.setAwayScore(Integer.parseInt(str[5].toString()));
				RS.setTournois(str[6].toString());
				RS.setNomVille(str[7].toString());
				RS.setCountry(str[8].toString());
				RS.setNeutre(Boolean.parseBoolean(str[9].toString()));
				//
				RSmodif.setId(Integer.parseInt(str[0].toString()));
				RSmodif.setDate(LocalDate.parse(str[1].toString()));
				RSmodif.setHomeTeam(str[2].toString());
				RSmodif.setAwayTeam(str[3].toString());
				RSmodif.setHomeScore(Integer.parseInt(str[4].toString()));
				RSmodif.setAwayScore(Integer.parseInt(str[5].toString()));
				RSmodif.setTournois(str[6].toString());
				RSmodif.setNomVille(str[7].toString());
				RSmodif.setCountry(str[8].toString());
				RSmodif.setNeutre(Boolean.parseBoolean(str[9].toString()));
				RSmodif.setId(Integer.parseInt(str[0].toString()));
				//
				txtf1.setText(RS.getDate().toString());
				txtf2.setText(RS.getHomeTeam());
				txtf3.setText(RS.getAwayTeam());
				txtf4.setText(""+RS.getHomeScore());
				txtf5.setText(""+RS.getAwayScore());
				txtf6.setText(RS.getTournois());
				txtf7.setText(RS.getNomVille());
				txtf8.setText(RS.getCountry());
				txtf9.setText(""+RS.isNeutre());
				txtf10.setText(""+RS.getId());
			}
			else if(list.size()==0) {
				txtf11.setText("Pas de résultats!!");
			}
			transaction.commit();//validation de la transaction
			em.close();//fermeture du flux
	}
	/**
	 * méthode de recherche par nom de l'équipe à domicile dans la table des résultats.
	 */
	public void rechercheHome() {
		//requete pour recherche dans mysql
		List<Results> list;
		Iterator<Results> iter = null;
		String ligne = null;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("config1");//Foot dans persistence.xm
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
			transaction.begin();//ouverture de la transaction
			Query query = em.createQuery("SELECT rs FROM Results rs WHERE rs.homeTeam= :hometeam");
			query.setParameter("hometeam",txtf2.getText().toString());
			list = query.getResultList();
			Results RS = new Results();
			if(list.size()>1) { 
				FA = new FenetreAffichage();
				iter = list.iterator();
				while(iter.hasNext()) {
					ligne = iter.next().toString();	
					FA.text.append(ligne+"\n");
				}
				FA.setVisible(true);
			}//fin if()
			else if(list.size()==1) {
				RSmodif = new Results();
				String[] str = list.toString().split(",");
				if(str[0].toString().contains("[")==true) {
					str[0] = str[0].toString().replace("[","");
				}
				if(str[9].toString().contains("]")==true) {
					str[9] = str[9].toString().replace("]","");
				}
				RS.setDate(LocalDate.parse(str[1].toString()));
				RS.setHomeTeam(str[2].toString());
				RS.setAwayTeam(str[3].toString());
				RS.setHomeScore(Integer.parseInt(str[4].toString()));
				RS.setAwayScore(Integer.parseInt(str[5].toString()));
				RS.setTournois(str[6].toString());
				RS.setNomVille(str[7].toString());
				RS.setCountry(str[8].toString());
				RS.setNeutre(Boolean.parseBoolean(str[9].toString()));
				RS.setId(Integer.parseInt(str[0].toString()));
				//
				RSmodif.setId(Integer.parseInt(str[0].toString()));
				RSmodif.setDate(LocalDate.parse(str[1].toString()));
				RSmodif.setHomeTeam(str[2].toString());
				RSmodif.setAwayTeam(str[3].toString());
				RSmodif.setHomeScore(Integer.parseInt(str[4].toString()));
				RSmodif.setAwayScore(Integer.parseInt(str[5].toString()));
				RSmodif.setTournois(str[6].toString());
				RSmodif.setNomVille(str[7].toString());
				RSmodif.setCountry(str[8].toString());
				RSmodif.setNeutre(Boolean.parseBoolean(str[9].toString()));
				RSmodif.setId(Integer.parseInt(str[0].toString()));
				//
				txtf1.setText(RS.getDate().toString());
				txtf2.setText(RS.getHomeTeam());
				txtf3.setText(RS.getAwayTeam());
				txtf4.setText(""+RS.getHomeScore());
				txtf5.setText(""+RS.getAwayScore());
				txtf6.setText(RS.getTournois());
				txtf7.setText(RS.getNomVille());
				txtf8.setText(RS.getCountry());
				txtf9.setText(""+RS.isNeutre());
				txtf10.setText(""+RS.getId());
			}
			else if(list.size()==0) {
				txtf11.setText("Pas de résultats!!");
			}
			transaction.commit();//validation de la transaction
			em.close();//fermeture du flux
	}
	/**
	 * méthode de recherche par nom de l'équipe invitée dans la table des résultats.
	 */
	public void rechercheAway() {
		//requete pour recherche dans mysql
		List<Results> list;
		Iterator<Results> iter = null;
		String ligne = null;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("config1");//Foot dans persistence.xm
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
			transaction.begin();//ouverture de la transaction
			Query query = em.createQuery("SELECT rs FROM Results rs WHERE rs.awayTeam= :awayteam");
			query.setParameter("awayteam",txtf3.getText().toString());
			list = query.getResultList();
			Results RS = new Results();
			if(list.size()>1) { 
				FA = new FenetreAffichage();
				iter = list.iterator();
				while(iter.hasNext()) {
					ligne = iter.next().toString();	
					FA.text.append(ligne+"\n");
				}
				FA.setVisible(true);
			}//fin if()
			else if(list.size()==1) {
				RSmodif = new Results();
				String[] str = list.toString().split(",");
				if(str[0].toString().contains("[")==true) {
					str[0] = str[0].toString().replace("[","");
				}
				if(str[9].toString().contains("]")==true) {
					str[9] = str[9].toString().replace("]","");
				}
				RS.setDate(LocalDate.parse(str[1].toString()));
				RS.setHomeTeam(str[2].toString());
				RS.setAwayTeam(str[3].toString());
				RS.setHomeScore(Integer.parseInt(str[4].toString()));
				RS.setAwayScore(Integer.parseInt(str[5].toString()));
				RS.setTournois(str[6].toString());
				RS.setNomVille(str[7].toString());
				RS.setCountry(str[8].toString());
				RS.setNeutre(Boolean.parseBoolean(str[9].toString()));
				RS.setId(Integer.parseInt(str[0].toString()));
				//
				RSmodif.setId(Integer.parseInt(str[0].toString()));
				RSmodif.setDate(LocalDate.parse(str[1].toString()));
				RSmodif.setHomeTeam(str[2].toString());
				RSmodif.setAwayTeam(str[3].toString());
				RSmodif.setHomeScore(Integer.parseInt(str[4].toString()));
				RSmodif.setAwayScore(Integer.parseInt(str[5].toString()));
				RSmodif.setTournois(str[6].toString());
				RSmodif.setNomVille(str[7].toString());
				RSmodif.setCountry(str[8].toString());
				RSmodif.setNeutre(Boolean.parseBoolean(str[9].toString()));
				RSmodif.setId(Integer.parseInt(str[0].toString()));
				//
				txtf1.setText(RS.getDate().toString());
				txtf2.setText(RS.getHomeTeam());
				txtf3.setText(RS.getAwayTeam());
				txtf4.setText(""+RS.getHomeScore());
				txtf5.setText(""+RS.getAwayScore());
				txtf6.setText(RS.getTournois());
				txtf7.setText(RS.getNomVille());
				txtf8.setText(RS.getCountry());
				txtf9.setText(""+RS.isNeutre());
				txtf10.setText(""+RS.getId());
			}
			else if(list.size()==0) {
				txtf11.setText("Pas de résultats!!");
			}
			transaction.commit();//validation de la transaction
			em.close();//fermeture du flux
	}
	/**
	 * méthode de recherche par nom de tournois dans la table des résultats.
	 */
	public void rechercheTournois() {
		//requete pour recherche dans mysql
		List<Results> list;
		Iterator<Results> iter = null;
		String ligne = null;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("config1");//Foot dans persistence.xm
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
			transaction.begin();//ouverture de la transaction
			Query query = em.createQuery("SELECT rs FROM Results rs WHERE rs.tournois= :tournois");
			query.setParameter("tournois",txtf6.getText().toString());
			list = query.getResultList();
			Results RS = new Results();
			if(list.size()>1) { 
				FA = new FenetreAffichage();
				iter = list.iterator();
				while(iter.hasNext()) {
					ligne = iter.next().toString();	
					FA.text.append(ligne+"\n");
				}
				FA.setVisible(true);
			}//fin if()
			else if(list.size()==1) {
				RSmodif = new Results();
				String[] str = list.toString().split(",");
				if(str[0].toString().contains("[")==true) {
					str[0] = str[0].toString().replace("[","");
				}
				if(str[9].toString().contains("]")==true) {
					str[9] = str[9].toString().replace("]","");
				}
				RS.setDate(LocalDate.parse(str[1].toString()));
				RS.setHomeTeam(str[2].toString());
				RS.setAwayTeam(str[3].toString());
				RS.setHomeScore(Integer.parseInt(str[4].toString()));
				RS.setAwayScore(Integer.parseInt(str[5].toString()));
				RS.setTournois(str[6].toString());
				RS.setNomVille(str[7].toString());
				RS.setCountry(str[8].toString());
				RS.setNeutre(Boolean.parseBoolean(str[9].toString()));
				RS.setId(Integer.parseInt(str[0].toString()));
				//
				RSmodif.setId(Integer.parseInt(str[0].toString()));
				RSmodif.setDate(LocalDate.parse(str[1].toString()));
				RSmodif.setHomeTeam(str[2].toString());
				RSmodif.setAwayTeam(str[3].toString());
				RSmodif.setHomeScore(Integer.parseInt(str[4].toString()));
				RSmodif.setAwayScore(Integer.parseInt(str[5].toString()));
				RSmodif.setTournois(str[6].toString());
				RSmodif.setNomVille(str[7].toString());
				RSmodif.setCountry(str[8].toString());
				RSmodif.setNeutre(Boolean.parseBoolean(str[9].toString()));
				RSmodif.setId(Integer.parseInt(str[0].toString()));
				//
				txtf1.setText(RS.getDate().toString());
				txtf2.setText(RS.getHomeTeam());
				txtf3.setText(RS.getAwayTeam());
				txtf4.setText(""+RS.getHomeScore());
				txtf5.setText(""+RS.getAwayScore());
				txtf6.setText(RS.getTournois());
				txtf7.setText(RS.getNomVille());
				txtf8.setText(RS.getCountry());
				txtf9.setText(""+RS.isNeutre());
				txtf10.setText(""+RS.getId());
			}
			else if(list.size()==0) {
				txtf11.setText("Pas de résultats!!");
			}
			transaction.commit();//validation de la transaction
			em.close();//fermeture du flux
	}
	/**
	 * méthode de recherche par identifiant id.
	 */
	public void rechercheId() {
		List<Results> list;
		Iterator<Results> iter = null;
		String ligne = null;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("config1");//Foot dans persistence.xm
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
			transaction.begin();//ouverture de la transaction
			Query query = em.createQuery("SELECT rs FROM Results rs WHERE rs.id= :id");
			query.setParameter("id",(Integer.parseInt(txtf10.getText().toString())));
			list = query.getResultList();
			Results RS = new Results();
			if(list.size()>1) { 
				FA = new FenetreAffichage();
				iter = list.iterator();
				while(iter.hasNext()) {
					ligne = iter.next().toString();	
					FA.text.append(ligne+"\n");
				}
				FA.setVisible(true);
			}//fin if()
			else if(list.size()==1) {
				RSmodif = new Results();
				String[] str = list.toString().split(",");
				if(str[0].toString().contains("[")==true) {
					str[0] = str[0].toString().replace("[","");
				}
				if(str[9].toString().contains("]")==true) {
					str[9] = str[9].toString().replace("]","");
				}
				RS.setDate(LocalDate.parse(str[1].toString()));
				RS.setHomeTeam(str[2].toString());
				RS.setAwayTeam(str[3].toString());
				RS.setHomeScore(Integer.parseInt(str[4].toString()));
				RS.setAwayScore(Integer.parseInt(str[5].toString()));
				RS.setTournois(str[6].toString());
				RS.setNomVille(str[7].toString());
				RS.setCountry(str[8].toString());
				RS.setNeutre(Boolean.parseBoolean(str[9].toString()));
				RS.setId(Integer.parseInt(str[0].toString()));
				//
				RSmodif.setId(Integer.parseInt(str[0].toString()));
				RSmodif.setDate(LocalDate.parse(str[1].toString()));
				RSmodif.setHomeTeam(str[2].toString());
				RSmodif.setAwayTeam(str[3].toString());
				RSmodif.setHomeScore(Integer.parseInt(str[4].toString()));
				RSmodif.setAwayScore(Integer.parseInt(str[5].toString()));
				RSmodif.setTournois(str[6].toString());
				RSmodif.setNomVille(str[7].toString());
				RSmodif.setCountry(str[8].toString());
				RSmodif.setNeutre(Boolean.parseBoolean(str[9].toString()));
				RSmodif.setId(Integer.parseInt(str[0].toString()));
				//
				txtf1.setText(RS.getDate().toString());
				txtf2.setText(RS.getHomeTeam());
				txtf3.setText(RS.getAwayTeam());
				txtf4.setText(""+RS.getHomeScore());
				txtf5.setText(""+RS.getAwayScore());
				txtf6.setText(RS.getTournois());
				txtf7.setText(RS.getNomVille());
				txtf8.setText(RS.getCountry());
				txtf9.setText(""+RS.isNeutre());
				txtf10.setText(""+RS.getId());
			}
			else if(list.size()==0) {
				txtf11.setText("Pas de résultats!!");
			}
			transaction.commit();//validation de la transaction
			em.close();//fermeture du flux
	}
	/**
	 * méthode pour modifier un résultat dans la table.
	 */
	public void modifier() {
		//requete pour ajout dans mysql
		System.out.println("modifier resultats");
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("config1");//Foot dans persistence.xm
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
			transaction.begin();//ouverture de la transaction
			//
			Query query = em.createQuery("UPDATE Results rs SET rs.date= :newdate  WHERE rs.date= :olddate");
			query.setParameter("newdate",(LocalDate.parse(txtf1.getText())));
			query.setParameter("olddate",(LocalDate.parse(RSmodif.getDate().toString())));
			query.executeUpdate();
			query = em.createQuery("UPDATE Results rs SET rs.homeTeam= :newhomeTeam  WHERE rs.homeTeam= :oldhomeTeam");
			query.setParameter("newhomeTeam",(txtf2.getText()));
			query.setParameter("oldhomeTeam",RSmodif.getHomeTeam().toString());
			query.executeUpdate();
			query = em.createQuery("UPDATE Results rs SET rs.awayTeam= :newawayTeam  WHERE rs.awayTeam= :oldawayTeam");
			query.setParameter("newawayTeam",(txtf3.getText()));
			query.setParameter("oldawayTeam",RSmodif.getAwayTeam().toString());
			query.executeUpdate();
			query = em.createQuery("UPDATE Results rs SET rs.homeScore= :newhomeScore  WHERE rs.homeScore= :oldhomeScore");
			query.setParameter("newhomeScore",(Integer.parseInt(txtf4.getText())));
			query.setParameter("oldhomeScore",RSmodif.getHomeScore());
			query.executeUpdate();
			query = em.createQuery("UPDATE Results rs SET rs.awayScore= :newawayScore  WHERE rs.awayScore= :oldawayScore");
			query.setParameter("newawayScore",(Integer.parseInt(txtf5.getText())));
			query.setParameter("oldawayScore",RSmodif.getAwayScore());
			query.executeUpdate();
			query = em.createQuery("UPDATE Results rs SET rs.tournois= :newtournois  WHERE rs.tournois= :oldtournois");
			query.setParameter("newtournois",(txtf6.getText()));
			query.setParameter("oldtournois",RSmodif.getTournois().toString());
			query.executeUpdate();
			query = em.createQuery("UPDATE Results rs SET rs.nomVille= :newnomVille  WHERE rs.nomVille= :oldnomVille");
			query.setParameter("newnomVille",(txtf7.getText()));
			query.setParameter("oldnomVille",RSmodif.getNomVille());
			query.executeUpdate();
			query = em.createQuery("UPDATE Results rs SET rs.country= :newcountry  WHERE rs.country= :oldcountry");
			query.setParameter("newcountry",(txtf8.getText()));
			query.setParameter("oldcountry",RSmodif.getCountry());
			query.executeUpdate();
			query = em.createQuery("UPDATE Results rs SET rs.neutre= :newneutre  WHERE rs.neutre= :oldneutre");
			query.setParameter("newneutre",(Boolean.parseBoolean(txtf9.getText())));
			query.setParameter("oldneutre",RSmodif.isNeutre());
			query.executeUpdate();
			//
			transaction.commit();//validation de la transaction
			em.close();//fermeture du flux
			
	}
	/**
	 * méthode pour éffacer les composants JTextField de la fenetre
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
		txtf10.setText(null);
	}
	//
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
			ma.txta.setText("Date de la recontre.");
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
			ma.txta.setText("Neutralité de l'endroit du match.");
			ma.setVisible(true);
		}
		else if(obj==jl10) {
			Point p = this.jl10.getLocationOnScreen();
			ma = new MessageAide();
			ma.xma = p.x+25;
			ma.yma = p.y+5;
			ma.setLocation(ma.xma,ma.yma);
			ma.txta.setText("Identifiant du résultat.");
			ma.setVisible(true);
		}
		else if(obj==jl11) {
			Point p = this.jl11.getLocationOnScreen();
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
		else if(obj==jl11) {
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
		else if(obj==rechercher) {
			//date, home,away,tournois
			if(txtf1.getText().equals("")==false){
				rechercheDate();
			}
			else if(txtf2.getText().equals("")==false){
				rechercheHome();
			}
			else if(txtf3.getText().equals("")==false){
				rechercheAway();
			}
			else if(txtf6.getText().equals("")==false){
				rechercheTournois();
			}
			else if(txtf10.getText().equals("")==false){
				rechercheId();
			}
		}
		else if(obj==modifier) {
			modifier();
		}
	}

}
