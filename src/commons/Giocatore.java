package commons;

import java.util.Date;

public class Giocatore extends Partecipante{

	public Giocatore(String nome, int punti, int partiteGiocate, int partiteVinte, int partitePerse, String cognome, Date dataNascita, 
			int annoImmatricolazione, int annoCorso, String Matricola) {
		super(nome, punti, partiteGiocate, partiteVinte, partitePerse);
		this.cognome=cognome;
		this.dataNascita=dataNascita;
		this.annoImmatricolazione=annoImmatricolazione;
		this.annoCorso=annoCorso;
		this.matricola=matricola;
	}


	public String getMatricola() {
		return matricola;
	}
	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}
	
	private String cognome, matricola;
	private Date dataNascita;
	private int annoImmatricolazione, annoCorso;
	
}
