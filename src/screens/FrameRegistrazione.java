package screens;

import javax.swing.*;

/**
 * Classe che implementa un MyFrame per la registrazione di un nuovo utente.
 * 
 * @author Luca Carnevale - 131694
 * @version 1.0
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import utenti.*;
public class FrameRegistrazione extends MyFrame {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * lista di utenti l.
	 */
	private ListaUtenti l;
	
	private JLabel Messaggio, UsernameTxt, PasswordTxt, ConfermaTxt, IndirizzoTxt;
	private JTextField Username, Indirizzo;
	private JPasswordField Password, Conferma;
	private JButton Accedi, Indietro;
	private JPanel pannelloRegistrazione, pannelloSup, pannelloCen, pannelloInf;
	
	/**
	 * Username dell' admin che verrà utilizzato all' atto di registrazione
	 * di un utente per verificare che non sia uguale a quello dell' amministratore.
	 */
	private static final String UserAdmin = "Admin";

	/**
	 * Metodo costruttore del frame di registrazione.
	 * @param l Lista di utenti.
	 */
	public FrameRegistrazione(ListaUtenti l) {
		
		super();
		this.l = l;
		setTitle("Area Personale - Registrati");
		pannelloRegistrazione = new JPanel();
		pannelloSup = new JPanel();
		pannelloCen = new JPanel();
		pannelloInf = new JPanel();
		
		pannelloRegistrazione.setLayout(new BorderLayout());
		Messaggio = new JLabel("Inserire i campi di seguito : ");
		IndirizzoTxt = new JLabel("Indirizzo");
		UsernameTxt = new JLabel("Username");
		PasswordTxt = new JLabel("Password");
		ConfermaTxt = new JLabel("Reinserire password");
		
		Username = new JTextField("", 20);
		Indirizzo = new JTextField("", 30);
		Password = new JPasswordField("", 20);
		Conferma = new JPasswordField("", 20);
		
		Indietro = new JButton("Indietro");
		Indietro.addActionListener(this);
		Accedi = new JButton("Registrati");
		Accedi.addActionListener(this);
		
		pannelloSup.add(Messaggio);
		
		pannelloCen.setLayout(new GridLayout(5, 2));
		
		pannelloCen.add(UsernameTxt);
		pannelloCen.add(Username);
		pannelloCen.add(PasswordTxt);
		pannelloCen.add(Password);
		pannelloCen.add(ConfermaTxt);
		pannelloCen.add(Conferma);
		pannelloCen.add(IndirizzoTxt);
		pannelloCen.add(Indirizzo);
		
		pannelloInf.add(Indietro);
		pannelloInf.add(Accedi);
		
		pannelloRegistrazione.add(pannelloSup, BorderLayout.NORTH);
		pannelloRegistrazione.add(pannelloCen, BorderLayout.CENTER);
		pannelloRegistrazione.add(pannelloInf, BorderLayout.SOUTH);
		
		this.add(pannelloRegistrazione);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		String scelta = e.getActionCommand();
		
		if(scelta.equals("Indietro"))
			cambioFrame(new FrameBenvenuto(), this);
		
		if(scelta.equals("Registrati")) {
			String user = "", password = "", conferma = "", indirizzo = "";
			char[] c;
			char[] p;
			user = Username.getText();
			indirizzo = Indirizzo.getText();
			p = Password.getPassword();
			c = Conferma.getPassword();
			
			for(int i = 0; i < p.length; i++) 
				password = password + p[i];
			for(int i = 0; i < c.length; i++)
				conferma = conferma + c[i];
			
			/**
			 * Controllo che tutti i campi siano inseriti.
			 */
			if(user.equals("") || password.equals("") || conferma.equals("") || indirizzo.equals("")) {
				cambioFrame(new FrameRegistrazione(l), this);
				JOptionPane.showMessageDialog(this, "Compilare tutti i campi.", "ATTENZIONE !", JOptionPane.ERROR_MESSAGE);
			}
			
			/**
			 * Controllo che l' username inserito dall' utente non sia
			 * uguale a quello dell' Admin.
			 */
			else {
				if(user.equals(UserAdmin)) {
					cambioFrame(new FrameRegistrazione(l), this);
					JOptionPane.showMessageDialog(this, "Username Admin non utilizzabile.", "ATTENZIONE !", JOptionPane.ERROR_MESSAGE);
				}
				
				/**
				 * Controllo che le password coincidano.
				 */
				else {
					if(!password.equals(conferma)) {
						cambioFrame(new FrameRegistrazione(l), this);
						JOptionPane.showMessageDialog(this, "Le password inserite non corrispondono.", "ATTENZIONE !", JOptionPane.ERROR_MESSAGE);
					}
					
					/**
					 * Controllo che il nome utente inserito non sia già utilizzato.
					 */
					else {
						Utente newUser = new Utente(user, password, indirizzo);
						if(l.isPresent(newUser)) {
							cambioFrame(new FrameRegistrazione(l), this);
							JOptionPane.showMessageDialog(this, "Username già in uso.", "ATTENZIONE !", JOptionPane.ERROR_MESSAGE);
						}
						
						/**
						 * Se tutti i controlli sono superati, aggiungo un nuovo utente alla lista.
						 */
						else {
							l.Aggiungi(newUser);
							l.AggiungiNuovoUtente(newUser);
							cambioFrame(new FrameUserLogged(l, newUser), this);
						}
					}
				}
			}
		}
		
	}
}
