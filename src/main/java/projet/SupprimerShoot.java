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

import javax.swing.ImageIcon;

import utils.FenetreAffichage;
import utils.MessageAide;

public class SupprimerShoot extends JFrame implements ActionListener,MouseListener{
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
	/**
	 * Attributs de la fenetre de suppréssion de tirs au but.
	 * Composants swing, classes.
	 */
	BorderLayout bl;
	GridLayout gl1,gl2,gl3;
	JPanel JP1,JP2,JP3,JP4;
	JButton retour,effacer,rechercher,supprimer;
	JLabel label,lab1,lab2,lab3,lab4,lab5,lab6,lab7,jl1,jl2,jl3,jl4,jl5,jl6,jl7;
	JTextField txtf1,txtf2,txtf3,txtf4,txtf5,txtf6,txtf7;
	Shootout SH;
	MessageAide ma;
	FenetreAffichage FA;
	//
	/**
	 * consructeur de la classe graphique.
	 */
	public SupprimerShoot() {
		bl = new BorderLayout();
		gl1 = new GridLayout(7,1);
		gl2 = new GridLayout(7,1);
		gl3 = new GridLayout(7,1);
		JP1 = new JPanel();
		JP2 = new JPanel();
		JP3 = new JPanel();
		JP4 = new JPanel();
		label = new JLabel("Suppréssion de tirs aux buts.");
		label.setHorizontalAlignment((int)CENTER_ALIGNMENT);
		lab1 = new JLabel(" Date: ");
		lab2 = new JLabel(" Equipe home: ");
		lab3 = new JLabel(" Equipe away:");
		lab4 = new JLabel(" Vainqueur: ");
		lab5 = new JLabel(" Premier tir: ");
		lab6 = new JLabel(" Id: ");
		lab7 = new JLabel("");
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
		txtf1 = new JTextField();
		txtf2 = new JTextField();
		txtf3 = new JTextField();
		txtf4 = new JTextField();
		txtf5 = new JTextField();
		txtf6 = new JTextField();
		txtf7 = new JTextField();
		retour = new JButton("Retour");
		retour.addActionListener(this);
		effacer = new JButton("Effacer");
		effacer.addActionListener(this);
		rechercher = new JButton("Rechercher");
		rechercher.addActionListener(this);
		supprimer = new JButton("Supprimer");
		supprimer.addActionListener(this);
		JP1.setLayout(gl1);
		JP1.add(lab1);
		JP1.add(lab2);
		JP1.add(lab3);
		JP1.add(lab4);
		JP1.add(lab5);
		JP1.add(lab6);
		JP1.add(lab7);
		JP2.setLayout(gl2);
		JP2.add(txtf1);
		JP2.add(txtf2);
		JP2.add(txtf3);
		JP2.add(txtf4);
		JP2.add(txtf5);
		JP2.add(txtf6);
		JP2.add(txtf7);
		JP3.setLayout(gl3);
		JP3.add(jl1);
		JP3.add(jl2);
		JP3.add(jl3);
		JP3.add(jl4);
		JP3.add(jl5);
		JP3.add(jl6);
		JP3.add(jl7);
		JP4.add(retour);
		JP4.add(effacer);
		JP4.add(rechercher);
		JP4.add(supprimer);
		this.setLayout(bl);
		this.add("North",label);
		this.add("West",JP1);
		this.add("Center",JP2);
		this.add("East",JP3);
		this.add("South",JP4);
		this.setLocation(100,100);
		this.setSize(450,250);
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
		txtf6.setText(null);
	}
	/**
	 * méthode de recherche par date de résultats  dans la table des tirs aux buts.
	 */
	public void rechercheDate() {
		//requete pour recherche dans mysql
		List<Shootout> list;
		Iterator<Shootout> iter = null;
		String ligne = null;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("config1");//Foot dans persistence.xm
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
			transaction.begin();//ouverture de la transaction
			Query query = em.createQuery("SELECT sh FROM Shootout sh WHERE sh.date= :date");
			query.setParameter("date",LocalDate.parse(txtf1.getText().toString()));
			list = query.getResultList();
			Shootout SH = new Shootout();
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
				String[] str = list.toString().split(",");
				if(str[0].toString().contains("[")==true) {
					str[0] = str[0].toString().replace("[","");
				}
				if(str[5].toString().contains("]")==true) {
					str[5] = str[5].toString().replace("]","");
				}
				SH.setDate(LocalDate.parse(str[1].toString()));
				SH.setHomeTeam(str[2].toString());
				SH.setAwayTeam(str[3].toString());
				SH.setWinner(str[4].toString());
				SH.setFirstShooter(str[5]);
				SH.setId(Integer.parseInt(str[0].toString()));
				txtf1.setText(SH.getDate().toString());
				txtf2.setText(SH.getHomeTeam());
				txtf3.setText(SH.getAwayTeam());
				txtf4.setText(SH.getWinner());
				txtf5.setText(SH.getFirstShooter());
				txtf6.setText(""+SH.getId());
			}
			else if(list.size()==0) {
				txtf7.setText("Pas de résultats!!");
			}
			transaction.commit();//validation de la transaction
			em.close();//fermeture du flux
	}
	/**
	 * méthode de recherche par nom de l'équipe à domicile dans la table des tirs au but.
	 */
	public void rechercheHome() {
		//requete pour recherche dans mysql
		List<Shootout> list;
		Iterator<Shootout> iter = null;
		String ligne = null;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("config1");//Foot dans persistence.xm
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
			transaction.begin();//ouverture de la transaction
			Query query = em.createQuery("SELECT sh FROM Shootout sh WHERE sh.homeTeam= :hometeam");
			query.setParameter("hometeam",txtf2.getText().toString());
			list = query.getResultList();
			Shootout SH = new Shootout();
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
				String[] str = list.toString().split(",");
				if(str[0].toString().contains("[")==true) {
					str[0] = str[0].toString().replace("[","");
				}
				if(str[5].toString().contains("]")==true) {
					str[5] = str[5].toString().replace("]","");
				}
				SH.setDate(LocalDate.parse(str[1].toString()));
				SH.setHomeTeam(str[2].toString());
				SH.setAwayTeam(str[3].toString());
				SH.setWinner(str[4].toString());
				SH.setFirstShooter(str[5]);
				SH.setId(Integer.parseInt(str[0].toString()));
				txtf1.setText(SH.getDate().toString());
				txtf2.setText(SH.getHomeTeam());
				txtf3.setText(SH.getAwayTeam());
				txtf4.setText(SH.getWinner());
				txtf5.setText(SH.getFirstShooter());
				txtf6.setText(""+SH.getId());
			}
			else if(list.size()==0) {
				txtf7.setText("Pas de résultats!!");
			}
			transaction.commit();//validation de la transaction
			em.close();//fermeture du flux
	}
	/**
	 * méthode de recherche par nom de l'équipe invitée dans la table des tirs au but.
	 */
	public void rechercheAway() {
		//requete pour recherche dans mysql
		List<Shootout> list;
		Iterator<Shootout> iter = null;
		String ligne = null;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("config1");//Foot dans persistence.xm
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
			transaction.begin();//ouverture de la transaction
			Query query = em.createQuery("SELECT sh FROM Shootout sh WHERE sh.awayTeam= :awayteam");
			query.setParameter("awayteam",txtf3.getText().toString());
			list = query.getResultList();
			Shootout SH = new Shootout();
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
				String[] str = list.toString().split(",");
				if(str[0].toString().contains("[")==true) {
					str[0] = str[0].toString().replace("[","");
				}
				if(str[5].toString().contains("]")==true) {
					str[5] = str[5].toString().replace("]","");
				}
				SH.setDate(LocalDate.parse(str[1].toString()));
				SH.setHomeTeam(str[2].toString());
				SH.setAwayTeam(str[3].toString());
				SH.setWinner(str[4].toString());
				SH.setFirstShooter(str[5]);
				SH.setId(Integer.parseInt(str[0].toString()));
				txtf1.setText(SH.getDate().toString());
				txtf2.setText(SH.getHomeTeam());
				txtf3.setText(SH.getAwayTeam());
				txtf4.setText(SH.getWinner());
				txtf5.setText(SH.getFirstShooter());
				txtf6.setText(""+SH.getId());
			}
			else if(list.size()==0) {
				txtf7.setText("Pas de résultats!!");
			}
			transaction.commit();//validation de la transaction
			em.close();//fermeture du flux
	}
	/**
	 * méthode de recherche par nom de tournois dans la table des tirs au but.
	 */
	public void rechercheVainqueur() {
		//requete pour recherche dans mysql
		List<Shootout> list;
		Iterator<Shootout> iter = null;
		String ligne = null;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("config1");//Foot dans persistence.xm
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
			transaction.begin();//ouverture de la transaction
			Query query = em.createQuery("SELECT sh FROM Shootout sh WHERE sh.winner= :winner");
			query.setParameter("winner",txtf4.getText().toString());
			list = query.getResultList();
			Shootout SH = new Shootout();
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
				String[] str = list.toString().split(",");
				if(str[0].toString().contains("[")==true) {
					str[0] = str[0].toString().replace("[","");
				}
				if(str[5].toString().contains("]")==true) {
					str[5] = str[5].toString().replace("]","");
				}
				SH.setDate(LocalDate.parse(str[1].toString()));
				SH.setHomeTeam(str[2].toString());
				SH.setAwayTeam(str[3].toString());
				SH.setWinner(str[4].toString());
				SH.setFirstShooter(str[5].toString());
				SH.setId(Integer.parseInt(""+str[0].toString()));
				txtf1.setText(SH.getDate().toString());
				txtf2.setText(SH.getHomeTeam().toString());
				txtf3.setText(SH.getAwayTeam().toString());
				txtf4.setText(SH.getWinner().toString());
				txtf5.setText(SH.getFirstShooter().toString());
				txtf6.setText(""+SH.getId());
				
			}
			else if(list.size()==0) {
				txtf7.setText("Pas de résultats!!");
			}
			transaction.commit();//validation de la transaction
			em.close();//fermeture du flux
	}
	/**
	 * méthode de recherche par identifiant id.
	 */
	public void rechercheId() {
		List<Shootout> list;
		Iterator<Shootout> iter = null;
		String ligne = null;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("config1");//Foot dans persistence.xm
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
			transaction.begin();//ouverture de la transaction
			Query query = em.createQuery("SELECT sh FROM Shootout sh WHERE sh.id= :id");
			query.setParameter("id",(Integer.parseInt(txtf6.getText().toString())));
			list = query.getResultList();
			Shootout SH = new Shootout();
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
				String[] str = list.toString().split(",");
				if(str[0].toString().contains("[")==true) {
					str[0] = str[0].toString().replace("[","");
				}
				if(str[5].toString().contains("]")==true) {
					str[5] = str[5].toString().replace("]","");
				}
				SH.setDate(LocalDate.parse(str[1].toString()));
				SH.setHomeTeam(str[2].toString());
				SH.setAwayTeam(str[3].toString());
				SH.setWinner(str[4].toString());
				SH.setFirstShooter(str[5].toString());
				SH.setId(Integer.parseInt(str[0].toString()));
				txtf1.setText(SH.getDate().toString());
				txtf2.setText(SH.getHomeTeam());
				txtf3.setText(SH.getAwayTeam());
				txtf4.setText(SH.getWinner());
				txtf5.setText(SH.getFirstShooter());
				txtf6.setText(""+SH.getId());
			}
			else if(list.size()==0) {
				txtf7.setText("Pas de résultats!!");
			}
			transaction.commit();//validation de la transaction
			em.close();//fermeture du flux
	}
	/**
	 * Méthode pour implémenter la requete de modification 
	 * d'une entrée dans la table shoutouts.
	 */
	public void supprimer() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("config1");//Foot dans persistence.xm
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
			transaction.begin();//ouverture de la transaction
			Query query = em.createQuery("DELETE FROM Shootout sh WHERE sh.id= :id");
			query.setParameter("id",(Integer.parseInt(txtf6.getText().toString())));
			query.executeUpdate();
			transaction.commit();//validation de la transaction
			em.close();//fermeture du flux
	}
	//
	/**
	 * méthode main pour tester la fenetre
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SupprimerShoot SSH = new SupprimerShoot();
		SSH.setVisible(true);
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
			ma.txta.setText("Date des tirs aux buts.");
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
			Point p = this.jl6.getLocationOnScreen();
			ma = new MessageAide();
			ma.xma = p.x+25;
			ma.yma = p.y+5;
			ma.setLocation(ma.xma,ma.yma);
			ma.txta.setText("Identifiant du tir au but.");
			ma.setVisible(true);
		}
		else if(obj==jl7) {
			Point p = this.jl7.getLocationOnScreen();
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
		else if(obj==jl7) {
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
			else if(txtf4.getText().equals("")==false){
				rechercheVainqueur();
			}
			else if(txtf6.getText().equals("")==false){
				rechercheId();
			}
		}
		else if(obj==supprimer) {
			supprimer();
		}
	}

}

