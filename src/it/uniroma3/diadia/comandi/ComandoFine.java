package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;


public class ComandoFine implements Comando {
	
	private String nomeComando = "fine";
	private String parametro;
	
	
	@Override
	public void esegui(Partita partita, IO IO) {
		IO.mostraMessaggio("Grazie di aver giocato!"); // si desidera smettere
		partita.setFinita();
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
