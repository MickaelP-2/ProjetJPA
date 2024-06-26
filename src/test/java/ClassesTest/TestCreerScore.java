package ClassesTest;

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
public class TestCreerScore extends JFrame implements ActionListener,MouseListener{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//
	/**
	 * Attributs de la fenetre de création de score de buteur.
	 * Composanst swing, classes.
	 */
	BorderLayout bl;
	GridLayout gl1,gl2,gl3;
	JPanel JP1,JP2,JP3,JP4;
	JLabel label, lab1,lab2,lab3,lab4,lab5,lab6,lab7,lab8,lab9,jl1,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9;
	JTextField txtf1,txtf2,txtf3,txtf4,txtf5,txtf6,txtf7,txtf8,txtf9;
	JButton retour, effacer,creer; 
	TestScores TSC;//pour importer la classe
	MessageAide ma;
	//
	/**
	 * Constructeur de la fenetre de création de score.
	 */
	public TestCreerScore() {
		//constructeur
		bl = new BorderLayout();
		gl1 = new GridLayout(10,1);
		gl2 = new GridLayout(10,1);
		gl3 = new GridLayout(10,1);
		JP1 = new JPanel();
		JP2 = new JPanel();
		JP3 = new JPanel();
		JP4 = new JPanel();
		label = new JLabel("Création/insertion de scores de buteurs.");
		label.setHorizontalAlignment((int)CENTER_ALIGNMENT);
		lab1 = new JLabel(" Date: ");
		lab2 = new JLabel(" Equipe home: ");
		lab3 = new JLabel(" Equipe away: ");
		lab4 = new JLabel(" Equipe(du buteur): ");
		lab5 = new JLabel(" Nom du joueur: ");
		lab6 = new JLabel(" Minute(du but): ");
		lab7 = new JLabel(" Contre son camp: ");
		lab8 = new JLabel(" Penalty ");
		lab9 = new JLabel("");
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
		txtf1 = new JTextField();
		txtf2 = new JTextField();
		txtf3 = new JTextField();
		txtf4 = new JTextField();
		txtf5 = new JTextField();
		txtf6 = new JTextField();
		txtf7 = new JTextField();
		txtf8 = new JTextField();
		txtf9 = new JTextField();
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
	 * Méthode main pour tester la fenetre et las composants de celle-ci.
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			TestCreerScore TCS = new TestCreerScore();
			TCS.setVisible(true);;
	}
	//
	/**
	 * Méthode pour effacer les champs de texte du formulaire.
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
	}
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
			txtf9.setText("Remplir les champs!");
		}
		try {
			 date = LocalDate.parse(txtf1.getText().toString());
		}
		catch(Exception e) {
			txtf9.setText("Vérifier la date saisie!");
			res = false;
		}
		try {
			home = Integer.parseInt(txtf4.getText());
		}
		catch(Exception e) {
			txtf9.setText("Vérifier la minute saisie!");
			res = false;
		}
		if(txtf7.getText().equals("true")==true || txtf7.getText().equals("false")==true || txtf7.getText().equals("TRUE")==true || txtf7.getText().equals("FALSE")==true) {
			res = true;
		}
		else res = false;
		
		if(txtf8.getText().equals("true")==true || txtf8.getText().equals("false")==true || txtf8.getText().equals("TRUE")==true || txtf8.getText().equals("FALSE")==true) {
			res = true;
		}
		else res = false;
		return res;
	}
	/**
	 * Méthode pour créer une entrée dans la table goalscorers.
	 */
	public void creer() {
		if(verifierDonnees()==false) {
			return;
		}
		else if(verifierDonnees()==true) {
					EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("config1");//Foot dans persistence.xm
					EntityManager em = entityManagerFactory.createEntityManager();
					EntityTransaction transaction = em.getTransaction();
					//Ouverture de la transaction pour le contexte de persistence
					transaction.begin();//ouverture de la transaction
						TestGoalscorers TGS = new TestGoalscorers();
						TGS.setDate(LocalDate.parse(txtf1.getText().toString()));//LocalDate
						TGS.setHomeTeam(txtf2.getText().toString());
						TGS.setAwayTeam(txtf3.getText().toString());
						TGS.setTeam(txtf4.getText().toString());
						TGS.setScorer(txtf5.getText().toString());
						TGS.setMinute(txtf6.getText().toString());
						TGS.setOwnGoal(Boolean.parseBoolean(txtf7.getText().toString()));
						TGS.setPenalty(Boolean.parseBoolean(txtf8.getText().toString()));
						em.persist(TGS);
					transaction.commit();//application de la transaction
					em.close();
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	/**
	 * Méthode pour gerer l'aide integrée. Au survol d'un composant JLabel,
	 * par la pointeur de la souris. Affiche un message d'aide.
	 */
	public void mouseEntered(MouseEvent me) {
		// TODO Auto-generated method stub
		Object obj = me.getSource();
		if(obj==jl1) {
			Point p = this.jl1.getLocationOnScreen();
			ma = new MessageAide();
			ma.xma = p.x+25;
			ma.yma = p.y+5;
			ma.setLocation(ma.xma,ma.yma);
			ma.txta.setText("Date de la recontre.(format ISO: YYYY-MM-DD");
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
			ma.txta.setText("But marqué contre son camp?(oui=true/non=false).");
			ma.setVisible(true);
		}
		else if(obj==jl8) {
			Point p = this.jl8.getLocationOnScreen();
			ma = new MessageAide();
			ma.xma = p.x+25;
			ma.yma = p.y+5;
			ma.setLocation(ma.xma,ma.yma);
			ma.txta.setText("But marqué sur penalty?(oui=true/non=false)");
			ma.setVisible(true);
		}
		else if(obj==jl9) {
			Point p = this.jl9.getLocationOnScreen();
			ma = new MessageAide();
			ma.xma = p.x+25;
			ma.yma = p.y+5;
			ma.setLocation(ma.xma,ma.yma);
			ma.txta.setText("Affiche des messages durant l'utilisation du logiciel");
			ma.setVisible(true);
		}
	}
	/**
	 * Méthode pour restituer la mémoire utilisée par l'aide intégrée,
	 * et masquer la fenetre.
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
	}
	/**
	 * Méthode pour implémenter les actions des différents boutons.
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		Object obj = ae.getSource();
		if(obj==retour) {
			this.setVisible(false);
			this.dispose();
			TestMenuAcces TMA = new TestMenuAcces();
			TMA.setVisible(true);
		}
		else if(obj==effacer) {
			effacer();
		}
		else if(obj==creer) {
			creer();
		}
	}

}
