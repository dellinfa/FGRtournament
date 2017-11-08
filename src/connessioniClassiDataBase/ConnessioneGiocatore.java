package connessioniClassiDataBase;

import java.sql.SQLException;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;

import gestori.GestoreDatiPersistenti;

public class ConnessioneGiocatore {
	public void saveGiocatore(String nome,String cognome,Date dataNascita,int annoImm, String annoCorso,String id)throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		
		String insertMySql = "INSERT INTO giocatore(nome,cognome,dataNascita,annoImmatricolazione,annoCorso,id) VALUES(?,?,?,?,?,?)";
		
		try {
			con = GestoreDatiPersistenti.getConnection();
			ps= con.prepareStatement(insertMySql);
			ps.setString(1,nome);
			ps.setString(2,cognome);
			java.sql.Date dataN = new java.sql.Date(dataNascita.getTime());
			ps.setInt(4,annoImm);
			ps.setString(5,annoCorso);
			ps.setString(6,id);
			
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
	
	public static void aggiornaPuntiSquadra(String giocatore, int punti) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;

		String insertMySql = "UPDATE giocatore SET punti = ? WHERE id = ?";

		try {
			con = GestoreDatiPersistenti.getConnection();
			ps = con.prepareStatement(insertMySql);
			ps.setInt(1, punti);
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
	
	public static void aggiornaPartiteVinte(String giocatore, int partiteVinte) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;

		String insertMySql = "UPDATE giocatore SET partiteVinte = ? WHERE id = ?";

		try {
			con = GestoreDatiPersistenti.getConnection();
			ps = con.prepareStatement(insertMySql);
			ps.setInt(1, partiteVinte);
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
	
	public static void aggiornaPartitePerse(String giocatore, int partitePerse) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;

		String insertMySql = "UPDATE giocatore SET partitePerse = ? WHERE id = ?";

		try {
			con = GestoreDatiPersistenti.getConnection();
			ps = con.prepareStatement(insertMySql);
			ps.setInt(1, partitePerse);
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
	
	public static void aggiornaPartiteG(String giocatore, int partiteGiocate) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;

		String insertMySql = "UPDATE squadra SET partiteGiocate = ? WHERE id = ?";

		try {
			con = GestoreDatiPersistenti.getConnection();
			ps = con.prepareStatement(insertMySql);
			ps.setInt(1, partiteGiocate);
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
