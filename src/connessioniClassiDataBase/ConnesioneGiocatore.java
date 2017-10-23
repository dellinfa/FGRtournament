package connessioniClassiDataBase;

import java.sql.SQLException;


import java.sql.Connection;
import java.sql.PreparedStatement;

import gestori.GestoreDatiPersistenti;

public class ConnesioneGiocatore {
	public void save(String nome,String cognome,String dataNascita,String annoImm, String annoCorso,String matricola)throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		
		String insertMySql = "INSERT INTO giocatori(nome,cognome,dataNascita,annoImmatricolazione,annoCorso,matricola) VALUES(?,?,?,?,?,?)";
		
		try {
			con = GestoreDatiPersistenti.getConnection();
			ps= con.prepareStatement(insertMySql);
			ps.setString(1,nome);
			ps.setString(2,cognome);
			ps.setString(3,dataNascita);
			ps.setString(4,annoImm);
			ps.setString(5,annoCorso);
			ps.setString(6,matricola);
			
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
