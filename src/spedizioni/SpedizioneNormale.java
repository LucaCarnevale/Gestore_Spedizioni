package spedizioni;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe che descrive una generica spedizione.
 * 
 * @author Luca Carnevale - 131694
 * @version 1.0
 */
public class SpedizioneNormale {

	/**
	 * Qui sono definiti tutti i campi necessari per l' inserimento
	 * di una spediizone.
	 */
	private String NomeUtente;
	private String Codice;
	private String Destinazione;
	private int Peso;
	private String DataSpedizione;
	private String Stato;
	
	/**
	 * Metodo costruttore che mi permette di creare una spedizione.
	 * Viene implementato nella schermata principale dedicata all' utente.
	 * @param Destinazione Luogo di arrivo.
	 * @param Peso Peso del pacco.
	 */
	public SpedizioneNormale(String Destinazione, int Peso) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String data = sdf.format(new Date());
		setDataSpedizione(data);
		setDestinazione(Destinazione);
		setPeso(Peso);
		setStato("IN PREPARAZIONE");
	}
	
	/**
	 * Metodo costruttore usato per la creazione di spedizioni per le quali
	 * i dati vengono letti da file.
	 * @param Nome Nome dell' utente.
	 * @param Codice Codice tracciabilità.
	 * @param Destinazione Luogo di arrivo.
	 * @param Peso Peso del pacco.
	 * @param Data Data di spedizione.
	 * @param Stato Stato della spedizione (IN PREPARAZIONE, IN TRANSITO, CONSEGNATA O FALLITA).
	 */
	public SpedizioneNormale(String Nome, String Codice, String Destinazione, int Peso, String Data, String Stato) {
		setNomeUtente(Nome);
		setCodice(Codice);
		setDestinazione(Destinazione);
		setPeso(Peso);
		setDataSpedizione(Data);
		setStato(Stato);
	}

	/**
	 * Metodo che ritorna la destinazione.
	 * @return Destinazione
	 */
	public String getDestinazione() {
		return Destinazione;
	}
	
	/**
	 * Metodo che imposta la destinazione della spedizione.
	 * @param destinazione Luogo di arrivo.
	 */
	public void setDestinazione(String destinazione) {
		Destinazione = destinazione;
	}

	/**
	 * Metodo che ritorna il codice di tracciabilità.
	 * @return Codice
	 */
	public String getCodice() {
		return Codice;
	}

	/**
	 * Metodo che imposta il codice della spedizione.
	 * @param codice Codice di tracciabilità.
	 */
	public void setCodice(String codice) {
		Codice = codice;
	}

	/**
	 * Metodo che ritorna il peso della spedizione.
	 * @return Peso
	 */
	public int getPeso() {
		return Peso;
	}

	/**
	 * Metodo che imposta il peso del pacco.
	 * @param peso Peso del pacco.
	 */
	public void setPeso(int peso) {
		Peso = peso;
	}

	/**
	 * Metodo che ritorna la data di immissione nel sistema.
	 * @return Data della spedizione.
	 */
	public String getDataSpedizione() {
		return DataSpedizione;
	}

	/**
	 * Metodo che imposta la data di spedizione.
	 * @param dataSpedizione Data della spedizione.
	 */
	public void setDataSpedizione(String dataSpedizione) {
		DataSpedizione = dataSpedizione;
	}

	/**
	 * Metodo che ritorna lo stato della spedizione.
	 * @return Stato
	 */
	public String getStato() {
		return Stato;
	}

	/**
	 * Metodo che imposta lo stato della spedizione.
	 * @param stato Stato della spedizione.
	 */
	public void setStato(String stato) {
		Stato = stato;
	}

	/**
	 * Metodo che ritorna il nome dell' utente.
	 * @return NomeUtente.
	 */
	public String getNomeUtente() {
		return NomeUtente;
	}

	/**
	 * Metodo che imposta il nome dell' utente.
	 * @param nomeUtente Nome dell' utente.
	 */
	public void setNomeUtente(String nomeUtente) {
		NomeUtente = nomeUtente;
	}
	
	/**
	 * Variabile con cui segnalo se è possibile o meno richiedere un rimborso per 
	 * una spedizione assicurata.
	 * @return true se il rimborso è possibile, false altrimenti.
	 */
	public boolean Rimborso(){
		return false;
	}

	/**
	 * Metodo usato per controllare se una spedizione si trova in uno stato
	 * finale, quindi se è possibile poterla rimuovere o meno dalla lista spedizioni.
	 * @return true se è possibile rimuoverla, false altrimenti.
	 */
	public boolean StatoFinale(){
		String stato = getStato();
		if (stato.equals("CONSEGNATA") || Stato.equals("FALLITA"))
			return true;
		return false;
	}


	/**
	 * Ritorna una Stringa con tutti i dati di una spedizione.
	 */
	public String toString(){
		return ("NORMALE\n"+getNomeUtente()+"\n"+getCodice()+"\n"+getDestinazione()+"\n"+getPeso()+"\n"+
				getDataSpedizione()+"\n"+getStato()+"\n");
	}
	
	public int getValoreAssicurazione() {
		return 0;
	}
}
