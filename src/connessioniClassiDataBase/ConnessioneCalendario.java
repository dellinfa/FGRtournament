package connessioniClassiDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import gestori.GestoreDatiPersistenti;

public class ConnessioneCalendario {

		public void saveCalendario(String idCalendario) throws SQLException {
			Connection con = null;
			PreparedStatement ps = null;

			String insertMySql = "INSERT INTO calendario(idCalendario) VALUES(?)";

			try {
				con = GestoreDatiPersistenti.getConnection();
				ps = con.prepareStatement(insertMySql);
				ps.setString(1, idCalendario);

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