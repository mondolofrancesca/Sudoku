package progettoEsame.strutturaGioco;

//
public class LogicaGioco {
	
	private GrigliaSudoku grigliaPartita;
	private GrigliaSudoku grigliaSoluzione;
	
	public LogicaGioco() {
		this.grigliaPartita = null;
		this.grigliaSoluzione = null;
	}
	
	/* Con questa funzione viene creata una nuova partita, prima crea la griglia con la 
	   soluzione, riempita grazie al generatore di sudoku, poi la griglia che vedrà il 
	   giocatore, svuotata di n celle in base al livello scelto.
	   Restituisce la griglia per la partita. */
	
	public void nuovaPartita(String livello) {
		boolean sudokuValida = false;
		while (!sudokuValida) {
			grigliaSoluzione = new GrigliaSudoku();
			GeneraSudoku generatore = new GeneraSudoku(grigliaSoluzione, livello);
			generatore.riempiMatrice();
			grigliaPartita = generatore.sudokuPerLivello();
			SoluzioneSudoku soluzione = new SoluzioneSudoku(grigliaPartita);
			sudokuValida = soluzione.soluzioneUnica();
		}
	}

	
	/* Inserisci numero controlla che la cella selezionata dall'utente sia modificabile, 
	   se è così fa un controllo con inserimentoPossibile.
	   Se il riscontro è positivo allora il numero viene inserito e il metodo 
	   restituisce true, altrimenti false */
	public boolean inserisciNumero(int riga, int colonna, int numero) {
		boolean numeroInserito = false;
		Cella cella = grigliaPartita.getCella(riga, colonna);
		if (!cella.getFissa()) {
			if (grigliaPartita.inserimentoPossibile(riga, colonna, numero)){
				cella.setNumero(numero);
                	numeroInserito = true;
			}
		}
		return numeroInserito;
	}
	
	
	public boolean rimuoviNumero(int riga, int colonna) {
		boolean numeroRimosso = false;
		Cella cella = grigliaPartita.getCella(riga, colonna);
		if (cella.getNumero() != 0 && !cella.getFissa()) {
			cella.svuotaCella();
			numeroRimosso = true;
		}
		return numeroRimosso;
	}
	
	/* Questa funzione controlla la presenza di caselle vuote, se non ce ne sono controlla
	   che gli inserimenti fatti siano corretti.
	   Se tutti gli inserimenti sono corretti il gioco finisce e viene restituito true,
	   altrimenti false. */
	public boolean terminePartita() {
		boolean partitaTerminata = true;
		if (grigliaPartita.cercaCellaVuota() == null) {
			for (int r=0; r<9; r++) {
				for (int c=0; c<9; c++) {
					if (grigliaPartita.getCella(r, c).getNumero() != grigliaSoluzione.getCella(r, c).getNumero()){
						partitaTerminata = false;
						break;
					}
				}
			}
		}
		else {
			partitaTerminata = false;
		}
		return partitaTerminata;
	}
	
	public GrigliaSudoku getGrigliaPartita() {
	    return grigliaPartita;
	}

	public GrigliaSudoku getGrigliaSoluzione() {
	    return grigliaSoluzione;
	}

	public void setGrigliaPartita(GrigliaSudoku grigliaPartita) {
	    this.grigliaPartita = grigliaPartita;
	}

	public void setGrigliaSoluzione(GrigliaSudoku grigliaSoluzione) {
	    this.grigliaSoluzione = grigliaSoluzione;
	}
}
