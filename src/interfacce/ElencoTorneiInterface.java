package interfacce;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.text.MaskFormatter;

import Controller.ControllerTorneo;
import commons.Torneo;
import gestori.GestoreDatiPersistenti;

public class ElencoTorneiInterface {
	private static ElencoTorneiInterface istanza;
	public static JComboBox<String> cbName;
	private static ControllerTorneo ct;
	private HashMap<String, Torneo> tornei;

	public static ElencoTorneiInterface getInstance() throws ParseException {
		if (istanza == null)
			istanza = new ElencoTorneiInterface();
		return istanza;
	}

	public ElencoTorneiInterface() throws ParseException {
		ct = ControllerTorneo.getInstance();

		frame = new JFrame("FGRtournament");
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(400, 100);

		tornei = (HashMap<String, Torneo>) ct.getTornei();

		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		panel1.setLayout(new BorderLayout());
		panel2.setLayout(new BorderLayout());
		JLabel label1 = new JLabel("Crea o seleziona il torneo");
		panel1.add(label1);
		frame.add(panel1, BorderLayout.NORTH);
		frame.add(panel2, BorderLayout.SOUTH);
		JButton buttonCreaTorneo = new JButton("CREA TORNEO");
		JButton buttonIndietro = new JButton("INDIETRO");
		panel2.add(buttonCreaTorneo, BorderLayout.EAST);
		panel2.add(buttonIndietro, BorderLayout.WEST);

		JPanel panel3 = new JPanel();
		panel3.setLayout(new BorderLayout());
		frame.add(panel3, BorderLayout.CENTER);

		String name;
		cbName = new JComboBox<String>();

		for (Entry<String, Torneo> entry : tornei.entrySet()) {
			name = entry.getValue().getSport();
			cbName.addItem(name);
		}

		JButton buttonVai = new JButton("SELEZIONA");
		panel2.add(buttonVai, BorderLayout.CENTER);

		ActionListener vai = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				try {
					new MenuPrincipale(cbName.getSelectedItem().toString());

				} catch (Exception e1) {
					ControllerTorneo.visualizzaErrore("Creare un torneo!");
					try {
						new ElencoTorneiInterface().aggiornaComboBox();
					} catch (ParseException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					};

				}
			}
		};

		panel3.add(cbName, BorderLayout.NORTH);
		ActionListener creaTorneo = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					frame.setVisible(false);
					new FormCreaTorneo();
				} catch (HeadlessException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};

		ActionListener indietro = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InterfacciaIniziale();
				frame.setVisible(false);
			}
		};

		buttonVai.addActionListener(vai);
		buttonIndietro.addActionListener(indietro);
		buttonCreaTorneo.addActionListener(creaTorneo);

		frame.pack();
		frame.setVisible(true);
		frame.setSize(500, 200);

	}

	public void aggiornaComboBox() throws ParseException {
		cbName.removeAllItems();
		ct.getTornei();
		String name;
		for (Entry<String, Torneo> entry : tornei.entrySet()) {
			name = entry.getValue().getSport();
			cbName.addItem(name);
		}
	}

	public static void main(String[] args) throws ParseException {
		ElencoTorneiInterface.getInstance();
	}

	public static String prendiSport() {
		return cbName.getSelectedItem().toString();
	}

	public static Torneo prendiTorneo() throws ParseException {
		return ct.getTornei().get(cbName.getSelectedItem().toString());
	}

	public JFrame frame;

}
