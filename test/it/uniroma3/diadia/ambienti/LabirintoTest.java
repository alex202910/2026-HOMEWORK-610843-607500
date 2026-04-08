package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertNotNull;
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
	void testVerificaStanzaCorrenteNonNulla() {
		assertNotNull(this.labirinto.getStanzaCorrente());
	}
	@Test
	void testVerificaStanzaVincenteNonNulla() {
		assertNotNull(this.labirinto.getStanzaVincente());
	}
	@Test
	void testVerificaStanzaVincente() { 
		assertTrue(this.labirinto.getStanzaVincente().getNome().equals(biblioteca.getNome()));
		
	}
	@Test
	void testVerificaStanzaCorrente() {
		assertTrue(this.labirinto.getStanzaCorrente().getNome().equals(atrio.getNome()));
	}

}
