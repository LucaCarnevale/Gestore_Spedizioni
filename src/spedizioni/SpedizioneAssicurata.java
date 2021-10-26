package spedizioni;

/**
 * Classe che implementa una spedizione assicurata. Estende la classe Spedizione, 
 * quindi ne eredita tutti gli attributi e alla quale viene aggiunto il valore val 
 * relativo all' assicurazione.
 * 
 * @author Luca Carnevale - 131694
 * @version 1.0
 */
public class SpedizioneAssicurata extends SpedizioneNormale {

	/**
	 * Valore della spedizione.
	 */
	private int ValoreAssicurazione;
	
	/**
	 * Metodo usato per creare una spedizione Assicurata.
	 * @param destinazione Destinazione della spedizione
	 * @param peso Peso del pacco.
	 * @param val Valore assicurazione
	 */
	public SpedizioneAssicurata(String destinazione, int peso, int val) {
		super(destinazione, peso);
		setValoreAssicurazione(val);

	}

	/**
	 * Metodo costruttore usato per creare una spedizione Assicurata, leggendo però
	 * tutti i dati da file.
	 * @param Nome Nome dell' utente.
	 * @param Codice Codice di tracciabilità.
	 * @param Destinazione Luogo di arrivo.
	 * @param Peso Peso del pacco.
	 * @param Data Data di spedizione.
	 * @param Stato Stato della spedizione.
	 * @param val Valore dell' assicurazione.
	 */
	public SpedizioneAssicurata(String Nome, String Codice, String Destinazione, int Peso, String Data, String Stato, int val) {
		super(Nome, Codice, Destinazione, Peso, Data, Stato);
		ValoreAssicurazione = val;
	}
	
	/**
	 * Metodo usato per ritornare il valore della spedizione assicurata.
	 */
	@Override
	public int getValoreAssicurazione() { 
		return ValoreAssicurazione;
		}

	/**
	 * Metodo usato per impostare il valore della spedizione assicurata.
	 * @param val Valore della spedizione assicurata.
	 */
	public void setValoreAssicurazione(int val){
		ValoreAssicurazione = val;
	}

	/**
	 * Stringa che ritorna tutti i dati relativi ad una spedizione assicurata.
	 */
	@Override
	public String toString(){
		return ("ASSICURATA"+"\n"+getValoreAssicurazione()+"\n"+ getNomeUtente()+"\n"+getCodice()+"\n"+getDestinazione()+"\n"+
				getPeso()+"\n"+getDataSpedizione()+"\n"+getStato()+"\n");
	}

	/**
	 * Metodo usato per controllare se una spedizione si trova in uno stato finale
	 * per decidere se è possibile o meno rimuoverla dalla lista.
	 */
	@Override
	public boolean StatoFinale() {
		String stato = this.getStato();
		if (stato.equals("CONSEGNATA")|| stato.equals("RIMBORSO EROGATO"))
			return true;
		return false;
	}

	/**
	 * Metodo usato per vedere se una spedizione assicurata si trova in uno stato 
	 * finale FALLITO, in modo tale da permettere al cliente di poter richiedere 
	 * il rimborso.
	 */
	@Override
	public boolean Rimborso(){
		String stato = this.getStato();
		if (stato.equals("FALLITA"))
			return true;
		return false;
	}

}

