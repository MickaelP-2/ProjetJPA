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
