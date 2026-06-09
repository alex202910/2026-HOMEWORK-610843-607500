package it.uniroma3.diadia.giocatore;

import java.util.Comparator;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Implementa un comparatore esterno speculare a ComparatorePerPagine delle dispense.
 * Gestisce l'ordinamento per peso crescente e, a parità di peso, per nome in ordine alfabetico.
 */
public class ComparatorePerPesoENome implements Comparator<Attrezzo> {

	@Override
	public int compare(Attrezzo a1, Attrezzo a2) {
		//Confronto sul peso
		int differenzaPeso = a1.getPeso() - a2.getPeso();
		if (differenzaPeso != 0) {
			return differenzaPeso;
		}
		
		//Confronto secondario se i pesi sono uguali: si delega all'ordinamento naturale del nome
		return a1.compareTo(a2);
	}
}