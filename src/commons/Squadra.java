package commons;

import java.util.ArrayList;

public class Squadra extends Partecipante{

	public Squadra(String nome, int punti, int partiteGiocate, int partiteVinte, int partitePerse, int nComponenti,
			ArrayList<Giocatore> listGiocatori) {
		super(nome, punti, partiteGiocate, partiteVinte, partitePerse);
	}

	public void removeGiocatore(Giocatore giocatore) {
		this.listGiocatori.remove(giocatore);
	}
	
	public void addGiocatore(Giocatore giocatore) {
		this.listGiocatori.add(giocatore);
	}
	
	//iahbohabehscbushefbsugevbsgfvb
	
	private int nComponenti;
	private ArrayList<Giocatore> listGiocatori;
} 
