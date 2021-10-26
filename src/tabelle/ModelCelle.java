package tabelle;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Classe che estende i metodi della funzione DefaultTableCellRenderer
 * e viene utilizzata per cambiare i colori delle spedizioni in base al loro stato.
 * 
 * @author Luca Carnevale - 131694
 * @version 1.0
 */
public class ModelCelle extends DefaultTableCellRenderer {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Metodo che eredito e sovrascrivo.
	 */
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		this.setHorizontalAlignment(JLabel.CENTER);
		
		String stato = (String) table.getValueAt(row, 5);
		
		/**
		 * Imposto un colore diverso per ogni stato delle spedizioni.
		 */
		switch(stato) {
		case "IN PREPARAZIONE" : {
			setBackground(Color.YELLOW);
			break;
		}
		
		case "IN TRANSITO" : {
			setBackground(Color.ORANGE);
			break;
		}
		case "CONSEGNATA" : {
			setBackground(Color.GREEN);
			break;
		}
		case "FALLITA" : {
			setBackground(Color.RED);
			break;
		}
		case "RICHIESTA RIMBORSO" : {
			setBackground(Color.BLUE);
			break;
		}
		case "RIMBORSO EROGATO" : {
			setBackground(Color.GREEN);
			break;
		}
		default : {
			setBackground(Color.WHITE);
			break;
		}
		}
		return this;
	}
}

