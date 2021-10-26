package thread;

import spedizioni.ListaSpedizioni;
import spedizioni.SpedizioneNormale;
import tabelle.TabellaSpedizioni;
import java.util.Random;

/**
 * Classe che implementa un thread che sarà utilizzato per modificare
 * lo stato delle speidizioni.
 * 
 * @author Luca Carnevale - 131694
 * @version 1.0
 */
public class ThreadSpedizioni extends Thread {
	
	/**
	 * Parametri su cui il thread lavorerà, ovvero lista delle spedizioni, tabella delle spedizioni,
	 * e tutti i dati relativi a queste ultime, ovvero stato, indice spedizione.
	 */
	private ListaSpedizioni lista;
	private TabellaSpedizioni tabella;
	private SpedizioneNormale spedizione;
	private int indice_spedizione;
	private String stato_spedizione;
	private int random;
	private Random rdm;
	
	/**
	 * Metodo del costruttore del thread.
	 * @param t tabella su cui quest' ultimo lavorerà.
	 */
	public ThreadSpedizioni(TabellaSpedizioni t) {
		lista = t.getLista();
		tabella = t;
	}
	
	/**
	 * Metodo di avvio del thread.
	 * Inizia generando un numero casuale di spedizione, da 0 all' indice massimo delle spedizioni
	 * presenti; dopodiché genererà un altro numero casuale tra 0 e il valore random impostato (300)
	 * e in base a quest' ultimo, si controllerà il range di valori in cui è incluso per modificare
	 * la spedizione di indice indice_spedizione.
	 */
	@Override
	public void run() {
		
		rdm = new Random();
		
		indice_spedizione = rdm.nextInt(lista.getNumSpedizioni());
		System.out.println("Numero spedizione scelto : " + indice_spedizione);
		spedizione = lista.getSpedizione(indice_spedizione);
		stato_spedizione = spedizione.getStato();
		
		random = rdm.nextInt(300);
		System.out.println("Numero random : " + random);
		
		
	       if (random < 100){
	           if (stato_spedizione.equals("IN PREPARAZIONE")){
	               spedizione.setStato("IN TRANSITO");
	                tabella.fireTableDataChanged();
	           }
	       }

	       
	       if (random > 100 && random < 200){
	           if (stato_spedizione.equals("IN TRANSITO")){
	               spedizione.setStato("CONSEGNATA");
	               tabella.fireTableDataChanged();
	           }
	       }

	       
	       if (random > 200){
	           if (stato_spedizione.equals("IN TRANSITO") || stato_spedizione.equals("IN PREPARAZIONE")){
	               spedizione.setStato("FALLITA");
	               tabella.fireTableDataChanged();
	           }
	       }

	       
	       if (random > 200 && random < 300){
	           if (stato_spedizione.equals("RIMBORSO RICHIESTO")){
	               spedizione.setStato("RIMBORSO EROGATO");
	               tabella.fireTableDataChanged();
	           }
	       }
	}
}
