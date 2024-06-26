package projet;

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
import jakarta.persistence.TypedQuery;

/**
 * Classe qui réalise la requete noméro 3:
 * Afficher les N meilleurs butteurs de tous les temps
 */
public class Requete3 extends JFrame implements ActionListener{
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
	Requete3(){
		bl = new BorderLayout();
		gl1 = new GridLayout(3,2);
		executer = new JButton("Executer");
		executer.addActionListener(this);
		fermer = new JButton("Fermer");
		fermer.addActionListener(this);
		label = new JLabel("Afficher les meilleurs butteurs d'une équipe.");
		label.setHorizontalAlignment((int)CENTER_ALIGNMENT);
		jl1 = new JLabel("Nombre de joueurs: ");
		jl2 = new JLabel("nom de l'équipe");
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
	
	//
	/**
	 * Methode contenant le code de la requete 3
	 */
	public void requeteMethode3() {
		Accueil.txta.setText(null);
		HashMap<String, Integer> copieFichier;//type Goalscorers
		List<Goalscorers> list;//type Goalscorers
		Iterator<Goalscorers> iter;//type Goalscorers
		int nb = Integer.parseInt(txtf1.getText());
		String equipe = txtf2.getText();
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
			TypedQuery<Goalscorers> query = em.createQuery("SELECT gs FROM Goalscorers gs WHERE gs.team = :equipe ",Goalscorers.class);
			query.setParameter("equipe", equipe);//type LocalDate
			list = query.getResultList();
			copieFichier = new HashMap<String, Integer>();
			int nbBut =0;
			if(list.size()>0) {
				iter = list.iterator();
				int i = 1;//pour le nb max a afficher
				equipe = txtf2.getText();
				while(iter.hasNext() && i<=nb) {
					//recherche des joueurs de l'equipe nommée
					Goalscorers GS = iter.next();
					equipe = GS.getTeam();
					if(copieFichier.containsKey(GS.getScorer())==false) {
						if(GS.getHomeTeam().equals(equipe)==false) {
							if(GS.getHomeTeam().equals(equipe)==true) {
								nbBut ++;
								copieFichier.replace(GS.getScorer(),nbBut);
							}
							else if(GS.getHomeTeam().equals(equipe)==false) {
								//premmiere entree du joueur 1but
								nbBut = 1;
								copieFichier.put(GS.getScorer(),nbBut);
							}
						}
					}
					else if(copieFichier.containsKey(GS.getScorer())==true) {
						if(GS.getHomeTeam().equals(equipe)==false) {
							if(GS.getHomeTeam().equals(equipe)==true) {
								nbBut ++;
								copieFichier.replace(GS.getScorer(),nbBut);
							}
							else if(GS.getHomeTeam().equals(equipe)==false) {
								nbBut  ++;
								copieFichier.put(GS.getScorer(),nbBut);
							}
						}
					}
				}
			}//fin if()
			else if(list.size()==0) {
				Accueil.txta.setText("Equipe inconnue!!");
			}
			transaction.commit();
			em.close();
			//Lecture de copieFichier
			List<Scores> list2 = new ArrayList<Scores>();
			Iterator<String> iterKey = copieFichier.keySet().iterator();
			Iterator<Integer> iterValue = copieFichier.values().iterator();
			Scores scor = null;
			while(iterKey.hasNext() && iterValue.hasNext()) {
				scor = new Scores();
				scor.setStr(iterKey.next());
				scor.setNombre(Integer.parseInt(iterValue.next().toString()));
				list2.add(scor);
			}
			Collections.sort(list2, Scores.comparateurScore);//ordre alphabetic des noms
			String[] tab = list2.toString().split(",");
			if(nb>tab.length) {
			nb=tab.length;
			}
			Accueil.txta.setText(equipe+": \n");
			for(int j=0;j<nb;j++) {
				if(tab[j].toString().contains("[")==true) {
					tab[j] = tab[j].toString().replace("[","");
				}
				else if(tab[j].toString().contains("]")==true) {
					tab[j] = tab[j].toString().replace("]","");
				}
				Accueil.txta.append((j+1)+": "+tab[j].toString()+" but(s)\n");
			}
	}
	//
	/**
	 * Methode pour implementer les actions des boutons.
	 */
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		Object obj = ae.getSource();
		if(obj == executer) {
			txtf3.setText("");
			if(txtf1.getText().equals("")==true || txtf2.getText().equals("")==true) {
				txtf3.setText("Nommer l'équipe!!");
				return;
			}
			System.out.println("JRB3: "+Accueil.JRB3.isSelected());
			requeteMethode3();
		}
		else if(obj == fermer) {
			this.setVisible(false);
			this.dispose();
		}
	}

}
