package spedizioni;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe che implementa una lista di spedizioni, contenente sia le normali
 * sia le spedizioni assicurate con l' uso dei generics.
 * 
 * @author Luca Carnevale - 131694
 * @version 1.0
 */
public class ListaSpedizioni implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Lista di spedizioni.
	 */
	private List <SpedizioneNormale> Spedizioni;
	
	/**
	 * Metodo costruttore che crea e ritorna un array di spedizioni.
	 */
	public ListaSpedizioni() {
		Spedizioni = new ArrayList<SpedizioneNormale>();
	}
	
	/**
	 * Metodo che aggiunge una spedizione alla lista.
	 * @param s Indice della spedizione da aggiungere.
	 */
	public void Aggiungi(SpedizioneNormale s) {
		Spedizioni.add(s);
	}
	
	/**
	 * Metodo che cancella una spedizione dalla lista.
	 * @param s Indice dela spedizione da cancellare.
	 */
	public void Cancella(SpedizioneNormale s) {
		Spedizioni.remove(s);
	}
	
	/**
	 * Metodo che ritorna la spedizione di indice index.
	 * @param index Indice della spedizione.
	 * @return Spedizione di indice index.
	 */
	public SpedizioneNormale getSpedizione(int index) {
		return Spedizioni.get(index);
	}
	
	/**
	 * Metodo che restituisce il numero di spedizioni contenute nela lista.
	 * @return Numero di spedizioni.
	 */
	public int getNumSpedizioni() {
		return Spedizioni.size();
	}


}