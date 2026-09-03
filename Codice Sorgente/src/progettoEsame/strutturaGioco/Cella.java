package progettoEsame.strutturaGioco;

//
public class Cella {
	private final int coordinataX;
	private final int coordinataY;
	private int numero;
	private boolean fissa;
	
	/*
	   Il metodo costruttore riceve in ingresso le coordinate delle celle e 
	   definisce gli attributi che ogni cella deve avere: 
	   - coordinataX (identifica la riga);
	   - coordinataY (identifica la colonna);
	   - numero (inizialmente impostato a 0);
	   - fissa (indica se la cella contiene un numero iniziale non modificabile).
	*/
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
	
	/*
	   Il metodo vuota() indica se la cella a cui viene applicato il metodo sia vuota o meno,
	   restituendo true se è vuota (contiene 0) e false altrimenti. 
	*/
	public boolean vuota() {
		boolean vuota = false;
		if (getNumero() == 0) {
			vuota = true;
		}
		return vuota;
	}
	
	/*
	   Il metodo svuotaCella() ha il compito di impostare a 0 il numero della cella a cui
	   viene applicato il metodo. 
	*/
	public void svuotaCella() {
		setNumero(0);
	}	
	
}
