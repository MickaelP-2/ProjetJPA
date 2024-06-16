package projet;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

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
 * classe pour vérifier l'existence des tables copies des fichiers .csv
 * exemple: C://xampp/mysql/data/nom_table
 */
public class VerificationBase extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//
	private Path path1, path2, path3;//pour le chemin d'acces aux copies des fichiers .csv
	String chemin = null, chemin2 = null;//chemin1=BDD,chemin2=fichier.csv
	BorderLayout bl;
	JButton fermer, verifier;//fermer ferme la fenetre, verifier pour verifier si les fichiers.csv sont copies en tables
	JTextField txtf1,txtf2,txtf3;//pour saisir le chemin racine des tables,afficher des messages
	JLabel jl1,jl2,jl3,label;
	JPanel JP1,JP2;
	GridLayout gl1;//pour disposition du panneau central
	//
	/**
	 * Constructeur de la classe VerificationBase, avec instanciation des composants.
	 */
	VerificationBase(){
		bl = new BorderLayout();
		fermer = new JButton("Fermer");
		fermer.addActionListener(this);
		verifier = new JButton("Verifier");
		verifier.addActionListener(this);
		txtf1 = new JTextField();
		txtf2 = new JTextField();
		txtf3 = new JTextField();
		jl1 = new JLabel("Chemin absolu de la BDD: ");
		jl2 = new JLabel("Chemin absolu du répertoire des fichiers.csv: ");
		jl3 = new JLabel("");
		label = new JLabel("Vérification: ");
		label.setHorizontalAlignment((int)CENTER_ALIGNMENT);
		gl1 = new GridLayout(3,2);
		JP1 = new JPanel();
		JP1.setLayout(gl1);
		JP1.add(jl1);
		JP1.add(txtf1);
		JP1.add(jl2);
		JP1.add(txtf2);
		JP1.add(jl3);
		JP1.add(txtf3);
		JP2 = new JPanel();
		JP2.add(fermer);
		JP2.add(verifier);
		this.setLayout(bl);
		this.add("North",label);
		this.add("Center",JP1);
		this.add("South",JP2);
		this.setLocation(150,150);
		this.setSize(450,150);
		this.setVisible(true);
	}
	//////////////////////////////////////////////////////
	/**
	 * methode qui permet de vérifier l'existence de la table ici:Foot
	 * en saisissant le chemin absolue de celle-ci. Permet aussi de 
	 * copier les données des fichiers .csv dans les tables.
	 */
	public void verification() {
		//verifier l'existence du repertoire et le lister si exists
		//exemple: C://xampp/mysql/data/nom_table
		//Verifier l'existance de la base de données
		//si = OK: verifier existence des tables:goalscorers,results,shootouts
		boolean res1, res2, res3, res4;
		EntityManagerFactory entityManagerFactory;//Foot dans persistence.xm
		EntityManager em;
		EntityTransaction transaction;
		HashSet<String> copieFichier;
		List<String> lines;
		Iterator<String> iter;
		int nb = 0;
		String[] copie;
		String ligne2 ="";
		String chem = txtf1.getText();//chemin de la base de données
		path1 = Paths.get(chem);
		res1 = Files.exists(path1);
		String ligne = null;
		
		if(res1==true) {
			txtf3.setText("Copie des fichiers en cours..");
			chemin2 = txtf2.getText();
			path2 = Paths.get(chemin2);
			Path pathFile = Paths.get(chemin2);//lire dans le fichier
			res2 = Files.exists(path2);//verifier l'existence du fichier
			System.out.println("res2: "+res2);
			if(res2==false) {
				txtf3.setText("Source de fichier inconnue!");
				return;
			}
			boolean isFile = Files.isRegularFile(path2);
			boolean isReadable = Files.isReadable(path2);
			String chemin3 = path2+"/results.csv";
			res3 = Files.exists(path2);
			if(res3==true) {
				//traitement copie du fichier
				pathFile = Paths.get(chemin3);
				copieFichier = new HashSet<String>();
				lines = null;
						try {
							//copie le fichier dans une liste de type string
							lines = Files.readAllLines(pathFile,StandardCharsets.UTF_8);
							copieFichier.addAll(lines);
						} 
						catch (IOException ioe) {
							// TODO Auto-generated catch block
							ioe.printStackTrace();
						}	
			iter = lines.iterator();
			iter.next();//Pour passer la premiere ligne avec le texte
			nb =1;
			copie = null;
			ligne2 ="";
				 entityManagerFactory = Persistence.createEntityManagerFactory("config1");//Foot dans persistence.xm
				 em = entityManagerFactory.createEntityManager();
				 transaction = em.getTransaction();
				//Ouverture de la transaction pour le contexte de persistence
				transaction.begin();//ouverture de la transaction
			while(iter.hasNext()) {
				ligne2 = iter.next();
				nb++;//OK
				copie = ligne2.split(",");
					Results RS = new Results();
					RS.setDate(LocalDate.parse(copie[0].toString()));//LocalDate
					RS.setHomeTeam(copie[1].toString());
					RS.setAwayTeam(copie[2].toString());
					RS.setHomeScore(Integer.parseInt(copie[3].toString()));
					RS.setAwayScore(Integer.parseInt(copie[4].toString()));
					RS.setTournois(copie[5].toString());
					RS.setNomVille(copie[6].toString());
					RS.setCountry(copie[7]);
					RS.setNeutre(Boolean.parseBoolean(copie[8].toString()));
					em.persist(RS);
			}//lecture du fichier .csv =OK
			transaction.commit();//application de la transaction
			}
			String chemin4 = path2+"/goalscorers.csv";
			res4 = Files.exists(path2);
			if(res4==true) {
				pathFile = Paths.get(chemin4);
				copieFichier = new HashSet<String>();
				lines = null;
						try {
							//copie le fichier dans une liste de type string
							lines = Files.readAllLines(pathFile,StandardCharsets.UTF_8);
							System.out.println("goalscorers : "+lines);
							copieFichier.addAll(lines);
						} 
						catch (IOException ioe) {
							// TODO Auto-generated catch block
							ioe.printStackTrace();
						}	
			iter = lines.iterator();
			iter.next();//Pour passer la premiere ligne avec le texte
			nb =1;
			copie = null;
			ligne2 ="";
			//Shootout SH = null, SH1;?????????????????????
				 entityManagerFactory = Persistence.createEntityManagerFactory("config1");//Foot dans persistence.xm
				 em = entityManagerFactory.createEntityManager();
				 transaction = em.getTransaction(); 
				//Ouverture de la transaction pour le contexte de persistence
				transaction.begin();
			while(iter.hasNext()) {
				ligne2 = iter.next();
				nb++;//OK
				copie = ligne2.split(",");
					Goalscorers GS = new Goalscorers();
					GS.setDate(LocalDate.parse(copie[0].toString()));//LocalDate
					GS.setHomeTeam(copie[1].toString());
					GS.setAwayTeam(copie[2].toString());
					GS.setTeam(copie[3].toString());
					GS.setScorer(copie[4].toString());
					GS.setMinute(copie[5].toString());
					GS.setOwnGoal(Boolean.parseBoolean(copie[6].toString()));
					GS.setPenalty(Boolean.parseBoolean(copie[7].toString()));
					em.persist(GS);
			}//lecture du fichier .csv =OK
			transaction.commit();//application de la transaction
			}
			chemin4 = chemin2+"/shootouts.csv";
			pathFile = Paths.get(chemin4);
			res2 = Files.exists(pathFile);
			if(res2 == true) {
			copieFichier = new HashSet<String>();//chemin2
			lines = null;
						try {
							//copie le fichier dans une liste de type string
							lines = Files.readAllLines(pathFile,StandardCharsets.UTF_8);
							copieFichier.addAll(lines);
						} 
						catch (IOException ioe) {
							// TODO Auto-generated catch block
							ioe.printStackTrace();
						}	
			iter = lines.iterator();
			iter.next();//Pour passer la premiere ligne avec le texte
			nb =1;
			copie = null;
			ligne2 ="";
				entityManagerFactory = Persistence.createEntityManagerFactory("config1");//pu_essai dans persistence.xm
				em = entityManagerFactory.createEntityManager();
				transaction = em.getTransaction();
				//Ouverture de la transaction pour le contexte de persistence
				transaction.begin();//ouverture de la transaction
			while(iter.hasNext()) {
				ligne2 = iter.next();
				nb++;//OK
				copie = ligne2.split(",");
				if(copie.length==4) {
					if(copie[0].equals(null)) {
						copie[0]="null";
					}
					if(copie[1].equals(null)) {
						copie[1]="null";
					}
					if(copie[2].equals(null)) {
						copie[2]="null";
					}
					if(copie[3].equals(null)) {
						copie[3]="null";
					}
				}//fin copie.length==4
				else if(copie.length==5) {
					if(copie[0].equals(null)) {
						copie[0]="null";
					}
					if(copie[1].equals(null)) {
						copie[1]="null";
					}
					if(copie[2].equals(null)) {
						copie[2]="null";
					}
					if(copie[3].equals(null)) {
						copie[3]="null";
					}
					if(copie[4].equals(null)) {
						copie[4]="null";
					}
				}//fin copie.length==5
					Shootout SH = new Shootout();
					if(copie.length==4) {
						SH.setDate(LocalDate.parse(copie[0].toString()));//LocalDate
						SH.setHomeTeam(copie[1].toString());
						SH.setAwayTeam(copie[2].toString());
						SH.setWinner(copie[3].toString());
						SH.setFirstShooter(null);
					}
					else if(copie.length==5) {
						SH.setDate(LocalDate.parse(copie[0].toString()));//LocalDate
						SH.setHomeTeam(copie[1].toString());
						SH.setAwayTeam(copie[2].toString());
						SH.setWinner(copie[3].toString());
						SH.setFirstShooter(copie[4].toString());
					}
					em.persist(SH);
			}//lecture du fichier .csv =OK
			//copie des données de .csv a BDD(Table=shootouts)
			transaction.commit();//application de la transaction
			em.close(); //fermeture de l'EntityManager
		}
		}
		else if(res1==false) {
			//????????????????????????????????
			System.out.println("res1: "+res1+"\n Base inexistante!!");
			txtf3.setText("BDD inéxistanate!!");
			return;
		}
	}//fin methode Verification()
	/////////////////////////////////////////////////////
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello!!");
		VerificationBase VB= new VerificationBase();
		VB.setVisible(true);
	}//fin main()
	/**
	 * implémentation des actions des boutons
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		Object obj = ae.getSource();
		if(obj==fermer) {
			this.setVisible(false);
			this.dispose();
		}
		else if(obj==verifier) {
			verification();
		}
	}//fin classe actionPerformed()
}//fin classe()
