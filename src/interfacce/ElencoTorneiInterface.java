package interfacce;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ElencoTorneiInterface {

	public ElencoTorneiInterface() {
		
		frame=new JFrame("FGRtournament");
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(400, 100);
		
		JPanel panel1=new JPanel();
		JPanel panel2=new JPanel();
		panel1.setLayout(new BorderLayout());
		panel2.setLayout(new BorderLayout());
		JLabel label1=new JLabel("Crea o seleziona il torneo");
		panel1.add(label1);
		frame.add(panel1,BorderLayout.NORTH);
		frame.add(panel2, BorderLayout.SOUTH);
		JButton buttonCreaTorneo = new JButton("CREA TORNEO");
		JButton buttonIndietro = new JButton("INDIETRO");
		panel2.add(buttonCreaTorneo, BorderLayout.EAST);
		panel2.add(buttonIndietro, BorderLayout.WEST);

		ActionListener creaTorneo=new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
					new FormCreaTorneo();								
			}
		};
		
		ActionListener indietro=new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				new InterfacciaIniziale();				
				frame.setVisible(false);
			}
		};
	

		buttonIndietro.addActionListener(indietro);
		buttonCreaTorneo.addActionListener(creaTorneo);
		
		
		frame.pack();
		frame.setVisible(true);
		frame.setSize(500,200);

	
	
}
		
		public static void main(String[] args) {
			
			new ElencoTorneiInterface();
		}
		private JFrame frame;
}
