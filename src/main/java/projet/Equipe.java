package projet;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//@Entity
//@Table(name="Equipe")
public class Equipe {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nom;
	private boolean home;
	private boolean away;
	private String ville;
	private List <String> joueurs;
	//
	Equipe(){
		
	}
	
	Equipe(String nnom, boolean hhome, boolean aaway, String vville, List<String> jjoueurs){
		nom = nnom;
		home = hhome;
		away = aaway;
		ville = vville;
		joueurs = jjoueurs;
				
	}
	//
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Equipe EQ = new Equipe();
		EQ.setNom("Ekip");
		EQ.setHome(false);
		EQ.setAway(true);
		EQ.setVille("Montpellier");
		EQ.setJoueurs(null);//??
		System.out.println("EQ: "+EQ.toString());
	}

	@Override
	public String toString() {
		return "Equipe [id=" + id + ", nom=" + nom + ", home=" + home + ", away=" + away + ", ville=" + ville
				+ ", joueurs=" + joueurs + "]";
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

	public boolean isHome() {
		return home;
	}

	public void setHome(boolean home) {
		this.home = home;
	}

	public boolean isAway() {
		return away;
	}

	public void setAway(boolean away) {
		this.away = away;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public List<String> getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(List<String> joueurs) {
		this.joueurs = joueurs;
	}

}
