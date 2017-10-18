package interfacce;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import commons.Torneo;

public class MenuPrincipale {
	
	public MenuPrincipale() {
		frame=new JFrame();
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JComboBox<Torneo> lista = new JComboBox<Torneo>();
		JPanel panel1=new JPanel();
		JLabel label1=new JLabel("Seleziona il torneo:");
		panel1.add(label1);
		frame.add(panel1,BorderLayout.NORTH);
		frame.add(lista,BorderLayout.CENTER);
		
		frame.pack();
		frame.setVisible(true);
	}

	private JFrame frame;
}
