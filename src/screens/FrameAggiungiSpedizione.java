package screens;

import utenti.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

/**
 * Classe che implementa un MyFrame; da qui l' utente potrà
 * aggiungere le sue spedizioni normali o assicurate.
 * 
 * @author Luca Carnevale - 131694
 * @version 1.0
 */
public class FrameAggiungiSpedizione extends MyFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Lista di utenti l
	 */
	private ListaUtenti l;
	
	/**
	 * Utente u che può aggiungere spedizioni.
	 */
	private Utente u;
	
	/**
	 * Indica il tipo di spedizione che sto aggiungendo.
	 */
	private int TipoSpedizione;
	
	private JLabel PesoVal, IndirizzoTxt, ValAssicur, Messaggio;
	private JTextField Peso, Indirizzo, ValoreAssicurazione;
	private JButton Inserisci, Indietro;
	private JPanel pannelloAggSpedizione, pannelloSup, pannelloCen, pannelloInf;
	
	/**
	 * Metodo costruttore del frame che permette di aggiungere una 
	 * nuova spedizione normale o assicurata.
	 * 
	 * @param val Indica il tipo di spedizione che si vuole effettuare
	 * @param l Lista di utenti
	 * @param u Utente che effettua spedizioni
	 */
	public FrameAggiungiSpedizione(int val, ListaUtenti l, Utente u) {
		
		super();
		this.l = l;
		this.u = u;
		TipoSpedizione = val;
		setTitle("Nuova Spedizione - Inserire i dati");
		
		Messaggio = new JLabel("Inserire i dati della spedizione : ");
		PesoVal = new JLabel("Peso");
		IndirizzoTxt = new JLabel("Indirizzo");
		ValAssicur = new JLabel("Valore Assicurazione");
		Peso = new JTextField("", 2);
		Indirizzo = new JTextField("", 50);
		ValoreAssicurazione = new JTextField("", 4);
		Inserisci = new JButton("Aggiungi spedizione");
		Indietro = new JButton("Indietro");
		
		ButtonGroup bg = new ButtonGroup();
        bg.add(Inserisci);
        bg.add(Indietro);

		if(TipoSpedizione == 1) 
			ValoreAssicurazione.setEditable(false);
		
		pannelloAggSpedizione = new JPanel();
		pannelloAggSpedizione.setLayout(new BorderLayout());
		
		pannelloSup = new JPanel();
		pannelloCen = new JPanel();
		pannelloCen.setLayout(new GridLayout(3, 2));
		pannelloInf = new JPanel();
		
		pannelloSup.add(Messaggio);
		pannelloCen.add(IndirizzoTxt);
		pannelloCen.add(Indirizzo);
		pannelloCen.add(PesoVal);
		pannelloCen.add(Peso);
		pannelloCen.add(ValAssicur);
		pannelloCen.add(ValoreAssicurazione);
		pannelloInf.add(Inserisci);
		pannelloInf.add(Indietro);
		
		pannelloAggSpedizione.add(pannelloSup, BorderLayout.NORTH);
		pannelloAggSpedizione.add(pannelloCen, BorderLayout.CENTER);
		pannelloAggSpedizione.add(pannelloInf, BorderLayout.SOUTH);
		
		this.add(pannelloAggSpedizione);
		Inserisci.addActionListener(this);
		Indietro.addActionListener(this);
		
		this.addWindowListener(this);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		
		String scelta = e.getActionCommand();
		
		/**
		 * Qui, prima di inserire una nuova spedizione si effettuano i vari 
		 * controlli, ovvero in base al tipo di spedizione controllo che 
		 * tutti i dati siano stati inseriti, che il peso di una spedizione sia 
		 * maggiore di 0; nel caso di una spedizione assicurata si controlla anche
		 * che il valore assicurazione sia maggiore di 0.
		 */
		if(scelta.equals("Aggiungi spedizione")) {
			String peso = Peso.getText();
			String indirizzo = Indirizzo.getText();
			String val = ValoreAssicurazione.getText();
			if(peso.equals("") || indirizzo.equals("")) {
				JOptionPane.showMessageDialog(this, "Inserire i campi mancanti. ", "ATTENZIONE !", JOptionPane.ERROR_MESSAGE);

			}
			
			if(TipoSpedizione == 2) {
				if(val.equals(""))
					JOptionPane.showMessageDialog(this, "Inserire i campi mancanti.", "ATTENZIONE !", JOptionPane.ERROR_MESSAGE);
			}
			
			/**
			 * Controllo che il peso della spedizione sia un intero.
			 */
			try {
				@SuppressWarnings("unused")
				int p = Integer.parseInt(peso);
			}
			
			catch(java.lang.NumberFormatException e1) {
				cambioFrame(new FrameAggiungiSpedizione(TipoSpedizione, l, u), this);
				JOptionPane.showMessageDialog(this, "Peso non valido.", "ATTENZIONE!", JOptionPane.ERROR_MESSAGE);
			}
			
			int P = Integer.parseInt(peso);
			
			if(P <= 0) {
				cambioFrame(new FrameAggiungiSpedizione(TipoSpedizione, l, u), this);
				JOptionPane.showMessageDialog(this, "Peso non valido.", "ATTENZIONE !", JOptionPane.ERROR_MESSAGE);
			}
			else {
				if(TipoSpedizione == 1) {
					u.CreaSpedizione(indirizzo, P);
					JOptionPane.showMessageDialog(this, "Spedizione creata correttamente.", "Inserimento completato", JOptionPane.INFORMATION_MESSAGE);
					cambioFrame(new FrameAggiungiSpedizione(TipoSpedizione, l, u), this);
				}
			}
			
				/**
				 * Controllo che il valore assicurato sia maggiore di 0.
				 */
				if(TipoSpedizione == 2) {
					try {
						@SuppressWarnings("unused")
						int v = Integer.parseInt(val);
					}
					catch(java.lang.NumberFormatException e2) {
						cambioFrame(new FrameAggiungiSpedizione(TipoSpedizione, l, u), this);
						JOptionPane.showMessageDialog(this, "Valore assicurazione non valido.", "ATTENZIONE !", JOptionPane.ERROR_MESSAGE);
					}
					
					int V = Integer.parseInt(val);
					if(V <= 0) {
						JOptionPane.showMessageDialog(this, "Valore Assicurazione non valido.", "ATTENZIONE !", JOptionPane.ERROR_MESSAGE);
						cambioFrame(new FrameAggiungiSpedizione(TipoSpedizione, l, u), this);
					}
					else {
						u.CreaSpedizione(indirizzo, P, V);
						JOptionPane.showMessageDialog(this, "Spedizione Assicurata inserita correttamente", "Inserimento completato", JOptionPane.INFORMATION_MESSAGE);
						cambioFrame(new FrameAggiungiSpedizione(TipoSpedizione, l, u), this);
					}
				}
			}
			if(scelta.equals("Indietro")) {
				l.SalvaLista();
				cambioFrame(new FrameUserLogged(l, u), this);
			}
		}
	
		public void windowsClosing(WindowEvent e) {
			l.SalvaLista();
			System.exit(0);
		}
}
