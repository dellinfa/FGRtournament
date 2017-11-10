package interfacce;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.sound.midi.SysexMessage;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicOptionPaneUI;

import Controller.ControllerCalendario;
import Controller.ControllerTorneo;
import commons.Giocatore;
import commons.Partita;
import commons.Squadra;
import commons.Torneo;
import connessioniClassiDataBase.ConnessioneCalendario;
import connessioniClassiDataBase.ConnessioneGiocatore;
import connessioniClassiDataBase.ConnessionePartite;
import connessioniClassiDataBase.ConnessioneSquadre;
import gestori.GestoreDatiPersistenti;

public class RegistraRisultato {
	private JFrame frame;
	private HashMap<String, Squadra> squadre = new HashMap<>();
	private HashMap<String, Giocatore> giocatori = new HashMap<>();
	private HashMap<String, Partita> partite = new HashMap<>();
	private ControllerTorneo ct;
	private ControllerCalendario cc;
	private static RegistraRisultato istanza;
	private JComboBox<String> cb;
	private JTextField tf1, tf2;
	private String s = null, partecipanteCasa = null, partecipanteOspite = null;
	private int puntiSC = 0, partiteGSC = 0, partiteVSC = 0, partitePSC = 0, puntiSO = 0, partiteGSO = 0,
			partiteVSO = 0, partitePSO = 0, j = 0;
	private boolean partiteFinite = false;

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
		giocatori = ct.getGiocatori();

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
					try {
						if (s.equalsIgnoreCase(entry.getValue().getIdPartita())) {
							labelCB.setText(entry.getValue().getIdPartecipanteCasa() + " - "
									+ entry.getValue().getIdPartecipanteOspite());
							partecipanteCasa = entry.getValue().getIdPartecipanteCasa();

							partecipanteOspite = entry.getValue().getIdPartecipanteOspite();
						}
					} catch (Exception e) {
						try {
							decretaVincitore();
						} catch (HeadlessException | ParseException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});

		buttonIndietro.addActionListener(indietro);
		buttonRegistraRisultato.addActionListener(registraRisultato);

