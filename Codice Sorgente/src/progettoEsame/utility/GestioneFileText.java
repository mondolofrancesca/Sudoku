package progettoEsame.utility;
//
import progettoEsame.strutturaGioco.Cella;
import progettoEsame.strutturaGioco.Classifica;
import progettoEsame.strutturaGioco.GrigliaSudoku;
import progettoEsame.strutturaGioco.LogicaGioco;
//
import java.io.*;
import java.util.*;

//
public class GestioneFileText {
	
	/*
	   Il metodo salvaClassifica(classifica) riceve in ingresso la classifica che verrà 
	   salvata riga per riga all'interno di un file di testo. 
	   In ogni riga c'è nome, livello e tempo di esecuzione separati da un punto e virgola.
	*/
	public static void salvaClassifica(Classifica classifica) {
		try {
			List<Dati> listaDati = classifica.getListaDati();
			BufferedWriter scrivi = new BufferedWriter(new FileWriter("classifica.txt"));
			for (Dati dati : listaDati) {
				scrivi.write(dati.getNome() + ";" + dati.getLivello() + ";" + dati.getTempo());
				scrivi.newLine();
			}
			scrivi.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	   Il metodo caricaClassifica(classifica) riceve in ingresso la classifica e legge 
	   i dati salvati nel file di testo, aggiungendoli alla classifica una riga alla volta.
	*/
	public static void caricaClassifica(Classifica classifica){
		try {
			List<Dati> listaDati = classifica.getListaDati();
			BufferedReader leggi = new BufferedReader(new FileReader("classifica.txt"));
			String riga = leggi.readLine();
			while (riga != null) {
				String[] dati = riga.split(";");
				String nome = dati[0];
				String livello = dati[1];
				long tempo = Long.parseLong(dati[2]);
				
				Dati datiRipresi = new Dati(nome, livello, tempo);
				
				listaDati.add(datiRipresi);
				riga = leggi.readLine();
			}
			leggi.close();
		}
		catch (FileNotFoundException e) {
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	   Il metodo salvaPartita(gioco, dati) riceve in ingresso gioco e dati e ha il compito
	   di salvare in un file di testo sia la griglia che l'utente sta completando, 
	   sia la griglia con la soluzione completa. 
	   Inoltre verranno salvati anche nome utente, livello e tempo di esecuzione.
	   Per salvare la griglia dell'utente, ad ogni numero viene associato F o M per  
	   indicare se la cella sia fissa o modificabile.
	*/
	public static void salvaPartita(LogicaGioco gioco, Dati dati) {
		GrigliaSudoku grigliaPartita = gioco.getGrigliaPartita();
		GrigliaSudoku grigliaSoluzione = gioco.getGrigliaSoluzione();
		String nome = dati.getNome();
		String livello = dati.getLivello();
		long tempo = dati.getTempo();
		try {
			BufferedWriter scrivi = new BufferedWriter(new FileWriter("partita.txt"));
			scrivi.write(nome + ";");
			scrivi.write(livello + ";");
			scrivi.write(tempo + ";\n");
			scrivi.write("GRIGLIA PARTITA \n");
			for (int r=0; r<9; r++) {
				for (int c=0; c<9; c++) {
					if (grigliaPartita.getCella(r, c).getFissa()) {
						scrivi.write(grigliaPartita.getCella(r, c).getNumero() + ",F;");
					}
					else {
						scrivi.write(grigliaPartita.getCella(r, c).getNumero() + ",M;");
					}
				}
				scrivi.newLine();
			}
			scrivi.write("GRIGLIA SOLUZIONE \n");
			for (int r=0; r<9; r++) {
				for (int c=0; c<9; c++) {
					scrivi.write(grigliaSoluzione.getCella(r, c).getNumero() + ";");
				}
				scrivi.newLine();
			}
			scrivi.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	   Il metodo caricaPartita(LogicaGioco gioco) riceve in ingresso gioco e ha il compito
	   di ripristinare la partita interrotta, ricreando la griglia dell'utente e quella 
	   della soluzione.
	   Inoltre restituisce i dati relativi a nome, livello e tempo.
	   Il timer riprederà dal tempo salvato.
	*/
	public static Dati caricaPartita(LogicaGioco gioco) {
		Dati dati = new Dati(null, null, 0);
		try {
			BufferedReader leggi = new BufferedReader(new FileReader("partita.txt"));
			String[] riga = leggi.readLine().split(";");
			String nome = riga[0];
			String livello = riga[1];
			long tempo = Long.parseLong(riga[2]);
			dati.setNome(nome);
			dati.setLivello(livello);
			dati.setTempo(tempo);
			
			leggi.readLine();
			
			GrigliaSudoku grigliaPartita = new GrigliaSudoku();
			for (int r=0; r<9; r++) {
				String[] rigaSudoku = leggi.readLine().split(";");
				for (int c=0; c<9; c++) {
					String[] coppia = rigaSudoku[c].split(",");
					Cella cella = grigliaPartita.getCella(r, c);
					cella.setNumero(Integer.parseInt(coppia[0]));
					if (coppia[1].equals("F")) {
						cella.setFissa(true);
					}
					else if (coppia[1].equals("M")) {
						cella.setFissa(false);
					}
				}
			}
			gioco.setGrigliaPartita(grigliaPartita);
			
			leggi.readLine();
			
			GrigliaSudoku grigliaSoluzione = new GrigliaSudoku();
			for (int r=0; r<9; r++) {
				String[] rigaSudoku = leggi.readLine().split(";");
				for (int c=0; c<9; c++) {
					Cella cella = grigliaSoluzione.getCella(r, c);
					cella.setNumero(Integer.parseInt(rigaSudoku[c]));
				}
			}
			gioco.setGrigliaSoluzione(grigliaSoluzione);
			
			leggi.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return dati;
	}
	
}
