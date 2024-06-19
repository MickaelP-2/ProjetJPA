package conception;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class ConnexionProjetFoot {

		static String url,user,pwd,urlDb;
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//connexion a la base de donnée avec un EntityManager, et une transaction
		ResourceBundle config = ResourceBundle.getBundle("config");//fichier .properties
		//String driver = config.getString("database.driver");
		 url = config.getString("database.url");
		 user = config.getString("database.user");//root par defaut
		 pwd = config.getString("database.pwd");//== par defaut
		System.out.println("\n url: "+url+"\n user: "+user+"\n pwd: "+pwd);
		
		//chargement du pilote pour mysql
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			//Class.forName("com.mysql.cj.jdbc.Driver");
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(e.toString());
			System.exit(-1);
		} 
		//connection a la BDD: Connection myConnection = DriverManager.getConnection(url,user,pwd)
		try {
			//ouverture de la connexion
			Connection myConnexion = DriverManager.getConnection(url, user, pwd);//!!portee de la var
			System.out.println("con: "+myConnexion.toString());
			//fermeture de la connexion
			//myConnexion.close();
			System.out.println("con: "+myConnexion.toString());
			//requete
			Statement stat =  myConnexion.createStatement();
			//int nbLignesModif = stat.executeUpdate("INSERT INTO FOURNISSEUR (id_fournisseur,nom) values (4,'Leroy Merlin')");
			//Exception si existant(id_fourniseur)
			boolean testCreate = stat.execute("CREATE DATABASE IF NOT EXISTS nimport");//0 car id=7 inexistant
			
			//
			//SELECT = executeQuery-> se positionner sur la premiere ligne = resultat.next()
			//ArrayList<Fournisseur> listeFournisseur = new ArrayList<>();//-> ajouter classe fournisseur
			//**ResultSet resultat = stat.executeQuery("SELECT * FROM FOURNISSEUR");
			/*
			while(resultat.next()) {
				Fournisseur f = new Fournisseur(resultat.getInt("id_fournisseur"),resultat.getString("nom"));
				listeFournisseur.add(f);
			}
			*/
			
			stat.close();//fermeture statement
			myConnexion.close();//fermeture connection
			System.out.println("testCreate: "+testCreate);
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("config1");//pu_essai dans persistence.xm
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		//Ouverture de la transaction pour le contexte de persistence
		transaction.begin();
		
		
		
		transaction.commit();
		em.close();//fermeture de l'entityManager()
		*/
	}

}
