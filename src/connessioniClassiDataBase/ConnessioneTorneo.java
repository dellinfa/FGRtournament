package connessioniClassiDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import commons.Calendario;
import commons.Classifica;
import commons.Partecipante;
import gestori.GestoreDatiPersistenti;

public class ConnessioneTorneo {
		public void saveTorneo(Enum modSvolgimentoIncontro,String modIscrizione,Enum modAttribuzionePtGara, int maxPartecipanti,
				int intervalloTempo, Date dataInizioTorneo, Date dataFineTorneo, Calendario calendario,
				Classifica classifica, ArrayList<Partecipante> listPartecipanti, String sport)throws SQLException{
			Connection con = null;
			PreparedStatement ps = null;
			
			String insertMySql = "INSERT INTO tornei(modIscrizione,modSvolgimentoTorneo,modAttribuzionePunti,maxPartecipanti,intervalloTempo,dataInizio,dataFine,tipologiaTornei,calendario) VALUES(?,?,?,?,?,?,?,?,?)";
			
			try {
				con = GestoreDatiPersistenti.getConnection();
				ps= con.prepareStatement(insertMySql);
				ps.setString(1,modIscrizione);
				ps.setObject(2,modSvolgimentoIncontro);
				ps.setObject(3,modAttribuzionePtGara);
				ps.setInt(4,maxPartecipanti);
				ps.setInt(5,intervalloTempo);
				ps.setDate(6,(java.sql.Date) dataInizioTorneo);
				ps.setDate(7,(java.sql.Date) dataFineTorneo);
				ps.setString(8,sport);
				ps.setObject(9,calendario);

				
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

