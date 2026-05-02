package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;


public class ComandoFine implements Comando {
	
	private IOConsole IO = new IOConsole();
	private String direzione;
	
	@Override
	public void esegui(Partita partita) {
		IO.mostraMessaggio("Grazie di aver giocato!"); // si desidera smettere
		partita.setFinita();
	}

	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;

	}

}
