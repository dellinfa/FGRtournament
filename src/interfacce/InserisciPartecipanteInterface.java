package interfacce;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Controller.ControllerTorneo;
import commons.Giocatore;
import commons.Squadra;
import commons.Torneo;
import connessioniClassiDataBase.ConnessioneGiocatore;
import connessioniClassiDataBase.ConnessioneSquadre;
import gestori.GestoreDatiPersistenti;

public class InserisciPartecipanteInterface {

	private JFrame frame;
	private ControllerTorneo ct;
	private HashMap<String, Torneo> tornei;
	public static JComboBox<String> cbListaSquadre;
	public static JComboBox<String> cbListaGiocatori;
	private HashMap<String, Squadra> squadre;
	private HashMap<String, Giocatore> giocatori;
	private static InserisciPartecipanteInterface istanza;
	private int numP, count;

	public static InserisciPartecipanteInterface getInstance() throws ParseException {
		if (istanza == null)
			istanza = new InserisciPartecipanteInterface();
		return istanza;
	}

	public InserisciPartecipanteInterface() throws ParseException {
		ct = ControllerTorneo.getInstance();

		frame = new JFrame("FGRtournament");
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(400, 100);

		tornei = ct.getTornei();
		squadre = ct.getSquadre();
		giocatori = ct.getGiocatori();

		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();

		Torneo nameSport = ElencoTorneiInterface.prendiTorneo();

		if (nameSport.getSport().equalsIgnoreCase("ping pong")) {

			for (Entry<String, Torneo> entry : tornei.entrySet()) {
				if (nameSport.getSport().equalsIgnoreCase(entry.getValue().getSport())) {
					numP = entry.getValue().getNumPartecipanti();
				}
			}

			JLabel label1 = new JLabel("Seleziona " + numP + " partecipanti: ");
			panel1.add(label1);
			frame.add(panel1, BorderLayout.NORTH);

			String matricola;

			cbListaGiocatori = new JComboBox<>();

			for (Entry<String, Giocatore> entry : giocatori.entrySet()) {
				matricola = entry.getValue().getId();
				boolean trovato = false;
				for (Entry<String, Torneo> torneo : tornei.entrySet()) {

					if (!torneo.getValue().getListPartecipanti().contains(entry.getValue())
							&& (entry.getValue().getNomeSquadra() == null)) {
						System.out.println(entry.getValue().getId());
						System.out.println(entry.getValue().getNomeSquadra());
						trovato = true;
						break;
					}

				}
				if (trovato)
					cbListaGiocatori.addItem(matricola);
				trovato = false;
			}

			panel2.add(cbListaGiocatori);
			frame.add(panel2, BorderLayout.CENTER);

			JButton buttonAggiungi = new JButton("AGGIUNGI");
			JButton buttonIndietro = new JButton("INDIETRO");

			JButton buttonCreaCalendario = new JButton("Crea calendario");
			panel2.add(buttonCreaCalendario);
			frame.add(panel2, BorderLayout.CENTER);

			panel3.add(buttonIndietro, BorderLayout.WEST);
			panel3.add(buttonAggiungi, BorderLayout.EAST);
			frame.add(panel3, BorderLayout.SOUTH);

			ActionListener indietro = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new MenuPrincipale(" ");
					frame.setVisible(false);
				}
			};

