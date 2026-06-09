package it.uniroma3.diadia.ambienti;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version HW3
*/

public class Stanza {
	
	private static final int NUMERO_MASSIMO_ATTREZZI = 10;
	private static final int NUMERO_MASSIMO_DIREZIONI = 4;
	
	private String nome;
    private List<Attrezzo> attrezzi;
    private Map<String, Stanza> stanzeAdiacenti;
    //La chiave è la direzione (nord, sud, ..) e il valore è la stanza adiacente
    /*
     * Eliminati:
     * - numeroAttrezzi
     * - numeroStanzaAdiacenti
     * - String[] direzioni
     */    
   
    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     */
    public Stanza(String nome) {
        this.nome = nome;
        this.stanzeAdiacenti = new HashMap<>();
        this.attrezzi = new ArrayList<>();
    }

    /**
     * Imposta una stanza adiacente.
     *
     * @param String direzione, direzione in cui sara' posta la stanza adiacente.
     * @param Stanza stanza, adiacente nella direzione indicata dal primo parametro.
     */
    public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
        this.stanzeAdiacenti.put(direzione, stanza);

        //Rimosso tutto il ciclo lineare
    }

    /**
     * @param direzione
     * @return la stanza adiacente nella direzione specificata, null altrimenti
     */
    
	public Stanza getStanzaAdiacente(String direzione) {
        return this.stanzeAdiacenti.get(direzione);
        //Rimosso tutto il ciclo lineare	
	}

    /**
     * Restituisce la nome della stanza.
     * @return il nome della stanza
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Restituisce la descrizione della stanza.
     * @return la descrizione della stanza
     */
    public String getDescrizione() {
        return this.toString();
    }

    /**
     * Restituisce la collezione di attrezzi presenti nella stanza.
     * @return la collezione di attrezzi nella stanza.
     */
    public Collection<Attrezzo> getAttrezzi() {
        return this.attrezzi;
    }

    /**
     * Mette un attrezzo nella stanza.
     * @param Attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
        if(attrezzo == null)
        	return false;
        
        if(this.attrezzi.size() >= NUMERO_MASSIMO_ATTREZZI)
        	return false;
        
        return this.attrezzi.add(attrezzo);
        //Rimosso tutto il ciclo lineare
    }

    /**
	* Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	* @return true se l'attrezzo esiste nella stanza, false altrimenti.
	*/
	public boolean hasAttrezzo(String nomeAttrezzo) {
		if(nomeAttrezzo == null)
			return false;
		return this.getAttrezzo(nomeAttrezzo) != null;
	}

	/**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return Attrezzo l'attrezzo presente nella stanza.
     * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		if(nomeAttrezzo == null)
			return null;
		
		Iterator<Attrezzo> it = this.attrezzi.iterator();
		while(it.hasNext()) {
			Attrezzo a = it.next();
			if(a.getNome().equals(nomeAttrezzo)) {
				return a;
			}
		}
		return null;
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		if(attrezzo == null)
				return false;
		return this.attrezzi.remove(attrezzo);
		//Rimosso tutto il ciclo lineare
	}

	public Collection<String> getDirezioni() {
		return this.stanzeAdiacenti.keySet();
    }
	
	/**
	* Restituisce una rappresentazione stringa di questa stanza,
	* stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	* @return la rappresentazione stringa
	*/
    public String toString() {
    	StringBuilder risultato = new StringBuilder();
    	risultato.append(this.nome);
    	risultato.append("\nUscite: ");
    	for (String direzione : this.stanzeAdiacenti.keySet()) {
    			risultato.append(" " + direzione);
    	}
    	risultato.append("\nAttrezzi nella stanza: ");
    	for (Attrezzo attrezzo : this.attrezzi) {
    		if(attrezzo!=null)
    		risultato.append(attrezzo.toString()+" ");
    	}
    	return risultato.toString();
    }
}