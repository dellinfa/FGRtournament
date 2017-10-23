package connessioniClassiDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import commons.Calendario;
import commons.Classifica;
import commons.Giocatore;
import commons.Partecipante;
import commons.Torneo;
import gestori.GestoreDatiPersistenti;

public class ConnessioneSquadre {

	public void saveSquadra(String nome, int punti, int partiteGiocate, int partiteVinte, int partitePerse, int nComponenti,
			ArrayList<Torneo> listTornei,ArrayList<Giocatore> listGiocatori)throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		
		String insertMySql = "INSERT INTO tornei(nome,punti,partiteGiocate,numeroPartiteVinte,numeroPartitePerse,numeroComponenti,torneo,giocatore) VALUES(?,?,?,?,?,?,?,?)";
		
		try {
			con = GestoreDatiPersistenti.getConnection();
			ps= con.prepareStatement(insertMySql);
			ps.setString(1,nome);
			ps.setInt(2,punti);
			ps.setInt(3,partiteGiocate);
			ps.setInt(4,partiteVinte);
			ps.setInt(5,partitePerse);
			ps.setInt(6,nComponenti);
			ps.setObject(7,listTornei);
			ps.setObject(8,listGiocatori);
			

			
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
