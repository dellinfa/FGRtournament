package connessioniClassiDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import Controller.ControllerTorneo;
import commons.Calendario;
import commons.Classifica;
import commons.Giocatore;
import commons.Partecipante;
import commons.Torneo;
import gestori.GestoreDatiPersistenti;

public class ConnessioneSquadre {

	public void saveSquadra(String nome, int punti, int partiteGiocate, int partiteVinte, int partitePerse,
			int nComponenti, Torneo torneo, ArrayList<Giocatore> listGiocatori) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;

		String insertMySql = "INSERT INTO squadra(id,punti,partiteGiocate,numeroPartiteVinte,numeroPartitePerse,numeroComponenti,torneo,giocatore) VALUES(?,?,?,?,?,?,?,?)";

		try {
			con = GestoreDatiPersistenti.getConnection();
			ps = con.prepareStatement(insertMySql);
			ps.setString(1, nome);
			ps.setInt(2, punti);
			ps.setInt(3, partiteGiocate);
			ps.setInt(4, partiteVinte);
			ps.setInt(5, partitePerse);
			ps.setInt(6, nComponenti);
			ps.setObject(7, torneo);
			ps.setObject(8, listGiocatori);

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

	public static void setTorneoToSquadra(String squadra, String torneo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;

		String insertMySql = "UPDATE squadra SET torneo = ? WHERE id = ?";

		try {
			con = GestoreDatiPersistenti.getConnection();
			ps = con.prepareStatement(insertMySql);
			ps.setString(1, torneo);
			ps.setString(2, squadra);

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
	
	public static void aggiornaPuntiSquadra(String squadra, int punti) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;

		String insertMySql = "UPDATE squadra SET punti = ? WHERE id = ?";

		try {
			con = GestoreDatiPersistenti.getConnection();
			ps = con.prepareStatement(insertMySql);
			ps.setInt(1, punti);
			ps.setString(2, squadra);

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
	
	public static void aggiornaPartiteVinte(String squadra, int partiteVinte) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;

		String insertMySql = "UPDATE squadra SET partiteVinte = ? WHERE id = ?";

		try {
			con = GestoreDatiPersistenti.getConnection();
			ps = con.prepareStatement(insertMySql);
			ps.setInt(1, partiteVinte);
			ps.setString(2, squadra);

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
	
	public static void aggiornaPartitePerse(String squadra, int partitePerse) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;

		String insertMySql = "UPDATE squadra SET partitePerse = ? WHERE id = ?";

		try {
			con = GestoreDatiPersistenti.getConnection();
			ps = con.prepareStatement(insertMySql);
			ps.setInt(1, partitePerse);
			ps.setString(2, squadra);

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
	
	public static void aggiornaPartiteG(String squadra, int partiteGiocate) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;

		String insertMySql = "UPDATE squadra SET partiteGiocate = ? WHERE id = ?";

		try {
			con = GestoreDatiPersistenti.getConnection();
			ps = con.prepareStatement(insertMySql);
			ps.setInt(1, partiteGiocate);
			ps.setString(2, squadra);

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
