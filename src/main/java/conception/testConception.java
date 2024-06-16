package conception;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
public class testConception extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//
	private Path path1,path2,path3;//pour le chemin d'acces aux copies des fichiers .csv
	String chemin = null;
	BorderLayout bl;
	JButton fermer, verifier;//fermer ferme la fenetre, verifier pour verifier si les fichiers.csv sont copies en tables
	JTextField txtf1,txtf2;//pour saisir le chemin racine des tables,afficher des messages
	JLabel jl1,jl2,label;
	JPanel JP1,JP2;
	GridLayout gl1;//pour disposition du paneau central
	//
	testConception(){
		bl = new BorderLayout();
		fermer = new JButton("Fermer");
		fermer.addActionListener(this);
		verifier = new JButton("Verifier");
		verifier.addActionListener(this);
		txtf1 = new JTextField();
		txtf2 = new JTextField();
		jl1 = new JLabel("Chemin: ");
		jl2 = new JLabel("");
		label = new JLabel("Vérification: ");
		label.setHorizontalAlignment((int)CENTER_ALIGNMENT);
		gl1 = new GridLayout(2,2);
		JP1 = new JPanel();
		JP1.setLayout(gl1);
		JP1.add(jl1);
		JP1.add(txtf1);
		JP1.add(jl2);
		JP1.add(txtf2);
		JP2 = new JPanel();
		JP2.add(fermer);
		JP2.add(verifier);
		this.setLayout(bl);
		this.add("North",label);
		this.add("Center",JP1);
		this.add("South",JP2);
		this.setLocation(150,150);
		this.setSize(350,150);
		this.setVisible(true);
		
	}
	//////////////////////////////////////////////////////
	public void verification() {
		//verfier l'existence du repertoire et le lister si exists
		//exemple: C://xampp/mysql/data/nom_table
		System.out.println("Saisir le chemin absolu de la base.");
		boolean res1, res2,res3;
		String chem = txtf1.getText();
		path1 = Paths.get(chem);
		res1 = Files.exists(path1);
		if(res1==false) {
			System.out.println("res1: "+res1);
		}
		else if(res1==true) {
			System.out.println("res1: "+res1);
			//Creation d'un EntityManager
			EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");//pu_essai dans persistence.xm
			EntityManager em = entityManagerFactory.createEntityManager();
			EntityTransaction transaction = em.getTransaction();
			//Ouverture de la transaction pour le contexte de persistence
			transaction.begin();
			
			
			try {
			Query query = em.createQuery("CREATE DATABASE IF NOT EXISTS TestJPA");//creation de la BDD
			query.getSingleResult();
			}
			catch(Exception e) {
				System.out.println("e: "+e.toString());
			}
			transaction.commit();
			em.close();//fermeture de l'entityManager()
			System.out.println("em fermé");
			res1 = Files.exists(path1);
			if(res1==false) {
				System.out.println("em fermé res1: "+res1);
			}
			
		}
	}
	//////////////////////////////////////////////////////
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello!!");
		testConception tC = new testConception();
		tC.setVisible(true);
	}//fin main()

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
	}

}//fin classe()
