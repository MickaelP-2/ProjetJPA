package ClassesTest;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class MessageAide extends JFrame{
	
private static final long serialVersionUID = 1L;
	//
	private BorderLayout bl;
	public JTextArea txta;
	public int xma,yma;//Piur les coordonnées de MessageAide
	////////////
	public MessageAide(){
		//Undecorated car la classe s'affiche au survole d'un composant JLabel
		// et disparait quand le pointeur de la souris le quitte
		xma = 50;
		yma = 50;
		setUndecorated(true);
		bl = new BorderLayout();
		
		txta = new JTextArea(4,35);
		txta.setBackground(Color.WHITE);
		this.setLayout(bl);
		this.add("Center",txta);
		this.pack();
		/////////
	}//fin Message()
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MessageAide aide = new MessageAide();
		aide.setLocation(250,250);
		aide.setSize(300,100);
		aide.txta.setText("Message d'aide sur plusieurs\n lignes de texte??");
		aide.pack();
		aide.setVisible(true);
	}//Fin main()
}//fin class MessageAide()
