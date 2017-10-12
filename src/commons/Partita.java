package commons;

public class Partita {

	public Partita(Partecipante partecipante1, Partecipante partecipante2, int risultatoPartecipante1,
			int risultatoPartecipante2, Enum stato) {
		this.partecipante1 = partecipante1;
		this.partecipante2 = partecipante2;
		this.risultatoPartecipante1 = risultatoPartecipante1;
		this.risultatoPartecipante2 = risultatoPartecipante2;
		this.stato = stato;
	}	
	
	public int getRisultatoPartecipante1() {
		return risultatoPartecipante1;
	}
	public void setRisultatoPartecipante1(int risultatoPartecipante1) {
		this.risultatoPartecipante1 = risultatoPartecipante1;
	}
	public int getRisultatoPartecipante2() {
		return risultatoPartecipante2;
	}
	public void setRisultatoPartecipante2(int risultatoPartecipante2) {
		this.risultatoPartecipante2 = risultatoPartecipante2;
	}




	private Partecipante partecipante1, partecipante2;
	private int risultatoPartecipante1, risultatoPartecipante2;
	private Enum stato;
}
