package interfacce;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class ClassificaInterface {
	
	private JFrame frame;
	
	
	
	public ClassificaInterface() {
		frame = new JFrame("FGRtournament");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setLocation(400, 100);
		
		JPanel panel1= new JPanel();
		JLabel label1= new JLabel("Classifica");
		panel1.add(label1);
		frame.add(panel1, BorderLayout.NORTH);
		
		JPanel panel2= new JPanel();
		JButton buttonIndietro= new JButton("INDIETRO");
		panel2.add(buttonIndietro);
		frame.add(panel2, BorderLayout.SOUTH);
		
		JPanel panel3= new JPanel();
		JTable table= new JTable();
		
		
		
		
		
		
		buttonIndietro.addActionListener(indietro);
		
		
		frame.pack();
		frame.setVisible(true);
		frame.setSize(500,250);
	}
	
	

	ActionListener indietro = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				new CalendarioInterface();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			frame.setVisible(false);
		}
	};
}
