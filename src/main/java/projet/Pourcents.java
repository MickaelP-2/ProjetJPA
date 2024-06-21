package projet;

import java.util.Comparator;

public class Pourcents {

	private String equipe;
	private int victoires;
	private int defaites;
	private int nuls;
	private int total;
	private Double pourcentageVictoires;
	private Double pourcentageDefaites;
	private Double pourcentageNuls;
	
	//
	public Pourcents() {
		
	}
	//
	public Pourcents(int vic,int def,int nul,int tot,Double pourcentVic, Double pourcentDef, Double pourcentNul) {
		this.equipe = "";
		this.victoires = vic;
		this.defaites = def;
		this.nuls = nul;
		this.total = tot;
		this.pourcentageVictoires = pourcentVic;
		this.pourcentageDefaites = pourcentDef;
		this.pourcentageNuls = pourcentNul;
	}
	public Pourcents(String equipe, int vic,int def,int nul,int tot,Double pourcentVic, Double pourcentDef, Double pourcentNul) {
		this.equipe = equipe;
		this.victoires = vic;
		this.defaites = def;
		this.nuls = nul;
		this.total = tot;
		this.pourcentageVictoires = pourcentVic;
		this.pourcentageDefaites = pourcentDef;
		this.pourcentageNuls = pourcentNul;
	}
	//
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pourcents pour = new Pourcents();
		pour.setVictoires(12);
		pour.setDefaites(3);
		pour.setNuls(1);
		pour.setTotal(16);
		pour.setPourcentageVictoires(11.35);
		pour.setPourcentageDefaites(34.99);
		pour.setPourcentageNuls(6.5);
		System.out.println("pour: "+pour.toString());
	}
	public int getVictoires() {
		return victoires;
	}
	public void setVictoires(int victoires) {
		this.victoires = victoires;
	}
	public int getDefaites() {
		return defaites;
	}
	public void setDefaites(int defaites) {
		this.defaites = defaites;
	}
	public String getEquipe() {
		return equipe;
	}
	public void setEquipe(String equipe) {
		this.equipe = equipe;
	}
	
	
	
	@Override
	public String toString() {
		return equipe + "," + victoires + "," + defaites + "," + nuls+ "," + total + "," + pourcentageVictoires + ","+ pourcentageDefaites + "," + pourcentageNuls + "]";
	}
	//
	public Double getPourcentageVictoires() {
		return pourcentageVictoires;
	}
	public void setPourcentageVictoires(Double pourcentageVictoires) {
		this.pourcentageVictoires = pourcentageVictoires;
	}
	public Double getPourcentageDefaites() {
		return pourcentageDefaites;
	}
	public void setPourcentageDefaites(Double pourcentageDefaites) {
		this.pourcentageDefaites = pourcentageDefaites;
	}
	public Double getPourcentageNuls() {
		return pourcentageNuls;
	}
	public void setPourcentageNuls(Double pourcentageNuls) {
		this.pourcentageNuls = pourcentageNuls;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getNuls() {
		return nuls;
	}
	public void setNuls(int nuls) {
		this.nuls = nuls;
	}
	//
	/**
	 * Methode de calcul du pourcentage de victoires
	 * @param nbMatch
	 * @param victoire
	 * @return Double res(% de victoires)
	 */
	public Double pourcentageVictoire(int nbMatch, int victoire) {
		Double res = 0.0;
		if(nbMatch==0.0 || victoire==0.0) {
			res = 0.0;
		}
		else {
			res = (victoire*100.0)/nbMatch;
		}
		return res;
	}
	/**
	 * Methode de calcul du pourcentage de défaites
	 * @param nbMatch
	 * @param defaites
	 * @return
	 */
	public Double pourcentageDefaites(int nbMatch, int defaites) {
		Double res = 0.0;
		if(nbMatch==0.0 || defaites==0.0) {
			res = 0.0;
		}
		else {
			res = (defaites*100.0)/nbMatch;
		}
		return res;
	}
	/**
	 * Methode de calcul du pourcentage de match nuls
	 * @param nbMatch
	 * @param nul
	 * @return
	 */
	public Double pourcentageNul(int nbMatch, int nul) {
		Double res = 0.0;
		if(nbMatch==0.0 || nul==0.0) {
			res = 0.0;
		}
		else {
			res = (nul*100.0)/nbMatch;
		}
		return res;
	}
	//
	/**
	 * Pour trier les pourcentages de victoire calculés par les methodes ci-dessus
	 */
	public static Comparator<Pourcents> comparateurPourcentageVictoire = new Comparator<Pourcents>(){
		@Override
		public int compare(Pourcents o1, Pourcents o2) {
			// TODO Auto-generated method stub
			
			return (int)(o2.getPourcentageVictoires() - o1.getPourcentageVictoires());
		}
	};
	/**
	 * Pour trier les pourcentages de defaites calculés par les methodes ci-dessus
	 */
	public static Comparator<Pourcents> comparateurPourcentageDefaites = new Comparator<Pourcents>(){
		@Override
		public int compare(Pourcents o1, Pourcents o2) {
			// TODO Auto-generated method stub
			
			return (int)(o2.getPourcentageDefaites() - o1.getPourcentageDefaites());
		}
	};
	/**
	 * Pour trier les pourcentages de match nul calculés par les methodes ci-dessus
	 */
	public static Comparator<Pourcents> comparateurPourcentageNuls = new Comparator<Pourcents>(){
		@Override
		public int compare(Pourcents o1, Pourcents o2) {
			// TODO Auto-generated method stub
			
			return (int)(o2.getPourcentageNuls() - o1.getPourcentageNuls());
		}
	};
	public static Comparator<Pourcents> comparateurVictoire = new Comparator<Pourcents>(){
		@Override
		public int compare(Pourcents o1, Pourcents o2) {
			// TODO Auto-generated method stub
			
			return (int)(o2.getVictoires() - o1.getVictoires());
		}
	};
	public static Comparator<Pourcents> comparateurdefaites = new Comparator<Pourcents>(){
		@Override
		public int compare(Pourcents o1, Pourcents o2) {
			// TODO Auto-generated method stub
			
			return (int)(o2.getDefaites() - o1.getDefaites());
		}
	};
}
