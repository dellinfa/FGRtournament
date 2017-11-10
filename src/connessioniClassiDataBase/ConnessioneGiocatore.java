package connessioniClassiDataBase;

import java.sql.SQLException;
import java.util.Date;

import commons.Torneo;

import java.sql.Connection;
import java.sql.PreparedStatement;

import gestori.GestoreDatiPersistenti;

public class ConnessioneGiocatore {
	public void saveGiocatore(String nome, int punti,  String cognome, Date dataNascita, int annoImmatricolazione, int annoCorso, String id,int partiteGiocate, int partiteVinte, int partitePerse, String recapito, String nomeSquadra, Torneo torneo)throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		
		String insertMySql = "INSERT INTO giocatore(nome,cognome,dataNascita,annoImmatricolazione,annoCorso,id,punti,partiteGiocate,partiteVinte,partitePerse,recapito,nome_squadra,torneo) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			con = GestoreDatiPersistenti.getConnection();
			ps= con.prepareStatement(insertMySql);
			ps.setString(1,nome);
			ps.setString(2,cognome);
			java.sql.Date dataN = new java.sql.Date(dataNascita.getTime());
			ps.setInt(3,annoImmatricolazione);
			ps.setInt(4,annoCorso);
			ps.setString(5,id);
			ps.setInt(6,punti);
			ps.setInt(7,partiteGiocate);
			ps.setInt(8,partiteVinte);
			ps.setInt(9,partitePerse);
			ps.setString(10,recapito);
			ps.setString(11,nomeSquadra);
			ps.setObject(12, torneo);
			
			
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
	
	public static void aggiornaPuntiGiocatore(String giocatore, int punti) throws SQLException {
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

		String insertMySql = "UPDATE giocatore SET partiteGiocate = ? WHERE id = ?";

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
