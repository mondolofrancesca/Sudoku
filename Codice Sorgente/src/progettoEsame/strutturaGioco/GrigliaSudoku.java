package progettoEsame.strutturaGioco;

//
public class GrigliaSudoku {
	
	private Cella[][] celle;
	
	/*
	   Il costruttore ha il compito di creare una griglia composta da 81 celle.
	*/
	public GrigliaSudoku() {
		celle = new Cella[9][9];

	    for (int i = 0; i < 9; i++) {
	        for (int j = 0; j < 9; j++) {
	            celle[i][j] = new Cella(i, j);
	        }
	    }
	}
	
	/* 
	   Il metodo cercaCellaVuota() analizza tutte le celle della matrice fino a trovarne una vuota.
	   Se trova una cella vuota la restituisce, altrimenti restituisce null. 
	*/
	public Cella cercaCellaVuota() {
	    for (int r=0; r<9; r++) {
	    		for (int c=0; c<9; c++) {
			    if (celle[r][c].vuota()) {
			    		return celle[r][c];
				}
	    		}
	    }
	    return null;
	}

	/* 
	   Il metodo inserimentoPossibile(riga, colonna, numero) analizza righe, colonne e 
	   blocchi 3x3 a cui appartiene la cella per controllare che il numero che vogliamo 
	   inserire non sia già presente.
	   Se è già presente restituisce false, altrimenti true.
	*/
	public boolean inserimentoPossibile(int r, int c, int n) {
	    for(int i=0; i<9; i++) {
	    		if (celle[r][i].getNumero() == n) {
	    			return false;
	    		}
	    }
	    for (int i=0; i<9; i++) {
	        if (celle[i][c].getNumero() == n) {
	            return false;
	        }
	    }
	    int coordinataXCella = (r / 3) * 3;
	    int coordinataYCella = (c / 3) * 3;
	    for (int i=coordinataXCella; i<coordinataXCella + 3; i++) {
	        for (int j = coordinataYCella; j < coordinataYCella + 3; j++) {
	            if (celle[i][j].getNumero() == n) {
	                return false;
	            }
	        }
	    }
	    return true;
	}
	
	/* 
	   Il metodo copiaGriglia() permette di duplicare una griglia creandone una nuova e 
	   copiando per ogni cella il numero e il valore di fissa.
	   Restituisce la copia della griglia.
	*/ 
	public GrigliaSudoku copiaGriglia() {
		GrigliaSudoku copia = new GrigliaSudoku();
		for (int r=0; r<9; r++) {
			for (int c=0; c<9; c++) {
				copia.celle[r][c].setNumero(celle[r][c].getNumero());
				copia.celle[r][c].setFissa(celle[r][c].getFissa());
			}
		}
		return copia;
	}
	
	public Cella getCella(int riga, int colonna) {
	    return celle[riga][colonna];
	}
}
