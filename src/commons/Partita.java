package commons;

public class Partita {

	public Partita(int risultatoPartecipanteCasa, int risultatoPartecipanteOspite, String idPartita, String idPartecipanteCasa, String idPartecipanteOspite, String idCalendario) {
		this.idPartecipanteCasa = idPartecipanteCasa;
		this.idPartecipanteOspite = idPartecipanteOspite;
		this.idPartita=idPartita;
		this.risultatoPartecipanteCasa = risultatoPartecipanteCasa;
		this.risultatoPartecipanteOspite = risultatoPartecipanteOspite;
		this.idCalendario= idCalendario;
		
	}	
	
	
	public String getIdPartecipanteCasa() {
		return idPartecipanteCasa;
	}


	public void setIdPartecipanteCasa(String idPartecipanteCasa) {
		this.idPartecipanteCasa = idPartecipanteCasa;
	}


	public String getIdPartecipanteOspite() {
		return idPartecipanteOspite;
	}


	public String getIdPartita() {
		return idPartita;
	}


	public void setIdPartita(String idPartita) {
		this.idPartita = idPartita;
	}


	public String getIdCalendario() {
		return idCalendario;
	}


	public void setIdCalendario(String idCalendario) {
		this.idCalendario = idCalendario;
	}


	public void setIdPartecipanteOspite(String idPartecipanteOspite) {
		this.idPartecipanteOspite = idPartecipanteOspite;
	}


	public int getRisultatoPartecipanteCasa() {
		return risultatoPartecipanteCasa;
	}
	public void setRisultatoPartecipanteCasa(int risultatoPartecipanteCasa) {
		this.risultatoPartecipanteCasa = risultatoPartecipanteCasa;
	}
	public int getRisultatoPartecipanteOspite() {
		return risultatoPartecipanteOspite;
	}
	public void setRisultatoPartecipanteOspite(int risultatoPartecipanteOspite) {
		this.risultatoPartecipanteOspite = risultatoPartecipanteOspite;
	}

	private String idPartita,idCalendario,idPartecipanteCasa, idPartecipanteOspite;;
	private int risultatoPartecipanteCasa, risultatoPartecipanteOspite;
}
