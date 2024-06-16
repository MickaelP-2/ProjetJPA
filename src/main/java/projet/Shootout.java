package projet;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ShootOut")
public class Shootout {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private LocalDate date;
	private String homeTeam;
	private String awayTeam;
	private String winner;
	private String firstShooter;
	
	//
	/**
	 * constructeur sans argument utilisé par hibernate
	 */
	Shootout(){
		
	}
	/**
	 * constructeur avec argument utilisé en developpement
	 * @param ddate
	 * @param hhometeam
	 * @param aawayteam
	 * @param wwinner
	 * @param ffirstshooter
	 */
	Shootout(LocalDate ddate, String hhometeam, String aawayteam, String wwinner, String ffirstshooter){
		date = ddate;
		homeTeam = hhometeam;
		awayTeam = aawayteam;
		winner = wwinner;
		firstShooter = ffirstshooter;
	}
	
	@Override
	public String toString() {
		return "Shootout [id=" + id + ", date=" + date + ", homeTeam=" + homeTeam + ", awayTeam=" + awayTeam
				+ ", winner=" + winner + ", firstShooter=" + firstShooter + "]";
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
	public String getWinner() {
		return winner;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}
	public String getFirstShooter() {
		return firstShooter;
	}
	public void setFirstShooter(String firstShooter) {
		this.firstShooter = firstShooter;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Shootout SO = new Shootout();
		LocalDate local = LocalDate.of(2024,12,12);
		SO.setDate(local);
		SO.setHomeTeam("Montpellier");
		SO.setAwayTeam("Nimes");
		SO.setWinner("Montpellier");
		SO.setFirstShooter("Montpellier");
		System.out.println("SO: "+SO.toString());
	}

}
