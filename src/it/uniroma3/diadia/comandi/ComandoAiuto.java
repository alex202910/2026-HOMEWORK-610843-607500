package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;


/**
 * Stampa informazioni di aiuto.
 */
public class ComandoAiuto implements Comando {
	
	private String nomeComando = "aiuto";
	private String parametro;
	
	static final private String[] elencoComandi = { "vai", "aiuto", "fine", "prendi", "posa", "guarda"};
	
	@Override
	public void esegui(Partita partita, IO IO) {
		for (int i = 0; i < elencoComandi.length; i++)
			IO.mostraMessaggio(elencoComandi[i] + " ");
		IO.mostraMessaggio("");
		return;
	}

	@Override
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	
	@Override
	public String getParametro() {
		return this.parametro;
	}

	@Override
	public String getNome() {
		return this.nomeComando;
	}
}
