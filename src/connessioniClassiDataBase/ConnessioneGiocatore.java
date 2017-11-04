package connessioniClassiDataBase;

import java.sql.SQLException;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;

import gestori.GestoreDatiPersistenti;

public class ConnessioneGiocatore {
	public void saveGiocatore(String nome,String cognome,Date dataNascita,int annoImm, String annoCorso,String matricola)throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		
		String insertMySql = "INSERT INTO giocatori(nome,cognome,dataNascita,annoImmatricolazione,annoCorso,matricola) VALUES(?,?,?,?,?,?)";
		
		try {
			con = GestoreDatiPersistenti.getConnection();
			ps= con.prepareStatement(insertMySql);
			ps.setString(1,nome);
			ps.setString(2,cognome);
			java.sql.Date dataN = new java.sql.Date(dataNascita.getTime());
			ps.setInt(4,annoImm);
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
	
	
	public static void setTorneoToGiocatore(String giocatore, String torneo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;

		String insertMySql = "UPDATE giocatore SET torneo = ? WHERE id = ?";

		try {
			con = GestoreDatiPersistenti.getConnection();
			ps = con.prepareStatement(insertMySql);
			ps.setString(1, torneo);
			ps.setString(2, giocatore);

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
