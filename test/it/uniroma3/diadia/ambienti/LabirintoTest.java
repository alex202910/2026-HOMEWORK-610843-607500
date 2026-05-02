package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LabirintoTest {
	
	private Labirinto labirinto;
	private Stanza biblioteca;
	private Stanza atrio;
	
	@BeforeEach
	public void setUp() {
		this.labirinto = new Labirinto();
		this.biblioteca = new Stanza("Biblioteca");
		this.atrio = new Stanza("Atrio");
	}
	
	@Test
	void testVerificaStanzaInizialeNotNull() {
		assertNotNull(this.labirinto.getStanzaIniziale());
	}
	
	@Test
	void testVerificaStanzaVincenteNotNull() {
		assertNotNull(this.labirinto.getStanzaFinale());
	}
	
	@Test
	void testVerificaStanzaVincente() {
		assertTrue(this.labirinto.getStanzaFinale().getNome().equals(biblioteca.getNome()));
	}
	
	@Test
	void testVerificaStanzaCorrente() {
		assertTrue(this.labirinto.getStanzaIniziale().getNome().equals(atrio.getNome()));
	}
}
