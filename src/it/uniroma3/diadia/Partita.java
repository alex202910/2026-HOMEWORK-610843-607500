package it.uniroma3.diadia;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version HW1
 */

public class Partita {
	
	private Stanza stanzaCorrente;
	private boolean finita;
	private Labirinto labirinto;
	private Giocatore giocatore;
	
	/*
	 * Costruttore per Partita
	 * 
	 * @return as
	 * 
	 */
	public Partita(){
		this.labirinto = new Labirinto();
		this.giocatore = new Giocatore();
		this.finita = false;
		this.stanzaCorrente = this.labirinto.getStanzaIniziale();
	}
	
	public Giocatore getGiocatore() {
        return this.giocatore;
    }

	public Labirinto getLabirinto() {
		return this.labirinto;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}
	
	/**
	 * Imposta la partita come finita
	 */
	public void setFinita() {
		this.finita = true;
	}
	
	/**
	 * @return vero se e solo se è stato raggiunto la Stanza Vincente
	 */
	public boolean vinta() {
		return this.labirinto.getStanzaIniziale() == this.labirinto.getStanzaFinale();
	}
	
	/**
	 * @return vero se e solo se i CFU del giocatore NOn sono a ZERO
	 */
	public boolean giocatoreIsVivo() {
		return this.giocatore.getCfu() > 0;
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || !giocatoreIsVivo();
	}
}
