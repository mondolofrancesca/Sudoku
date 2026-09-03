package progettoEsame.strutturaGioco;

//
public class LogicaGioco {
	
	private GrigliaSudoku grigliaPartita;
	private GrigliaSudoku grigliaSoluzione;
	
	public LogicaGioco() {
		this.grigliaPartita = null;
		this.grigliaSoluzione = null;
	}
	
	/* 
	   Il metodo nuovaPartita(String livello) crea una nuova partita, creando: 
	   - la griglia con la soluzione (griglia piena);
	   - la griglia che vedrà il giocatore (griglia sudoku).
	   La griglia viene controllata per verificare che abbia una soluzione unica;
	   se non è così, viene generata una nuova partita.
	*/
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

	
	/* 
	   Il metodo inserisciNumero(riga, colonna, numero) controlla che la cella selezionata 
	   dall'utente sia modificabile, se è così fa un controllo con inserimentoPossibile.
	   Se il controllo ha esito positivo allora il numero viene inserito e il metodo 
	   restituisce true, altrimenti false. 
	*/
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
	
	/*
	   Il metodo rimuoviNumero(riga, colonna) controlla che la cella da cui si vuole 
	   rimuovere il numero sia modificabile e non vuota, se le condizioni vengono
	   rispettate si applica alla cella il metodo svuotaCella().
	   Viene restituito true se il numero viene rimosso, altrimenti false.
	*/
	public boolean rimuoviNumero(int riga, int colonna) {
		boolean numeroRimosso = false;
		Cella cella = grigliaPartita.getCella(riga, colonna);
		if (cella.getNumero() != 0 && !cella.getFissa()) {
			cella.svuotaCella();
			numeroRimosso = true;
		}
		return numeroRimosso;
	}
	
	/* 
	   Il metodo terminePartita() controlla se sono presenti celle vuote, 
	   se non ce ne sono controlla che gli inserimenti fatti siano corretti confrontando
	   questa griglia riempita dall'utente con la griglia contenente la soluzione.
	   Se tutti gli inserimenti sono corretti il gioco finisce e viene restituito true,
	   altrimenti false. 
	*/
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
