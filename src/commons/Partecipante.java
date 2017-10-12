package commons;

public class Partecipante {

	public Partecipante(String nome, int punti, int partiteGiocate, int partiteVinte, int partitePerse) {
		this.nome = nome;
		this.punti = punti;
		this.partiteGiocate = partiteGiocate;
		this.partiteVinte = partiteVinte;
		this.partitePerse = partitePerse;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	private String nome;
	private int punti, partiteGiocate, partiteVinte, partitePerse;
}
