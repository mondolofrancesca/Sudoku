package progettoEsame.strutturaGioco;

//
public class GrigliaSudoku {
	
	private Cella[][] celle;
	
	public GrigliaSudoku() {
		celle = new Cella[9][9];

	    for (int i = 0; i < 9; i++) {
	        for (int j = 0; j < 9; j++) {
	            celle[i][j] = new Cella(i, j);
	        }
	    }
	}
	
	/* Analizza tutte le celle della matrice fino a trovarne una vuota.
	   Se trova una cella vuota la restituisce, altrimenti restituisce null. */
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

	/* Analizza righe, colonne e griglia 3x3 per controllare che il numero che vogliamo 
	   inserire non sia già peresente.
	   Se già presente restituisce false, altrimenti true 
	   (true = via libera / false = stop)*/
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
	
	/* Permette di duplicare una griglia facendone una copia di ogni cella.
	   Restituisce la copia. */ 
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
