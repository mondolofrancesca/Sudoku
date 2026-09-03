package progettoEsame.strutturaGioco;

//
public class SoluzioneSudoku {
	
	private GrigliaSudoku griglia;
	private int numeroSoluzioni;
	
	public SoluzioneSudoku(GrigliaSudoku griglia) {
		this.griglia = griglia;
	}
	
	
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
	
	
	public boolean trovaSoluzione() {
		numeroSoluzioni = 0;
		GrigliaSudoku grigliaCopia = griglia.copiaGriglia();
		boolean soluzionePresente = analizza(grigliaCopia, true);
		return soluzionePresente;
	}
	
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
