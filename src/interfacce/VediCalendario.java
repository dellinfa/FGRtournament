package interfacce;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import commons.Torneo;

public class VediCalendario {

	private JFrame frame;
	private JTextArea ta = new JTextArea();
	private String s;
	private HashMap<String, Torneo> tornei;

	public VediCalendario() throws IOException, ParseException {

		frame = new JFrame("FGRtournament");
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(400, 100);
		
		Torneo nameSport = ElencoTorneiInterface.prendiTorneo();
		String nomeS;
		
		JPanel p = new JPanel();
		nomeS=nameSport.getSport();
		if(nomeS.equalsIgnoreCase("pallavolo")) {
			try {
				FileReader fr = new FileReader("calendarioPallavolo.txt");
				Scanner sc = new Scanner(fr);
				while (sc.hasNext()) {
					ta.append(sc.nextLine());
					ta.append("\n");
				}
				fr.close();
			} catch (IOException e) {

			}
		}else if(nomeS.equalsIgnoreCase("calcio")) {
			try {
				FileReader fr = new FileReader("calendarioCalcio.txt");
				Scanner sc = new Scanner(fr);
				while (sc.hasNext()) {
					ta.append(sc.nextLine());
					ta.append("\n");
				}
				fr.close();
			} catch (IOException e) {

			}
		}else if(nomeS.equalsIgnoreCase("basket")) {
			try {
				FileReader fr = new FileReader("calendarioBasket.txt");
				Scanner sc = new Scanner(fr);
				while (sc.hasNext()) {
					ta.append(sc.nextLine());
					ta.append("\n");
				}
				fr.close();
			} catch (IOException e) {

			}
		}else {
			try {
				FileReader fr = new FileReader("calendarioPingPong.txt");
				Scanner sc = new Scanner(fr);
				while (sc.hasNext()) {
					ta.append(sc.nextLine());
					ta.append("\n");
				}
				fr.close();
			} catch (IOException e) {

			}
		}
		//this.leggi();
		ta.setEditable(false);
		
		JScrollPane listPane = new JScrollPane(ta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	

		JLabel label1 = new JLabel("Calendario");

		JButton buttonIndietro = new JButton("INDIETRO");
		
		frame.add(p);
		frame.add(listPane);
		frame.add(label1, BorderLayout.NORTH);
		frame.add(buttonIndietro, BorderLayout.AFTER_LAST_LINE);
		
		
		
		buttonIndietro.addActionListener(indietro);
		
		frame.pack();
		frame.setVisible(true);
		frame.setSize(350, 600);

	}

//	public void leggi() throws FileNotFoundException {
//		try {
//			FileReader fr = new FileReader("calendario.txt");
//			Scanner sc = new Scanner(fr);
//			while (sc.hasNext()) {
//				ta.append(sc.nextLine());
//				ta.append("\n");
//			}
//			fr.close();
//		} catch (IOException e) {
//
//		}
//
//	}
	
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
