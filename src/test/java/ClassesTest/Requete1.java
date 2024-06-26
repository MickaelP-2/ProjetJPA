package ClassesTest;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
 * Classe qui réalise la requete noméro 1:
 * Afficher les N meilleurs butteurs de tous les temps
 */
public class Requete1 extends JFrame implements ActionListener{
	/**
	 * Decaration des attibuts de la classe
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
	Requete1(){
		bl = new BorderLayout();
		gl1 = new GridLayout(2,2);
		executer = new JButton("Executer");
		executer.addActionListener(this);
		fermer = new JButton("Fermer");
		fermer.addActionListener(this);
		label = new JLabel("Afficher les N meilleurs butteurs.");
		label.setHorizontalAlignment((int)CENTER_ALIGNMENT);
		jl1 = new JLabel("Combien de joueurs: ");
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
	 * Methode main utilisée pour tester la classe
	 * Visualiser la fenetre créée
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Requete1 req1 = new Requete1();
		req1.setVisible(true);
	}
	//
	/**
	 * Methode qui contient le code de la requete 1.
	 */
	public void requeteMethode1() {
		System.out.println("req1!!");
		//boolean res1, res2, res3, res4;
		TestAccueil.txta.setText(null);
		HashMap<String, Integer> copieFichier;//type Goalscorers
		List<TestGoalscorers> list;//type Goalscorers
		Iterator<TestGoalscorers> iter;//type Goalscorers
		int nb = Integer.parseInt(txtf1.getText());
		String ligne = null;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("config1");//Foot dans persistence.xm
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
			//Ouverture de la transaction pour le contexte de persistence
			transaction.begin();//ouverture de la transaction
			//Lecture de la table et copie dans un hashset-> cumul de buts si deja existants
			//**Query query = em.createQuery("SELECT gs FROM Goalscorers gs WHERE gs.scorer = :nom");
			//TypedQuery<Goalscorers> query = em.createQuery("SELECT gs FROM Goalscorers gs WHERE gs.date = :date ",Goalscorers.class);
			
			Query query = em.createQuery("SELECT gs FROM Goalscorers gs");
			//query.setParameter("date", LocalDate.parse("1917-10-03"));//type LocalDate
			list = query.getResultList();
			int nbBut = 1;
			copieFichier = new HashMap<String, Integer>();
			if(list.size()>0) {
				iter = list.iterator();
				while(iter.hasNext()) {
					TestGoalscorers GS = iter.next();	
					if(copieFichier.containsKey(GS.getScorer())==false) {
						nbBut = 1;
						copieFichier.put(GS.getScorer(),nbBut);
					}
					else if(copieFichier.containsKey(GS.getScorer())==true) {
						nbBut++;
						copieFichier.replace(GS.getScorer(),nbBut);
					}
				}
			}//fin if()
			else if(list.size()==0) {
				TestAccueil.txta.setText("Pas de résultats!!");
			}
			transaction.commit();//validation de la transaction
			em.close();//fermeture du flux
			//
			List<TestScores> list2 = new ArrayList<TestScores>();//pour copier copieFichier
			Iterator<String> iterKey = copieFichier.keySet().iterator();
			Iterator<Integer> iterValue = copieFichier.values().iterator();
			TestScores scor = null;
			while(iterKey.hasNext() && iterValue.hasNext()) {
				scor = new TestScores();
				ligne = iterValue.next()+" but(s): "+iterKey.next();
				scor.setStr(iterKey.next());
				scor.setNombre(Integer.parseInt(iterValue.next().toString()));
				list2.add(scor);
			}
			Collections.sort(list2, TestScores.comparateurScore);//ordre alphabetic des noms
			int i = Integer.parseInt(txtf1.getText());
			String[] tab = list2.toString().split(",");
			TestAccueil.txta.setText("Meilleurs joueurs: \n");
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
	 * methode pour implementer les actions des boutons.
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		Object obj = ae.getSource();
		if(obj == executer) {
			txtf2.setText("");
			if(txtf1.getText().equals("")==true) {
				txtf2.setText("Saisir le nombre de joueurs!");
				return;
			}
			System.out.println("JRB1: "+TestAccueil.JRB1.isSelected());
			requeteMethode1();
		}
		else if(obj == fermer) {
			this.setVisible(false);
			this.dispose();
		}
	}

}
