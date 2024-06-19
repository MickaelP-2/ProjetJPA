package projet;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Classe qui réalise la requete noméro 5:
 * Afficher les N meilleurs butteurs de tous les temps
 */
public class Requete5 extends JFrame implements ActionListener{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//
	private Path path;
	String chemin;
	BorderLayout bl;
	JButton executer,fermer;
	JTextField txtf1,txtf2,txtf3;
	JLabel jl1,jl2,jl3,label;
	JPanel JP1,JP2;
	GridLayout gl1;
	//
	/**
	 * Constructeur de classe
	 */
	Requete5(){
		bl = new BorderLayout();
		gl1 = new GridLayout(3,2);
		executer = new JButton("Executer");
		executer.addActionListener(this);
		fermer = new JButton("Fermer");
		fermer.addActionListener(this);
		label = new JLabel("Afficher les matchs entre deux équipes.");
		label.setHorizontalAlignment((int)CENTER_ALIGNMENT);
		jl1 = new JLabel("Equipe 1: ");
		jl2 = new JLabel("Equipe 2: ");
		jl3 = new JLabel();
		txtf1 = new JTextField();
		txtf2 = new JTextField();
		txtf3 = new JTextField();
		JP1 = new JPanel();
		JP1.setLayout(gl1);
		JP1.add(jl1);
		JP1.add(txtf1);
		JP1.add(jl2);
		JP1.add(txtf2);
		JP1.add(jl3);
		JP1.add(txtf3);
		JP2 = new JPanel();
		JP2.add(executer);
		JP2.add(fermer);
		this.setLayout(bl);;
		this.add("North",label);
		this.add("Center",JP1);
		this.add("South",JP2);
		this.setLocation(200,200);
		this.setSize(300,175);
		this.setVisible(true);
		
	}
	/**
	 * Methode pour tester la classe
	 * Visualiser la fenetre créée
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Requete5 req5 = new Requete5();
		req5.setVisible(true);
	}
	//
	/**
	 * Methode qui implemente la requete 5
	 */
	public void requeteMethode5() {
		System.out.println("req5!!");
	}
	//
	/**
	 * Methode pour implementer les actions des boutons
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		Object obj = ae.getSource();
		if(obj == executer) {
			txtf3.setText("");
			if(txtf1.getText().equals("")==true || txtf2.getText().equals("")==true) {
				txtf3.setText("Nommer les équipes!!");
				return;
			}
			System.out.println("JRB5: "+Accueil.JRB5.isSelected());
			requeteMethode5();
		}
		else if(obj == fermer) {
			this.setVisible(false);
			this.dispose();
		}
	}

}
