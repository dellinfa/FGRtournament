package connessioniClassiDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import commons.Calendario;
import commons.Classifica;
import commons.Partecipante;
import gestori.GestoreDatiPersistenti;

public class ConnessioneTorneo {
	
		public void saveTorneo(String sport,String modSvolgimentoIncontro,String modAttribuzionePtGara, int numPartecipanti,
				Date dataInizioTorneo, Date dataFineTorneo,String idCalendario, String idClassifica)throws SQLException, ParseException{
			Connection con = null;
			PreparedStatement ps = null;
			
			
			String insertMySql = "INSERT INTO torneo(sport,modSvolgimentoIncontro,modAttribuzionePtGara,numPartecipanti,dataInizioTorneo,dataFineTorneo,idCalendario,idClassifica) VALUES(?,?,?,?,?,?,?,?)";
			
			try {
				con = GestoreDatiPersistenti.getConnection();
				ps= con.prepareStatement(insertMySql);
				ps.setString(1,sport);
				ps.setString(2,modSvolgimentoIncontro);
				ps.setString(3,modAttribuzionePtGara);
				ps.setInt(4,numPartecipanti);
				java.sql.Date dataInizio = new java.sql.Date(dataInizioTorneo.getTime());
				java.sql.Date dataFine = new java.sql.Date(dataFineTorneo.getTime());
				ps.setDate(5,dataInizio);
				ps.setDate(6,dataFine);
				ps.setObject(7,idCalendario);
				ps.setObject(8,idClassifica);
				ps.executeUpdate();
				
			}finally {
				try {
					if(ps!=null) {
						ps.close();
					}
				}finally {
						if(con!=null)
							con.close();
				}
					}
			}
		
		
		public static void setCalendarioToTorneo(String torneo, String calendario) throws SQLException {
			Connection con = null;
			PreparedStatement ps = null;

			String insertMySql = "UPDATE torneo SET idCalendario = ? WHERE  sport= ?";

			try {
				con = GestoreDatiPersistenti.getConnection();
				ps = con.prepareStatement(insertMySql);
				ps.setString(1, calendario);
				ps.setString(2, torneo);

				ps.executeUpdate();

			} finally {
				try {
					if (ps != null) {
						ps.close();
					}
				} finally {
					if (con != null)
						con.close();
				}
			}
		}
		
		public static void setClassificaToTorneo(String torneo, String classifica) throws SQLException {
			Connection con = null;
			PreparedStatement ps = null;

			String insertMySql = "UPDATE torneo SET idClassifica = ? WHERE  sport= ?";

			try {
				con = GestoreDatiPersistenti.getConnection();
				ps = con.prepareStatement(insertMySql);
				ps.setString(1, classifica);
				ps.setString(2, torneo);

				ps.executeUpdate();

			} finally {
				try {
					if (ps != null) {
						ps.close();
					}
				} finally {
					if (con != null)
						con.close();
				}
			}
		}
		
		
		

}

