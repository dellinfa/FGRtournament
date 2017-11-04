package commons;

import java.util.ArrayList;
import java.util.Date;

public class Torneo {
	
	public Torneo(String sport, String modSvolgimentoIncontro, String modAttribuzionePtGara, int numPartecipanti,
			Date dataInizioTorneo, Date dataFineTorneo, Calendario calendario, Classifica classifica, ArrayList<Partecipante> listPartecipanti) {
		this.modSvolgimentoIncontro = modSvolgimentoIncontro;
		this.modAttribuzionePtGara = modAttribuzionePtGara;
		this.numPartecipanti = numPartecipanti;
		this.dataInizioTorneo = dataInizioTorneo;
		this.dataFineTorneo = dataFineTorneo;
		this.sport=sport;
		this.calendario = calendario;
		this.classifica = classifica;
		this.listPartecipanti = listPartecipanti;
		
	}



	public String getSport() {
		return sport;
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
	public int getNumPartecipanti() {
		return numPartecipanti;
	}
	public void setNumPartecipanti(int numPartecipanti) {
		this.numPartecipanti = numPartecipanti;
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
	public ArrayList<Partecipante> getListPartecipanti() {
		return listPartecipanti;
	}
	public void setListPartecipanti(ArrayList<Partecipante> listPartecipanti) {
		this.listPartecipanti = listPartecipanti;
	}
	
	public void addListPartecipanti(Partecipante partecipante) {
		this.listPartecipanti.add(partecipante);
	}



	private String sport;
	private String modSvolgimentoIncontro, modAttribuzionePtGara;
	private int numPartecipanti;
	private Date dataInizioTorneo, dataFineTorneo;
	private Calendario calendario;
	private Classifica classifica;
	private ArrayList<Partecipante> listPartecipanti;
}
