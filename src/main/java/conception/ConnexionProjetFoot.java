package conception;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class ConnexionProjetFoot {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//connexion a la base de donnée avec un EntityManager, et une transaction
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");//pu_essai dans persistence.xm
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		//Ouverture de la transaction pour le contexte de persistence
		transaction.begin();
		
		
		
		transaction.commit();
		em.close();//fermeture de l'entityManager()
	}

}
