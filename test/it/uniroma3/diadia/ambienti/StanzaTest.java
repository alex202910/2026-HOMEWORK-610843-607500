package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {

	private Stanza stanza;
	private Attrezzo attrezzo;
	
	@BeforeEach
	public void setUp(){
		this.stanza = new Stanza("N11");
		this.attrezzo = new Attrezzo("penna", 2);
	}
	
	@Test
	void testHasAttrezzoStanzaVuota() {
		assertFalse(this.stanza.hasAttrezzo("penna"));
	}
	
	@Test
	void testHasAttrezzoStanzaNonVuota() {
		assertFalse(this.stanza.hasAttrezzo("penna"));
		this.stanza.addAttrezzo(attrezzo);
		assertTrue(this.stanza.hasAttrezzo("penna"));
	}
	
	@Test
	void testStanzaConNumeroMassimoAttrezzi() {
		for(int i=0; i<10; i++) {
			this.stanza.addAttrezzo(attrezzo);
		}
		assertFalse(this.stanza.addAttrezzo(attrezzo));
	}
	
}
