package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {
	
	@Test
	public void testStanzaAdiacenteBloccata() {
	    StanzaBloccata atrio = new StanzaBloccata("Atrio", "nord", "passepartout");
	    Stanza biblioteca = new Stanza("Biblioteca");
	    atrio.impostaStanzaAdiacente("nord", biblioteca);

	    assertEquals(atrio, atrio.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testStanzaAdiacenteAccessibilePerPresenzaDelAttrezzo() {
	    StanzaBloccata atrio = new StanzaBloccata("Atrio", "nord", "passepartout");
	    Stanza biblioteca = new Stanza("Biblioteca");
	    Attrezzo chiave = new Attrezzo("passepartout",1);
	    
	    atrio.impostaStanzaAdiacente("nord", biblioteca);
	    atrio.addAttrezzo(chiave);
	    
	    assertEquals(biblioteca, atrio.getStanzaAdiacente("nord"));
	}

}
