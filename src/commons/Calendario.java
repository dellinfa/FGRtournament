package commons;

import java.util.ArrayList;
import java.util.Date;

public class Calendario {
	
		public Calendario(Date dataSvolgimento, ArrayList<Partita> listPartite) {
		this.dataSvolgimento = dataSvolgimento;
		this.listPartite = listPartite;
	}
		
		public Date getDataSvolgimento() {
			return dataSvolgimento;
		}
		
		public void setDataSvolgimento(Date dataSvolgimento) {
			this.dataSvolgimento = dataSvolgimento;
		}
		
		public void addPartita(ArrayList<Partita> listPartite) {
			this.listPartite = listPartite;
		}


	private Date dataSvolgimento;
	private ArrayList<Partita> listPartite;
}
