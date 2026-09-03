package progettoEsame.strutturaGioco;
//
import java.util.*;

//
public class GeneraSudoku {
	
	private GrigliaSudoku griglia;
	private String livello;
	
	/*
	   Il costruttore riceve la griglia del sudoku e il livello selezionato dall'utente.
	*/
	public GeneraSudoku(GrigliaSudoku griglia, String livello) {
		this.griglia = griglia;
		this.livello = livello;
	}
	
	public List<Integer> generaLista(){
		List<Integer> numeri = new ArrayList<>();
		for (int i=1; i<10; i++) {
	    		numeri.add(i);
	    }
	    Collections.shuffle(numeri);
	    return numeri;
	}
	
	/* 
	   Il metodo riempiMatrice() ha il compito di riempire la griglia del sudoku. 
	   Una volta trovata una cella vuota, cerca di riempirla con un numero casuale da 1 a 9 preso da listaNumeri
	   e avviene il controllo della validità tramite il metodo inserimentoPossibile(riga, colonna, numero). 
	   Il metodo viene ripreso e continua fino al riempimento completo della matrice del sudoku. 
	   Se durante il riempimento uno dei numeri precedentemente inseriti non dovesse andare bene 
	   viene sostituito con un altro e il controllo/riempimento riprende da lì.
	   Se il riempimento va a buon fine restituisce true, altrimenti false; 
	   in base alla restituzione il metodo decide come proseguire. 
	*/
	public boolean riempiMatrice() {
	    Cella cella = griglia.cercaCellaVuota();
	    	if (cella == null) {
	        return true;
	    	}
	    else {
	        int riga = cella.getCoordinataX();
	        int colonna = cella.getCoordinataY();
	        List<Integer> listaNumeri = generaLista(); 
	        for (int i=0; i<listaNumeri.size(); i++) {
	        		int numero = listaNumeri.get(i);
	        		if (griglia.inserimentoPossibile(riga, colonna, numero)) {
	                griglia.getCella(riga, colonna).setNumero(numero);
	                if (riempiMatrice()) {
	                    return true;
	                }
	                griglia.getCella(riga, colonna).svuotaCella();
	            }
	        }
	    }
	    return false;
	}
	
	/* 
	   Il metodo sudokuPerLivello() copia la griglia completa creata in precedenza da 
	   riempiMatrice(), per mantenere la griglia originale, e modifica la copia rimuovendo 
	   n celle in modo casuale in base al livello scelto nella schermata di avvio.
	   Le celle rimanenti diventano la struttura del sudoku e non saranno modificabili dall'utente.
	   Viene restituita la copia della griglia con le celle svuotate. 
	*/
	public GrigliaSudoku sudokuPerLivello(){
		GrigliaSudoku celleSudoku = griglia.copiaGriglia();
		int j = 0;
		Random random = new Random();
		//
	    if (livello.equals("Facile")) {
			// devo svuotare 45 caselle (36 piene)
			j = 45;
		}
		else if (livello.equals("Medio")) {
			// devo svuotare 53 caselle (28 piene)
			j = 53;
		}
		else if (livello.equals("Difficile")) {
			// devo svuotare 61 caselle (20 piene)
			j = 61;
		}
	    for (int i=0; i<j; i++) {
			int riga = random.nextInt(9);
			int colonna = random.nextInt(9);
			while (celleSudoku.getCella(riga, colonna).vuota()) {
				riga = random.nextInt(9);
				colonna = random.nextInt(9);
			}
			celleSudoku.getCella(riga, colonna).svuotaCella();
		}
	    for (int r=0; r<9; r++) {
	    		for (int c=0; c<9; c++) {
	    			if (!celleSudoku.getCella(r, c).vuota()) {
	    				celleSudoku.getCella(r, c).setFissa(true);
	    			}
	    		}
	    }
	    return celleSudoku;
	}
		
}
