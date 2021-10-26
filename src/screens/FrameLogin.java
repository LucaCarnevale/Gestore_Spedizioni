package screens;

import utenti.*;
import utenti.Utente;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Classe che implementa un Frame per il login dell' utente.
 * Estende la classe MyFrame
 * 
 * @author Luca Carnevale - 131694
 * @version 1.0
 */
public class FrameLogin extends MyFrame {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Nome e password dell' amministratore, entrambe 
	 * definite staticamente all' interno del programma.
	 */
	private final static String IDAdmin = "Admin";
	private final static String AdminPassword = "Admin";
	
	/**
	 * Lista di utenti.
	 */
	private ListaUtenti l;
	
	/**
	 * Componenti grafiche.
	 */
	private JLabel Testo;
	private JLabel UserTxt, PassTxt;
	private JButton Accedi, Indietro;
	private JTextField Username;
	private JPasswordField Password;
	private JPanel pannelloLogin, pannelloSup, pannelloCen, pannelloInf;
	
	private boolean admin;
	
	/**
	 * Metodo che verifica se si sta eseguendo il login come admin o come cliente.
	 * @return true se si è Admin, false altrimenti.
	 */
	public boolean isAdmin() {
		return admin;
	}
	
	/**
	 * Metodo che imposta la variabile per controllare chi sta eseguendo il login.
	 * @param admin true se si è admin, false altrimenti.
	 */
	public void setAdmin(boolean admin) {
        this.admin = admin;
    }
	
	/**
	 * Metodo costruttore del frame per il login,
	 * @param lista lista utenti
	 */
	public FrameLogin(ListaUtenti lista) {
		super();
		
		setTitle("Area Personale - Accedi");
		
		l = lista;
		this.admin = false;
		pannelloLogin = new JPanel();
		pannelloSup = new JPanel();
		pannelloInf = new JPanel();
		pannelloCen = new JPanel();
		pannelloLogin.setLayout(new BorderLayout());
		Testo = new JLabel("Inserire i campi : ");
		UserTxt = new JLabel("Username : ");
		PassTxt = new JLabel("Password ");
		Username = new JTextField("", 20);
		Password = new JPasswordField("", 20);
		Indietro = new JButton("Indietro");
		Indietro.addActionListener(this);
		Accedi = new JButton("Accedi");
		Accedi.addActionListener(this);
		
		pannelloSup.add(Testo);
		pannelloCen.setLayout(new GridLayout(2, 2));
		pannelloCen.add(UserTxt);
		pannelloCen.add(Username);
		pannelloCen.add(PassTxt);
		pannelloCen.add(Password);

		pannelloInf.add(Indietro);
		pannelloInf.add(Accedi);

		pannelloLogin.add(pannelloSup, BorderLayout.NORTH);
		pannelloLogin.add(pannelloCen, BorderLayout.CENTER);
		pannelloLogin.add(pannelloInf, BorderLayout.SOUTH);
		this.add(pannelloLogin);
 
    }
	
	/**
	 * Metodo usato per il frame di login dedicato all' admin
	 * @param l lista utenti
	 * @param admin bool che indica che sono l' admin
	 */
	public FrameLogin(ListaUtenti l, boolean admin) {
		this(l);
		setAdmin(admin);
	}
    
	@Override
	public void actionPerformed(ActionEvent e) {
		String scelta = e.getActionCommand();
		
		if(scelta.equals("Indietro")) {
			cambioFrame(new FrameBenvenuto(), this);
		}
		
		if(scelta.equals("Accedi")) {
			String user = "", password = "";
			char[] p;
			user = Username.getText();
			p = Password.getPassword();
			for(int i = 0; i < p.length; i++) 
				password = password + p[i];
		
			/**
			 * Qui si effettua un controllo sul contenuto dei campi
			 * per accertarsi che non siano vuoti.
			 */
			if(user.equals("") || password.equals("")) {
				JOptionPane.showMessageDialog(this, "Inserire tutte le credenziali.", "ATTENZIONE !", JOptionPane.ERROR_MESSAGE);
				cambioFrame(new FrameLogin(l), this);
			}
			/**
			 * Se si è admin, controlla che le credenziali inserite siano valide e corrette.
			 */
			if(isAdmin()) {
				if(user.equals(IDAdmin) && password.equals(AdminPassword)) {
				cambioFrame(new FrameTabella(l), this);
			}
				else {
					JOptionPane.showMessageDialog(this, "Credenziali ADMIN non valide.", "ATTENZIONE !", JOptionPane.ERROR_MESSAGE);
				}
			}
		
		/**
		 * Altrimenti si controlla se il nome_utente inserito non sia equivalente 
		 * a quello dell' admin o di un altro utente registrato;
		 * In relazione ad un determinato nome_utente, si controlla che la
		 * password sia corretta.
		 */
		else {
			if(user.equals(IDAdmin)) {
				JOptionPane.showMessageDialog(this, "Username con privilegi.", "ATTENZIONE !", JOptionPane.ERROR_MESSAGE);
				cambioFrame(new FrameLogin(l), this);
			}
			else {
				Utente presente = new Utente();
				for(int i = 0; i < l.getNumUtenti(); i++) {
					Utente tmp = l.get(i);
					if(tmp.getNome_utente().equals(user))
						presente = l.get(i);
				}
				if(presente.getNome_utente().equals("")) {
					JOptionPane.showMessageDialog(this, "Utente inesistente.", "ATTENZIONE !", JOptionPane.ERROR_MESSAGE);
					cambioFrame(new FrameLogin(l), this);
				}
				else {
					if(presente.getPassword().equals(password)) {
						@SuppressWarnings("unused")
						Utente tmp;
						for (int i=0; i<l.getNumUtenti(); i++){
                            tmp = l.get(i);
                        }
                        cambioFrame(new FrameUserLogged(l, presente), this);
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Password errata.", "ATTENZIONE !", JOptionPane.ERROR_MESSAGE);
                    }
				}
				}      
			}
		}
	}
}
	
	

