package commons;

import java.util.Date;

public class Giocatore extends Partecipante{

	public Giocatore(String nome,  String cognome, Date dataNascita, int annoImmatricolazione, int annoCorso, String id,int punti, int partiteGiocate, int partiteVinte, int partitePerse, String recapito, String nomeSquadra ) {
		super(id, punti, partiteGiocate, partiteVinte, partitePerse);
		this.cognome=cognome;
		this.dataNascita=dataNascita;
		this.annoImmatricolazione=annoImmatricolazione;
		this.annoCorso=annoCorso;
		this.id=id;
		this.recapito=recapito;
		this.nomeSquadra=nomeSquadra;
	}

	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRecapito() {
		return recapito;
	}

	public void setRecapito(String recapito) {
		this.recapito = recapito;
	}

	public String getNomeSquadra() {
		return nomeSquadra;
	}

	public void setNomeSquadra(String nomeSquadra) {
		this.nomeSquadra = nomeSquadra;
	}

	public Date getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}
	public int getAnnoImmatricolazione() {
		return annoImmatricolazione;
	}
	public void setAnnoImmatricolazione(int annoImmatricolazione) {
		this.annoImmatricolazione = annoImmatricolazione;
	}
	public int getAnnoCorso() {
		return annoCorso;
	}
	public void setAnnoCorso(int annoCorso) {
		this.annoCorso = annoCorso;
	}

	private String cognome, id, recapito, nomeSquadra;
	private Date dataNascita;
	private int annoImmatricolazione, annoCorso;
	
}
