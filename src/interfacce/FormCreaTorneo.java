
package interfacce;


import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.GridLayout;
import java.awt.RadialGradientPaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import java.util.Enumeration;
import java.sql.SQLException;
import java.text.ParseException;

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

import commons.Calendario;
import connessioniClassiDataBase.ConnessioneTorneo;

public class FormCreaTorneo {
	
	public FormCreaTorneo() {
		
		frame= new JFrame("FGRtournament");
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(400, 100);
		
		JPanel panel1= new JPanel();
		JLabel label1= new JLabel("Compila i campi");
		panel1.add(label1);
		frame.add(panel1, BorderLayout.NORTH);
		
		JPanel panel2=new JPanel();
		panel2.setLayout(new BorderLayout());
		JButton buttonIndietro = new JButton("INDIETRO");
		JButton buttonCrea = new JButton("CREA");
		panel2.add(buttonCrea, BorderLayout.EAST);
		panel2.add(buttonIndietro, BorderLayout.WEST);
		frame.add(panel2, BorderLayout.SOUTH);
		
		JPanel panel3=new JPanel();
		panel3.setLayout(new GridLayout(15,2));
		
		JLabel label2=new JLabel("Sport");
		JTextField sport =new JTextField(20);
		JLabel label3=new JLabel("Modalit� svolgimento incontro");
		JRadioButton modSvolgimentoIncontroSingola =new JRadioButton("Singola");
		JRadioButton modSvolgimentoIncontroSquadra =new JRadioButton("Squadra");
		ButtonGroup group= new ButtonGroup();
		group.add(modSvolgimentoIncontroSquadra);
		group.add(modSvolgimentoIncontroSingola);
		modSvolgimentoIncontroSingola.setSelected(false);
		modSvolgimentoIncontroSquadra.setSelected(false);
		JLabel label8=new JLabel("Numero massimo di partecipanti");
		String[] numMaxPartecipanti  = new String[] {"2", "4", "6", "8", "10"};
		JComboBox<String> cbMaxPartecipanti = new JComboBox<>(numMaxPartecipanti);
		JLabel label4=new JLabel("Modalit� attribuzione punti (Vincente-Pareggio-Perdente)");
		String[] modAttribuzionePt  = new String[]{"0", "1", "2"};
		JComboBox<String> cbAttribuzionePt = new JComboBox<>(modAttribuzionePt);
		JTextField modAttribuzioniPt=new JTextField(20);
		JLabel label5=new JLabel("Intervallo tempo");
		JTextField intervalloTempo=new JTextField(20);
		JLabel label6=new JLabel("Data inizio torneo");
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd/MM/yyyy");
		dateChooser.setBounds(588, 59, 163, 40);
        JLabel label7=new JLabel("Data fine torneo");
        JDateChooser dateChooser1 = new JDateChooser();
		dateChooser1.setDateFormatString("dd/MM/yyyy");
		dateChooser1.setBounds(588, 59, 163, 40);
		
		panel3.add(label2);
		panel3.add(sport);
		panel3.add(label3);
		panel3.add(modSvolgimentoIncontroSingola);
		panel3.add(modSvolgimentoIncontroSquadra);
		panel3.add(label6);
		panel3.add(dateChooser);
		panel3.add(label7);
		panel3.add(dateChooser1);
		panel3.add(label8);
		panel3.add(cbMaxPartecipanti);
		panel3.add(label4);
		panel3.add(cbAttribuzionePt);
		panel3.add(label5);
		panel3.add(intervalloTempo);	
		
		ActionListener back=new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new ElencoTorneiInterface();
				frame.setVisible(false);
			}
		};
		

		ActionListener crea=new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new ElencoTorneiInterface();
				frame.setVisible(false);
				
				
				String sp,mAPG,mSI;
				int maxP,inT;
				String prova;
				Calendario C = new Calendario(null, null);
				Date dI,dF;
				
				
				
				sp=sport.getText();
				maxP = Integer.parseInt((String) cbMaxPartecipanti.getSelectedItem());
				inT =Integer.parseInt((intervalloTempo.getText())) ;
				dI = dateChooser.getDate();
				dF = dateChooser1.getDate();
				prova = dI.toString();
				
				
				mAPG = cbAttribuzionePt.getSelectedItem().toString() ;
				if(modSvolgimentoIncontroSingola.isSelected())
					mSI = modSvolgimentoIncontroSingola.getText();
				else
					mSI = modSvolgimentoIncontroSquadra.getText();
				System.out.println(prova);
				
				ConnessioneTorneo cg = new ConnessioneTorneo();
				

				try {
					cg.saveTorneo(mSI,mAPG,maxP,inT,dI,dF,C.toString(),sp);
					System.out.println("okokokok");
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				
			}
		};
			
		buttonCrea.addActionListener(crea);	// deve anche aggiungere il tonreo al db
		buttonIndietro.addActionListener(back);
		panel2.add(panel3);
		frame.add(panel3,BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		frame.setSize(500,600);
	}
	private JFrame frame;
	private JList maxPartecipantiList; 

}

