package progettoEsame.utility;

//
import java.util.*;

//

/*
   Questa classe contiene le stringhe utilizzate nei pop up dell'applicazione.
*/
public class Costanti {

	public static final String intestazioneNessunaSelezione = "Errore selezione";
	
	public static final String testoNessunaSelezione = "Non è stata selezionata nessuna opzione!";
	
	public static final String intestazioneNomeMancante = "Errore inserimento";
	
	public static final String testoNomeMancante = "Non è stato inserito il nome!";
	
	public static final String intestazioneInserimentoErrato = "Inserimento errato";
	
	public static final String testoInserimentoErrato = "Il numero inserito è già presente nella stessa riga, colonna o griglia 3x3";
	
	public static final String intestazioneRegole = "Regole";
			
	public static final String testoRegole = "Il Sudoku è composto da una griglia 9×9, suddivisa in 9 blocchi 3×3.\r\n" + "\r\n"
			+ "L'obiettivo è completare la griglia inserendo i numeri da 1 a 9.\r\n" + "\r\n"
			+ "Ogni numero può comparire una sola volta:\r\n" + "\r\n"
			+ "in ogni riga;\r\n" + "in ogni colonna;\r\n" + "in ogni blocco 3×3.\r\n" + "\r\n"
			+ "I numeri già presenti all'inizio della partita sono fissi e non possono essere modificati.\r\n" + "\r\n"
			+ "Per inserire un numero, seleziona una casella vuota e premi il numero desiderato sul tastierino.\r\n" + "\r\n"
			+ "Se vuoi cancellare un numero inserito, seleziona la casella e premi X.\r\n" + "\r\n"
			+ "La partita termina quando tutte le caselle sono state completate correttamente.";
			
	public static final String intestazioneEsci = "Esci";
	
	public static final String testoEsci = "Vuoi uscire dal gioco?";
	
	public static final String intestazioneSalva = "Salva";
	
	public static final String testoSalva = "Vuoi salvare la partita?";
	
	public static final String intestazioneVittoria = "Vittoria!";
	
	public static final String testoVittoria = "Sudoku terminato con successo! \nHAI VINTO!";
	
	public static final String intestazioneClassifica = "Classifica";
	
	/*
	   Il metodo testoClassifica(List<Dati> classifica) prende i dati dalla classifica 
	   e crea una stringa contenente nome, livello e tempo di ogni partita, 
	   che viene mostrata dal pop up Classifica.
	*/
	public static String testoClassifica(List<Dati> classifica) {
		StringBuilder testo = new StringBuilder("Nome - Livello - Tempo \n");
		for (int i=0; i<classifica.size(); i++) {
			Dati dati = classifica.get(i);
			int n = i + 1;
			long tempoMinuti = dati.getTempo() / 60;
			long tempoSecondi = dati.getTempo() % 60;
			testo.append(n + ") " + dati.getNome() + " - " + dati.getLivello() + " - " + String.format("%02d:%02d", tempoMinuti, tempoSecondi) + "\n");
			if ((i<classifica.size()-1) && (!classifica.get(i+1).getLivello().equals(dati.getLivello()))) {
				testo.append(" - - - - - - - - - - - - - - - - - - - - - - - - - \n");
			}
		}
		return testo.toString();
	}
}
		