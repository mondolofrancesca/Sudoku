package progettoEsame.strutturaGioco;
//
import progettoEsame.utility.Dati;
//
import java.util.*;

//
public class Classifica {
	
	private List<Dati> listaDati;
	
	/*
	   Il costruttore inizializza la lista che contiene i dati delle partite.
	*/
	public Classifica() {
		listaDati = new ArrayList<>();
	}
	
	public void aggiungiDati(Dati dati) {
		listaDati.add(dati);
	}
	
	public List<Dati> getListaDati() {
		return listaDati;
	}
	
	/*
	   Il metodo ordinaLivello(String livello) riceve in ingresso il livello secondo cui 
	   vogliamo selezionare i dati presenti all'interno della listaDati.
	   Viene esaminato ogni elemento della lista e quelli che hanno il livello richiesto 
	   vengono inseriti in una nuova lista che viene poi restituita.
	*/
	public List<Dati> ordinaLivello(String livello) {
		List<Dati> listaLivello = new ArrayList<>();
		for (int i=0; i<listaDati.size(); i++) {
			if (listaDati.get(i).getLivello().equals(livello)) {
				listaLivello.add(listaDati.get(i));
			}
		}
		return listaLivello;
	}
	
	/*
	   Il metodo ordinaTempo(List<Dati> listaLivello) riceve in ingresso una lista di 
	   dati che modifica riordinando i dati presenti in modo crescente in base al tempo.  
	*/
	public void ordinaTempo(List<Dati> listaLivello){
		listaLivello.sort((d1, d2)-> Long.compare(d1.getTempo(), d2.getTempo()));
	}
	
	/*
	   Il metodo creaClassifica() crea e restituisce una lista di dati ordinati prima in 
	   base al livello, grazie al metodo ordinaLivello(String livello), e poi in base al 
	   tempo di esecuzione del sudoku, grazie al metodo ordinaTempo(List<Dati> listaLivello);
	   i dati dei tre livelli vengono infine inseriti nella lista classifica complessiva.
	*/
	public List<Dati> creaClassifica() {
		List<Dati> classifica = new ArrayList<>();
		List<Dati> facile = ordinaLivello("Facile");
		List<Dati> medio = ordinaLivello("Medio");
		List<Dati> difficile = ordinaLivello("Difficile");
		
		ordinaTempo(facile);
		ordinaTempo(medio);
		ordinaTempo(difficile);
		
		classifica.addAll(facile);
		classifica.addAll(medio);
		classifica.addAll(difficile);
		
		return classifica;
	}
}
