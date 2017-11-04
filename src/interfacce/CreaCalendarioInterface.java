package interfacce;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Controller.ControllerTorneo;
import commons.Giocatore;
import commons.Partecipante;
import commons.Squadra;
import commons.Torneo;
import gestori.GestoreDatiPersistenti;

public class CreaCalendarioInterface {

	private JFrame frame;
	private HashMap<String, Torneo> tornei;
	private ControllerTorneo ct;
	private HashMap<String, Squadra> squadre;
	private HashMap<String, Giocatore> giocatori;
	private Torneo torneo;
	private ArrayList<String> partecipanti = new ArrayList<String>();

	private static CreaCalendarioInterface istanza;

	public static CreaCalendarioInterface getInstance() throws ParseException, SQLException, IOException {
		if (istanza == null)
			istanza = new CreaCalendarioInterface();
		return istanza;
	}

	public CreaCalendarioInterface() throws ParseException, SQLException, IOException {

		frame = new JFrame("FGRtournament");
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(400, 100);

		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JLabel label1 = new JLabel("Calendario");
		frame.setLayout(new BorderLayout());
		panel1.add(label1);
		panel2.setLayout(new GridLayout(15, 3));
		frame.add(panel1, BorderLayout.NORTH);
		frame.add(panel2, BorderLayout.CENTER);

		ct = ControllerTorneo.getInstance();
		tornei = ct.getTornei();
		giocatori = ct.getGiocatori();
		squadre = ct.getSquadre();
		Torneo nameSport = ElencoTorneiInterface.prendiTorneo();
		int numPart;
		String nomeS;
		try {
			for (Entry<String, Torneo> entry : tornei.entrySet()) {
				if (nameSport.getSport().equalsIgnoreCase(entry.getValue().getSport())) {
					numPart = entry.getValue().getNumPartecipanti();
				}
			}
		} catch (Exception e) {
			System.out.println("Errore partecipanti!");
			return;
		}

		int i = 0;

		if (nameSport.getSport().equalsIgnoreCase("ping pong")) {
			for (Entry<String, Giocatore> entry : giocatori.entrySet()) {
				if (nameSport.getSport()
						.equalsIgnoreCase(GestoreDatiPersistenti.torneoGiocatoreS(entry.getValue().getId()))) {
					partecipanti.add(entry.getValue().getId());
					i++;

				}
			}
		} else {

			for (Entry<String, Squadra> entry : squadre.entrySet()) {
				if (nameSport.getSport()
						.equalsIgnoreCase(GestoreDatiPersistenti.torneoSquadraS(entry.getValue().getId()))) {
					partecipanti.add(entry.getValue().getId());
					i++;

				}
			}
		}
		
		nomeS= nameSport.getSport();
		
		if(nomeS.equalsIgnoreCase("calcio")){
			FileOutputStream fos= new FileOutputStream("calendarioCalcio.txt");
			PrintStream ps= new PrintStream(fos);
			int numero_partecipanti = partecipanti.size();
			int giornate = numero_partecipanti - 1;

			/* crea gli array per le due liste in casa e fuori */
			String[] casa = new String[numero_partecipanti / 2];
			String[] trasferta = new String[numero_partecipanti / 2];

			for (int k = 0; k < numero_partecipanti / 2; k++) {
				casa[k] = partecipanti.get(k);
				trasferta[k] = partecipanti.get(numero_partecipanti - 1 - k);
			}

			for (int k = 0; k < giornate; k++) {
				// stampa le partite di questa giornata 
				ps.println("Giornata " + (k + 1));

				for (int j = 0; j < numero_partecipanti / 2; j++)
					ps.println((j + 1) + ") " + trasferta[j] + " - " + casa[j]);

				// Ruota in gli elementi delle liste, tenendo fisso il primo elemento. Salva l'elemento fisso
				String pivot = casa[0];

				 // sposta in avanti gli elementi di "trasferta" inserendo all'inizio l'elemento casa[1] e salva l'elemento uscente in "riporto"
				 

				String riporto = trasferta[trasferta.length - 1];
				trasferta = shiftRight(trasferta, casa[1]);

				 //sposta a sinistra gli elementi di "casa" inserendo all'ultimo posto l'elemento "riporto"			 
				casa = shiftLeft(casa, riporto);

				// ripristina l'elemento fisso
				casa[0] = pivot;
			}
		}else if(nomeS.equalsIgnoreCase("pallavolo")) {
			FileOutputStream fos= new FileOutputStream("calendarioPallavolo.txt");
			PrintStream ps= new PrintStream(fos);
			int numero_partecipanti = partecipanti.size();
			int giornate = numero_partecipanti - 1;

			/* crea gli array per le due liste in casa e fuori */
			String[] casa = new String[numero_partecipanti / 2];
			String[] trasferta = new String[numero_partecipanti / 2];

			for (int k = 0; k < numero_partecipanti / 2; k++) {
				casa[k] = partecipanti.get(k);
				trasferta[k] = partecipanti.get(numero_partecipanti - 1 - k);
			}

			for (int k = 0; k < giornate; k++) {
				// stampa le partite di questa giornata 
				ps.println("Giornata " + (k + 1));

				for (int j = 0; j < numero_partecipanti / 2; j++)
					ps.println((j + 1) + ") " + trasferta[j] + " - " + casa[j]);

				// Ruota in gli elementi delle liste, tenendo fisso il primo elemento. Salva l'elemento fisso
				String pivot = casa[0];

				 // sposta in avanti gli elementi di "trasferta" inserendo all'inizio l'elemento casa[1] e salva l'elemento uscente in "riporto"
				 

				String riporto = trasferta[trasferta.length - 1];
				trasferta = shiftRight(trasferta, casa[1]);

				 //sposta a sinistra gli elementi di "casa" inserendo all'ultimo posto l'elemento "riporto"			 
				casa = shiftLeft(casa, riporto);

				// ripristina l'elemento fisso
				casa[0] = pivot;
			}
		}else if(nomeS.equalsIgnoreCase("basket")) {
			FileOutputStream fos= new FileOutputStream("calendarioBasket.txt");
			PrintStream ps= new PrintStream(fos);
			int numero_partecipanti = partecipanti.size();
			int giornate = numero_partecipanti - 1;

			/* crea gli array per le due liste in casa e fuori */
			String[] casa = new String[numero_partecipanti / 2];
			String[] trasferta = new String[numero_partecipanti / 2];

			for (int k = 0; k < numero_partecipanti / 2; k++) {
				casa[k] = partecipanti.get(k);
				trasferta[k] = partecipanti.get(numero_partecipanti - 1 - k);
			}

			for (int k = 0; k < giornate; k++) {
				// stampa le partite di questa giornata 
				ps.println("Giornata " + (k + 1));

				for (int j = 0; j < numero_partecipanti / 2; j++)
					ps.println((j + 1) + ") " + trasferta[j] + " - " + casa[j]);

				// Ruota in gli elementi delle liste, tenendo fisso il primo elemento. Salva l'elemento fisso
				String pivot = casa[0];

				 // sposta in avanti gli elementi di "trasferta" inserendo all'inizio l'elemento casa[1] e salva l'elemento uscente in "riporto"
				 

				String riporto = trasferta[trasferta.length - 1];
				trasferta = shiftRight(trasferta, casa[1]);

				 //sposta a sinistra gli elementi di "casa" inserendo all'ultimo posto l'elemento "riporto"			 
				casa = shiftLeft(casa, riporto);

				// ripristina l'elemento fisso
				casa[0] = pivot;
			}
		}else {
			FileOutputStream fos= new FileOutputStream("calendarioPingPong.txt");
			PrintStream ps= new PrintStream(fos);
			int numero_partecipanti = partecipanti.size();
			int giornate = numero_partecipanti - 1;

			/* crea gli array per le due liste in casa e fuori */
			String[] casa = new String[numero_partecipanti / 2];
			String[] trasferta = new String[numero_partecipanti / 2];

			for (int k = 0; k < numero_partecipanti / 2; k++) {
				casa[k] = partecipanti.get(k);
				trasferta[k] = partecipanti.get(numero_partecipanti - 1 - k);
			}

			for (int k = 0; k < giornate; k++) {
				// stampa le partite di questa giornata 
				ps.println("Giornata " + (k + 1));

				for (int j = 0; j < numero_partecipanti / 2; j++)
					ps.println((j + 1) + ") " + trasferta[j] + " - " + casa[j]);

				// Ruota in gli elementi delle liste, tenendo fisso il primo elemento. Salva l'elemento fisso
				String pivot = casa[0];

				 // sposta in avanti gli elementi di "trasferta" inserendo all'inizio l'elemento casa[1] e salva l'elemento uscente in "riporto"
				 

				String riporto = trasferta[trasferta.length - 1];
				trasferta = shiftRight(trasferta, casa[1]);

				 //sposta a sinistra gli elementi di "casa" inserendo all'ultimo posto l'elemento "riporto"			 
				casa = shiftLeft(casa, riporto);

				// ripristina l'elemento fisso
				casa[0] = pivot;
			}
		}
		
	
	

		
		
		
		
		JOptionPane.showMessageDialog(frame, "Calendario creato!");
		new MenuPrincipale(" ");
		frame.pack();
		frame.setVisible(false);
		frame.setSize(500, 100);

	}

	private String[] shiftLeft(String[] data, String add) {
		String[] temp = new String[data.length];
		for (int i = 0; i < data.length - 1; i++) {
			temp[i] = data[i + 1];
		}
		temp[data.length - 1] = add;
		return temp;
	}

	private String[] shiftRight(String[] data, String add) {
		String[] temp = new String[data.length];
		for (int i = 1; i < data.length; i++) {
			temp[i] = data[i - 1];
		}
		temp[0] = add;
		return temp;
	}

}
