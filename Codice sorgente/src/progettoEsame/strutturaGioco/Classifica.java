package progettoEsame.strutturaGioco;
//
import progettoEsame.utility.Dati;
//
import java.util.*;

//
public class Classifica {
	
	private List<Dati> listaDati;
	
	public Classifica() {
		listaDati = new ArrayList<>();
	}
	
	public void aggiungiDati(Dati dati) {
		listaDati.add(dati);
	}
	
	public List<Dati> getListaDati() {
		return listaDati;
	}
	
	public List<Dati> ordinaLivello(String livello) {
		List<Dati> listaLivello = new ArrayList<>();
		for (int i=0; i<listaDati.size(); i++) {
			if (listaDati.get(i).getLivello().equals(livello)) {
				listaLivello.add(listaDati.get(i));
			}
		}
		return listaLivello;
	}
	
	public void ordinaTempo(List<Dati> listaLivello){
		listaLivello.sort( (d1, d2)-> Long.compare(d1.getTempo(), d2.getTempo()) );
	}
	
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
