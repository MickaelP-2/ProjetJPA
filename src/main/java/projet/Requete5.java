package projet;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
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
	 * Affiche les statistiques des rencontres entre deux équipes
	 */
	public void requeteMethode5() {
		Accueil.txta.setText(null);
		String equipe1 = null;
		String equipe2 = null;
		String titre = "Rencontres: ";
		if(txtf1.getText().equals("")==true || txtf2.getText().equals("")==true) {
			//test pour verfiier les valeurs des 2 equipes
			txtf3.setText("Nommer les 2 equipes.");
			return;
		}
		else {
			equipe1 = txtf1.getText();
			equipe2 = txtf2.getText();
		}
		//recherche dans la base les matchs entre les deux equipes et copie dans un HashMap
		HashMap<LocalDate, Pourcents> copieFichier;//Composant HashMap types: String pour le nom de l'equipe, Pourcents pour les valeurs
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
			Pourcents PR1=null;
			Pourcents PR2=null;
			int nbVic1=0,nbDef1=0,nbNul1=0,tot1=0;
			int nbVic2=0,nbDef2=0,nbNul2=0,tot2=0;
			int total=0;
			int nbBut = 0;
			String equipe = null;
			int nbMatch =0;//pour le nb total de match joués
			int victoires=0,defaites=0,nuls=0;//defaite ou victoire -> null non pris en compte
			copieFichier = new HashMap<LocalDate, Pourcents>();//nomVille, Score(away+home)
			if(list.size()>0) {
				iter = list.iterator();
				int i = 1;//pour le nb max a afficher
				while(iter.hasNext()) {//&& i<nb
					PR1 = new Pourcents();//pourcentages de l'equipe1
					PR2 = new Pourcents();//pourcentages de l'equipe2
					Results RS = iter.next();
					//Afficher les matchs+dates et % victoire des 2 equipes
						if(RS.getHomeTeam().equals(equipe1)==true) {
							//cas equipe1 = HomeTeam
							if(RS.getHomeScore() > RS.getAwayScore()) {
								//cas de victoire home de equipe1
								nbVic1++;
								nbDef2++;
								total++;	
							}
							else if(RS.getHomeScore() == RS.getAwayScore()) {
								//cas de null de equipe1
								total++;
								nbNul1++;
								nbNul2++;
							}
							else if(RS.getHomeScore() < RS.getAwayScore()) {
								//cas de null de equipe1
								nbDef1++;
								nbVic2++;
								total++;
							}
					}//fin copieFichier
					else if(RS.getAwayTeam().equals(equipe1)==true) {
						//cas equipe1 = AwayTeam
						if(RS.getHomeScore() > RS.getAwayScore()) {
							//cas de victoire home de equipe1
							nbDef1++;
							nbVic2++;
							total++;
						}
						else if(RS.getHomeScore() == RS.getAwayScore()) {
							//cas de null de equipe1
							total++;
							nbNul2++;
							nbNul1++;
						}
						else if(RS.getHomeScore() < RS.getAwayScore()) {
							//cas de null de equipe1
							nbVic2++;
							nbDef1++;
							total++;
						}
					}
				}//fin while()
				transaction.commit();
				em.close();
				
			}//fin if()
			else if(list.size()==0) {
				Accueil.txta.setText("Equipe inconnue!!");
			}
			//
			total = list.size();
			DecimalFormat format = new DecimalFormat("##0.00");//formatage pour les attributs de type double(2 chiffres apres la virgule)
				PR1 = new Pourcents(nbVic1,nbDef1,nbNul1,total,0.0,0.0,0.0);
				PR1.setPourcentageVictoires(PR1.pourcentageVictoire(total, nbVic1));
				PR1.setPourcentageDefaites(PR1.pourcentageDefaites(total, nbDef1));
				PR1.setPourcentageNuls(PR1.pourcentageNul(total, nbNul1));
				Object obj1 = format.format(PR1.getPourcentageVictoires());
				Object obj2 = format.format(PR1.getPourcentageDefaites());
				Object obj3 = format.format(PR1.getPourcentageNuls());
				if(obj1.toString().contains(",")==true) {
					obj1 = obj1.toString().replace(",",".");
				}
				if(obj2.toString().contains(",")==true) {
					obj2 = obj2.toString().replace(",",".");
				}
				if(obj3.toString().contains(",")==true) {
					obj3 = obj3.toString().replace(",",".");
				}
				PR1.setPourcentageVictoires(Double.parseDouble(obj1.toString()));
				PR1.setPourcentageDefaites(Double.parseDouble(obj2.toString()));
				PR1.setPourcentageNuls(Double.parseDouble(obj3.toString()));
				PR2 = new Pourcents(nbVic2,nbDef2,nbNul2,total,0.0,0.0,0.0);
				PR2.setPourcentageVictoires(PR2.pourcentageVictoire(total, nbVic2));
				PR2.setPourcentageDefaites(PR2.pourcentageDefaites(total, nbDef2));
				PR2.setPourcentageNuls(PR2.pourcentageNul(total, nbNul2));
				obj1 = format.format(PR2.getPourcentageVictoires());
				obj2 = format.format(PR2.getPourcentageDefaites());
				obj3 = format.format(PR2.getPourcentageNuls());
				if(obj1.toString().contains(",")==true) {
					obj1 = obj1.toString().replace(",",".");
				}
				if(obj2.toString().contains(",")==true) {
					obj2 = obj2.toString().replace(",",".");
				}
				if(obj3.toString().contains(",")==true) {
					obj3 = obj3.toString().replace(",",".");
				}
				PR2.setPourcentageVictoires(Double.parseDouble(obj1.toString()));
				PR2.setPourcentageDefaites(Double.parseDouble(obj2.toString()));
				PR2.setPourcentageNuls(Double.parseDouble(obj3.toString()));
				PR1 = new Pourcents(nbVic1,nbDef1,nbNul1,total,PR1.getPourcentageVictoires(),PR1.getPourcentageDefaites(),PR1.getPourcentageNuls());
				PR2 = new Pourcents(nbVic2,nbDef2,nbNul2,total,PR2.getPourcentageVictoires(),PR2.getPourcentageDefaites(),PR2.getPourcentageNuls());
			titre = titre+" "+equipe1+":"+equipe2+"\n "+total+" match(s): ";
			String ligne1 = equipe1+"\n victoire(s): "+PR1.getVictoires()+" defaite(s): "+PR1.getDefaites()+" nul(s): "+PR1.getNuls();
			String ligne2 = equipe2+"\n victoire(s): "+PR2.getVictoires()+" defaite(s): "+PR2.getDefaites()+" nul(s): "+PR2.getNuls();
			String ligne3 = equipe1+"\n victoire: "+PR1.getPourcentageVictoires()+"% defaite: "+PR1.getPourcentageDefaites()+"% nul: "+PR1.getPourcentageNuls()+"%";
			String ligne4 = equipe2+"\n victoire: "+PR2.getPourcentageVictoires()+"% defaite: "+PR2.getPourcentageDefaites()+"% nul: "+PR2.getPourcentageNuls()+"%";
			Accueil.txta.setText(titre+"\n");
			Accueil.txta.append(ligne1+"\n");
			Accueil.txta.append(ligne2+"\n");
			Accueil.txta.append(ligne3+"\n");
			Accueil.txta.append(ligne4+"\n");
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
