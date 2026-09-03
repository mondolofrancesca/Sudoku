package progettoEsame.utility;

//
public class Dati {
	
	private String nome;
	private String livello;
	private long tempo;
	
	public Dati(String nome, String livello, long tempo) {
		this.nome = nome;
		this.livello = livello;
		this.tempo = tempo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getLivello() {
		return livello;
	}
	
	public long getTempo() {
		return tempo;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setLivello(String livello) {
		this.livello = livello;
	}
	
	public void setTempo(long tempo) {
		this.tempo = tempo;
	}
	
}
