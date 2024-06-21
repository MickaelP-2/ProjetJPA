package projet;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.util.HashMap;
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

/**
 * Classe qui réalise la requete noméro 5:
 * Afficher les N meilleurs butteurs de tous les temps
 */
public class Requete5 extends JFrame implements ActionListener{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//
	private Path path;
	String chemin;
	BorderLayout bl;
	JButton executer,fermer;
	JTextField txtf1,txtf2,txtf3;
	JLabel jl1,jl2,jl3,label;
	JPanel JP1,JP2;
	GridLayout gl1;
	//
	/**
	 * Constructeur de classe
	 */
	Requete5(){
		bl = new BorderLayout();
		gl1 = new GridLayout(3,2);
		executer = new JButton("Executer");
		executer.addActionListener(this);
		fermer = new JButton("Fermer");
		fermer.addActionListener(this);
		label = new JLabel("Afficher les matchs entre deux équipes.");
		label.setHorizontalAlignment((int)CENTER_ALIGNMENT);
		jl1 = new JLabel("Equipe 1: ");
		jl2 = new JLabel("Equipe 2: ");
		jl3 = new JLabel();
		txtf1 = new JTextField();
		txtf2 = new JTextField();
		txtf3 = new JTextField();
		JP1 = new JPanel();
		JP1.setLayout(gl1);
		JP1.add(jl1);
		JP1.add(txtf1);
		JP1.add(jl2);
		JP1.add(txtf2);
		JP1.add(jl3);
		JP1.add(txtf3);
		JP2 = new JPanel();
		JP2.add(executer);
		JP2.add(fermer);
		this.setLayout(bl);;
		this.add("North",label);
		this.add("Center",JP1);
		this.add("South",JP2);
		this.setLocation(200,200);
		this.setSize(300,175);
		this.setVisible(true);
		
	}
	/**
	 * Methode pour tester la classe
	 * Visualiser la fenetre créée
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Requete5 req5 = new Requete5();
		req5.setVisible(true);
	}
	//
	/**
	 * Methode qui implemente la requete 5
	 */
	public void requeteMethode5() {
		System.out.println("req5!!");
		Accueil.txta.setText(null);
		String equipe1 = null;
		String equipe2 = null;
		if(txtf1.getText().equals("")==true || txtf2.getText().equals("")==true) {
			//test pour verfiier les valeurs des 2 equipes
			txtf3.setText("Nommer les 2 equipes.");
			return;
		}
		else {
			equipe1 = txtf1.getText();
			equipe2 = txtf2.getText();
		}
		System.out.println("Equipes: "+equipe1+" "+equipe2);//OK
		//recherche dans la base les matchs entre les deux equipes et copie dans un HashMap
		HashMap<String, Pourcents> copieFichier;//Composant HashMap types: String pour le nom de l'equipe, Pourcents pour les valeurs
		List<Results> list;//type Results
		Iterator<Results> iter;//type results
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("config1");//Foot dans persistence.xm
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
			//Ouverture de la transaction pour le contexte de persistence
			transaction.begin();//ouverture de la transaction
			//Lecture de la table et copie dans un hashset-> cumul de buts si deja existants
			Query query = em.createQuery("SELECT rs FROM Results rs WHERE (rs.homeTeam = :equipe1 OR rs.homeTeam = :equipe2) AND (rs.awayTeam = :equipe1 OR rs.awayTeam = :equipe2)");
			query.setParameter("equipe1", equipe1);
			query.setParameter("equipe2", equipe2);
			//selectionner dans Result les equipes
			list = query.getResultList();
			System.out.println("list??: "+list.size()+"\n "+list);//OK
			int nbBut = 0;
			String equipe = null;
			int nbMatch =0;//pour le nb total de match joués
			int victoires=0,defaites=0,nuls=0;//defaite ou victoire -> null non pris en compte
			copieFichier = new HashMap<String, Pourcents>();//nomVille, Score(away+home)
			if(list.size()>0) {
				iter = list.iterator();
				int i = 1;//pour le nb max a afficher
				while(iter.hasNext()) {//&& i<nb
					Results RS = iter.next();
					equipe = RS.getHomeTeam();
					if(copieFichier.containsKey(equipe)==true) {
						//cas ou l'equipe est dans le HashMap
						if(RS.getHomeTeam().equals(equipe)==true) {
							//pour le cas equipe=HomeTeam
							nbMatch++;
							if(RS.getHomeScore()>RS.getAwayScore()) {
								victoires++;
							}
							else if(RS.getHomeScore()<RS.getAwayScore()) {
								defaites++;
							}
							else if(RS.getHomeScore() == RS.getAwayScore()) {
								nuls++;
							}
						}
						else if(RS.getAwayTeam().equals(equipe)==true) {
							//pour le cas ou equipe=Awayteam
							nbMatch++;
							if(RS.getHomeScore()<RS.getAwayScore()) {
								victoires++;
							}
							else if(RS.getHomeScore()>RS.getAwayScore()) {
								defaites++;
							}
							else if(RS.getHomeScore() == RS.getAwayScore()) {
								nuls++;
							}
						}
						copieFichier.replace(equipe,new Pourcents(victoires,defaites,nuls,nbMatch,0.0,0.0,0.0));
				}//fin if(equipe presente
					else if(copieFichier.containsKey(equipe)==false) {
						//cas ou l'equipe n'est pas dans le HashMap
						if(RS.getHomeTeam().equals(equipe)==true) {
							//pour le cas equipe=HomeTeam
							nbMatch=0;
							victoires=0;
							defaites=0;
							nuls=0;
							nbMatch++;
							if(RS.getHomeScore()>RS.getAwayScore()) {
								victoires++;
							}
							else if(RS.getHomeScore()<RS.getAwayScore()) {
								defaites++;
							}
							else if(RS.getHomeScore() == RS.getAwayScore()) {
								nuls++;
							}
						}
						else if(RS.getAwayTeam().equals(equipe)==true) {
							//pour le cas ou equipe=Awayteam
							nbMatch=0;
							victoires=0;
							defaites=0;
							nuls=0;
							nbMatch++;
							if(RS.getHomeScore()<RS.getAwayScore()) {
								victoires++;
							}
							else if(RS.getHomeScore()>RS.getAwayScore()) {
								defaites++;
							}
							else if(RS.getHomeScore() == RS.getAwayScore()) {
								nuls++;
							}
						}
						copieFichier.put(equipe,new Pourcents(victoires,defaites,nuls,nbMatch,0.0,0.0,0.0));
					}
				}
				transaction.commit();
				em.close();
				
			}//fin if()
			else if(list.size()==0) {
				Accueil.txta.setText("Equipe inconnue!!");
			}
			//
			
	}
	//
	/**
	 * Methode pour implementer les actions des boutons
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		Object obj = ae.getSource();
		if(obj == executer) {
			txtf3.setText("");
			if(txtf1.getText().equals("")==true || txtf2.getText().equals("")==true) {
				txtf3.setText("Nommer les équipes!!");
				return;
			}
			System.out.println("JRB5: "+Accueil.JRB5.isSelected());
			requeteMethode5();
		}
		else if(obj == fermer) {
			this.setVisible(false);
			this.dispose();
		}
	}

}
