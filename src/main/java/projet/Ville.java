package projet;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



//@Entity
//@Table(name="Ville")
public class Ville {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nom;
	private String country;
	private boolean neutre;
	private List<Equipe> equipe;
	//private List<String> equipe;
	//
	Ville(){
		
	}
	//
	Ville(String nnom, String ccountry, boolean nneutre,List<Equipe> eequipe){
		nom = nnom;
		country = ccountry;
		neutre = nneutre;
		equipe = eequipe;
	}
	//
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ville VI = new Ville();
		VI.setNom("Montpellier");
		VI.setCountry("France");
		VI.setNeutre(false);
		VI.setEquipe(null);//??-> List
		System.out.println("VI: "+VI.toString());
	}
	@Override
	public String toString() {
		return "Ville [id=" + id + ", nom=" + nom + ", country=" + country + ", neutre=" + neutre + ", equipe=" + equipe
				+ "]";
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public boolean isNeutre() {
		return neutre;
	}
	public void setNeutre(boolean neutre) {
		this.neutre = neutre;
	}
	public List<Equipe> getEquipe() {
		return equipe;
	}
	public void setEquipe(List<Equipe> equipe) {
		this.equipe = equipe;
	}

}
