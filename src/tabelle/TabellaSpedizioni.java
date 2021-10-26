package tabelle;

import javax.swing.table.AbstractTableModel;
import spedizioni.*;

/**
 * Classe che definisce la tabella, estendendo i metodi della funzione 
 * AbstractTabelModel da cui eredita anche le caratteristiche.
 * 
 * @author Luca Carnevale - 131694
 * @version 1.0
 */
public class TabellaSpedizioni extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Nomi delle varie colonne della tabella.
	 */
	private String[] ColName = {"Nome", "Codice", "Destinazione", "Peso", "Data", "Stato", "Valore Assicurazione"};
	
	/**
	 * Lista di spedizioni con cui vado a popolare la tabella.
	 */
	private ListaSpedizioni l;
	
	/**
	 * Metodo con cui costruisco la tabella.
	 * @param l Lista di utenti.
	 */
	public TabellaSpedizioni(ListaSpedizioni l) {
		this.l = l;
	}
	
	/**
	 * Metodo che ritorna il numero di colonne della tabella.
	 */
	public int getColumnCount() {
		return ColName.length;
	}
	
	/**
	 * Metodo che ritorna il numero di righe della tabella.
	 */
	public int getRowCount() {
		return l.getNumSpedizioni();
	}
	
	/**
	 * Metodo che ritorna il contenuto di una determinata combinazione riga/colonna.
	 */
	public Object getValueAt(int row, int col) {
		SpedizioneNormale s = l.getSpedizione(row);
		
		/**
		 * Stringa corrispondente alla colonna.
		 */
		switch(col) {
			case 0 :
				return s.getNomeUtente();
			case 1 :
				return s.getCodice();
			case 2 :
				return s.getDestinazione();
			case 3 :
				return s.getPeso();
			case 4 :
				return s.getDataSpedizione();
			case 5 : 
				return s.getStato();
			case 6 :
				return s.getValoreAssicurazione();
			default :
				return null;
		}
	}
	
	/**
	 * Metodo che ritorna il nome di una determinata colonna.
	 */
	public String getColumnName(int col) {
		return ColName[col];
	}
	
	/**
	 * Metodo che ritorna vero se una cella è sovrascrivibile, falso altrimenti.
	 */
	public boolean isCellEditable(int row, int col) {
		if(col == 5)
			return true;
		return false;
	}
	
	/**
	 * Metodo usato per impostare lo stato di una spedizione.
	 */
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		String NuovoValore = (String) aValue;
		SpedizioneNormale s = l.getSpedizione(rowIndex);
		s.setStato(NuovoValore);
		fireTableDataChanged();
	}
	
	/**
	 * Metodo che ritorna la lista della spedizioni che popola la tabella.
	 * @return l Lista di spedizioni
	 */
	public ListaSpedizioni getLista() {
		return l;
	}
}