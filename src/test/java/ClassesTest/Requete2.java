package ClassesTest;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
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
 * Classe qui réalise la requete noméro 2:
 * Afficher les N meilleurs butteurs de tous les temps
 */
public class Requete2 extends JFrame implements ActionListener{
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
	Requete2(){
		bl = new BorderLayout();
		gl1 = new GridLayout(3,2);
		executer = new JButton("Executer");
		executer.addActionListener(this);
		fermer = new JButton("Fermer");
		fermer.addActionListener(this);
		label = new JLabel("Afficher les meilleurs butteurs, d'une compétition.");
		label.setHorizontalAlignment((int)CENTER_ALIGNMENT);
		jl1 = new JLabel("Nombre voulu: ");
		jl2 = new JLabel("Nom de la compétition: ");
		jl3 = new JLabel("");
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
		this.setSize(300,150);
		this.setVisible(true);
		
	}
	/**
	 * Methode pour tester la classe.
	 * Visualiser la fenetre créée
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Requete2 req2 = new Requete2();
		req2.setVisible(true);
	}
	//
	/**
	 * Methode qui contient le code de la requete 2
	 */
	public void requeteMethode2() {
		//boolean res1, res2, res3, res4;
		TestAccueil.txta.setText(null);
		HashMap<String, Integer> copieFichier;//type Goalscorers
		List<TestResults> list;//type Goalscorers
		Iterator<TestResults> iter;//type Goalscorers
		int nb = Integer.parseInt(txtf1.getText());
		String competition = txtf2.getText();
		String[] copie;
		String ligne2 ="";
		String ligne = null;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("config1");//Foot dans persistence.xm
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
			//Ouverture de la transaction pour le contexte de persistence
			transaction.begin();//ouverture de la transaction
			//Lecture de la table et copie dans un hashset-> cumul de buts si deja existants
			//**Query query = em.createQuery("SELECT gs FROM Goalscorers gs WHERE gs.scorer = :nom");
			TypedQuery<TestResults> query = em.createQuery("SELECT rs FROM Results rs WHERE rs.tournois = :tournois ",TestResults.class);
			query.setParameter("tournois", competition);//type LocalDate
			list = query.getResultList();
			int nbBut = 0;
			String nomPays = null;
			copieFichier = new HashMap<String, Integer>();//nomVille, Score(away+home)
			if(list.size()>0) {
				iter = list.iterator();
				int i = 1;//pour le nb max a afficher
				while(iter.hasNext()) {//&& i<nb
					TestResults RS = iter.next();
					System.out.println(i+"->"+RS.toString());
					nomPays = RS.getHomeTeam();
					if(copieFichier.containsKey(nomPays)==false) {
						//ajouter le nom du pays et le score away/home
						if(RS.getHomeTeam().equals(nomPays)==true) {
							nbBut = RS.getHomeScore();
						}
						else if(RS.getAwayTeam().equals(nomPays)==true) {
							nbBut = RS.getAwayScore();
						}
						copieFichier.put(nomPays,nbBut);
					}
					else if(copieFichier.containsKey(nomPays)==true) {
						//changer le score away/home
						if(RS.getHomeTeam().equals(nomPays)==true) {
							nbBut +=RS.getHomeScore();
						}
						else if(RS.getAwayTeam().equals(nomPays)==true) {
							nbBut +=RS.getAwayScore();
						}
						copieFichier.replace(nomPays,nbBut);
					}
				}
				transaction.commit();
				em.close();
			}//fin if()
			else if(list.size()==0) {
				TestAccueil.txta.setText("Compétition inconnue!!");
			}
			//Lecture de copieFichier
			List<TestScores> list2 = new ArrayList<TestScores>();
			Iterator<String> iterKey = copieFichier.keySet().iterator();
			Iterator<Integer> iterValue = copieFichier.values().iterator();
			TestScores scor = null;
			
			while(iterKey.hasNext() && iterValue.hasNext()) {
				scor = new TestScores();
				scor.setStr(iterKey.next());
				scor.setNombre(Integer.parseInt(iterValue.next().toString()));
				list2.add(scor);
			}
			Collections.sort(list2, TestScores.comparateurScore);//ordre alphabetic des noms
			String[] tab = list2.toString().split(",");
			if(nb>tab.length) {
			nb=tab.length;
			}
			TestAccueil.txta.setText(competition+" : \n");
			for(int j=0;j<nb;j++) {
				if(tab[j].toString().contains("[")==true) {
					tab[j] = tab[j].toString().replace("[","");
				}
				else if(tab[j].toString().contains("]")==true) {
					tab[j] = tab[j].toString().replace("]","");
				}
				TestAccueil.txta.append((j+1)+": "+tab[j].toString()+" but(s)\n");
			}
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
				txtf2.setText("Remplir les champs!!");
				return;
			}
			System.out.println("JRB2: "+TestAccueil.JRB2.isSelected());
			requeteMethode2();
		}
		else if(obj == fermer) {
			this.setVisible(false);
			this.dispose();
		}
	}

}
