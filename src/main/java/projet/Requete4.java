package projet;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
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
import jakarta.persistence.TypedQuery;

/**
 * Classe qui réalise la requete noméro 4:
 * Afficher les N meilleurs butteurs de tous les temps
 */
public class Requete4 extends JFrame implements ActionListener{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//
	private Path path;
	String chemin;
	BorderLayout bl;
	JButton executer,fermer;
	JTextField txtf1,txtf2;
	JLabel jl1,jl2,label;
	JPanel JP1,JP2;
	GridLayout gl1;
	//
	/**
	 * Constructeur de classe
	 */
	Requete4(){
		bl = new BorderLayout();
		gl1 = new GridLayout(2,2);
		executer = new JButton("Executer");
		executer.addActionListener(this);
		fermer = new JButton("Fermer");
		fermer.addActionListener(this);
		label = new JLabel("Afficher les équipes ayant gagnés le plus de match(en %).");
		label.setHorizontalAlignment((int)CENTER_ALIGNMENT);
		jl1 = new JLabel("Combien d'équipe: ");
		jl2 = new JLabel("");
		txtf1 = new JTextField();
		txtf2 = new JTextField();
		JP1 = new JPanel();
		JP1.setLayout(gl1);
		JP1.add(jl1);
		JP1.add(txtf1);
		JP1.add(jl2);
		JP1.add(txtf2);
		JP2 = new JPanel();
		JP2.add(executer);
		JP2.add(fermer);
		this.setLayout(bl);;
		this.add("North",label);
		this.add("Center",JP1);
		this.add("South",JP2);
		this.setLocation(200,200);
		this.setSize(300,150);
		this.setVisible(true);
		
	}
	/**
	 * Methode main pour tester la classe
	 * Visualiser la fenetre créée
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Requete4 req4 = new Requete4();
		req4.setVisible(true);
	}
	//
	/**
	 * Methode qui contient le code de la requete 4
	 */
	public void requeteMethode4() {
		Accueil.txta.setText(null);
		HashMap<String, Pourcents> copieFichier;//Composant HashMap types: String pour le nom de l'equipe, Pourcents pour les valeurs
		List<Results> list;//type Results
		Iterator<Results> iter;//type Results
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("config1");//Foot dans persistence.xm
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
			//Ouverture de la transaction pour le contexte de persistence
			transaction.begin();//ouverture de la transaction
			//Lecture de la table et copie dans un hashset-> cumul de buts si deja existants
			Query query = em.createQuery("SELECT rs FROM Results rs");
			//selectionner dans Result les equipes
			list = query.getResultList();
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
			//Lecture de copieFichier
			//declaration d'un objet de type List<Pourcents>
			List<Pourcents> list2 = new ArrayList<Pourcents>();
			//iterator sur les cles de type string du HashMap
			Iterator<String> iterKey = copieFichier.keySet().iterator();
			//iterator sur les valeurs de type Pourcents du HashMap
			Iterator<Pourcents> iterValue = copieFichier.values().iterator();
			//declaration d'un objet de type List<Pourcents>
			List<Pourcents> list3 = new ArrayList<Pourcents>();
			Pourcents pour3 = new Pourcents();
			String key = null;
			Pourcents pour = new Pourcents();//declaration d'une instance de Pourcents
			DecimalFormat format = new DecimalFormat("##0.00");//formatage pour les attributs de type double(2 chiffres apres la virgule)
			int i = 0;
			//Pour nb a afficher utiliser var defaut pour tous afficher!!
			while(iterKey.hasNext() && iterValue.hasNext()) {
				i++;
				key= iterKey.next();//iterator sur les cles du HashMap
				pour = iterValue.next();//iterator sur les valeurs du HashMap
				//calcul des pourcentages d'une instance de ourcents
				pour.setPourcentageVictoires(pour.pourcentageVictoire(pour.getTotal(),pour.getVictoires()));
				pour.setPourcentageDefaites(pour.pourcentageVictoire(pour.getTotal(),pour.getDefaites()));
				pour.setPourcentageNuls(pour.pourcentageVictoire(pour.getTotal(),pour.getNuls()));
				Object obj1 = format.format(pour.getPourcentageVictoires());
				if(obj1.toString().contains(",")==true) {
					obj1 = obj1.toString().replace(',','.');//pour remplacer la , par .-> notation anglo-saxonne
				}
				Object obj2 = format.format(pour.getPourcentageDefaites());
				if(obj2.toString().contains(",")==true) {
					obj2 = obj2.toString().replace(',','.');//pour remplacer la , par .-> notation anglo-saxonne
				}
				Object obj3 = format.format(pour.getPourcentageNuls());
				if(obj3.toString().contains(",")==true) {
					obj3 = obj3.toString().replace(',','.');//pour remplacer la , par .-> notation anglo-saxonne
				}
				//affectation des pourcentages d'une instance de Pourcents
				pour.setPourcentageVictoires(Double.parseDouble(obj1.toString()));//obj1.toString()
				pour.setPourcentageDefaites(Double.parseDouble(obj2.toString()));
				pour.setPourcentageNuls(Double.parseDouble(obj3.toString()));
				pour3 = new Pourcents(key,pour.getVictoires(),pour.getDefaites(),pour.getNuls(),pour.getTotal(),pour.getPourcentageVictoires(),pour.getPourcentageDefaites(),pour.getPourcentageNuls());
				list3.add(pour3);
			}
			//Utiliser la transformation de Pourcents(tri) et l'associer a 
			Collections.sort(list3, Pourcents.comparateurPourcentageVictoire);//ordre alphabetic des noms
			String[] tab = list3.toString().split("],");//=OK pour separation lignes
			//verification du nombre d'equipes a afficher
			int nb = 0;
			try {
				nb = Integer.parseInt(txtf1.getText());
			}
			catch(Exception e) {
				//gestion de l'exception et affectation de 
				System.err.println("??: "+e.toString());
				nb = tab.length;
			}
			if(nb>tab.length) {
				nb = tab.length;//changement de valeur si le nombre disponible est inferieur a celui demandé
			}
			Accueil.txta.setText("Equipe(s) : \n");
			for(int j=0;j<nb;j++) { 
				
				if(tab[j].toString().contains("[")==true) {
					tab[j] = tab[j].toString().replace("[","");
				}
				else if(tab[j].toString().contains("]")==true) {
					tab[j] = tab[j].toString().replace("]","");
				}
				String[] tab2 = tab[j].toString().split(",");
				//affichage des resultats dans un composant TextArea+ScrollPane(Vertical+Horizintal
				Accueil.txta.append((j+1)+": "+tab2[0].toString()+": "+tab2[5].toString()+" % victoire en "+tab2[4].toString()+" matchs.\n");
			}
	}
	//
	/**
	 * Methode qui implemente les actions des boutons.
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		Object obj = ae.getSource();
		if(obj == executer) {
			txtf2.setText("");
			if(txtf1.getText().equals("")==true) {
				txtf2.setText("Saisir le nombre d'equipes à afficher!!");
				return;
			}
			requeteMethode4();
		}
		else if(obj == fermer) {
			this.setVisible(false);
			this.dispose();
		}
	}

}
