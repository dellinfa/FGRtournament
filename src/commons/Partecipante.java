package commons;

public class Partecipante {

	public Partecipante(String id, int punti, int partiteGiocate, int partiteVinte, int partitePerse) {
		this.id = id;
		this.punti = punti;
		this.partiteGiocate = partiteGiocate;
		this.partiteVinte = partiteVinte;
		this.partitePerse = partitePerse;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPunti() {
		return punti;
	}
	public void setPunti(int punti) {
		this.punti = punti;
	}
	public int getPartiteGiocate() {
		return partiteGiocate;
	}
	public void setPartiteGiocate(int partiteGiocate) {
		this.partiteGiocate = partiteGiocate;
	}
	public int getPartiteVinte() {
		return partiteVinte;
	}
	public void setPartiteVinte(int partiteVinte) {
		this.partiteVinte = partiteVinte;
	}
	public int getPartitePerse() {
		return partitePerse;
	}
	public void setPartitePerse(int partitePerse) {
		this.partitePerse = partitePerse;
	}
	
	private String id;
	private int punti, partiteGiocate, partiteVinte, partitePerse;
}
