package screens;

import javax.swing.JFrame;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Classe che implementa un JFrame, un ActionListener e un WindowsListener.
 * Ogni classe Frame appartenente al package screens, estende questa classe.
 * 
 * @author Luca Carnevale - 131694
 * @version 1.0
 */
public class MyFrame extends JFrame implements ActionListener, WindowListener {

	/**
	 * Metodo del costruttore della classe MyFrame; 
	 * alla chiusura del frame, il programma termina.
	 */
	public MyFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private static final long serialVersionUID = 1L;
	
	@Override
	public void actionPerformed(ActionEvent e) {}

	/**
	 * Questo metodo viene usato per passare da un frame all' altro.
	 * @param f1 frame visibile
	 * @param f2 frame da chiudere
	 */
	public void cambioFrame(MyFrame f1, MyFrame f2) {
		f2.setVisible(false);
        f2.dispose();

        f1.setVisible(true);
        f1.pack();
        f1.setLocationRelativeTo(null);
	}

	/**
	 * Tra tutti questi metodi implementati, solo il secondo darà usato per 
	 * intercettare la chiusura del frame e salvare quindi i dati.
	 */
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	}

