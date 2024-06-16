package projet;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//@Entity
//@Table(name="But")
public class But {

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int id;
		private LocalDate date;
		private Equipe equipe;
		private Joueur scorers;
		//private String equipe;
		//private String scorers;
		private boolean ownGoal;
		private boolean penalty;
		//
		But(){
			
		}
		//
		But(LocalDate ddate, Equipe eequipe, Joueur sscorer,boolean oowngoal, boolean ppenalty){
			date = ddate;
			equipe = eequipe;
			scorers = sscorer;
			ownGoal = oowngoal;
			penalty = ppenalty;
		}
		//
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		But B = new But();
		B.setDate(LocalDate.of(2023, 06,04));
		B.setEquipe(new Equipe());
		B.setScorers(new Joueur());
		B.setOwnGoal(true);
		B.setPenalty(false);
		System.out.println("B: "+B.toString());
	}
	@Override
	public String toString() {
		return "But [id=" + id + ", date=" + date + ", equipe=" + equipe + ", scorers=" + scorers + ", ownGoal="
				+ ownGoal + ", penalty=" + penalty + "]";
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
	public Equipe getEquipe() {
		return equipe;
	}
	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}
	public Joueur getScorers() {
		return scorers;
	}
	public void setScorers(Joueur scorers) {
		this.scorers = scorers;
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

}
