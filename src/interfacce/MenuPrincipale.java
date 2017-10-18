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
		frame.setLocation(250, 250);
		
		JPanel panel1=new JPanel();
		JLabel label1=new JLabel("Scegli l'azione da fare");
		panel1.add(label1);
		frame.add(panel1,BorderLayout.NORTH);
		
		JPanel panel2= new JPanel();

		
		frame.pack();
		frame.setVisible(true);
	}

	private JFrame frame;
}
