package gestori;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import commons.Torneo;

import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;



public class GestoreDatiPersistenti {
	public GestoreDatiPersistenti() throws ParseException{
		this.tornei=new HashMap<String, Torneo>();

		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.err.println("Errore caricamento driver MySQL");
			}
			
			this.connection = null;
			
			try {
				this.connection = DriverManager.getConnection(url);
				this.stm = this.connection.createStatement();
			} catch (SQLException e) {
				System.err.println("Errore connessione database");
			}
			
			tornei=caricaTornei();			
			
		}
	

	public static GestoreDatiPersistenti getInstance() throws ParseException {
		if(istanza == null) 
			istanza = new GestoreDatiPersistenti();
		return istanza;
	}
	
	
	public HashMap<String, Torneo> caricaTornei() throws ParseException {
		ResultSet rs = null;
		String stringSport= null;
		String modSvolgimentoTorneo= null;
		String modAttribuzionePunti=null;
		int maxPartecipanti;
		int intervalloTempo;
		Date dataInizio=null;
		Date dataFine=null;
		String stringDataInizio= null;
		String stringDataFine= null;
		Torneo torneo= null;
		
		try {
			rs = stm.executeQuery("select * from tornei");
			while(rs.next()) {
				stringSport=rs.getString("sport");
				modSvolgimentoTorneo= rs.getString("modSvolgimentoTorneo");
				modAttribuzionePunti= rs.getString("modAttrbuzionePunti");
				maxPartecipanti= rs.getInt("maxPartecipanti");
				intervalloTempo= rs.getInt("intervalloTempo");
				stringDataInizio=rs.getString("dataInizio");
				stringDataFine=rs.getString("dataFine");
				dataInizio=new SimpleDateFormat("yyyy-MM-dd").parse(stringDataInizio);
				dataFine=new SimpleDateFormat("yyyy-MM-dd").parse(stringDataFine);
				torneo=new Torneo(null,rs.getString("modSvolgimentoTorneo"),rs.getString("modAttrbuzionePunti"),rs.getInt("maxPartecipanti"),rs.getInt("intervalloTempo"),rs.getDate("dataInizio"),rs.getDate("dataFine"),rs.getString("sport"),null);
				this.tornei.put(torneo.getSport(), torneo);
			}
			return this.tornei;
			
		}catch (SQLException e) {
			System.err.println("Errore caricamento tornei");
			return null;
		}
		
		
	}


	public static Connection getConnection()
	{
		Connection con = null;
		try 
		{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fgrtournament?user=root&password=fgrtournament");
		}
		catch (SQLException e1)
		{
			e1.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		
		}
		return con;
	}

	private HashMap<String, Torneo> tornei;
	private final String url = "jdbc:mysql://localhost:3306/fgrtournament?user=root&password=fgrtournament";
	private Connection connection;
	private Statement stm;
	private static GestoreDatiPersistenti istanza;
	}
