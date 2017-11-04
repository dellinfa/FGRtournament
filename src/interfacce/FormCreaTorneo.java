
package interfacce;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.RadialGradientPaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.DateTimeException;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import Controller.ControllerTorneo;
import commons.Calendario;
import commons.Classifica;
import commons.Partecipante;
import connessioniClassiDataBase.ConnessioneTorneo;

public class FormCreaTorneo {

	private ElencoTorneiInterface et;

	public FormCreaTorneo() throws HeadlessException, ParseException {

		frame = new JFrame("FGRtournament");
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(400, 100);

		et = ElencoTorneiInterface.getInstance();
		JPanel panel1 = new JPanel();
		JLabel label1 = new JLabel("Compila i campi");
		panel1.add(label1);
		frame.add(panel1, BorderLayout.NORTH);

		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		JButton buttonIndietro = new JButton("INDIETRO");
		JButton buttonCrea = new JButton("CREA");
		panel2.add(buttonCrea, BorderLayout.EAST);
		panel2.add(buttonIndietro, BorderLayout.WEST);
		frame.add(panel2, BorderLayout.SOUTH);

		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayout(15, 2));

		JLabel label2 = new JLabel("Sport");
		JTextField sport = new JTextField(20);
		// JLabel label3=new JLabel("Modalità svolgimento incontro");
		// String[] modSvolgimentoIncontri = new String[] {"Squadra", "Singola"};
		// JComboBox<String> cbModSvolgimentoIncontri = new
		// JComboBox<>(modSvolgimentoIncontri);
		JLabel label8 = new JLabel("Numero massimo di partecipanti");
		String[] numMaxPartecipanti = new String[] { "2", "4", "6", "8", "10" };
		JComboBox<String> cbMaxPartecipanti = new JComboBox<>(numMaxPartecipanti);
		// JLabel label4=new JLabel("Modalità attribuzione punti
		// (Vincente-Pareggio-Perdente)");
		// String[] modAttribuzionePt = new String[]{"3-1-0", "2-1-0", "1-0-0"};
		// JComboBox<String> cbAttribuzionePt = new JComboBox<>(modAttribuzionePt);
		//JTextField modAttribuzioniPt = new JTextField(20);
		JLabel label6 = new JLabel("Data inizio torneo");
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd/MM/yyyy");
		dateChooser.setBounds(588, 59, 163, 40);
		JLabel label7 = new JLabel("Data fine torneo");
		JDateChooser dateChooser1 = new JDateChooser();
		dateChooser1.setDateFormatString("dd/MM/yyyy");
		dateChooser1.setBounds(588, 59, 163, 40);

		panel3.add(label2);
		panel3.add(sport);
		// panel3.add(label3);
		// panel3.add(cbModSvolgimentoIncontri);
		panel3.add(label6);
		panel3.add(dateChooser);
		panel3.add(label7);
		panel3.add(dateChooser1);
		panel3.add(label8);
		panel3.add(cbMaxPartecipanti);
		// panel3.add(label4);
		// panel3.add(cbAttribuzionePt);

