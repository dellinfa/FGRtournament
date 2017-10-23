package interfacce;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuPrincipale {
	
	public MenuPrincipale(int sport) {
		this.sport=sport;
		
		frame=new JFrame("FGRtournament");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setLocation(400, 100);
		
		JPanel panel1=new JPanel();
		JLabel label1=new JLabel("Seleziona un'operazione da effettuare");
		panel1.add(label1);
		frame.add(label1,BorderLayout.NORTH);
		
		JPanel panel2=new JPanel();
		panel2.setLayout(new GridLayout(4,1));
		JButton calendario=new JButton("Calendario");
		panel2.add(calendario);
		frame.add(panel2,BorderLayout.CENTER);
		
		JButton classifica=new JButton("Classifica");
		panel2.add(classifica);
		
		JButton inserisciPartecipante=new JButton("Inserisci partecipante");
		panel2.add(inserisciPartecipante);
		
		JButton creaCalendario= new JButton(" Crea calendario");
		panel2.add(creaCalendario);
		
		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayout(1,1));
		JButton indietro=new JButton("Indietro");
		panel3.add(indietro);
		frame.add(panel3, BorderLayout.SOUTH);
		
		ActionListener cale=new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new CalendarioInterface();
			}
		};
		
		ActionListener cla=new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new ClassificaInterface();
			}
		};
		
		ActionListener insert;
		if (sport==0) {	
			insert=new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					frame.setVisible(false);
					new InserisciPartecipantePallavoloInterface();
				}
			};
		}
		else {
			insert=new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					frame.setVisible(false);
					new FormCreaTorneo();
					
				}
			};
		}
		ActionListener back=new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new ElencoTorneiInterface();
				frame.setVisible(false);
			}
		};
		
		calendario.addActionListener(cale);
		classifica.addActionListener(cla);
		inserisciPartecipante.addActionListener(insert);
		indietro.addActionListener(back);
		
		frame.pack();
		frame.setVisible(true);
		frame.setSize(500,250);
		
	}

	public static void main(String[] args){
		new MenuPrincipale(sport);
	}
	private static int sport;
	private JFrame frame;
	
	
}
