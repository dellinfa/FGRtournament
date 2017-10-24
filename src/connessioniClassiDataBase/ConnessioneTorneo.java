package connessioniClassiDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;

import javax.swing.AbstractButton;

import commons.Calendario;
import commons.Classifica;
import commons.Partecipante;
import gestori.GestoreDatiPersistenti;

public class ConnessioneTorneo {
		public void saveTorneo(String modSvolgimentoIncontro,String modAttribuzionePtGara, int maxPartecipanti,
				int intervalloTempo,Date dataInizioTorneo, Date dataFineTorneo, String calendario, String sport)throws SQLException{
			Connection con = null;
			PreparedStatement ps = null;
			
			String insertMySql = "INSERT INTO tornei(modSvolgimentoTorneo,modAttrbuzionePunti,maxPartecipanti,intervalloTempo,dataInizio,dataFine,sport,calendario) VALUES(?,?,?,?,?,?,?,?)";
			
			try {
				con = GestoreDatiPersistenti.getConnection();
				ps= con.prepareStatement(insertMySql);
				ps.setString(1,modSvolgimentoIncontro);
				ps.setString(2,modAttribuzionePtGara);
				ps.setInt(3,maxPartecipanti);
				ps.setInt(4,intervalloTempo);
				java.sql.Date dataInizio = new java.sql.Date(dataInizioTorneo.getDate());
				java.sql.Date dataFine = new java.sql.Date(dataFineTorneo.getDate());
				ps.setDate(5,dataInizio);
				ps.setDate(6,dataFine);
				ps.setString(7,sport);
				ps.setObject(8,calendario);

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

}

