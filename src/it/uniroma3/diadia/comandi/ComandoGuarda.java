package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

/*
 * stampa le informazioni sulla stanza corrente e sullo stato della partita
 */
public class ComandoGuarda implements Comando {

	private String nomeComando = "guarda";
	private String parametro;
	private IOConsole IO = new IOConsole();
	
	@Override
	public void esegui(Partita partita) {
		IO.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		IO.mostraMessaggio("CFU = " + partita.getGiocatore().getCfu());

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
