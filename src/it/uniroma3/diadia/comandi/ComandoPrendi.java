package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando {
	
	private String nomeAttrezzo;
	private IOConsole IO = new IOConsole();
	
	@Override
	public void esegui(Partita partita) {
		
		if (nomeAttrezzo != null) {

			if (partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo) != null) {

				Attrezzo AttrezzoDaEliminare = partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
				if (partita.getGiocatore().getBorsa().addAttrezzo(AttrezzoDaEliminare)) {
					partita.getStanzaCorrente().removeAttrezzo(AttrezzoDaEliminare);
					IO.mostraMessaggio("l'oggetto" + " " + nomeAttrezzo + " " + "è stato preso dalla stanza");
				} else
					IO.mostraMessaggio("la borsa è troppo piena, svuotala");
			} else
				IO.mostraMessaggio("l'oggetto" + " " + nomeAttrezzo + " " + "non è stato trovato nella stanza");
		} else
			IO.mostraMessaggio("Quale attrezzo prendi?");

	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;

	}

}