			ActionListener addGiocatore = new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					count = 0;
					nameSport.addListPartecipanti(giocatori.get(cbListaGiocatori.getSelectedItem().toString()));
					try {
						for (Entry<String, Giocatore> entry1 : giocatori.entrySet()) {
							if (nameSport.getSport().equalsIgnoreCase(
									GestoreDatiPersistenti.torneoGiocatoreS(entry1.getValue().getId()))) {
								count++;

							}
						}

						System.out.println(count);

						if (count < numP) {
							ConnessioneGiocatore.setTorneoToGiocatore(cbListaGiocatori.getSelectedItem().toString(),
									nameSport.getSport());
							try {
								aggiornaComboBoxGiocatore();
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else {
							ControllerTorneo.visualizzaErrore("Numero massimo partecipanti raggiunto!");

						}

					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

				}
			};

			ActionListener creaCalendario = new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					count = 0;
					for (Entry<String, Giocatore> entry1 : giocatori.entrySet()) {
						try {
							if (nameSport.getSport().equalsIgnoreCase(
									GestoreDatiPersistenti.torneoGiocatoreS(entry1.getValue().getId()))) {
								count++;

							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					if (count == numP ) {

						try {
							frame.setVisible(false);
							new CreaCalendarioInterface();

						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						ControllerTorneo.visualizzaErrore("Impossibile creare il calendario. Inserire i partecipanti!");
					}

				}

			};

			buttonIndietro.addActionListener(indietro);
			buttonAggiungi.addActionListener(addGiocatore);
			buttonCreaCalendario.addActionListener(creaCalendario);

			frame.pack();
			frame.setVisible(true);
			frame.setSize(300, 200);

		} else {

			for (Entry<String, Torneo> entry : tornei.entrySet()) {
				if (nameSport.getSport().equalsIgnoreCase(entry.getValue().getSport())) {
					numP = entry.getValue().getNumPartecipanti();
				}
			}

			JLabel label1 = new JLabel("Seleziona " + numP + " squadre: ");
			panel1.add(label1);
			frame.add(panel1, BorderLayout.NORTH);

			String nomeS;

			cbListaSquadre = new JComboBox<>();

			for (Entry<String, Squadra> entry : squadre.entrySet()) {
				nomeS = entry.getValue().getId();
				boolean trovato = false;
				for (Entry<String, Torneo> torneo : tornei.entrySet()) {
					if (torneo.getValue().getListPartecipanti().contains(entry.getValue())) {
						trovato = true;
						break;
					}
				}
				if (!trovato)
					cbListaSquadre.addItem(nomeS);
				trovato = false;
			}

			panel2.add(cbListaSquadre);
			frame.add(panel2, BorderLayout.CENTER);

			JButton buttonAggiungi = new JButton("AGGIUNGI");
			JButton buttonIndietro = new JButton("INDIETRO");

			JButton buttonCreaCalendario = new JButton("Crea calendario");
			panel2.add(buttonCreaCalendario);
			frame.add(panel2, BorderLayout.CENTER);

			panel3.add(buttonIndietro, BorderLayout.WEST);
			panel3.add(buttonAggiungi, BorderLayout.EAST);
			frame.add(panel3, BorderLayout.SOUTH);

			ActionListener indietro = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new MenuPrincipale(" ");
					frame.setVisible(false);
				}
			};

			ActionListener addSquadra = new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					count = 0;
					nameSport.addListPartecipanti(squadre.get(cbListaSquadre.getSelectedItem().toString()));
					try {
						for (Entry<String, Squadra> entry1 : squadre.entrySet()) {
							if (nameSport.getSport().equalsIgnoreCase(
									GestoreDatiPersistenti.torneoSquadraS(entry1.getValue().getId()))) {
								count++;

							}
						}

						if (count < numP) {
							ConnessioneSquadre.setTorneoToSquadra(cbListaSquadre.getSelectedItem().toString(),
									nameSport.getSport());
							try {
								aggiornaComboBoxSquadra();
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else {
							ControllerTorneo.visualizzaErrore("Numero massimo partecipanti raggiunto!");

						}

					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

				}
			};

			ActionListener creaCalendario = new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					count = 0;
					for (Entry<String, Squadra> entry1 : squadre.entrySet()) {
						try {
							if (nameSport.getSport().equalsIgnoreCase(
									GestoreDatiPersistenti.torneoSquadraS(entry1.getValue().getId()))) {
								count++;

							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					

					if (count == numP) {

						try {

							frame.setVisible(false);

							new CreaCalendarioInterface();

						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						ControllerTorneo.visualizzaErrore("Impossibile creare il calendario. Inserire i partecipanti!");
					}

				}

			};

			buttonIndietro.addActionListener(indietro);
			buttonAggiungi.addActionListener(addSquadra);
			buttonCreaCalendario.addActionListener(creaCalendario);

			frame.pack();
			frame.setVisible(true);
			frame.setSize(300, 200);
		}
	}

	public void aggiornaComboBoxSquadra() throws ParseException {
		cbListaSquadre.removeAllItems();
		String nomeS;
		for (Entry<String, Squadra> entry : squadre.entrySet()) {
			nomeS = entry.getValue().getId();
			boolean trovato = false;
			for (Entry<String, Torneo> torneo : tornei.entrySet()) {
				if (torneo.getValue().getListPartecipanti().contains(entry.getValue())) {
					trovato = true;
					break;
				}
			}
			if (!trovato)
				cbListaSquadre.addItem(nomeS);

			trovato = false;

		}

	}

	public void aggiornaComboBoxGiocatore() throws ParseException {
		cbListaGiocatori.removeAllItems();
		String nomeS;
		for (Entry<String, Giocatore> entry : giocatori.entrySet()) {
			nomeS = entry.getValue().getId();
			boolean trovato = false;
			for (Entry<String, Torneo> torneo : tornei.entrySet()) {
				if (!torneo.getValue().getListPartecipanti().contains(entry.getValue())
						&& (entry.getValue().getNomeSquadra() == null)) {
					trovato = true;
					break;
				}
			}
			if (trovato)
				cbListaGiocatori.addItem(nomeS);

			trovato = false;

		}

	}

}
