package progettoEsame.sudoku;
//
import progettoEsame.gui.SchermataAvvio;
import progettoEsame.strutturaGioco.Classifica;
import progettoEsame.strutturaGioco.LogicaGioco;
import progettoEsame.utility.GestioneFileText;

//
public class Main {
	
    public static void main(String[] args) {
        LogicaGioco gioco = new LogicaGioco();
        Classifica classificazione = new Classifica();
        GestioneFileText.caricaClassifica(classificazione);
        new SchermataAvvio(gioco, classificazione); 
    } 
}
