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

	public void savePartita(int risultatoPartecipanteCasa, int risultatoPartecipanteOspite, String idPartita,
			String idPartecipanteCasa, String idPartecipanteOspite, String idCalendario) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;

		String insertMySql = "INSERT INTO partita(risultatoPartecipanteCasa,risultatoPartecipanteOspite,idPartita,idPartecipanteCasa,idPartecipanteOspite, idCalendario) VALUES(?,?,?,?,?,?)";

		try {
			con = GestoreDatiPersistenti.getConnection();
			ps = con.prepareStatement(insertMySql);
			ps.setInt(1, risultatoPartecipanteCasa);
			ps.setInt(2, risultatoPartecipanteCasa);
			ps.setString(3, idPartita);
			ps.setString(4, idPartecipanteCasa);
			ps.setString(5, idPartecipanteOspite);
			ps.setString(6, idCalendario);

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
	
	public static void setRisultatoCasaToPartita(String idPartita, int risultatoPartecipanteCasa) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;

		String insertMySql = "UPDATE partita SET risultatoPartecipanteCasa = ? WHERE idPartita = ?";

		try {
			con = GestoreDatiPersistenti.getConnection();
			ps = con.prepareStatement(insertMySql);
			ps.setInt(1, risultatoPartecipanteCasa);
			ps.setString(2, idPartita);

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
	
	public static void setRisultatoOspiteToPartita(String idPartita, int risultatoPartecipanteOspite) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;

		String insertMySql = "UPDATE partita SET risultatoPartecipanteOspite = ? WHERE idPartita = ?";

		try {
			con = GestoreDatiPersistenti.getConnection();
			ps = con.prepareStatement(insertMySql);
			ps.setInt(1, risultatoPartecipanteOspite);
			ps.setString(2, idPartita);
			

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
	
	public static void removePartita(String idPartita) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;

		String insertMySql = "DELETE FROM partita WHERE idPartita = ?";

		try {
			con = GestoreDatiPersistenti.getConnection();
			ps = con.prepareStatement(insertMySql);
			ps.setString(1, idPartita);
			

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
