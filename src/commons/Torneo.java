package commons;

import java.util.ArrayList;
import java.util.Date;

public class Torneo {
	
	public Torneo(String modIscrizione, String modSvolgimentoIncontro, String modAttribuzionePtGara, int maxPartecipanti,
			int intervalloTempo, Date dataInizioTorneo, Date dataFineTorneo, String sport, Calendario calendario) {
		this.modIscrizione = modIscrizione;
		this.modSvolgimentoIncontro = modSvolgimentoIncontro;
		this.modAttribuzionePtGara = modAttribuzionePtGara;
		this.maxPartecipanti = maxPartecipanti;
		this.intervalloTempo = intervalloTempo;
		this.dataInizioTorneo = dataInizioTorneo;
		this.dataFineTorneo = dataFineTorneo;
		this.sport=sport;
		this.calendario = calendario;
//		this.classifica = classifica;
//		this.listPartecipanti = listPartecipanti;
		
	}
//	
//	public void removePartecipante(Partecipante partecipante) {
//		this.listPartecipanti.remove(partecipante);
//	}
	
	public String getModIscrizione() {
		return modIscrizione;
	}

	public void setModIscrizione(String modIscrizione) {
		this.modIscrizione = modIscrizione;
	}

	public String getSport() {
		return this.sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}

	public String getModSvolgimentoIncontro() {
		return modSvolgimentoIncontro;
	}

	public void setModSvolgimentoIncontro(String modSvolgimentoIncontro) {
		this.modSvolgimentoIncontro = modSvolgimentoIncontro;
	}

	public String getModAttribuzionePtGara() {
		return modAttribuzionePtGara;
	}

	public void setModAttribuzionePtGara(String modAttribuzionePtGara) {
		this.modAttribuzionePtGara = modAttribuzionePtGara;
	}

	public int getMaxPartecipanti() {
		return maxPartecipanti;
	}

	public void setMaxPartecipanti(int maxPartecipanti) {
		this.maxPartecipanti = maxPartecipanti;
	}

	public int getIntervalloTempo() {
		return intervalloTempo;
	}

	public void setIntervalloTempo(int intervalloTempo) {
		this.intervalloTempo = intervalloTempo;
	}

	public Date getDataInizioTorneo() {
		return dataInizioTorneo;
	}

	public void setDataInizioTorneo(Date dataInizioTorneo) {
		this.dataInizioTorneo = dataInizioTorneo;
	}

	public Date getDataFineTorneo() {
		return dataFineTorneo;
	}

	public void setDataFineTorneo(Date dataFineTorneo) {
		this.dataFineTorneo = dataFineTorneo;
	}

//	public ArrayList<Partecipante> getListPartecipanti() {
//		return listPartecipanti;
//	}
//
//	public void setListPartecipanti(ArrayList<Partecipante> listPartecipanti) {
//		this.listPartecipanti = listPartecipanti;
//	}
//
//	public void addPartecipante(Partecipante partecipante) {
//		this.listPartecipanti.add(partecipante);
//	}
		
	public Calendario getCalendario() {
		return calendario;
	}

	public void setCalendario(Calendario calendario) {
		this.calendario = calendario;
	}

//	public Classifica getClassifica() {
//		return classifica;
//	}
//
//	public void setClassifica(Classifica classifica) {
//		this.classifica = classifica;
//	}




	private String modIscrizione, sport;
	private String modSvolgimentoIncontro, modAttribuzionePtGara;
	private int maxPartecipanti, intervalloTempo;
	private Date dataInizioTorneo, dataFineTorneo;
	private Calendario calendario;
//	private Classifica classifica;
//	private ArrayList<Partecipante> listPartecipanti;
}
