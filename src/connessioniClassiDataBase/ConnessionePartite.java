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

public class ConnessionePartite {
	
		public void savePartita(int risultatoPartecipante1,
				int risultatoPartecipante2,Enum stato,Partecipante partecipante1, Partecipante partecipante2)throws SQLException{
			Connection con = null;
			PreparedStatement ps = null;
			
			String insertMySql = "INSERT INTO tornei(risultatoPartecipanteCasa,risultatoPartecipanteOspite,statoPartita,squadraCasa,squadraOspite) VALUES(?,?,?,?,?)";
			
			try {
				con = GestoreDatiPersistenti.getConnection();
				ps= con.prepareStatement(insertMySql);
				ps.setInt(1,risultatoPartecipante1);
				ps.setInt(2,risultatoPartecipante2);
				ps.setObject(3,stato);
				ps.setObject(4,partecipante1);
				ps.setObject(5,partecipante2);

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

