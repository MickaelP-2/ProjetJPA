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
import utils.MessageAide;

//attributs: date, homeTeam, awayTeam, Team, scorer, minute, ownGoal, Penalty
	/*table = goalscorers
	 * private int id;
	private LocalDate date;
	private String homeTeam;
	private String awayTeam;
	private String team;
	private String scorer;
	private String minute;
	private boolean ownGoal;
	private boolean penalty;
	 */
public class ModifierScore extends JFrame implements ActionListener,MouseListener{

	private static final long serialVersionUID = 1L;
	//
	/**
	 * Attributs de la fenetre de modification de scores.
	 * Composants swing, classes.
	 */
	BorderLayout bl;
	GridLayout gl1,gl2,gl3;
	JPanel JP1,JP2,JP3,JP4;
	JLabel label, lab1,lab2,lab3,lab4,lab5,lab6,lab7,lab8,lab9,lab10,jl1,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9,jl10;
	JTextField txtf1,txtf2,txtf3,txtf4,txtf5,txtf6,txtf7,txtf8,txtf9,txtf10;
	JButton retour, effacer,rechercher,modifier; 
	Scores SC;//pour importer la classe
	MessageAide ma;
	FenetreAffichage FA;
	Goalscorers GSmodif ;
	//
	/**
	 * Constructeur de la fenetre de modification d'un score de buteur.
	 */
	public ModifierScore() {
		//constructeur
		bl = new BorderLayout();
		gl1 = new GridLayout(10,1);
		gl2 = new GridLayout(10,1);
		gl3 = new GridLayout(10,1);
		JP1 = new JPanel();
		JP2 = new JPanel();
		JP3 = new JPanel();
		JP4 = new JPanel();
		label = new JLabel("Modification de scores de buteurs.");
		label.setHorizontalAlignment((int)CENTER_ALIGNMENT);
		lab1 = new JLabel(" Date: ");
		lab2 = new JLabel(" Equipe home: ");
		lab3 = new JLabel(" Equipe away: ");
		lab4 = new JLabel(" Equipe(du buteur): ");
		lab5 = new JLabel(" Nom du joueur: ");
		lab6 = new JLabel(" Minute(du but): ");
		lab7 = new JLabel(" Contre son camp: ");
		lab8 = new JLabel(" Penalty ");
		lab9 = new JLabel(" Id: ");
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
		rechercher = new JButton("Rechercher");
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
	 * Méthode main pour tester la fenetre de modification
	 *  d'un but d'un joueur et ses composants. 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			ModifierScore MS = new ModifierScore();
			MS.setVisible(true);;
	}
	//
	/**
	 * Méthode pour effacer les champs des formulaires.
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
	 * méthode de recherche par date de résultats  dans la table des scores.
	 */
	public void rechercheDate() {
		//requete pour recherche dans mysql
		List<Goalscorers> list;
		Iterator<Goalscorers> iter = null;
		String ligne = null;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("config1");//Foot dans persistence.xm
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
			transaction.begin();//ouverture de la transaction
			Query query = em.createQuery("SELECT gs FROM Goalscorers gs WHERE gs.date= :date");
			query.setParameter("date",LocalDate.parse(txtf1.getText().toString()));
			list = query.getResultList();
			Goalscorers GS = new Goalscorers();
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
				GSmodif = new Goalscorers();
				String[] str = list.toString().split(",");
				if(str[0].toString().contains("[")==true) {
					str[0] = str[0].toString().replace("[","");
				}
				if(str[8].toString().contains("]")==true) {
					str[8] = str[8].toString().replace("]","");
				}
				GS.setDate(LocalDate.parse(str[1].toString()));
				GS.setHomeTeam(str[2].toString());
				GS.setAwayTeam(str[3].toString());
				GS.setTeam(str[4].toString());
				GS.setScorer(str[5].toString());
				GS.setMinute(str[6].toString());
				GS.setOwnGoal(Boolean.parseBoolean(str[7].toString()));
				GS.setPenalty(Boolean.parseBoolean(str[8].toString()));
				GS.setId(Integer.parseInt(str[0].toString()));
				//
				GSmodif.setId(Integer.parseInt(str[0].toString()));
				GSmodif.setDate(LocalDate.parse(str[1].toString()));
				GSmodif.setHomeTeam(str[2].toString());
				GSmodif.setAwayTeam(str[3].toString());
				GSmodif.setTeam(str[4].toString());
				GSmodif.setScorer(str[5].toString());
				GSmodif.setMinute(str[6].toString());
				GSmodif.setOwnGoal(Boolean.parseBoolean(str[7].toString()));
				GSmodif.setPenalty(Boolean.parseBoolean(str[8].toString()));
				//
				txtf1.setText(GS.getDate().toString());
				txtf2.setText(GS.getHomeTeam());
				txtf3.setText(GS.getAwayTeam());
				txtf4.setText(GS.getTeam());
				txtf5.setText(GS.getScorer());
				txtf6.setText(GS.getMinute());
				txtf7.setText(""+GS.isOwnGoal());
				txtf8.setText(""+GS.isPenalty());
				txtf9.setText(""+GS.getId());
			}
			else if(list.size()==0) {
				txtf10.setText("Pas de résultats!!");
			}
			transaction.commit();//validation de la transaction
			em.close();//fermeture du flux
	}
	/**
	 * méthode de recherche par nom de l'équipe à domicile dans la table des scores.
	 */
	public void rechercheHome() {
		//requete pour recherche dans mysql
		List<Goalscorers> list;
		Iterator<Goalscorers> iter = null;
		String ligne = null;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("config1");//Foot dans persistence.xm
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
			transaction.begin();//ouverture de la transaction
			Query query = em.createQuery("SELECT gs FROM Goalscorers gs WHERE gs.homeTeam= :hometeam");
			query.setParameter("hometeam",txtf2.getText().toString());
			list = query.getResultList();
			Goalscorers GS = new Goalscorers();
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
				GSmodif = new Goalscorers();
				String[] str = list.toString().split(",");
				if(str[0].toString().contains("[")==true) {
					str[0] = str[0].toString().replace("[","");
				}
				if(str[8].toString().contains("]")==true) {
					str[8] = str[8].toString().replace("]","");
				}
				GS.setDate(LocalDate.parse(str[1].toString()));
				GS.setHomeTeam(str[2].toString());
				GS.setAwayTeam(str[3].toString());
				GS.setTeam(str[4].toString());
				GS.setScorer(str[5]);
				GS.setMinute(str[6].toString());
				GS.setOwnGoal(Boolean.parseBoolean(str[7].toString()));
				GS.setPenalty(Boolean.parseBoolean(str[8].toString()));
				GS.setId(Integer.parseInt(str[0].toString()));
				//
				GSmodif.setId(Integer.parseInt(str[0].toString()));
				GSmodif.setDate(LocalDate.parse(str[1].toString()));
				GSmodif.setHomeTeam(str[2].toString());
				GSmodif.setAwayTeam(str[3].toString());
				GSmodif.setTeam(str[4].toString());
				GSmodif.setScorer(str[5].toString());
				GSmodif.setMinute(str[6].toString());
				GSmodif.setOwnGoal(Boolean.parseBoolean(str[7].toString()));
				GSmodif.setPenalty(Boolean.parseBoolean(str[8].toString()));
				//
				txtf1.setText(GS.getDate().toString());
				txtf2.setText(GS.getHomeTeam());
				txtf3.setText(GS.getAwayTeam());
				txtf4.setText(GS.getTeam());
				txtf5.setText(GS.getScorer());
				txtf6.setText(GS.getMinute());
				txtf7.setText(""+GS.isOwnGoal());
				txtf8.setText(""+GS.isPenalty());
				txtf9.setText(""+GS.getId());
			}
			else if(list.size()==0) {
				txtf10.setText("Pas de résultats!!");
			}
			transaction.commit();//validation de la transaction
			em.close();//fermeture du flux
	}
	/**
	 * méthode de recherche par nom de l'équipe invitée dans la table des scores.
	 */
	public void rechercheAway() {
		//requete pour recherche dans mysql
		List<Goalscorers> list;
		Iterator<Goalscorers> iter = null;
		String ligne = null;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("config1");//Foot dans persistence.xm
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
			transaction.begin();//ouverture de la transaction
			Query query = em.createQuery("SELECT gs FROM Goalscorers gs WHERE gs.awayTeam= :awayteam");
			query.setParameter("awayteam",txtf3.getText().toString());
			list = query.getResultList();
			Goalscorers GS = new Goalscorers();
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
				GSmodif = new Goalscorers();
				String[] str = list.toString().split(",");
				if(str[0].toString().contains("[")==true) {
					str[0] = str[0].toString().replace("[","");
				}
				if(str[8].toString().contains("]")==true) {
					str[8] = str[8].toString().replace("]","");
				}
				GS.setDate(LocalDate.parse(str[1].toString()));
				GS.setHomeTeam(str[2].toString());
				GS.setAwayTeam(str[3].toString());
				GS.setTeam(str[4].toString());
				GS.setScorer(str[5]);
				GS.setMinute(str[6].toString());
				GS.setOwnGoal(Boolean.parseBoolean(str[7].toString()));
				GS.setPenalty(Boolean.parseBoolean(str[8].toString()));
				GS.setId(Integer.parseInt(str[0].toString()));
				//
				GSmodif.setId(Integer.parseInt(str[0].toString()));
				GSmodif.setDate(LocalDate.parse(str[1].toString()));
				GSmodif.setHomeTeam(str[2].toString());
				GSmodif.setAwayTeam(str[3].toString());
				GSmodif.setTeam(str[4].toString());
				GSmodif.setScorer(str[5].toString());
				GSmodif.setMinute(str[6].toString());
				GSmodif.setOwnGoal(Boolean.parseBoolean(str[7].toString()));
				GSmodif.setPenalty(Boolean.parseBoolean(str[8].toString()));
				//
				txtf1.setText(GS.getDate().toString());
				txtf2.setText(GS.getHomeTeam());
				txtf3.setText(GS.getAwayTeam());
				txtf4.setText(GS.getTeam());
				txtf5.setText(GS.getScorer());
				txtf6.setText(GS.getMinute());
				txtf7.setText(""+GS.isOwnGoal());
				txtf8.setText(""+GS.isPenalty());
				txtf9.setText(""+GS.getId());
			}
			else if(list.size()==0) {
				txtf10.setText("Pas de résultats!!");
			}
			transaction.commit();//validation de la transaction
			em.close();//fermeture du flux
	}
	/**
	 * méthode de recherche par nom de tournois dans la table des scores.
	 */
	public void rechercheJoueur() {
		//requete pour recherche dans mysql
		List<Goalscorers> list;
		Iterator<Goalscorers> iter = null;
		String ligne = null;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("config1");//Foot dans persistence.xm
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
			transaction.begin();//ouverture de la transaction
			Query query = em.createQuery("SELECT gs FROM Goalscorers gs WHERE gs.scorer= :scorer");
			query.setParameter("scorer",txtf5.getText().toString());
			list = query.getResultList();
			Goalscorers GS = new Goalscorers();
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
				GSmodif = new Goalscorers();
				String[] str = list.toString().split(",");
				if(str[0].toString().contains("[")==true) {
					str[0] = str[0].toString().replace("[","");
				}
				if(str[8].toString().contains("]")==true) {
					str[8] = str[8].toString().replace("]","");
				}
				GS.setDate(LocalDate.parse(str[1].toString()));
				GS.setHomeTeam(str[2].toString());
				GS.setAwayTeam(str[3].toString());
				GS.setTeam(str[4].toString());
				GS.setScorer(str[5].toString());
				GS.setMinute(str[6].toString());
				GS.setOwnGoal(Boolean.parseBoolean(str[7].toString()));
				GS.setPenalty(Boolean.parseBoolean(str[8].toString()));
				GS.setId(Integer.parseInt(str[0].toString()));
				//
				GSmodif.setId(Integer.parseInt(str[0].toString()));
				GSmodif.setDate(LocalDate.parse(str[1].toString()));
				GSmodif.setHomeTeam(str[2].toString());
				GSmodif.setAwayTeam(str[3].toString());
				GSmodif.setTeam(str[4].toString());
				GSmodif.setScorer(str[5].toString());
				GSmodif.setMinute(str[6].toString());
				GSmodif.setOwnGoal(Boolean.parseBoolean(str[7].toString()));
				GSmodif.setPenalty(Boolean.parseBoolean(str[8].toString()));
				//
				txtf1.setText(GS.getDate().toString());
				txtf2.setText(GS.getHomeTeam().toString());
				txtf3.setText(GS.getAwayTeam().toString());
				txtf4.setText(GS.getTeam().toString());
				txtf5.setText(GS.getScorer().toString());
				txtf6.setText(GS.getMinute().toString());
				txtf7.setText(""+GS.isOwnGoal());
				txtf8.setText(""+GS.isPenalty());
				txtf9.setText(""+GS.getId());
			}
			else if(list.size()==0) {
				txtf10.setText("Pas de résultats!!");
			}
			transaction.commit();//validation de la transaction
			em.close();//fermeture du flux
	}
	/**
	 * méthode de recherche par identifiant id.
	 */
	public void rechercheId() {
		List<Goalscorers> list;
		Iterator<Goalscorers> iter = null;
		String ligne = null;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("config1");//Foot dans persistence.xm
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
			transaction.begin();//ouverture de la transaction
			Query query = em.createQuery("SELECT gs FROM Goalscorers gs WHERE gs.id= :id");
			query.setParameter("id",(Integer.parseInt(txtf9.getText().toString())));
			list = query.getResultList();
			Goalscorers GS = new Goalscorers();
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
				GSmodif = new Goalscorers();
				String[] str = list.toString().split(",");
				if(str[0].toString().contains("[")==true) {
					str[0] = str[0].toString().replace("[","");
				}
				if(str[8].toString().contains("]")==true) {
					str[8] = str[8].toString().replace("]","");
				}
				GS.setDate(LocalDate.parse(str[1].toString()));
				GS.setHomeTeam(str[2].toString());
				GS.setAwayTeam(str[3].toString());
				GS.setTeam(str[4].toString());
				GS.setScorer(str[5]);
				GS.setMinute(str[6].toString());
				GS.setOwnGoal(Boolean.parseBoolean(str[7].toString()));
				GS.setPenalty(Boolean.parseBoolean(str[8].toString()));
				GS.setId(Integer.parseInt(str[0].toString()));
				//
				GSmodif.setId(Integer.parseInt(str[0].toString()));
				GSmodif.setDate(LocalDate.parse(str[1].toString()));
				GSmodif.setHomeTeam(str[2].toString());
				GSmodif.setAwayTeam(str[3].toString());
				GSmodif.setTeam(str[4].toString());
				GSmodif.setScorer(str[5].toString());
				GSmodif.setMinute(str[6].toString());
				GSmodif.setOwnGoal(Boolean.parseBoolean(str[7].toString()));
				GSmodif.setPenalty(Boolean.parseBoolean(str[8].toString()));
				//
				txtf1.setText(GS.getDate().toString());
				txtf2.setText(GS.getHomeTeam());
				txtf3.setText(GS.getAwayTeam());
				txtf4.setText(GS.getTeam());
				txtf5.setText(GS.getScorer());
				txtf6.setText(GS.getMinute());
				txtf7.setText(""+GS.isOwnGoal());
				txtf8.setText(""+GS.isPenalty());
				txtf9.setText(""+GS.getId());
			}
			else if(list.size()==0) {
				txtf10.setText("Pas de résultats!!");
			}
			transaction.commit();//validation de la transaction
			em.close();//fermeture du flux
	}
	/**
	 * Méthode pour implémenter la requete de modification 
	 * d'une entrée dans la table goalscorers.
	 */
	public void modifier() {
		System.out.println("modifier score");
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("config1");//Foot dans persistence.xm
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
			transaction.begin();//ouverture de la transaction
			//
			Query query = em.createQuery("UPDATE Goalscorers gs SET gs.date= :newdate  WHERE gs.date= :olddate");
			query.setParameter("newdate",(LocalDate.parse(txtf1.getText())));
			query.setParameter("olddate",(LocalDate.parse(GSmodif.getDate().toString())));
			query.executeUpdate();
			query = em.createQuery("UPDATE Goalscorers gs SET gs.homeTeam= :newhomeTeam  WHERE gs.homeTeam= :oldhomeTeam");
			query.setParameter("newhomeTeam",(txtf2.getText()));
			query.setParameter("oldhomeTeam",GSmodif.getHomeTeam().toString());
			query.executeUpdate();
			query = em.createQuery("UPDATE Goalscorers gs SET gs.awayTeam= :newawayTeam  WHERE gs.awayTeam= :oldawayTeam");
			query.setParameter("newawayTeam",(txtf3.getText()));
			query.setParameter("oldawayTeam",GSmodif.getAwayTeam().toString());
			query.executeUpdate();
			query = em.createQuery("UPDATE Goalscorers gs SET gs.team= :newTeam  WHERE gs.team= :oldTeam");
			query.setParameter("newTeam",(txtf4.getText()));
			query.setParameter("oldTeam",GSmodif.getTeam());
			query.executeUpdate();
			query = em.createQuery("UPDATE Goalscorers gs SET gs.scorer= :newScorer  WHERE gs.scorer= :oldScorer");
			query.setParameter("newScorer",(txtf5.getText()));
			query.setParameter("oldScorer",GSmodif.getScorer());
			query.executeUpdate();
			query = em.createQuery("UPDATE Goalscorers gs SET gs.minute= :newMinute  WHERE gs.minute= :oldMinute");
			query.setParameter("newMinute",(txtf6.getText()));
			query.setParameter("oldMinute",GSmodif.getMinute());
			query.executeUpdate();
			query = em.createQuery("UPDATE Goalscorers gs SET gs.ownGoal= :newownGoal  WHERE gs.ownGoal= :oldownGoal");
			query.setParameter("newownGoal",(Boolean.parseBoolean(txtf7.getText().toString())));
			query.setParameter("oldownGoal",(GSmodif.isOwnGoal()));
			query.executeUpdate();
			query = em.createQuery("UPDATE Goalscorers gs SET gs.penalty= :newpenalty  WHERE gs.penalty= :oldpenalty");
			query.setParameter("newpenalty",(Boolean.parseBoolean(txtf8.getText().toString())));
			query.setParameter("oldpenalty",(GSmodif.isPenalty()));
			query.executeUpdate();
			//
			transaction.commit();//validation de la transaction
			em.close();//fermeture du flux
	}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	/**
	 * Méthode de gestion du survol d'un composant par le 
	 * pointeur de la souris.Affiche un message d'aide.
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
			ma.txta.setText("Nom de l'équipe locale.");
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
			ma.txta.setText("Nom de l'équipe qui marque.");
			ma.setVisible(true);
		}
		else if(obj==jl5) {
			Point p = this.jl5.getLocationOnScreen();
			ma = new MessageAide();
			ma.xma = p.x+25;
			ma.yma = p.y+5;
			ma.setLocation(ma.xma,ma.yma);
			ma.txta.setText("Nom du joueur qui marque.");
			ma.setVisible(true);
		}
		else if(obj==jl6) {
			Point p = this.jl6.getLocationOnScreen();
			ma = new MessageAide();
			ma.xma = p.x+25;
			ma.yma = p.y+5;
			ma.setLocation(ma.xma,ma.yma);
			ma.txta.setText("Minute ou le but est marqué..");
			ma.setVisible(true);
		}
		else if(obj==jl7) {
			Point p = this.jl7.getLocationOnScreen();
			ma = new MessageAide();
			ma.xma = p.x+25;
			ma.yma = p.y+5;
			ma.setLocation(ma.xma,ma.yma);
			ma.txta.setText("But marqué contre son camp?(oui/non).");
			ma.setVisible(true);
		}
		else if(obj==jl8) {
			Point p = this.jl8.getLocationOnScreen();
			ma = new MessageAide();
			ma.xma = p.x+25;
			ma.yma = p.y+5;
			ma.setLocation(ma.xma,ma.yma);
			ma.txta.setText("But marqué sur penalty?(oui/non)");
			ma.setVisible(true);
		}
		else if(obj==jl9) {
			Point p = this.jl9.getLocationOnScreen();
			ma = new MessageAide();
			ma.xma = p.x+25;
			ma.yma = p.y+5;
			ma.setLocation(ma.xma,ma.yma);
			ma.txta.setText("identifiant du score.");
			ma.setVisible(true);
		}
		else if(obj==jl10) {
			Point p = this.jl10.getLocationOnScreen();
			ma = new MessageAide();
			ma.xma = p.x+25;
			ma.yma = p.y+5;
			ma.setLocation(ma.xma,ma.yma);
			ma.txta.setText("Affiche des messages durant l'utilisation du logiciel.");
			ma.setVisible(true);
		}
	}
	/**
	 * Méthode pour restituer la mémoire utilisée par la fenetre
	 * de l'aide intégrée.Masque la fenetre affichée.
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
	 * Méthode de gestion des actions des boutons de la fenetre.
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
				rechercheJoueur();
			}
			else if(txtf9.getText().equals("")==false){
				rechercheId();
			}
		}
		else if(obj==modifier) {
			modifier();
		}
	}

}
