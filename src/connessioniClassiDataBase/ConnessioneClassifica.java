package connessioniClassiDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import gestori.GestoreDatiPersistenti;

public class ConnessioneClassifica {

	public void saveClassifica(String idClassifica) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;

		String insertMySql = "INSERT INTO classifica(idClassifica) VALUES(?)";

		try {
			con = GestoreDatiPersistenti.getConnection();
			ps = con.prepareStatement(insertMySql);
			ps.setString(1, idClassifica);

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
