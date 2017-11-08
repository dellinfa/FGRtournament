package interfacce;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicOptionPaneUI;

import Controller.ControllerCalendario;
import Controller.ControllerTorneo;
import commons.Partita;
import commons.Squadra;
import commons.Torneo;
import connessioniClassiDataBase.ConnessioneCalendario;
import connessioniClassiDataBase.ConnessionePartite;
import connessioniClassiDataBase.ConnessioneSquadre;

public class RegistraRisultato {
	private JFrame frame;
	private HashMap<String, Squadra> squadre = new HashMap<>();
	private HashMap<String, Partita> partite = new HashMap<>();
	private ControllerTorneo ct;
	private ControllerCalendario cc;
	private static RegistraRisultato istanza;
	private JComboBox<String> cb;
	private JTextField tf1, tf2;
	private String s = null, squadraCasa = null, squadraOspite = null;
	private int puntiSC = 0, partiteGSC = 0, partiteVSC = 0, partitePSC = 0, puntiSO = 0, partiteGSO = 0,
			partiteVSO = 0, partitePSO = 0;

	public static RegistraRisultato getInstance() throws ParseException, SQLException, IOException {
		if (istanza == null)
			istanza = new RegistraRisultato();
		return istanza;
	}

	public RegistraRisultato() throws ParseException, SQLException {
		frame = new JFrame("FGRtournament");
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(400, 100);

		JPanel panel1 = new JPanel();
		JLabel label1 = new JLabel("Registra risultati:");

		JButton buttonIndietro = new JButton("INDIETRO");
		panel1.add(label1);

		JPanel panel3 = new JPanel();
		panel3.setLayout(new FlowLayout());

		cb = new JComboBox<String>();
		panel3.add(cb);

		JLabel labelCB = new JLabel();
		labelCB.setText((String) cb.getSelectedItem());
		panel3.add(labelCB);

		tf1 = new JTextField(5);
		panel3.add(tf1);

		tf2 = new JTextField(5);
		panel3.add(tf2);

		JPanel panel5 = new JPanel();
		panel5.setLayout(new BorderLayout());
		JButton buttonRegistraRisultato = new JButton("REGISTRA");
		panel5.add(buttonRegistraRisultato);

		ct = ControllerTorneo.getInstance();
		cc = ControllerCalendario.getInstance();
		squadre = ct.getSquadre();
		partite = cc.getPartite();

		Torneo nameSport = ElencoTorneiInterface.prendiTorneo();

		for (Entry<String, Partita> entry : partite.entrySet()) {
			if (nameSport.getSport().equalsIgnoreCase(entry.getValue().getIdCalendario())) {
				cb.addItem(entry.getValue().getIdPartita());
			}
		}

		panel5.add(buttonIndietro, BorderLayout.WEST);
		panel5.add(buttonRegistraRisultato, BorderLayout.EAST);
		frame.add(panel5, BorderLayout.SOUTH);

		frame.add(panel3, BorderLayout.CENTER);
		frame.add(panel1, BorderLayout.NORTH);

		cb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				s = (String) cb.getSelectedItem();
				for (Entry<String, Partita> entry : partite.entrySet()) {
					if (s.equalsIgnoreCase(entry.getValue().getIdPartita())) {
						labelCB.setText(entry.getValue().getIdPartecipanteCasa() + " - "
								+ entry.getValue().getIdPartecipanteOspite());
						squadraCasa = entry.getValue().getIdPartecipanteCasa();
						squadraOspite = entry.getValue().getIdPartecipanteOspite();
					}
				}
			}
		});

		buttonIndietro.addActionListener(indietro);
		buttonRegistraRisultato.addActionListener(registraRisultato);

		frame.pack();
		frame.setVisible(true);
		frame.setSize(350, 150);
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

	ActionListener registraRisultato = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int casa = 0, ospite = 0;
			String idPartita = (String) cb.getSelectedItem();
			try {
				String nameSport = ElencoTorneiInterface.prendiTorneo().getSport();
				if (nameSport.equalsIgnoreCase("calcio")) {
					try {
						casa = Integer.parseInt(tf1.getText());

					} catch (Exception ee) {
						ControllerCalendario.visualizzaErrore("Risultato 'casa' errato!");
						return;
					}
					// aggiornare combo box e aggiornare classifica
					try {
						ospite = Integer.parseInt(tf2.getText());

					} catch (Exception e1) {
						ControllerCalendario.visualizzaErrore("Risultato 'ospite' errato!");
						return;
					}

					if (casa >= 0 && casa <= 99 && ospite >= 0 && ospite <= 99) {
						ConnessionePartite.setRisultatoCasaToPartita(idPartita, casa);
						ConnessionePartite.setRisultatoOspiteToPartita(idPartita, ospite);

						tf1.setText("");
						tf2.setText("");

						for (Entry<String, Squadra> entry : squadre.entrySet()) {
							if (squadraCasa.equalsIgnoreCase(entry.getValue().getId())) {
								puntiSC = entry.getValue().getPunti();
								partiteGSC = entry.getValue().getPartiteGiocate();
								partiteVSC = entry.getValue().getPartiteVinte();
								partitePSC = entry.getValue().getPartitePerse();

							} else if (squadraOspite.equalsIgnoreCase(entry.getValue().getId())) {
								puntiSO = entry.getValue().getPunti();
								partiteGSO = entry.getValue().getPartiteGiocate();
								partiteVSO = entry.getValue().getPartiteVinte();
								partitePSO = entry.getValue().getPartitePerse();

							}
						}
						
						
						if (casa > ospite) {
							ConnessioneSquadre.aggiornaPuntiSquadra(squadraCasa, (puntiSC + 3));
							ConnessioneSquadre.aggiornaPartiteG(squadraCasa, (partiteGSC + 1));
							ConnessioneSquadre.aggiornaPartiteG(squadraOspite, (partiteGSO + 1));
							ConnessioneSquadre.aggiornaPartiteVinte(squadraCasa, (partiteVSC + 1));
							ConnessioneSquadre.aggiornaPartitePerse(squadraOspite, (partiteVSO + 1));

						} else if (casa == ospite) {
							ConnessioneSquadre.aggiornaPuntiSquadra(squadraCasa, (puntiSC + 1));
							ConnessioneSquadre.aggiornaPuntiSquadra(squadraOspite, (puntiSO + 1));
							ConnessioneSquadre.aggiornaPartiteG(squadraCasa, (partiteGSC + 1));
							ConnessioneSquadre.aggiornaPartiteG(squadraOspite, (partiteGSO + 1));

						} else if (casa < ospite) {
							ConnessioneSquadre.aggiornaPuntiSquadra(squadraOspite, (puntiSO + 3));
							ConnessioneSquadre.aggiornaPartiteG(squadraCasa, (partiteGSC + 1));
							ConnessioneSquadre.aggiornaPartiteG(squadraOspite, (partiteGSO + 1));
							ConnessioneSquadre.aggiornaPartiteVinte(squadraOspite, (partiteVSO + 1));
							ConnessioneSquadre.aggiornaPartitePerse(squadraCasa, (partiteVSC + 1));

						}
						
						
						new RegistraRisultato();
						frame.setVisible(false);
					}

					else {
						ControllerCalendario.visualizzaErrore("I risultati devono essere compresi tra 0-99!");
						return;
					}

				} else if (nameSport.equalsIgnoreCase("basket")) {

				}
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	};

}
