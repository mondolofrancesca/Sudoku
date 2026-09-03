package progettoEsame.gui;
//
import progettoEsame.strutturaGioco.Classifica;
import progettoEsame.strutturaGioco.LogicaGioco;
import progettoEsame.utility.Dati;
import progettoEsame.utility.GestioneFileText;
//
import java.awt.*;
import javax.swing.*;

//
public class SchermataAvvio {
	
	private LogicaGioco gioco;
	private Classifica classificazione;
	private JFrame frame;
	
	public SchermataAvvio(LogicaGioco gioco, Classifica classificazione) {
		this.gioco = gioco;
		this.classificazione = classificazione;
		frame = new JFrame();
		frame.setTitle("Avvia Sudoku");
		frame.setSize(1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		elementiPagina();
	}
	
	
	private void elementiPagina() {
		JPanel pannello = new JPanel();
		pannello.setLayout(new BorderLayout(20,20));
		
		// AREA TITOLO
		JPanel areaTitolo = new JPanel(new FlowLayout(FlowLayout.CENTER));
				
		// --> INTESTAZIONE
		JLabel intestazione = new JLabel("SUDOKU!"); 
		intestazione.setFont(new Font("Arial", Font.BOLD, 36));
		intestazione.setForeground(Color.ORANGE);
		
		// areaTitolo
		areaTitolo.add(intestazione);
		
		// AREA SCELTA 
		JPanel areaScelta = new JPanel();
		areaScelta.setLayout(new BoxLayout(areaScelta, BoxLayout.Y_AXIS));
		
		// --> INSERIMENTO NOME
		JPanel rigaNome = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));
		
		JLabel inserimentoNome = new JLabel("Inserisci il tuo nome: ");
		inserimentoNome.setFont(new Font("Arial", Font.BOLD, 18));
		
		JTextField campoInserimentoNome = new JTextField();
		campoInserimentoNome.setPreferredSize(new Dimension(300, 50));
		campoInserimentoNome.setFont(new Font("Arial", Font.BOLD, 16));
		campoInserimentoNome.setBackground(Color.WHITE);
		campoInserimentoNome.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
		
		rigaNome.add(inserimentoNome);
		rigaNome.add(campoInserimentoNome);
		
		// --> MENU
		JPanel rigaMenu = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));
		String [] livelli = {"Facile", "Medio", "Difficile"};
		JComboBox<String> livello = new JComboBox<>(livelli);
		livello.setEnabled(false);
		livello.setPreferredSize(new Dimension(300, 50));
		livello.setFont(new Font("Arial", Font.BOLD, 16));
		livello.setBackground(Color.WHITE);
		livello.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
		rigaMenu.add(livello);
		
		// --> RADIOBUTTON 
		JPanel rigaRadiobutton = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));
		ButtonGroup gruppoRadioButton = new ButtonGroup();
		
		// 
		JRadioButton opzioneNuova = new JRadioButton("Nuova partita");
		gruppoRadioButton.add(opzioneNuova);
		opzioneNuova.addActionListener(e -> {
			livello.setEnabled(true);
			campoInserimentoNome.setEnabled(true); });
		opzioneNuova.setFont(new Font("Arial", Font.BOLD, 18));
		
		//
		JRadioButton opzioneRiprendi = new JRadioButton("Riprendi partita");
		gruppoRadioButton.add(opzioneRiprendi);
		opzioneRiprendi.addActionListener(e -> {
			livello.setEnabled(false);
			campoInserimentoNome.setEnabled(false); });
		opzioneRiprendi.setFont(new Font("Arial", Font.BOLD, 18));
		
		//
		rigaRadiobutton.add(opzioneNuova);
		rigaRadiobutton.add(opzioneRiprendi);
		
		// --> AVVIA 
		JPanel rigaAvvio = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));
		JButton avvia = new JButton("Avvia gioco!");
		avvia.addActionListener(e -> {
			String nome = campoInserimentoNome.getText().trim();
			if (opzioneNuova.isSelected()) {
				if (nome.isEmpty()) {
				PopUp.nomeMancante(frame);
				}
				else {
					String livelloScelto = (String) livello.getSelectedItem();
					gioco.nuovaPartita(livelloScelto);
					Dati dati = new Dati(nome, livelloScelto, 0);
					new SchermataGioco(gioco, classificazione, dati);
					frame.dispose();
				}
			}
			else if (opzioneRiprendi.isSelected()) {
				Dati dati = GestioneFileText.caricaPartita(gioco);
				new SchermataGioco(gioco, classificazione, dati);
				frame.dispose();
			}
			else {
				PopUp.nessunaSelezione(frame);
			}
		});
		avvia.setPreferredSize(new Dimension(150, 40));
		avvia.setFont(new Font("Arial", Font.BOLD, 16));
		avvia.setBackground(Color.WHITE);
		avvia.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
		rigaAvvio.add(avvia);
		
		// areaScelta
		areaScelta.add(rigaNome);
		areaScelta.add(rigaRadiobutton);
		areaScelta.add(rigaMenu);
		areaScelta.add(rigaAvvio);
		
		// AREA BOTTONI 
		JPanel areaBottoni = new JPanel(new FlowLayout(FlowLayout.CENTER, 200, 30));
		
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
		
		// areaBottoni 
		areaBottoni.add(regole);
		areaBottoni.add(classifica);
		
		// PANNELLO
		pannello.add(areaTitolo, BorderLayout.NORTH);
		pannello.add(areaScelta, BorderLayout.CENTER);
		pannello.add(areaBottoni, BorderLayout.SOUTH);
		
		pannello.setBorder(BorderFactory.createEmptyBorder(10, 30, 20, 30));
		frame.add(pannello);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
