package screens;

import utenti.*;

import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionEvent;

/**
 * Classe che implementa un Frame, dal quale si potrà scegliere se accedere
 * come admin o cliente.
 * 
 * @author Luca Carnevale - 131694
 * @version 1.0 
 *
 */

public class FrameBenvenuto extends MyFrame {
	
	private static final long serialVersionUID = 100L;
	
	/**
	 * Lista di utenti.
	 */	
	private ListaUtenti l;
	private JButton Admin, UtLogin;
	private JLabel Testo, benvenuto;
	private JPanel pannelloPrincipale, pannelloSup, pannelloCen, pannelloInf;
	
	/**
	 * Metodo costruttore del FrameBenvenuto, dove creo o leggo 
	 * la lista di utenti che viene da file.
	 */
	
	public FrameBenvenuto() {
		
		super();
		l = new ListaUtenti();
		l.CaricaLista();
		pannelloPrincipale = new JPanel();
		pannelloSup = new JPanel();
		pannelloCen = new JPanel();
		pannelloInf = new JPanel();
		setTitle("Gestore Spedizioni");
		
		pannelloPrincipale.setLayout(new BorderLayout());
		benvenuto = new JLabel("Benvenuto nel sistema di Gestione Spedizioni");
		Testo = new JLabel("Come vuoi autenticarti ?");
		
		Admin = new JButton("Admin");
		Admin.addActionListener(this);
		UtLogin = new JButton("Utente");
		UtLogin.addActionListener(this);
		
		pannelloSup.add(benvenuto);
		pannelloCen.add(Testo);
		pannelloInf.add(Admin);
		pannelloInf.add(UtLogin);
		
		
		pannelloPrincipale.add(pannelloSup, BorderLayout.NORTH);
	    pannelloPrincipale.add(pannelloCen, BorderLayout.CENTER);
	    pannelloPrincipale.add(pannelloInf, BorderLayout.SOUTH);
	    this.add(pannelloPrincipale);
	}
	
	public void actionPerformed(ActionEvent e) {
	    String Scelta = e.getActionCommand();

	   if (Scelta.equals("Utente")){
	       cambioFrame(new FrameUtente(), this);
	   }

	    if (Scelta.equals("Admin")){
	        cambioFrame(new FrameLogin(l, true), this);
	    }
	}
}

