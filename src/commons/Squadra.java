package commons;

import java.util.ArrayList;

public class Squadra extends Partecipante{

	public Squadra(String id, int punti, int partiteGiocate, int partiteVinte, int partitePerse, int nComponenti,
			ArrayList<Giocatore> listGiocatori) {
		super(id, punti, partiteGiocate, partiteVinte, partitePerse);
	}

	public int getnComponenti() {
		return nComponenti;
	}
	public void setnComponenti(int nComponenti) {
		this.nComponenti = nComponenti;
	}
	public ArrayList<Giocatore> getListGiocatori() {
		return listGiocatori;
	}
	public void setListGiocatori(ArrayList<Giocatore> listGiocatori) {
		this.listGiocatori = listGiocatori;
	}
	
	private int nComponenti;
	private ArrayList<Giocatore> listGiocatori;

} 