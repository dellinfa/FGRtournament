package commons;

import java.util.ArrayList;
import java.util.Date;

public class Calendario {
	
	
	//Creare ALGORITMO per la creazione del calendario
	
		public Calendario(Date dataSvolgimento, ArrayList<Partita> partite) {
		this.dataSvolgimento = dataSvolgimento;
		this.partite = partite;
	}
		
		public Date getDataSvolgimento() {
			return dataSvolgimento;
		}
		
		public void setDataSvolgimento(Date dataSvolgimento) {
			this.dataSvolgimento = dataSvolgimento;
		}
		
		public void addPartita(ArrayList<Partita> partite) {
			this.partite = partite;
		}


	private Date dataSvolgimento;
	private ArrayList<Partita> partite;
}
