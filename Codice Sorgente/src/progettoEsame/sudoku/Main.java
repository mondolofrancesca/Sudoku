package progettoEsame.sudoku;
//
import progettoEsame.gui.SchermataAvvio;
import progettoEsame.strutturaGioco.Classifica;
import progettoEsame.strutturaGioco.LogicaGioco;
import progettoEsame.utility.GestioneFileText;

//
public class Main {
	
	/* Il metodo main(String[] args) ha il compito di avviare il gioco, crea:
	   - l'oggetto gioco che gestisce la logica del gioco; 
	   - la classifica e carica i dati salvati nelle precedenti prtite;
	   - la schermata di avvio che permetterà all'utete di iniziare a giocare. */
    public static void main(String[] args) {
        LogicaGioco gioco = new LogicaGioco();
        Classifica classificazione = new Classifica();
        GestioneFileText.caricaClassifica(classificazione);
        new SchermataAvvio(gioco, classificazione); 
    } 
}
