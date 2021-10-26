package screens;

import utenti.*;


import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Classe che implementa un frame per le operazioni, quali login e
 * registrazione, dedicate all' utente.
 * 
 * @author Luca Carnevale - 131694
 * @version 1.0
 */
public class FrameUtente extends MyFrame {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Lista di utenti.
	 */
	private ListaUtenti l;
	private JButton UtRegis, UtLogin, Indietro;
	private JLabel Presentazione, Testo;
	private JPanel pannelloPrincipale, pannelloSup, pannelloCen, pannelloInf;
	
	/**
	 * Metodo del costruttore del frame per il login o la registrazione dell' utente.
	 */
	public FrameUtente() {
		super();
		
		setTitle("Area Utente - Autenticazione");
		
		l = new ListaUtenti();
		l.CaricaLista();
		
		pannelloPrincipale = new JPanel();
		pannelloSup = new JPanel();
		pannelloCen = new JPanel();
		pannelloInf = new JPanel();
		
		
		pannelloPrincipale.setLayout(new BorderLayout());
		Presentazione = new JLabel("Benvenuto nell' area utente");
		Testo = new JLabel("Come vuoi accedere ?");
		
		UtLogin = new JButton("Login");
		UtLogin.addActionListener(this);
		UtRegis = new JButton("Registrati");
		UtRegis.addActionListener(this);
		Indietro = new JButton("Indietro");
		Indietro.addActionListener(this);
		
		pannelloSup.add(Presentazione);
		pannelloCen.add(Testo);
		pannelloInf.add(UtLogin);
		pannelloInf.add(UtRegis);
		pannelloInf.add(Indietro);
		
		pannelloPrincipale.add(pannelloSup, BorderLayout.NORTH);
		pannelloPrincipale.add(pannelloCen, BorderLayout.CENTER);
		pannelloPrincipale.add(pannelloInf, BorderLayout.SOUTH);
		
		this.add(pannelloPrincipale);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		String Scelta = e.getActionCommand();
		
		if(Scelta.equals("Login"))
			cambioFrame(new FrameLogin(l), this);
		
		if(Scelta.equals("Registrati"))
			cambioFrame(new FrameRegistrazione(l), this);
		
		if(Scelta.equals("Indietro"))
			cambioFrame(new FrameBenvenuto(), this);
		
	}

}
