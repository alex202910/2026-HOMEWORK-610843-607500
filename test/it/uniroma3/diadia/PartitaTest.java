package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

class PartitaTest {
	
	private Partita partita;
	
	@BeforeEach
	public void setUp() {
		this.partita = new Partita();
	}
	@Test
	void testNuovaPartitaNonVinta() {
		assertFalse(this.partita.vinta());
	}
	
	@Test
	void testVerificaPresenzaCfu() {
		assertFalse(this.partita.isFinita());
	}
	
	@Test
	void testNuovaPartitaPoiFinita() {
		this.partita.setFinita();
		assertTrue(this.partita.isFinita());
	}
}
