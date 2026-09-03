package progettoEsame.gui;
//
import progettoEsame.strutturaGioco.Cella;
import progettoEsame.strutturaGioco.Classifica;
import progettoEsame.strutturaGioco.LogicaGioco;
import progettoEsame.utility.Dati;
import progettoEsame.utility.GestioneFileText;
//
import java.awt.*;
import javax.swing.*;


//
public class SchermataGioco {
	
	private LogicaGioco gioco;
	private Classifica classificazione;
	private Dati dati;
	private Timer timer;
	private JLabel etichettaTimer;
	private long tempoTrascorso;
	private JButton[][] bottoni;
	private JButton[][] tasti;
	private int rigaSelezionata = 10;
	private int colonnaSelezionata = 10;
	private JFrame frame;

	/* 
	   Il metodo costruttore riceve la logica del gioco, la classifica ed i dati raccolti nella partita corrente, 
	   crea la finestra di gioco e richiama il metodo elementiPagina() per costruire l'interfaccia grafica.
	*/
	public SchermataGioco(LogicaGioco gioco, Classifica classificazione, Dati dati) {
		this.gioco = gioco;
		this.classificazione = classificazione;
		this.dati = dati;
		frame = new JFrame();
		frame.setTitle("Giochiamo");
		frame.setSize(1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		elementiPagina();
		avviaTimer();
	}
	
	/* 
	   Il metodo elementiPagina() ha il compito di creare l'interfaccia grafica della pagina di gioco. 
	   In questo metodo vengono creati diversi pannelli che suddividono la pagina in tre zone: 
	   - area titolo (intestazione);
	   - area gioco (griglia del sudoku, tastierino numerico, timer); 
	   - area bottoni (tasti regole, classifica ed esci). 
	   Per la creazione della griglia del sudoku e del tastierino numerico
	   vengono richiamati rispettivamente i metodi creaGriglia() e creaTastiera(), 
	   mentre il timer viene avvito richiamando il metodo avviaTimer() all'interno del costruttore.
	*/
	private void elementiPagina() {
		JPanel pannello = new JPanel();
		pannello.setLayout(new BorderLayout(20,20));
		
		// AREA TITOLO
		JPanel areaTitolo = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		// --> INTESTAZIONE
		JLabel intestazione = new JLabel("GIOCHIAMO!"); 
		intestazione.setFont(new Font("Arial", Font.BOLD, 28));
		intestazione.setForeground(Color.BLUE);
		
		// areaTitolo
		areaTitolo.add(intestazione);
		
		// AREA GIOCO 
		JPanel areaGioco = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 20));
		
		// --> GRIGLIA
		JPanel griglia = creaGriglia();
		
		// --> TASTIERA
		JPanel tastiera = creaTastiera();
		
