package ClassesTest;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="Results")
public class TestResults {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private LocalDate date;
	private String homeTeam;
	private String awayTeam;
	private int homeScore;
	private int awayScore;
	//**private Tournois tournois;
	private String tournois;
	private String nomVille;
	private String country;
	private boolean neutre;
	//
	/**
	 * constructeur sans argument , utilisé par JPA
	 */
	TestResults(){
		
	}
	/**
	 * constructeur avec arguments
	 * @param homeTeam
	 * @param awayTeam
	 * @param homescore
	 * @param awayScore
	 * @param tournois
	 * @param ville
	 * @param country
	 * @param neutre
	 */
	TestResults(LocalDate ddate, String hhomeTeam,String aawayTeam, int hhomescore,int aawayScore,String ttournois,String vville,String ccountry, boolean nneutre){
		date = ddate;
		homeTeam = hhomeTeam;
		awayTeam = aawayTeam;
		homeScore = hhomescore;
		awayScore = aawayScore;
		tournois = ttournois;
		nomVille = vville;
		country = ccountry;
		neutre = nneutre;
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
	public String getHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}
	public String getAwayTeam() {
		return awayTeam;
	}
	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}
	public int getHomeScore() {
		return homeScore;
	}
	public void setHomeScore(int homeScore) {
		this.homeScore = homeScore;
	}
	public int getAwayScore() {
		return awayScore;
	}
	public void setAwayScore(int awayScore) {
		this.awayScore = awayScore;
	}
	public String getTournois() {
		return tournois;
	}
	public void setTournois(String tournois) {
		this.tournois = tournois;
	}
	public String getNomVille() {
		return nomVille;
	}
	public void setNomVille(String nomVille) {
		this.nomVille = nomVille;
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
	
	
	@Override
	public String toString() {
		/*
		return "Results [id=" + id + ", date=" + date + ", homeTeam=" + homeTeam + ", awayTeam=" + awayTeam
				+ ", homeScore=" + homeScore + ", awayScore=" + awayScore + ", tournois=" + tournois + ", nomVille="
				+ nomVille + ", country=" + country + ", neutre=" + neutre + "]";
		*/
		return id+","+date+","+homeTeam+","+awayTeam+","+homeScore+","+awayScore+","+tournois+","+nomVille+","+country+","+neutre;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestResults TRE = new TestResults();
		LocalDate local = LocalDate.of(2024,12,25);
		TRE.setDate(local);
		TRE.setHomeTeam("Montpellier");
		TRE.setAwayTeam("Nimes");
		TRE.setHomeScore(3);
		TRE.setAwayScore(0);
		TRE.setTournois("Derby-Montpelier/Nimes");
		TRE.setNomVille("Montpellier");
		TRE.setCountry("France");
		TRE.setNeutre(true);
		System.out.println("RE: "+TRE.toString());//OK
	}
}
