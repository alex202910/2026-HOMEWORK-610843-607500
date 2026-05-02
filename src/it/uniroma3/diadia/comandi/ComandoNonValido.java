package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando {
	
	private IOConsole IO = new IOConsole();
	private String direzione;
	
	@Override
	public void esegui(Partita partita) {
		if(direzione==null) {
			IO.mostraMessaggio("Comando non Valido. Riprova.");
			return;
		}
	}

	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;

	}

}