		// --> TIMER 
		JPanel rigaTimer = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 30));
		etichettaTimer = new JLabel();
		etichettaTimer.setFont(new Font("Arial", Font.BOLD, 18));
		rigaTimer.add(etichettaTimer);
		
		// areaDestra
		JPanel areaDestra = new JPanel();
		areaDestra.setLayout(new BoxLayout(areaDestra, BoxLayout.Y_AXIS));
		areaDestra.add(rigaTimer);
		areaDestra.add(tastiera);
		
		// areaGioco 
		areaGioco.add(griglia);
		areaGioco.add(areaDestra);
		
		// AREA BOTTONI 
		JPanel areaBottoni = new JPanel(new FlowLayout(FlowLayout.CENTER, 150, 30));
		
		// --> REGOLE
		JButton regole = new JButton("Regole");
		regole.addActionListener(e -> {
			PopUp.mostraRegole(frame);
		});
		regole.setPreferredSize(new Dimension(150, 40));
		regole.setFont(new Font("Arial", Font.BOLD, 16));
		regole.setBackground(Color.WHITE);
		regole.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
		
		// --> CLASSIFICA 
		JButton classifica = new JButton("Classifica");
		classifica.addActionListener(e -> {
			PopUp.mostraClassifica(frame, classificazione.creaClassifica());
		});
		classifica.setPreferredSize(new Dimension(150, 40));
		classifica.setFont(new Font("Arial", Font.BOLD, 16));
		classifica.setBackground(Color.WHITE);
		classifica.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
		
		// --> ESCI
		JButton esci = new JButton("Esci");
		esci.addActionListener(e -> {
			if (PopUp.esci(frame)) {
				if (PopUp.salva(frame)) {
					dati.setTempo(tempoTrascorso);
					GestioneFileText.salvaPartita(gioco, dati);
				}
				frame.dispose();
				new SchermataAvvio(gioco, classificazione);
			}
		});
		esci.setPreferredSize(new Dimension(150, 40));
		esci.setFont(new Font("Arial", Font.BOLD, 16));
		esci.setBackground(Color.WHITE);
		esci.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
		
		// areaBottoni 
		areaBottoni.add(regole);
		areaBottoni.add(classifica);
		areaBottoni.add(esci);
		
		// PANNELLO 
		pannello.add(areaTitolo, BorderLayout.NORTH);
		pannello.add(areaGioco, BorderLayout.CENTER);
		pannello.add(areaBottoni, BorderLayout.SOUTH);

		pannello.setBorder(BorderFactory.createEmptyBorder(10, 30, 20, 30));
		frame.setLocationRelativeTo(null);
		frame.add(pannello);
		frame.setVisible(true);
	}
	
	/*	
	   Il metodo creaTastiera() oltre ad avere il compito di creare il tastierino numerico, controlla: 
	   - la validità della posizione in cui viene inserito il numero tramite il 
	     metodo inserisciNumero(riga, colonna, numero); 
	   - se la partita è terminata, in tal caso ferma il timer, aggiorna la classifica 
	     e ci riporta alla schermata di avvio.
	*/
	private JPanel creaTastiera() {
		JPanel tastiera = new JPanel(new GridLayout(4,3,3,3));
		tasti = new JButton[4][3];
		int i = 1;
		for (int r=0; r<4; r++) {
			for (int c=0; c<3; c++) {
				JButton bottone = new JButton(); 
				bottone.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
				bottone.setFont(new Font("Arial", Font.BOLD, 14));
				bottone.setBackground(Color.WHITE);
				tasti[r][c] = bottone;
				if (i<10) {
					int numero = i;
					bottone.setText(String.valueOf(i));
					bottone.addActionListener(e -> {
						if ((rigaSelezionata != 10) && (colonnaSelezionata != 10)) {
							boolean inserito = gioco.inserisciNumero(rigaSelezionata, colonnaSelezionata, numero);
							if (inserito) {
								bottoni[rigaSelezionata][colonnaSelezionata].setText(String.valueOf(numero));
							}
							else {
								PopUp.inserimentoErrato(frame);
							}
						}
						if (gioco.terminePartita()) {
							timer.stop();
							dati.setTempo(tempoTrascorso);
							classificazione.aggiungiDati(dati);
							GestioneFileText.salvaClassifica(classificazione);
							PopUp.vittoria(frame);
							frame.dispose();
							new SchermataAvvio(gioco, classificazione);
						}
					});
				}
				else if (i == 11) {
					bottone.setText(String.valueOf("X"));
					bottone.addActionListener(e -> {
						if ((rigaSelezionata != 10) && (colonnaSelezionata != 10)) {
							boolean rimosso = gioco.rimuoviNumero(rigaSelezionata, colonnaSelezionata);
							if (rimosso) {
								bottoni[rigaSelezionata][colonnaSelezionata].setText("");
							}
						}
					});
				}
				else {
					bottone.setEnabled(false);
					bottone.setBackground(null);
					bottone.setBorder(null);
				}
				i++;
				tastiera.add(bottone);
			}
		}
		tastiera.setPreferredSize(new Dimension(150,200));
		return tastiera;
	}
	
	/*
	   Il metodo creaGriglia() ha il compito di creare e riempire con i numeri iniziali 
	   la griglia del sudoku; vengono creati prima nove pannelli che vengono riempiti con 
	   altri nove pannelli che diventeranno le caselle per i singoli numeri.
	   Nel momento in cui una casella viene selezionata viene evidenziata per distinguerla dalle altre, 
	   non è possibile modificare le caselle con i numeri iniziali.
	*/
	private JPanel creaGriglia() {
		JPanel griglia = new JPanel(new GridLayout(3,3,1,1));
		griglia.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		bottoni = new JButton[9][9]; 
		for (int orizzontale=0; orizzontale<3; orizzontale++) {
			for (int verticale=0; verticale<3; verticale++) {
				JPanel blocco = new JPanel(new GridLayout(3,3));
				blocco.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
				for (int r=0; r<3; r++) {
					for (int c=0; c<3; c++) {
						int riga = orizzontale * 3 + r;
						int colonna = verticale * 3 + c;
						JButton bottone = new JButton();
						bottone.setFont(new Font("Arial", Font.BOLD, 14));
						bottone.setBackground(Color.WHITE);
						bottoni[riga][colonna] = bottone;
						Cella cella = gioco.getGrigliaPartita().getCella(riga, colonna);
						if (!cella.vuota()) {
							bottone.setText(String.valueOf(cella.getNumero()));
							if (cella.getFissa()) {
								bottone.setEnabled(false);
								bottone.setBackground(Color.ORANGE);
							}
						}
						bottone.addActionListener(e -> {
							rigaSelezionata = riga;
							colonnaSelezionata = colonna;
							
							for (int rg = 0; rg < 9; rg++) {
						        for (int cl = 0; cl < 9; cl++) {
						            bottoni[rg][cl].setBorder(UIManager.getBorder("Button.border"));
						        }
						    }
						    bottone.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
						});
						blocco.add(bottone);
					}
				}
				griglia.add(blocco);
			}
		}
		griglia.setPreferredSize(new Dimension(400,400));
		return griglia;
	}

	/*
	   Il metodo avviaTimer() tiene traccia del tempo impiegato per risolvere il sudoku e
	   aggiorna l'etichetta del timer ogni secondo.
	*/
	private void avviaTimer() {
		tempoTrascorso = dati.getTempo();
		setEtichettaTimer();
		timer = new Timer(1000, e -> {
			tempoTrascorso++;
		long minuti = tempoTrascorso / 60;
		long secondi = tempoTrascorso % 60;
		
		etichettaTimer.setText(String.format("Tempo: %02d:%02d", minuti, secondi));
		});
		timer.start();
		
	}
	
	/*
	   Il metodo setEtichettaTimer() segna il tempo iniziale, da cui parte il conteggio del tempo:
	   - quando si inizia una nuova partita il tempo è 00:00;
   	   - quando si riprende una partita viene visualizzato il tempo precedentemente salvato.
   	*/
	private void setEtichettaTimer() {
		long minuti = tempoTrascorso / 60;
	    long secondi = tempoTrascorso % 60;

	    etichettaTimer.setText(String.format("Tempo: %02d:%02d", minuti, secondi));
	}

}
