package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

/*
 * stampa le informazioni sulla stanza corrente e sullo stato della partita
 */
public class ComandoGuarda implements Comando {
	
	private IOConsole IO = new IOConsole();
	private String parametro;
	
	@Override
	public void esegui(Partita partita) {
		IO.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		IO.mostraMessaggio("CFU = " + partita.getGiocatore().getCfu());

	}

	@Override
	public void setParametro(String parametro) {
		this.parametro = parametro;

	}

}
