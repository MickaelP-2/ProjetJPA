package projet;

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

	static Connection myConnexion;
	static Statement stat;
	static ResultSet result;
	static String url,user,pwd;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//connexion a la base de donnée avec un EntityManager, et une transaction
		//**EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("config1");//pu_essai dans persistence.xm
		//**EntityManager em = entityManagerFactory.createEntityManager();
		//**System.out.println("em: "+em.toString());
		
		//**EntityTransaction transaction = em.getTransaction();
		//Ouverture de la transaction pour le contexte de persistence
		ResourceBundle config = ResourceBundle.getBundle("config");//fichier .properties
		url = config.getString("database.url");
		user = config.getString("database.user");//root par defaut
		pwd = config.getString("database.pwd");//== par defaut
		System.out.println("\n url: "+url+"\n user: "+user+"\n pwd: "+pwd);
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(e.toString());
			System.exit(-1);
		} 
		try {
			Connection myConnection = DriverManager.getConnection(url, user,pwd);
			stat =  myConnexion.createStatement();
			boolean creer = stat.execute("CREATE DATABASE IF NOT EXIST TestJPA ");
			System.out.println("???creer: "+creer);
			
			
			stat.close();
			myConnection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//**transaction.begin();
		
		
		//**transaction.commit();
		
		//**em.close();//fermeture de l'entityManager()
	}

}

