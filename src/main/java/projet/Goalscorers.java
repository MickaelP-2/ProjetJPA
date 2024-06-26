package projet;

import java.time.LocalDate;
import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="GoalScorers")
public class Goalscorers {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private LocalDate date; 
	private String homeTeam;
	private String awayTeam;
	private String team;
	//**private Joueur scorer;
	private String scorer;
	private String minute;
	private boolean ownGoal;
	private boolean penalty;
	
	/**
	 * constructeur sans arguments utilisé par hibernate
	 */
	Goalscorers(){
		
	}
	
	Goalscorers(LocalDate ddate,String hhometeam,String aawayteam, String tteam, String sscorers, String mminute, boolean oownGoal, boolean ppenalty ){
		date = ddate;
		homeTeam = hhometeam;
		awayTeam = aawayteam;
		team = tteam;
		scorer = sscorers;
		minute = mminute;
		ownGoal = oownGoal;
		penalty = ppenalty;
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

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}
	
	@Override
	public String toString() {
		/*
		return "Goalscorers [id=" + id + ", date=" + date + ", homeTeam=" + homeTeam + ", awayTeam=" + awayTeam
				+ ", team=" + team + ", scorer=" + scorer + ", minute=" + minute + ", ownGoal=" + ownGoal + ", penalty="
				+ penalty + "]";
		*/
		return id+","+date+","+homeTeam+","+awayTeam+","+team+","+scorer+","+minute+","+ownGoal+","+penalty;
	}

	public String getScorer() {
		return scorer;
	}

	public void setScorer(String scorer) {
		this.scorer = scorer;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	public boolean isOwnGoal() {
		return ownGoal;
	}

	public void setOwnGoal(boolean ownGoal) {
		this.ownGoal = ownGoal;
	}

	public boolean isPenalty() {
		return penalty;
	}

	public void setPenalty(boolean penalty) {
		this.penalty = penalty;
	}
	//
}
