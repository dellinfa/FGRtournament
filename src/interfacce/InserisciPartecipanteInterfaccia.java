
package interfacce;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import connessioniClassiDataBase.ConnesioneGiocatore;

public class InserisciPartecipanteInterfaccia {
	
	public InserisciPartecipanteInterfaccia() {
		
		frame= new JFrame();
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(250, 250);
		
		JPanel panel1= new JPanel();
		JLabel label1= new JLabel("Scegli tipo di partecipante:");
		panel1.add(label1);
		frame.add(panel1, BorderLayout.NORTH);
		
		JPanel panel2=new JPanel();
		panel2.setLayout(new BorderLayout());
		frame.add(panel2, BorderLayout.SOUTH);
		JButton conferma = new JButton();
		JPanel panel3=new JPanel();
		panel3.setLayout(new GridLayout(10,2));
		
		String partecipante[]= {"Nessuno", "Squadra", "Giocatore"};
		JComboBox listaProfili=new JComboBox(partecipante);
		listaProfili.setSelectedIndex(0);
		panel2.add(listaProfili,BorderLayout.NORTH);
	
		ActionListener comboBoxListner= new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == listaProfili) {
					JComboBox cb= (JComboBox) e.getSource();
					String str= (String) cb.getSelectedItem();
				
				switch(str) {
					case "Squadra" : {	
						JLabel label2=new JLabel("Inserisci il nome");
						JTextField nome=new JTextField(20);
						JLabel label3=new JLabel("Inserisci il Cognome");
						JTextField cognome=new JTextField(20);
						JLabel label4=new JLabel("Inserisci la data di nascita");
						JTextField dataNascita=new JTextField(20);
						JLabel label5=new JLabel("Inserisci l'anno di immatricolazione");
						JTextField annoImm=new JTextField(20);
						JLabel label6=new JLabel("Inserisci anno corso");
						JTextField annoCorso=new JTextField(20);
						JLabel label7=new JLabel("Inserisci la matricola(5 CHAR)");
						JPasswordField matricola=new JPasswordField(20);
						
						String a = nome.getText();
						String b = cognome.getText();
						String c = dataNascita.getText();
						String d = annoImm.getText();
						String g = annoCorso.getText();
						String f = matricola.getText();
						
						ConnesioneGiocatore cg = new ConnesioneGiocatore();
						
						try {
							cg.save(a, b, c, d, g, f);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					
						panel2.add(listaProfili,BorderLayout.NORTH);
						
						panel3.add(label2);
						panel3.add(nome);
						panel3.add(label3);
						panel3.add(cognome);
						panel3.add(label4);
						panel3.add(dataNascita);
						panel3.add(label5);
						panel3.add(annoImm);
						panel3.add(label6);
						panel3.add(annoCorso);
						panel3.add(label7);
						panel3.add(matricola);
						
						panel2.add(panel3);
						frame.add(panel2,BorderLayout.CENTER);
			
						JButton indietro=new JButton("Indietro");
						JButton registra=new JButton("Registra");
						
						ActionListener indietroListener=new ActionListener(){
							public void actionPerformed(ActionEvent e){
								new MenuPrincipale(1);
								frame.setVisible(false);
							}
						};
						
						ActionListener registraListener=new ActionListener(){
							public void actionPerformed(ActionEvent e){
								try{
									
								// int codiceSicurezza=Integer.parseInt(new String(password3.getPassword()));
			/*					if(new String(password1.getPassword()).equals(new String(password2.getPassword())))
									if(new String(password3.getPassword()).equals(new String(password4.getPassword())))
									if(listaProfili.getSelectedItem().equals("Amministratore"))
											controller.creaProfiloUtente(nome.getText(), cognome.getText(), codiceFiscale.getSelectedText(), indirizzo.getText(), email.getText(), username.getText(), new String(password1.getPassword()), (String)listaProfili.getSelectedItem(), codiceSicurezza, 0);
										else if(listaProfili.getSelectedItem().equals("Impiegato"))
											controller.creaProfiloUtente(nome.getText(), cognome.getText(), codiceFiscale.getSelectedText(), indirizzo.getText(), email.getText(), username.getText(), new String(password1.getPassword()), (String)listaProfili.getSelectedItem(),0,codiceSicurezza);
										else
											controller.creaProfiloUtente(nome.getText(), cognome.getText(), codiceFiscale.getSelectedText(), indirizzo.getText(), email.getText(), username.getText(), new String(password1.getPassword()), (String)listaProfili.getSelectedItem(), 0, 0);
								*/
								frame.setVisible(false);
								JOptionPane.showMessageDialog(null, "Registrazione avvenuta con successo");
								}catch(NumberFormatException e1){
									JOptionPane.showMessageDialog(null, "Il codice di sicurezza deve essere un intero");
								}
								
							
							}
						};
						
						
						indietro.addActionListener(indietroListener);
						registra.addActionListener(registraListener);
						
						JPanel panel4=new JPanel();
						panel4.setLayout(new GridLayout(1,2));
						panel4.add(indietro);
						panel4.add(registra);
						panel2.add(panel4,BorderLayout.SOUTH);
						
						frame.pack();
						frame.setVisible(true);
					}
					case "Giocatore" :{
						JLabel label2=new JLabel("Inserisci il nome");
						JTextField nome=new JTextField(20);
						JLabel label3=new JLabel("Inserisci il Cognome");
						JTextField cognome=new JTextField(20);
						JLabel label4=new JLabel("Inserisci la data di nascita");
						JTextField dataNascita=new JTextField(20);
						JLabel label5=new JLabel("Inserisci l'anno di immatricolazione");
						JTextField annoImm=new JTextField(20);
						JLabel label6=new JLabel("Inserisci anno corso");
						JTextField annoCorso=new JTextField(20);
						JLabel label7=new JLabel("Inserisci la matricola(5 CHAR)");
						JPasswordField matricola=new JPasswordField(20);
						
						String a = nome.getText();
						String b = cognome.getText();
						String c = dataNascita.getText();
						String d = annoImm.getText();
						String g = annoCorso.getText();
						String f = matricola.getText();
						
						ConnesioneGiocatore cg = new ConnesioneGiocatore();
						
						try {
							cg.save(a, b, c, d, g, f);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					
						panel2.add(listaProfili,BorderLayout.NORTH);
						
						panel3.add(label2);
						panel3.add(nome);
						panel3.add(label3);
						panel3.add(cognome);
						panel3.add(label4);
						panel3.add(dataNascita);
						panel3.add(label5);
						panel3.add(annoImm);
						panel3.add(label6);
						panel3.add(annoCorso);
						panel3.add(label7);
						panel3.add(matricola);
						
						
						panel2.add(panel3);
						frame.add(panel2,BorderLayout.CENTER);

						JButton indietro=new JButton("Indietro");
						JButton registra=new JButton("Registra");
						
						ActionListener indietroListener=new ActionListener(){
							public void actionPerformed(ActionEvent e){
								new MenuPrincipale(1);
								frame.setVisible(false);
							}
						};
						
						ActionListener registraListener=new ActionListener(){
							public void actionPerformed(ActionEvent e){
								try{
									
								//int codiceSicurezza=Integer.parseInt(new String(password3.getPassword()));
			/*					if(new String(password1.getPassword()).equals(new String(password2.getPassword())))
									if(new String(password3.getPassword()).equals(new String(password4.getPassword())))
									if(listaProfili.getSelectedItem().equals("Amministratore"))
											controller.creaProfiloUtente(nome.getText(), cognome.getText(), codiceFiscale.getSelectedText(), indirizzo.getText(), email.getText(), username.getText(), new String(password1.getPassword()), (String)listaProfili.getSelectedItem(), codiceSicurezza, 0);
										else if(listaProfili.getSelectedItem().equals("Impiegato"))
											controller.creaProfiloUtente(nome.getText(), cognome.getText(), codiceFiscale.getSelectedText(), indirizzo.getText(), email.getText(), username.getText(), new String(password1.getPassword()), (String)listaProfili.getSelectedItem(),0,codiceSicurezza);
										else
											controller.creaProfiloUtente(nome.getText(), cognome.getText(), codiceFiscale.getSelectedText(), indirizzo.getText(), email.getText(), username.getText(), new String(password1.getPassword()), (String)listaProfili.getSelectedItem(), 0, 0);
								*/
								frame.setVisible(false);
								JOptionPane.showMessageDialog(null, "Registrazione avvenuta con successo");
								}catch(NumberFormatException e1){
									JOptionPane.showMessageDialog(null, "Il codice di sicurezza deve essere un intero");
								}
								
							
							}
						};
					
						indietro.addActionListener(indietroListener);
						registra.addActionListener(registraListener);
						
						JPanel panel4=new JPanel();
						panel4.setLayout(new GridLayout(1,2));
						panel4.add(indietro);
						panel4.add(registra);
						panel2.add(panel4,BorderLayout.SOUTH);
							
						frame.pack();
						frame.setVisible(true);
					}
				}	
				}
			}
		};
		
		listaProfili.addActionListener(comboBoxListner);
		
	
		frame.pack();
		frame.setVisible(true);
		
	}


	private JFrame frame;

}

