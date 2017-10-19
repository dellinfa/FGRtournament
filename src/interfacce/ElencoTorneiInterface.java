package interfacce;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ElencoTorneiInterface {

	public ElencoTorneiInterface() {
		
		frame=new JFrame("FGRtournament");
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(250, 250);
		
		JPanel panel1=new JPanel();
		JLabel label1=new JLabel("Seleziona il torneo");
		panel1.add(label1);
		frame.add(panel1,BorderLayout.NORTH);
		
		JButton buttonCalcio = new JButton("CALCIO");
		JButton buttonBasket = new JButton("BASKET");
		JButton buttonPallavolo = new JButton("PALLAVOLO");
		JButton buttonIndietro = new JButton("INDIETRO");

		JPanel panel2= new JPanel();
		panel2.add(buttonPallavolo, BorderLayout.NORTH);
		panel2.add(buttonCalcio, BorderLayout.CENTER);
		panel2.add(buttonBasket, BorderLayout.SOUTH);
		frame.add(panel2, BorderLayout.CENTER);
		JPanel panel3= new JPanel();		
		panel3.add(buttonIndietro, BorderLayout.PAGE_START);
		frame.add(panel3, BorderLayout.SOUTH);

		
		ActionListener menuPrincipalePallavolo=new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
					new MenuPrincipale(0);								
			}
		};
		
		ActionListener menuPrincipaleCalcio= new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new MenuPrincipale(1);				
			}
		};
	
		ActionListener menuPrincipaleBasket= new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new MenuPrincipale(2);	
			}
		};


		ActionListener indietroListener=new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new InterfacciaIniziale();
			}
		};	
				

		buttonPallavolo.addActionListener(menuPrincipalePallavolo);
		buttonCalcio.addActionListener(menuPrincipaleCalcio);
		buttonBasket.addActionListener(menuPrincipaleBasket);
		buttonIndietro.addActionListener(indietroListener);
		
		frame.pack();
		frame.setVisible(true);
		

	
	
}
		
		public static void main(String[] args) {
			
			new ElencoTorneiInterface();
		}
		private JFrame frame;
}
