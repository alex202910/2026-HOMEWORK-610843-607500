package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;


/**
 * Stampa informazioni di aiuto.
 */
public class ComandoAiuto implements Comando {
	
	static final private String[] elencoComandi = { "vai", "aiuto", "fine", "prendi", "posa", "guarda"};
	private IOConsole IO = new IOConsole();
	private String parametro;
	
	@Override
	public void esegui(Partita partita) {
		for (int i = 0; i < elencoComandi.length; i++)
			IO.mostraMessaggio(elencoComandi[i] + " ");
		IO.mostraMessaggio("");
		return;
	}

	@Override
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

}
