package interfacce;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.ControllerCalendario;
import commons.Calendario;

public class MenuPrincipale {

	private HashMap<String, Calendario> calendari;
	private ControllerCalendario cc;

	public MenuPrincipale(String sport) throws ParseException {
		this.sport = sport;

		frame = new JFrame("FGRtournament");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setLocation(400, 100);

		JPanel panel1 = new JPanel();
		JLabel label1 = new JLabel("Seleziona un'operazione da effettuare");
		panel1.add(label1);
		frame.add(label1, BorderLayout.NORTH);

		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(4, 1));
		JButton calendario = new JButton("Calendario");
		panel2.add(calendario);
		frame.add(panel2, BorderLayout.CENTER);

		JButton classifica = new JButton("Classifica");
		panel2.add(classifica);

		JButton inserisciPartecipante = new JButton("Inserisci partecipante");
		panel2.add(inserisciPartecipante);

		cc = ControllerCalendario.getInstance();
		calendari = cc.getCalendario();

		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayout(1, 1));
		JButton indietro = new JButton("Indietro");
		panel3.add(indietro);
		frame.add(panel3, BorderLayout.SOUTH);

		ActionListener cale = new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					frame.setVisible(false);
					new CalendarioInterface();
					
					
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		};

		ActionListener cla = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				try {
					boolean trovato = false;
					for (Entry<String, Calendario> entry : calendari.entrySet()) {
						if (ElencoTorneiInterface.prendiTorneo().getSport()
								.equalsIgnoreCase(entry.getValue().getIdCalendario())) {
							trovato = true;
						}
					}
					if (trovato) {
						new ClassificaInterface();
					} else {
						ControllerCalendario
								.visualizzaErrore("Non sono stati inseriti tutti i partecipanti per questo torneo!");
						new MenuPrincipale(ElencoTorneiInterface.prendiTorneo().getSport());
					}

				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};

		ActionListener insert;

		insert = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				try {
					new InserisciPartecipanteInterface();
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}

		};

		ActionListener back = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					frame.setVisible(false);
					new ElencoTorneiInterface();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
			}
		};

		calendario.addActionListener(cale);
		classifica.addActionListener(cla);
		inserisciPartecipante.addActionListener(insert);
		indietro.addActionListener(back);

		frame.pack();
		frame.setVisible(true);
		frame.setSize(500, 250);

	}

	public static void main(String[] args) throws ParseException {
		new MenuPrincipale(sport);
	}

	private static String sport;
	private JFrame frame;

}
