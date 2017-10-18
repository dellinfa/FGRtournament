
	package interfacce;

	import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.util.ArrayList;

	import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JList;
	import javax.swing.JPanel;
	import javax.swing.JScrollBar;
	import javax.swing.JScrollPane;
	import javax.swing.ListSelectionModel;

	import commons.Torneo;

	public class InterfacciaIniziale {

		public static void main(String[] args) {
			
			new InterfacciaIniziale();
			
	}
		
		public InterfacciaIniziale() {
			JFrame intro= new JFrame("FGRtournament");
			JButton entra = new JButton("Entra");
			JPanel p1 = new JPanel();
			JLabel l1 = new JLabel("Benvenuto in FGRtournament, software per gestire tornei di Pallavolo,Basket,Calcio.      "
					+ "Entra per selezionare la tipologia del torneo.");
			
			JPanel p2 = new JPanel();
			
			intro.pack();
			intro.setLocation(250,250);
			intro.setVisible(true);
			intro.setTitle("FGRtournament");
			intro.setLayout(null);
			intro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			intro.getContentPane().setLayout(new BorderLayout());
			
			p1.add(l1);
			p2.add(entra);
			
			intro.add(p1,BorderLayout.NORTH);
			intro.add(p2,BorderLayout.CENTER);
			
			ActionListener elencoTorneoInterface = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 intro.setVisible(false);
					 new ElencoTorneiInterface();
				}
			};
			
			entra.addActionListener(elencoTorneoInterface);
			
			intro.pack();
			intro.setVisible(true);
			intro.setSize(850,200);
			

		}
		
		
		}
	



