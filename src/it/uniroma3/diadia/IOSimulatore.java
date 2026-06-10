package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.List;

/*
 * Classe che simula i comandi di lettura e scrittura di Input e Output
 */
public class IOSimulatore implements IO {
	
	private List<String> righeDaLeggere;
	private int indiceRigaCorrente;
	private List<String> messaggiProdotti;

	public IOSimulatore(List<String> righeDaLeggere) {
		this.righeDaLeggere = righeDaLeggere;
		this.indiceRigaCorrente = 0;
		this.messaggiProdotti = new ArrayList<>();
	}

	@Override
	public String leggiRiga() {
		if (this.indiceRigaCorrente < this.righeDaLeggere.size()) {
			String riga = this.righeDaLeggere.get(this.indiceRigaCorrente);
			this.indiceRigaCorrente++;
			return riga;
		}
		return "fine"; // Stringa di chiusura di sicurezza
	}

	@Override
	public void mostraMessaggio(String messaggio) {
		// Cattura il messaggio all'interno della collezione per l'analisi del test case
		this.messaggiProdotti.add(messaggio);
	}
	
	public List<String> getMessaggiProdotti() {
		return this.messaggiProdotti;
	}
}
