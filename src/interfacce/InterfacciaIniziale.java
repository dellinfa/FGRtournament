
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
			JPanel p2 = new JPanel();
			JPanel p3 = new JPanel();
			JLabel l1 = new JLabel("Benvenuto in FGRtournament, software per la gestione di tornei.");
			JLabel l2 = new JLabel("Entra per selezionare il torneo.");
			
			intro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			intro.setLocation(400,100);
			
			intro.add(p1, BorderLayout.NORTH);
			intro.add(p2, BorderLayout.CENTER);
			intro.add(p3, BorderLayout.SOUTH);
			
			p1.setLayout(new BorderLayout());
			p2.setLayout(new BorderLayout());
			p3.setLayout(new BorderLayout());
			p1.add(l1, BorderLayout.NORTH);
			p2.add(l2, BorderLayout.CENTER);
			p3.add(entra, BorderLayout.NORTH);			
			
			ActionListener elencoTorneoInterface = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 intro.setVisible(false);
					 new ElencoTorneiInterface();
				}
			};
			
			entra.addActionListener(elencoTorneoInterface);
			
			intro.pack();
			intro.setVisible(true);
			intro.setSize(500,100);
			
		}
}
	



