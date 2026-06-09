package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {

	private Stanza stanza;
	private Stanza stanzaAdiacente;
	private Attrezzo attrezzo;
	
	@BeforeEach
	public void setUp(){
		this.stanza = new Stanza("N11");
		this.attrezzo = new Attrezzo("penna", 2);
		this.stanzaAdiacente = new Stanza("Biblioteca");
	}
	
	@Test
	void testHasAttrezzoNull() {
		assertFalse(this.stanza.hasAttrezzo(""));
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
	
	@Test
	void testGetAttrezzoEsistente() {
		this.stanza.addAttrezzo(attrezzo);
		assertEquals(attrezzo, this.stanza.getAttrezzo("penna"));
	}
	
	@Test
	void testGetAttrezzoNonEsistente() {
		assertNull(this.stanza.getAttrezzo("martello"));
	}
	
	@Test
	void testGetAttrezzoNull() {
		assertNull(this.stanza.getAttrezzo(null));
	}
	
	// ==========================================
	// TEST RIMOZIONE ATTREZZI (removeAttrezzo)
	// ==========================================
	@Test
	void testRemoveAttrezzoEsistente() {
		this.stanza.addAttrezzo(attrezzo);
		assertTrue(this.stanza.removeAttrezzo(attrezzo));
		assertFalse(this.stanza.hasAttrezzo("penna"));
	}
	
	@Test
	void testRemoveAttrezzoNonEsistente() {
		assertFalse(this.stanza.removeAttrezzo(attrezzo));
	}
	
	@Test
	void testRemoveAttrezzoNull() {
		assertFalse(this.stanza.removeAttrezzo(null));
	}
	
	// ==========================================
	// TEST DIREZIONI E STANZE ADIACENTI
	// ==========================================
	@Test
	void testGetStanzaAdiacenteNonImpostata() {
		assertNull(this.stanza.getStanzaAdiacente("nord"));
	}
	
	@Test
	void testImpostaEGetStanzaAdiacente() {
		this.stanza.impostaStanzaAdiacente("nord", stanzaAdiacente);
		assertEquals(stanzaAdiacente, this.stanza.getStanzaAdiacente("nord"));
	}
	
	@Test
	void testImpostaStanzaAdiacenteSovrascrittura() {
		Stanza altraStanza = new Stanza("Aula N10");
		this.stanza.impostaStanzaAdiacente("nord", stanzaAdiacente);
		//Sovrascrivo la direzione nord con un'altra stanza
		this.stanza.impostaStanzaAdiacente("nord", altraStanza);
		
		assertEquals(altraStanza, this.stanza.getStanzaAdiacente("nord"));
	}
}
