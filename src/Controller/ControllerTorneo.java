package Controller;

import gestori.GestoreDatiPersistenti;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import commons.*;


public class ControllerTorneo {
	
	private static ControllerTorneo istanza;
	private static GestoreDatiPersistenti dp;
	private HashMap<String, Torneo> tornei;
	
	public static ControllerTorneo getInstance() throws ParseException {
		if(istanza == null) 
			istanza = new ControllerTorneo();
		return istanza;
	}
	
	private ControllerTorneo() throws ParseException{
		this.dp=GestoreDatiPersistenti.getInstance();
		this.tornei= dp.caricaTornei();
	}
	
	public HashMap<String, Torneo> getTornei() throws ParseException{
		tornei = dp.caricaTornei();
		return this.tornei;
	}
	
	
	public Torneo creaTorneo() {
		return null;
		
	}
	
	public Partecipante creaPartecipante() {
		return null;
		
	}

	public static void visualizzaErrore(String errore) {
		JOptionPane.showMessageDialog(null,errore,"Errore",JOptionPane.ERROR_MESSAGE);

		
	}
	
	
	

	//private ArrayList<Partecipante> listPartecipanti;
	//private ArrayList<Torneo> listTornei;

}
