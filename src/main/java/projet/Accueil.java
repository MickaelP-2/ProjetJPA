package projet;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Accueil extends JFrame implements ActionListener,Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BorderLayout bl,bl2;
	GridLayout gl1,gl2;
	JPanel JP1,JP2,JP3;
	JTextArea txta;//pour l'affichage des resultats des requetes
	JTextField txtf1;//affichage de la date et de messages
	JLabel label1;
	String ligne1,ligne2;
	JButton quitter,verifier,requete;//verifier affiche la fenetre de verification,
	JRadioButton JRB1,JRB2,JRB3,JRB4,JRB5;
	ButtonGroup bg;
	JScrollPane jsp;
	public Thread T1;
	boolean isAlive ;
	Calendar date;
	Date date1,date2;
	SimpleDateFormat formatDate;
	//
	Accueil(){
		formatDate = new SimpleDateFormat("EEEE dd MMMM yyyy HH':'mm':'ss",new Locale("fr","FR"));
		isAlive = true;
		ligne1 = "";
		ligne2 = "";
		T1 = new Thread(this);
		bl = new BorderLayout();
		JP1 = new JPanel();
		JP2 = new JPanel();
		JP3 = new JPanel();
		quitter = new JButton("Quitter");
		quitter.addActionListener(this);
		verifier = new JButton("Vérifier");
		verifier.addActionListener(this);
		requete = new JButton("Requètes");
		requete.addActionListener(this);
		label1 = new JLabel("Résultats: ");
		label1.setHorizontalAlignment((int)CENTER_ALIGNMENT);
		txta = new JTextArea(20,25);
		txta.setSize(10,25);
		txtf1 = new JTextField();
		jsp = new JScrollPane(txta);
		jsp.setBounds(10,50,350,150);
		JRB1 = new JRadioButton("Requete 1",false);
		JRB1.addActionListener(this);
		JRB2 = new JRadioButton("Requete 2",false);
		JRB2.addActionListener(this);
		JRB3 = new JRadioButton("Requete 3",false);
		JRB3.addActionListener(this);
		JRB4 = new JRadioButton("Requete 4",false);
		JRB4.addActionListener(this);
		JRB5 = new JRadioButton("Requete 5",false);
		JRB5.addActionListener(this);
		bg = new ButtonGroup();
		bg.add(JRB1);
		bg.add(JRB2);
		bg.add(JRB3);
		bg.add(JRB4);
		bg.add(JRB5);
		gl1 = new GridLayout(5,1);
		JP2.setLayout(gl1);
		JP2.add(JRB1);
		JP2.add(JRB2);
		JP2.add(JRB3);
		JP2.add(JRB4);
		JP2.add(JRB5);
		JP3.add(quitter);
		JP3.add(requete);
		JP3.add(verifier);
		bl2 = new BorderLayout();
		JP1.setLayout(bl2);
		JP1.add("North",label1);
		JP1.add("Center",jsp);
		date = Calendar.getInstance();
		date2 = date.getTime();
		ligne1 = formatDate.format(date2).toString();
		txtf1.setText(formatDate.format(date2).toString());
		this.setLayout(bl);
		this.add("North",txtf1);
		this.add("Center",JP2);
		this.add("South",JP3);
		this.add("East",JP1);
		this.setLocation(100,100);
		this.setSize(500,450);
		this.setVisible(true);
		T1.run();
	}//fin constructeur()

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Accueil ACC = new Accueil();
		ACC.setVisible(true);
	}//fin main()

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		Object obj = ae.getSource();
		if(obj==quitter) {
			isAlive = false;
			try {
				Thread.sleep(1000);
			}
			catch(Exception ex) {
				txtf1.setText(ex.toString());
				System.err.println(ex.toString());
			}
			this.setVisible(false);
			this.dispose();
		}
		else if(obj==verifier) {
			VerificationBase verif = new VerificationBase();
			verif.setVisible(true);
		}
	}//fin actionPerformed()
@Override
	public void run() {
		// TODO Auto-generated method stub
		date = Calendar.getInstance();
			while(isAlive) {	
				//date = Calendar.getInstance();
				try {
					Thread.sleep(1000);//Attente de 1min
				} //fin try
				catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					isAlive = false;
				}//fin catch()
				catch(Exception e) {
					System.err.println("??"+e.toString());	
				}//fin catch()
				ligne1 = txtf1.getText();
				date = Calendar.getInstance();
				date2 = date.getTime();
				ligne2 = formatDate.format(date2).toString();
				if(ligne1.equals(ligne2)==false) {
					txtf1.setText(ligne2);
				}//fin if()
			}//fin while()
	}//fin run()
}//fin classe Accueil()
