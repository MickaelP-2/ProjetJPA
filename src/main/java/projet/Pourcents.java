package projet;

import java.util.Comparator;

public class Pourcents {

	private int victoires;
	private int defaites;
	private int nuls;
	private int total;
	
	//
	public Pourcents() {
		
	}
	//
	public Pourcents(int vic,int def,int nul,int tot) {
		this.victoires = vic;
		this.defaites = def;
		this.nuls = nul;
		this.total = tot;
	}
	//
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pourcents pour = new Pourcents();
		pour.setVictoires(12);
		pour.setDefaites(3);
		pour.setNuls(1);
		pour.setTotal(16);
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
	
	@Override
	public String toString() {
		return "Pourcents [victoires=" + victoires + ", defaites=" + defaites + ", nuls=" + nuls + ", total=" + total
				+ "]";
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
