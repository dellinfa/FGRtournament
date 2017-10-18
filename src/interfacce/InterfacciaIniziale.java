
	package interfacce;

	import java.awt.BorderLayout;
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
		
			JFrame intro= new JFrame("FGRtournament");
			JButton entra = new JButton("Entra");
			
			
			intro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			intro.getContentPane().setLayout(new BorderLayout());
			intro.getContentPane().add(new JLabel(""), BorderLayout.NORTH);
			intro.getContentPane().add(entra);
			
			ActionListener elencoTorneoInterface= new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 intro.setVisible(false);
					 new ElencoTorneiInterface();
				}
			};
			
			entra.addActionListener(elencoTorneoInterface);
			intro.pack();
			intro.setVisible(true);

		}
		
		
	}



