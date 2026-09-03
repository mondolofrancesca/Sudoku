package progettoEsame.strutturaGioco;

//
public class SoluzioneSudoku {
	
	private GrigliaSudoku griglia;
	private int numeroSoluzioni;
	
	public SoluzioneSudoku(GrigliaSudoku griglia) {
		this.griglia = griglia;
	}
	
	/*
	   Il metodo soluzioneUnica() ha il compito di capire se la griglia sudoku creata 
	   abbia una unica soluzione e quindi possa essere utilizzata per giocare.
	   Restituisce true se il numero delle soluzioni è uno, altrimenti false. 
	*/
	public boolean soluzioneUnica() {
		boolean unicaSoluzione = false;
		numeroSoluzioni = 0;
		GrigliaSudoku grigliaCopia = griglia.copiaGriglia();
		analizza(grigliaCopia, false);
		if (numeroSoluzioni == 1) {
			unicaSoluzione = true;
		}
		return unicaSoluzione;
	}
	
	/*
	   Il metodo trovaSoluzione() ha il compito di capire se la griglia sudoku creata 
	   sia risolvibile.
	   Restituisce true se è presente una soluzione, altrimenti false. 
	*/
	public boolean trovaSoluzione() {
		numeroSoluzioni = 0;
		GrigliaSudoku grigliaCopia = griglia.copiaGriglia();
		boolean soluzionePresente = analizza(grigliaCopia, true);
		return soluzionePresente;
	}
	
	/*
	   Il metodo analizza(griglia, boolean) ha il compito di analizzare la griglia appena 
	   creata e pronta per il gioco, ci sono due scenari possibili:
	   - il metodo viene richimato da trovaSoluzione(), quindi deve controllare
	     che sia presente una soluzione;
	   - il metodo viene richimato da soluzioneUnica(), quindi deve controllare 
	     quante soluzioni sono presenti nella griglia.
	     
	   Quando viene trovata una soluzione, il numero delle soluzioni viene incrementato 
	   (se non ci sono celle vuote cercaCellaVuota() restituisce null).
	   
	   - Se analizza() viene invocato da trovaSoluzione() primaSoluzione vale true, 
	     il metodo si ferma alla prima soluzione trovata;
	   - Se analizza() viene invocato da soluzioneUnica() primaSoluzione vale false,
	     il metodo continua fino a quando viene trovata più di una soluzione.
	     
	   Viene restituito true se è presente una soluzione e primaSoluzione vale true, 
	   altrimenti se non ci sono soluzioni o ce n'è più di una restituisce false.  
	*/
	private boolean analizza(GrigliaSudoku griglia, boolean primaSoluzione) {
		Cella cella = griglia.cercaCellaVuota();
	    	if (cella == null) {
	    		numeroSoluzioni++;
	        return primaSoluzione;
	    	}
	    else {
	        int riga = cella.getCoordinataX();
	        int colonna = cella.getCoordinataY();
	        for (int numero=1; numero<10; numero++) {
	        		if (griglia.inserimentoPossibile(riga, colonna, numero)) {
	                cella.setNumero(numero);
	                if (analizza(griglia, primaSoluzione) && primaSoluzione) {
	                		return true;
	                }
	                cella.svuotaCella();
	                if (!primaSoluzione && numeroSoluzioni > 1) {
	                		return false;
	                }
	            }
	        }
	    }
		return false;
	}
	
}
