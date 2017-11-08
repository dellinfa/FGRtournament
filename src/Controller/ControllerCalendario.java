package Controller;

import gestori.GestoreDatiPersistenti;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import commons.*;

public class ControllerCalendario {
	
	public Calendario creaCalendario() {
		return null;
		
	}
	
	public static ControllerCalendario getInstance() throws ParseException {
		if(istanza == null) 
			istanza = new ControllerCalendario();
		return istanza;
	}
	
	private ControllerCalendario() throws ParseException{
		this.dp=GestoreDatiPersistenti.getInstance();
		this.calendari= dp.caricaCalendari();
	}
	

	public HashMap<String, Partita> getPartite() throws ParseException{
		partite = dp.caricaPartita();
		return this.partite;
	}
	
	public HashMap<String, Calendario> getCalendario() throws ParseException{
		calendari = dp.caricaCalendari();
		return this.calendari;
	}
	
	public static void visualizzaErrore(String errore) {
		JOptionPane.showMessageDialog(null,errore,"Errore",JOptionPane.ERROR_MESSAGE);

	}
	
	
	private static ControllerCalendario istanza;
	private static GestoreDatiPersistenti dp;
	private HashMap<String, Partita> partite;
	private HashMap<String, Calendario> calendari;
}

