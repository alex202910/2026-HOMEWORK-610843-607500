package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando {
	
	private String nomeComando = "posa";
	private String nomeAttrezzo;
	
	@Override
	public void esegui(Partita partita, IO IO) {
		
		if (nomeAttrezzo != null) {

			if (partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo) != null) {

				Attrezzo AttrezzoDaPosare = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
				if (partita.getStanzaCorrente().addAttrezzo(AttrezzoDaPosare)) {
					partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
					IO.mostraMessaggio("l'oggetto" + " " + nomeAttrezzo + " " + "è stato posato");
				} else
					IO.mostraMessaggio("la stanza è troppo piena, prova altrove");
			} else
				IO.mostraMessaggio("l'oggetto" + " " + nomeAttrezzo + " " + "non è presente nella tua borsa");
		} else
			IO.mostraMessaggio("Quale oggetto vuoi posare?");
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;

	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

	@Override
	public String getNome() {
		return this.nomeComando;
	}

}
