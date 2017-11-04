package commons;

import java.util.ArrayList;

public class Classifica {
	
	//Creare algoritmo per il calcolo dei punti
	
	public Classifica(ArrayList<Partecipante> listPartecipanti, ArrayList<Partita> listPartite) {
		this.listPartecipanti = listPartecipanti;
		this.listPartite = listPartite;
	}
	public void removePartecipante(Partecipante partecipante) {
		this.listPartecipanti.remove(partecipante);
	}
	
	public void addPartecipante(Partecipante partecipante) {
		this.listPartecipanti.add(partecipante);
	}
	
	public void removePartita(Partita partita) {
		this.listPartite.remove(partita);
	}

	public void addPartita(Partita partita) {
		this.listPartite.add(partita);
	}

	private ArrayList<Partecipante> listPartecipanti;
	private ArrayList<Partita> listPartite;
	
}