		if (cb.getItemCount() == 0) {

			decretaVincitore();
			frame.setVisible(false);
			WindowEvent closingEvent = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
		    Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closingEvent);
		}

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
						try {
							for (Entry<String, Squadra> entry : squadre.entrySet()) {
								if (partecipanteCasa.equalsIgnoreCase(entry.getValue().getId())) {
									puntiSC = entry.getValue().getPunti();
									partiteGSC = entry.getValue().getPartiteGiocate();
									partiteVSC = entry.getValue().getPartiteVinte();
									partitePSC = entry.getValue().getPartitePerse();

								} else if (partecipanteOspite.equalsIgnoreCase(entry.getValue().getId())) {
									puntiSO = entry.getValue().getPunti();
									partiteGSO = entry.getValue().getPartiteGiocate();
									partiteVSO = entry.getValue().getPartiteVinte();
									partitePSO = entry.getValue().getPartitePerse();

								}
							}
						} catch (Exception ee) {
							ControllerCalendario.visualizzaErrore("Partita non selezionata correttamente!");
							return;
						}

						if (casa > ospite) {
							ConnessioneSquadre.aggiornaPuntiSquadra(partecipanteCasa, (puntiSC + 3));
							ConnessioneSquadre.aggiornaPartiteG(partecipanteCasa, (partiteGSC + 1));
							ConnessioneSquadre.aggiornaPartiteG(partecipanteOspite, (partiteGSO + 1));
							ConnessioneSquadre.aggiornaPartiteVinte(partecipanteCasa, (partiteVSC + 1));
							ConnessioneSquadre.aggiornaPartitePerse(partecipanteOspite, (partitePSO + 1));

						} else if (casa == ospite) {
							ConnessioneSquadre.aggiornaPuntiSquadra(partecipanteCasa, (puntiSC + 1));
							ConnessioneSquadre.aggiornaPuntiSquadra(partecipanteOspite, (puntiSO + 1));
							ConnessioneSquadre.aggiornaPartiteG(partecipanteCasa, (partiteGSC + 1));
							ConnessioneSquadre.aggiornaPartiteG(partecipanteOspite, (partiteGSO + 1));

						} else if (casa < ospite) {
							ConnessioneSquadre.aggiornaPuntiSquadra(partecipanteOspite, (puntiSO + 3));
							ConnessioneSquadre.aggiornaPartiteG(partecipanteCasa, (partiteGSC + 1));
							ConnessioneSquadre.aggiornaPartiteG(partecipanteOspite, (partiteGSO + 1));
							ConnessioneSquadre.aggiornaPartiteVinte(partecipanteOspite, (partiteVSO + 1));
							ConnessioneSquadre.aggiornaPartitePerse(partecipanteCasa, (partitePSC + 1));

						}

						ConnessionePartite.removePartita(idPartita);
						if (cb.getItemCount() != 0) {
							cb.removeItem(idPartita);
						}

						if (cb.getItemCount() == 0) {
							decretaVincitore();
							frame.setVisible(false);
							new MenuPrincipale(ElencoTorneiInterface.prendiSport());
							return;
						}

					}

					else {
						ControllerCalendario.visualizzaErrore("I risultati devono essere compresi tra 0-99!");
						return;
					}

				} else if (nameSport.equalsIgnoreCase("basket")) {
					try {
						casa = Integer.parseInt(tf1.getText());

					} catch (Exception ee) {
						ControllerCalendario.visualizzaErrore("Risultato 'casa' errato!");
						return;
					}
					try {
						ospite = Integer.parseInt(tf2.getText());

					} catch (Exception e1) {
						ControllerCalendario.visualizzaErrore("Risultato 'ospite' errato!");
						return;
					}

					if (casa >= 0 && casa <= 999 && ospite >= 0 && ospite <= 999) {
						if (casa == ospite) {
							ControllerCalendario.visualizzaErrore("La partita non può terminare in pareggio!");
							return;
						} else {

							ConnessionePartite.setRisultatoCasaToPartita(idPartita, casa);
							ConnessionePartite.setRisultatoOspiteToPartita(idPartita, ospite);

							tf1.setText("");
							tf2.setText("");
							try {
								for (Entry<String, Squadra> entry : squadre.entrySet()) {
									if (partecipanteCasa.equalsIgnoreCase(entry.getValue().getId())) {
										puntiSC = entry.getValue().getPunti();
										partiteGSC = entry.getValue().getPartiteGiocate();
										partiteVSC = entry.getValue().getPartiteVinte();
										partitePSC = entry.getValue().getPartitePerse();

									} else if (partecipanteOspite.equalsIgnoreCase(entry.getValue().getId())) {
										puntiSO = entry.getValue().getPunti();
										partiteGSO = entry.getValue().getPartiteGiocate();
										partiteVSO = entry.getValue().getPartiteVinte();
										partitePSO = entry.getValue().getPartitePerse();

									}
								}
							} catch (Exception ee) {
								ControllerCalendario.visualizzaErrore("Partita non selezionata correttamente!");
								return;
							}

							if (casa > ospite) {
								ConnessioneSquadre.aggiornaPuntiSquadra(partecipanteCasa, (puntiSC + 2));
								ConnessioneSquadre.aggiornaPartiteG(partecipanteCasa, (partiteGSC + 1));
								ConnessioneSquadre.aggiornaPartiteG(partecipanteOspite, (partiteGSO + 1));
								ConnessioneSquadre.aggiornaPartiteVinte(partecipanteCasa, (partiteVSC + 1));
								ConnessioneSquadre.aggiornaPartitePerse(partecipanteOspite, (partitePSO + 1));

							} else if (casa < ospite) {
								ConnessioneSquadre.aggiornaPuntiSquadra(partecipanteOspite, (puntiSO + 2));
								ConnessioneSquadre.aggiornaPartiteG(partecipanteCasa, (partiteGSC + 1));
								ConnessioneSquadre.aggiornaPartiteG(partecipanteOspite, (partiteGSO + 1));
								ConnessioneSquadre.aggiornaPartiteVinte(partecipanteOspite, (partiteVSO + 1));
								ConnessioneSquadre.aggiornaPartitePerse(partecipanteCasa, (partitePSC + 1));

							}

							ConnessionePartite.removePartita(idPartita);
							if (cb.getItemCount() != 0) {
								cb.removeItem(idPartita);
							}
							if (cb.getItemCount() == 0) {
								decretaVincitore();
								frame.setVisible(false);

								return;
							}
						}
					} else {
						ControllerCalendario.visualizzaErrore("I risultati devono essere compresi tra 0-999!");
						return;
					}
				} else if (nameSport.equalsIgnoreCase("ping pong")) {
					try {
						casa = Integer.parseInt(tf1.getText());
						System.out.println(casa);

					} catch (Exception ee) {
						ControllerCalendario.visualizzaErrore("Risultato 'casa' errato!");
						return;
					}
					try {
						ospite = Integer.parseInt(tf2.getText());
						System.out.println(ospite);

					} catch (Exception e1) {
						ControllerCalendario.visualizzaErrore("Risultato 'ospite' errato!");
						return;
					}

					if (casa >= 0 && casa <= 21 && ospite >= 0 && ospite <= 21) {
						if (casa == ospite) {
							ControllerCalendario.visualizzaErrore("La partita non può terminare in pareggio!");
							return;
						} else if (casa != 21 && ospite != 21) {
							ControllerCalendario
									.visualizzaErrore("Casa o Ospite deve avere il punteggio di vittoria(21)!");
							return;
						} else {

							ConnessionePartite.setRisultatoCasaToPartita(idPartita, casa);
							ConnessionePartite.setRisultatoOspiteToPartita(idPartita, ospite);

							tf1.setText("");
							tf2.setText("");
							try {
								for (Entry<String, Giocatore> entry : giocatori.entrySet()) {
									if (partecipanteCasa.equalsIgnoreCase(entry.getValue().getId())) {
										puntiSC = entry.getValue().getPunti();
										System.out.println(puntiSC);
										partiteGSC = entry.getValue().getPartiteGiocate();
										partiteVSC = entry.getValue().getPartiteVinte();
										partitePSC = entry.getValue().getPartitePerse();

									} else if (partecipanteOspite.equalsIgnoreCase(entry.getValue().getId())) {
										puntiSO = entry.getValue().getPunti();
										System.out.println(puntiSO);
										partiteGSO = entry.getValue().getPartiteGiocate();
										partiteVSO = entry.getValue().getPartiteVinte();
										partitePSO = entry.getValue().getPartitePerse();

									}
								}
							} catch (Exception ee) {
								ControllerCalendario.visualizzaErrore("Partita non selezionata correttamente!");
								return;
							}

							if (casa > ospite) {
								ConnessioneGiocatore.aggiornaPuntiGiocatore(partecipanteCasa, (puntiSC + 1));
								ConnessioneGiocatore.aggiornaPartiteG(partecipanteCasa, (partiteGSC + 1));
								ConnessioneGiocatore.aggiornaPartiteG(partecipanteOspite, (partiteGSO + 1));
								ConnessioneGiocatore.aggiornaPartiteVinte(partecipanteCasa, (partiteVSC + 1));
								ConnessioneGiocatore.aggiornaPartitePerse(partecipanteOspite, (partitePSO + 1));

							} else if (casa < ospite) {
								ConnessioneGiocatore.aggiornaPuntiGiocatore(partecipanteOspite, (puntiSO + 1));
								ConnessioneGiocatore.aggiornaPartiteG(partecipanteCasa, (partiteGSC + 1));
								ConnessioneGiocatore.aggiornaPartiteG(partecipanteOspite, (partiteGSO + 1));
								ConnessioneGiocatore.aggiornaPartiteVinte(partecipanteOspite, (partiteVSO + 1));
								ConnessioneGiocatore.aggiornaPartitePerse(partecipanteCasa, (partitePSC + 1));

							}

							ConnessionePartite.removePartita(idPartita);
							if (cb.getItemCount() != 0) {
								cb.removeItem(idPartita);
							}

							else if (cb.getItemCount() == 0) {
								decretaVincitore();
								return;
							}
						}
					} else {
						ControllerCalendario.visualizzaErrore("I risultati devono essere compresi tra 0-21!");
						return;
					}
				} else if (nameSport.equalsIgnoreCase("pallavolo")) {
					try {
						casa = Integer.parseInt(tf1.getText());

					} catch (Exception ee) {
						ControllerCalendario.visualizzaErrore("Risultato 'casa' errato!");
						return;
					}
					try {
						ospite = Integer.parseInt(tf2.getText());

					} catch (Exception e1) {
						ControllerCalendario.visualizzaErrore("Risultato 'ospite' errato!");
						return;
					}

					if (casa >= 0 && casa <= 3 && ospite >= 0 && ospite <= 3) {
						if (casa == ospite) {
							ControllerCalendario.visualizzaErrore("La partita non può terminare in pareggio!");
							return;
						} else if (casa != 3 && ospite != 3) {
							ControllerCalendario
									.visualizzaErrore("Casa o Ospite deve avere il punteggio di vittoria(3)!");
							return;
						} else {
							ConnessionePartite.setRisultatoCasaToPartita(idPartita, casa);
							ConnessionePartite.setRisultatoOspiteToPartita(idPartita, ospite);

							tf1.setText("");
							tf2.setText("");
							try {
								for (Entry<String, Squadra> entry : squadre.entrySet()) {
									if (partecipanteCasa.equalsIgnoreCase(entry.getValue().getId())) {
										System.out.println(partecipanteCasa);
										puntiSC = entry.getValue().getPunti();
										System.out.println(puntiSC);
										partiteGSC = entry.getValue().getPartiteGiocate();
										partiteVSC = entry.getValue().getPartiteVinte();
										partitePSC = entry.getValue().getPartitePerse();

									} else if (partecipanteOspite.equalsIgnoreCase(entry.getValue().getId())) {
										System.out.println(partecipanteOspite);
										puntiSO = entry.getValue().getPunti();
										System.out.println(puntiSO);
										partiteGSO = entry.getValue().getPartiteGiocate();
										partiteVSO = entry.getValue().getPartiteVinte();
										partitePSO = entry.getValue().getPartitePerse();

									}
								}
							} catch (Exception ee) {
								ControllerCalendario.visualizzaErrore("Partita non selezionata correttamente!");
								return;
							}

							if (casa > ospite) {
								ConnessioneSquadre.aggiornaPuntiSquadra(partecipanteCasa, (puntiSC + 2));
								ConnessioneSquadre.aggiornaPartiteG(partecipanteCasa, (partiteGSC + 1));
								ConnessioneSquadre.aggiornaPartiteG(partecipanteOspite, (partiteGSO + 1));
								ConnessioneSquadre.aggiornaPartiteVinte(partecipanteCasa, (partiteVSC + 1));
								ConnessioneSquadre.aggiornaPartitePerse(partecipanteOspite, (partitePSO + 1));

							} else if (casa < ospite) {
								ConnessioneSquadre.aggiornaPuntiSquadra(partecipanteOspite, (puntiSO + 2));
								ConnessioneSquadre.aggiornaPartiteG(partecipanteCasa, (partiteGSC + 1));
								ConnessioneSquadre.aggiornaPartiteG(partecipanteOspite, (partiteGSO + 1));
								ConnessioneSquadre.aggiornaPartiteVinte(partecipanteOspite, (partiteVSO + 1));
								ConnessioneSquadre.aggiornaPartitePerse(partecipanteCasa, (partitePSC + 1));

							}

							ConnessionePartite.removePartita(idPartita);
							if (cb.getItemCount() != 0) {
								cb.removeItem(idPartita);
							}

							else if (cb.getItemCount() == 0) {
								decretaVincitore();
								frame.setVisible(false);

								return;
							}

						}
					} else {
						ControllerCalendario.visualizzaErrore("I risultati devono essere compresi tra 0-3!");
						return;
					}
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

	public void decretaVincitore() throws HeadlessException, ParseException, SQLException {
		
		

		Comparator<Integer> comparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer left, Integer right) {
				return right - left;
			}
		};

		ArrayList<Integer> listPunti = new ArrayList<>();
		ArrayList<String> listVincitori = new ArrayList<>();

		if (ElencoTorneiInterface.prendiTorneo().getSport().equalsIgnoreCase("ping pong")) {
			try {
				for (Entry<String, Giocatore> entry : giocatori.entrySet()) {
					if (ElencoTorneiInterface.prendiTorneo().getSport()
							.equalsIgnoreCase(GestoreDatiPersistenti.torneoGiocatoreS(entry.getValue().getId()))) {

						listPunti.add(j, entry.getValue().getPunti());
						j++;
					}
				}

				Collections.sort(listPunti, comparator);

				for (Entry<String, Giocatore> entry : giocatori.entrySet()) {
					if (ElencoTorneiInterface.prendiTorneo().getSport()
							.equalsIgnoreCase(GestoreDatiPersistenti.torneoGiocatoreS(entry.getValue().getId()))) {
						if (entry.getValue().getPunti() == listPunti.get(0)) {
							listVincitori.add(entry.getValue().getId());
						}
					}
				}
			} catch (Exception e) {
				System.err.println("Decretato vincitore");
				return;
			}

			if (listVincitori.size() == 1) {
				JOptionPane.showMessageDialog(frame,
						"Il torneo è terminato, il vincitore è :\n" + listVincitori.toString());

				WindowEvent closingEvent = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
			    Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closingEvent);
				return;
			} else if (listVincitori.size()>1){
				JOptionPane.showMessageDialog(frame,
						"Il torneo è terminato, i vincitori sono :\n" + listVincitori.toString());
				frame.setVisible(false);
				WindowEvent closingEvent = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
			    Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closingEvent);

			}
			else {
				frame.setVisible(false);
				new MenuPrincipale(ElencoTorneiInterface.prendiTorneo().getSport());
				return;
			}
		} else {
			try {
				for (Entry<String, Squadra> entry : squadre.entrySet()) {
					if (ElencoTorneiInterface.prendiTorneo().getSport()
							.equalsIgnoreCase(GestoreDatiPersistenti.torneoSquadraS(entry.getValue().getId()))) {
						listPunti.add(j, entry.getValue().getPunti());
						j++;
					}
				}

				Collections.sort(listPunti, comparator);

				for (Entry<String, Squadra> entry : squadre.entrySet()) {
					if (ElencoTorneiInterface.prendiTorneo().getSport()
							.equalsIgnoreCase(GestoreDatiPersistenti.torneoSquadraS(entry.getValue().getId()))) {
						if (entry.getValue().getPunti() == listPunti.get(0)) {
							listVincitori.add(entry.getValue().getId());
						}
					}
				}
			} catch (Exception e) {
				System.err.println("Decretato vincitore");
				return;
			}

			if (listVincitori.size() == 1) {
				JOptionPane.showMessageDialog(frame,
						"Il torneo è terminato, il vincitore è :\n" + listVincitori.toString());
				frame.setVisible(false);
				WindowEvent closingEvent = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
			    Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closingEvent);
			} else if (listVincitori.size()>1){
				JOptionPane.showMessageDialog(frame,
						"Il torneo è terminato, i vincitori sono :\n" + listVincitori.toString());
				frame.setVisible(false);
				WindowEvent closingEvent = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
			    Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closingEvent);

			}
			else {
				frame.setVisible(false);
				new MenuPrincipale(ElencoTorneiInterface.prendiTorneo().getSport());
				return;
			}

		}
	}

}
