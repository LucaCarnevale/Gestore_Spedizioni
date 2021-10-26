package screens;

import thread.*;
import tabelle.TabellaSpedizioni;
import utenti.*;
import spedizioni.*;
import tabelle.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

/**
 * Classe che implementa un MyFrame e serve :
 * - all' utente, per visualizzare tutte le spedizioni e relativi dati presenti nel suo account.
 * - all' Admin, per visualizzare tutte le spedizioni di tutti gli utenti.
 * 
 * @author Luca Carnevale - 131694
 * @version 1.0
 */
public class FrameTabella extends MyFrame {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Lista di utenti.
	 */
	private ListaUtenti userList;
	
	/**
	 * Lista di spedizioni.
	 */
	private ListaSpedizioni listaspedizioni;
	
	/**
	 * Utente di cui si vogliono vedere le spedizioni.
	 */
	private Utente user;
	
	/**
	 * Variabile booleana che mi dice se sono admin o utente.
	 */
	private boolean admin;
	
	/**
	 * Variabile utilizzata dal Thread per modificare lo stato delle spedizioni.
	 */
	private boolean modifiche;
	
	private JLabel MessaggioUtente;
	private JLabel NomeUtente;
	private JLabel MessaggioAdmin;
	private JLabel MessaggioAdmin1;
	private JButton Logout;
	private JButton Indietro;
	private JButton rimuoviSped;
	private JButton modificaSped;
	private JButton Rimborso;
	
	private JPanel pannelloSup, pannelloCen, pannelloInf;
	private JPanel pannelloTabella;
	
	private JTable t;
	private TabellaSpedizioni tabella;
	
	/**
	 * Metodo costruttore utilizzato per creare e inizializzare una tabella
	 * con i dati di cui ho bisogno; Essi cambiano se si accede come 
	 * Admin o come utente.
	 */
	public FrameTabella() {
		
		setTitle("Tabella Spedizioni Utenti");
		
		pannelloSup = new JPanel();
		pannelloCen = new JPanel();
		pannelloInf = new JPanel();
		pannelloTabella = new JPanel();
		pannelloTabella.setLayout(new BorderLayout());
		
		NomeUtente = new JLabel(" ");
		MessaggioUtente = new JLabel("Se una spedizione assicurata è fallita, puoi richiedere il rimborso tramite il pulsante presente.");
		MessaggioAdmin = new JLabel("Modifica - Per modificare le spedizioni presenti." + "\n");
		MessaggioAdmin1 = new JLabel("Rimuovi - Per cancellare una spedizione consegnata o fallita.");
		
		Indietro = new JButton("Indietro");
		Indietro.addActionListener(this);
		Rimborso = new JButton("Rimborso");
		Rimborso.addActionListener(this);
		Logout = new JButton("Logout");
		Logout.addActionListener(this);
		rimuoviSped = new JButton("Rimuovi");
		rimuoviSped.addActionListener(this);
		modificaSped = new JButton("Modifica");
		modificaSped.addActionListener(this);
		
		setModifiche(false);
		
		pannelloTabella.add(pannelloSup, BorderLayout.NORTH);
		pannelloTabella.add(pannelloCen, BorderLayout.CENTER);
		pannelloTabella.add(pannelloInf, BorderLayout.SOUTH);
	}
	
	/**
	 * Metodo costruttore di un frame tabella per un utente.
	 * @param u Utente.
	 * @param l Lista di utenti di cui fa parte l' utente.
	 */
	public FrameTabella(Utente u, ListaUtenti l) {
		this();
		user = u;
		userList = l;
		setAdmin(false);
		
		pannelloSup.add(NomeUtente);
		pannelloSup.add(MessaggioUtente);
		
		tabella = new TabellaSpedizioni(user.getSpedizioni());
		t = new JTable(tabella);
		t.setDefaultRenderer(Object.class, new ModelCelle());
		t.setPreferredScrollableViewportSize(t.getPreferredSize());
		JScrollPane scrollpane = new JScrollPane(t);
		pannelloCen.add(scrollpane);
		
		pannelloInf.add(Indietro);
		pannelloInf.add(Rimborso);
		
		this.add(pannelloTabella);
	}
	
	/**
	 * Metodo costruttore del frame tabella per un Admin.
	 * @param l Lista di utenti.
	 */
	public FrameTabella(ListaUtenti l) {
		this();
		userList = l;
		setAdmin(true);
		
		pannelloSup.add(MessaggioAdmin);
		pannelloSup.add(MessaggioAdmin1);
		
		/**
		 * Creo una nuova lista con all' interno tutti gli utenti registrati.
		 */
		listaspedizioni = new ListaSpedizioni();
		for(int i = 0; i < l.getNumUtenti(); i++) {
			Utente u = l.get(i);
			ListaSpedizioni tmp = u.getSpedizioni();
			for(int j = 0; j < tmp.getNumSpedizioni(); j++)
				listaspedizioni.Aggiungi(tmp.getSpedizione(j));
		}
		tabella = new TabellaSpedizioni(listaspedizioni);
		t = new JTable(tabella);
		t.setDefaultRenderer(Object.class, new ModelCelle());
		t.setPreferredScrollableViewportSize(t.getPreferredSize());
		JScrollPane scrollPane = new JScrollPane(t);
		pannelloCen.add(scrollPane);
		
		pannelloInf.add(Logout);
		pannelloInf.add(rimuoviSped);
		pannelloInf.add(modificaSped);
		
		this.add(pannelloTabella);
	}
	
