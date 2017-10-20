package interfacce;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;




public class InserisciPartecipanteInterface {

		private JFrame frame;
//		private ControllerRegistrazione controller;
		
		
		public InserisciPartecipanteInterface(){
//			controller=ControllerRegistrazione.getInstance();
			frame=new JFrame();
			frame.getContentPane().setLayout(new BorderLayout());
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocation(250, 250);
			
			JPanel panel1=new JPanel();
			JLabel label1=new JLabel("Inserisci i dati personali");
			panel1.add(label1);
			frame.add(panel1,BorderLayout.NORTH);
			
			JPanel panel2=new JPanel();
			panel2.setLayout(new BorderLayout());
			JButton conferma = new JButton();
			JPanel panel3=new JPanel();
			panel3.setLayout(new GridLayout(10,2));
			
			//String partecipante[]= {"Squadra", "Giocatore"};
			JComboBox listaProfili=new JComboBox();
			
			listaProfili.addItem("Squadra");
			listaProfili.addItem("Partecipante");
			
/*
			ItemListener itemListner = new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent ie) {
					// TODO Auto-generated method stub
					int state=ie.getStateChange();
					((state == ItemEvent.SELECTED) ?"Giocatore":"Squadra");
				}
			};
			
			listaProfili.addItemListener(itemListner);*/
			
			if(listaProfili.getSelectedItem()== "Partecipante") {
				JLabel label2=new JLabel("Inserisci il Nome");
				JTextField nome=new JTextField(20);
				JLabel label3=new JLabel("Inserisci il Cognome");
				JTextField cognome=new JTextField(20);
				JLabel label4=new JLabel("Inserisci il codiceFiscale");
				JTextField codiceFiscale=new JTextField(20);
				JLabel label5=new JLabel("Inserisci la tua email");
				JTextField email=new JTextField(20);
				JLabel label6=new JLabel("Inserisci il tuo username");
				JTextField username=new JTextField(20);
				JLabel label7=new JLabel("Inserisci una password");
				JPasswordField password1=new JPasswordField(20);
				JLabel label8=new JLabel("Inserisci di nuovo la password");
				JPasswordField password2=new JPasswordField(20);
				JLabel label9=new JLabel("(Solo se Impiegato o Amministratore) Inserisci il codice di sicurezza");
				JPasswordField password3=new JPasswordField(20);
				JLabel label10=new JLabel("(Solo se Impiegato o Amministratore) Inserisci nuovamente il codice di sicurezza");
				JPasswordField password4=new JPasswordField(20);
				JTextField indirizzo=new JTextField(20);
				JLabel label11=new JLabel("Inserisci il tuo indirizzo");
				
				panel2.add(listaProfili,BorderLayout.NORTH);
				
				
				panel3.add(label2);
				panel3.add(nome);
				panel3.add(label3);
				panel3.add(cognome);
				panel3.add(label4);
				panel3.add(codiceFiscale);
				panel3.add(label11);
				panel3.add(indirizzo);
				panel3.add(label5);
				panel3.add(email);
				panel3.add(label6);
				panel3.add(username);
				panel3.add(label7);
				panel3.add(password1);
				panel3.add(label8);
				panel3.add(password2);
				panel3.add(label9);
				panel3.add(password3);
				panel3.add(label10);
				panel3.add(password4);
				
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
							
						int codiceSicurezza=Integer.parseInt(new String(password3.getPassword()));
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
			else {
				JLabel label2=new JLabel("Inserisci il CAZZOOOOOOOOOOOOO");
				JTextField nome=new JTextField(20);
				JLabel label3=new JLabel("Inserisci il Cognome");
				JTextField cognome=new JTextField(20);
				JLabel label4=new JLabel("Inserisci il codiceFiscale");
				JTextField codiceFiscale=new JTextField(20);
				JLabel label5=new JLabel("Inserisci la tua email");
				JTextField email=new JTextField(20);
				JLabel label6=new JLabel("Inserisci il tuo username");
				JTextField username=new JTextField(20);
				JLabel label7=new JLabel("Inserisci una password");
				JPasswordField password1=new JPasswordField(20);
				JLabel label8=new JLabel("Inserisci di nuovo la password");
				JPasswordField password2=new JPasswordField(20);
				JLabel label9=new JLabel("(Solo se Impiegato o Amministratore) Inserisci il codice di sicurezza");
				JPasswordField password3=new JPasswordField(20);
				JLabel label10=new JLabel("(Solo se Impiegato o Amministratore) Inserisci nuovamente il codice di sicurezza");
				JPasswordField password4=new JPasswordField(20);
				JTextField indirizzo=new JTextField(20);
				JLabel label11=new JLabel("Inserisci il tuo indirizzo");
				
				panel2.add(listaProfili,BorderLayout.NORTH);
				
				panel3.add(label2);
				panel3.add(nome);
				panel3.add(label3);
				panel3.add(cognome);
				panel3.add(label4);
				panel3.add(codiceFiscale);
				panel3.add(label11);
				panel3.add(indirizzo);
				panel3.add(label5);
				panel3.add(email);
				panel3.add(label6);
				panel3.add(username);
				panel3.add(label7);
				panel3.add(password1);
				panel3.add(label8);
				panel3.add(password2);
				panel3.add(label9);
				panel3.add(password3);
				panel3.add(label10);
				panel3.add(password4);
				
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
							
						int codiceSicurezza=Integer.parseInt(new String(password3.getPassword()));
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

