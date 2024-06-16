package projet;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//@Entity
//@Table(name="Equipe")
public class Tournois {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private LocalDate date;
	private String nom;
	private Ville ville;
	private List <Equipe> participant;
	//private String ville;
	//private List <String> participant;
	//
	Tournois(){
		
	}
	//
	Tournois(LocalDate ddate,String nnom, Ville vville, List<Equipe> pparticipant){
		date = ddate;
		nom = nnom;
		ville = vville;
		participant = pparticipant;
	}
	//
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tournois TO = new Tournois();
		TO.setDate(LocalDate.of(2001, 9, 11));
		TO.setNom("Derby local");
		TO.setVille(new Ville());
		TO.setParticipant(null);//??
		System.out.println("TO: "+TO.toString());
	}
	@Override
	public String toString() {
		return "Tournois [id=" + id + ", date=" + date + ", nom=" + nom + ", ville=" + ville + ", participant="
				+ participant + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Ville getVille() {
		return ville;
	}
	public void setVille(Ville ville) {
		this.ville = ville;
	}
	public List<Equipe> getParticipant() {
		return participant;
	}
	public void setParticipant(List<Equipe> participant) {
		this.participant = participant;
	}

}
