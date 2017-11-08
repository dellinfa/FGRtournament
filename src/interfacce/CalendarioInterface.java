package interfacce;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.plaf.basic.BasicOptionPaneUI;

import Controller.ControllerTorneo;
import commons.Giocatore;
import commons.Partecipante;
import commons.Squadra;
import commons.Torneo;
import gestori.GestoreDatiPersistenti;

public class CalendarioInterface {

	private JFrame frame;

	public CalendarioInterface() throws ParseException {

		frame = new JFrame("FGRtournament");
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(400, 100);

		JPanel panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());

		JLabel label1 = new JLabel("Scegli un'operazione da effettuare: ");

		JButton buttonVediCalendario = new JButton("VEDI CALENDARIO");
		JButton buttonRegistraRisultato = new JButton("REGISTRA RISULTATO");
		JButton buttonIndietro = new JButton("INDIETRO");

		panel1.add(label1, BorderLayout.NORTH);
		panel1.add(buttonRegistraRisultato, BorderLayout.EAST);
		panel1.add(buttonVediCalendario, BorderLayout.WEST);
		panel1.add(buttonIndietro, BorderLayout.SOUTH);
		frame.add(panel1);

		ActionListener indietro = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MenuPrincipale(" ");
				frame.setVisible(false);
			}
		};


		ActionListener registraRisultato = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new RegistraRisultato();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.setVisible(false);
			}
		};

		ActionListener vediCalendario = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					new VediCalendario();
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.setVisible(false);
			}
		};

		buttonIndietro.addActionListener(indietro);
		buttonVediCalendario.addActionListener(vediCalendario);
		buttonRegistraRisultato.addActionListener(registraRisultato);

		frame.pack();
		frame.setVisible(true);
		frame.setSize(400, 125);
	}

}
