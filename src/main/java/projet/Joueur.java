package projet;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//@Entity
//@Table(name="Joueur")
public class Joueur {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nom;
	private String prenom;
	private Equipe equipe;
	//private String equipe;
	
	Joueur(){
		
	}
	
	Joueur(String nnom, String pprenom, Equipe eequipe){
		nom = nnom;
		prenom = pprenom;
		equipe = eequipe;
	}
	//
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Joueur JO = new Joueur();
		JO.setNom("Nimport");
		JO.setPrenom("Koa");
		JO.setEquipe(new Equipe());
		System.out.println("JO: "+JO.toString());
	}

	@Override
	public String toString() {
		return "Joueur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", equipe=" + equipe + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

}