	/**
	 * Metodo utilizzato per vedere se si è Admin o meno.
	 * @return true se sono admin, false altrimenti.
	 */
	public boolean isAdmin() { return admin; }
	
	/**
	 * Metodo usato nel costruttore per vedere se sono admin o utente.
	 * @param admin true se sono admin, false altrimenti.
	 */
	public void setAdmin(boolean admin) { this.admin = admin; }
	
	/**
	 * Metodo usato per creare Thread, il quale modifica le spedizioni.
	 * @return true se viene creato, false altrimenti.
	 */
	public boolean isModifiche() { return modifiche; }
	
	/**
	 * Metodo che prende in ingresso la variabile precedente impostandola in
	 * modo tale da creare o meno il thread di modifica spedizione.
	 * @param modifiche crea modifiche oppure interrompi modifiche.
	 */
	public void setModifiche(boolean modifiche) { this.modifiche = modifiche; }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String Scelta = e.getActionCommand();
		
		if(Scelta.equals("Logout")) {
			if(isAdmin()) {
				userList.SalvaLista();
				cambioFrame(new FrameBenvenuto(), this);
			}
			else {
				userList.SalvaLista();
				cambioFrame(new FrameUserLogged(userList, user), this);
			}
		}
		
		if(Scelta.equals("Indietro")) {
			if(!isAdmin()) {
				userList.SalvaLista();
				cambioFrame(new FrameUserLogged(userList, user), this);
			}
		}
		
		/**
		 * Qualora una spedizione assicurata risulti fallita, tramite questo 
		 * pulsante può essere richiesto un rimborso.
		 */
		if(Scelta.equals("Rimborso")) {
			ListaSpedizioni sped = user.getSpedizioni();
			int numrimborsi = 0;
			
			for(int i = 0; i < t.getRowCount(); i++) {
				SpedizioneNormale s = sped.getSpedizione(i);
				if(s.Rimborso()) {
					t.setValueAt("RICHIESTA RIMBORSO", i, 5);
					numrimborsi++;
				}
			}
			
			if(numrimborsi == 0)
				JOptionPane.showMessageDialog(this, "Nessun rimborso richiesto.", "ATTENZIONE !", JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(this, "Richiesti " + numrimborsi + " rimborsi.", "Rimborso", JOptionPane.INFORMATION_MESSAGE);
		}

		/**
		 * Qualora una spedizione risulti in stato finale, quindi consegnata
		 * o rimborsata, può essere rimossa dalla lista attraverso il pulsante di seguito.
		 */
		if(Scelta.equals("Rimuovi")) {
			ListaSpedizioni sped = tabella.getLista();
			SpedizioneNormale s = sped.getSpedizione(t.getSelectedRow());
			
			if(s.StatoFinale()) {
				String nome = s.getNomeUtente();
				Utente u = userList.TrovaUser(nome);
				ListaSpedizioni ListaSpedUtente = u.getSpedizioni();
				
				ListaSpedUtente.Cancella(s);
				u.dec();
				sped.Cancella(sped.getSpedizione(t.getSelectedRow()));
				tabella.fireTableDataChanged();
				JOptionPane.showMessageDialog(this, "Spedizione eliminata.", "Operazione conclusa !", JOptionPane.INFORMATION_MESSAGE);
			}
			else
				JOptionPane.showMessageDialog(this, "Spedizione non in stato finale.", "ATTENZIONE !", JOptionPane.ERROR_MESSAGE);
		}
		
		/**
		 * Essendo presente un thread, attraverso questo pulsante posso avviarlo
		 * per cambiare lo stato delle spedizioni, finché non viene premuto di nuovo
		 * il pulsante modifica.
		 */
			if(Scelta.equals("Modifica")) {
				
				setModifiche(!isModifiche());
				Runnable r = new Runnable() {
					
					@Override
					public void run() {
						Thread t = new Thread();
						
						while(isModifiche()) {
						t = new ThreadSpedizioni(tabella);
						t.start();
						
						try {
							Thread.sleep(4000);
						}
						catch (InterruptedException e) {
							e.printStackTrace();
						}
						t.interrupt();
						
					}
					}
				};
				new Thread(r).start();
			
			}
		}
			@Override
			public void windowClosing(WindowEvent e) {
				userList.SalvaLista();
				System.exit(0);
			}
}
