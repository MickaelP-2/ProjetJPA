package ClassesTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestScores {

	private String str;
	private Integer nombre;
	//
	public TestScores() {
		
	}
	public TestScores(String sstr, Integer nnombre) {
		this.str = sstr;
		this.nombre = nnombre;
	}
	//
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestScores scor = new TestScores();
		scor.setStr("nimport");
		scor.setNombre(5);
		TestScores scor1 = new TestScores("nimport1",11);
		TestScores scor2 = new TestScores("nimport2",1);
		TestScores scor3 = new TestScores("nimport3",6);
		TestScores scor4 = new TestScores("nimport4",33);
		TestScores scor5 = new TestScores("nimport5",-10);
		System.out.println("scores: "+scor.toString());
		List<TestScores> SCOR = new ArrayList<TestScores>();
		SCOR.add(scor1);
		SCOR.add(scor2);
		SCOR.add(scor3);
		SCOR.add(scor4);
		SCOR.add(scor5);
		Collections.sort(SCOR, TestScores.comparateurScore);
		System.out.println("Scor: "+SCOR);
		Collections.sort(SCOR, TestScores.comparateurNom);
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
	public static Comparator<TestScores> comparateurScore = new Comparator<TestScores>(){
		@Override
		public int compare(TestScores o1, TestScores o2) {
		// TODO Auto-generated method stub
		return  (int)(o2.getNombre() - o1.getNombre());
	}
	};
	public static Comparator<TestScores> comparateurNom = new Comparator<TestScores>(){
		@Override
		public int compare(TestScores o1, TestScores o2) {
		// TODO Auto-generated method stub
		return  o1.getStr().compareTo(o2.getStr());
	}
	};
}
