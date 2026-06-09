package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPosaTest {
	
	private Partita partita;
	private Attrezzo axe;
	private Stanza atrio;
	private IOConsole io;

	@BeforeEach
	public void setUp() {
		partita = new Partita();
		axe = new Attrezzo("axe",5);
		atrio = partita.getStanzaCorrente();
		io = new IOConsole();
	}

	@Test
	void testPosaOggettoEsistente() {
		partita.getGiocatore().getBorsa().addAttrezzo(axe);

		Comando comando = new ComandoPosa();
		comando.setParametro("axe"); 
		comando.esegui(partita, io);
		
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("axe")); //rimosso dalla borsa
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("axe"));		   //aggiunto nell stanza
	}
	
	@Test
	void testPosaOggettoNonEsistente() {
		partita.getGiocatore().getBorsa().addAttrezzo(axe);
		
		Comando comando = new ComandoPosa();
		comando.setParametro("lanterna"); 
		comando.esegui(partita, io);
		
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("lanterna"));
	}
	
	@Test
	void testPosaOggettoInUnaStanzaPiena() {
		
		int maxAttrezziStanza = atrio.getAttrezzi().size();
		for(int i=0; i<maxAttrezziStanza; i++) {
			atrio.addAttrezzo(new Attrezzo("finto" + i, 1)); //vengono aggiunti gli attrezzi finto0, finto1, ...
		}
		
		//il giocatore ha l'axe da posare
		partita.getGiocatore().getBorsa().addAttrezzo(axe);
		
		Comando comando = new ComandoPosa();
		comando.setParametro("axe"); 
		comando.esegui(partita, io);
		
		
		//l'axe rimane nella borsa per la stanza in cui la vuole posare (l'atrio) è piena
		//e di conseguenza l'axe non si trova nell'atrio
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("axe")); 
		assertFalse(atrio.hasAttrezzo("axe")); 
	}
}
