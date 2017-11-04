package interfacce;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.plaf.basic.BasicOptionPaneUI;

import Controller.ControllerTorneo;
import commons.Squadra;
import commons.Torneo;

public class RegistraRisultato {
	private JFrame frame;
	private HashMap<String, Squadra> squadre;
	private ControllerTorneo ct;
	private static RegistraRisultato istanza;
	
	public static RegistraRisultato getInstance() throws ParseException, SQLException, IOException {
		if (istanza == null)
			istanza = new RegistraRisultato();
		return istanza;
	}

	
	public RegistraRisultato() throws ParseException {
		frame = new JFrame("FGRtournament");
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(400, 100);
		
		
		JPanel panel1= new JPanel();
		JLabel label1= new JLabel("Regstra risultati:");	

		JPanel panel2= new JPanel();
		JButton buttonIndietro= new JButton("INDIETRO");
		panel1.add(label1);
		panel2.add(buttonIndietro);	
		
		JPanel panel3 = new JPanel();
		panel3.setLayout(new BorderLayout());
		JComboBox<String> cb= new JComboBox<String>();
		panel3.add(cb, BorderLayout.NORTH);
		
		JPanel panel4 = new JPanel();
		panel4.setLayout(new BorderLayout());
		JTextArea ta1= new JTextArea();
		ta1.setEditable(true);
		panel4.add(ta1, BorderLayout.NORTH);

		JPanel panel5 = new JPanel();
		panel5.setLayout(new BorderLayout());
		JTextArea ta2= new JTextArea();
		ta2.setEditable(true);
		panel5.add(ta2, BorderLayout.NORTH);
		
		frame.add(panel4, BorderLayout.CENTER);
		frame.add(panel5, BorderLayout.EAST);
		frame.add(panel3, BorderLayout.WEST);
		frame.add(panel1, BorderLayout.NORTH);
		frame.add(panel2, BorderLayout.SOUTH);
		
		
		

		ct = ControllerTorneo.getInstance();
		squadre = ct.getSquadre();
		
		
		
		
		
		
		
		
		buttonIndietro.addActionListener(indietro);
		
		frame.pack();
		frame.setVisible(true);
		frame.setSize(350, 400);
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
