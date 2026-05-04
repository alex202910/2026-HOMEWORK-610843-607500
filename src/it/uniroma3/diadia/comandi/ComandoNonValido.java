package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando {

	private String nomeComando = "non valido";
	private String parametro;
	
	private IOConsole IO = new IOConsole();
	
	@Override
	public void esegui(Partita partita) {
		if(parametro==null) {
			IO.mostraMessaggio("Comando non Valido. Riprova.");
			return;
		}
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
