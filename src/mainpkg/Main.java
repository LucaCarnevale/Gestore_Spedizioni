package mainpkg;
import screens.*;

/**
 * Classe contenente il main; da qui viene creato e reso visibile un FrameIniziale.
 * 
 * @author Luca Carnevale - 131694
 * @version 1.0
 *
 */

public class Main {

	public static void main(String[] args) {
		FrameBenvenuto frame = new FrameBenvenuto();
		frame.setVisible(true);
		frame.pack();
		frame.setLocationRelativeTo(null);

	}

}
