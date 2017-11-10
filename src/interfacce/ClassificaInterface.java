package interfacce;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.swing.table.*;
import javax.swing.DefaultRowSorter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.RowSorter.SortKey;
import javax.swing.SortOrder;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

import Controller.ControllerTorneo;
import commons.Giocatore;
import commons.Partecipante;
import commons.Squadra;
import commons.Torneo;
import connessioniClassiDataBase.ConnessioneSquadre;
import gestori.GestoreDatiPersistenti;

public class ClassificaInterface {

	private JFrame frame;
	private JScrollPane pane;
	private JTable table;
	private HashMap<String, Torneo> tornei = new HashMap<>();
	private HashMap<String, Squadra> squadre = new HashMap<>();
	private HashMap<String, Giocatore> giocatori = new HashMap<>();
	private ControllerTorneo ct;
	private ConnessioneSquadre cs;
	private ArrayList<Integer> arrayPunti = new ArrayList<Integer>();

	private Object[][] data;
	private String col[];
	private int sortColumn;
	private int row = 0, j = 0;

	public ClassificaInterface() throws ParseException, SQLException {
		frame = new JFrame("FGRtournament");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setLocation(200, 100);

		JPanel panel1 = new JPanel();
		JLabel label1 = new JLabel("Classifica");
		panel1.add(label1);
		frame.add(panel1, BorderLayout.NORTH);

		JPanel panel2 = new JPanel();
		JButton buttonIndietro = new JButton("INDIETRO");
		panel2.add(buttonIndietro);
		frame.add(panel2, BorderLayout.SOUTH);

		JPanel panel3 = new JPanel();
		panel3.setLayout(null);

		ct = ControllerTorneo.getInstance();

		tornei = ct.getTornei();
		squadre = ct.getSquadre();
		giocatori = ct.getGiocatori();

		String nameSport;
		Torneo nameSportT = ElencoTorneiInterface.prendiTorneo();
		int numPart = 0;
		nameSport = ElencoTorneiInterface.prendiTorneo().getSport();
		for (Entry<String, Torneo> entry : tornei.entrySet()) {
			if (nameSport.equalsIgnoreCase(entry.getValue().getSport())) {
				numPart = entry.getValue().getNumPartecipanti();
			}
		}

		ArrayList<Integer> listPunti = new ArrayList<>();

		if (nameSport.equalsIgnoreCase("ping pong")) {

			data = new Object[giocatori.size()][5];
			col = new String[5];
			col[0] = "Giocatore";
			col[1] = "Punti";
			col[2] = "Giocate";
			col[3] = "Vinte";
			col[4] = "Perse";

			for (Entry<String, Giocatore> entry : giocatori.entrySet()) {
				if (nameSportT.getSport()
						.equalsIgnoreCase(GestoreDatiPersistenti.torneoGiocatoreS(entry.getValue().getId()))) {

					listPunti.add(j, entry.getValue().getPunti());
					j++;
				}
			}

			Comparator<Integer> comparator = new Comparator<Integer>() {
				@Override
				public int compare(Integer left, Integer right) {
					return right - left;
				}
			};

			Collections.sort(listPunti, comparator);

			for (int k = 0; k < listPunti.size(); k++) {

				for (Entry<String, Giocatore> entry : giocatori.entrySet()) {
					if (nameSportT.getSport()
							.equalsIgnoreCase(GestoreDatiPersistenti.torneoGiocatoreS(entry.getValue().getId()))) {
						if(entry.getValue().getPunti()==listPunti.get(k)) {

						data[row][0] = entry.getValue().getId();
						data[row][1] = entry.getValue().getPunti();
						data[row][2] = entry.getValue().getPartiteGiocate();
						data[row][3] = entry.getValue().getPartiteVinte();
						data[row][4] = entry.getValue().getPartitePerse();
						row++;
						giocatori.remove(entry.getValue().getId());
						break;
						}
					}
				}
			}

		} else {

			data = new Object[squadre.size()][5];
			col = new String[5];
			col[0] = "Squadra";
			col[1] = "Punti";
			col[2] = "Giocate";
			col[3] = "Vinte";
			col[4] = "Perse";

			for (Entry<String, Squadra> entry : squadre.entrySet()) {
				if (nameSportT.getSport()
						.equalsIgnoreCase(GestoreDatiPersistenti.torneoSquadraS(entry.getValue().getId()))) {

					listPunti.add(j, entry.getValue().getPunti());
					j++;

				}
			}

			Comparator<Integer> comparator = new Comparator<Integer>() {
				@Override
				public int compare(Integer left, Integer right) {
					return right - left;
				}
			};

			Collections.sort(listPunti, comparator);

			for (int k = 0; k < listPunti.size(); k++) {

				for (Entry<String, Squadra> entry : squadre.entrySet()) {
					if (nameSportT.getSport()
							.equalsIgnoreCase(GestoreDatiPersistenti.torneoSquadraS(entry.getValue().getId()))) {
						if(entry.getValue().getPunti()==listPunti.get(k)) {

						data[row][0] = entry.getValue().getId();
						data[row][1] = entry.getValue().getPunti();
						data[row][2] = entry.getValue().getPartiteGiocate();
						data[row][3] = entry.getValue().getPartiteVinte();
						data[row][4] = entry.getValue().getPartitePerse();
						row++;
						squadre.remove(entry.getValue().getId());
						break;
						}
					}
				}
			}

		}
		

		DefaultTableModel model = new DefaultTableModel(data, col) {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}

		};

		JTable table = new JTable(model);


		// Imposto colori alternati per le righe
		UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		if (defaults.get("Table.alternateRowColor") == null)
			defaults.put("Table.alternateRowColor", new Color(240, 240, 240));
		table.setGridColor(new Color(209, 209, 209));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.yellow);
		panel3.setLayout(null);
		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(6, 6, 975, 538);
		panel3.add(pane);
		frame.add(panel3);

		buttonIndietro.addActionListener(indietro);

		frame.pack();
		frame.setVisible(true);
		frame.setSize(1000, 300);
	}

	ActionListener indietro = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				new MenuPrincipale(ElencoTorneiInterface.prendiTorneo().getSport());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			frame.setVisible(false);
		}
	};
}
