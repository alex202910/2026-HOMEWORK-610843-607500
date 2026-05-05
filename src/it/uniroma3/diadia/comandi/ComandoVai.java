package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Cerca di andare in una direzione. Se c'e' una stanza ci entra e ne stampa il
 * nome, altrimenti stampa un messaggio di errore
 */
public class ComandoVai implements Comando {
	
	private String nomeComando = "vai";
	private String direzione;

	/**
	 * esecuzione del comando
	 */
	@Override
	public void esegui(Partita partita, IO IO) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		
		if(direzione==null) {
			IO.mostraMessaggio("Dove vuoi andare? Devi specificare una direzione");
			return;
		}
		
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(direzione);
		if(prossimaStanza==null) {
			IO.mostraMessaggio("Direzione inesistente");
			return;
		}
		
		partita.setStanzaCorrente(prossimaStanza);
		IO.mostraMessaggio(partita.getStanzaCorrente().getNome());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
		
		IO.mostraMessaggio("CFU = " + partita.getGiocatore().getCfu());
	}
	
	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;
	}
	
	@Override
	public String getParametro() {
		return this.direzione;
	}

	@Override
	public String getNome() {
		return this.nomeComando;
	}
}
