package screens;

import utenti.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Classe che implementa un MyFrame e dalla quale l' utente potrà
 * scegliere le varie operazioni da eseguire.
 * 
 * @author Luca Carnevale - 131694
 * @version 1.0
 */
public class FrameUserLogged extends MyFrame {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Lista di utenti.
	 */
	private ListaUtenti l;
	
	/**
	 * Utente al quale il Frame farà riferimento.
	 */
	private Utente u;
	
	private JLabel NomeUtente, Testo;
	private JButton Logout, Visualizza, NuovaSpedizione, NuovaSpedizioneAssicurata;
	private JPanel pannelloUtente, pannelloSup, pannelloCen, pannelloInf;
	
	/**
	 * Meotodo del costruttore utilizzato per scegliere le operazioni da eseguire.
	 * @param lista Lista di utenti.
	 * @param user Utente loggato.
	 */
	public FrameUserLogged(ListaUtenti lista, Utente user) {
		
		super();
		
		setTitle("Area Riservata - Benvenuto");
	
		l = lista;
		u = user;

        pannelloUtente = new JPanel();
        pannelloSup = new JPanel();
        pannelloCen = new JPanel();
        pannelloInf = new JPanel();

        pannelloUtente.setLayout(new BorderLayout());

        Testo = new JLabel("Inserisci nuove spedizioni o visualizza spedizioni in corso");

        NomeUtente = new JLabel(u.getNome_utente());
        NuovaSpedizione = new JButton("Nuova Spedizione");
        NuovaSpedizione.addActionListener(this);
        
        NuovaSpedizioneAssicurata = new JButton("Nuova Spedizione Assicurata");
        NuovaSpedizioneAssicurata.addActionListener(this);
        Logout = new JButton("Logout");
        Logout.addActionListener(this);
        Visualizza = new JButton("Visualizza spedizioni");
        Visualizza.addActionListener(this);

        pannelloSup.add(NomeUtente);
        pannelloSup.add(Testo);

        pannelloCen.add(NuovaSpedizione);
        pannelloCen.add(NuovaSpedizioneAssicurata);
        pannelloInf.add(Visualizza);
        pannelloInf.add(Logout);

        pannelloUtente.add(pannelloSup, BorderLayout.NORTH);
        pannelloUtente.add(pannelloCen, BorderLayout.CENTER);
        pannelloUtente.add(pannelloInf, BorderLayout.SOUTH);

        this.add(pannelloUtente);
    }


    public void actionPerformed(ActionEvent e) {
        String Scelta = e.getActionCommand();

        if (Scelta.equals("Nuova Spedizione")){
            cambioFrame(new FrameAggiungiSpedizione(1, l, u), this);
        }

        if (Scelta.equals("Nuova Spedizione Assicurata")){
            cambioFrame(new FrameAggiungiSpedizione(2, l, u), this);
        }

       if (Scelta.equals("Visualizza spedizioni")){
            cambioFrame(new FrameTabella(u, l), this);
        }

        if (Scelta.equals("Logout")){
            l.SalvaLista();
            cambioFrame(new FrameBenvenuto(), this);
        }
    }
}


