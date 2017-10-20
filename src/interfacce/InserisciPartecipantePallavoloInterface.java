package interfacce;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InserisciPartecipantePallavoloInterface {
	
	public InserisciPartecipantePallavoloInterface() {
		System.out.println("entro");
		frame=new JFrame();
		frame.setVisible(true);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(250, 250);
		
		JPanel panel1=new JPanel();
		JLabel label1=new JLabel("Inserisci i dati personali");
		panel1.add(label1);
		frame.add(panel1,BorderLayout.NORTH);
	}
	
	private JFrame frame;
}
