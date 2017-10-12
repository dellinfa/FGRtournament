package commons;

import java.util.Date;

public class Presidente extends Giocatore{

	public Presidente(String nome, int punti, int partiteGiocate, int partiteVinte, int partitePerse, String cognome,
			Date dataNascita, int annoImmatricolazione, int annoCorso, String Matricola, String recapito) {
		super(nome, punti, partiteGiocate, partiteVinte, partitePerse, cognome, dataNascita, annoImmatricolazione, annoCorso,
				Matricola);
		this.recapito=recapito;
	}
	
	public void setRecapito(String recapito) {
		this.recapito=recapito;
	}
	
	public String getRecapito() {
		return recapito;
	}
	
	private String recapito;

}
