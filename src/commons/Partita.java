package commons;

public class Partita {

	public Partita(int risultatoPartecipanteCasa, int risultatoPartecipanteOspite, Partecipante partecipanteCasa, Partecipante partecipanteOspite) {
		this.partecipanteCasa = partecipanteCasa;
		this.partecipanteOspite = partecipanteOspite;
		this.risultatoPartecipanteCasa = risultatoPartecipanteCasa;
		this.risultatoPartecipanteOspite = risultatoPartecipanteOspite;
		
	}	
	
	public Partecipante getPartecipanteCasa() {
		return partecipanteCasa;
	}
	public void setPartecipanteCasa(Partecipante partecipanteCasa) {
		this.partecipanteCasa = partecipanteCasa;
	}
	public Partecipante getPartecipanteOspite() {
		return partecipanteOspite;
	}
	public void setPartecipanteOspite(Partecipante partecipanteOspite) {
		this.partecipanteOspite = partecipanteOspite;
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

	private Partecipante partecipanteCasa, partecipanteOspite;
	private int risultatoPartecipanteCasa, risultatoPartecipanteOspite;
}
