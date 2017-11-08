package gestori;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import commons.Calendario;
import commons.Classifica;
import commons.Giocatore;
import commons.Partecipante;
import commons.Partita;
import commons.Squadra;
import commons.Torneo;
import interfacce.ElencoTorneiInterface;

import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class GestoreDatiPersistenti {
	public GestoreDatiPersistenti() throws ParseException {
		this.tornei = new HashMap<String, Torneo>();
		this.squadre = new HashMap<String, Squadra>();
		this.giocatori = new HashMap<String, Giocatore>();
		this.partite = new HashMap<String, Partita>();
		this.calendari=new HashMap<String,Calendario>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Errore caricamento driver MySQL");
		}

		this.connection = null;

		try {
			this.connection = DriverManager.getConnection(url);
			this.stm = this.connection.createStatement();
			this.stm1 = this.connection.createStatement();
		} catch (SQLException e) {
			System.err.println("Errore connessione database");
		}

		tornei = caricaTornei();

	}

	public static GestoreDatiPersistenti getInstance() throws ParseException {
		if (istanza == null)
			istanza = new GestoreDatiPersistenti();
		return istanza;
	}

	public HashMap<String, Torneo> caricaTornei() throws ParseException {
		ResultSet rs = null;
		String stringSport = null;
		String modSvolgimentoIncontro = null;
		String modAttribuzionePtGara = null;
		int numPartecipanti;
		Date dataInizioTorneo = null;
		Date dataFineTorneo = null;
		String stringDataInizioTorneo = null;
		String stringDataFineTorneo = null;
		Calendario calendario = null;
		Classifica classifica = null;
		ArrayList<Partecipante> listPartecipanti = new ArrayList<Partecipante>();
		Torneo torneo = null;

		squadre = caricaSquadre();

		try {
			rs = stm1.executeQuery("select * from torneo");
			while (rs.next()) {
				stringSport = rs.getString("sport");
				modSvolgimentoIncontro = rs.getString("modSvolgimentoIncontro");
				modAttribuzionePtGara = rs.getString("modAttribuzionePtGara");
				numPartecipanti = rs.getInt("numPartecipanti");
				stringDataInizioTorneo = rs.getString("dataInizioTorneo");
				stringDataFineTorneo = rs.getString("dataFineTorneo");
				dataInizioTorneo = new SimpleDateFormat("yyyy-MM-dd").parse(stringDataInizioTorneo);
				dataFineTorneo = new SimpleDateFormat("yyyy-MM-dd").parse(stringDataFineTorneo);

				for (Entry<String, Squadra> entry : squadre.entrySet()) {
					if (this.torneoSquadra(entry.getValue()) != null
							&& this.torneoSquadra(entry.getValue()).equalsIgnoreCase(stringSport)) {
						listPartecipanti.add(entry.getValue());
					}
				}

				torneo = new Torneo(stringSport, modSvolgimentoIncontro, modAttribuzionePtGara, numPartecipanti,
						dataInizioTorneo, dataFineTorneo, null, null, listPartecipanti);
				this.tornei.put(torneo.getSport(), torneo);
			}
			return this.tornei;

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Errore caricamento tornei");
			return null;
		}

	}

	public static Connection getConnection() {
		Connection con = null;
		try {
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			con = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/fgr_tournament?user=root&password=fgrtournament");
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		}
		return con;
	}

	public HashMap<String, Squadra> caricaSquadre() {
		ResultSet rs = null;
		String id = null;
		int punti;
		int partiteGiocate;
		int partiteVinte;
		int partitePerse;
		int nComponenti;
		Squadra squadra = null;

		try {
			rs = stm.executeQuery("select * from squadra");
			while (rs.next()) {
				id = rs.getString("id");
				punti = rs.getInt("punti");
				partiteGiocate = rs.getInt("partiteGiocate");
				partiteVinte = rs.getInt("partiteVinte");
				partitePerse = rs.getInt("partitePerse");
				nComponenti = rs.getInt("nComponenti");
				squadra = new Squadra(id, punti, partiteGiocate, partiteVinte, partitePerse, nComponenti, null);
				this.squadre.put(squadra.getId(), squadra);
			}
			return this.squadre;

		} catch (SQLException e) {
			System.err.println("Errore caricamento tornei");
			return null;
		}

	}

	public HashMap<String, Giocatore> caricaGiocatore() throws ParseException {
		ResultSet rs = null;
		String id;
		String nome = null, cognome = null, recapito = null, nomeSquadra = null;
		Date dataNascita;
		int annoImmatricolazione, annoCorso;
		int punti;
		int partiteGiocate;
		int partiteVinte;
		int partitePerse;
		Giocatore giocatore = null;

		try {
			rs = stm.executeQuery("select * from giocatore");
			while (rs.next()) {
				nome = rs.getString("nome");
				cognome = rs.getString("cognome");
				String stringDataNascita = rs.getString("dataNascita");
				dataNascita = new SimpleDateFormat("yyyy-MM-dd").parse(stringDataNascita);
				annoImmatricolazione = rs.getInt("annoImmatricolazione");
				annoCorso = rs.getInt("annoCorso");
				id = rs.getString("id");
				punti = rs.getInt("punti");
				partiteGiocate = rs.getInt("partiteGiocate");
				partiteVinte = rs.getInt("partiteVinte");
				partitePerse = rs.getInt("partitePerse");
				recapito = rs.getString("recapito");
				nomeSquadra = rs.getString("nome_squadra");

				giocatore = new Giocatore(nome, punti, partiteGiocate, partiteVinte, partitePerse, cognome, dataNascita,
						annoImmatricolazione, annoCorso, id, recapito, nomeSquadra);
				this.giocatori.put(giocatore.getId(), giocatore);
			}
			return this.giocatori;

		} catch (SQLException e) {
			System.err.println("Errore caricamento giocatori");
			return null;
		}
	}
	
	public HashMap<String, Partita> caricaPartita() throws ParseException {
		ResultSet rs = null;
		String idPartita=null,idCalendario=null,idPartecipanteCasa=null,idPartecipanteOspite=null;
		int risultatoPartecipanteCasa,risultatoPartecipanteOspite;
		Partita partita= null;

		try {
			rs = stm.executeQuery("select * from partita");
			while (rs.next()) {
				risultatoPartecipanteCasa = rs.getInt("risultatoPartecipanteCasa");
				risultatoPartecipanteOspite = rs.getInt("risultatoPartecipanteOspite");;
				idPartita = rs.getString("idPartita");
				idPartecipanteCasa = rs.getString("idPartecipanteCasa");
				idPartecipanteOspite = rs.getString("idPartecipanteOspite");
				idCalendario = rs.getString("idCalendario");

				
				partita = new Partita(risultatoPartecipanteCasa, risultatoPartecipanteOspite, idPartita, idPartecipanteCasa, idPartecipanteOspite, idCalendario);
				this.partite.put(partita.getIdPartita(),partita);
			}
			return this.partite;

		} catch (SQLException e) {
			System.err.println("Errore caricamento partite");
			return null;
		}
	}

	public static String torneoSquadra(Squadra squadra) throws SQLException {
		ResultSet rs = null;
		rs = stm.executeQuery("select torneo from squadra where id = \"" + squadra.getId() + "\";");
		rs.next();
		return rs.getString("torneo");
	}

	public static String torneoSquadraS(String id) throws SQLException {
		ResultSet rs = null;
		rs = stm.executeQuery("select torneo from squadra where id = \"" + id + "\";");
		rs.next();
		return rs.getString("torneo");
	}

	public static String torneoGiocatore(Giocatore giocatore) throws SQLException {
		ResultSet rs = null;
		rs = stm.executeQuery("select torneo from giocatore where id = \"" + giocatore.getId() + "\";");
		rs.next();
		return rs.getString("torneo");
	}

	public static String torneoGiocatoreS(String id) throws SQLException {
		ResultSet rs = null;
		rs = stm.executeQuery("select torneo from giocatore where id = \"" + id + "\";");
		rs.next();
		return rs.getString("torneo");
	}

	public HashMap<String, Calendario> caricaCalendari() {
		ResultSet rs = null;
		String idCalendario=null;
		Calendario calendario= null;

		try {
			rs = stm.executeQuery("select * from calendario");
			while (rs.next()) {
				idCalendario = rs.getString("idCalendario");
				
				calendario = new Calendario(idCalendario);
				this.calendari.put(calendario.getIdCalendario(),calendario);
			}
			return this.calendari;

		} catch (SQLException e) {
			System.err.println("Errore caricamento calendari");
			return null;
		}
	}

	private HashMap<String, Squadra> squadre;
	private HashMap<String, Calendario> calendari;
	private HashMap<String, Torneo> tornei;
	private HashMap<String, Giocatore> giocatori;
	private HashMap<String, Partita> partite;

	private final String url = "jdbc:mysql://localhost:3306/fgr_tournament?user=root&password=fgrtournament";
	private Connection connection;
	private static Statement stm;
	private Statement stm1;
	private static GestoreDatiPersistenti istanza;
	
}
