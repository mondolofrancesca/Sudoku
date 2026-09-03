package progettoEsame.gui;
// 
import progettoEsame.utility.Costanti;
import progettoEsame.utility.Dati;

//
import java.util.*;
import javax.swing.*;

//
public class PopUp {
	
	/* Questo pop up viene mostrato quando l'utente non seleziona nulla 
	   nella schermata di avvio gioco. */
	public static void nessunaSelezione(JFrame parent) {
		JOptionPane.showMessageDialog(
				parent, 
				Costanti.testoNessunaSelezione, 
				Costanti.intestazioneNessunaSelezione, 
				JOptionPane.ERROR_MESSAGE
				);
	}
	
	/* Questo pop up viene mostrato quando l'utente non inserisce il proprio nome 
	   nella schermata di avvio gioco. */
	public static void nomeMancante(JFrame parent) {
		JOptionPane.showMessageDialog(
				parent, 
				Costanti.testoNomeMancante, 
				Costanti.intestazioneNomeMancante, 
				JOptionPane.ERROR_MESSAGE
				);
	}
	
	/* Questo pop up viene mostrato quando l'utente inserisce nel sudoku un numero già 
	   presente nella stessa riga/colonna/blocco 3x3 (schermata di gioco). */
	public static void inserimentoErrato(JFrame parent) {
		JOptionPane.showMessageDialog(
				parent, 
				Costanti.testoInserimentoErrato,
				Costanti.intestazioneInserimentoErrato, 
				JOptionPane.ERROR_MESSAGE
				);
	}
	
	/* Questo pop up mostra le regole del gioco, 
	   il bottone è disponibile nella schermata di avvio e di gioco. */
	public static void mostraRegole(JFrame parent) {
		JOptionPane.showMessageDialog(
				parent, 
				Costanti.testoRegole, 
				Costanti.intestazioneRegole, 
				JOptionPane.INFORMATION_MESSAGE
				);
	}
	
	/* Questo pop up mostra la classifica creata partita dopo partita. 
	   La classifica si basa sul tempo di esecuzione del sudoku,
	   il bottone è disponibile nella schermata di avvio e di gioco. */
	public static void mostraClassifica(JFrame parent, List<Dati> classifica) {
		JOptionPane.showMessageDialog(
				parent, 
				Costanti.testoClassifica(classifica), 
				Costanti.intestazioneClassifica, 
				JOptionPane.INFORMATION_MESSAGE
				);
	}
	
	/* Questo pop up viene mostrato quando l'utente vuole uscire dal gioco, gli si pone una  
	   domanda di conferma prima di uscire (schermata di gioco). */
	public static boolean esci(JFrame parent) {
		boolean uscire = false; 
		int scelta = JOptionPane.showConfirmDialog(
				parent, 
				Costanti.testoEsci, 
				Costanti.intestazioneEsci, 
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE
				);
		if (scelta == JOptionPane.YES_OPTION) {
			uscire = true;
		}
		return uscire;
	}
	
	/* Questo pop up viene mostrato quando l'utente vuole uscire dal gioco, gli si chiede se 
	   desidera salvare la partita (schermata di gioco). */
	public static boolean salva(JFrame parent) {
		boolean salvataggio = false; 
		int scelta = JOptionPane.showConfirmDialog(
				parent, 
				Costanti.testoSalva, 
				Costanti.intestazioneSalva, 
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE
				);
		if (scelta == JOptionPane.YES_OPTION) {
			salvataggio = true;
		}
		return salvataggio;
	}
	
	/* Questo pop up viene mostrato quando l'utente termina con successo il sudoku 
	   (schermata di gioco). */
	public static void vittoria(JFrame parent) {
		JOptionPane.showMessageDialog(
				parent, 
				Costanti.testoVittoria,
				Costanti.intestazioneVittoria, 
				JOptionPane.INFORMATION_MESSAGE
				);
	}
	
}