		ActionListener back = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new ElencoTorneiInterface();
				} catch (ParseException e1) {

					e1.printStackTrace();
				}
				frame.setVisible(false);
			}
		};

		ActionListener crea = new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String sp, mAPG, mSI;
				int numP;
				String prova;
				Calendario Cal = new Calendario(null, null);
				Classifica Cla = new Classifica(null, null);
				Date dI, dF;

				sp = sport.getText();
				if (sport.getText().equalsIgnoreCase("calcio") || sport.getText().equalsIgnoreCase("pallavolo")
						|| sport.getText().equalsIgnoreCase("basket")
						|| sport.getText().equalsIgnoreCase("ping pong")) {

					numP = Integer.parseInt((String) cbMaxPartecipanti.getSelectedItem());
					dI = dateChooser.getDate();
					
//					Calendar today = Calendar.getInstance();
//					int day = today.get(Calendar.DAY_OF_MONTH);
//					int month = today.get(Calendar.MONTH) + 1;
//					int year = today.get(Calendar.YEAR);

					Calendar dICal = Calendar.getInstance();

					try {
						dICal.setTime(dI);
						if(dI.before(new Date())){
							throw new IllegalArgumentException(); 
						}
					} catch(IllegalArgumentException e3) {
						ControllerTorneo.visualizzaErrore("La data di inizio torneo è antecedente o uguale a quella odierna!");
						return;
					}
					catch(NullPointerException e3) {
						ControllerTorneo.visualizzaErrore("Errore data inizio!");
						return;
					}
					int dayI = dICal.get(Calendar.DAY_OF_MONTH);
					int monthI = dICal.get(Calendar.MONTH) + 1;
					int yearI = dICal.get(Calendar.YEAR);

//					se il giorno della prenotazione Ã¨ antecedente a quello odierno
//					if (dICal.before(new Date())) {
//						ControllerTorneo.visualizzaErrore("La data di inizio torneo è antecedente a quella odierna!");
//						return;
//					}

					dF = dateChooser1.getDate();
					Calendar dFCal = Calendar.getInstance();

					try {
						dFCal.setTime(dF);
					} catch (Exception e2) {
						ControllerTorneo.visualizzaErrore("Errore data fine");
						return;
					}
					int dayF = dFCal.get(Calendar.DAY_OF_MONTH);
					int monthF = dFCal.get(Calendar.MONTH) + 1;
					int yearF = dFCal.get(Calendar.YEAR);

					// se il giorno della prenotazione Ã¨ antecedente a quello odierno
					if (dFCal.before(new Date()) || dF.before(dI)) {
						ControllerTorneo.visualizzaErrore("La data di fine torneo è errata!");
						return;
					}

					// mAPG = cbAttribuzionePt.getSelectedItem().toString() ;
					//
					// mSI = cbModSvolgimentoIncontri.getSelectedItem().toString();

					ConnessioneTorneo cg = new ConnessioneTorneo();
					
					try {

						
						
						switch (sp.toUpperCase()) {
						
						case "PALLAVOLO":
							cg.saveTorneo(sp, "Squadra", "2-0-0", numP, dI, dF, Cal.toString(), Cla.toString());
							JOptionPane.showMessageDialog(frame, "Torneo creato: " + sp.toUpperCase()
									+ " \n Modalità svolgimento: Squadra \n Attribuzione punti: Vincitore-2 Perdente-0 \n Data inizio: "
									+ dayI + "-" + monthI + "-" + yearI + "\n Data fine: " + dayF + "-" + monthF + "-"
									+ yearF+ "\n Numero partecipanti: "+numP);

							break;
						case "CALCIO":
							cg.saveTorneo(sp, "Squadra", "3-1-0", numP, dI, dF, Cal.toString(), Cla.toString());

							JOptionPane.showMessageDialog(frame, "Torneo creato: " + sp.toUpperCase()
									+ " \n Modalità svolgimento: Squadra \n Attribuzione punti: Vincitore-3 Pareggio-1 Perdente-0 \n Data inizio: "
									+ dayI + "-" + monthI + "-" + yearI + "\n Data fine: " + dayF + "-" + monthF + "-"
									+ yearF+ "\n Numero partecipanti: "+numP);

							break;

						
						case "BASKET":
							cg.saveTorneo(sp, "Squadra", "2-0-0", numP, dI, dF, Cal.toString(), Cla.toString());
							JOptionPane.showMessageDialog(frame, "Torneo creato: " + sp.toUpperCase()
									+ " \n Modalità svolgimento: Squadra \n Attribuzione punti: Vincitore-2 Perdente-0 \n Data inizio: "
									+ dayI + "-" + monthI + "-" + yearI + "\n Data fine: " + dayF + "-" + monthF + "-"
									+ yearF+ "\n Numero partecipanti: "+numP);

							break;
						case "PING PONG":
							cg.saveTorneo(sp, "Singola", "1-0-0", numP, dI, dF, Cal.toString(), Cla.toString());
						JOptionPane.showMessageDialog(frame, "Torneo creato: " + sp.toUpperCase()
								+ " \n Modalità svolgimento: Singola \n Attribuzione punti: Vincitore-1 Perdente-0 \n Data inizio: "
									+ dayI + "-" + monthI + "-" + yearI + "\n Data fine: " + dayF + "-" + monthF + "-"
									+ yearF+ "\n Numero partecipanti: "+numP);

				break;
				}


					} 
					catch (SQLException e1) {						
						ControllerTorneo.visualizzaErrore("Torneo già esistente!");
					} catch (ParseException e1) {
						ControllerTorneo.visualizzaErrore("Impossibile creare il torneo!");

					}
					try {
						et.aggiornaComboBox();
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					et.frame.pack();
					et.frame.setVisible(true);
					frame.setVisible(false);
					

				} else {
					try {
						new FormCreaTorneo();
					} catch (HeadlessException | ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					frame.setVisible(false);
					JOptionPane.showMessageDialog(null, "Torneo non concesso!", "ErroreTorneo",
							JOptionPane.ERROR_MESSAGE);

				}
			}
		};

		buttonCrea.addActionListener(crea);
		buttonIndietro.addActionListener(back);
		panel2.add(panel3);
		frame.add(panel3, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		frame.setSize(500, 400);
	}

	private JFrame frame;
	private JList maxPartecipantiList;

}
