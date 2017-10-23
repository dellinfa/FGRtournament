package commons;

import java.util.ArrayList;
import java.util.Date;

public class Torneo {
	
	public Torneo(String modIscrizione, Enum modSvolgimentoIncontro, Enum modAttribuzionePtGara, int maxPartecipanti,
			int intervalloTempo, Date dataInizioTorneo, Date dataFineTorneo, Calendario calendario,
			Classifica classifica, ArrayList<Partecipante> listPartecipanti, String sport) {
		this.modIscrizione = modIscrizione;
		this.modSvolgimentoIncontro = modSvolgimentoIncontro;
		this.modAttribuzionePtGara = modAttribuzionePtGara;
		this.maxPartecipanti = maxPartecipanti;
		this.intervalloTempo = intervalloTempo;
		this.dataInizioTorneo = dataInizioTorneo;
		this.dataFineTorneo = dataFineTorneo;
		this.calendario = calendario;
		this.classifica = classifica;
		this.listPartecipanti = listPartecipanti;
		this.sport=sport;
	}
	
	public void removePartecipante(Partecipante partecipante) {
		this.listPartecipanti.remove(partecipante);
	}
	
	public void addPartecipante(Partecipante partecipante) {
		this.listPartecipanti.add(partecipante);
	}
		
	public Calendario getCalendario() {
		return calendario;
	}

	public void setCalendario(Calendario calendario) {
		this.calendario = calendario;
	}

	public Classifica getClassifica() {
		return classifica;
	}

	public void setClassifica(Classifica classifica) {
		this.classifica = classifica;
	}




	private String modIscrizione, sport;
	private Enum modSvolgimentoIncontro, modAttribuzionePtGara;
	private int maxPartecipanti, intervalloTempo;
	private Date dataInizioTorneo, dataFineTorneo;
	private Calendario calendario;
	private Classifica classifica;
	private ArrayList<Partecipante> listPartecipanti;
}
