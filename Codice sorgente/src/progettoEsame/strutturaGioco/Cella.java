package progettoEsame.strutturaGioco;

//
public class Cella {
	private final int coordinataX;
	private final int coordinataY;
	private int numero;
	private boolean fissa;
	
	public Cella(int coordinataX, int coordinataY) {	
		this.coordinataX = coordinataX;
		this.coordinataY = coordinataY;
		this.numero = 0;
		this.fissa = false;
	}
 
	public int getNumero() {
	    return numero;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public int getCoordinataX() {
	    return coordinataX;
	}
	
	public int getCoordinataY() {
	    return coordinataY;
	}
	
	public boolean getFissa() {
	    return fissa;
	}

	public void setFissa(boolean fissa) {
	    this.fissa = fissa;
	}
	
	public boolean vuota() {
		boolean vuota = false;
		if (getNumero() == 0) {
			vuota = true;
		}
		return vuota;
	}
	
	public void svuotaCella() {
		setNumero(0);
	}	
	
}
