package projet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Scores {

	private String str;
	private Integer nombre;
	//
	public Scores() {
		
	}
	public Scores(String sstr, Integer nnombre) {
		this.str = sstr;
		this.nombre = nnombre;
	}
	//
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scores scor = new Scores();
		scor.setStr("nimport");
		scor.setNombre(5);
		Scores scor1 = new Scores("nimport1",11);
		Scores scor2 = new Scores("nimport2",1);
		Scores scor3 = new Scores("nimport3",6);
		Scores scor4 = new Scores("nimport4",33);
		Scores scor5 = new Scores("nimport5",-10);
		System.out.println("scores: "+scor.toString());
		List<Scores> SCOR = new ArrayList<Scores>();
		SCOR.add(scor1);
		SCOR.add(scor2);
		SCOR.add(scor3);
		SCOR.add(scor4);
		SCOR.add(scor5);
		Collections.sort(SCOR, Scores.comparateurScore);
		System.out.println("Scor: "+SCOR);
		Collections.sort(SCOR, Scores.comparateurNom);
		System.out.println("Scor: "+SCOR);
	}
	@Override
	public String toString() {
		return str + " " + nombre;//changer pour en pas afficher les noms de attributs
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public Integer getNombre() {
		return nombre;
	}
	public void setNombre(Integer nombre) {
		this.nombre = nombre;
	}
	//
	public static Comparator<Scores> comparateurScore = new Comparator<Scores>(){
		@Override
		public int compare(Scores o1, Scores o2) {
		// TODO Auto-generated method stub
		return  (int)(o2.getNombre() - o1.getNombre());
	}
	};
	public static Comparator<Scores> comparateurNom = new Comparator<Scores>(){
		@Override
		public int compare(Scores o1, Scores o2) {
		// TODO Auto-generated method stub
		return  o1.getStr().compareTo(o2.getStr());
	}
	};
}
